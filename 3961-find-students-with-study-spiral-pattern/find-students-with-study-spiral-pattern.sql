# Write your MySQL query statement below


/*
Study Spiral Pattern - Explanation

1. BASE:
   Uses LAG() to get the previous session date for each student.
   If the gap between consecutive sessions is greater than 2 days,
   the current session starts a new pattern group.
   A running MAX() propagates the latest pattern_start date to
   pattern_date, keeping sessions within the same continuous group.

2. CONSECUTIVE_DAY:
   Uses ROW_NUMBER() partitioned by student, pattern group, and
   subject to number each occurrence of a subject independently.
   For example, Math sessions receive ranks 1, 2, 3, and so on.

3. SUBJECT_PATTERN:
   Groups sessions by student, pattern group, and occurrence rank.
   GROUP_CONCAT() builds an ordered subject sequence, COUNT()
   calculates the number of subjects, and SUM() calculates the
   total study hours for that occurrence rank.
   Example:
     Rank 1: Math,Physics,Chemistry
     Rank 2: Math,Physics,Chemistry
   Matching sequences indicate a potential repeating cycle.

4. SEQUENTIAL_PAT:
   Uses LAG() to compare each subject pattern with the previous
   pattern. When the pattern changes, cycle_repeat stores the
   current rank; otherwise, it is NULL.
   A running MAX() propagates the latest non-NULL rank into
   cycle_seq_count, grouping consecutive matching patterns.

5. FINAL AGGREGATION:
   Filters for patterns containing more than 2 subjects.
   GROUP BY groups matching patterns, while HAVING COUNT(*) > 1
   requires the pattern to occur at least twice.
   MAX(total_subjects) gives the cycle length, and
   SUM(total_hours) calculates total study hours across the
   repeated patterns.

6. STUDENT JOIN AND SORTING:
   Joins the results with students to retrieve student_name
   and major. Results are ordered by cycle_length descending,
   followed by total_study_hours descending.

IMPORTANT:
   The scalar condition checking whether the students table
   contains more than one row is unrelated to the pattern rules
   and can be removed.
   For deterministic ordering when multiple sessions share a
   date, use ORDER BY session_date, session_id in window functions.
*/


with base as (
select *, max(pattern_start)
over (partition by student_id order by session_date rows between unbounded preceding and current row) as pattern_date
from
(
select *, case when datediff(session_date,lag(session_date) over (partition by student_id order by session_date )) <=2 then null else session_date end as pattern_start
from study_sessions
) t
),
consecutive_day as (
select *, row_number() over(partition by student_id,pattern_date,subject order by session_date) as inside_rank
from base
),
subject_pattern as (
select student_id,pattern_date,inside_rank,group_concat(subject order by session_date) as sub_pattern,count(subject) as total_subjects,sum(hours_studied) as total_hours
from consecutive_day
group by student_id,pattern_date,inside_rank
),
sequential_pat as (
select *, max(cycle_repeat)
over (partition by student_id,pattern_date order by inside_rank rows between unbounded preceding and current row) as cycle_seq_count
from(
select *, case when sub_pattern=lag(sub_pattern) over (partition by student_id,pattern_date order by inside_rank) then null else inside_rank end as cycle_repeat
from subject_pattern
) c
)


select p.student_id,s.student_name,s.major,cycle_length,total_study_hours
from (
select student_id,max(total_subjects) as cycle_length ,sum(total_hours) as total_study_hours
from sequential_pat 
where total_subjects>2
group by student_id,pattern_date,cycle_seq_count
having count(sub_pattern)>1) p join students s on p.student_id=s.student_id
where (select count(*) from students) >1
order by cycle_length desc,total_study_hours desc



# Write your MySQL query statement below

#[total time = (out time - in time)] this is the key factor here else is pretty straight forward

select event_day AS day,  emp_id, SUM(out_time - in_time) AS total_time from Employees
group BY emp_id, event_day;

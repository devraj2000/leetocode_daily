-- # Write your MySQL query statement below

-- select id , name,
-- case
-- when sex = 'm' then 'f'
-- when sex = 'f' then 'm'
-- else sex
-- end as sex, salary from Salary


UPDATE Salary
SET sex = CASE 
    WHEN sex = 'm' THEN 'f'
    WHEN sex = 'f' THEN 'm'
    ELSE sex
end
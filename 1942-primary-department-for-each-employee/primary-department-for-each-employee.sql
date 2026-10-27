# Write your MySQL query statement below
SELECT employee_id, department_id FROM Employee WHERE primary_flag='Y' OR employee_id not in (select employee_id from Employee where primary_flag = 'Y' )
# Write your MySQL query statement below

select customer_id from Customer GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (SELECT COUNT(*) FROM Product); 
# Write your MySQL query statement below
#nural join is basically its join two table based on the common collum
 
select product_id,product_name from product natural join sales group by product_id
having min(sale_date)>='2019-01-01' and max(sale_date)<='2019-03-31'
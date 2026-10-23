# Write your MySQL query statement below

-- generalised equation is (s1 - b1) + (s2 - b2) + ... (sn - bn)
-- [when operation = 'Buy' then -price ]---> this is the core logic
--  This is the core logic.
--  If you bought the stock ('Buy'), it treats the price as a negative value (money spent).
--  If you sold the stock ('Sell'), it treats the price as a positive value (money received).


select stock_name, SUM(
    case
        when operation = 'Buy' then -price
        else price
    end
) as capital_gain_loss
from Stocks
group by stock_name
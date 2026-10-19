# Write your MySQL query statement below

-- COUNT(DISTINCT partner_id) — Counts the number of unique partners for each manufacturer on each date.

-- COUNT(DISTINCT lead_id) — Counts the number of unique leads for each manufacturer on each date, ignoring duplicate IDs.

-- GROUP BY make_name, date_id — Groups records by manufacturer and date so each combination gets its own summary.

select date_id, make_name, count(distinct lead_id) as unique_leads, count(distinct partner_id) as unique_partners from DailySales group by date_id, make_name;



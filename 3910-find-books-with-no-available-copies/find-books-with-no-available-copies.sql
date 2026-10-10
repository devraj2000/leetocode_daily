# Write your MySQL query statement below


select l.book_id ,l.title,l.author,l.genre,l.publication_year, count(record_id) as current_borrowers
FROM   library_books l, borrowing_records b
where l.book_id=b.book_id and b.return_date is null
GROUP BY l.book_id,l.total_copies
       
HAVING COUNT(b.record_id) = l.total_copies
ORDER BY current_borrowers DESC,
         l.title ASC;
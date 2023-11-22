select
    BOOK_ID,
    AUTHOR_NAME,
    date_format(PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHD_DATE
from book
left join author using(author_id)
where category = '경제'
order by published_date ASC;
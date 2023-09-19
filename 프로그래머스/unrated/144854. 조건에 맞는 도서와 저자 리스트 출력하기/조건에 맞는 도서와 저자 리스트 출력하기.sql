# tablse: BOOK, AUTHOR
#'경제' 카테고리에 속하는 도서들
## 도서 ID(BOOK_ID), 저자명(AUTHOR_NAME), 출판일(PUBLISHED_DATE) 리스트
## 출판일 오름차순
## dateformat: yyyy-mm-dd
select BOOK_ID, AUTHOR_NAME, date_format(PUBLISHED_DATE, "%Y-%m-%d")
from BOOK b
join AUTHOR a on b.AUTHOR_ID = a.AUTHOR_ID
where CATEGORY = "경제"
order by PUBLISHED_DATE asc;
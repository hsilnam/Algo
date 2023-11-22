select
    p.PRODUCT_CODE,
    sum(o.SALES_AMOUNT * p.PRICE) as SALES
from product as p
left join offline_sale as o
on p.product_id = o.product_id
where offline_sale_id is not null
group by p.product_code
order by 2 DESC, 1 ASC;
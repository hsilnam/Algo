# ANIMAL_INS, ANIMAL_OUTS
# 입양을 간 기록은 있는데, 보호소에 들어온 기록이 없는 동물
## ANIMAL_ID, NAME
## id 오름차순

select ao.ANIMAL_ID, ao.NAME
from ANIMAL_OUTS ao
left outer join ANIMAL_INS ai on ai.ANIMAL_ID = ao.ANIMAL_ID
WHERE ai.ANIMAL_ID IS NULL
order by ANIMAL_ID;
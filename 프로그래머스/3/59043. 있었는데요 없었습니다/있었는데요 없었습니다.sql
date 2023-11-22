select
    ins.ANIMAL_ID,
    ins.NAME
from animal_ins as ins
left join animal_outs as outs using(animal_id)
where ins.datetime > outs.datetime
order by ins.datetime;
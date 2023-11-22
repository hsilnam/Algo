select
    ins.name,
    ins.datetime
from animal_ins as ins
left join animal_outs as outs using(animal_id)
where outs.animal_id is null
order by 2
limit 3;
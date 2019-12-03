select * from timeliste where timelistenr = 3;

select count(*) from timeliste;

select count(*) - count(utbetalt) from timeliste;

select status, count(status) from timeliste
group by status;

select count(*) as alle, count(pause) as pause
from timelistelinje;

select count(*) - count(pause) from timelistelinje;




select sum(timeantall.timeantall) from timeantall inner join timeliste
on timeantall.timelistenr = timeliste.timelistenr
where timeliste.utbetalt is null;

select distinct beskrivelse from timelistelinje
where beskrivelse like '%Test%' or beskrivelse like '%test%';

select varighet.timelistenr, varighet.linjenr, varighet, beskrivelse
from varighet inner join timelistelinje
on varighet.timelistenr = timelistelinje.timelistenr
and varighet.linjenr = timelistelinje.linjenr order by varighet desc limit 5;

select timeliste.timelistenr, count(linjenr)
from timeliste full outer join timelistelinje
on timelistelinje.timelistenr = timeliste.timelistenr
group by timeliste.timelistenr order by timelistenr asc;

select 200*(sum(timeantall.timeantall)) as utbetalt
from timeantall inner join timeliste
on timeantall.timelistenr = timeliste.timelistenr
where timeliste.utbetalt is not null;

select timelistenr, count(linjenr) from timelistelinje
where pause is null group by timelistenr order by timelistenr asc;

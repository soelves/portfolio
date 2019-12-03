-- Oppgave 1
select filmcharacter, count(filmcharacter) from filmcharacter
group by filmcharacter.filmcharacter having count(filmcharacter) > 2000
order by count(filmcharacter) desc limit 100;

-- Oppgave 2a
select title, prodyear from film
inner join filmparticipation fp on fp.filmid = film.filmid
inner join person on person.personid = fp.personid
where firstname = 'Stanley' and lastname = 'Kubrick' and parttype = 'director'
limit 100;

-- Oppgave 2b
select title, prodyear from film
natural join filmparticipation fp
natural join person
where firstname = 'Stanley' and lastname = 'Kubrick' and parttype = 'director'
limit 100;

-- Oppgave 2c
select title, prodyear from film, filmparticipation fp, person
where film.filmid = fp.filmid and person.personid = fp.personid
and firstname = 'Stanley' and lastname = 'Kubrick' and parttype = 'director'
limit 100;

-- Oppgave 3
select person.personid, firstname || ' ' ||lastname as navn, title, country
from film
inner join filmparticipation fp on fp.filmid = film.filmid
inner join person on person.personid = fp.personid
inner join filmcountry on filmcountry.filmid = film.filmid
inner join filmcharacter on filmcharacter.partid = fp.partid
where filmcharacter = 'Ingrid' and firstname = 'Ingrid'
limit 100;

-- Oppgave 4
select film.filmid id, title, count(genre) from film
full outer join filmgenre on filmgenre.filmid = film.filmid
where title like '%Antoine %'
group by id, title
limit 100;

-- Oppgave 5
select title, parttype, count(parttype)
from film
inner join filmparticipation fp on fp.filmid = film.filmid
inner join filmitem on filmitem.filmid = film.filmid
group by parttype, title, filmtype
having title like '%Lord of the Rings%' and filmtype like 'C'
order by title limit 100;

-- Oppgave 6
select prodyear , title from film
where prodyear = (select min(prodyear) from film);

-- Oppgave 7
(select title, prodyear from film
inner join filmgenre on filmgenre.filmid = film.filmid
where genre like 'Comedy')
intersect all
(select title, prodyear from film
inner join filmgenre on filmgenre.filmid = film.filmid
where genre like 'Film-Noir');

-- Oppgave 8
((select title, prodyear from film
inner join filmgenre on filmgenre.filmid = film.filmid
where genre like 'Comedy')
intersect all
(select title, prodyear from film
inner join filmgenre on filmgenre.filmid = film.filmid
where genre like 'Film-Noir'))
union all
(select title, prodyear from film
where prodyear = (select min(prodyear) from film));

-- Oppgave 9
(select title, prodyear from film
inner join filmparticipation fp on fp.filmid = film.filmid
inner join person on person.personid = fp.personid
where firstname = 'Stanley' and lastname = 'Kubrick' and parttype = 'director')
intersect all
(select title, prodyear from film
inner join filmparticipation fp on fp.filmid = film.filmid
inner join person on person.personid = fp.personid
where firstname = 'Stanley' and lastname = 'Kubrick' and parttype = 'cast');

-- Oppgave 10
(select maintitle, rank, votes from series
inner join filmrating fr on fr.filmid = series.seriesid
where votes > 1000)
intersect all
(select maintitle, rank, votes from series
inner join filmrating fr on fr.filmid = series.seriesid
where rank = (select max(rank) from filmrating where votes > 1000));

-- Oppgave 11
select country from filmcountry
group by country
having count(country) = 1;

-- Oppgave 12
with unike as(
    select filmcharacter from filmcharacter
    group by filmcharacter
    having count(partid) = 1
)
select firstname, lastname, count(*) as antall from unike
natural join filmcharacter
natural join filmparticipation
natural join person
group by firstname, lastname
having count(*) > 199
order by antall desc;

-- Oppgave 13
(select distinct firstname, lastname from film
inner join filmparticipation fp on fp.filmid = film.filmid
inner join person on person.personid = fp.personid
inner join filmrating fr on fr.filmid = film.filmid
where parttype = 'director' and votes > 60000 and rank > 7.9)
except all
(select distinct firstname, lastname from film
inner join filmparticipation fp on fp.filmid = film.filmid
inner join person on person.personid = fp.personid
inner join filmrating fr on fr.filmid = film.filmid
where parttype = 'director' and votes > 60000 and rank < 8)
;

# --- Sample dataset

# --- !Ups

insert into actor (id,name) values (1,'Silverter Stallone');
insert into actor (id,name) values (2,'Antonio Banderas');
insert into actor (id,name) values (3,'Penélope Cruz');
insert into actor (id,name) values (4,'Javier Bardem');

insert into film (id,title) values (1,'Rambo');
insert into film (id,title) values (2,'Jamón Jamón');
insert into film (id,title) values (3,'Huevos de Oro');

insert into film_actor (film_id,actor_id) values (1,1);
insert into film_actor (film_id,actor_id) values (2,3);
insert into film_actor (film_id,actor_id) values (2,4);
insert into film_actor (film_id,actor_id) values (3,2);
insert into film_actor (film_id,actor_id) values (3,3);
insert into film_actor (film_id,actor_id) values (3,4);

# --- !Downs

delete from film_actor;
delete from actor;
delete from film;

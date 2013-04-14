# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table actor (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_actor primary key (id))
;

create table film (
  id                        bigint not null,
  title                     varchar(255),
  constraint pk_film primary key (id))
;


create table film_actor (
  film_id                        bigint not null,
  actor_id                       bigint not null,
  constraint pk_film_actor primary key (film_id, actor_id))
;
create sequence actor_seq;

create sequence film_seq;




alter table film_actor add constraint fk_film_actor_film_01 foreign key (film_id) references film (id) on delete restrict on update restrict;

alter table film_actor add constraint fk_film_actor_actor_02 foreign key (actor_id) references actor (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists actor;

drop table if exists film;

drop table if exists film_actor;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists actor_seq;

drop sequence if exists film_seq;


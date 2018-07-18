/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     18/7/9 17:09:10                              */
/*==============================================================*/


drop table if exists Course;

drop table if exists User;

/*==============================================================*/
/* Table: Course                                                */
/*==============================================================*/
create table Course
(
   Course_id            int not null,
   user_id              int,
   name                 char(256) comment 'username',
   traffic              int,
   primary key (Course_id)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   user_id              int not null,
   password             char(256),
   name                 char(256),
   role                 char(256),
   primary key (user_id)
);

alter table Course add constraint FK_teach foreign key (user_id)
      references User (user_id) on delete cascade on update cascade;


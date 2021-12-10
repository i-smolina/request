create table usr (user_id bigint auto_increment primary key, username varchar(20) not null, password varchar(20) not null);
create table role (role_id bigint auto_increment primary key, role varchar(20) not null);
create table user_roles (user_id bigint not null, role_id bigint not null, foreign key (user_id) references usr(user_id), foreign key (role_id) references role(role_id));
insert into usr (username, password) values ('user1', 'pass1');


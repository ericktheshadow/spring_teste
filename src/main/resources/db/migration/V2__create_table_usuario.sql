create table usuario(
       id bigint not null auto_increment,
       nome varchar(50) not null,
       email varchar(50) not null,
       senha varchar(500) not null,
       primary key(id)
);

insert into usuario values(1,'erick','erick@email.com',"$2a$10$j3S6jvBdV2xfaPnhvrM3S.MEx58GZrFCB69hdITlx.DEI1rIB7eT6");
create table usuario(
       id bigint not null auto_increment,
       nome varchar(50) not null,
       email varchar(50) not null,
       senha varchar(50) not null,
       primary key(id)
);

insert into usuario values(1,'erick','erick@email.com',"sonic450");
create database agenda;

use agenda;

create table contatos(
    id int not null auto_increment primary key,
    nome varchar(50) not null,
    idade int not null,
    dataCadastro date
);
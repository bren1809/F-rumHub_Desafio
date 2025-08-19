create table topicos (
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    mensagem text not null,
    data_criacao datetime not null default current_timestamp,
    estado varchar(50) not null,
    autor varchar(255) not null,
    curso varchar(255) not null,

    primary key(id)
);
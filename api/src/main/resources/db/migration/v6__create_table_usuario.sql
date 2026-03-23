create table usuario(
    id bigserial primary key,
    nome varchar(200),
    email varchar(100) unique
)
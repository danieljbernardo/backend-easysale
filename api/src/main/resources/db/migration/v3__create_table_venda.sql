create table venda(
    id bigserial primary key,
    cliente_id bigint,
    data_venda date,
    constraint fk_venda_cliente foreign key (cliente_id) references cliente (id)
)
create table item_venda(
    id bigserial primary key,
    venda_id bigint,
    produto_id bigint,
    quantidade bigint,
    preco_unitario decimal,

    constraint fk_item_venda foreign key (venda_id) references venda(id),
    constraint fk_item_produto foreign key (produto_id) references produto(id)
)
create table nota_fiscal(
    id bigserial primary key,
    venda_id bigint,
    valor_total decimal,
    numero_nota bigint,
    serie bigint,
    data_emissao date,

    constraint fk_nota_venda foreign key (venda_id) references venda(id)
)
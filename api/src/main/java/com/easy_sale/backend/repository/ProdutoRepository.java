package com.easy_sale.backend.repository;

import com.easy_sale.backend.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findByCodigo(Long codigo);
}

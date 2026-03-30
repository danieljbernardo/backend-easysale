package com.easy_sale.backend.repository;

import com.easy_sale.backend.domain.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    Venda findByIdVenda(Long id);
}

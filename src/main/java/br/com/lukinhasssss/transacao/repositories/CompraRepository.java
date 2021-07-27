package br.com.lukinhasssss.transacao.repositories;

import br.com.lukinhasssss.transacao.entities.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, String> {

    Page<Compra> findAllByIdCartao(String idCartao, Pageable pageable);

}

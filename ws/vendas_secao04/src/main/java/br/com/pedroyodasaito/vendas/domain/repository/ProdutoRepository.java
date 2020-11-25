package br.com.pedroyodasaito.vendas.domain.repository;

import br.com.pedroyodasaito.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}

package br.com.pedroyodasaito.domain.repository.jparepository;

import br.com.pedroyodasaito.domain.entity.jparepository.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}

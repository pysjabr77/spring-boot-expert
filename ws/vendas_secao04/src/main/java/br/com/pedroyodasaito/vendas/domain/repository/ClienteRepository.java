package br.com.pedroyodasaito.vendas.domain.repository;

import br.com.pedroyodasaito.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

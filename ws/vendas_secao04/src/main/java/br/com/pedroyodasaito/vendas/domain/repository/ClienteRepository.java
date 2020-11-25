package br.com.pedroyodasaito.vendas.domain.repository;

import br.com.pedroyodasaito.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findClienteByNomeContains(String nome);

}

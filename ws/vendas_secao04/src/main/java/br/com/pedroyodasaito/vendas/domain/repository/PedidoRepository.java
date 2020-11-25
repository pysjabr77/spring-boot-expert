package br.com.pedroyodasaito.vendas.domain.repository;

import br.com.pedroyodasaito.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}

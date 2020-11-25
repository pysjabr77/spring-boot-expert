package br.com.pedroyodasaito.vendas.domain.repository;

import br.com.pedroyodasaito.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}

package br.com.pedroyodasaito.vendas.service.pedido;

import br.com.pedroyodasaito.vendas.domain.entity.Pedido;
import br.com.pedroyodasaito.vendas.domain.enums.StatusPedido;
import br.com.pedroyodasaito.vendas.service.pedido.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarStatus(Integer id, StatusPedido statusPedido);

}

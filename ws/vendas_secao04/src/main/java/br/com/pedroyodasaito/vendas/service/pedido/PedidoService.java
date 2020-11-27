package br.com.pedroyodasaito.vendas.service.pedido;

import br.com.pedroyodasaito.vendas.domain.entity.Pedido;
import br.com.pedroyodasaito.vendas.service.pedido.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

}

package br.com.pedroyodasaito.vendas.api.controller;

import br.com.pedroyodasaito.vendas.service.pedido.PedidoService;
import br.com.pedroyodasaito.vendas.service.pedido.dto.PedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.salvar(pedidoDTO).getId();
    }

}

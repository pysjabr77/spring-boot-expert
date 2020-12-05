package br.com.pedroyodasaito.vendas.api.controller;

import br.com.pedroyodasaito.vendas.domain.entity.ItemPedido;
import br.com.pedroyodasaito.vendas.domain.entity.Pedido;
import br.com.pedroyodasaito.vendas.domain.enums.StatusPedido;
import br.com.pedroyodasaito.vendas.service.pedido.PedidoService;
import br.com.pedroyodasaito.vendas.service.pedido.dto.AtualizacaoStatusPedidoDTO;
import br.com.pedroyodasaito.vendas.service.pedido.dto.InformacoesItemPedidoDTO;
import br.com.pedroyodasaito.vendas.service.pedido.dto.InformacoesPedidoDTO;
import br.com.pedroyodasaito.vendas.service.pedido.dto.PedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = {"/{id}"})
    public InformacoesPedidoDTO obter(@PathVariable Integer id) {
        return pedidoService
                .obterPedidoCompleto(id)
                .map(pedido -> converterPedidoParaInformacoesPedidoDTO(pedido))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO atualizacaoStatusPedidoDTO) {
        pedidoService.atualizarStatus(id, StatusPedido.valueOf(atualizacaoStatusPedidoDTO.getNovoStatus()));
    }

    private InformacoesPedidoDTO converterPedidoParaInformacoesPedidoDTO(Pedido pedido) {
        return InformacoesPedidoDTO.builder()
                .codigoPedido(pedido.getId())
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .status(pedido.getStatus().name())
                .total(pedido.getTotal())
                .itens(converterItensPedidoParaInformacoesItemPedidoDTO(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converterItensPedidoParaInformacoesItemPedidoDTO(List<ItemPedido> listaItemPedido){
        if (CollectionUtils.isEmpty(listaItemPedido)) {
            return Collections.emptyList();
        }

        return listaItemPedido.stream()
                .map(item -> InformacoesItemPedidoDTO.builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());

    }


}

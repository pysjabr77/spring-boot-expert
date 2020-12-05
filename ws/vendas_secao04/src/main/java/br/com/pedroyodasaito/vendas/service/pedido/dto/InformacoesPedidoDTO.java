package br.com.pedroyodasaito.vendas.service.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {
    private Integer codigoPedido;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private String status;
    private List<InformacoesItemPedidoDTO> itens;
}

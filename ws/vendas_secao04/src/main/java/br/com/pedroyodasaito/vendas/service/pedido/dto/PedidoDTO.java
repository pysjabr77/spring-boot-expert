package br.com.pedroyodasaito.vendas.service.pedido.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class PedidoDTO {

    private Integer clienteID;
    private BigDecimal total;
    private Set<ItemPedidoDTO> itensPedido;

    public PedidoDTO() {
    }

    public PedidoDTO(Integer clienteID, BigDecimal total, Set<ItemPedidoDTO> itensPedido) {
        this.clienteID = clienteID;
        this.total = total;
        this.itensPedido = itensPedido;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "clienteID=" + clienteID +
                ", total=" + total +
                ", itensPedido=" + itensPedido +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDTO pedidoDTO = (PedidoDTO) o;
        return clienteID.equals(pedidoDTO.clienteID) &&
                total.equals(pedidoDTO.total) &&
                itensPedido.equals(pedidoDTO.itensPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteID, total, itensPedido);
    }

    public Integer getClienteID() {
        return clienteID;
    }

    public void setClienteID(Integer clienteID) {
        this.clienteID = clienteID;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<ItemPedidoDTO> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(Set<ItemPedidoDTO> itensPedido) {
        this.itensPedido = itensPedido;
    }
}

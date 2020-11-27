package br.com.pedroyodasaito.vendas.service.pedido.dto;

import java.util.Objects;

public class ItemPedidoDTO {

    private Integer produtoID;
    private Integer quantidade;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(Integer produtoID, Integer quantidade) {
        this.produtoID = produtoID;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedidoDTO{" +
                "produtoID=" + produtoID +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoDTO that = (ItemPedidoDTO) o;
        return produtoID.equals(that.produtoID) &&
                quantidade.equals(that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoID, quantidade);
    }

    public Integer getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(Integer produtoID) {
        this.produtoID = produtoID;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

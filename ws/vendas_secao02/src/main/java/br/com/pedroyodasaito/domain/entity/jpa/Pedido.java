package br.com.pedroyodasaito.domain.entity.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Pedido {

    private Integer id;
    private ClienteJPA clienteJPA;
    private LocalDate dataPedido;
    private BigDecimal total;

    public Pedido() {
    }

    public Pedido(Integer id, ClienteJPA clienteJPA, LocalDate dataPedido, BigDecimal total) {
        this.id = id;
        this.clienteJPA = clienteJPA;
        this.dataPedido = dataPedido;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", clienteJPA=" + clienteJPA +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteJPA getClienteJPA() {
        return clienteJPA;
    }

    public void setClienteJPA(ClienteJPA clienteJPA) {
        this.clienteJPA = clienteJPA;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

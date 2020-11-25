package br.com.pedroyodasaito.domain.entity.jparepository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteJPAInterfaceRepository clienteJPAInterfaceRepository;
    @Column(name = "data_pedido")
    private LocalDate dataPedido;
    @Column(name = "total", scale = 2, precision = 20)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Pedido(Integer id, ClienteJPAInterfaceRepository clienteJPAInterfaceRepository, LocalDate dataPedido, BigDecimal total) {
        this.id = id;
        this.clienteJPAInterfaceRepository = clienteJPAInterfaceRepository;
        this.dataPedido = dataPedido;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", clienteJPAInterfaceRepository=" + clienteJPAInterfaceRepository +
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

    public ClienteJPAInterfaceRepository getClienteJPAInterfaceRepository() {
        return clienteJPAInterfaceRepository;
    }

    public void setClienteJPAInterfaceRepository(ClienteJPAInterfaceRepository clienteJPAInterfaceRepository) {
        this.clienteJPAInterfaceRepository = clienteJPAInterfaceRepository;
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

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}

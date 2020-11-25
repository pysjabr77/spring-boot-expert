package br.com.pedroyodasaito.domain.entity.jparepository;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class ClienteJPAInterfaceRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome", length = 100)
    private String nome;

    @OneToMany (mappedBy = "clienteJPAInterfaceRepository")
    private Set<Pedido> pedidos;

    public ClienteJPAInterfaceRepository() {
    }

    public ClienteJPAInterfaceRepository(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ClienteJPAInterfaceRepository(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ClienteJPAInterfaceRepository{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteJPAInterfaceRepository clienteJPAInterfaceRepository = (ClienteJPAInterfaceRepository) o;
        return Objects.equals(id, clienteJPAInterfaceRepository.id);
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

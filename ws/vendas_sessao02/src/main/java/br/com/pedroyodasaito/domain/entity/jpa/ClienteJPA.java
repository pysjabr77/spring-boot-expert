package br.com.pedroyodasaito.domain.entity.jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class ClienteJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome", length = 100)
    private String nome;

    public ClienteJPA() {
    }

    public ClienteJPA(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ClienteJPA(String nome) {
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
        ClienteJPA clienteJPA = (ClienteJPA) o;
        return Objects.equals(id, clienteJPA.id);
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
}

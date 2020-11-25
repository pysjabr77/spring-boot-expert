package br.com.pedroyodasaito.domain.repository.jpa;

import br.com.pedroyodasaito.domain.entity.jpa.ClienteJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClienteJPARepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public ClienteJPA salvar(ClienteJPA clienteJPA) {
        entityManager.persist(clienteJPA);
        return clienteJPA;
    }

    @Transactional
    public ClienteJPA obter(Integer id) {
        return entityManager.find(ClienteJPA.class, id);
    }

    @Transactional (readOnly = true)
    public List<ClienteJPA> listar() {
        return entityManager.createQuery("from ClienteJPA", ClienteJPA.class).getResultList();
    }

    @Transactional (readOnly = true)
    public List<ClienteJPA> listarPorNome(String nome) {
        TypedQuery<ClienteJPA> query = entityManager.createQuery("select c from ClienteJPAInterfaceRepository  c where c.nome = :nome", ClienteJPA.class);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }

    @Transactional
    public ClienteJPA atualizar(ClienteJPA clienteJPA) {
        return entityManager.merge(clienteJPA);
    }

    @Transactional
    public void deletar(Integer id) {
        entityManager.remove(obter(id));
    }

    @Transactional
    public void deletar(ClienteJPA clienteJPA) {
        if(!entityManager.contains(clienteJPA)) {
            clienteJPA = entityManager.merge(clienteJPA);
        }
        entityManager.remove(clienteJPA);
    }

}

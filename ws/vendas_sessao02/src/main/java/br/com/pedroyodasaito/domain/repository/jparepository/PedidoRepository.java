package br.com.pedroyodasaito.domain.repository.jparepository;

import br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository;
import br.com.pedroyodasaito.domain.entity.jparepository.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByClienteJPAInterfaceRepository(ClienteJPAInterfaceRepository cliente);

}

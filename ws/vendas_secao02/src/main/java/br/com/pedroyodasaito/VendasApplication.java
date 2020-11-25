package br.com.pedroyodasaito;

import br.com.pedroyodasaito.domain.entity.jparepository.ClienteJPAInterfaceRepository;
import br.com.pedroyodasaito.domain.entity.jparepository.Pedido;
import br.com.pedroyodasaito.domain.repository.jparepository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class VendasApplication {
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(@Autowired br.com.pedroyodasaito.domain.repository.jparepository.ClienteJPAInterfaceRepository clienteJPAInterfaceRepository,
                                  @Autowired PedidoRepository pedidoRepository) {
        return args -> {
            System.out.println("-----------------------------------------------------");
            System.out.println("Gravando dados no banco");
            clienteJPAInterfaceRepository.save(new ClienteJPAInterfaceRepository("Pedro Yoda Saito"));
            clienteJPAInterfaceRepository.save(new ClienteJPAInterfaceRepository("João da Silva"));

            System.out.println("-----------------------------------------------------");
            System.out.println("Listando dados Inseridos");
            List<ClienteJPAInterfaceRepository> clientes = clienteJPAInterfaceRepository.findAll();
            clientes.forEach(System.out::println);

            System.out.println("-----------------------------------------------------");
            System.out.println("Atualizando dados");
            clientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clienteJPAInterfaceRepository.save(c);
            });
            List<ClienteJPAInterfaceRepository> clientes2 = clienteJPAInterfaceRepository.findAll();
            clientes2.forEach(System.out::println);

            System.out.println("-----------------------------------------------------");
            System.out.println("Obtendo por id");
            Optional<ClienteJPAInterfaceRepository> clientes3 = clienteJPAInterfaceRepository.findById(1);
            if (clientes3.isPresent()) {
                System.out.println(clientes3.toString());
            } else {
                System.out.println("Cliente não encontrado");
            }

            System.out.println("-----------------------------------------------------");
            System.out.println("existe por nome - Query Method");
            boolean ret = clienteJPAInterfaceRepository.existsByNome("Pedro Yoda Saito atualizado");
            if (!ret) {
                System.out.println("Não encontrado");
            } else {
                System.out.println("Encontrado");
            }

            System.out.println("-----------------------------------------------------");
            System.out.println("Obtendo por nome - Query Method");
            ClienteJPAInterfaceRepository c2 = clienteJPAInterfaceRepository.findOneByNome("Pedro Yoda Saito atualizado");
            if (c2 == null) {
                System.out.println("Não encontrado");
            } else {
                System.out.println(c2.toString());
            }

            System.out.println("-----------------------------------------------------");
            System.out.println("Obtendo por nome or id - Query Method");
            List<ClienteJPAInterfaceRepository> c3 = clienteJPAInterfaceRepository.findByNomeLikeOrId("Pedro Yoda Saito atualizado", 2);
            c3.forEach(System.out::println);

            System.out.println("-----------------------------------------------------");
            System.out.println("Obtendo por nome - Query Method");
            List<ClienteJPAInterfaceRepository> clientes4 = clienteJPAInterfaceRepository.findByNomeLike("%Yoda%");
            clientes4.forEach(System.out::println);

            System.out.println("-----------------------------------------------------");
            System.out.println("Obtendo por nome - @Query");
            List<ClienteJPAInterfaceRepository> clientes5 = clienteJPAInterfaceRepository.buscarPorNome("%Yoda%");
            clientes5.forEach(System.out::println);

            System.out.println("-----------------------------------------------------");
            System.out.println("Obtendo por nome - @Query com sql nativo 1");
            List<ClienteJPAInterfaceRepository> clientes6 = clienteJPAInterfaceRepository.buscarPorNomeNativo1("Yoda");
            clientes6.forEach(System.out::println);

            System.out.println("-----------------------------------------------------");
            System.out.println("Obtendo por nome - @Query com sql nativo 2");
            List<ClienteJPAInterfaceRepository> clientes7 = clienteJPAInterfaceRepository.buscarPorNomeNativo2("%Yoda%");
            clientes7.forEach(System.out::println);

            System.out.println("-----------------------------------------------------");
            System.out.println("Deletar pelo id");
            clienteJPAInterfaceRepository.deleteById(1);
            List<ClienteJPAInterfaceRepository> delCliente1 = clienteJPAInterfaceRepository.findAll();
            delCliente1.forEach(System.out::println);

            System.out.println("-----------------------------------------------------");
            System.out.println("Deletar pelo objeto");
            clienteJPAInterfaceRepository.delete(new ClienteJPAInterfaceRepository(2,"João da Silva"));
            List<ClienteJPAInterfaceRepository> delCliente2 = clienteJPAInterfaceRepository.findAll();
            delCliente2.forEach(System.out::println);


            System.out.println("-----------------------------------------------------");
            System.out.println("Criando um pedido e salvando");
            ClienteJPAInterfaceRepository cli = new ClienteJPAInterfaceRepository("Cliente Pedido");
            clienteJPAInterfaceRepository.save(cli);

            Pedido p = new Pedido();
            p.setClienteJPAInterfaceRepository(cli);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidoRepository.save(p);

            System.out.println("-----------------------------------------------------");
            System.out.println("Buscando o cliente com os seus pedidos");
            ClienteJPAInterfaceRepository cliComPedidos = clienteJPAInterfaceRepository.findClienteFetchPedidos(cli.getId());
            System.out.println(cliComPedidos.toString());
            System.out.println(cliComPedidos.getPedidos().toString());

            System.out.println("-----------------------------------------------------");
            System.out.println("Buscando pedidos do cliente");
            List<Pedido> pedidos = pedidoRepository.findByClienteJPAInterfaceRepository(cli);
            pedidos.forEach(System.out::println);

        };
    }

//    Usando JPA EntityManager
//    @Bean
//    public CommandLineRunner init(@Autowired ClienteJPAInterfaceRepository clienteJPARepository) {
//        return args -> {
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Gravando dados no banco");
//            clienteJPARepository.salvar(new ClienteJPAInterfaceRepository("Pedro Yoda Saito"));
//            clienteJPARepository.salvar(new ClienteJPAInterfaceRepository("João da Silva"));
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Listando dados Inseridos");
//            List<ClienteJPAInterfaceRepository> clientes = clienteJPARepository.listar();
//            clientes.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Atualizando dados");
//            clientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado");
//                clienteJPARepository.atualizar(c);
//            });
//            List<ClienteJPAInterfaceRepository> clientes2 = clienteJPARepository.listar();
//            clientes2.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Obtendo por id");
//            ClienteJPAInterfaceRepository clientes3 = clienteJPARepository.obter(1);
//            System.out.println(clientes3.toString());
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Obtendo por nome");
//            List<ClienteJPAInterfaceRepository> clientes4 = clienteJPARepository.listarPorNome("Yoda");
//            clientes4.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Deletar pelo id");
//            clienteJPARepository.deletar(1);
//            List<ClienteJPAInterfaceRepository> clientes5 = clienteJPARepository.listar();
//            clientes5.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Deletar pelo objeto");
//            clienteJPARepository.deletar(new ClienteJPAInterfaceRepository(2,"João da Silva"));
//            List<ClienteJPAInterfaceRepository> clientes6 = clienteJPARepository.listar();
//            clientes6.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//
//        };
//    }

//    USANDO JDBC TEMPLATE
//    @Bean
//    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
//        return args -> {
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Gravando dados no banco");
//            clienteRepository.salvar(new ClienteJPAInterfaceRepository("Pedro Yoda Saito"));
//            clienteRepository.salvar(new ClienteJPAInterfaceRepository("João da Silva"));
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Listando dados Inseridos");
//            List<ClienteJPAInterfaceRepository> clientes = clienteRepository.listar();
//            clientes.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Atualizando dados");
//            clientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado");
//                clienteRepository.atualizar(c);
//            });
//            List<ClienteJPAInterfaceRepository> clientes2 = clienteRepository.listar();
//            clientes2.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Obtendo por id");
//            List<ClienteJPAInterfaceRepository> clientes3 = clienteRepository.obter(1);
//            clientes3.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Obtendo por nome");
//            List<ClienteJPAInterfaceRepository> clientes4 = clienteRepository.listarPorNome("Yoda");
//            clientes4.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Deletar");
//            clienteRepository.deletar(1);
//            List<ClienteJPAInterfaceRepository> clientes5 = clienteRepository.listar();
//            clientes5.forEach(c -> {
//                System.out.println(c.toString());
//            });
//
//        };
//    }
}

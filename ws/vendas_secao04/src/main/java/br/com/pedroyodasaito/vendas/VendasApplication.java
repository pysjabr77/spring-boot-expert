package br.com.pedroyodasaito.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
//        return args -> {
//            System.out.println("=================================================");
//            System.out.println("Criando cliente teste");
//            Cliente c = new Cliente(null, "Pedro Yoda Saito");
//            clienteRepository.save(c);
//        };
//    }

}

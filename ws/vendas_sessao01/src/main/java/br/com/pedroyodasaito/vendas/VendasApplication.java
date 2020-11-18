package br.com.pedroyodasaito.vendas;

import br.com.pedroyodasaito.vendas.annotations.Gato;
import br.com.pedroyodasaito.vendas.interfaces.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @Value("${application.name.por.properties}")
    private String applicationNamePorProperties;

    @Gato
    private Animal animal;

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Obtido por bean: "+applicationName+", obtido por properties: "+applicationNamePorProperties;
    }

    @Bean(name = "executarAnimal")
    public CommandLineRunner executar() {
        return args -> {
            this.animal.fazerBarulho();
        };
    }
}

package br.com.pedroyodasaito.vendas;

import br.com.pedroyodasaito.vendas.annotations.ConfigurationDevelopment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@ConfigurationDevelopment
public class VendasConfiguration {

    @Bean(name = "applicationName")
    public String applicationName() {
        return "Aplicação de Vendas";
    }

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Ambiente de Desenvolvimento Rodando");
        };
    }

}

package br.com.pedroyodasaito.vendas.configurations;

import br.com.pedroyodasaito.vendas.interfaces.Animal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {

    @Bean("cachorro")
    public Animal cachorro() {
        return () -> System.out.println("Au au");
    }

    @Bean("gato")
    public Animal gato() {
        return () -> System.out.println("Miau miau");
    }
}

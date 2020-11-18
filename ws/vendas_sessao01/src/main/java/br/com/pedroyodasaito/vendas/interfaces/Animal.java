package br.com.pedroyodasaito.vendas.interfaces;

public interface Animal {
    void fazerBarulho();

    default void comer() {};
}

package br.com.pedroyodasaito.vendas.service;

import br.com.pedroyodasaito.vendas.model.Cliente;
import br.com.pedroyodasaito.vendas.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository clientesRepository;

    @Autowired
    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository  = clientesRepository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        clientesRepository.salvar(cliente);
    }

    public void validarCliente(Cliente cliente) {
        //aplica validações
    }

}

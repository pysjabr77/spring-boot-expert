package br.com.pedroyodasaito.vendas.api.controller;

import br.com.pedroyodasaito.vendas.domain.entity.Cliente;
import br.com.pedroyodasaito.vendas.domain.repository.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController (ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @ResponseBody
    @GetMapping (value = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) {
//            ResponseEntity<Cliente> clienteResponseEntity = new ResponseEntity<>(cliente.get(), HttpStatus.OK);
//            return clienteResponseEntity;
            //ou
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

//    @ResponseBody
//    @GetMapping
//    public ResponseEntity<List<Cliente>> getClientes(@RequestBody Cliente cliente) {
//        List<Cliente> lista = clienteRepository.findClienteByNomeContains(cliente.getNome());
//        if(lista.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(lista);
//    }

    @ResponseBody
    @GetMapping
    public ResponseEntity find(Cliente cliente) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(cliente, matcher);
        List<Cliente> lista = clienteRepository.findAll(example);
        return ResponseEntity.ok(lista);
    }


    @ResponseBody
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @ResponseBody
    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {
//        Optional<Cliente> c = clienteRepository.findById(id);
//        if(c.isPresent()) {
//            cliente.setId(c.get().getId());
//            clienteRepository.save(cliente);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
        //ou
        return clienteRepository
                .findById(id)
                .map(c -> {
                  cliente.setId(c.getId());
                  clienteRepository.save(cliente);
                  return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

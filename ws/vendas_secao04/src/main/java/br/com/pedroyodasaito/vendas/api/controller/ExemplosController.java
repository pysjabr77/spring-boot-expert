package br.com.pedroyodasaito.vendas.api.controller;

import br.com.pedroyodasaito.vendas.domain.entity.Cliente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exemplos")
public class ExemplosController {

    @ResponseBody
    @RequestMapping(value = "/helloTeste/{nome}", method = RequestMethod.GET)
    public String exemploSimples(@PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }

    @ResponseBody
    @RequestMapping(value = {"/exemplo-array-path/{nome}", "/exemplo-array-path"}, method = RequestMethod.GET)
    public String exemploArrayPath(@PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }

    @ResponseBody
    @RequestMapping(value = {"/exemplo-array-path/{nome}", "/exemplo-array-path"},
    consumes = {"application/json", "application/xml"},
            method = RequestMethod.POST)
    public String exemploConsumes(@PathVariable("nome") String nomeCliente, @RequestBody Cliente cliente) {
        return String.format("Hello %s", nomeCliente);
    }

    @ResponseBody
    @RequestMapping(value = {"/exemplo-array-path/{nome}", "/exemplo-array-path"},
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"},
            method = RequestMethod.POST)
    public String exemploProduces(@PathVariable("nome") String nomeCliente, @RequestBody Cliente cliente) {
        return String.format("Hello %s", nomeCliente);
    }

}

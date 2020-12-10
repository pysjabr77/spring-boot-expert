package br.com.pedroyodasaito.vendas.api.controller;

import br.com.pedroyodasaito.vendas.api.ApiErrors;
import br.com.pedroyodasaito.vendas.exception.PedidoNaoEncontradoException;
import br.com.pedroyodasaito.vendas.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException regraNegocioException) {
        return new ApiErrors(regraNegocioException.getMessage());
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException pedidoNaoEncontradoException) {
        return new ApiErrors(pedidoNaoEncontradoException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ApiErrors(methodArgumentNotValidException
                .getBindingResult()
                .getAllErrors().stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList()));
    }

}

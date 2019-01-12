package br.com.page.gerenciamentoeletronicoimagens.handlerexceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.page.gerenciamentoeletronicoimagens.exception.ArquivoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ArquivoNaoEncontradoException.class)
    public ResponseEntity<?> handleLivroNaoEncontradoException(ArquivoNaoEncontradoException e,
	    HttpServletRequest request) {

    	/*
	DetalhesErro erro = DetalhesErro.build().status(HttpStatus.NOT_FOUND.value())
		.titulo(env.getMessage("msg.arquivo.nao.encontrado", null, locale));
		
		*/

	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

 

}
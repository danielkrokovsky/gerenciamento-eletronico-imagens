package br.com.page.gerenciamentoeletronicoimagens.handlerexceptions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.page.gerenciamentoeletronicoimagens.exception.ArquivoNaoEncontradoException;
import br.com.page.gerenciamentoeletronicoimagens.exception.CnpjNaoEncontradoException;
import br.com.page.gerenciamentoeletronicoimagens.util.DetalhesErro;


@ControllerAdvice
public class ResourceExceptionHandler {
	
    @Autowired
    private MessageSource env;
    private Locale locale = Locale.getDefault();

	@ExceptionHandler(ArquivoNaoEncontradoException.class)
	public ResponseEntity<?> handleArquivoNaoEncontradoException(ArquivoNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = DetalhesErro.build().status(HttpStatus.NOT_FOUND.value())
				.titulo(env.getMessage("msg.arquivo.nao.encontrado", null, locale));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}
	
	@ExceptionHandler(CnpjNaoEncontradoException.class)
	public ResponseEntity<?> handleCnpjNaoEncontradoException(CnpjNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = DetalhesErro.build().status(HttpStatus.NOT_FOUND.value())
				.titulo(env.getMessage("msg.cnpj.nao.encontrado", null, locale));

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}

}
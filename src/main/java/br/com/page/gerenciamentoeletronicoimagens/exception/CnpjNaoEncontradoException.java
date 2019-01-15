package br.com.page.gerenciamentoeletronicoimagens.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CnpjNaoEncontradoException extends RuntimeException {

	
	    private static final long serialVersionUID = 1L;

	    public CnpjNaoEncontradoException() {
		super();
	    }

	    public CnpjNaoEncontradoException(String menssagem) {
		super(menssagem);
	    }

	    public CnpjNaoEncontradoException(String menssagem, Throwable causa) {
		super(menssagem, causa);
	    }

	}

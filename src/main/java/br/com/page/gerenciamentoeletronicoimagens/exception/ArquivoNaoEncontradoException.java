package br.com.page.gerenciamentoeletronicoimagens.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArquivoNaoEncontradoException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ArquivoNaoEncontradoException() {
	super();
    }

    public ArquivoNaoEncontradoException(String menssagem) {
	super(menssagem);
    }

    public ArquivoNaoEncontradoException(String menssagem, Throwable causa) {
	super(menssagem, causa);
    }

}

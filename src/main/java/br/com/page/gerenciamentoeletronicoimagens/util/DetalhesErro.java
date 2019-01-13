package br.com.page.gerenciamentoeletronicoimagens.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class DetalhesErro {

    private String titulo;

    private int status;

    public DetalhesErro titulo(String titulo) {

	this.titulo = titulo;

	return this;
    }

    public DetalhesErro status(int i) {

	this.status = i;

	return this;
    }

    public static DetalhesErro build() {

	return new DetalhesErro();
    }

}
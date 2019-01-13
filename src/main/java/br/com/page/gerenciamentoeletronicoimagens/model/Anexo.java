package br.com.page.gerenciamentoeletronicoimagens.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "ANEXO")
public class Anexo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NOME_ARQUIVO")
	private String nomeArquivo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INCLUSAO")
	private Date dataInclusao;

	@Column(name = "DIRETORIO")
	private String diretorio;
	

	public Anexo nomeArquivo(String nomeArquivo) {

		this.nomeArquivo = nomeArquivo;

		return this;
	}

	public Anexo dataInclusao(Date dataInclusao) {

		this.dataInclusao = dataInclusao;

		return this;
	}
	
	public Anexo diretorio(String diretorio) {

		this.diretorio = diretorio;

		return this;
	}

	public static Anexo builder() {

		return new Anexo();
	}

}

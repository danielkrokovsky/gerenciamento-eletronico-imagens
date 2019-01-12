package br.com.page.gerenciamentoeletronicoimagens.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.page.gerenciamentoeletronicoimagens.model.Anexo;

@Repository
public interface AnexoRepository extends CrudRepository<Anexo, Long> {
	
	List<Anexo> findByDiretorio(String diretorio);

}

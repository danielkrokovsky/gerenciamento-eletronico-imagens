package br.com.page.gerenciamentoeletronicoimagens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.page.gerenciamentoeletronicoimagens.model.Anexo;

@Repository
public interface AnexoRepository extends CrudRepository<Anexo, Long> {
	
	List<Anexo> findByDiretorio(String diretorio);
		
	@Query("SELECT f FROM Anexo f WHERE f.diretorio like :diretorio and f.id = :id")
	Anexo findByAnexo(@Param("id")Long id, @Param("diretorio") String diretorio);

}

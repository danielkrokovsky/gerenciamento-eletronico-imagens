package br.com.page.gerenciamentoeletronicoimagens;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.page.gerenciamentoeletronicoimagens.model.Anexo;
import br.com.page.gerenciamentoeletronicoimagens.repository.AnexoRepository;
import br.com.page.gerenciamentoeletronicoimagens.util.StorageFileSystemService;
import br.com.page.gerenciamentoeletronicoimagens.util.Utils;

@Service
public class UploadService {	
	
	   @Autowired
	   private StorageFileSystemService storageFileSystemService;
	   
	   @Autowired
	   private AnexoRepository anexoRepository;	   
	   
	   public List<Anexo> upload(List<MultipartFile> arquivos, String cnpj) {
		   		   
		   arquivos.stream().forEach(a -> {
			   
			   
			   String extensao = a.getOriginalFilename().substring(a.getOriginalFilename().lastIndexOf('.'));
			   String nomeArquivo = UUID.randomUUID().toString() + extensao;
			   String diretorio = cnpj.concat(Utils.diretorio());
		   
			   storageFileSystemService.createDiretorio(diretorio);			   
			   storageFileSystemService.store(a, diretorio.concat(nomeArquivo));			   

			   Anexo anexo = Anexo.builder()
					   .diretorio(cnpj)
					   .nomeArquivo(nomeArquivo)
					   .nomeOriginal(a.getOriginalFilename())
					   .dataInclusao(Calendar.getInstance().getTime());
			   
			   salvarDados(anexo);
	   
		   });
		   
		   return anexoRepository.findByDiretorio(cnpj);
	   }   
	   
	   public void download() {
		   
	   }
	   
	   private void salvarDados(Anexo anexo) {
		   
		   anexoRepository.save(anexo);
	   }

}

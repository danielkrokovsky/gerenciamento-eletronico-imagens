package br.com.page.gerenciamentoeletronicoimagens.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.page.gerenciamentoeletronicoimagens.exception.ArquivoNaoEncontradoException;
import br.com.page.gerenciamentoeletronicoimagens.exception.CnpjNaoEncontradoException;
import br.com.page.gerenciamentoeletronicoimagens.model.Anexo;
import br.com.page.gerenciamentoeletronicoimagens.repository.AnexoRepository;
import br.com.page.gerenciamentoeletronicoimagens.util.StorageFileSystemService;
import br.com.page.gerenciamentoeletronicoimagens.util.Utils;

@Service
public class GerenciadorService {

	@Autowired
	private StorageFileSystemService storageFileSystemService;

	@Autowired
	private AnexoRepository anexoRepository;
	
	public List<Anexo> upload(List<MultipartFile> arquivos, String cnpj) {

		arquivos.stream().forEach(a -> {

			String nomeArquivo = a.getOriginalFilename();
			String diretorio = cnpj.concat(Utils.diretorio());

			storageFileSystemService.createDiretorio(diretorio);
			storageFileSystemService.store(a, diretorio.concat(nomeArquivo));

			Anexo anexo = Anexo.builder().diretorio(cnpj)
					.nomeArquivo(a.getOriginalFilename().trim())
					.dataInclusao(Calendar.getInstance().getTime());
			
			salvarDados(anexo);
		});

		return anexoRepository.findByDiretorio(cnpj);
	}

	public void download(String cnpj, ZipOutputStream zipOutputStream) throws IOException{

		List<Anexo> uploads = anexoRepository.findByDiretorio(cnpj);

		if (uploads.size() > 0) {
			List<Resource> resources = getResources(uploads);
			List<File> files = getFiles(resources);
			storageFileSystemService.createZipFile(files, zipOutputStream);			
			
		} else {
			throw new ArquivoNaoEncontradoException();
		}
	}
	
	public Resource downloadFile(String cnpj, Long id) throws Exception{

		Anexo anexoDownload = anexoRepository.findByAnexo(id, cnpj);
		
		Resource res = null;
		
		if(anexoDownload != null) {
			res = storageFileSystemService.loadFile(anexoDownload.getPath());
		}else {
			throw new ArquivoNaoEncontradoException();
		}
		
		return res;
	}

	private List<File> getFiles(List<Resource> resources) {

		List<File> files = new ArrayList<>();
		resources.stream().forEach(res -> {
			try {
				files.add(res.getFile());
			} catch (IOException e) {

				throw new ArquivoNaoEncontradoException();
			}
		});
		return files;
	}

	private List<Resource> getResources(List<Anexo> uploads) {

		List<Resource> resources = new ArrayList<>();

		uploads.stream().forEach(file -> {

			String diretorio = file.getDiretorio().concat(Utils.diretorio(file.getDataInclusao()));

				try {
					resources.add(storageFileSystemService.loadFile(diretorio.concat(file.getNomeArquivo())));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		});
		return resources;

	}

	private void salvarDados(Anexo anexo) {

		anexoRepository.save(anexo);
	}
	
	
	public List<Anexo> listarDados(String cnpj) {
		
		List<Anexo> list = anexoRepository.findByDiretorio(cnpj);
		
		if(list == null || list.isEmpty()) {
			
			throw new CnpjNaoEncontradoException();
		}

		return list;
	}

}

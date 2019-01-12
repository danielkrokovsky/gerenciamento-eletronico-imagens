package br.com.page.gerenciamentoeletronicoimagens;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.page.gerenciamentoeletronicoimagens.model.Anexo;
import br.com.page.gerenciamentoeletronicoimagens.repository.AnexoRepository;
import br.com.page.gerenciamentoeletronicoimagens.util.StorageFileSystemService;
import br.com.page.gerenciamentoeletronicoimagens.util.Utils;
import br.com.page.gerenciamentoeletronicoimagens.util.Zip;

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

			Anexo anexo = Anexo.builder().diretorio(cnpj).nomeArquivo(nomeArquivo).nomeOriginal(a.getOriginalFilename())
					.dataInclusao(Calendar.getInstance().getTime());

			salvarDados(anexo);

		});

		return anexoRepository.findByDiretorio(cnpj);
	}

	public ZipOutputStream download(String cnpj) {

		List<Anexo> uploads = anexoRepository.findByDiretorio(cnpj);
		List<Resource> resources = new ArrayList<>();
		List<File> files = new ArrayList<>();
		ZipOutputStream zip = null;

		uploads.stream().forEach(file -> {

			String diretorio = file.getDiretorio().concat(Utils.diretorio(file.getDataInclusao()));

			try {
				resources.add(storageFileSystemService.loadFile(diretorio.concat(file.getNomeArquivo())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		resources.stream().forEach(res -> {

			try {
				files.add(res.getFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		try {

			zip = Zip.create("rysryks", files);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return zip;
	}

	private void salvarDados(Anexo anexo) {

		anexoRepository.save(anexo);
	}

}

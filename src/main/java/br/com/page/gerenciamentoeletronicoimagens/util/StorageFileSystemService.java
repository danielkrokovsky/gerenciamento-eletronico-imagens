package br.com.page.gerenciamentoeletronicoimagens.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.page.gerenciamentoeletronicoimagens.exception.ArquivoNaoEncontradoException;


@Service
public class StorageFileSystemService {

	private Path rootLocation;

	@Value("${root.locationUnix}")
	private String enderecoUnix;

	@Value("${root.locationWin}")
	private String enderecoWin;
	
	private static String OS = System.getProperty("os.name").toLowerCase();

	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
	
	public void createDiretorio(String pasta) {
		
		File diretorio = new File(rootLocation.toAbsolutePath().toString().concat("/").concat(pasta));
		
		if(!diretorio.exists()) {

			diretorio.mkdirs();
		}
		
	}

	@PostConstruct
	public void start() {

		rootLocation = Paths.get((isWindows() ? enderecoWin : enderecoUnix));

		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			
		}
	}

	public void store(MultipartFile file, String fileName) {
		try {
			Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
		} 
		catch (Exception e) {
		}
	}

	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);
	}

	public Resource loadFile(String filename) throws Exception {

		Path file = rootLocation.resolve(filename);

		Resource resource = new UrlResource(file.toUri());
		if (resource.exists() || resource.isReadable()) {
			return resource;
		} else {
			throw new ArquivoNaoEncontradoException();
		}
	}

}

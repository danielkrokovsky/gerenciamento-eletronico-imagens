package br.com.page.gerenciamentoeletronicoimagens.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.page.gerenciamentoeletronicoimagens.model.Anexo;
import br.com.page.gerenciamentoeletronicoimagens.service.GerenciadorService;

@RestController
public class AnexoController {

	@Autowired
	private GerenciadorService gerenciadorService;

	@RequestMapping(value = "/upload/{cnpj}", method = RequestMethod.POST)
	public ResponseEntity<List<Anexo>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] request,
			@PathVariable("cnpj") String cnpj) {

		List<Anexo> anexos = gerenciadorService.upload(Arrays.asList(request), cnpj);

		return ResponseEntity.ok().body(anexos);
	}

	@RequestMapping(value = "/download/{cnpj}", method = RequestMethod.GET, produces = "application/zip")
	public void downloadleFiles(HttpServletResponse response, @PathVariable("cnpj") String cnpj) throws IOException {

		response.setStatus(HttpServletResponse.SC_OK);
		response.setHeader("Content-Disposition", "attachment; filename=" + cnpj.concat(".zip"));
		response.setHeader("Content-Type", "application/zip");
		ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
		gerenciadorService.download(cnpj, zipOutputStream);
	}

	@RequestMapping(value = "/listarimagens/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<List<Anexo>> listarImagens(@PathVariable("cnpj") String cnpj) throws IOException {

		return ResponseEntity.ok().body(gerenciadorService.listarDados(cnpj));
	}

	@RequestMapping(value = "/download/{cnpj}/{id}", method = RequestMethod.GET,  produces = "application/image")
	public ResponseEntity<Resource> downloadImagens(HttpServletResponse response, @PathVariable("cnpj") String cnpj,
			@PathVariable("id") Long id) throws Exception {
		
		Resource res = gerenciadorService.downloadFile(cnpj, id);
	    
	    return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + res.getFilename() + "\"")
				.body(res);
		
	}
}

package br.com.page.gerenciamentoeletronicoimagens.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.page.gerenciamentoeletronicoimagens.UploadService;
import br.com.page.gerenciamentoeletronicoimagens.model.Anexo;

@RestController
public class AnexoController {
	
	@Autowired
	private UploadService uploadService;

	@RequestMapping(value = "/upload/{cnpj}", method = RequestMethod.POST)
	public ResponseEntity<List<Anexo>> uploadMultipleFiles(@RequestParam("files")  MultipartFile[] request, @PathVariable("cnpj") String cnpj) {

		List<Anexo> anexos = uploadService.upload(Arrays.asList(request), cnpj);		
		
		return ResponseEntity.ok().body(anexos);
	}

}

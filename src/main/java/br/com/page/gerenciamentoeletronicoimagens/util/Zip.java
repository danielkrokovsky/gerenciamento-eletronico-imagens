package br.com.page.gerenciamentoeletronicoimagens.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

	public static ZipOutputStream create(String name, String endereco) throws IOException {
        byte[] buffer = new byte[1024];
        
        ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream(name));

            	File file = new File(endereco);
                FileInputStream fileIn = new FileInputStream(file);
                zipFile.putNextEntry(new ZipEntry(name));
                zipFile.setMethod(ZipOutputStream.DEFLATED);
                zipFile.setLevel(5);
                
                int lenRead = 0;
                while((lenRead = fileIn.read(buffer)) > 0){
                    zipFile.write(buffer, 0, lenRead);

            }
        
        return zipFile;
    }

}

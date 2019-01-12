package br.com.page.gerenciamentoeletronicoimagens.util;

import java.util.Calendar;

public class Utils {
	
	public static String diretorio() {
		
		Calendar cal = Calendar.getInstance();
		
		int ano = cal.get(Calendar.YEAR);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		
		String diretorio = "/"+ano+""+mes+""+dia+"/";
		
		return diretorio;
	}

}

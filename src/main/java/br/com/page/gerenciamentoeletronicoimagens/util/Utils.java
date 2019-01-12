package br.com.page.gerenciamentoeletronicoimagens.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	
	public static String diretorio() {
		
		Calendar cal = Calendar.getInstance();
		
		int ano = cal.get(Calendar.YEAR);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		
		String diretorio = "/"+ano+""+mes+""+dia+"/";
		
		return diretorio;
	}
	
	
public static String diretorio(Date data) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		int ano = cal.get(Calendar.YEAR);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		
		String diretorio = "/"+ano+""+mes+""+dia+"/";
		
		return diretorio;
	}
	

}

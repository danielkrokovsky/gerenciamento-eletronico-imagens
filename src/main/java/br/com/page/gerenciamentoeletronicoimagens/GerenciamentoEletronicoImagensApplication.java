package br.com.page.gerenciamentoeletronicoimagens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class GerenciamentoEletronicoImagensApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoEletronicoImagensApplication.class, args);
	}
	
    @Bean
    public MessageSource messageSource() {
	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	messageSource.setBasenames("classpath:messages/messages");
	messageSource.setDefaultEncoding("UTF-8");
	return messageSource;
    }

}


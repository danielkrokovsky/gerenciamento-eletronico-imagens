# gerenciamento-eletronico-imagens


Nome do Candidato: Daniel Rodrigo da Silva Krokovsky


Docker

Caso queira subir a aplicação via docker, seguir os seguintes passos:

  - baixar o projeto di gitlab
  - instalar o maven
  - ir a pasta raiz do projeto, e executar o comando: mvn clean package
  - estando na pasta raiz do projeto, executar o comando: docker build .
  - estando na pasta raiz do projeto, executar o comando: docker-compose up
  
  Endpoits:
    
  UPLOAD - http://localhost:8080/gerenciador/upload/{cnpj}
  
  GET -    http://localhost:8080/gerenciador/download/{cnpj}
  
  GET -    http://localhost:8080/gerenciador/listarimagens/{cnpj}
  
  GET -    http://localhost:8080/gerenciador/{cnpj}/{id}
  
  
Tomcat

 Caso queira subir a aplicação via tomcat do Spring, seguir os seguintes passos:
 
  - baixar o projeto do gitlab
  - instalar o maven
  - Criar uma pasta com permissão de leitura e escrita
  - ir ao arquivo src/main/resources/application.properties, e alterar a propriedade root.locationUnix ou root.locationWin (de 	  acordo com o Sistema operacional), e adicionar o caminho da pasta criada.
  - Na pasta raiz do projeto, executar o comando: mvn spring-boot:run
  
  Endpoits:
  
  UPLOAD - http://localhost:9001/upload/{cnpj}
  
  GET -    http://localhost:9001/download/{cnpj}
  
  GET -    http://localhost:9001/listarimagens/{cnpj}
  
  GET -    http://localhost:9001//download/{cnpj}/{id}
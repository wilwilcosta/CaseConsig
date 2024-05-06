# CaseConsig
Case de consignado, feito com API Rest em Spring Boot, contendo 3 microsserviços, 1 gateway e 1 ServiceDiscovery

- Microsservices de Identificação, Simnulação e Custódia
- Service Discovery Eureka
- Gateway eureka

Como rodar o projeto:
Mudar as linhas do application.properties dos 3 microsserviços para as credenciais do root user do db Local MySQL da máquina:
![image](https://github.com/wilwilcosta/CaseConsig/assets/58920637/c43215d4-b596-4c7b-985a-4ffbe3ea6839)

spring.datasource.url=jdbc:mysql://localhost:{seu localhost_db}/caso_consig_wilson?createDatabaseIfNotExist=true
spring.datasource.username={root_user}
spring.datasource.password={root_password}

Rodar o Main(já configurados nas configs de run de cada um) dos projetos nesta ordem:
1 - ServiceDiscovery
2 - Gatewayms
3 - CaseIdentificacao
4 - MicrosservicoSimulacao
5 - MicrosservicoCustodia

Assim que qualquer um dos microsserviços for executado, as tabelas serão criadas com uso do flyway, com DB "caso_consig_wilson", que estão presentes na pasta de db.migrations
![image](https://github.com/wilwilcosta/CaseConsig/assets/58920637/2be0a8f3-2653-49d4-b11b-961c9c82c7c6)

Collection do postman com todas as chamadas da API: 
[Case Consignado.postman_collection.json](https://github.com/wilwilcosta/CaseConsig/files/15215368/Case.Consignado.postman_collection.json)

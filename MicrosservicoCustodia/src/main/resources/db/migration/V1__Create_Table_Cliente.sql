CREATE TABLE IF NOT EXISTS cliente(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(14) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    correntista BIT NOT NULL,
    segmento varchar(30) NULL,
    convenio varchar(30) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO CLIENTE (cpf, nome, correntista, segmento, convenio) VALUES ("111.111.111-11", "Michael Jackson", 1, "Varejo", "EP");
INSERT INTO CLIENTE (cpf, nome, correntista, segmento, convenio) VALUES ("222.222.222-22", "Lebron James", 1, "Uniclass", "OP");
INSERT INTO CLIENTE (cpf, nome, correntista, segmento, convenio) VALUES ("333.333.333-33", "Madonna", 1, "Person", "INSS");
INSERT INTO CLIENTE (cpf, nome, correntista, segmento, convenio) VALUES ("444.444.444-44", "Marta Vieira da Silva", 0, null, "EP");
INSERT INTO CLIENTE (cpf, nome, correntista, segmento, convenio) VALUES ("555.555.555-55", "Messi", 0, null, "INSS");
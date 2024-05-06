CREATE TABLE IF NOT EXISTS custodia(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    data_contrato Date NOT NULL,
    id_simulacao bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_simulacao) REFERENCES simulacao(id)
);
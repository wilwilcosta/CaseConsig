CREATE TABLE IF NOT EXISTS simulacao(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(14) NOT NULL,
    data_contratacao Date NOT NULL,
    convenio BIT NOT NULL,
    valor_solicitado DOUBLE NULL,
    taxa_aplicada DOUBLE NOT NULL,
    quantidade_parcelas INT NOT NULL,
    valor_total DOUBLE NOT NULL,
    valor_parcela DOUBLE NOT NULL,
    PRIMARY KEY (id)
)
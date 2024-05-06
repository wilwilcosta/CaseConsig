package br.com.CaseWilsonCustodia.CaseCustodia.MicrosservicoCustodia.model;

import jdk.jfr.Description;

public enum Convenio {
    @Description("Empresa privada")
    EP,

    @Description("Órgão público")
    OP,

    @Description("INSS")
    INSS
}

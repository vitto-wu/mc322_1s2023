public enum CalcSeguro {
    VALOR_BASE (10.0),
    FATOR_18_30 (1.25),
    FATOR_30_60 (1.0),
    FATOR_60_90 (1.5);

    public double valor;

    CalcSeguro(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}

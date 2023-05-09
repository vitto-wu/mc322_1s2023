import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    private int quantidadeFuncionarios;

    //Construtor
    public ClientePJ(String nome, String endereco, String cnpj,
            LocalDate dataFundacao, int quantidadeFuncionarios) {
        super(nome, endereco);
        if(Validacao.validarCNPJ(cnpj)){
            this.cnpj = cnpj;
        } else {
            this.cnpj = "CNPJ inválido";
        }
        this.dataFundacao = dataFundacao;
        this.quantidadeFuncionarios = quantidadeFuncionarios;
    }
    
    @Override
    /**
     * Calcula o score do cliente jurídico baseado em critérios como a quantidade de funcionarios e qunatidade de veículos
     * @return o score do cliente
     */
    public double calculaScore(){
        return CalcSeguro.VALOR_BASE.getValor() * (1 + (quantidadeFuncionarios) / 100) * getListaVeiculos().size();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return super.toString() +
                "\nCNPJ: " + cnpj +
                "\nData de Fundação: " + dataFundacao.format(formatoData) +
                "\nQuantidade de Funcionarios: " + quantidadeFuncionarios;
    }
    
    //Getters e Setters
    @Override
    public String getTipoCliente(){
        return "PJ";
    }

    @Override
    public String getIdentificacao(){
        return cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQuantidadeFuncionarios() {
        return quantidadeFuncionarios;
    }

    public void setQuantidadeFuncionarios(int quantidadeFuncionarios) {
        this.quantidadeFuncionarios = quantidadeFuncionarios;
    }

    
}

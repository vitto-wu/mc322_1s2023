import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private String classeEconomica;
    private LocalDate dataLicenca;
    private LocalDate dataNascimento;

    // Construtor
    public ClientePF(String nome, String endereco, String cpf,
            String genero, String educacao, String classeEconomica, LocalDate dataLicenca, LocalDate dataNascimento) {
        super(nome, endereco);
        if(Validacao.validarCPF(cpf)){
            this.cpf = cpf;
        } else {
            this.cpf = "CPF inválido";
        }
        this.genero = genero;
        this.educacao = educacao;
        this.classeEconomica = classeEconomica;
        this.dataLicenca = dataLicenca;
        this.dataNascimento = dataNascimento;
    }

    @Override
    /**
     * Calcula o score do cliente baseado em critérios como a idade e qunatidade de veículos
     * @return o score do cliente
     */
    public double calculaScore(){
        LocalDate dataHoje = LocalDate.now();
        int idade = Period.between(dataNascimento, dataHoje).getYears();
        double fator_idade = 0.0;

        if(idade >= 18 && idade < 30){
            fator_idade = CalcSeguro.FATOR_18_30.getValor();
        } else if(idade >= 30 && idade < 60){
            fator_idade = CalcSeguro.FATOR_30_60.getValor();
        } else if(idade >= 60 && idade < 90){
            fator_idade = CalcSeguro.FATOR_60_90.getValor();
        }

        return CalcSeguro.VALOR_BASE.getValor() * fator_idade * getListaVeiculos().size();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return super.toString() +
                "\nCPF: " + cpf + 
                "\nGenero: " + genero + 
                "\nEducação: " + educacao + 
                "\nClasse Economica: " + classeEconomica + 
                "\nData da Licença: " + dataLicenca.format(formatoData) + 
                "\nData de Nascimento: " + dataNascimento.format(formatoData);
    }
    
    //Getters e Setters
    @Override
    public String getTipoCliente(){
        return "PF";
    }

    @Override
    public String getIdentificacao(){
        return cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    
}

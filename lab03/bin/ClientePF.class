import java.util.Date;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private String classeEconomica;
    private Date dataLicenca;
    private Date dataNascimento;

    // Construtor
    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cpf, String genero,
            String educacao, String classeEconomica, Date dataLicenca, Date dataNascimento) {
        super(nome, endereco, listaVeiculos);
        this.cpf = cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.classeEconomica = classeEconomica;
        this.dataLicenca = dataLicenca;
        this.dataNascimento = dataNascimento;
    }

    
    @Override
    public String toString() {
        return super.toString() + String.format(
                "CPF: %s\nGenero: %s\nData da Licença: %s\nEducação: %s\nData de Nascimento: %s\nClasse Econômica: %s",
                this.cpf, this.genero, this.dataLicenca, this.educacao, this.dataNascimento, this.classeEconomica);
    }

    @Override
    public String getTipoCliente(){
        return "PF";
    }

    /**
     * Verifica se os digitos verificadores são coerentes
     * @param verificacao String cpf sem pontuação
     * @return true caso os digitos forem validos e false caso não
     */
    private boolean digitosVerificadores(String verificacao) {
        for (int i = 0; i < 2; i++) {
            int soma = 0;

            for (int j = 0; j < 9; j++) {
                soma += (verificacao.charAt(j + i) - '0') * (10 - j);
            }

            if ((11 - soma % 11) != (verificacao.charAt(9 + i) - '0')) {
                return false;
            }
        }
        return true;
    }

    /**
     * verifica se o cpf é valido
     * @param cpf String cpf
     * @return true caso o cpf é valido e false caso não
     */
    public boolean validarCPF(String cpf) {
        String verificacao = cpf.replaceAll("[^0-9]", "");

        if (verificacao.length() != 11) {
            return false;
        }

        for (int i = 1; i < 11; i++) {
            if (verificacao.charAt(0) == verificacao.charAt(i)) {
                if (i == 10) {
                    return false;
                }
                continue;
            }
            break;
        }

        return digitosVerificadores(verificacao);
    }

    // Getters e Setters
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

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

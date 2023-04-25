import java.util.Date;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    
    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, Date dataFundacao) {
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }
    
    /**
     * Verifica se o cnpj é valido
     * @param cnpj String cnpj
     * @return true caso seja válido e false caso não
     */
    public boolean validarCNPJ(String cnpj) {
        String verificacao = cnpj.replaceAll("[^0-9]", "");
        int[] multiplicadores = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int soma = 0, resto;


        for (int i = 0; i < 12; i++) {
            soma += (verificacao.charAt(i) - '0') * multiplicadores[i+1];
        }

        if(soma % 11 < 2){
            resto = 0;
        } else {
            resto = 11 - (soma % 11);
        }

        //primeiro digito
        if (resto !=  (verificacao.charAt(12) - '0')){
            return false;
        }

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (verificacao.charAt(i) - '0') * multiplicadores[i];
        }
        if(soma % 11 < 2){
            resto = 0;
        } else {
            resto = 11 - (soma % 11);
        }

        //segundo digito
        if (resto != (verificacao.charAt(13) - '0')){
            return false;
        }

        return true;
    }

    @Override
    public String toString(){
        return super.toString() + String.format("CNPJ: %s\nData de Fundação: %s", this.cnpj, this.dataFundacao);
    }

    @Override
    public String getTipoCliente(){
        return "PJ";
    }

    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
}

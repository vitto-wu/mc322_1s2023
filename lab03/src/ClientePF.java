import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente{
    private String cpf ;
    private Date dataNascimento ;

    //Construtor
    public ClientePF ( String nome , String endereco , Date dataLicenca , 
        String educacao , String genero , String classeEconomica ,
        List < Veiculo > listaVeiculos , String cpf , Date dataNascimento ) {
        super ( nome , endereco , dataLicenca , educacao , genero , classeEconomica , listaVeiculos );
        this . cpf = cpf;
        this . dataNascimento = dataNascimento;
    }

    //Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    
    @Override
    public String toString () {
        
    }

    //Verifica se os digitos verificadores são válidos
    private boolean digitosVerificadores(String verificacao){
        for (int i = 0; i < 2; i++){
            int soma = 0;

            for (int j = 0; j < 9; j++){
                soma += (verificacao.charAt(j+i) - '0') * (10 - j);
            }

            if ((11 - soma % 11) != (verificacao.charAt(9+i) - '0')){
                return false;
            }
        }
        return true;
    }

    //Validação de CPF
    public boolean validarCPF(String cpf){
        String verificacao = cpf.replaceAll("[^0-9]", "");

        if (verificacao.length() != 11){
            return false;
        }   

        for( int i = 1; i < 11; i++){
            if(verificacao.charAt(0) == verificacao.charAt(i)){
                if(i == 10){
                    return false;
                }
                continue;
            }
            break;
        }

        return digitosVerificadores(verificacao);
    }
}

public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    //Constructor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    //Validação de CPF
    public boolean digitosVerificadores(String verificacao){
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

    public boolean validarCPF(){
        String verificacao = this.cpf.replaceAll("[^0-9]", "");

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

    //Getters e setters 
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}

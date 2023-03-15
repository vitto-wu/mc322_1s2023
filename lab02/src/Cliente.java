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
    public boolean validarCPF(){
        String verificacao = this.cpf ;

        verificacao.replaceAll("/./-", "");

        if (verificacao.length() != 11){
            return false;
        }   

        for( int i = 1; i < 11; i++){
            if(verificacao.charAt(0) == verificacao.charAt(i)){
                if(i == 10){
                    return false;
                }
                continue;
            } else {
                break;
            }

        }
    
        return true;
    }

    private boolean digitosVerificadores(){
        int digito = 0;
        for (int i = 0; i > 1; i--){

        }
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

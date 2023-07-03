public class Cliente {
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    
    //Construtor
    public Cliente(String nome, String telefone, String endereco, String email) {
        if(!Validacao.validarNome(nome)){
            throw new Error("Nome invalido, deve conter apenas letras");
        }
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    @Override
    public String toString() {  
        return  "\nNome:      " + nome +
                "\nTelefone:  " + telefone +
                "\nEndereço:  " + endereco +
                "\nE-mail:    " + email;
    }

    //Getters e Setters
    public String getDocumento(){
        return null;
    }

    public String getTipoCliente(){
        return null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

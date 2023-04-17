import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List <Sinistro> listaSinistros;
    private List <Cliente> listaClientes;
    
    //Construtor
    public Seguradora(String nome, String telefone, String email, String endereco, List <Sinistro> listaSinistros,
            List <Cliente> listaClientes) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = listaSinistros;
        this.listaClientes = listaClientes;
    }

    //Cadastro do Cliente
    public boolean cadastrarCliente(Cliente cliente){
        
    }

    //Remover cliente da lista
    public boolean removerCliente(String cliente){

    }

    //Lista todos os clientes
    public void listarClientes(String cliente){
        for(int i = 0; i < listaClientes.size(); i++){
            
        }
    }

    //Gera um novo sinistro
    public boolean gerarSinistro(){

    }
    
    //Verifica um sinistro
    public boolean visulizarSinistro(String cliente){
        
    }

    //Lista todos os sinistros
    public void listarSinistros(){
        for(int i = 0; i < listaSinistros.size(); i++){
            
        }
    }

    //Getters e Setters    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}

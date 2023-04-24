import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList <Sinistro> listaSinistros;
    private ArrayList <Cliente> listaClientes;
    
    //Construtor
    public Seguradora(String nome, String telefone, String email, String endereco, ArrayList <Sinistro> listaSinistros,
            ArrayList <Cliente> listaClientes) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = listaSinistros;
        this.listaClientes = listaClientes;
    }

    //Cadastro do Cliente
    public boolean cadastrarCliente(Cliente cliente){
        if(cliente == null){
            return false;
        }

        for(Cliente clientesCadastrados : listaClientes){
            if(clientesCadastrados.equals(cliente)){
                return false;
            }
        }

        listaClientes.add(cliente);
        return true;
    }

    //Remover cliente da lista
    public boolean removerCliente(String cliente){
        if(cliente == null){
            return false;
        }

        for(int i = 0; i < listaClientes.size(); i++){
            if(listaClientes.get(i).equals(cliente)){
                listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    //Lista todos os clientes PJ ou PF
    public ArrayList <Cliente> listarClientes(String tipo_cliente){
        ArrayList <Cliente> tipo_clientes = new ArrayList <Cliente>();

        for(Cliente clientesCadastrados : listaClientes){
            if(clientesCadastrados.getTipoCliente() == tipo_cliente){
                tipo_clientes.add(clientesCadastrados);
            }
        }

        return tipo_clientes;
    }

    //Gera um novo sinistro
    public boolean gerarSinistro(int id, String data, String endereco, Seguradora seguradora, Veiculo veiculo,
    Cliente cliente){
        Sinistro novoSinistro = new Sinistro(id, data, endereco, seguradora, veiculo, cliente);

        for(Cliente clientesCadastrados : listaClientes){
            if(clientesCadastrados.equals(novoSinistro)){
                return false;
            }
        }

        listaSinistros.add(novoSinistro);
        return true;
    }
    
    //Verifica um sinistro
    public boolean visulizarSinistro(String cliente){
        if(cliente == null){
            return false;
        }

        for(Sinistro sinistrosCadastrados : listaSinistros){
            if(sinistrosCadastrados.getCliente().equals(cliente)){
                return true;
            }
        }
        return false;
    }

    //Lista todos os sinistros
    public ArrayList <Sinistro> listarSinistros(){
        return listaSinistros;
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

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}

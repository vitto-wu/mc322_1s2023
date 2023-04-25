import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList <Sinistro> listaSinistros;
    private ArrayList <Cliente> listaClientes;
    
    //Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList <Sinistro>();
        this.listaClientes = new ArrayList <Cliente>();
    }

    /**
     * @return informaçoes da seguradora
     */
    public String toString(){
        return String.format("Nome: %s\nTelefone: %s\nEndereco: %s\nEmail: %s", this.nome, this.telefone, this.endereco, this.email);
    }

    /**
     * Cadastra um cliente adicionando o na listaClientes caso seja válido ou não repetido
     * @param cliente Cliente cliente
     * @return true caso seja possivel e false caso não
     */
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

    /**
     * Remove um cliente da listaClientes caso seja válido
     * @param cliente Cliente cliente
     * @return true caso seja possivel e false caso não
     */
    public boolean removerCliente(String cliente){
        if(cliente == null){
            return false;
        }

        for(int i = 0; i < listaClientes.size(); i++){
            if(listaClientes.get(i).getNome().equals(cliente)){
                listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Lista todos os clientes (PF ou PJ) da listaClientes
     * @param tipo_cliente String tipo do cliente se é PF ou PJ
     * @return ArrayList dos clientes
     */
    public ArrayList <Cliente> listarClientes(String tipo_cliente){
        ArrayList <Cliente> tipo_clientes = new ArrayList <Cliente>();

        for(Cliente clientesCadastrados : listaClientes){
            if(clientesCadastrados.getTipoCliente() == tipo_cliente){
                tipo_clientes.add(clientesCadastrados);
            }
        }

        return tipo_clientes;
    }

    /**
     * gera um novo sinistro
     * @param data String data do ocorrido
     * @param endereco String endereço do ocorrido
     * @param seguradora Seguradora seguradora
     * @param veiculo Veiculo veiculo
     * @return true caso seja possivel gerar e false caso tenha sinistro repetido na lista
     */
    public boolean gerarSinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo,
    Cliente cliente){  
        Sinistro novoSinistro = new Sinistro(data, endereco, seguradora, veiculo, cliente);

        for(Sinistro sinistrosCadastrados : listaSinistros){
            if(sinistrosCadastrados.equals(novoSinistro)){
                return false;
            }
        }

        listaSinistros.add(novoSinistro);
        return true;
    }
    
    /**
     * visualiza os sinistros de um cliente
     * @param cliente String cliente
     * @return true caso seja tenha e false caso não
     */
    public boolean visulizarSinistro(String cliente){
        boolean tem = false;

        for(int i = 0; i < listaSinistros.size(); i++){
            if(listaSinistros.get(i).getCliente().getNome().equals(cliente)){
                System.out.println("\n" + listaSinistros.get(i));
                tem = true;
            }
        }

        return tem;
    }

    /**
     * @return lista dos sinistros
     */
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

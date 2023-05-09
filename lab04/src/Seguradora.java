import java.util.ArrayList;
import java.time.LocalDate;

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
     * Cadastra um cliente adicionando o na listaClientes caso este seja válido ou não repetido
     * @param cliente cliente a ser adicionado
     * @return true caso seja possivel adicionar e false caso não
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
        cliente.setValorSeguro(calcularPrecoSeguroCliente(cliente));
        listaClientes.add(cliente);
        return true;
    }

    /**
     * Remove um cliente da listaClientes
     * @param identificacaoCliente cpf ou cnpj do cliente
     * @return true caso seja possivel remover o cliente e false caso não
     */
    public boolean removerCliente(String identificacaoCliente){
        for(int i = 0; i < listaClientes.size(); i++){
            if(listaClientes.get(i).getIdentificacao().equals(identificacaoCliente)){
                listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Lista todos os clientes (PF ou PJ) da listaClientes
     * @param tipo_cliente PF ou PJ
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
     * @param data data do ocorrido
     * @param endereco endereço do ocorrido
     * @param seguradora seguradora
     * @param veiculo veiculo
     * @return true caso seja possivel gerar e false caso tenha sinistro repetido na lista
     */
    public boolean gerarSinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo,
    Cliente cliente){  
        Sinistro novoSinistro = new Sinistro(data, endereco, seguradora, veiculo, cliente);

        if(listaSinistros.contains(novoSinistro)){
            return false;
        } else {
            cliente.setValorSeguro(calcularPrecoSeguroCliente(cliente));
            listaSinistros.add(novoSinistro);
            return true;
        }        
    }

    /**
     * visualiza os sinistros de um cliente
     * @param documentoCliente responsável dos sinistros
     * @return lista dos sinistros do cliente
     */
    public String visulizarSinistro(String documentoCliente){
        String sin = "";

        for(int i = 0; i < listaSinistros.size(); i++){
            if(listaSinistros.get(i).getCliente().getIdentificacao().equals(documentoCliente)){
                sin += listaSinistros.get(i) + "\n";
            }
        }

        return sin;
    }

    /**
     * @return lista dos sinistros
     */
    public ArrayList <Sinistro> listarSinistros(){
        return listaSinistros;
    }

    /**
     * remove um sinistro da lista de sinistros
     */
    public boolean removerSinistro(int id){
        for(Sinistro s : listaSinistros){
            if(s.getId() == id){
                listaSinistros.remove(s);
                for(int i = 0; i < Validacao.idRegistrados.size(); i++){
                    if(Validacao.idRegistrados.get(i) == id){
                        Validacao.idRegistrados.remove(i);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Calcula o preço do seguro de um cliente baseado em seu score e a quantidade de sinistros
     * @return preço do seguro
     */
    public double calcularPrecoSeguroCliente(Cliente cliente){
        int quantidadeSinistros = 0;
        for (Sinistro s : listaSinistros){
            if (s.getCliente().equals(cliente)){
                quantidadeSinistros++;
            }
        }
        return cliente.calculaScore() * (1 + quantidadeSinistros);
    }

    /**
     * Calcula a receita da seguradora
     * @return receita da seuguradora
     */
    public double calcularReceita() {
        double receita = 0;
        for (Cliente c : this.listaClientes) {
            receita += c.getValorSeguro(); 
        }
        return receita;
    }

    /**
     * Transferir seguro de um cliente para outro
     * @return true se a operação foi realizada com sucesso, false senão
     */
    public boolean transferirSeguro(Cliente clienteRemetente, Cliente clienteDestinatario) {
        if(clienteRemetente.getListaVeiculos().isEmpty()){
            return false;
        }

        for(Veiculo v : clienteRemetente.getListaVeiculos()){
            clienteDestinatario.adicionarVeiculo(v);
            clienteRemetente.removerVeiculo(v.getPlaca());
        }

        clienteRemetente.setValorSeguro(calcularPrecoSeguroCliente(clienteRemetente));
        clienteDestinatario.setValorSeguro(calcularPrecoSeguroCliente(clienteDestinatario));
        return true;
    }


    @Override
    public String toString() {
        return "->Seguradora" +
                "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\ne-mail: " + email +
                "\nEndereço: " + endereco +
                "\nLista do Sinistros: " + listaSinistros +
                "\nLista dos Clientes: " + listaClientes;
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

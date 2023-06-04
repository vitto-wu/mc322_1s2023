import java.util.ArrayList;
import java.time.LocalDate;

public class Seguradora {
    private String cnpj;
    private String nome;
    private String telefone; 
    private String endereco; 
    private String email;
    private ArrayList <Seguro> listaSeguros;
    private ArrayList <Cliente> listaClientes;

    //Construtor
    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) {
        if(!Validacao.validarCNPJ(cnpj)){
            throw new Error("Cpnj invalido");
        }
        if(!Validacao.validarNome(nome)){
            throw new Error("Nome invalido, deve contar apenas letras");
        }
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSeguros = new ArrayList <>();
        this.listaClientes = new ArrayList <>();
    }

    /**
     * Lista todos os clientes (PF ou PJ) da listaClientes
     * @param {String} tipoCliente - Cliente do tipo PF ou PJ
     * @return {String} lista dos clientes
     */
    public String listarClientes(String tipoCliente){
        StringBuilder tipo_clientes = new StringBuilder();
        tipo_clientes.append("[\n");

        for(Cliente c : listaClientes){
            if(c.getTipoCliente() == tipoCliente){
                tipo_clientes.append("- " + c.getDocumento() + "\n");
            }
        }

        tipo_clientes.append("]");

        return tipo_clientes.toString();
    }

    /**
     * Gera um seguro para o cliente PF
     * @return True caso consiga gerar e false caso não
     */
    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, Veiculo veiculo, ClientePF clientePF) {
        Seguro novoSeguro = new SeguroPF(dataInicio, dataFim, this, veiculo, clientePF);
        cadastrarCliente(clientePF);
        return listaSeguros.add(novoSeguro);
    }

    /**
     * Gera um seguro para o cliente PJ
     * @return True caso consiga gerar e false caso não
     */
    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, Frota frota, ClientePJ clientePJ) {
        Seguro novoSeguro = new SeguroPJ(dataInicio, dataFim, this, frota, clientePJ);
        cadastrarCliente(clientePJ);
        return listaSeguros.add(novoSeguro);
    }


    /**
     * Cancela o seguro do cliente pelo id
     * @param {int} id - Id do seguro
     * @return {boolean} true caso seja possível cancelar e false caso não
     */
    public boolean cancelarSeguro(int id){
        for (int i = 0; i < listaSeguros.size(); i++) {
            if (listaSeguros.get(i).getId() == id) {
                listaSeguros.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Cadastra um cliente adicionando o na listaClientes caso este seja válido ou não repetido
     * @param {Cliente} cliente - Cliente PF ou PJ
     * @return {boolean} true caso seja possivel cadastrar e false caso não
     */
    public boolean cadastrarCliente(Cliente cliente){
        if(cliente == null){
            return false;
        }

        if(listaClientes.contains(cliente)){
            return false;
        }

        listaClientes.add(cliente);
        return true;
    }

    /**
     * Remove um cliente da listaClientes
     * @param {String} documentoCliente - CPF ou Cnpj do cliente
     * @return {booleano} true caso seja possivel remover o cliente e false caso não
     */
    public boolean removerCliente(String documentoCliente){
        for(int i = 0; i < listaClientes.size(); i++){
            if(listaClientes.get(i).getDocumento().equals(documentoCliente)){
                listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca todos os seguros do cliente na seguradora
     * @param {Cliente} cliente - Cliente PF ou PJ
     * @return {ArrayList} lista dos seguros
     */
    public ArrayList <Seguro> getSeguroPorCliente(Cliente cliente){
        ArrayList <Seguro> segurosCliente = new ArrayList<>();

        for(Seguro s : listaSeguros){
            if(s.getCliente().equals(cliente)){
                segurosCliente.add(s);
            }
        }

        return segurosCliente;
    }

    /**
     * retorna os sinistros do cliente
     * @param {Cliente} cliente - Cliente PF ou PJ
     * @return {ArrayList} lista dos sinistros
     */
    public ArrayList <Sinistro> getSinistrosPorCliente(Cliente cliente){
        ArrayList <Sinistro> listaSinistros = new ArrayList<>();

        for(Seguro seguro : getSeguroPorCliente(cliente)){
            for(Sinistro sinistro : seguro.getListaSinistros()){
                listaSinistros.add(sinistro);
            }
        }

        return listaSinistros;
    }

    /**
     * Calcula a receita da seguradora
     * @return {double} receita da seuguradora
     */
    public double calcularReceita() {
        double receita = 0;
        for (Seguro s : this.listaSeguros) {
            receita += s.getValorMensal(); 
        }
        return receita;
    }

    @Override
    public String toString() {
        StringBuilder listaSegurosString = new StringBuilder();
        StringBuilder listaClientesString = new StringBuilder();

        for(Seguro s : listaSeguros){
            listaSegurosString.append("- " + s.getId() + "\n");
        }

        for(Cliente c : listaClientes){
            listaClientesString.append("- " + c.getDocumento() + "\n");
        }

        return  "\nNome: " + nome +
                "\nNome: " + cnpj +
                "\nTelefone: " + telefone +
                "\ne-mail: " + email +
                "\nEndereço: " + endereco +
                "\nLista de Seguros: [\n" + listaSegurosString + "]" +
                "\nLista dos Clientes: [\n" + listaClientesString + "]\n";
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

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    
}

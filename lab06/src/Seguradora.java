import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Seguradora {
    private String cnpj;
    private String nome;
    private String telefone; 
    private String endereco; 
    private String email;
    private ArrayList <Seguro> listaSeguros;
    private ArrayList <Cliente> listaClientes;
    private ArquivoClientePF arquivoClientePF;
    private ArquivoClientePJ arquivoClientePJ;
    private ArquivoCondutor arquivoCondutor;
    private ArquivoFrota arquivoFrota;
    private ArquivoSeguro arquivoSeguro;
    private ArquivoSinistro arquivoSinistro;
    private ArquivoVeiculo arquivoVeiculo;

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
        arquivoClientePF = new ArquivoClientePF();
        arquivoClientePJ = new ArquivoClientePJ();
        arquivoCondutor = new ArquivoCondutor();
        arquivoFrota = new ArquivoFrota();
        arquivoSeguro = new ArquivoSeguro();
        arquivoVeiculo = new ArquivoVeiculo();
        arquivoSinistro = new ArquivoSinistro();
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

    public boolean gravarDados(Object o, ArquivosTipo tipo) {
        if (tipo == ArquivosTipo.CLIENTE_PF) {
            return arquivoClientePF.gravarArquivo((ClientePF)o);
        } else if (tipo == ArquivosTipo.CLIENTE_PJ) {
            return arquivoClientePJ.gravarArquivo((ClientePJ)o);
        } else if (tipo == ArquivosTipo.CONDUTOR) {
            return arquivoCondutor.gravarArquivo((Condutor)o);
        } else if (tipo == ArquivosTipo.FROTA) {
            return arquivoFrota.gravarArquivo((Frota)o);
        } else if (tipo == ArquivosTipo.SEGURO) {
            return arquivoSeguro.gravarArquivo((Seguro)o);
        } else if (tipo == ArquivosTipo.VEICULO) {
            return arquivoVeiculo.gravarArquivo((Veiculo)o);
        }
        return arquivoSinistro.gravarArquivo((Sinistro)o);
    }

    private ClientePF gerarClientePF(String[] entrada){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String cpf = entrada[0];
        String nome = entrada[1];
        String telefone = entrada[2];
        String endereco = entrada[3];
        String email = entrada[4];
        String genero = entrada[5];
        String educacao = entrada[6];
        LocalDate dataNascimento = LocalDate.parse(entrada[7], formatoData);

        return new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNascimento);
    }

    private ClientePJ gerarClientePJ(String[] entrada){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String cnpj = entrada[0];
        String nome = entrada[1];
        String telefone = entrada[2];
        String endereco = entrada[3];
        String email = entrada[4];
        LocalDate dataFundacao = LocalDate.parse(entrada[5], formatoData);
        String codeFrota = entrada[6];

        return new ClientePJ(nome, telefone, endereco, email, cnpj,
            dataFundacao, 0);
    }

    private Condutor gerarCondutor(String[] entrada){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String cpf = entrada[0];
        String nome = entrada[1];
        String telefone = entrada[2];
        String endereco = entrada[3];
        String email = entrada[4];
        LocalDate dataNascimento = LocalDate.parse(entrada[5], formatoData);

        return new Condutor(nome, cpf, telefone, endereco, email, dataNascimento);
    }

    private Frota gerarFrota(String[] entrada){
        Frota frota = new Frota();
        for (int i = 1; i <= 3; i++) {
            if (i == entrada.length) { return frota; }
            if (!entrada[i].equals("")) {
                frota.adicionarVeiculo(gerarVeiculo(arquivoVeiculo.lerArquivo(entrada[i]).split(",")));
            }
        }
        return frota;
    }

    private Veiculo gerarVeiculo(String[] entrada){
        String placa = entrada[0];
        String marca = entrada[1];
        String modelo = entrada[2];
        int anoFabricacao = Integer.parseInt(entrada[3]);

        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    private Seguro gerarSeguro(String[] entrada){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataInicio = LocalDate.parse(entrada[1], formatoData);
        LocalDate dataFim = LocalDate.parse(entrada[2], formatoData);
        double valorMensal = Double.parseDouble(entrada[6]);
        SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, this, null, null);
        seguro.setValorMensal(valorMensal);

        return seguro;
    }

    private Sinistro gerarSinistro(String[] entrada){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate data = LocalDate.parse(entrada[1],formatoData);
        String endereco = entrada[2];

        return new Sinistro(data, endereco, null, null);
    }

    public String lerDados(String identificador, ArquivosTipo tipo){
        String objetoToString = "";
        if (tipo == ArquivosTipo.CLIENTE_PF) {
            objetoToString = gerarClientePF(arquivoClientePF.lerArquivo(identificador).split(",")).toString();
        } else if (tipo == ArquivosTipo.CLIENTE_PJ) {
            objetoToString = gerarClientePJ(arquivoClientePJ.lerArquivo(identificador).split(",")).toString();
        } else if (tipo == ArquivosTipo.CONDUTOR) {
            objetoToString = gerarCondutor(arquivoCondutor.lerArquivo(identificador).split(",")).toString();
        } else if (tipo == ArquivosTipo.FROTA) {
            objetoToString = gerarFrota(arquivoFrota.lerArquivo(identificador).split(",")).toString();
        } else if (tipo == ArquivosTipo.SEGURO) {
            objetoToString = gerarSeguro(arquivoSeguro.lerArquivo(identificador).split(",")).toString();
        } else if (tipo == ArquivosTipo.VEICULO) {
            objetoToString = gerarVeiculo(arquivoVeiculo.lerArquivo(identificador).split(",")).toString();
        } else {
            objetoToString = gerarSinistro(arquivoSinistro.lerArquivo(identificador).split(",")).toString();
        }
        return objetoToString;
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

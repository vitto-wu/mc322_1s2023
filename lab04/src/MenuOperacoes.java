import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public enum MenuOperacoes {
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    TRASNFERIR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    SAIR(0);

    public final int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }

    public int getOperacao() {
        return this.operacao;
    }

    public Seguradora objetoSeguradora(String nomeSeguradora){
        for(Seguradora s : Validacao.listaSeguradoras){
            if(s.getNome().equals(nomeSeguradora)){
                return s;
            }
        }
        return null;
    }

    //------------- Menu CADASTRAR ---------------- //
    /**
     * Cadastra o cliente na seguradora
     */
    public boolean cadastro(String nomeSeguradora, Cliente cliente) {
        Seguradora s = objetoSeguradora(nomeSeguradora);
        if (s.getNome().equals(nomeSeguradora)) {
            return s.cadastrarCliente(cliente);
        }
        return false;
    }

    /**
     * Cadastra o veículo do cliente
     */
    public boolean cadastro(String nomeSeguradora, String clienteDocumento, Veiculo veiculo) {
        Seguradora s = objetoSeguradora(nomeSeguradora);
        if (s.getNome().equals(nomeSeguradora)) {
            for (Cliente c : s.getListaClientes()) {
                if (c.getIdentificacao().equals(clienteDocumento)) {
                    return c.adicionarVeiculo(veiculo);
                }
            }
        }
        return false;
    }

    /**
     * Cadastra uma nova seguradora
     */
    public boolean cadastro(Seguradora seguradora) {
        if (Validacao.listaSeguradoras.contains(seguradora)) {
            return false;
        }
        Validacao.listaSeguradoras.add(seguradora);
        return true;
    }

    //------------- Menu LISTAR ---------------- //
    /**
     * Lista os clientes (0), sinistros (1), veiculos (2) da seguradora
     * @return lista de clientes / lista de sinistros / lista de veículos
     */
    public String listar(String nomeSeguradora, int operacao) {
        Seguradora s = objetoSeguradora(nomeSeguradora);
        String listaRetorno = "";

        switch (operacao) {
            case 0:
                listaRetorno = "Lista de Clientes da Seguradora" + nomeSeguradora + ":\n";
                if (s.getListaClientes().size() == 0) {
                    listaRetorno += "A seguradora não possui clientes\n";
                }
                for (int i = 0; i <= s.getListaClientes().size(); i++) {
                    listaRetorno += s.getListaClientes().get(i).toString() + "\n";
                }
            case 1:
                listaRetorno = "Lista de sinistros registrados na Seguradora" + nomeSeguradora + ":\n";
                if (s.getListaSinistros().size() == 0) {
                    listaRetorno += "A seguradora não possui sinistros registrados\n";
                }
                for (int i = 0; i <= s.getListaClientes().size(); i++) {
                    listaRetorno += s.getListaClientes().get(i).toString() + "\n";
                }
            case 2:
                listaRetorno = "Lista de veículos cadastrados na Seguradora" + nomeSeguradora + ":\n";
                for (Cliente c : s.getListaClientes()) {
                    for (Veiculo v : c.getListaVeiculos()) {
                        listaRetorno += v.toString() + "\n";
                    }
                }
        }
        return listaRetorno;
    }

 /**
     * Lista sinistros (0), veiculos (1) do cliente
     * @return lista de sinistros / lista de veículos
     */
    public String listar(String nomeSeguradora, String documentoCliente, int operacao){
        Seguradora s = objetoSeguradora(nomeSeguradora);
        String listaRetorno = "";
        
        switch (operacao){
            case 0:
                listaRetorno += "Sinistro(s) registrado(s) no documento:" + documentoCliente + "\n";
                String sin = s.visulizarSinistro(documentoCliente);
                if(sin.length() == 0){
                    listaRetorno += "Não há sinistro registrado nesse documento";
                }
            case 1:
                listaRetorno += "Veículos(s) registrado(s) no documento:" + documentoCliente + "\n";
                for(Cliente c : s.getListaClientes()){
                    if(c.getIdentificacao().equals(documentoCliente)){
                        for(Veiculo v : c.getListaVeiculos()){
                            listaRetorno += v.toString() + "\n";
                        }
                    }
                }
        }

        return listaRetorno;
    }
    
    //------------- Menu EXCLUIR ---------------- //
    /**
     * Remover o cliente da seguradora
     */
    public boolean remocao(String nomeSeguradora, String documentoCliente) {
        Seguradora s = objetoSeguradora(nomeSeguradora);
        if (s.getNome().equals(nomeSeguradora)) {
            return s.removerCliente(documentoCliente);
        }
        return false;
    }

    /**
     * Remover o veiculo do cliente
     */
    public boolean remocao(String nomeSeguradora, String documentoCliente, String placa){
        Seguradora s = objetoSeguradora(nomeSeguradora);
        if(s.getNome().equals(nomeSeguradora)){
            for(Cliente c : s.getListaClientes()){
                return c.removerVeiculo(placa);
            }
        }

        return false;
    }

    /**
     * Remover o sinistro da seguradora
     */
    public boolean remocao(String nomeSeguradora, int sinistro){
        Seguradora s = objetoSeguradora(nomeSeguradora);
        if(s.getNome().equals(nomeSeguradora)){
            s.removerSinistro(sinistro);
        }
        return false;
    }

    //------------- GERAR SINISTRO ---------------- //
    public boolean gerarSinistro(String data, String endereco, String nomeSeguradora, String placa, String documentoCliente){
        Seguradora s = objetoSeguradora(nomeSeguradora);
        Cliente cliente = null;
        Veiculo veiculo = null;

        for(Cliente c : s.getListaClientes()){
            if(c.getIdentificacao().equals(documentoCliente)){
                cliente = c;
                for(Veiculo v : c.getListaVeiculos()){
                    if(v.getPlaca().equals(placa)){
                        veiculo = v;
                        break;
                    }
                }
                break;
            }
        }

        return s.gerarSinistro(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")), endereco, s, veiculo, cliente);
    }

    //------------- TRANSFERRIR SEGURO ---------------- //
    public boolean transferirSeguro(String nomeSeguradora, String clienteRemetenteDocumento, String clienteDestinatarioDocumento){
        Seguradora s = objetoSeguradora(nomeSeguradora);
        Cliente clienteRemetente = null, clienteDestinatario = null;

        for(Cliente c : s.getListaClientes()){
            if(c.getIdentificacao().equals(clienteRemetenteDocumento)){
                clienteRemetente = c;
            } else if(c.getIdentificacao().equals(clienteDestinatarioDocumento)){
                clienteDestinatario = c;
            }
        }
        return s.transferirSeguro(clienteRemetente, clienteDestinatario);
    }

    //------------- RECEITA ---------------- //
    public double receita(String nomeSeguradora){
        Seguradora s = objetoSeguradora(nomeSeguradora);
        return s.calcularReceita();
    }
}

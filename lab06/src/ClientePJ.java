import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    private ArrayList <Frota> listaFrota;
    private int qntdFuncionarios;

    //Construtor
    public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj,
            LocalDate dataFundacao, int qntdFuncionarios) {
        super(nome, telefone, endereco, email);
        if(!Validacao.validarCNPJ(cnpj)){
            throw new Error("Cpnj invalido");
        }
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.listaFrota = new ArrayList <Frota>();
        this.qntdFuncionarios = qntdFuncionarios;
    }
    
    /**
     * Cadastra uma nova frota na lista de frotas
     * @param {Frota} frota
     * @return {boolean} true caso seja possivel adicionar e false caso contrário
     */
    public boolean cadastrarFrota(Frota frota){
        if(frota == null || listaFrota.contains(frota)){
            return false;
        }

        listaFrota.add(frota);
        return true;
    }

    /**
     * Adiciona o veiculo caso não esteje na frota e remove caso contrário 
     * @param {Veiculo} veiculo
     * @param {Frota} frota
     * @return {boolean} true caso seja possivel realizar a operação e falso caso não
     */
    public boolean atualizarFrota(Veiculo veiculo, Frota frota){
        if(veiculo == null){
            return false;
        }

        if(frota.getListaVeiculos().contains(veiculo)){
            frota.removerVeiculo(veiculo);
        } else {
            frota.adicionarVeiculo(veiculo);
        }

        return true;
    }

    /**
     * remove a frota da lista de frotas
     * @param {Frota} frota
     * @return {boolean} true caso seja possivel remover e false caso não
     */
    public boolean atualizarFrota(Frota frota){
        if(frota == null){
            return false;
        }

        listaFrota.remove(frota);
        return true;
    }

    /**
     * busca todos os veículos pertencentes à frota
     * @param {Frota} frota
     * @return {ArrayList} lista de veiculos
     */
    public ArrayList <Veiculo> getVeiculosPorFrota(Frota frota){
        ArrayList <Veiculo> veiculos = new ArrayList<>();

        for(Veiculo v : frota.getListaVeiculos()){
            veiculos.add(v);
        }

        return veiculos;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder listaFrotaString = new StringBuilder();

        for(Frota f : listaFrota){
           listaFrotaString.append("- " + f.getCode() + "\n");
        }

        return  super.toString() +
                "\nCNPJ:      " + cnpj +
                "\nData de Fundaçao: " + dataFundacao.format(formatoData) +
                "\nLista de Frota:  [\n" + listaFrotaString + "]\n";
    }
            
    //Getters e Setters
    @Override
    public String getDocumento(){
        return cnpj;
    }

    @Override
    public String getTipoCliente(){
        return "PJ";
    }

    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    public int getQntdFuncionarios() {
        return qntdFuncionarios;
    }

    public void setQntdFuncionarios(int qntdFuncionarios) {
        this.qntdFuncionarios = qntdFuncionarios;
    }
}

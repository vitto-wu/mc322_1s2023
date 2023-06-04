import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private LocalDate dataNascimento;
    private ArrayList <Veiculo> listaVeiculos;

    // Construtor
    public ClientePF(String nome, String telefone, String endereco, String email, String cpf, String genero,
            String educacao, LocalDate dataNascimento) {
        super(nome, telefone, endereco, email);
        if(!Validacao.validarCPF(cpf)){
            throw new Error("CPF invalido");
        }
        this.cpf = cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = new ArrayList <Veiculo>();
    }

    /**
     * Adiciona o veículo na lista de veículos do cliente
     * @param {Veiculo} veiculo
     * @return {boolean} true caso seja possivel adicionar e false caso não
     */
    public boolean cadastrarVeiculo(Veiculo veiculo){
        if(veiculo == null || listaVeiculos.contains(veiculo)){
            return false;
        }

        listaVeiculos.add(veiculo);
        return true;
    }

    /**
     * Remove o veículo da lista de veículos do cliente
     * @param {String} placa do veículo
     * @return {boolean} true caso seja possivel remover e false caso não
     */
    public boolean removerVeiculo(String placa){
        for(Veiculo v : listaVeiculos){
            if(v.getPlaca().equals(placa)){
                listaVeiculos.remove(v);
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return super.toString() +
        "\nCPF:       " + cpf + 
        "\nGenero:    " + genero + 
        "\nEducaçao:  " + educacao + 
        "\nData de Nascimento: " + dataNascimento.format(formatoData) +
        "\nLista de Veículos:  " + listaVeiculos + "\n";
    }
    
    //Getters e Setters
    @Override
    public String getDocumento(){
        return cpf;
    }

    @Override
    public String getTipoCliente(){
        return "PF";
    }

    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }   
}


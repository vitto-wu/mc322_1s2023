import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Sinistro {
    private final int id;
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    /**
     * Gera uma id entre 0 e 999999, nao registrado na lista idRegistrados
     * @return id
     */
    public int gerarId() {
        Random r = new Random();
        int id = r.nextInt(1000000);
        for (Integer i : Validacao.idRegistrados){
            id = id == i ? gerarId() : id;
        }
        return id;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "-> Sinistro" +
                "\nID: " + id +
                "\nData: " + data.format(formatoData) +
                "\nEnderço: " + endereco +
                "\nSeguradora: " + seguradora.getNome() +
                "\nVeículo: " + veiculo.getPlaca() +
                "\nCliente: " + cliente.getIdentificacao();
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
}

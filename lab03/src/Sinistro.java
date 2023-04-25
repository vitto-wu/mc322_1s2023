import java.util.Random;

public class Sinistro {
    private final int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    
    //Constutor
    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo,
            Cliente cliente) {
        id = gerarId();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    /**
     * @return as informações do sinistro
     */
    public String toString(){
        return String.format("ID: %d\nData: %s\nEndereço: %s\n-Seguradora: \n%s-Veículo: \n%s\n-Cliente: \n%s", this.id, this.data, this.endereco, this.seguradora, this.veiculo, this.cliente);
    }

    /**
     * Gera uma id entre 100000 e 99999
     * @return id
     */
    public int gerarId() {
        Random r = new Random();
        return 100000 + r.nextInt(89999);
    }
    
    //Getters e Setters
    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

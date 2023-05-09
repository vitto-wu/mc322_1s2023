import java.util.ArrayList;

public class Cliente {
    private String  nome;
    private String  endereco;
    private ArrayList <Veiculo> listaVeiculos;
    private double valorSeguro;
    
    //Construtor
    public Cliente(String nome, String endereco) {
        if(Validacao.validarNome(nome)){
            this.nome = nome;
        } else {
            this.nome = "nome inválido";
        }
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList <Veiculo>();
    }

    public double calculaScore(){
        return 0.0;
    }

    /**
     * Adiciona o veículo na lista de veículos do cliente
     * @param veiculo objeto Veículo
     * @return true caso seja possivel e false caso não
     */
    public boolean adicionarVeiculo(Veiculo veiculo){
        if(listaVeiculos.contains(veiculo)){
            return false;
        }

        listaVeiculos.add(veiculo);
        return true;
    }

    /**
     * Remove o veículo da lista de veículos do cliente
     * @param placa placa do veículo
     * @return true caso seja possivel e false caso não
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
        return "-> Cliente" +
                "\nNome: " + nome +
                "\nEndereço: " + endereco +
                "\nLista Veículos: " + listaVeiculos +
                "\nValor do Seguro: " + valorSeguro;
    }

    //Getters e Setteres
    public String getTipoCliente(){
        return null;
    }

    public String getIdentificacao(){
        return null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    
}

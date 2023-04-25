import java.util.ArrayList;

public class Cliente {
    private String  nome;
    private String  endereco;
    private ArrayList <Veiculo> listaVeiculos;
    
    //Construtor
    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
    }
    
    /**
     * @return as informações do objeto
     */
    public String toString(){
        return String.format("Nome: %s\nEndereço: %s\nLista de Veículos: \n" + this.listaVeiculos + "\n", this.nome, this.endereco );
    }
    
    //Getters e Setters
    public String getTipoCliente(){
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
     * @param veiculo objeto Veículo
     * @return true caso seja possivel e false caso não
     */
    public boolean removerVeiculo(Veiculo veiculo){
        if(listaVeiculos.contains(veiculo)){
            listaVeiculos.remove(veiculo);
            return true;
        }

        return false;
    }
}

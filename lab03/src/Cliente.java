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
    
    //Função na qual retorna os dados do cliente         
    public String toString(){
        return String.format("Nome: %s\nEndereço: %s\nLista de Veículos: %s", this.nome, this.endereco, this.listaVeiculos);
    }

    public String getTipoCliente(){
        return null;
    }

    //Getters e Setters
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

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
}

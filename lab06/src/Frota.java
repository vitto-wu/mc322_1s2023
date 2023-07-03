import java.util.ArrayList;
import java.util.Random;

public class Frota {
    private int code;
    private ArrayList <Veiculo> listaVeiculos;

    //Construtor
    public Frota() {
        this.code = gerarCode();
        this.listaVeiculos = new ArrayList <Veiculo>();
    }

    /**
     * Gera um codigo aleatório, não registrado na lista codesFrota
     * @return {Number} code
     */
    public int gerarCode() {
        Random r = new Random();
        int id = r.nextInt(1000000);

        if(BancoDados.idSeguroRegistrados.contains(id)){
            id = gerarCode();
        }

        BancoDados.codesFrota.add(code);
        return id;
    }

    /**
     * Adiciona o veículo a frota
     * @param {Veiculo} objeto veículo a ser adicionado
     * @return {boolean} true caso seja possivel e false caso não
     */
    public boolean adicionarVeiculo(Veiculo veiculo){
        if(listaVeiculos.contains(veiculo)){
            return false;
        }

        listaVeiculos.add(veiculo);
        return true;
    }

    /**
     * Remove o veículo da frota
     * @param {Veiculo} veiculo
     * @return {boolean} true caso seja possivel remover e false caso não
     */
    public boolean removerVeiculo(Veiculo veiculo){
        if(veiculo == null || !listaVeiculos.contains(veiculo)){
            return false;
        }

        listaVeiculos.remove(veiculo);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder placaVeiculos = new StringBuilder();

        for(Veiculo v : listaVeiculos){
            placaVeiculos.append("- " + v.getPlaca() + "\n");
        }

        return  "\nCode:  " + code + 
                "\nListaVeiculos: [\n" + placaVeiculos + "]\n";
    }

    //Getters e setters
    public String getCode() {
        return Integer.toString(code);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }    
}

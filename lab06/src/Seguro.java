import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Seguro {
    private final int id;
    private LocalDate dataInicio; 
    private LocalDate dataFim; 
    private Seguradora seguradora; 
    private ArrayList <Sinistro> listaSinistros; 
    private ArrayList <Condutor> listaCondutores; 
    protected double valorMensal;

    //Construtor
    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.id = gerarId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<>();
        this.listaCondutores = new ArrayList<>();
    }

    /**
     * Gera uma id entre 0 e 999999, não registrado na lista idSeguroRegistrados
     * @return {number} id
     */
    public int gerarId() {
        Random r = new Random();
        int id = r.nextInt(1000000);

        if(BancoDados.idSeguroRegistrados.contains(id)){
            id = gerarId();
        }

        BancoDados.idSeguroRegistrados.add(id);
        return id;
    }

    /**
     * adiciona o condutor na lista de condutores do seguro
     * @return {boolean} true caso seja possivel adicionar o condutor e false caso não
     */
    public boolean autorizarCondutor(Condutor condutor){
        if (!listaCondutores.contains(condutor)) {
            listaCondutores.add(condutor);
            valorMensal = calcularValor();
            return true;
        }
        return false;
    }

    /**
     * remover o condutor na lista de condutores do seguro
     * @return {boolean} true caso seja possivel remover o condutor e false caso não
     */
    public boolean desautorizarCondutor(Condutor condutor){
        if (listaCondutores.contains(condutor)) {
            listaCondutores.remove(condutor);
            valorMensal = calcularValor();
            return true;
        }
        return false;
    }

    /**
     * calcula o valor mensal do seguro
     * @return {double} valor mensal do seguro
     */
    public double calcularValor(){
        return 0.0;
    }

    /**
     * gera um novo sinistro
     * @param {LocalDate} data - Data do ocorrido
     * @param {String}    endereco - Endereço do ocorrido
     * @param {Condutor}  condutor
     * @param {Seguro}    seguro
     * @return true caso seja possivel gerar e false caso contrário
     */
    public boolean gerarSinistro(LocalDate data, String endereco, Condutor condutor){
        Sinistro novoSinistro = new Sinistro(data, endereco, condutor, this);

        if (condutor.getListaSinistros().contains(novoSinistro)) {
            return false;
        }

        condutor.adicionarSinistro(novoSinistro);
        listaSinistros.add(novoSinistro);
        return true;
    }

    /**
     * remove sinistro do seguro e do condutor
     * @param {int}  idSinistro - Id do sinistro
     * @param {Condutor}  condutor
     * @return true caso seja possivel remover e false caso não
     */
    public boolean removerSinistro(int idSinistro, Condutor condutor){
        for(Sinistro s : listaSinistros){
            if(s.getId() == idSinistro){
                condutor.getListaSinistros().remove(s);
                listaSinistros.remove(s);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder listaSinistrosString = new StringBuilder();
        StringBuilder listaCondutoresString = new StringBuilder();

        for(Sinistro s : listaSinistros){
            listaSinistrosString.append("- " + s.getId() + "\n");
        }

        for(Condutor c : listaCondutores){
            listaCondutoresString.append("- " + c.getCpf() + "\n");
        }

        return  "\nid:             " + id + 
                "\nData de Inicio: " + dataInicio + 
                "\nData Fim:       " + dataFim + 
                "\nSeguradora:     " + seguradora.getNome() +
                "\nLista de Sinistros:  [\n" + listaSinistrosString + "]" +
                "\nLista de Condutores: [\n" + listaCondutoresString + "]" +
                "\nValor Mensal:   " + valorMensal;
    }

    //Getters e Setters
    public Cliente getCliente(){
        return null;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    } 

    
}

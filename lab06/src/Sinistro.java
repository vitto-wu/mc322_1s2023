import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Sinistro {
    private final int id;
    private LocalDate data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;
    
    //Construtor
    public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

    /**
     * Gera uma id entre 0 e 999999, não registrado na lista idSinistroRegistrados
     * @return {number} id
     */
    public int gerarId() {
        Random r = new Random();
        int id = r.nextInt(1000000);
        
        if(BancoDados.idSinistroRegistrados.contains(id)){
            id = gerarId();
        }

        BancoDados.idSinistroRegistrados.add(id);
        return id;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return  "\nId:       " + id +
                "\nData:     " + data.format(formatoData) +
                "\nEnderço:  " + endereco +
                "\nCondutor: " + condutor.getCpf() +
                "\nSeguro:   " + seguro.getId() + "\n";
    }

    //Getters e setters
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

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    
}

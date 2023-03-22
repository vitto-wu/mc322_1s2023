import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    // Constructor
    public Sinistro(String data, String endereco) {
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
    }
    
    // Geração de Ids
    public int gerarId() {
        Random r = new Random();
        return r.nextInt(100000);
    }
    
    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

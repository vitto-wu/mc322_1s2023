import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List <Sinistro> listaSinistros;
    private List <Cliente> listaClientes;
    
    public Seguradora(String nome, String telefone, String email, String endereco, String listaSinistros,
            String listaClientes) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = List <Sinistro>;
        this.listaClientes = listaClientes;
    }

}

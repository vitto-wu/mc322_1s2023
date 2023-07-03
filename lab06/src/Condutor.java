import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Condutor {
    private String nome; 
    private final String cpf; 
    private String telefone; 
    private String endereco; 
    private String email; 
    private LocalDate dataNascimento; 
    private ArrayList <Sinistro> listaSinistros;

    //Construtor
    public Condutor(String nome, String cpf, String telefone, String endereco, String email, LocalDate dataNascimento) {
        if(!Validacao.validarNome(nome)){
            throw new Error("Nome invalido, deve conter apenas letras");
        }
        if(!Validacao.validarCPF(cpf)){
            throw new Error("CPF invalido");
        }
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinistros = new ArrayList <Sinistro>();
    }

    /**
     * Adiciona o sisnistro na lista de sinistros do condutor
     * @param {Sinistro} sinistro
     */
    public void adicionarSinistro(Sinistro sinistro){
        listaSinistros.add(sinistro);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder listaSinistrosString = new StringBuilder();

        for(Sinistro s : listaSinistros){
            listaSinistrosString.append("\n- " + s.getId() + "\n");
        }

        return  "\nNome:      " + nome +         
                "\nCPF:       " + cpf + 
                "\nTelefone:  " + telefone + 
                "\nEndereço:  " + endereco + 
                "\nE-mail:    " + email + 
                "\nData de Nascimento: " + dataNascimento.format(formatoData) +
                "\nLista de Sinistros: [\n" + listaSinistrosString + "]\n";
    }

    //Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    } 
}
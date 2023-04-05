import java.util.Date;
import java.util.List;

public class Cliente {
    private String  nome;
    private String  endereco;
    private Date dataLicenca;
    private String  educacao;
    private String  genero;
    private String  classeEconomica;
    private List <Veiculo> listaVeiculos;
    
    //Construtor
    public Cliente(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, List<Veiculo> listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.listaVeiculos = listaVeiculos;
    }
    
    //Função na qual retorna os dados do cliente         
    public String toString(){
        return String.format("Nome: %s\nEndereço: %s\nData da Licença: %d\nEducação: %s\nGenero: %s\nClasse Econômica: %s\nLista de Veículos: %s", this.nome, this.endereco, this.dataLicenca, this.educacao, this.genero, this.classeEconomica, this.listaVeiculos);
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

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

}

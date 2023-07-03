import java.time.LocalDate;
import java.time.Period;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ clientePJ;

    // Construtor
    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota,
            ClientePJ clientePJ) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.clientePJ = clientePJ;
        this.valorMensal = calcularValor();
    }

    @Override
    public double calcularValor(){
        int qntdVeiculos = 0;
        LocalDate dataHoje = LocalDate.now();
        int anosPosFundacao = Period.between(clientePJ.getDataFundacao(), dataHoje).getYears();
        int qntdSinistrosCondutor = 0;
        
        for(Frota f : clientePJ.getListaFrota()){
            qntdVeiculos += clientePJ.getVeiculosPorFrota(f).size();
        }

        for(Condutor c : getListaCondutores()){
            qntdSinistrosCondutor += c.getListaSinistros().size();
        }

        return CalcSeguro.VALOR_BASE.getValor() * (10 + clientePJ.getQntdFuncionarios() / 10) *
                (1 + 1 / (qntdVeiculos + 2) ) *
                (1 + 1 / (anosPosFundacao + 2) ) *
                (2 + getListaSinistros().size() / 10) *
                (5 + qntdSinistrosCondutor / 10);
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\nFrota:          " + frota.getCode() + 
                "\nClientePJ:      " + clientePJ.getCnpj() + "\n";
    }

    // Getters e setters
    public Cliente getCliente(){
        return clientePJ;
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getClientePJ() {
        return clientePJ;
    }

    public void setClientePJ(ClientePJ clientePJ) {
        this.clientePJ = clientePJ;
    }
}

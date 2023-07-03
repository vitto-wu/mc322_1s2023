import java.time.LocalDate;
import java.time.Period;

public class SeguroPF extends Seguro {
    Veiculo veiculo;
    ClientePF clientePF;

    // Construtor
    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora,
            Veiculo veiculo, ClientePF clientePF) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.clientePF = clientePF;
        this.valorMensal = calcularValor();
    }

    @Override
    public double calcularValor(){
        LocalDate dataHoje = LocalDate.now();
        int idade = Period.between(clientePF.getDataNascimento(), dataHoje).getYears();
        double fator_idade = 0.0;
        int qntdSinistrosCondutor = 0;

        for(Condutor c : getListaCondutores()){
            qntdSinistrosCondutor += c.getListaSinistros().size();
        }

        if(idade >= 18 && idade < 30){
            fator_idade = CalcSeguro.FATOR_18_30.getValor();
        } else if(idade >= 30 && idade < 60){
            fator_idade = CalcSeguro.FATOR_30_60.getValor();
        } else if(idade >= 60 && idade < 90){
            fator_idade = CalcSeguro.FATOR_60_90.getValor();
        }

        return CalcSeguro.VALOR_BASE.getValor() * fator_idade * 
                (1 + 1/ clientePF.getListaVeiculos().size() + 2 ) * 
                (2 + getListaSinistros().size() / 10) * 
                (5 + qntdSinistrosCondutor /10);
    }

    
    @Override
    public String toString() {
        return  super.toString() +
                "\nVeiculo:        " + veiculo.getPlaca() + 
                "\nClientePF:      " + clientePF.getCpf() + "\n";
    }

    // Getters e setters
    public Cliente getCliente(){
        return clientePF;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getClientePF() {
        return clientePF;
    }

    public void setClientePF(ClientePF clientePF) {
        this.clientePF = clientePF;
    }
}

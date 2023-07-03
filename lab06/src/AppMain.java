import java.time.LocalDate;

public class AppMain {
    public static void main(String[] args) throws Exception {

        //Instanciação de Objetos das Classes
        ClientePF aClientePF = new ClientePF("clientePfA","(11) 98392-4983", "Rua Quadrado", "clientePFA@mail.com", "691.484.220-53", "masculino", "Ensino Médio Completo", LocalDate.of(1999, 01, 01));
        ClientePF bClientePF = new ClientePF("clientePfB","(41) 9847-8921", "Rua Círculo", "BPF@mail.com", "605.109.380-00", "femenino", "Ensino Médio Completo", LocalDate.of(1994, 12, 31));
        ClientePJ clientePJ = new ClientePJ("empresaPJ", "(32) 97318-1734", "Rua Retângulo", "empresaPJ@mail.com", "27.743.073/0001-20", LocalDate.of(2004, 06, 06), 15);

        Condutor aCondutor = new Condutor("clientePfA", "691.484.220-53", "(11) 98392-4983", "Rua Quadrado", "clientePFA@gmail.com", LocalDate.of(1999, 01, 01));
        Condutor bCondutor = new Condutor("clientePfB", "605.109.380-00", "(41) 9847-8921", "Rua Círculo", "BPF@gmail.com", LocalDate.of(1994, 12, 31));

        Seguradora segSeguro = new Seguradora("27.278.045/0001-89", "segSeguro", "(92) 98938-1923", "segseguro@mail.com", "Rua Hexagono");
        BancoDados.listaSeguradoras.add(segSeguro);

        Veiculo aVeiculo = new Veiculo("ONJ8S01", "Audi", "Audi A3", 2019);
        Veiculo bVeiculo = new Veiculo("FOH9U73", "Audi", "Audi A4", 2020);
        Veiculo cVeiculo = new Veiculo("AIJ8O46", "Audi", "Audi A5", 2021);
        aClientePF.cadastrarVeiculo(aVeiculo);
        bClientePF.cadastrarVeiculo(bVeiculo);
        bClientePF.cadastrarVeiculo(cVeiculo);

        Frota frotaA = new Frota();
        clientePJ.cadastrarFrota(frotaA);
        clientePJ.atualizarFrota(cVeiculo, frotaA);

        segSeguro.gerarSeguro(LocalDate.of(2023, 06, 01), LocalDate.of(2028, 12, 31), aVeiculo, aClientePF);
        segSeguro.gerarSeguro(LocalDate.of(2023, 06, 01), LocalDate.of(2028, 12, 31), frotaA, clientePJ);
        SeguroPF segPF = (SeguroPF) segSeguro.getSeguroPorCliente(aClientePF).get(0);
        SeguroPJ segPJ = (SeguroPJ) segSeguro.getSeguroPorCliente(clientePJ).get(0);
        segPF.autorizarCondutor(aCondutor);

        segPF.gerarSinistro(LocalDate.of(2023, 06, 02), "Rua Cubo", aCondutor);
        Sinistro aSinistro = segPF.getListaSinistros().get(0);

        //impressao da função toString()
        System.out.println("\n............  TO STRING DE CADA OBJETO  .........\n");  
        System.out.println("-> CLIENTES: ");
        System.out.println(aClientePF);
        System.out.println(bClientePF);
        System.out.println(clientePJ);
        System.out.println("\n->CONDUTORES: ");
        System.out.println(aCondutor);
        System.out.println(bCondutor);
        System.out.println("\n-> SEGURADORA: ");
        System.out.println(segSeguro);
        System.out.println("\n-> VEICULOS: ");
        System.out.println(aVeiculo);
        System.out.println(bVeiculo);
        System.out.println(cVeiculo);
        System.out.println("\n-> FROTA: ");
        System.out.println(frotaA);
        System.out.println("\n-> SEGUROS: ");
        System.out.println(segPF);
        System.out.println(segPJ);
        System.out.println("\n-> SINISTRO: ");
        System.out.println(aSinistro);

        //Impressao das funçoes principais da seguradora
        System.out.println("\n.......  FUNCOES PRINCIPAIS DA SEGURADORA  ....\n");  
        System.out.println("\n-> listarClientes(): ");
        System.out.println(segSeguro.listarClientes("PF"));
        System.out.println("\n-> gerarSeguro(): ");
        System.out.println(segSeguro.gerarSeguro(LocalDate.of(2023, 06, 01), LocalDate.of(2028, 12, 31), aVeiculo, aClientePF));
        System.out.println("\n-> gerarSeguro(): ");
        System.out.println(segSeguro.cancelarSeguro(segSeguro.getListaSeguros().get(2).getId()));
        System.out.println("\n-> cadastrarCliente(): ");
        System.out.println(segSeguro.cadastrarCliente(bClientePF));
        System.out.println("\n-> cadastrarCliente(): ");
        System.out.println(segSeguro.cadastrarCliente(bClientePF));
        System.out.println("\n-> removerCliente(): ");
        System.out.println(segSeguro.removerCliente(bClientePF.getDocumento()));
        System.out.println("\n-> getSeguroPorCliente(): ");
        System.out.println(segSeguro.getSeguroPorCliente(aClientePF));
        System.out.println("\n-> getSinistrosPorCliente(): ");
        System.out.println(segSeguro.getSinistrosPorCliente(aClientePF));
        
        System.out.println("\n....    OPERACOES EM ARQUIVOS   ....\n");

        System.out.println("\nREGISTRO DE UM CLIENTE PF: ");
        System.out.println(segSeguro.gravarDados(aClientePF, ArquivosTipo.CLIENTE_PF));
        System.out.println("\n-> REGISTRO DE UM CLIENTE PJ: ");
        System.out.println(segSeguro.gravarDados(clientePJ, ArquivosTipo.CLIENTE_PJ));
        System.out.println("\n-> REGISTRO DE UM CONDUTOR: ");
        System.out.println(segSeguro.gravarDados(aCondutor, ArquivosTipo.CONDUTOR));
        System.out.println("\n-> REGISTRO DE UMA FROTA: ");
        System.out.println(segSeguro.gravarDados(frotaA, ArquivosTipo.FROTA));
        System.out.println("\n-> REGISTRO DE UM VEICULO: ");
        System.out.println(segSeguro.gravarDados(aVeiculo, ArquivosTipo.VEICULO));

        System.out.println("\n------------------------------\n");
        System.out.println("\n-> LEITURA DE UM CLIENTE PF: ");
        System.out.println(segSeguro.lerDados("691.484.220-53", ArquivosTipo.CLIENTE_PF));
        System.out.println("\n-> LEITURA DE UM CLIENTE PJ: ");
        System.out.println(segSeguro.lerDados("27.743.073/0001-20", ArquivosTipo.CLIENTE_PJ));
        System.out.println("\n-> LEITURA DE UM CONDUTOR: ");
        System.out.println(segSeguro.lerDados("691.484.220-53", ArquivosTipo.CONDUTOR));
        System.out.println("\n-> LEITURA DE UMA FROTA: ");
        System.out.println(segSeguro.lerDados("001", ArquivosTipo.FROTA));
        System.out.println("\n-> LEITURA DE UM VEICULO: ");
        System.out.println(segSeguro.lerDados("UVW-3210", ArquivosTipo.VEICULO));

        //Menu Operações
        MenuOperacoes.MenuPrincipal();
    }
}

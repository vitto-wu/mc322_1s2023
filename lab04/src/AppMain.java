import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppMain {
    static DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Lê a partir do teclado os dados do cliente (PF ou PJ)
     * @return obejeto cliente
     */
    public static Cliente cadastroCliente(Scanner entrada) throws Exception{
        System.out.println("Cadastro do Cliente: \n[1] Sou uma pessoa física\n[2]  Represento uma empresa como pessoa jurídica");
        int tipoCliente = entrada.nextInt();
        System.out.println("Nome: ");
        String nome = entrada.nextLine();
        System.out.println("Endereço: ");
        String endereco = entrada.nextLine();
        switch (tipoCliente){
            case 1:
                System.out.println("Data de nascimento: ");
                String dataNascimento = entrada.next();
                System.out.println("CPF: ");
                String cpf = entrada.next();
                System.out.println("Gênero: ");
                String genero = entrada.nextLine();
                System.out.println("Educacao: ");
                String educacao = entrada.nextLine();
                System.out.println("Classe Econômica: ");
                String classeEconomica = entrada.nextLine();
                System.out.println("Data de licença: ");
                String dataLicenca = entrada.next();
                return new ClientePF(nome, endereco, cpf, genero, educacao, classeEconomica, LocalDate.parse(dataLicenca, formatoData), LocalDate.parse(dataNascimento, formatoData));
            case 2:
                System.out.println("CNPJ: ");
                String cnpj = entrada.next();
                System.out.println("Data de fundação: ");
                String dataFundacao = entrada.next();
                System.out.println("Quantidade de funcionários: ");
                int quantidadeFuncionarios = entrada.nextInt();
                return new ClientePJ(nome, endereco, cnpj, LocalDate.parse(dataFundacao, formatoData), quantidadeFuncionarios);
            default:
                return null;
        }
    }

    /**
     * Lê a partir do teclado os dados do vículo
     * @return obejeto veículo
     */
    public static Veiculo cadastroVeiculo(Scanner entrada) throws Exception{
        System.out.println("Placa: ");
        String placa = entrada.next();
        System.out.println("Marca: ");
        String marca = entrada.nextLine();
        System.out.println("Modelo: ");
        String modelo = entrada.nextLine();
        System.out.println("Ano de fabricação: ");
        int anoFabricacao = entrada.nextInt();

        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    /**
     * Lê a partir do teclado os dados da seguradora
     * @return obejeto seguradora
     */
    public static Seguradora cadastroSeguradora(Scanner entrada) throws Exception{
        System.out.println("Nome: ");
        String nomeSeguradora = entrada.nextLine();
        System.out.println("Telefone: ");
        String telefone = entrada.next();
        System.out.println("Email: ");
        String email = entrada.next();
        System.out.println("Endereço: ");
        String endereco = entrada.nextLine();

        return new Seguradora(nomeSeguradora, telefone, email, endereco);
    }

    /**
     * Menu Interativo responsável pelo Cadastro
     */
    public static void menuCadastro(Scanner entrada) throws Exception{
        while(true) {
            String nomeSeguradora;
            boolean operacaoConcluida = false;

            System.out.println("\n............ CADASTRAR ............\n");
            System.out.println("[1] CADASTRAR CLIENTE PF/PJ");
            System.out.println("[2] CADASTRAR VEICULO");
            System.out.println("[3] CADASTRAR SEGURADORA");
            System.out.println("[0] VOLTAR");
            System.out.println("\n--------------------------------\n");
            System.out.println("Digite o número da operação desejada: ");
            int operacao = entrada.nextInt();
            entrada.nextLine();
            System.out.println("\n............\n");
            switch (operacao) {
                case 1:
                    System.out.println("Nome da Seguradora: ");
                    nomeSeguradora = entrada.nextLine();
                    if (Validacao.seguradoraCadastrada(nomeSeguradora)) {
                        Cliente cliente = cadastroCliente(entrada);
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastro(nomeSeguradora, cliente);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                case 2:
                    System.out.println("Nome da Seguradora: ");
                    nomeSeguradora = entrada.next();
                    if (Validacao.seguradoraCadastrada(nomeSeguradora)) {
                        Veiculo veiculo = cadastroVeiculo(entrada);
                        System.out.println("Documento do cliente: ");
                        String clienteDocumento = entrada.next();
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastro(nomeSeguradora, clienteDocumento, veiculo);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                case 3:
                    Seguradora seguradora = cadastroSeguradora(entrada);
                    if(MenuOperacoes.CADASTRAR.cadastro(seguradora)){
                        System.out.println("A seguradora inserida já foi cadastrada!");
                    } else {
                        operacaoConcluida = true;
                    }
                case 0:
                    break;
            }

            if (operacaoConcluida) {
                System.out.println("\nOperação concluída com sucesso!");
            } else {
                System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
            }
        }
    }

     /**
     * Menu Interativo responsável pela listagem
     */
    public static void menuListagem(Scanner entrada) {
        while(true) {
            System.out.println("\n............ LISTAR ............\n");
            System.out.println("[1] LISTAR CLIENTE (PF/PJ) POR SEGURADORA");
            System.out.println("[2] LISTAR SINISTROS POR SEGURADORA");
            System.out.println("[3] LISTAR SINISTRO POR CLIENTE");
            System.out.println("[4] LISTAR VEICULO POR CLIENTE");
            System.out.println("[5] LISTAR VEICULO POR SEGURADORA");
            System.out.println("[0] VOLTAR");
            System.out.println("\n------------------------------------------\n");
            System.out.println("Digite o número da operação desejada: ");
            int operacao = entrada.nextInt();
            entrada.nextLine();
            System.out.println("\n........................\n");

            if(operacao == 0){
                break;
            } else {
                System.out.println("Nome da Seguradora: ");
                String nomeSeguradora = entrada.nextLine();

                if (!Validacao.seguradoraCadastrada(nomeSeguradora)) {
                    System.out.println("A seguradora inserida não está cadastrada!");
                } else {
                    switch (operacao) {
                        case 1:
                            System.out.println(MenuOperacoes.LISTAR.listar(nomeSeguradora, 0));
                        case 2:
                            System.out.println(MenuOperacoes.LISTAR.listar(nomeSeguradora, 1));
                        case 3:
                            System.out.println("Documento do cliente: ");
                            String clienteDocumento = entrada.next();
                            System.out.println(MenuOperacoes.LISTAR.listar(nomeSeguradora, clienteDocumento, 0));
                        case 4:
                            System.out.println("Documento do cliente: ");
                            clienteDocumento = entrada.next();
                            System.out.println(MenuOperacoes.LISTAR.listar(nomeSeguradora, clienteDocumento, 1));
                        case 5:
                            System.out.println(MenuOperacoes.LISTAR.listar(nomeSeguradora, 2));
                    }
                }
            }
        }
    }

    /**
     * Menu Interativo responsável pela Exclusão
    */
    public static void menuExclusão(Scanner entrada) {
        while(true) {
            boolean operacaoConcluida = false;
            System.out.println("\n............ EXCLUIR ............\n");
            System.out.println("[1] EXCLUIR CLIENTE");
            System.out.println("[2] EXCLUIR VEICULO");
            System.out.println("[3] EXCLUIR SINISTRO");
            System.out.println("[0] VOLTAR");
            System.out.println("\n------------------------------------------------\n");
            System.out.println("Digite a operação que deseja realizar: ");
            int operacao = entrada.nextInt();
            entrada.nextLine();
            System.out.println("\n............\n");

            if(operacao == 0){
                return;
            } else {
                System.out.println("Nome da Seguradora: ");
                String nomeSeguradora = entrada.nextLine();

                if (!Validacao.seguradoraCadastrada(nomeSeguradora)) {
                    System.out.println("A seguradora inserida não está cadastrada!");
                } else {
                    switch (operacao) {
                        case 1:
                            System.out.println("Documento do cliente: ");
                            String clienteDocumento = entrada.next();
                            operacaoConcluida = MenuOperacoes.EXCLUIR.remocao(nomeSeguradora, clienteDocumento);
                            break;
                        case 2:
                            System.out.println("Documento do cliente: ");
                            clienteDocumento = entrada.next();
                            System.out.println("Placa do veículo: ");
                            String placa = entrada.next();
                            operacaoConcluida = MenuOperacoes.EXCLUIR.remocao(nomeSeguradora, clienteDocumento, placa);
                            break;
                        case 3:
                            System.out.println("ID do sinistro: ");
                            int idSinistro = entrada.nextInt();
                            operacaoConcluida = MenuOperacoes.EXCLUIR.remocao(nomeSeguradora, idSinistro);
                            break;
                    }
                }
            }

            if (operacaoConcluida) {
                System.out.println("\nOperação concluída com sucesso!");
            } else {
                System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
            }
        }
    }

    /**
     * Menu Interativo Principal
     */
    public static void MenuOperacoesInterativo() throws Exception {
        Scanner entrada = new Scanner(System.in);
        while (true) {
            String nomeSeguradora;
            boolean operacaoConcluida = false;

            System.out.println("\n............ MENU PRINCIPAL ............\n");
            System.out.println("[1] CADASTRAR");
            System.out.println("[2] LISTAR");
            System.out.println("[3] EXCLUIR");
            System.out.println("[4] GERAR SINISTRO");
            System.out.println("[5] TRANSFERIR SEGURO");
            System.out.println("[6] CALCULAR RECEITA DA SEGURADORA");
            System.out.println("[0] SAIR");
            System.out.println("\n------------------------------------------------\n");
            System.out.println("Digite o número da operação desejada: ");
            int operacao = entrada.nextInt();
            System.out.println("\n........................\n");

            switch (operacao) {
                case 1:
                    menuCadastro(entrada);
                case 2:
                    menuListagem(entrada);
                case 3:
                    menuExclusão(entrada);
                case 4:
                    System.out.println("Nome da Seguradora: ");
                    nomeSeguradora = entrada.nextLine();
                    if (Validacao.seguradoraCadastrada(nomeSeguradora)) {
                        System.out.println("Data do sinistro: ");
                        String data = entrada.next();
                        System.out.println("Endereço: ");
                        String endereco = entrada.nextLine();
                        System.out.println("Placa do veículo: ");
                        String placa = entrada.nextLine();
                        System.out.println("Documento do cliente: ");
                        String documentoCliente = entrada.nextLine();
                        operacaoConcluida = MenuOperacoes.GERAR_SINISTRO.gerarSinistro(data, endereco, nomeSeguradora, placa, documentoCliente);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    
                    if (operacaoConcluida) {
                        System.out.println("\nOperação concluída com sucesso!");
                    } else {
                        System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
                    }
                case 5:
                    System.out.println("Nome da Seguradora: ");
                    nomeSeguradora = entrada.nextLine();
                    if (Validacao.seguradoraCadastrada(nomeSeguradora)) {
                        System.out.println("Documento do remetente: ");
                        String clienteRemetenteDocumento = entrada.next();
                        System.out.println("Documento do destinatário: ");
                        String clienteDestinatarioDocumento = entrada.next();
                        operacaoConcluida = MenuOperacoes.TRASNFERIR_SEGURO.transferirSeguro(nomeSeguradora, clienteRemetenteDocumento, clienteDestinatarioDocumento);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }

                    if (operacaoConcluida) {
                        System.out.println("\nOperação concluída com sucesso!");
                    } else {
                        System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
                    }
                case 6:
                    System.out.println("Nome da Seguradora: ");
                    nomeSeguradora = entrada.nextLine();
                    if (Validacao.seguradoraCadastrada(nomeSeguradora)) {
                        System.out.println("Receita da seguradora" + nomeSeguradora + ": " + MenuOperacoes.CALCULAR_RECEITA_SEGURADORA.receita(nomeSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                case 0:
                    entrada.close();
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println("\n............Instanciar Clientes e Seguradora.........\n");

        Veiculo v1 = new Veiculo("ABC1D23", "BMW", "X5", 2023);

        Veiculo v2 = new Veiculo("DCB3A21", "Audi", "A4 Sedan", 2023);

        ClientePF pessoaFisica = new ClientePF("PF", "Rua Euclides", "604.229.621-39", "masculino", "Ensino Médio Completo", "Alta", LocalDate.of(2023, 1, 1), LocalDate.of(2000, 1, 1));

        ClientePJ pessoaJuridica = new ClientePJ("PJ", "Rua Pitagoras", "11.222.333/0001-81", LocalDate.parse("02/02/2020", formatoData), 100);

        Seguradora seguradora = new Seguradora("seguradora", "(12) 3456-7890", "seguradora@email.com", "Rua Descartes");


        System.out.println(v1);
        System.out.println("\n--*--\n");
        System.out.println(v2);
        System.out.println("\n--*--\n");
        System.out.println(pessoaFisica);
        System.out.println("\n--*--\n");
        System.out.println(pessoaJuridica);
        System.out.println("\n--*--\n");
        System.out.println(seguradora);

        System.out.println("\n............ Adicionar veículos ..............\n");
        pessoaFisica.adicionarVeiculo(v1);
        pessoaJuridica.adicionarVeiculo(v2);

        System.out.println("Veículos pessoaFisica: \n" + pessoaFisica.getListaVeiculos());
        System.out.println("\n--*--\n");
        System.out.println("Veículos pessoaJuridica: \n" + pessoaJuridica.getListaVeiculos());

        System.out.println("\n............ Cadastrar clientes ..............\n");
        seguradora.cadastrarCliente(pessoaFisica);
        seguradora.cadastrarCliente(pessoaJuridica);

        System.out.println("- Lista de clientes PF: \n" + seguradora.listarClientes("PF"));
        System.out.println("\n--*--\n");
        System.out.println("- Lista de clientes PJ: \n" + seguradora.listarClientes("PJ"));

        System.out.println("\n............ Gerar sinistros ..............\n");
        seguradora.gerarSinistro(LocalDate.parse("20/04/2023", formatoData), "Rua Pitágoras", seguradora, v2, pessoaJuridica);
        seguradora.gerarSinistro(LocalDate.parse("10/02/2022", formatoData), "Rua Augusta", seguradora, v1, pessoaFisica);

        System.out.println("Sinistros gerados: \n" + seguradora.listarSinistros().get(0) + "\n--*--\n" + seguradora.listarSinistros().get(1));

        System.out.println("\n...... Chamar os métodos da classe Seguradora ......\n");
        
        System.out.println("-Lista de Clientes PF: \n" + seguradora.listarClientes("PF"));
        System.out.println("\n--*--\n");
        System.out.println("-Vizualizar sinistro: \n" + seguradora.visulizarSinistro("11.222.333/0001-81"));
        System.out.println("\n--*--\n");
        System.out.println("-Lista de Sinistros: \n" + seguradora.listarSinistros());
        System.out.println("\n--*--\n");
        System.out.println("-Receita da seguradora: \n" + seguradora.calcularReceita());

        System.out.println("\n...... Atualizar o atributo valorSeguro de cada cliente ......\n");
        for(Cliente c : seguradora.getListaClientes()){
            c.setValorSeguro(seguradora.calcularPrecoSeguroCliente(c));
            System.out.println("-Preco Seguro do Cliente " + c.getIdentificacao() + ": \n" + c.getValorSeguro());
        }

        System.out.println("\n...... Receita total da seguradora ......\n");
        System.out.println("-Receita da seguradora: \n" + seguradora.calcularReceita());

        MenuOperacoesInterativo();
    }
}

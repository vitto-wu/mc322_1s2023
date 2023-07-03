import java.util.Scanner;

public class MenuOperacoes {
    //Menu Principal de Operações
    public static void MenuPrincipal(){
        Scanner entrada = new Scanner(System.in);
        while (true) {
            System.out.println("\n............ MENU PRINCIPAL ............\n");
            System.out.println("[1] CADASTRAR");
            System.out.println("[2] LISTAR");
            System.out.println("[3] EXCLUIR");
            System.out.println("[4] GERAR SINISTRO");
            System.out.println("[5] CALCULAR RECEITA DA SEGURADORA");
            System.out.println("[0] SAIR");
            System.out.println("\n........................................\n");
            System.out.println("Digite o número da operaçao desejada: ");
            int operacao = entrada.nextInt();
            entrada.nextLine();

            switch (operacao) {
                case 1:
                    menuCadastro(entrada);
                    break;
                case 2:
                    menuListagem(entrada);
                    break;
                case 3:
                    menuExclusao(entrada);
                    break;
                case 4:
                    gerarSinistro(entrada);
                    break;
                case 5:
                    calcularReceita(entrada);
                    break;
                case 0:
                    entrada.close();
                default:
                    System.out.println("\nENTRADA INVALIDA, TENTE NOVAMENTE!!");
                    break;
            }
        }
    }

    /**
     * Menu controle dos cadastro
     * @param {Scanner} entrada
     */
    private static void menuCadastro(Scanner entrada){
        while(true) {
            System.out.println("\n............ CADASTRAR ............\n");
            System.out.println("[1] CADASTRAR  CLIENTE PF/PJ");
            System.out.println("[2] CADASTRAR  VEICULO");
            System.out.println("[3] CADASTRAR  FROTA");
            System.out.println("[4] CADASTRAR  SEGURADORA");
            System.out.println("[5] CADASTRAR  CONDUTOR");
            System.out.println("[6] CADASTRAR  SEGURO");
            System.out.println("[0] VOLTAR");
            System.out.println("\n--------------------------------\n");
            System.out.println("Digite o número da operaçao desejada: ");
            int operacao = entrada.nextInt();
            
            if(operacao == 0){
                return;
            }

            String nomeSeguradora = entrada.nextLine();
            System.out.println("Nome da Seguradora: ");
            nomeSeguradora = entrada.nextLine();

            if(operacao != 4 && !Validacao.seguradoraCadastrada(nomeSeguradora)){
                System.out.println("A seguradora inserida nao está cadastrada!");
            } else {
                switch (operacao) {
                    case 1:
                        if(Operacoes.menuCadastroCLiente(entrada, nomeSeguradora)){
                            System.out.println("\nCliente cadastrado com sucesso!");
                        } else {
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        }
                        break;
                    case 2:
                        if(Operacoes.menuCadastroVeiculo(entrada, nomeSeguradora)){
                            System.out.println("\nVeículo cadastrado com sucesso!");
                        } else {
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        }
                        break;
                    case 3:
                        if(Operacoes.menuCadastroFrota(entrada, nomeSeguradora)){
                            System.out.println("\nFrota cadastrado com sucesso!");
                        } else {
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        }
                        break;
                    case 4:
                        if(Operacoes.menuCadastroSeguradora(entrada, nomeSeguradora)){
                            System.out.println("\nSeguradora cadastrado com sucesso!");
                        } else {
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        }
                        break;
                    case 5:
                        if(Operacoes.menuCadastroCondutor(entrada, nomeSeguradora)){
                            System.out.println("\nCondutor cadastrado com sucesso!");
                        } else {
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        }
                        break;
                    case 6:
                        if(Operacoes.menuCadastroSeguro(entrada, nomeSeguradora)){
                            System.out.println("\nSeguro cadastrado com sucesso!");
                        } else {
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        }
                        break;
                    default:
                        System.out.println("\nENTRADA INVALIDA, TENTE NOVAMENTE!!");
                        break;
                }
            }
        }
    }

    /**
     * Menu controle da listagem
     * @param {Scanner} entrada
     */
    private static void menuListagem(Scanner entrada){
        while(true) {
            System.out.println("\n............ LISTAR ............\n");
            System.out.println("[1] LISTAR CLIENTE PF/PJ POR SEGURADORA");
            System.out.println("[2] LISTAR VEICULO");
            System.out.println("[3] LISTAR FROTA");
            System.out.println("[4] LISTAR SEGURO POR CLIENTE");
            System.out.println("[5] LISTAR SINISTRO POR CLIENTE");
            System.out.println("[0] VOLTAR");
            System.out.println("\n--------------------------------\n");
            System.out.println("Digite o número da operaçao desejada: ");
            int operacao = entrada.nextInt();
            
            if(operacao == 0){
                return;
            }

            String nomeSeguradora = entrada.nextLine();
            System.out.println("Nome da Seguradora: ");
            nomeSeguradora = entrada.nextLine();

            if(!Validacao.seguradoraCadastrada(nomeSeguradora)){
                System.out.println("A seguradora inserida nao está cadastrada!");
            } else {
                switch (operacao) {
                    case 1:
                        System.out.println(Operacoes.menuListarCliente(entrada, nomeSeguradora));
                        break;
                    case 2:
                        System.out.println(Operacoes.menuListarVeiculo(entrada, nomeSeguradora));
                        break;
                    case 3:
                        System.out.println(Operacoes.menuListarFrota(entrada, nomeSeguradora));
                        break;
                    case 4:
                        System.out.println(Operacoes.menuListarSeguro(entrada, nomeSeguradora));
                        break;
                    case 5:
                        System.out.println(Operacoes.menuListarSinistro(entrada, nomeSeguradora));
                        break;
                    default:
                        System.out.println("\nENTRADA INVALIDA, TENTE NOVAMENTE!!");
                        break;
                }
            }
        }
    }

    /**
     * Menu controle responsável pela remoção
     * @param {Scanner} entrada
     */
    private static void menuExclusao(Scanner entrada){
        while(true) {
            System.out.println("\n............ EXCLUIR ............\n");
            System.out.println("[1] EXCLUIR  CLIENTE");
            System.out.println("[2] EXCLUIR  VEICULO");
            System.out.println("[3] EXCLUIR  FROTA");
            System.out.println("[4] CANCELAR SEGURO");
            System.out.println("[5] REMOVER  CONDUTOR DO SEGURO");
            System.out.println("[6] EXCLUIR  SINISTRO");
            System.out.println("[0] VOLTAR");
            System.out.println("\n--------------------------------\n");
            System.out.println("Digite o número da operaçao desejada: ");
            int operacao = entrada.nextInt();
            
            if(operacao == 0){
                return;
            }

            String nomeSeguradora = entrada.nextLine();
            System.out.println("Nome da Seguradora: ");
            nomeSeguradora = entrada.nextLine();

            if(!Validacao.seguradoraCadastrada(nomeSeguradora)){
                System.out.println("A seguradora inserida nao está cadastrada!");
            } else {
                switch (operacao) {
                    case 1:
                        if(Operacoes.menuExcluirCliente(entrada, nomeSeguradora)){
                            System.out.println("CLiente removido com sucesso!");
                        }else{
                            System.out.println("\nErro! Cliente nao cadastrado na seguradora!");
                        };
                        break;
                    case 2:
                        if(Operacoes.menuExcluirVeiculo(entrada, nomeSeguradora)){
                            System.out.println("Veiculo removido com sucesso!");
                        }else{
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        };
                        break;
                    case 3:
                        if(Operacoes.menuExcluirFrota(entrada, nomeSeguradora)){
                            System.out.println("Frota removido com sucesso!");
                        }else{
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        };
                        break;
                    case 4:
                        if(Operacoes.menuExcluirSeguro(entrada, nomeSeguradora)){
                            System.out.println("Seguro removido com sucesso!");
                        }else{
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        };
                        break;
                    case 5:
                        if(Operacoes.menuExcluirCondutor(entrada, nomeSeguradora)){
                            System.out.println("Condutor removido com sucesso!");
                        }else{
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        };
                        break;
                    case 6:
                        if(Operacoes.menuExcluirSinistro(entrada, nomeSeguradora)){
                            System.out.println("Sinistro removido com sucesso!");
                        }else{
                            System.out.println("\nErro! A operaçao nao foi possível de ser concluída! Tente novamente!");
                        };
                        break;
                    default:
                        System.out.println("\nENTRADA INVALIDA, TENTE NOVAMENTE!!");
                        break;
                }
            }
        }
    }

    /**
     * Gera um sinistro a partir dos dados fornecidos pelo usuário na entrada
     * @param {Scanner} entrada
     */
    private static void gerarSinistro(Scanner entrada){
        System.out.println("Nome da Seguradora: ");
        String nomeSeguradora = entrada.nextLine();
        if (Validacao.seguradoraCadastrada(nomeSeguradora)) {
            System.out.println("Data do sinistro: ");
            String data = entrada.next();
            System.out.println("Endereço: ");
            String endereco = entrada.nextLine();
            System.out.println("Cpf do condutor: ");
            String cpfCondutor = entrada.nextLine();
            System.out.println("Id do seguro: ");
            int idSeguro = entrada.nextInt();
            entrada.nextLine();
            boolean operacaoConcluida = Operacoes.gerarSinistro(data, endereco, cpfCondutor , nomeSeguradora, idSeguro);

            if (operacaoConcluida) {
                System.out.println("\nSeguro foi registrado com sucesso!");
            } else {
                System.out.println("\nErro! Nao foi possível gerar um novo sinistro, tente novamente!");
            }
        } else {
            System.out.println("A seguradora inserida nao está cadastrada, tente novamente!");
        }
    }

    /**
     * Calcula a receita de uma seguradora a partir da nome fornecido pelo usuário
     * @param {Scanner} entrada
     */
    private static void calcularReceita(Scanner entrada){
        System.out.println("Nome da Seguradora: ");
        String nomeSeguradora = entrada.nextLine();
        if (Validacao.seguradoraCadastrada(nomeSeguradora)) {
            System.out.println("Receita da Seguradora " + nomeSeguradora + " : R$" + Operacoes.calcularReceita(nomeSeguradora));
        } else {
            System.out.println("A seguradora inserida nao está cadastrada, tente novamente!");
        }
    }
}

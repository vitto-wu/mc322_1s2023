import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Operacoes {
    //formataçao das datas
    static DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Retorna o objeto seguradora a partir do nome da seguradora
     * @param {String} nomeSeguradora
     * @return {Seguradora} objeto seguradora
     */
    private static Seguradora objetoSeguradora(String nomeSeguradora){
        for(Seguradora s : BancoDados.listaSeguradoras){
            if(s.getNome().equals(nomeSeguradora)){
                return s;
            }
        }
        return null;
    }

    /**
     * Retorna o objeto cliente a partir da identidade do cliente e do objeto seguradora
     * @param {String}      documentoCliente - documento do cliente
     * @param {Seguradora}  seguradora - Objeto seguradora
     * @return {Cliente} objeto cliente
     */
    private static Cliente objetoCliente(String documentoCliente, Seguradora seguradora){
        for(Cliente c : seguradora.getListaClientes()){
            if(c.getDocumento().equals(documentoCliente)){
                return c;
            }
        }
        return null;
    }

    /**
     * Retorna o objeto condutor a partir do cpf do condutor e do objeto seguro
     * @param {String}  cpfCondutor  - Cpf do condutor
     * @param {Seguro}  seguro - Objeto seguro
     * @return {Condutor} objeto condutor
     */
    private static Condutor objetoCondutor(String cpfCondutor, Seguro seguro){
        for(Condutor c : seguro.getListaCondutores()){
            if(c.getCpf().equals(cpfCondutor)){
                return c;
            }
        }
        return null;
    }

    /**
     * Retorna o objeto seguro a partir do id do seguro e do objeto seguradora
     * @param {int}     idSeguro  - Id do seguro
     * @param {Seguro}  seguro - Objeto seguradora
     * @return {seguro} objeto seguro
     */
    private static Seguro objetoSeguro(int idSeguro, Seguradora seguradora){
        for(Seguro s : seguradora.getListaSeguros()){
            if(s.getId() == idSeguro){
                return s;
            }
        }
        return null;
    }

    /**
     * Retorna o objeto veiculo a partir da placa do veiculo e do objeto cliente
     * @param {String}      placa  - Placa do veiculo
     * @param {ClientePF}   clientePF
     * @return {Veiculo} objeto veiculo
     */
    private static Veiculo objetoVeiculo(String placa, ClientePF clientePF){
        for(Veiculo v : clientePF.getListaVeiculos()){
            if(v.getPlaca().equals(placa)){
                return v;
            }
        }
        return null;
    }

    /**
     * Retorna o objeto frota a partir da code do frota e do objeto cliente
     * @param {String}     code  - code do frota
     * @param {ClientePJ}  clientePJ
     * @return {Frota} objeto frota
     */
    private static Frota objetoFrota(String code, ClientePJ clientePJ){
        for(Frota f : clientePJ.getListaFrota()){
            if(f.getCode().equals(code)){
                return f;
            }
        }
        return null;
    }
    
    /**
     * gera um novo sinistro
     * @param {String} data
     * @param {String} endereco
     * @param {String} cpfCondutor
     * @param {String} nomeSeguradora
     * @param {int}    idSeguro
     * @return {boolean} true caso consiga gerar o sinistro e false caso não
     */
    public static boolean gerarSinistro(String data, String endereco, String cpfCondutor ,String nomeSeguradora, int idSeguro){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);
        Seguro seguro = objetoSeguro(idSeguro, seguradora);
        Condutor condutor = objetoCondutor(cpfCondutor, seguro);

        return seguro.gerarSinistro(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")), endereco, condutor);
    }

    /**
     * Calcula a receita da seguradora
     * @param {String} nomeSeguradora
     * @return {double} receita da seguradora
     */
    public static double calcularReceita(String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        return seguradora.calcularReceita();
    }

    /**
     * Menu para o cadastro de clientes
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso a operação seja bem sucedida e false caso não
     */
    public static boolean menuCadastroCLiente(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Cadastro do Cliente: \n" + 
                           "[1] Sou uma pessoa física\n" + 
                           "[2] Represento uma empresa como pessoa jurídica");
        int tipoCliente = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Nome: ");
        String nome = entrada.nextLine();
        System.out.println("Telefone: ");
        String telefone = entrada.nextLine();
        System.out.println("Endereço: ");
        String endereco = entrada.nextLine();
        System.out.println("E-mail: ");
        String email = entrada.nextLine();

        switch (tipoCliente){
            case 1:
                System.out.println("Data de nascimento: ");
                String dataNascimento = entrada.nextLine();
                System.out.println("CPF: ");
                String cpf = entrada.nextLine();
                System.out.println("Gênero: ");
                String genero = entrada.nextLine();
                System.out.println("Educacao: ");
                String educacao = entrada.nextLine();
                ClientePF clientePF = new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, LocalDate.parse(dataNascimento, formatoData));
                return seguradora.cadastrarCliente(clientePF);
            case 2:
                System.out.println("CNPJ: ");
                String cnpj = entrada.nextLine();
                System.out.println("Data de fundaçao: ");
                String dataFundacao = entrada.nextLine();
                System.out.println("Quantidade de funcionários: ");
                int quantidadeFuncionarios = entrada.nextInt();
                ClientePJ clientePJ = new ClientePJ(nome, telefone, endereco, email, cnpj, LocalDate.parse(dataFundacao, formatoData) , quantidadeFuncionarios);
                return seguradora.cadastrarCliente(clientePJ);
            default:
                return false;
        }
    }

    /**
     * Menu para o cadastro de veiculos na lista de veiculos caso pessoa física e na frota caso pessoa jurídica
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso a operação seja bem sucedida e false caso não
     */
    public static boolean menuCadastroVeiculo(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Tipo de Cliente: \n" + 
                           "[1] Sou uma pessoa física\n" + 
                           "[2] Represento uma empresa como pessoa jurídica");
        int tipoCliente = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Documento do Cliente (CPF / CNPJ): ");
        String documentoCliente = entrada.nextLine();
        Cliente cliente = objetoCliente(documentoCliente, seguradora);
        if(cliente == null){
            System.out.println("\nErro! Cliente nao cadastrado na seguradora!");
            return false;
        }

        System.out.println("Placa do veiculo: ");
        String nome = entrada.nextLine();
        System.out.println("Marca: ");
        String marca = entrada.nextLine();
        System.out.println("Modelo: ");
        String modelo = entrada.nextLine();
        System.out.println("Ano de fabricaçao: ");
        int anoFabricacao = entrada.nextInt();
        entrada.nextLine();
        
        Veiculo veiculo = new Veiculo(nome, marca, modelo, anoFabricacao);
        switch(tipoCliente){
            case 1:
                ClientePF clientePF = (ClientePF) cliente;
                return clientePF.cadastrarVeiculo(veiculo);
            case 2:
                System.out.println("Code da frota: ");
                String codeFrota = entrada.nextLine();

                ClientePJ clientePJ = (ClientePJ) cliente;
                Frota frota = objetoFrota(codeFrota, clientePJ);
                return clientePJ.atualizarFrota(veiculo, frota);
            default:
                return false;
        }
    }

    /**
     * Menu para o cadastro de uma nova frota
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso a operação seja bem sucedida e false caso não
     */
    public static boolean menuCadastroFrota(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("CNPJ do Cliente: ");
        String documentoCliente = entrada.nextLine();
        ClientePJ clientePJ = (ClientePJ) objetoCliente(documentoCliente, seguradora);
        if(clientePJ == null){
            System.out.println("\nErro! Cliente nao cadastrado na seguradora!");
            return false;
        }
        
        Frota frota = new Frota();
        System.out.println("Codigo da frota: " + frota.gerarCode());
        return clientePJ.cadastrarFrota(frota);
    }

    /**
     * Menu para o cadastro de uma nova seguradora
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso a operação seja bem sucedida e false caso não
     */
    public static boolean menuCadastroSeguradora(Scanner entrada, String nomeSeguradora){
        System.out.println("CNPJ da seguradora: ");
        String cnpj = entrada.nextLine();
        System.out.println("Telefone: ");
        String telefone = entrada.nextLine();
        System.out.println("Endereço: ");
        String endereco = entrada.nextLine();
        System.out.println("E-mail: ");
        String email = entrada.nextLine();

        Seguradora seguradora = new Seguradora(cnpj, nomeSeguradora, telefone, email, endereco);
        return BancoDados.listaSeguradoras.add(seguradora);
    }

    /**
     * Menu para o cadastro de condutor
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso a operação seja bem sucedida e false caso não
     */
    public static boolean menuCadastroCondutor(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Id do seguro: ");
        int idSeguro = entrada.nextInt();
        entrada.nextLine();
        Seguro seguro = objetoSeguro(idSeguro, seguradora);
        if(seguro == null){
            System.out.println("Seguro nao cadastrado na seguradora");
            return false;
        }

        System.out.println("Nome: ");
        String nome = entrada.nextLine();
        System.out.println("CPF: ");
        String cpf = entrada.nextLine();
        System.out.println("Telefone: ");
        String telefone = entrada.nextLine();
        System.out.println("Endereço: ");
        String endereco = entrada.nextLine();
        System.out.println("E-mail: ");
        String email = entrada.nextLine();
        System.out.println("Data de nascimento: ");
        String dataNascimento = entrada.nextLine();

              
        Condutor condutor = new Condutor(nome, cpf, telefone, endereco, email, LocalDate.parse(dataNascimento, formatoData));
        return seguro.autorizarCondutor(condutor);
    }

    /**
     * Menu para o cadastro de um novo seguro
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso a operação seja bem sucedida e false caso não
     */
    public static boolean menuCadastroSeguro(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);
        boolean cadastrado = false;

        System.out.println("Tipo de Cliente: \n" + 
                           "[1] Sou uma pessoa física\n" + 
                           "[2] Represento uma empresa como pessoa jurídica");
        int tipoCliente = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Documento do cliente (CPF / CNPJ): ");
        String documentoCliente = entrada.nextLine();
        Cliente cliente = objetoCliente(documentoCliente, seguradora);
        if(cliente == null){
            System.out.println("\nErro! Cliente nao cadastrado na seguradora!");
            return false;
        }

        System.out.println("Data Inicial: ");
        String dataInicio = entrada.nextLine();
        System.out.println("Data fim: ");
        String dataFim = entrada.nextLine();

        switch (tipoCliente){
            case 1:
                ClientePF clientePF = (ClientePF) cliente;
                System.out.println("Placa do Veiculo: ");
                String placa = entrada.nextLine();
                Veiculo veiculo = objetoVeiculo(placa, clientePF);

                cadastrado = seguradora.gerarSeguro(LocalDate.parse(dataInicio, formatoData), LocalDate.parse(dataFim, formatoData), veiculo, clientePF);
                break;
            case 2:
                ClientePJ clientePJ = (ClientePJ) cliente;
                System.out.println("Code da frota: ");
                String code = entrada.nextLine();
                Frota frota = objetoFrota(code, clientePJ);

                cadastrado = seguradora.gerarSeguro(LocalDate.parse(dataInicio, formatoData), LocalDate.parse(dataFim, formatoData), frota, clientePJ);
                break;
        }

        System.out.println("Id do seguro: " + seguradora.getListaSeguros().get(seguradora.getListaClientes().size() - 1).getId());
        return cadastrado;
    }

    /**
     * Menu para o listagem de clientes
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {String} lista dos clientes
     */
    public static String menuListarCliente(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Tipo de Cliente: \n" + 
                           "[1] Clientes Pessoa Fisica\n" + 
                           "[2] Clientes Pessoas Juridica");
        int tipoCliente = entrada.nextInt();
        entrada.nextLine();

        switch (tipoCliente){
            case 1: 
                return "Clientes PF: " + seguradora.listarClientes("PF");
            case 2:
                return "Clientes PJ: " +seguradora.listarClientes("PJ");
        }
        return "Erro! Tente novamente!";
    }

    /**
     * Menu para o listagem de veiculos
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {String} lista dos veiculos
     */
    public static String menuListarVeiculo(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Tipo de Cliente: \n" + 
                           "[1] Clientes Pessoa Fisica\n" + 
                           "[2] Clientes Pessoas Juridica");
        int tipoCliente = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Documento do cliente (CPF / CNPJ): ");
        String documentoCliente = entrada.nextLine();
        Cliente cliente = objetoCliente(documentoCliente, seguradora);
        if(cliente == null){
            return "\nErro! Cliente nao cadastrado na seguradora! Tente Novamente!";
        }

        switch (tipoCliente){
            case 1: 
                ClientePF clientePF = (ClientePF) cliente;
                return "Veículos do cliente: " + clientePF.getListaVeiculos().toString();
            case 2:
                System.out.println("Code da frota: ");
                String codeFrota = entrada.nextLine();

                ClientePJ clientePJ = (ClientePJ) cliente;
                Frota frota = objetoFrota(codeFrota, clientePJ);
                return "Veículos da frota: " + clientePJ.getVeiculosPorFrota(frota).toString();
        }
        return "Erro! Tente novamente!";
    }

    /**
     * Menu para o listagem de frotas
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {String} lista dos frotas
     */
    public static String menuListarFrota(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("CNPJ do cliente: ");
        String documentoCliente = entrada.nextLine();
        ClientePJ clientePJ = (ClientePJ) objetoCliente(documentoCliente, seguradora);
        if(clientePJ == null){
            return "\nErro! Cliente nao cadastrado na seguradora! Tente Novamente!";
        }
        
        return "Frotas do cliente: " +clientePJ.getListaFrota().toString();
    }

    /**
     * Menu para o listagem de seguros
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {String} lista dos seguros
     */
    public static String menuListarSeguro(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Documento do cliente (CPF / CNPJ): ");
        String documentoCliente = entrada.nextLine();
        Cliente cliente = objetoCliente(documentoCliente, seguradora);
        if(cliente == null){
            return "\nErro! Cliente nao cadastrado na seguradora! Tente Novamente!";
        }

        return "Seguros do cliente: " + seguradora.getSeguroPorCliente(cliente).toString();
    }

    /**
     * Menu para o listagem de sinistros
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {String} lista dos sinistros
     */
    public static String menuListarSinistro(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Documento do cliente (CPF / CNPJ): ");
        String documentoCliente = entrada.nextLine();
        Cliente cliente = objetoCliente(documentoCliente, seguradora);
        if(cliente == null){
            return "\nErro! Cliente nao cadastrado na seguradora! Tente Novamente!";
        }

        return "Sinistros do cliente: " + seguradora.getSinistrosPorCliente(cliente).toString();
    }

    /**
     * Menu exclusão de cliente
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso seja possivel remover o cliente e false caso não
     */
    public static boolean menuExcluirCliente(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Documento do cliente (CPF / CNPJ): ");
        String documentoCliente = entrada.nextLine();

        return seguradora.removerCliente(documentoCliente);
    }

    /**
     * Menu exclusão de veiculo
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso seja possivel remover o veiculo e false caso não
     */
    public static boolean menuExcluirVeiculo(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Tipo de Cliente: \n" + 
                           "[1] Clientes Pessoa Fisica\n" + 
                           "[2] Clientes Pessoas Juridica");
        int tipoCliente = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Documento do cliente (CPF / CNPJ): ");
        String documentoCliente = entrada.nextLine();
        Cliente cliente = objetoCliente(documentoCliente, seguradora);
        if(cliente == null){
            System.out.println("\nErro! Cliente nao cadastrado na seguradora! Tente Novamente!");
            return false;
        }

        System.out.println("Placa do veiculo: ");
        String placa = entrada.nextLine();
        switch (tipoCliente){
            case 1: 
                ClientePF clientePF = (ClientePF) cliente;
                clientePF.removerVeiculo(placa);
            case 2:
                System.out.println("Code da frota: ");
                String codeFrota = entrada.nextLine();

                ClientePJ clientePJ = (ClientePJ) cliente;
                Frota frota = objetoFrota(codeFrota, clientePJ);
                return clientePJ.atualizarFrota(null, frota);
        }
        return false;
    }

    /**
     * Menu exclusão de frota
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso seja possivel remover o frota e false caso não
     */
    public static boolean menuExcluirFrota(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("CNPJ do cliente: ");
        String documentoCliente = entrada.nextLine();
        ClientePJ clientePJ = (ClientePJ) objetoCliente(documentoCliente, seguradora);
        if(clientePJ == null){
            System.out.println("\nErro! Cliente nao cadastrado na seguradora! Tente Novamente!");
            return false;
        }
        System.out.println("Code da frota: ");
        String codeFrota = entrada.nextLine();
        Frota frota = objetoFrota(codeFrota, clientePJ);

        return clientePJ.atualizarFrota(frota);
    }

    /**
     * Menu cancelamento de seguro
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso seja possivel cancelar o seguro e false caso não
     */
    public static boolean menuExcluirSeguro(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Id do Seguro: ");
        int idSeguro = entrada.nextInt();
        entrada.nextLine();

        return seguradora.cancelarSeguro(idSeguro);
    }

    /**
     * Menu remoção de condutor do seguro
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso seja possivel remover o condutor e false caso não
     */
    public static boolean menuExcluirCondutor(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Id do Seguro: ");
        int idSeguro = entrada.nextInt();
        entrada.nextLine();
        Seguro seguro = objetoSeguro(idSeguro, seguradora);

        System.out.println("CPF do condutor: ");
        String cpfCondutor = entrada.nextLine();
        Condutor condutor = objetoCondutor(cpfCondutor, seguro);

        return seguro.desautorizarCondutor(condutor);
    }

    /**
     * Menu exclusão de sinistro
     * @param {Scanner} entrada - Entrada do usuário
     * @param {String}  nomeSeguradora
     * @return {boolean} true caso seja possivel excluir o sinistro e false caso não
     */
    public static boolean menuExcluirSinistro(Scanner entrada, String nomeSeguradora){
        Seguradora seguradora = objetoSeguradora(nomeSeguradora);

        System.out.println("Id do Seguro: ");
        int idSeguro = entrada.nextInt();
        entrada.nextLine();
        Seguro seguro = objetoSeguro(idSeguro, seguradora);

        System.out.println("CPF do condutor: ");
        String cpfCondutor = entrada.nextLine();
        Condutor condutor = objetoCondutor(cpfCondutor, seguro);

        System.out.println("Id do Sinistro: ");
        int idSinistro = entrada.nextInt();
        entrada.nextLine();

        return seguro.removerSinistro(idSinistro, condutor);
    }
}

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("\n............Instanciar Clientes e Seguradora.........\n");

        ClientePF pessoaFisica = new ClientePF("PF", "Rua Euclides", new ArrayList<Veiculo>(), "604.229.621-39", "masculino", "Ensino Médio Completo", "Alta", new Date(123,04,20), new Date(100, 01, 01));

        ClientePJ pessoaJuridica = new ClientePJ("PJ", "Rua Pitagoras", new ArrayList <Veiculo>(), "11.222.333/0001-81", new Date(123,04,20));

        Seguradora seguradora = new Seguradora("seguradora", "(11) 2345-6789", "seguradora@email.com", "Rua Descartes");

        System.out.println(pessoaFisica);
        System.out.println("--*--");
        System.out.println(pessoaJuridica);
        System.out.println("--*--");
        System.out.println(seguradora);


        System.out.println("\n..............Cadastro e remoção de cliente .........\n");

        seguradora.cadastrarCliente(pessoaFisica);
        System.out.println("- Lista de clientes após o cadastro: \n" + seguradora.listarClientes("PF"));
        seguradora.removerCliente(pessoaFisica.getNome());
        System.out.println("--*--");
        System.out.println("- Lista de clientes após a remoção: \n" + seguradora.listarClientes("PF"));


        System.out.println("\n.............. Testes de validação ...............\n");

        System.out.println("Teste de Validação de CPF:");
        System.out.println("'604.229.621-39': " + pessoaFisica.validarCPF("604.229.621-39"));
        System.out.println("'123.456.789-00': " + pessoaFisica.validarCPF("123.456.789-00"));
        System.out.println("'000.000.000-00': " + pessoaFisica.validarCPF("000.000.000-00"));
        System.out.println("--*--");
        System.out.println("Teste de Validação de CNPJ:");
        System.out.println("'11.222.333/0001-81': " + pessoaJuridica.validarCNPJ("11.222.333/0001-81"));
        System.out.println("'11.222.333/0001-45': " + pessoaJuridica.validarCNPJ("11.222.333/0001-45"));


        System.out.println("\n............ Adicionar veículos ..............\n");

        Veiculo vPF = new Veiculo("ABC1D23", "BMW", "X5", 2023);
        pessoaFisica.adicionarVeiculo(vPF);
        Veiculo vPJ = new Veiculo("DCB3A21", "Audi", "A4 Sedan", 2023);
        pessoaJuridica.adicionarVeiculo(vPJ);

        System.out.println("- Veículo da Pessoa Física: " + vPF);
        System.out.println("--*--");
        System.out.println("- Veículo da Pessoa Jurídica:" + vPJ);
        

        System.out.println("\n............ Cadastrar clientes ..............\n");

        seguradora.cadastrarCliente(pessoaFisica);
        seguradora.cadastrarCliente(pessoaJuridica);

        System.out.println("- Lista de clientes PF: \n" + seguradora.listarClientes("PF"));
        System.out.println("--*--");
        System.out.println("- Lista de clientes PJ: \n" + seguradora.listarClientes("PJ"));


        System.out.println("\n............ Gerar sinistro ..............\n");

        seguradora.gerarSinistro("20/04/2023", "Rua Pitágoras", seguradora, vPJ, pessoaJuridica);

        System.out.println("Novo sinistro: \n" + seguradora.listarSinistros());


        System.out.println("\n............ Chamar os métodos toString() ..............\n");

        System.out.println("- PF toString(): \n" + pessoaFisica.toString());
        System.out.println("--*--");
        System.out.println("- PJ toString(): \n" +pessoaFisica.toString());
        System.out.println("--*--");
        System.out.println("- Veículo toString(): \n" + vPF.toString());
        System.out.println("--*--");
        System.out.println("- Seguradora toString(): \n" + seguradora.toString());
        System.out.println("--*--");
        System.out.println("- Sinistro toString(): \n" + seguradora.getListaSinistros().get(0).toString());
        
        System.out.println("\n...... Chamar os métodos da classe Seguradora ......\n");
        
        System.out.println("-Lista de Clientes PF: \n" + seguradora.listarClientes("PF"));
        System.out.println("--*--");
        System.out.println("-Lista de Clientes PJ: \n" + seguradora.listarClientes("PJ"));
        System.out.println("--*--");
        System.out.println("-Lista de Sinistros: \n" + seguradora.listarSinistros());
        System.out.println("--*--");

        Scanner scan = new Scanner(System.in);
        System.out.println("\nDigite o nome do cliente para visualizar o sinistro: ");
        String entrada = scan.next();

        String mensagem = seguradora.visulizarSinistro(entrada) ? "" : "Cliente ou Sinistro não encontrado";
        System.out.println(mensagem);
    }
}

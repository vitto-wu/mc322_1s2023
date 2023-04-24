import java.util.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        /**
         *  Cadastrar e remover pelo menos 1 Cliente (ClientePF ou ClientePJ);
            • Adicionar pelo menos 1 Veiculo em cada Cliente instanciado;
            • Instanciar pelo menos 1 objeto de Seguradora;
            • Cadastrar pelo menos 2 clientes em Seguradora (sem remover), sendo 1 do tipo ClientePF e 1 do tipo
            ClientePJ;
            • Gerar pelo menos 1 Sinistro;
            • Chamar o m ́etodo toString() de cada classe;

            • Chamar os m ́etodos listarClientes (tipoCliente: String), visualizarSinistro(cliente: String) e listarSi-
            nistros() da classe Seguradora;

            • Implementar e chamar um método que faça leitura de dados usando a função System.In.
         */

        ClientePJ pessoaJuridica = new ClientePJ("PJ", "Rua Pitagoras", new ArrayList <Veiculo>(), "11.222.333/0001-81", new Date(123,04,20));

        ClientePF pessoaFisica = new ClientePF("pf", "Rua Euclides", new ArrayList<Veiculo>(), "604.229.621-39", "masculino", "Ensino Médio Completo", "A", new Date(123,04,20), new Date(100, 01, 01));

        Seguradora seguradora = new Seguradora("seguradora", "(11) 1111-1111", "seguradora@mail.com", "Rua Descartes", new ArrayList<Sinistro>(), new ArrayList<Cliente>());

        System.out.println("\n..............................................\n");
        System.out.println("Teste de Validação de CPF:");
        System.out.println("'604.229.621-39': " + pessoaFisica.validarCPF("604.229.621-39"));
        System.out.println("'000.000.000-00': " + pessoaFisica.validarCPF("000.000.000-00"));
        System.out.println("--*--");
        System.out.println("Teste de Validação de CNPJ:");
        System.out.println("'11.222.333/0001-81': " + pessoaJuridica.validarCNPJ("11.222.333/0001-81"));
        System.out.println("'11.222.333/0001-45': " + pessoaJuridica.validarCNPJ("11.222.333/0001-45"));
        seguradora.gerarSinistro(11111, new Date(123,04,20), "Rua Pitagoras", seguradora, , pessoaFisica)
    }
}

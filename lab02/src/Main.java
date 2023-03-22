public class Main {
    public static void main(String[] args) throws Exception {

        //Instanciação dos objetos
        Cliente cliente = new Cliente("cliente", "604.229.621-39", "15/02/1963", 60, "Rua Pitágoras");
        Sinistro sinistro = new Sinistro("16/03/2023", "Rua Pitágoras");
        Seguradora seguradora = new Seguradora("cliente", "3333-3333", "cliente@gmail.com", "Rua Pitágoras");
        Veiculo veiculo = new Veiculo("ABC1234", "marca", "modelo");


        System.out.println("\n..............................................\n");
        System.out.println("Teste das funções da classe 'Cliente'\n");
        System.out.println("Nome Inicial: " + cliente.getNome());
        cliente.setNome("novo nome");
        System.out.println("Nome Alterado: " + cliente.getNome());
        System.out.println("--*--");
        System.out.println("Teste de Validação de CPF:");
        System.out.println("'604.229.621-39': " + cliente.validarCPF("604.229.621-39"));
        System.out.println("'000.000.000-00': " + cliente.validarCPF("000.000.000-00"));
        System.out.println("--*--");
        System.out.println("Teste 'toString':");
        System.out.println(cliente.toString());
        System.out.println("\n..............................................\n");
        System.out.println("Teste das funções da classe 'Seguradora'\n");
        System.out.println("Telefone Inicial: " + seguradora.getTelefone());
        seguradora.setTelefone("2222-2222");
        System.out.println("Telefone Altarado: " + seguradora.getTelefone());
        System.out.println("\n..............................................\n");
        System.out.println("Teste das funções da classe 'Veículo'\n");
        System.out.println("Placa Inicial: " + veiculo.getPlaca());
        veiculo.setPlaca("CBA4321");
        System.out.println("Placa Alterado: " + veiculo.getPlaca());
        System.out.println("\n..............................................\n");
        System.out.println("Teste das funções da classe 'Sinistro'\n");
        System.out.println("Data Inicial: " + sinistro.getData());
        sinistro.setData("21/03/2023");
        System.out.println("Data Alterado: " + sinistro.getData());
        System.out.println("--*--");
        System.out.println("Teste de Geração de ID:");
        System.out.println(sinistro.gerarId());
        System.out.println("\n..............................................\n");
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente("cliente", "604.229.621-39", "15/02/1963", 60, "Rua Pitágoras");
        Sinistro sinistro = new Sinistro("16/03/2023", "Rua Pitágoras");
        Seguradora seguradora = new Seguradora("cliente", "3333-3333", "cliente@gmail.com", "Rua Pitágoras");
        Veiculo carro = new Veiculo("0000-00", "marca", "modelo");
    }
}

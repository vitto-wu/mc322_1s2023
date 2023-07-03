import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ArquivoClientePJ implements InterfaceArquivos<ClientePJ>{

    private static String filePath = "lab06\\src\\lab06-seguradora_arquivos_v2\\clientesPJ.csv";

    public boolean gravarArquivo(ClientePJ clientePJ) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String cliente = dadosToString(clientePJ);

            writer.write(cliente);
            writer.newLine();
            writer.close();
        } catch(Exception error) {
            System.out.println("Não foi possível gravar os dados!");
            return false;
        }
        return true;
    }

    public String lerArquivo(String cnpj) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int i = 0;
            while ((linha = reader.readLine()) != null) {
                if (i >= 1) {
                    String[] campos = linha.split(",");
                    if (campos[0].equals(cnpj)) {
                        return linha;
                    }
                }
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao iterar pelas linhas do arquivo: " + e.getMessage());
        }
        
        return "[NÃO-ENCONTRADO]";
    };

    public String dadosToString(ClientePJ clientePJ) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String codeFrota = "";
        if (clientePJ.getListaFrota().size() > 0) {
            codeFrota = clientePJ.getListaFrota().get(0).getCode();
        }

        String dataFundacao = clientePJ.getDataFundacao().format(formatoData);
        return clientePJ.getCnpj() + "," + clientePJ.getNome() + "," + clientePJ.getTelefone() + "," +
        clientePJ.getEndereco() + "," + clientePJ.getEmail() + "," + dataFundacao + "," + 
        codeFrota;
    }
}
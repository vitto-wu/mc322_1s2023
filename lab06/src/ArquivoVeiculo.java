import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;

public class ArquivoVeiculo implements InterfaceArquivos<Veiculo> {

    private static String filePath = "lab06\\src\\lab06-seguradora_arquivos_v2\\veiculos.csv";

    public boolean gravarArquivo(Veiculo veiculo) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String veiculoString = dadosToString(veiculo);
            writer.write(veiculoString);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os dados!");
            return false;
        }
        return true;
    }
    public String lerArquivo(String placa) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int i = 0;
            while ((linha = reader.readLine()) != null) {
                if (i >= 1) {
                    String[] campos = linha.split(",");
                    if (campos[0].equals(placa)) {
                        return linha;
                    }
                }
                i++;
            }
            reader.close();
        } catch (IOException error) {
            System.out.println("Ocorreu um erro ao iterar pelas linhas do arquivo: " + error.getMessage());
        }
        
        return "[ARQUIVO NÃO ENCONTRADO]";
    };

    public String dadosToString(Veiculo veiculo) {
        return veiculo.getPlaca() + "," + veiculo.getMarca() + "," + veiculo.getModelo() + "," + veiculo.getAnoFabricacao();
    }
}

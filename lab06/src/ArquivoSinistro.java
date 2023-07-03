import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class ArquivoSinistro implements InterfaceArquivos<Sinistro>{

    private static String filePath = "lab06\\src\\lab06-seguradora_arquivos_v2\\sinistro.csv";

    public void criarArquivo() {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String header = "ID_SINISTRO,DATA,ENDERECO,CONDUTOR,SEGURO";
            writer.write(header);
            writer.newLine();
            writer.close();
        } catch(Exception error) {
            System.out.println("Não foi possível criar o arquivo!");
        }
    }

    public boolean gravarArquivo(Sinistro sinistro) {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            if (!fileExists) {
                criarArquivo();
            }

            String sinistroString = dadosToString(sinistro);
            sinistroString = getNovaId() + "," + sinistroString;
            writer.write(sinistroString);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os dados!");
            return false;
        }
        return true;
    }
    public String lerArquivo(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int i = 0;
            while ((linha = reader.readLine()) != null) {
                if (i >= 1) {
                    String[] campos = linha.split(",");
                    if (campos[0].equals(id)) {
                        return linha;
                    }
                }
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao iterar pelas linhas do arquivo: " + e.getMessage());
        }
        
        return "ARQUIVO NÃO ENCONTRADO";
    };

    public String dadosToString(Sinistro sinistro) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String data = sinistro.getData().format(formatoData);

        return data + "," + sinistro.getEndereco() + "," + sinistro.getCondutor().getCpf() + "," + sinistro.getSeguro().getId();
    }

    public String getNovaId() {
        int numeroLinhas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                numeroLinhas++;
            }
            DecimalFormat decimalFormat = new DecimalFormat("000");
            String numeroZero = decimalFormat.format(numeroLinhas);
            reader.close();
            return numeroZero;
            
        } catch (IOException error) {
            System.out.println("Ocorreu um erro ao iterar pelas linhas do arquivo: " + error.getMessage());
        }
        
        return "000";
    }
}
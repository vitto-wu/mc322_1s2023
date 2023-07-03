import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class ArquivoSeguro implements InterfaceArquivos<Seguro>{

    private static String filePath = "lab06\\src\\lab06-seguradora_arquivos_v2\\serguros.csv";

    public void criarArquivo() {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String header = "ID_SEGURO,DATA_INICIO,DATA_FIM,SEGURADORA,LISTA_SINISTROS,LISTA_CONDUTORES,VALOR_MENSAL";
            writer.write(header);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os Dados");
        }
    }

    public boolean gravarArquivo(Seguro seguro) {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            if (!fileExists) {
                criarArquivo();
            }

            String seguroString = dadosToString(seguro);
            seguroString = getNovaId() + "," + seguroString;
            writer.write(seguroString);
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
        } catch (IOException error) {
            System.out.println("Ocorreu um erro ao iterar pelas linhas do arquivo: " + error.getMessage());
        }
        
        return "ARQUIVO NÃO ENCONTRADO";
    };

    public String dadosToString(Seguro seguro) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataInicio = seguro.getDataInicio().format(formatoData);
        String dataFim = seguro.getDataFim().format(formatoData);

        return dataInicio + "," + dataFim + "," + seguro.getSeguradora().getNome() + "," +
        "" + "," + "" + "," + seguro.getValorMensal();
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
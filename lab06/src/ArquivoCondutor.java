import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.format.DateTimeFormatter;

public class ArquivoCondutor implements InterfaceArquivos<Condutor>{

    private static String filePath = "lab06\\src\\lab06-seguradora_arquivos_v2\\condutor.csv";

    public boolean gravarArquivo(Condutor condutor) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String condutorString = dadosToString(condutor);
            writer.write(condutorString);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os dados!");
            return false;
        }
        return true;
    }

    public String lerArquivo(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int i = 0;
            while ((linha = reader.readLine()) != null) {
                if (i >= 1) {
                    String[] campos = linha.split(",");
                    String regex = "[^0-9]";

                    if (campos[0].equals(cpf.replaceAll(regex, ""))) {
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

    public String dadosToString(Condutor condutor) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String regex = "[^0-9]";

        String dataNascimento = condutor.getDataNascimento().format(formatoData);
        return condutor.getCpf().replaceAll(regex, "") + "," + condutor.getNome() + "," + condutor.getTelefone() + "," +
        condutor.getEndereco() + "," + condutor.getEmail() + "," + dataNascimento;
    }
}
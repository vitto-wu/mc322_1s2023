import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class ArquivoFrota implements InterfaceArquivos<Frota>{

    private static String filePath = "lab06\\src\\lab06-seguradora_arquivos_v2\\frotas.csv";

    public boolean gravarArquivo(Frota frota) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String frotaString = dadosToString(frota);
            frotaString = getNovaId() + "," + frotaString;
            writer.write(frotaString);
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

    public String dadosToString(Frota frota) {
        StringBuilder string = new StringBuilder();

        int tamanhoFrota = frota.getListaVeiculos().size();

        if (tamanhoFrota == 1) {
            string.append(frota.getListaVeiculos().get(0).getPlaca()+","+""+","+"");
        }
        if (tamanhoFrota == 2) {
            string.append(frota.getListaVeiculos().get(0).getPlaca()+","+frota.getListaVeiculos().get(1).getPlaca()+","+"");
        }
        if (tamanhoFrota >= 3) {
            string.append(frota.getListaVeiculos().get(0).getPlaca()+","+frota.getListaVeiculos().get(1).getPlaca()
            +","+frota.getListaVeiculos().get(2).getPlaca());
        }
        return string.toString();
    }

    public String getNovaId() {
        int numeroLinhas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                numeroLinhas++;
            }
            DecimalFormat decimalFormat = new DecimalFormat("000");
            String numeroComZero = decimalFormat.format(numeroLinhas);
            reader.close();
            return numeroComZero;
            
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao iterar pelas linhas do arquivo: " + e.getMessage());
        }
        
        return "000";
    }
}
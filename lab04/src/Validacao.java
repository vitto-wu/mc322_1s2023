import java.util.ArrayList;

public class Validacao {
    //Lista de IDs já registrados
    public static ArrayList <Integer> idRegistrados = new ArrayList<>();

    //Lista das seguradoras cadstradss
    public static ArrayList <Seguradora> listaSeguradoras = new ArrayList<>();

    /**
     * Verifica se os digitos verificadores do CPF são validos
     * @param numeroCPF cpf numérico
     * @param indexDigitoVerificador indice do digito verificador (0 ou 1)
     * @return true caso os digitos forem validos e false caso não
     */
    private static boolean digitosVerificadoresCPF(String numeroCPF, int indexDigitoVerificador) {
        int soma = 0, resto;

        for (int i = 0; i < 9 + indexDigitoVerificador; i++) {
            soma += (numeroCPF.charAt(i) - '0') * (10 + indexDigitoVerificador - i);
        }

        resto = (soma % 11 < 2) ?  0 : 11 - (soma % 11);

        return (resto ==  (numeroCPF.charAt(9 + indexDigitoVerificador) - '0'));
    }

    /**
     * verifica se o cpf é valido
     * @param cpf String cpf
     * @return true caso o cpf é valido e false caso não
     */
    public static boolean validarCPF(String cpf) {
        String numeroCPF = cpf.replaceAll("[^0-9]", "");

        if (numeroCPF.length() != 11) {
            return false;
        }

        for (int i = 1; i < 11; i++) {
            if (numeroCPF.charAt(0) != numeroCPF.charAt(i)) {
                break;
            }
            if (i == 10) {
                return false;
            }
        }

        return (digitosVerificadoresCPF(numeroCPF, 0) && digitosVerificadoresCPF(numeroCPF, 1));
    }

    /**
     * Verifica se os digitos verificadores do cnpj é valido
     * @param numeroCNPJ cnpj numérico
     * @param multiplicadores lista de multiplicadores para calcular os digitos verificadores
     * @param indexDigitoVerificador indice dos digitos verificador (0 ou 1)
     * @return true caso seja os digitos forem válidos e false caso não
     */
    private static boolean varificarDigitosCNPJ(String numeroCNPJ, int[] multiplicadores, int indexDigitoVerificador){
        int soma = 0, resto;

        for (int i = 0; i < 12 + indexDigitoVerificador; i++) {
            soma += (numeroCNPJ.charAt(i) - '0') * multiplicadores[i + 1 - indexDigitoVerificador];
        }

        resto = (soma % 11 < 2) ?  0 : 11 - (soma % 11);

        return (resto ==  (numeroCNPJ.charAt(12 + indexDigitoVerificador) - '0'));
    }

    /**
     * Verifica se o cnpj é valido
     * @param cnpj String cnpj
     * @return true caso seja válido e false caso não
     */
    public static boolean validarCNPJ(String cnpj) {
        String numeroCNPJ = cnpj.replaceAll("[^0-9]", "");
        int[] multiplicadores = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        
        if(numeroCNPJ.length() != 14){
            return false;
        }

        return (varificarDigitosCNPJ(numeroCNPJ, multiplicadores, 0) &&  varificarDigitosCNPJ(numeroCNPJ, multiplicadores, 1));
    }

    /**
     * Verifica se um nome contém apenas letras
     * @param nome nome do cliente
     * @return true caso seja válido e false caso não
     */
    public static boolean validarNome(String nome) {
        return nome.matches("[a-zA-Z]+");
    }

    /**
     * Verifica se a seguradora foi cadastrada
     * @param nomeSeguradora nome da seguradora
     * @return true caso exista a seguradora e false caso não
     */
    public static boolean seguradoraCadastrada(String nomeSeguradora) {
        for(Seguradora s : listaSeguradoras){
            if(s.getNome().equals(nomeSeguradora)){
                return true;
            }
        }

        return false;
    }
}

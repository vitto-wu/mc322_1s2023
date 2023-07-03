public interface InterfaceArquivos<T> {
    public boolean gravarArquivo(T t);
    public String lerArquivo(String identificador);
    public String dadosToString(T t);
}
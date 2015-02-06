package projecto;

/**
 * Classe que representa um DVD da biblioteca.
 * Deriva da classe Produto
 */
public class DVD extends Produto
{
    public DVD(String titulo, String editora, String ano)
    {
        super(titulo, editora, ano);
    }

    /**
     * Função responsável por criar novo DVD através de dados lidos de um ficheiro
     * @param linha recebe uma linha com os atributos do dvd separados por ";"
     */
    public DVD ( String linha )
    {
        super(linha);
    }
}

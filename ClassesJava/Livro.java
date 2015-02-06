package projecto;

/**
 * Classe que representa um livro da biblioteca.
 * Deriva da classe Produto
 */
public class Livro extends Produto
{
    private String autor, cota, ISBN;

    public Livro(String title, String author, String editor, String anoEdicao, String cotaParam, String ISBNParam)
    {
        super(title,editor,anoEdicao);
        this.ISBN = ISBNParam;
        this.cota = cotaParam;
        this.autor = author;
    }

    /**
     * Função responsável por criar novo livro através de dados lidos de um ficheiro
     * @param linha recebe uma linha com os atributos do livro separados por ";"
     */
    public Livro ( String linha )
    {
        super(linha);

        if ( isInvalid() )
            return;

        String[] result = linha.split(";");
        if ( result.length !=7 )
        {
            makeInvalid();
            return;
        }
        cota = result[4];
        ISBN = result[5];
        autor = result[6];
    }

    /**
     * Função responsável por criar linha para ser escrita num ficheiro com informação do livro
     * @return String com os atributos da classe, separados por ";"
     */
    public String getLine()
    {
        return ( super.getLine() + ";" + this.cota + ";" + this.ISBN + ";" + this.autor );
    }

    public String getAutor() {
        return autor;
    }

    public String getCota() {
        return cota;
    }

    public String getISBN() {
        return ISBN;
    }

}

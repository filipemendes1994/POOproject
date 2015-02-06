package projecto;

/**
 * Classe que representa um utilizador do tipo Leitor Aluno na biblioteca. <br/>
 * Deriva da classe Leitor
 */
public class Aluno extends Leitor
{
    public Aluno ( String nome, String morada, String telefone, String mail, String password , String data, Biblioteca biblio)
    {
        super( nome, morada, telefone, mail, password, data, biblio);
    }

    /**
     *
     * @return número máximo de produtos que um aluno pode ter requisitados ao mesmo tempo
     */
    public int maximoProdutos()
    {
        return 2;
    }

    /**
     *
     * @return número máximo de dias que um aluno pode ter um produto requisitado
     */
    public int maximoTempo()
    {
        return 5;
    }
}

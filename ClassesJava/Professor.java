package projecto;

/**
 * Classe que representa um utilizador do tipo Leitor Professor na biblioteca. <br/>
 * Deriva da classe Leitor
 */
public class Professor extends Leitor
{
    public Professor ( String nome, String morada, String telefone, String mail, String password, String data, Biblioteca biblio )
    {
        super( nome, morada, telefone, mail, password, data, biblio);
    }

    /**
     *
     * @return número máximo de produtos que um professor pode ter requisitados ao mesmo tempo
     */
    public int maximoProdutos()
    {
        return 5;
    }

    /**
     *
     * @return número máximo de dias que um professor pode ter um produto requisitado
     */
    public int maximoTempo()
    {
        return 90; // 90 dias, correspondendo mais ou menos a 3 meses
    }
}
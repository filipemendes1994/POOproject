package projecto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Classe abstracta que representa um produto na biblioteca <br/>
 * Livro e DVD são classes que a extendem, sendo a presente classe uma generalização de ambas
 */
public abstract class Produto implements Serializable
{
    private String titulo, editora,ano;
    private static int idActual = 1;
    private int id;
    protected ArrayList<Requisicao> requisicoesAntigas;
    protected Requisicao requisicaoActual;

    public Produto(String title, String editor, String anoEdicao)
    {
        this.titulo = title;
        this.id = idActual++;
        this.editora = editor;
        this.ano = anoEdicao;
        this.requisicoesAntigas = new ArrayList<Requisicao>();  // iniciar o array das requisicoes
        this.requisicaoActual = null;  // iniciar RequisicaoActual a null, indicando que está disponível para ser requisitado
    }

    /**
     *
     * @param r  Requisição que se pretende associar a este produto
     *
     * Caso seja uma requisição que já foi terminada, é adicionada ao array de <br/>
     * requisições antigas,caso contrário é definida como a requisição actual do produto <br/>
     */
    public void adicionaRequisicaoDoFicheiro( Requisicao r )
    {
        if ( r.isTerminada() )
            this.requisicoesAntigas.add(r);
        else
            this.requisicaoActual = r;
    }

    /**
     *
     * @param linha String que representa uma linha lida de um ficheiro <br/>
     *
     * Obtém-se os atributos do Produto dessa mesma linha e constrói-se um novo <br/>
     * produto com base neles
     */
    public Produto ( String linha )
    {
        String[] result = linha.split(";"); // pois em cada linha está guardado um produto com os seus
                                            // atributos separados por ";"

        if ( result.length < 4 ) // não há atributos suficientes
        {
            makeInvalid();
            return;
        }
        titulo = result[0];
        editora = result[1];
        ano = result[2];

        try
        {
            id = Integer.valueOf(result[3]); // obter o id do produto
        } catch (NumberFormatException e) {
            makeInvalid();
            return;
        }

        if ( id > idActual )  // deste modo nunca temos de guardar o idActual em nenhum ficheiro
            idActual = id;

        this.requisicoesAntigas = new ArrayList<Requisicao>();
        this.requisicaoActual = null;
    }

    /**
     *
     * @return String com os atributos do produto separados por ";", para ser escrita num ficheiro
     */
    public String getLine()
    {
        return ( this.titulo + ";" + this.editora + ";" + this.ano + ";" + this.id );
    }

    public boolean isInvalid()
    {
        return ( id == -1);
    }

    /**
     *
     * @param o Recebe um produto o
     * @return true se este produto e o produto passado como parâmetro forem o mesmo
     *         false caso contrário
     */
    public boolean equals ( Produto o )
    {
        return (this.id==o.id);
    }

    protected void makeInvalid()
    {
        id = -1;
    }

    /**
     * Função responsável por devolver número de vezes que este produto foi requisitado no mês passado
     * @return número de vezes que este produto foi requisitado no mês passado
     */
    public int requisicoesMesPassado()
    {
        //obter mes actual e mes passado
        int mesPassado, numRequisicoes=0;
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        int ano = Calendar.getInstance().get(Calendar.YEAR);

        if ( mesActual == 0 )
        {
            mesPassado = 11;
            ano -= 1;
        }
        else
            mesPassado = mesActual - 1;

        for ( Requisicao r : requisicoesAntigas )
            if ( r.getDataRequisicao().get(Calendar.MONTH) == mesPassado && r.getDataRequisicao().get(Calendar.YEAR) == ano )
                numRequisicoes++;

        // verificar ainda  a requisicao actual
        if ( this.requisicaoActual != null )
            if ( this.requisicaoActual.getDataRequisicao().get(Calendar.MONTH) == mesPassado && this.requisicaoActual.getDataRequisicao().get(Calendar.YEAR) == ano  )
                numRequisicoes++;

        return numRequisicoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String getAno() {
        return ano;
    }

    public Requisicao getRequisicaoActual() {
        return requisicaoActual;
    }

    public int getId() {
        return id;
    }
}

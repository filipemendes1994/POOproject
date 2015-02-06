package projecto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe que representa cada Requisição da Biblioteca. <br/>
 * Cada requisição tem sempre associada a data em que foi feita, o utilizador que requisitou,
 * o produto requisitado e uma variável booleana que indica se a requisição já está terminada, isto é,
 * se o produto em questão já foi entregue pelo utilizador
 */
public class Requisicao implements Serializable
{
    private Calendar dataRequisicao;
    private Utilizador utilizador;
    private Produto produto;
    private boolean terminada;
    
    public Requisicao( Utilizador utilizadorNovo, Produto produto)
    {
        this.utilizador = utilizadorNovo;
        this.produto = produto;
        this.dataRequisicao = Calendar.getInstance(); // data actual
        this.terminada = false;
    }

    public Requisicao (){
    }

    /**
     * Função responsável por criar nova Requisição através de dados de uma Stream
     * @param stream recebe a stream de onde vai ler os dados para criar esta Requisição
     * @param biblio biblioteca a que esta requisição ficará associada
     * @return true se a leitura dos dados for bem sucedida. <br/>
     *         false quando deu algum erro
     */
    public boolean fromObject( ObjectInputStream stream , Biblioteca biblio )
    {
        try {
            this.dataRequisicao = (Calendar) stream.readObject();
            this.utilizador = biblio.encontraUtilizador((Utilizador)stream.readObject());
            int ID = stream.readInt();
            this.produto = biblio.encontraProdutoID( ID );
            this.terminada = stream.readBoolean();
            this.produto.adicionaRequisicaoDoFicheiro(this);
            this.utilizador.adicionaRequisicaoDoFicheiro(this);
            biblio.adicionaRequisicaoDoFicheiro(this);
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) { return false; }

        return true;
    }

    /**
     * Função responsável por escrever os dados desta requisição para uma stream
     * @param stream recebe a stream para onde deve escrever
     */
    public void objectWrite( ObjectOutputStream stream )
    {
        try {
            stream.writeObject(this.dataRequisicao);
            stream.writeObject(this.utilizador);
            stream.writeInt(this.produto.getId());
            stream.writeBoolean(this.terminada);
        } catch (IOException ignore) {}
    }

    /**
     *
     * @return a data de entrega do produto desta requisicão, tendo em conta a data da requisição
     * e o tipo de utilizador que requisitou
     */
    public Calendar dataEntrega()
    {
        Calendar dataEntrega = this.dataRequisicao;
        dataEntrega.add( Calendar.DAY_OF_MONTH , ((Leitor)this.utilizador).maximoTempo());
        return dataEntrega;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public Calendar getDataRequisicao()
    {
        Calendar copia = Calendar.getInstance();
        copia.setTime( dataRequisicao.getTime() );
        return copia;
    }

    public Produto getProduto() {
        return produto;
    }

    public boolean isTerminada() {
        return terminada;
    }

    public void setTerminada(boolean terminada) {
        this.terminada = terminada;
    }
}
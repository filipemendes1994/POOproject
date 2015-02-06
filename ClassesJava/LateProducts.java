/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Esta classe é responsável por criar uma nova janela onde serão listados todos os produtos que se <br/>
 * encontram actualmente em atraso (isto é, que já deviam ter sido entregues mas que não foram)
 */
public class LateProducts extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1,jButton2;
    private JLabel jLabel2, jLabel3, jLabel4, jLabel6,jLabel7;
    private JComboBox jComboBox1;
    private JTable tabelaLivros, tabelaDVD;
    private JScrollPane areaRolamento1, areaRolamento2;
    private JPanel painelTabelas;
    private boolean mostraLivros, mostraDVD;
    private Object [][] livros, dvd;

    /**
     *
     * @param biblioteca Receba a biblioteca que nos servirá como base de dados
     */
    public LateProducts(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    /**
     * Função responsável por criar e adicionar os listeners aos componentes da janela <br/>
     * No caso de não existirem produtos em atraso é apresentada uma mensagem na própria janela com <br/>
     * essa mesma informação, caso contrário são geradas, também na mesma janela, tabelas com os <br/>
     * produtos em atraso, podendo ser escolhido o tipo de produto (Livro,DVD,Ambos) <br/>
     * Nessas tabelas contam o produto, o utilizador, a data de requisicao e a suposta data de entrega
     */
    private void initComponents()
    {
        mostraDVD = false;
        mostraLivros = true; // a comboBox por defeito vem com "Livros" seleccionado
        livros = biblioteca.livrosAtraso();
        dvd = biblioteca.dvdAtraso();

        this.setLayout(new BorderLayout(10, 10));
        this.setMinimumSize(new Dimension(800, 500));
        painelTabelas = new JPanel();
        painelTabelas.setLayout( new BoxLayout( painelTabelas, BoxLayout.Y_AXIS));

        // TITULOS
        jLabel2 = new JLabel("MENU DE ADMINISTRADOR");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel= new JPanel();
        painel.add(jLabel2);

        jLabel3 = new JLabel("PRODUTOS EM ATRASO");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel2 = new JPanel();
        painel2.add(jLabel3);

        JPanel painelTitulos = new JPanel();
        painelTitulos.setLayout( new BoxLayout( painelTitulos,  BoxLayout.Y_AXIS));
        painelTitulos.add(painel);
        painelTitulos.add(painel2);
        //

        // PEDIR DADOS AO UTILIZADOR
        jLabel4 = new JLabel("Produtos");
        String [] poss = { "Livros", "DVD", "Ambos"};
        jComboBox1 = new JComboBox(poss);
        jComboBox1.addItemListener( new EventoJCBox());
        JPanel pesquisa = new JPanel();
        pesquisa.add(jLabel4);
        pesquisa.add(jComboBox1);

        painelTitulos.add(pesquisa);
        //

        // BOTÕES
        JPanel botoes = new JPanel();

        jButton1 = new JButton("RETROCEDER");
        botoes.add(jButton1);

        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2 = new JButton("PESQUISAR");
        botoes.add(jButton2);

        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        //

        // IMPRIMIR INFORMAÇAO
        JPanel total = new JPanel();
        total.setLayout( new BoxLayout( total, BoxLayout.Y_AXIS ));

        JPanel temp1 = new JPanel();
        jLabel6 = new JLabel("Não existem livros em atraso");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setVisible(false);
        temp1.add(jLabel6);
        total.add(temp1);

        JPanel temp2 = new JPanel();
        jLabel7 = new JLabel("Não existem DVD em atraso");
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setVisible(false);
        temp2.add(jLabel7);
        total.add(temp2);

        painelTabelas.add(total);

        // Tabelas
        String[] titulos = {"Nome Produto","Utilizador", "Data Requisicao", "Data Entrega" };

        // como vamos precisando de ir alterando as tabelas, temos de usar o DefaultTableModel,
        // para posteriormente podermos usar metodos como 'addRow'

        DefaultTableModel tmLivros = new DefaultTableModel(titulos,0);
        tabelaLivros = new JTable(tmLivros);
        areaRolamento1 = new JScrollPane(tabelaLivros);
        tabelaLivros.setPreferredScrollableViewportSize(new Dimension(450, 100));
        areaRolamento1.setVisible(false);
        painelTabelas.add(areaRolamento1);

        DefaultTableModel tmDVD = new DefaultTableModel(titulos,0);
        tabelaDVD = new JTable(tmDVD);
        areaRolamento2 = new JScrollPane(tabelaDVD);
        tabelaDVD.setPreferredScrollableViewportSize(new Dimension(450, 100));
        areaRolamento2.setVisible(false);
        painelTabelas.add(areaRolamento2);
        //

        //
        this.add("North", painelTitulos);
        this.add("Center" , painelTabelas );
        this.add("South", botoes);
        this.setVisible(true);
        //
    }

    /**
     * Esta função é chamada quando se pretende listar os livros em atraso. <br/>
     * Não precisa de receber de nada porque tanto a tabela como o array com os livros <br/>
     * são variáveis da classe <br/>
     * Tem como função apresentar uma mensagem a avisar que não há livros em atraso ou, caso existam, <br/>
     * preencher a tabela correspondente aos livros, já criada, com os dados do array dos livros.
     */
    private void imprimeTabelaLivros()
    {
        if ( livros == null )
        {
            jLabel6.setVisible(true);
            areaRolamento1.setVisible(false);
        }
        else
        {
            jLabel6.setVisible(false);
            DefaultTableModel tm2 = (DefaultTableModel) tabelaLivros.getModel();
            int numRows = tm2.getRowCount();

            // apagar tabela anterior, se existente
            for ( int i =0; i<numRows ; i++ )
                tm2.removeRow(0);

            for ( Object[] u: livros )
                tm2.addRow(u);

            areaRolamento1.setVisible(true);
        }
    }

    /**
     * Esta função é chamada quando se pretende listar os DVD em atraso. <br/>
     * Não precisa de receber de nada porque tanto a tabela como o array com os DVD <br/>
     * são variáveis da classe <br/>
     * Tem como função apresentar uma mensagem a avisar que não há DVD em atraso ou, caso existam, <br/>
     * preencher a tabela correspondente aos DVD, já criada, com os dados do array dos DVD.
     */
    private void imprimeTabelaDVD()
    {
        if ( dvd == null )
        {
            jLabel7.setVisible(true);
            areaRolamento2.setVisible(false);
        }
        else
        {
            jLabel7.setVisible(false);
            DefaultTableModel tm2 = (DefaultTableModel) tabelaDVD.getModel();
            int numRows = tm2.getRowCount();

            // apagar tabela anterior, se existente
            for ( int i =0; i<numRows ; i++ )
                tm2.removeRow(0);

            for ( Object[] u: dvd )
                tm2.addRow(u);

            areaRolamento2.setVisible(true);
        }
    }

    /**
     *
     * @param evt A função é chamada quando é detectado um clique de rato no botão "Pesquisar" <br/>
     *
     * A função verifica quais os produtos que se pretendem, através de variáveis booleanas, <br/>
     * que são alteradas cada vez que se muda a JComboBox e chama as respectivas funções <br/>
     * construtoras de tabelas ou de mensagens de aviso.
     */
    private void jButton2MouseClicked( MouseEvent evt)
    {
        if ( this.mostraLivros && this.mostraDVD ) // se for para imprimir de ambos os produtos
        {
            imprimeTabelaLivros();
            imprimeTabelaDVD();
        }

        else if (this.mostraLivros )
        {
            areaRolamento2.setVisible(false);
            imprimeTabelaLivros();
        }

        else
        {
            areaRolamento1.setVisible(false);
            imprimeTabelaDVD();
        }

        this.repaint();
        this.revalidate();
    }

    /**
     * Listener para a JComboBox
     * Altera as variáveis booleanas "mostraLivros" e "mostraDVD" consoante <br/>
     * a opção seleccionada pelo user
     */
    private class EventoJCBox implements ItemListener
    {
        public void itemStateChanged( ItemEvent ev )
        {
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);

            if ( jComboBox1.getSelectedItem().equals("Livros") )
            {
                mostraLivros = true;
                mostraDVD = false;
            }

            else if ( jComboBox1.getSelectedItem().equals("DVD") )
            {
                mostraDVD = true;
                mostraLivros = false;
            }

            else
            {
                mostraLivros = true;
                mostraDVD = true;
            }
        }
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
    }
}

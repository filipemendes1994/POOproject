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
import java.util.Calendar;

public class PopularProducts extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1,jButton2;
    private JLabel jLabel2, jLabel3, jLabel4, jLabel6,jLabel7;
    private JComboBox jComboBox1;
    private JTable tabelaLivros, tabelaDVD;
    private JScrollPane areaRolamento1, areaRolamento2;
    private JPanel painelTabelas;
    private boolean mostraLivros;
    private Object [] livro, dvd;

    public PopularProducts(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        mostraLivros = true;

        this.setLayout(new BorderLayout(10, 10));
        this.setMinimumSize(new Dimension(500, 450));
        painelTabelas = new JPanel();
        painelTabelas.setLayout( new BoxLayout( painelTabelas, BoxLayout.Y_AXIS));

        // TITULOS
        jLabel2 = new JLabel("MENU DE ADMINISTRADOR");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel= new JPanel();
        painel.add(jLabel2);

        jLabel3 = new JLabel("PRODUTOS MAIS REQUISITADOS DO MÊS DE " + mesPassado().toUpperCase());
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel2 = new JPanel();
        painel2.add(jLabel3);

        JPanel painelTitulos = new JPanel();
        painelTitulos.setLayout( new BoxLayout( painelTitulos, BoxLayout.Y_AXIS));
        painelTitulos.add(painel);
        painelTitulos.add(painel2);
        //

        // PEDIR DADOS AO UTILIZADOR
        jLabel4 = new JLabel("Produtos");
        String [] poss = { "Livro", "DVD" };
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
        jLabel6 = new JLabel("Não foram requisitados livros no mês passado");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setVisible(false);
        temp1.add(jLabel6);
        total.add(temp1);

        JPanel temp2 = new JPanel();
        jLabel7 = new JLabel("Não foram requisitados DVD no mês passado");
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setVisible(false);
        temp2.add(jLabel7);
        total.add(temp2);

        painelTabelas.add(total);

        // Tabelas
        String[] titulosLivro = {"Titulo","Autor", "Ano", "Editora", "Cota", "ISBN", "Nº Requisições" };
        String[] titulosDVD = { "Titulo", "Ano", "Editora", "Nº Requisições"};

        DefaultTableModel tmLivros = new DefaultTableModel(titulosLivro,0);
        tabelaLivros = new JTable(tmLivros);
        areaRolamento1 = new JScrollPane(tabelaLivros);
        tabelaLivros.setPreferredScrollableViewportSize(new Dimension(450, 100));
        areaRolamento1.setVisible(false);
        painelTabelas.add(areaRolamento1);

        DefaultTableModel tmDVD = new DefaultTableModel(titulosDVD,0);
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

    private String mesPassado()
    {
        String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        //obter mes actual e mes passado
        int mesPassado;
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);

        if ( mesActual == 0 )
            mesPassado = 11;

        else
            mesPassado = mesActual - 1;

        return meses[mesPassado];
    }

    private class EventoJCBox implements ItemListener
    {
        public void itemStateChanged( ItemEvent ev )
        {
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);

            mostraLivros = jComboBox1.getSelectedItem().equals("Livro");
        }
    }

    private void imprimeTabelaLivros()
    {
        if ( livro == null )
        {
            jLabel6.setVisible(true);
            areaRolamento1.setVisible(false);
        }
        else
        {
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            areaRolamento2.setVisible(false);

            DefaultTableModel tm2 = (DefaultTableModel) tabelaLivros.getModel();
            int numRows = tm2.getRowCount();

            // apagar tabela anterior, se existente
            for ( int i =0; i<numRows ; i++ )
                tm2.removeRow(0);

            tm2.addRow(livro);

            areaRolamento1.setVisible(true);
        }
    }

    private void imprimeTabelaDVD()
    {
        areaRolamento1.setVisible(false);

        if ( dvd == null )
        {
            jLabel7.setVisible(true);
            areaRolamento2.setVisible(false);
        }
        else
        {
            jLabel7.setVisible(false);
            jLabel6.setVisible(false);

            DefaultTableModel tm2 = (DefaultTableModel) tabelaDVD.getModel();
            int numRows = tm2.getRowCount();

            // apagar tabela anterior, se existente
            for ( int i =0; i<numRows ; i++ )
                tm2.removeRow(0);

            tm2.addRow(dvd);

            areaRolamento2.setVisible(true);
        }
    }

    private void jButton2MouseClicked( MouseEvent evt)
    {
        if ( this.mostraLivros ) // se for para imprimir o livro
        {
            livro = this.biblioteca.livroMaisRequisitado();
            imprimeTabelaLivros();
        }

        else
        {
            dvd = this.biblioteca.dvdMaisRequisitado();
            imprimeTabelaDVD();
        }

        this.repaint();
        this.revalidate();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
    }
}

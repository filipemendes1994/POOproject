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

public class ViewProduct extends javax.swing.JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1,jButton2;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel6,jLabel7;
    private JTextField caixaTitulo;
    private JComboBox jComboBox1;
    private JTable tabelaLivros, tabelaDVD;
    private JScrollPane areaRolamento1, areaRolamento2;
    private JPanel painelTabelas;
    private boolean titulo;
    private Object [][] livros, dvd;

    public ViewProduct(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        this.setLayout(new BorderLayout(10, 10));
        this.setMinimumSize(new Dimension(800, 500));
        painelTabelas = new JPanel();
        painelTabelas.setLayout( new BoxLayout( painelTabelas, BoxLayout.Y_AXIS));

        // TITULOS
        jLabel2 = new JLabel("MENU DE LEITOR");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel= new JPanel();
        painel.add(jLabel2);

        jLabel3 = new JLabel("CONSULTAR PRODUTO");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel2 = new JPanel();
        painel2.add(jLabel3);

        JPanel painelTitulos = new JPanel();
        painelTitulos.setLayout( new BoxLayout( painelTitulos,  BoxLayout.Y_AXIS));
        painelTitulos.add(painel);
        painelTitulos.add(painel2);
        //

        // PEDIR DADOS AO UTILIZADOR
        jLabel4 = new JLabel("Pesquisar Por");
        String [] poss = { " ", "Titulo", "Autor"};
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

        // CAMPOS DE DADOS
        JPanel temp = new JPanel();
        jLabel1 = new JLabel("Inserir Titulo: ");
        temp.add(jLabel1);

        caixaTitulo = new JTextField(10);
        temp.add(caixaTitulo);

        painelTitulos.add(temp);
        //

        // IMPRIMIR INFORMAÇAO
        JPanel total = new JPanel();
        total.setLayout( new BoxLayout( total, BoxLayout.Y_AXIS ));

        JPanel temp1 = new JPanel();
        jLabel6 = new JLabel("Não foram encontrados livros para esse titulo");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setVisible(false);
        temp1.add(jLabel6);
        total.add(temp1);

        JPanel temp2 = new JPanel();
        jLabel7 = new JLabel("Não foram encontrados DVD para esse titulo");
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setVisible(false);
        temp2.add(jLabel7);
        total.add(temp2);

        painelTabelas.add(total);

        // Tabelas
        String[] titulosDVD = {"Titulo","Ano","Editora", "Nº Requisicoes", "Requisitado Actualmente"};
        String[] titulos = {"Titulo","Ano","Editora", "Nº Requisicoes", "Requisitado Actualmente","Cota","ISBN","Autor"};

        DefaultTableModel tmLivros = new DefaultTableModel(titulos,0);
        tabelaLivros = new JTable(tmLivros);
        areaRolamento1 = new JScrollPane(tabelaLivros);
        tabelaLivros.setPreferredScrollableViewportSize( new Dimension(450,100));
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
        this.add("South" , botoes );
        this.setVisible(true);
        //
    }

    private void imprimeTabelaAutor()
    {
        areaRolamento2.setVisible(false);

        if ( livros == null )
        {
            jLabel6.setText("Não foram encontrados livros para esse autor");
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

    private void imprimeTabelasTitulo()
    {
        if ( livros == null )
        {
            jLabel6.setText("Não foram encontrados livros para esse titulo");
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

        // Verificamos agora para os dvd
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

            // adicionar novas linhas
            for ( Object[] u: dvd )
                tm2.addRow(u);

            areaRolamento2.setVisible(true);
        }
    }

    private void jButton2MouseClicked( MouseEvent evt)
    {
        if ( this.titulo ) // se for para pesquisar por titulo
        {
            System.out.println("HEYYY" + caixaTitulo.getText());
            livros = biblioteca.consultaLivroTitulo( caixaTitulo.getText() );

            dvd = biblioteca.consultaDVDTitulo( caixaTitulo.getText() );
            imprimeTabelasTitulo();
        }

        else // Para pesquisar por autor
        {
            livros = biblioteca.consultaLivroAutor( caixaTitulo.getText() );
            imprimeTabelaAutor();
        }

        this.repaint();
        this.revalidate();
    }

    private class EventoJCBox implements ItemListener
    {
        public void itemStateChanged( ItemEvent ev )
        {
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);

            if ( jComboBox1.getSelectedItem().equals("Titulo") )
            {
                jLabel1.setText("Inserir Titulo: ");
                titulo = true;
            }

            else if ( jComboBox1.getSelectedItem().equals("Autor") )
            {
                jLabel1.setText("Inserir Autor: ");
                titulo = false;
            }

        }
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
    }
}

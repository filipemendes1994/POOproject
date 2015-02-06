/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteProduct extends JFrame
{
    private Biblioteca biblioteca;
    private JLabel jLabel1, jLabel2, jLabel3,jLabel4, jLabel6;
    private JComboBox jComboBox1;
    private JButton jButton1, jButton2;
    private JTextField caixaTitulo;
    private boolean livro;  // indica se o produto a requisitar é um livro ou um dvd


    public DeleteProduct(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        this.setLayout(new BorderLayout(10, 10));
        this.setMinimumSize(new Dimension(700, 300));

        // TITULOS
        jLabel2 = new JLabel("MENU DE LEITOR");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel= new JPanel();
        painel.add(jLabel2);

        jLabel3 = new JLabel("ELIMINAR PRODUTO");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel2 = new JPanel();
        painel2.add(jLabel3);

        JPanel painelTitulos = new JPanel();
        painelTitulos.setLayout( new BoxLayout( painelTitulos,  BoxLayout.Y_AXIS));
        painelTitulos.add(painel);
        painelTitulos.add(painel2);
        //

        // PEDIR DADOS AO UTILIZADOR
        jLabel4 = new JLabel("Requisitar Produto");
        String [] poss = { " ", "Livro", "DVD"};
        jComboBox1 = new JComboBox(poss);
        jComboBox1.addItemListener( new EventoJCBox());
        JPanel pesquisa = new JPanel();
        pesquisa.add(jLabel4);
        pesquisa.add(jComboBox1);

        painelTitulos.add(pesquisa);
        //

        // BOTÕES
        JPanel botoes = new JPanel();

        jButton1 = new JButton("ELIMINAR");
        botoes.add(jButton1);

        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2 = new JButton("RETROCEDER");
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
        jLabel6 = new JLabel("Não foi encontrado nenhum produto para os dados inseridos");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setVisible(false);
        //

        this.add("North", painelTitulos);
        this.add("Center" , jLabel6 );
        this.add("South" , botoes );
        this.setVisible(true);

    }

    private class EventoJCBox implements ItemListener
    {
        public void itemStateChanged( ItemEvent ev )
        {
            if ( jComboBox1.getSelectedItem().equals("Livro") )
            {
                jLabel1.setText("Inserir ISBN: ");
                livro = true;
            }

            else if ( jComboBox1.getSelectedItem().equals("DVD") )
            {
                jLabel1.setText("Inserir Titulo: ");
                livro = false;
            }
        }
    }

    private void jButton1MouseClicked( MouseEvent evt)
    {
        String dados = this.caixaTitulo.getText();
        jLabel6.setVisible(false);

        ResultadoRequisicao resultado = ((Administrador)this.biblioteca.getUtilizadorActual()).eliminaProduto( dados, livro);

        if ( resultado == ResultadoRequisicao.OK )
        {
            JOptionPane.showMessageDialog(this,"O produto foi eliminado do sistema com sucesso");
            return;
        }

        else if ( resultado == ResultadoRequisicao.UNAVAILABLE )
        {
            JOptionPane.showMessageDialog(this, "Impossível eliminar: produto encontra-se actualmente requisitado");
            return;
        }

        // caso nenhum produto tenha sido encontrado
        jLabel6.setVisible(true);
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
    }
}

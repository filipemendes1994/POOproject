package projecto;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Estatisticas extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1,jButton2;
    private JLabel jLabel2, jLabel3, jLabel4, jLabel6,jLabel7;
    private JComboBox jComboBox1;
    private JPanel painelTabelas;
    private int mesEscolhido;
    private float[] info;

    public Estatisticas( Biblioteca biblioteca )
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        this.mesEscolhido = 1;

        this.setLayout(new BorderLayout(10, 10));
        this.setMinimumSize(new Dimension(500, 450));
        painelTabelas = new JPanel();
        painelTabelas.setLayout( new BoxLayout( painelTabelas, BoxLayout.Y_AXIS));

        // TITULOS
        jLabel2 = new JLabel("MENU DE ADMINISTRADOR");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel= new JPanel();
        painel.add(jLabel2);

        jLabel3 = new JLabel("ESTATÍSTICAS MENSAIS");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel painel2 = new JPanel();
        painel2.add(jLabel3);

        JPanel painelTitulos = new JPanel();
        painelTitulos.setLayout( new BoxLayout( painelTitulos, BoxLayout.Y_AXIS));
        painelTitulos.add(painel);
        painelTitulos.add(painel2);
        //

        // PEDIR DADOS AO UTILIZADOR
        jLabel4 = new JLabel("Escolher Mês");
        String [] poss = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto","Setembro", "Outubro", "Novembro", "Dezembro"};
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
        jLabel6 = new JLabel("Erro no cálculo das estatísticas");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setVisible(false);
        temp1.add(jLabel6);
        total.add(temp1);

        JPanel temp2 = new JPanel();
        jLabel7 = new JLabel("Erro no cálculo das estatísticas");
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setVisible(false);
        temp2.add(jLabel7);
        total.add(temp2);

        painelTabelas.add(total);
        //

        //
        this.add("North", painelTitulos);
        this.add("Center" , painelTabelas );
        this.add("South", botoes);
        this.setVisible(true);
        //
    }

    private class EventoJCBox implements ItemListener
    {
        public void itemStateChanged( ItemEvent ev )
        {
            String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto","Setembro", "Outubro", "Novembro", "Dezembro"};
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);

            String mes = (String)jComboBox1.getSelectedItem();

            // encontrar o número correspondente ao mês escolhido
            for ( int i=0; i<meses.length; i++ )
                if ( meses[i].equals(mes) )
                    mesEscolhido = i+1;
        }
    }

    private void jButton2MouseClicked( MouseEvent evt)
    {
        info = this.biblioteca.dadosEstatisticos( this.mesEscolhido );
        System.out.println("O MEDIO E " + info[0]);
        jLabel6.setText("NÚMERO MÉDIO DE REQUISIÇÕES: " + info[0]);
        jLabel7.setText("DIA COM MAIS REQUISIÇÕES: " + (int)info[1]);

        jLabel7.setVisible(true);
        jLabel6.setVisible(true);

        this.repaint();
        this.revalidate();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
    }
}

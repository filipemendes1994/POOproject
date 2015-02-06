package projecto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InformacoesMensais extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1, jButton2, jButton3, jButton4;
    private JLabel jLabel1;
    private MenuAdmin menu;

    public InformacoesMensais( Biblioteca biblioteca, MenuAdmin menuAdmin )
    {
        this.biblioteca = biblioteca;
        this.menu = menuAdmin;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        // TITULOS
        jLabel1 = new JLabel("INFORMAÇÕES MENSAIS");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);

        // BOTÕES
        jButton1 = new JButton("PRODUTOS MAIS REQUISITADOS");
        jButton2 = new JButton("PRODUTOS NÃO REQUISITADOS");
        jButton3 = new JButton("ESTATÍSTICAS MENSAIS");
        jButton4 = new JButton("RETROCEDER");

        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        //

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(23, 23, 23)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(100, 100, 100)
                                .addComponent(jButton4)
                                .addGap(0, 41, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        new PopularProducts( this.biblioteca ).setVisible(true);
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        new ProdutosNaoRequisitados( this.biblioteca );
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        new Estatisticas( this.biblioteca );
    }

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt)
    {
        this.setVisible(false);
        new MenuLeitor( this.biblioteca, this.menu ).setVisible(true);
    }
}
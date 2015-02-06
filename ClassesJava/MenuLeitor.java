/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Esta é a classe responsável por gerir a janela do Menu de Leitor <br/>
 * É a única janela que está disponível para um Utilizador Leitor mas também está disponível para
 * administradores, a par de outras janelas com mais opções especiais
 */
public class MenuLeitor extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton10, jButton1, jButton11, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9;
    private JLabel jLabel1;
    private MenuAdmin menuAnterior;

    /**
     *
     * @param biblioteca Recebe a biblioteca que servirá de base de dados para as suas operações
     * @param menu Receba ainda um Menu Administrador. No caso de Leitores isto nunca será necessário, <br/>
     *             mas para Administradores é importante pois permite voltar a uma janela anterior ao <br/>
     *             clicar no botão "Retroceder" (invisível para leitores), que está apenas disponível <br/>
     *             para eles
     */
    public MenuLeitor(Biblioteca biblioteca, MenuAdmin menu )
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.menuAnterior = menu;
        initComponents();
    }

    private void initComponents()
    {
        jLabel1 = new JLabel();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        jButton8 = new JButton();
        jButton10 = new JButton();
        jButton11 = new JButton();
        jButton1 = new JButton();
        jButton9 = new JButton();

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("MENU DE LEITOR");

        jButton2.setText("REQUISITAR PRODUTO");
        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("DEVOLVER PRODUTO");
        jButton3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("INFORMAÇÕES MENSAIS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("PRODUTOS EM ATRASO");
        jButton5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.setText("IMPRIMIR REQUISICOES ACTUAIS");
        jButton6.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton7.setText("IMPRIMIR ADMINISTRADORES");
        jButton7.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        jButton8.setText("IMPRIMIR LEITORES");
        jButton8.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        jButton9.setText("IMPRIMIR PRODUTOS");
        jButton9.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        jButton10.setText("LOG OUT");
        jButton10.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });

        jButton11.setText("CONSULTAR PRODUTO");
        jButton11.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });

        jButton1.setText("RETROCEDER");
        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        // Existem operações que apenas os administradores podem efectuar. Desse modo, temos de
        // esconder essas mesmas operações quando o utilizador logado é um Leitor
        // O Leitor fica apenas com as opções de: Requisitar Produto, Consultar Produto, Devolver Produto e Imprimir Produtos;
        if ( menuAnterior == null )
        {
            jButton1.setVisible(false);
            jButton4.setVisible(false);
            jButton5.setVisible(false);
            jButton6.setVisible(false);
            jButton7.setVisible(false);
            jButton8.setVisible(false);
        }

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                            .addComponent(jButton2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton11, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)
                    .addComponent(jButton2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton11)
                    .addGap(13, 13, 13)
                    .addComponent(jButton3)
                    .addGap(12, 12, 12)
                    .addComponent(jButton4)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton5)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton6)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton7)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton8)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton9)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton10)
                    .addContainerGap())
        );

        pack();
    }

    /**
     *
     * @param evt Esta função é chamada quando é detectado um clique de rato no botão jButton1 ( Log Out ) <br/>
     *            indicando que o utilizador quer fazer log out
     *            Para tal, abre uma nova janela de login e termina a janela actual
     */
    private void jButton10MouseClicked(java.awt.event.MouseEvent evt)
    {
        new Login(biblioteca).setVisible(true);
        this.dispose();
    }

    // Utilizador pretende imprimir todos os Leitores da Biblioteca
    private void jButton8MouseClicked(java.awt.event.MouseEvent evt)
    {
        new PrintReaders( this.biblioteca ).setVisible(true);
    }

    // Utilizador pretende imprimir todos os Administradores da Biblioteca
    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {
        new PrintAdmin( this.biblioteca ).setVisible(true);
    }

    // Utilizador pretende imprimir todas as requisicoes actuais
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt)
    {
        new PrintRequires( this.biblioteca ).setVisible(true);
    }

    // Utilizador pretende conhecer todos os produtos que se encontram actualmente em atraso
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt)
    {
        new LateProducts( this.biblioteca ).setVisible(true);
    }

    // Utilizador pretende obter informações de requisições para um determinado mês
    //( Produtos Mais Requisitados, Produtos não Requisitados, Estatísticas Mensais )
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)
    {
        this.setVisible(false);
        new InformacoesMensais(this.biblioteca, this.menuAnterior ).setVisible(true);
    }

    // Utilizador pretende devolver um produto
    private void jButton3MouseClicked(MouseEvent evt)
    {
        new DeliverProduct( this.biblioteca ).setVisible(true);
    }

    // Utilizador pretende fazer nova Requisição
    private void jButton2MouseClicked(MouseEvent evt)
    {
        new RequireProduct( this.biblioteca ).setVisible(true);
    }

    // Utilizador pretende consultar um produto
    private void jButton11MouseClicked(MouseEvent evt)
    {
        new ViewProduct( this.biblioteca ).setVisible(true);
    }

    // Utilizador pretende voltar ao menu anterior ( apenas disponível para Administradores )
    private void jButton1MouseClicked(MouseEvent evt)
    {
        this.menuAnterior.setVisible(true);
        this.dispose();
    }

    // Utilizador pretende imprimir todos os produtos existentes na biblioteca
    private void jButton9MouseClicked( MouseEvent evt)
    {
        new PrintProdutos( this.biblioteca );
    }
}

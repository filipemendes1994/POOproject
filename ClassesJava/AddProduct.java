package projecto;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddProduct extends JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1, jButton2;
    private JCheckBox jCheckBox1, jCheckBox2;
    private JLabel jLabel1, jLabel2, jLabel3,jLabel4,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9;
    private JTextField jTextField1,  jTextField2,  jTextField3, jTextField4, jTextField5, jTextField6 ;

    public AddProduct(Biblioteca biblioteca)
    {
        this.biblioteca = biblioteca;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        // TITULOS
        jLabel1 = new JLabel("MENU DE ADMINISTRADOR");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2 = new JLabel("NOVO PRODUTO");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        //

        // CHECK BOX
        jCheckBox1 = new JCheckBox("LIVRO");
        jCheckBox2 = new JCheckBox("DVD");
        jCheckBox1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jCheckBox2MouseClicked(evt);
            }
        });
        jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox2StateChanged(evt);
            }
        });
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        //

        // CAMPOS DE DADOS
        jLabel3 = new JLabel("TITULO");
        jLabel4 = new JLabel("ANO");
        jLabel5 = new JLabel("EDITORA");
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jLabel6 = new JLabel("*Cota");
        jLabel7 = new JLabel("*ISBN");
        jLabel8 = new JLabel("*Autor");
        jLabel9 = new JLabel("(*) Apenas se for livro");
        jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        // BOTÕES
        jButton1 = new JButton("Retroceder");
        jButton2 = new JButton("Confirmar");

        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        //

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox2)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(133, 133, 133))))
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jButton2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                    .addGap(31, 31, 31)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField1)
                                            .addComponent(jTextField2)
                                            .addComponent(jTextField3)
                                            .addComponent(jTextField4, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                            .addComponent(jTextField5, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                            .addComponent(jTextField6, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel9)))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox1))
                    .addGap(16, 16, 16)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(19, 19, 19)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(17, 17, 17)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel9)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addContainerGap())
        );

        pack();
    }

    private void jCheckBox2ActionPerformed(ActionEvent evt) {
        if(jCheckBox2.isSelected())
            jCheckBox1.setSelected(false);
    }

    private void jCheckBox1ActionPerformed(ActionEvent evt) {
        if(jCheckBox1.isSelected())
            jCheckBox2.setSelected(false);
    }

    private void jCheckBox2StateChanged(ChangeEvent evt) {
        if(jCheckBox2.isSelected())
            jCheckBox1.setSelected(false);
    }

    private void jCheckBox1StateChanged(ChangeEvent evt) {
         if(jCheckBox1.isSelected())
            jCheckBox2.setSelected(false);
    }

    private void jCheckBox2MouseClicked(MouseEvent evt) {
        if(jCheckBox2.isSelected())
            jCheckBox1.setSelected(false);
    }

    private void jCheckBox1MouseClicked(MouseEvent evt) {
         if(jCheckBox1.isSelected())
            jCheckBox2.setSelected(false);
    }

    private void jTextField1ActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    // CARREGARAM NO BOTÃO 'CONFIRMAR' , TEMOS DE VERIFICAR OS DADOS INSERIDOS
    private void jButton2MouseClicked(MouseEvent evt)
    {
        String titulo = this.jTextField1.getText(), editora = this.jTextField3.getText();
        String ano = this.jTextField2.getText();

        // se for um livro
        if( jCheckBox1.isSelected() && !jCheckBox2.isSelected())
        {
            String ISBN = this.jTextField5.getText(), cota = this.jTextField4.getText(), autor = this.jTextField6.getText();

            if ( !titulo.isEmpty() && !editora.isEmpty() && !ano.isEmpty() && !ISBN.isEmpty() && !cota.isEmpty() && !autor.isEmpty())
            {
                ((Administrador)this.biblioteca.getUtilizadorActual()).adicionaLivro(titulo,editora,ano,autor,ISBN,cota);
                JOptionPane.showMessageDialog(this,"Dados Inseridos Com Sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            }

            else
                JOptionPane.showMessageDialog(this,"Dados Incompletos","Erro",JOptionPane.ERROR_MESSAGE);

        }

        // se for um DVD
        else
        {
            if ( !titulo.isEmpty() && !editora.isEmpty() && !ano.isEmpty() )
            {
                ((Administrador)this.biblioteca.getUtilizadorActual()).adicionaDVD(titulo,editora,ano);
                JOptionPane.showMessageDialog(this,"Dados Inseridos Com Sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            }

            else
                JOptionPane.showMessageDialog(this,"Dados Incompletos","Erro",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
    }

}

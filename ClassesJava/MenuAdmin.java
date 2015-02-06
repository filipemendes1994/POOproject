/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuAdmin extends javax.swing.JFrame
{
    private Biblioteca biblioteca;
    private JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8;
    private JLabel jLabel1;

    public MenuAdmin( Biblioteca biblioteca )
    {
        this.biblioteca = biblioteca;
        initComponents();
    }

    private void initComponents()
    {
        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        jButton3 = new JButton();
        jButton8 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("MENU DE ADMINISTRADOR");

        jButton1.setText("ADICIONAR PRODUTO");
        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton4.setText("ELIMINAR PRODUTO");
        jButton4.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setText("ADICIONAR ADMINISTRADOR");
        jButton5.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("ADICIONAR LEITOR");
        jButton6.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton3.setText("ELIMINAR ADMINISTRADOR");
        jButton3.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton8.setText("ELIMINAR LEITOR");
        jButton8.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        jButton7.setText("MAIS OPÇÕES");
        jButton7.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton2.setText("LOG OUT");
        jButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked( MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                    .addGap(104, 104, 104)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(100, 100, 100)
                .addComponent(jButton2)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        new AddProduct( this.biblioteca ).setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        this.dispose();
        new MenuLeitor( this.biblioteca, this ).setVisible(true);
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        new AddReader( this.biblioteca).setVisible(true);
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        new AddAdmin( this.biblioteca ).setVisible(true);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
        new DeleteProduct( this.biblioteca ).setVisible(true);
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt)
    {
        new Login(biblioteca).setVisible(true);
        this.dispose();
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt)
    {
        new DeleteAdmin(this.biblioteca);
    }

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt)
    {
        new DeleteLeitor(this.biblioteca);
    }
}

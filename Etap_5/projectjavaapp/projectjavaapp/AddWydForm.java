package projectjavaapp;

import javax.swing.JOptionPane;
import static projectjavaapp.LoginForm.queryToSend;

/**
 *
 * @author CLEVO
 */
public class AddWydForm extends javax.swing.JFrame {
    private LibForm LibF = null;
    
    public AddWydForm(LibForm libF) {
        LibF = libF;
    }

    /** Creates new form AddAutForm */
    public AddWydForm() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNazwaWAW = new javax.swing.JLabel();
        jTextFieldNazwaWAW = new javax.swing.JTextField();
        jLabelMiastoAW = new javax.swing.JLabel();
        jTextFieldMiastoAW = new javax.swing.JTextField();
        jButtonDodajWydawnictwoAW = new javax.swing.JButton();
        jButtonDodajWydawnictwoExt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelNazwaWAW.setText("Nazwa Wydawnictwa");

        jTextFieldNazwaWAW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNazwaWAWActionPerformed(evt);
            }
        });

        jLabelMiastoAW.setText("Miasto");

        jTextFieldMiastoAW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMiastoAWActionPerformed(evt);
            }
        });

        jButtonDodajWydawnictwoAW.setText("DODAJ");
        jButtonDodajWydawnictwoAW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajWydawnictwoAWActionPerformed(evt);
            }
        });

        jButtonDodajWydawnictwoExt.setText("WSTECZ");
        jButtonDodajWydawnictwoExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajWydawnictwoExtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jButtonDodajWydawnictwoExt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDodajWydawnictwoAW, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelNazwaWAW, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelMiastoAW, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldMiastoAW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(jTextFieldNazwaWAW, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNazwaWAW)
                    .addComponent(jTextFieldNazwaWAW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMiastoAW)
                    .addComponent(jTextFieldMiastoAW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDodajWydawnictwoAW, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDodajWydawnictwoExt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNazwaWAWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNazwaWAWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNazwaWAWActionPerformed

    private void jTextFieldMiastoAWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMiastoAWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMiastoAWActionPerformed

    private void jButtonDodajWydawnictwoAWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajWydawnictwoAWActionPerformed
        // TODO add your handling code here:
        String nazwa = jTextFieldNazwaWAW.getText();
        String miasto = jTextFieldMiastoAW.getText();
        
        if(nazwa.trim().equals("") || miasto.trim().equals("")) 
        {       
            JOptionPane.showMessageDialog(null, "Jedno lub więcej pól jest puste","Uzupelnij puste pola",2);
        }
        else{
                queryToSend.add("3");
                queryToSend.add(nazwa);
                
                if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                    queryToSend.add("22");
                    queryToSend.add(nazwa);
                    queryToSend.add(miasto);

                    if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                        JOptionPane.showMessageDialog(null, "Nowe wydawnictwo zostalo dodane do listy.");
                        jTextFieldNazwaWAW.setText("");
                        jTextFieldMiastoAW.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null, "Blad: Sprawdz poprawnosc wprowadzonych danych");
                     }
                } else {
                    JOptionPane.showMessageDialog(null, "Wydawnictwo z taka nazwa juz istnieje","Blad",2);
                }
        }
    }//GEN-LAST:event_jButtonDodajWydawnictwoAWActionPerformed

    private void jButtonDodajWydawnictwoExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajWydawnictwoExtActionPerformed
        // TODO add your handling code here:
        //LibF.initialInitOut();
        this.dispose();
    }//GEN-LAST:event_jButtonDodajWydawnictwoExtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddWydForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddWydForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddWydForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddWydForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddWydForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodajWydawnictwoAW;
    private javax.swing.JButton jButtonDodajWydawnictwoExt;
    private javax.swing.JLabel jLabelMiastoAW;
    private javax.swing.JLabel jLabelNazwaWAW;
    private javax.swing.JTextField jTextFieldMiastoAW;
    private javax.swing.JTextField jTextFieldNazwaWAW;
    // End of variables declaration//GEN-END:variables

}

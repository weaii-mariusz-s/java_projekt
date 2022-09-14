package projectjavaapp;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static projectjavaapp.LoginForm.queryToSend;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author CLEVO
 */
public class LibForm extends javax.swing.JFrame {

    /**
     * Creates new form LibForm
     * @param args
     */
    //SimpleDateFormat dateForm = new SimpleDateFormat("hh:mm:ss dd-M-yy");yyyy-MM-dd HH:mm:ss
    static DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("HH:mm:ss:dd-M-yy");
    static DateTimeFormatter dateUpdate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static String emailUser = null;
    static Color newBlue = new Color(230, 247, 255, 255);
            
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LibForm(emailUser).setVisible(true);
        });
    }
    
    public LibForm(String emailUser) {
        LibForm.emailUser = emailUser;
        initComponents();
        initialInit();
    }

    LibForm() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void cleanTableKsiazki(){
        String[] arrS = {"Nr katalogowy", "Tytuł", "Imię autora", "Nazwisko autora", "Kategoria", "Wydawnictwo", "Rok wydania", "Liczba sztuk"};
         jTableKsiazkiK.setModel(new DefaultTableModel(null, arrS));
    }
    
    private void initialInit(){
        jTextFieldEmail.setText(emailUser);
        
        queryToSend.add("16");        
        Client.readWriteServer(this, queryToSend, null, jComboBoxAutorK);
        
        queryToSend.add("17");        
        Client.readWriteServer(this, queryToSend, null, jComboBoxWydawnictwoK); 
        
        queryToSend.add("18");        
        Client.readWriteServer(this, queryToSend, null, jComboBoxKategoriaK);
        
        jPanelMenuWZW.setVisible(false);
        jPanelDetailsWZW.setVisible(false);
    }
    
    public void initialInitOut(){       
        queryToSend.add("17");        
        Client.readWriteServer(this, queryToSend, null, jComboBoxWydawnictwoK);
    }

    public void searchTable(String sear, String text, String que, JTable table){
    if(sear.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Pole do wyszukiwania jest puste.","Uzupelnij puste pole",2);
        }
        else{
            queryToSend.add(que);
            queryToSend.add(text);
            queryToSend.add(sear);
            
            Client.readWriteServer(this, queryToSend, table, null);
        }
    }
    private void searchParamTable(String sear, String text, int numA, String que, JTable tableF, JTable table){
    if(sear.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Pole do wyszukiwania jest puste.","Uzupelnij puste pole",2);
        }
        else{
            int indexArr = tableF.getSelectedRow();
            TableModel model = tableF.getModel();

            String parm = (model.getValueAt(indexArr, numA).toString());
            
            queryToSend.add(que);
            queryToSend.add(parm);
            queryToSend.add(text);
            queryToSend.add(sear);
            
            Client.readWriteServer(this, queryToSend, table, null);
        }
    }
    
    private void dateGained(JTextField field, String dateV){
    String dateValue = field.getText().trim().toLowerCase();

        if(dateValue.equals(dateV))
        {
            field.setText("");
            field.setForeground(Color.black);
        }
    }
    
    private void dateLost(JTextField field, String dateV){
    String dateValue = field.getText().trim().toLowerCase();

        if(dateValue.equals(dateV) || dateValue.equals(""))
        {
            field.setText(dateV);
            field.setForeground(Color.gray);
        }
    }
    
    private boolean isCorrectYear(String string) {
    int intValue;
    LocalDate now = LocalDate.now();

    if(string == null || string.equals("") || string.equals("rrrr")) {
        return false;
    }   
    try {
        intValue = Integer.parseInt(string);
        if(intValue > 0 && intValue <= now.getYear()){
            return true;
        }      
    } catch (NumberFormatException e) {
    }
    return false;
}
    
    private boolean isCorrectFullDate(String string){
    boolean isValidFormat = string.matches("([0-9]{2})-([0-1][0-9])-([0-9]{4})");
    return isValidFormat;
  }
    
    public void sendLog(String logInfo, JTextArea textArea){
                    LocalDateTime date = LocalDateTime.now();
                    queryToSend.add("60");      //sendLog
                    queryToSend.add(date.format(dateForm));
                    queryToSend.add(logInfo);
                     if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                         textArea.append(date.format(dateForm) + ": " + logInfo + "\n");
                     }
                }
    
    private void readTableWZW(JTable table, String type, String que){
        int indexArr = jTableFastWZW.getSelectedRow();
        TableModel model2 = jTableFastWZW.getModel();
                
        String emailCzyt = (model2.getValueAt(indexArr, 2).toString());
        queryToSend.add(que);
        queryToSend.add(emailCzyt);
        queryToSend.add(type);
        Client.readWriteServer(this, queryToSend, table, null);
  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPanelBibliotekarz = new javax.swing.JTabbedPane();
        jPanelKsiazki = new javax.swing.JPanel();
        jPanelMenuK = new javax.swing.JPanel();
        jLabelTytul = new javax.swing.JLabel();
        jTextFieldTytulK = new javax.swing.JTextField();
        jLabelAutor = new javax.swing.JLabel();
        jComboBoxAutorK = new javax.swing.JComboBox<>();
        jLabelKategoria = new javax.swing.JLabel();
        jButtonUDodajK = new javax.swing.JButton();
        jButtonAktualizujK = new javax.swing.JButton();
        jButtonUsunK = new javax.swing.JButton();
        jComboBoxKategoriaK = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButtonRokSK = new javax.swing.JButton();
        jButtonKategoriaSK = new javax.swing.JButton();
        jButtonAutorSK = new javax.swing.JButton();
        jButtonTytulSK = new javax.swing.JButton();
        jButtonWydawnictwoSK = new javax.swing.JButton();
        jLabelWydawnictwo = new javax.swing.JLabel();
        jComboBoxWydawnictwoK = new javax.swing.JComboBox<>();
        jButtonWydawnictwo = new javax.swing.JButton();
        jLabelRokWydania = new javax.swing.JLabel();
        jTextFieldRokWydaniaK = new javax.swing.JTextField();
        jLabelIlosc = new javax.swing.JLabel();
        jTextFieldIloscK = new javax.swing.JTextField();
        jButtonWyswielK = new javax.swing.JButton();
        jPanelConsoleK = new javax.swing.JPanel();
        jScrollPaneDB_Console3 = new javax.swing.JScrollPane();
        jTextAreaS = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPaneTableB = new javax.swing.JScrollPane();
        jTableKsiazkiK = new javax.swing.JTable();
        jPanelAutorzy = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPaneTableB5 = new javax.swing.JScrollPane();
        jTableAutorzyA = new javax.swing.JTable();
        jPanelMenuK3 = new javax.swing.JPanel();
        jLabelImieA = new javax.swing.JLabel();
        jTextFieldImieA = new javax.swing.JTextField();
        jLabelNazwiskoA = new javax.swing.JLabel();
        jLabelMiastoA = new javax.swing.JLabel();
        jButtonUDodajS = new javax.swing.JButton();
        jButtonAktualizujS = new javax.swing.JButton();
        jButtonUsunS = new javax.swing.JButton();
        jButtonDataSmA = new javax.swing.JButton();
        jButtonDataUrA = new javax.swing.JButton();
        jButtonMiastoA = new javax.swing.JButton();
        jButtonNazwiskoA = new javax.swing.JButton();
        jButtonImieA = new javax.swing.JButton();
        jButtonKrajA = new javax.swing.JButton();
        jLabelKrajA = new javax.swing.JLabel();
        jLabelDataUrA = new javax.swing.JLabel();
        jTextFieldDataUrA = new javax.swing.JTextField();
        jLabelDataSmA = new javax.swing.JLabel();
        jTextFieldIDataSmA = new javax.swing.JTextField();
        jButtonWyswielS = new javax.swing.JButton();
        jTextFieldINazwiskoA = new javax.swing.JTextField();
        jTextFieldIMiastoA = new javax.swing.JTextField();
        jTextFieldIKrajA = new javax.swing.JTextField();
        jPanelConsoleK1 = new javax.swing.JPanel();
        jScrollPaneDB_Console5 = new javax.swing.JScrollPane();
        jTextAreaK = new javax.swing.JTextArea();
        jPanelCzytelnicy = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPaneTableB6 = new javax.swing.JScrollPane();
        jTableCzytelnicyCz = new javax.swing.JTable();
        jPanelMenuK6 = new javax.swing.JPanel();
        jLabelImieC = new javax.swing.JLabel();
        jTextFieldImieC = new javax.swing.JTextField();
        jLabelNazwiskoC = new javax.swing.JLabel();
        jLabelTelefonC = new javax.swing.JLabel();
        jButtonAktualizujC = new javax.swing.JButton();
        jButtonUsunC = new javax.swing.JButton();
        jButtonTelefonC = new javax.swing.JButton();
        jButtonNazwiskoC = new javax.swing.JButton();
        jButtonImieC = new javax.swing.JButton();
        jButtonKrajA3 = new javax.swing.JButton();
        jLabelEmailC = new javax.swing.JLabel();
        jButtonWyswielC = new javax.swing.JButton();
        jTextFieldINazwiskoC = new javax.swing.JTextField();
        jTextFieldITelefonC = new javax.swing.JTextField();
        jTextFieldIEmailC = new javax.swing.JTextField();
        jPanelConsoleK2 = new javax.swing.JPanel();
        jScrollPaneDB_Console8 = new javax.swing.JScrollPane();
        jTextAreaC = new javax.swing.JTextArea();
        jPanelWZW = new javax.swing.JPanel();
        jPanelMenuK7 = new javax.swing.JPanel();
        jLabelImieWZW = new javax.swing.JLabel();
        jTextFieldImieWZW = new javax.swing.JTextField();
        jLabelNazwiskoC1 = new javax.swing.JLabel();
        jButtonWyszukajWZW = new javax.swing.JButton();
        jButtonEmailWZW = new javax.swing.JButton();
        jButtonNazwiskoWZW = new javax.swing.JButton();
        jButtonImieWZW = new javax.swing.JButton();
        jLabelEmailWZW = new javax.swing.JLabel();
        jButtonWyswielWZW = new javax.swing.JButton();
        jTextFieldINazwiskoWZW = new javax.swing.JTextField();
        jTextFieldIEmailWZW = new javax.swing.JTextField();
        jPanelConsoleK11 = new javax.swing.JPanel();
        jScrollPaneDB_Console17 = new javax.swing.JScrollPane();
        jTextAreaWZW = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableFastWZW = new javax.swing.JTable();
        jPanelMenuWZW = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanelMenuKWZW = new javax.swing.JPanel();
        jLabelImieWZW1 = new javax.swing.JLabel();
        jTextFieldTytulWZW = new javax.swing.JTextField();
        jLabelNazwiskoC2 = new javax.swing.JLabel();
        jButtonNazwiskoWZW1 = new javax.swing.JButton();
        jButtonITytulWZW = new javax.swing.JButton();
        jButtonWyswielKsiazkiWZW = new javax.swing.JButton();
        jComboBoxAutorWZW = new javax.swing.JComboBox<>();
        jButtonWypozyczWZW = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableWypozyczeniaKsiazkiWZW = new javax.swing.JTable();
        jPanelListaZyczen = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableWypozyczeniaListaZyczenWZW = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelWypozyczenia = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableWypozyczeniaWZW = new javax.swing.JTable();
        jPanelMenuKWZW1 = new javax.swing.JPanel();
        jLabelImieWZW2 = new javax.swing.JLabel();
        jTextFieldTytulWWZW = new javax.swing.JTextField();
        jLabelNazwiskoC3 = new javax.swing.JLabel();
        jButtonZwrocWZW = new javax.swing.JButton();
        jButtonDataWWZW = new javax.swing.JButton();
        jButtonTytulWWZW = new javax.swing.JButton();
        jButtonWyswielWWZW = new javax.swing.JButton();
        jTextFieldDatalWWZW = new javax.swing.JTextField();
        jPanelZwroty = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableZwrotyWZW = new javax.swing.JTable();
        jPanelMenuKWZW2 = new javax.swing.JPanel();
        jLabelImieWZW3 = new javax.swing.JLabel();
        jTextFieldTytulZWZW = new javax.swing.JTextField();
        jLabelNazwiskoC4 = new javax.swing.JLabel();
        jButtonDataZWZW = new javax.swing.JButton();
        jButtonTytulZWZW = new javax.swing.JButton();
        jButtonWyswielZWZW = new javax.swing.JButton();
        jTextFieldDataZWZW = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanelDetailsWZW = new javax.swing.JPanel();
        jLabelSzczegolyInfo = new javax.swing.JLabel();
        jLabelSzczegolyOpl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPanelBibliotekarz.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPanelBibliotekarz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPanelBibliotekarzMouseClicked(evt);
            }
        });

        jPanelKsiazki.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelKsiazkiComponentShown(evt);
            }
        });

        jPanelMenuK.setBorder(javax.swing.BorderFactory.createTitledBorder("Dodaj/Usuń"));

        jLabelTytul.setText("Tytuł");

        jTextFieldTytulK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTytulKActionPerformed(evt);
            }
        });

        jLabelAutor.setText("Autor");

        jComboBoxAutorK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxAutorKItemStateChanged(evt);
            }
        });
        jComboBoxAutorK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAutorKActionPerformed(evt);
            }
        });

        jLabelKategoria.setText("Kategoria");

        jButtonUDodajK.setText("DODAJ");
        jButtonUDodajK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUDodajKActionPerformed(evt);
            }
        });

        jButtonAktualizujK.setText("AKTUALIZUJ");
        jButtonAktualizujK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonAktualizujKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonAktualizujKMouseExited(evt);
            }
        });
        jButtonAktualizujK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAktualizujKActionPerformed(evt);
            }
        });

        jButtonUsunK.setText("USUŃ");
        jButtonUsunK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonUsunKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonUsunKMouseExited(evt);
            }
        });
        jButtonUsunK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsunKActionPerformed(evt);
            }
        });

        jComboBoxKategoriaK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxKategoriaKItemStateChanged(evt);
            }
        });
        jComboBoxKategoriaK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxKategoriaKActionPerformed(evt);
            }
        });

        jButton2.setText("jButton1");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonRokSK.setText("jButton1");
        jButtonRokSK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonRokSKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonRokSKMouseExited(evt);
            }
        });
        jButtonRokSK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRokSKActionPerformed(evt);
            }
        });

        jButtonKategoriaSK.setText("jButton1");
        jButtonKategoriaSK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonKategoriaSKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonKategoriaSKMouseExited(evt);
            }
        });
        jButtonKategoriaSK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKategoriaSKActionPerformed(evt);
            }
        });

        jButtonAutorSK.setText("jButton1");
        jButtonAutorSK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonAutorSKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonAutorSKMouseExited(evt);
            }
        });
        jButtonAutorSK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutorSKActionPerformed(evt);
            }
        });

        jButtonTytulSK.setText("jButton1");
        jButtonTytulSK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonTytulSKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonTytulSKMouseExited(evt);
            }
        });
        jButtonTytulSK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTytulSKActionPerformed(evt);
            }
        });

        jButtonWydawnictwoSK.setText("jButton1");
        jButtonWydawnictwoSK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonWydawnictwoSKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonWydawnictwoSKMouseExited(evt);
            }
        });
        jButtonWydawnictwoSK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWydawnictwoSKActionPerformed(evt);
            }
        });

        jLabelWydawnictwo.setText("Wydawnictwo");

        jComboBoxWydawnictwoK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxWydawnictwoKItemStateChanged(evt);
            }
        });
        jComboBoxWydawnictwoK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWydawnictwoKActionPerformed(evt);
            }
        });

        jButtonWydawnictwo.setText("jButton1");
        jButtonWydawnictwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWydawnictwoActionPerformed(evt);
            }
        });

        jLabelRokWydania.setText("Rok wydania");

        jTextFieldRokWydaniaK.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldRokWydaniaK.setText("rrrr");
        jTextFieldRokWydaniaK.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldRokWydaniaKFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldRokWydaniaKFocusLost(evt);
            }
        });
        jTextFieldRokWydaniaK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRokWydaniaKActionPerformed(evt);
            }
        });

        jLabelIlosc.setText("Ilość");

        jTextFieldIloscK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIloscKActionPerformed(evt);
            }
        });

        jButtonWyswielK.setText("W. WSZYSTKIE");
        jButtonWyswielK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuKLayout = new javax.swing.GroupLayout(jPanelMenuK);
        jPanelMenuK.setLayout(jPanelMenuKLayout);
        jPanelMenuKLayout.setHorizontalGroup(
            jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuKLayout.createSequentialGroup()
                        .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelIlosc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTytul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelAutor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelKategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelWydawnictwo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelRokWydania, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldRokWydaniaK, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuKLayout.createSequentialGroup()
                                .addComponent(jComboBoxWydawnictwoK, 0, 94, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonWydawnictwo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxKategoriaK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxAutorK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldTytulK)
                            .addComponent(jTextFieldIloscK))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonTytulSK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAutorSK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonKategoriaSK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonWydawnictwoSK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRokSK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMenuKLayout.createSequentialGroup()
                        .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonUDodajK, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jButtonWyswielK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonAktualizujK, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jButtonUsunK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMenuKLayout.setVerticalGroup(
            jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuKLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTytul)
                    .addComponent(jTextFieldTytulK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTytulSK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAutor)
                    .addComponent(jComboBoxAutorK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAutorSK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelKategoria)
                    .addComponent(jComboBoxKategoriaK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKategoriaSK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxWydawnictwoK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelWydawnictwo)
                        .addComponent(jButtonWydawnictwoSK)
                        .addComponent(jButtonWydawnictwo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRokWydania)
                    .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldRokWydaniaK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonRokSK)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIlosc)
                    .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldIloscK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUDodajK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAktualizujK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUsunK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonWyswielK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelConsoleK.setBorder(javax.swing.BorderFactory.createTitledBorder("DB Console"));

        jTextAreaS.setEditable(false);
        jTextAreaS.setColumns(20);
        jTextAreaS.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextAreaS.setRows(5);
        jTextAreaS.setToolTipText("");
        jTextAreaS.setDoubleBuffered(true);
        jScrollPaneDB_Console3.setViewportView(jTextAreaS);

        javax.swing.GroupLayout jPanelConsoleKLayout = new javax.swing.GroupLayout(jPanelConsoleK);
        jPanelConsoleK.setLayout(jPanelConsoleKLayout);
        jPanelConsoleKLayout.setHorizontalGroup(
            jPanelConsoleKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneDB_Console3)
        );
        jPanelConsoleKLayout.setVerticalGroup(
            jPanelConsoleKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneDB_Console3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableKsiazkiK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nr Katalogowy", "Tytuł", "Imię autora", "Nazwisko autora", "Kategoria", "Wydawnictwo", "Rok wydania", "Liczba sztuk"
            }
        ));
        jTableKsiazkiK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKsiazkiKMouseClicked(evt);
            }
        });
        jScrollPaneTableB.setViewportView(jTableKsiazkiK);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTableB, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTableB, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanelKsiazkiLayout = new javax.swing.GroupLayout(jPanelKsiazki);
        jPanelKsiazki.setLayout(jPanelKsiazkiLayout);
        jPanelKsiazkiLayout.setHorizontalGroup(
            jPanelKsiazkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKsiazkiLayout.createSequentialGroup()
                .addGroup(jPanelKsiazkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelConsoleK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMenuK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelKsiazkiLayout.setVerticalGroup(
            jPanelKsiazkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKsiazkiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelKsiazkiLayout.createSequentialGroup()
                .addComponent(jPanelMenuK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(jPanelConsoleK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPanelBibliotekarz.addTab("Książki", jPanelKsiazki);

        jPanelAutorzy.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAutorzyComponentShown(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableAutorzyA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numer ID", "Imię", "Nazwisko", "Miasto", "Kraj pochodzenia", "Data urodzenia", "Data śmierci"
            }
        ));
        jTableAutorzyA.setToolTipText("");
        jTableAutorzyA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAutorzyAMouseClicked(evt);
            }
        });
        jScrollPaneTableB5.setViewportView(jTableAutorzyA);
        if (jTableAutorzyA.getColumnModel().getColumnCount() > 0) {
            jTableAutorzyA.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTableB5, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTableB5, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanelMenuK3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dodaj/Usuń"));

        jLabelImieA.setText("Imię");

        jTextFieldImieA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldImieAActionPerformed(evt);
            }
        });

        jLabelNazwiskoA.setText("Nazwisko");

        jLabelMiastoA.setText("Miasto");

        jButtonUDodajS.setText("DODAJ");
        jButtonUDodajS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUDodajSActionPerformed(evt);
            }
        });

        jButtonAktualizujS.setText("AKTUALIZUJ");
        jButtonAktualizujS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonAktualizujSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonAktualizujSMouseExited(evt);
            }
        });
        jButtonAktualizujS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAktualizujSActionPerformed(evt);
            }
        });

        jButtonUsunS.setText("USUŃ");
        jButtonUsunS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonUsunSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonUsunSMouseExited(evt);
            }
        });
        jButtonUsunS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsunSActionPerformed(evt);
            }
        });

        jButtonDataSmA.setText("jButton1");
        jButtonDataSmA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonDataSmAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonDataSmAMouseExited(evt);
            }
        });
        jButtonDataSmA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDataSmAActionPerformed(evt);
            }
        });

        jButtonDataUrA.setText("jButton1");
        jButtonDataUrA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonDataUrAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonDataUrAMouseExited(evt);
            }
        });
        jButtonDataUrA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDataUrAActionPerformed(evt);
            }
        });

        jButtonMiastoA.setText("jButton1");
        jButtonMiastoA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonMiastoAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonMiastoAMouseExited(evt);
            }
        });
        jButtonMiastoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMiastoAActionPerformed(evt);
            }
        });

        jButtonNazwiskoA.setText("jButton1");
        jButtonNazwiskoA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonNazwiskoAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonNazwiskoAMouseExited(evt);
            }
        });
        jButtonNazwiskoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNazwiskoAActionPerformed(evt);
            }
        });

        jButtonImieA.setText("jButton1");
        jButtonImieA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonImieAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonImieAMouseExited(evt);
            }
        });
        jButtonImieA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImieAActionPerformed(evt);
            }
        });

        jButtonKrajA.setText("jButton1");
        jButtonKrajA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonKrajAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonKrajAMouseExited(evt);
            }
        });
        jButtonKrajA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKrajAActionPerformed(evt);
            }
        });

        jLabelKrajA.setText("Kraj");

        jLabelDataUrA.setText("Data urodzenia");

        jTextFieldDataUrA.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldDataUrA.setText("dd-mm-rrrr");
        jTextFieldDataUrA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDataUrAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDataUrAFocusLost(evt);
            }
        });
        jTextFieldDataUrA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataUrAActionPerformed(evt);
            }
        });

        jLabelDataSmA.setText("Data śmierci");

        jTextFieldIDataSmA.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldIDataSmA.setText("dd-mm-rrrr");
        jTextFieldIDataSmA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldIDataSmAFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDataSmAFocusLost(evt);
            }
        });
        jTextFieldIDataSmA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDataSmAActionPerformed(evt);
            }
        });

        jButtonWyswielS.setText("W. WSZYSTKICH");
        jButtonWyswielS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielSActionPerformed(evt);
            }
        });

        jTextFieldINazwiskoA.setDisabledTextColor(new java.awt.Color(230, 247, 255));
        jTextFieldINazwiskoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldINazwiskoAActionPerformed(evt);
            }
        });

        jTextFieldIMiastoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIMiastoAActionPerformed(evt);
            }
        });

        jTextFieldIKrajA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIKrajAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuK3Layout = new javax.swing.GroupLayout(jPanelMenuK3);
        jPanelMenuK3.setLayout(jPanelMenuK3Layout);
        jPanelMenuK3Layout.setHorizontalGroup(
            jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuK3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuK3Layout.createSequentialGroup()
                        .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelDataSmA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelImieA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNazwiskoA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelMiastoA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelKrajA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelDataUrA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldDataUrA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jTextFieldImieA)
                            .addComponent(jTextFieldIDataSmA)
                            .addComponent(jTextFieldINazwiskoA)
                            .addComponent(jTextFieldIMiastoA)
                            .addComponent(jTextFieldIKrajA))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonImieA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonNazwiskoA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonMiastoA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonKrajA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDataUrA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDataSmA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMenuK3Layout.createSequentialGroup()
                        .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonUDodajS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonWyswielS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonAktualizujS, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jButtonUsunS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelMenuK3Layout.setVerticalGroup(
            jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuK3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImieA)
                    .addComponent(jTextFieldImieA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImieA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNazwiskoA)
                    .addComponent(jButtonNazwiskoA)
                    .addComponent(jTextFieldINazwiskoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMiastoA)
                    .addComponent(jButtonMiastoA)
                    .addComponent(jTextFieldIMiastoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelKrajA)
                    .addComponent(jButtonKrajA)
                    .addComponent(jTextFieldIKrajA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDataUrA)
                    .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldDataUrA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonDataUrA)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDataSmA)
                    .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonDataSmA, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldIDataSmA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUDodajS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAktualizujS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUsunS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonWyswielS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelConsoleK1.setBorder(javax.swing.BorderFactory.createTitledBorder("DB Console"));

        jTextAreaK.setEditable(false);
        jTextAreaK.setColumns(20);
        jTextAreaK.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextAreaK.setRows(5);
        jTextAreaK.setToolTipText("");
        jTextAreaK.setDoubleBuffered(true);
        jScrollPaneDB_Console5.setViewportView(jTextAreaK);

        javax.swing.GroupLayout jPanelConsoleK1Layout = new javax.swing.GroupLayout(jPanelConsoleK1);
        jPanelConsoleK1.setLayout(jPanelConsoleK1Layout);
        jPanelConsoleK1Layout.setHorizontalGroup(
            jPanelConsoleK1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneDB_Console5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelConsoleK1Layout.setVerticalGroup(
            jPanelConsoleK1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneDB_Console5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelAutorzyLayout = new javax.swing.GroupLayout(jPanelAutorzy);
        jPanelAutorzy.setLayout(jPanelAutorzyLayout);
        jPanelAutorzyLayout.setHorizontalGroup(
            jPanelAutorzyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAutorzyLayout.createSequentialGroup()
                .addGroup(jPanelAutorzyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMenuK3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelConsoleK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAutorzyLayout.setVerticalGroup(
            jPanelAutorzyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAutorzyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelAutorzyLayout.createSequentialGroup()
                .addComponent(jPanelMenuK3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addComponent(jPanelConsoleK1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPanelBibliotekarz.addTab("Autorzy", jPanelAutorzy);

        jPanelCzytelnicy.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelCzytelnicyComponentShown(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableCzytelnicyCz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numer ID", "Imię", "Nazwisko", "Data urodzenia", "Płeć", "Telefon", "Email"
            }
        ));
        jTableCzytelnicyCz.setToolTipText("");
        jTableCzytelnicyCz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCzytelnicyCzMouseClicked(evt);
            }
        });
        jScrollPaneTableB6.setViewportView(jTableCzytelnicyCz);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTableB6, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTableB6, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanelMenuK6.setBorder(javax.swing.BorderFactory.createTitledBorder("Dodaj/Usuń"));

        jLabelImieC.setText("Imię");

        jTextFieldImieC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldImieCActionPerformed(evt);
            }
        });

        jLabelNazwiskoC.setText("Nazwisko");

        jLabelTelefonC.setText("Telefon");

        jButtonAktualizujC.setText("AKTUALIZUJ");
        jButtonAktualizujC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonAktualizujCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonAktualizujCMouseExited(evt);
            }
        });
        jButtonAktualizujC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAktualizujCActionPerformed(evt);
            }
        });

        jButtonUsunC.setText("USUŃ");
        jButtonUsunC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonUsunCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonUsunCMouseExited(evt);
            }
        });
        jButtonUsunC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsunCActionPerformed(evt);
            }
        });

        jButtonTelefonC.setText("jButton1");
        jButtonTelefonC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonTelefonCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonTelefonCMouseExited(evt);
            }
        });
        jButtonTelefonC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTelefonCActionPerformed(evt);
            }
        });

        jButtonNazwiskoC.setText("jButton1");
        jButtonNazwiskoC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonNazwiskoCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonNazwiskoCMouseExited(evt);
            }
        });
        jButtonNazwiskoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNazwiskoCActionPerformed(evt);
            }
        });

        jButtonImieC.setText("jButton1");
        jButtonImieC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonImieCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonImieCMouseExited(evt);
            }
        });
        jButtonImieC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImieCActionPerformed(evt);
            }
        });

        jButtonKrajA3.setText("jButton1");
        jButtonKrajA3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonKrajA3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonKrajA3MouseExited(evt);
            }
        });
        jButtonKrajA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKrajA3ActionPerformed(evt);
            }
        });

        jLabelEmailC.setText("Email");

        jButtonWyswielC.setText("W. WSZYSTKICH");
        jButtonWyswielC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielCActionPerformed(evt);
            }
        });

        jTextFieldINazwiskoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldINazwiskoCActionPerformed(evt);
            }
        });

        jTextFieldITelefonC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldITelefonCActionPerformed(evt);
            }
        });

        jTextFieldIEmailC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIEmailCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuK6Layout = new javax.swing.GroupLayout(jPanelMenuK6);
        jPanelMenuK6.setLayout(jPanelMenuK6Layout);
        jPanelMenuK6Layout.setHorizontalGroup(
            jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuK6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuK6Layout.createSequentialGroup()
                        .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelImieC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNazwiskoC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(jLabelTelefonC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelEmailC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldImieC, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jTextFieldINazwiskoC)
                            .addComponent(jTextFieldITelefonC)
                            .addComponent(jTextFieldIEmailC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonImieC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonNazwiskoC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonTelefonC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonKrajA3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMenuK6Layout.createSequentialGroup()
                        .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonUsunC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonWyswielC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)
                        .addComponent(jButtonAktualizujC, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMenuK6Layout.setVerticalGroup(
            jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuK6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImieC)
                    .addComponent(jTextFieldImieC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImieC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNazwiskoC)
                    .addComponent(jButtonNazwiskoC)
                    .addComponent(jTextFieldINazwiskoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefonC)
                    .addComponent(jButtonTelefonC)
                    .addComponent(jTextFieldITelefonC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmailC)
                    .addComponent(jButtonKrajA3)
                    .addComponent(jTextFieldIEmailC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelMenuK6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAktualizujC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonWyswielC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUsunC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelConsoleK2.setBorder(javax.swing.BorderFactory.createTitledBorder("DB Console"));

        jTextAreaC.setEditable(false);
        jTextAreaC.setColumns(20);
        jTextAreaC.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextAreaC.setRows(5);
        jTextAreaC.setToolTipText("");
        jTextAreaC.setDoubleBuffered(true);
        jScrollPaneDB_Console8.setViewportView(jTextAreaC);

        javax.swing.GroupLayout jPanelConsoleK2Layout = new javax.swing.GroupLayout(jPanelConsoleK2);
        jPanelConsoleK2.setLayout(jPanelConsoleK2Layout);
        jPanelConsoleK2Layout.setHorizontalGroup(
            jPanelConsoleK2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneDB_Console8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelConsoleK2Layout.setVerticalGroup(
            jPanelConsoleK2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneDB_Console8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelCzytelnicyLayout = new javax.swing.GroupLayout(jPanelCzytelnicy);
        jPanelCzytelnicy.setLayout(jPanelCzytelnicyLayout);
        jPanelCzytelnicyLayout.setHorizontalGroup(
            jPanelCzytelnicyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCzytelnicyLayout.createSequentialGroup()
                .addGroup(jPanelCzytelnicyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMenuK6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelConsoleK2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCzytelnicyLayout.setVerticalGroup(
            jPanelCzytelnicyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCzytelnicyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelCzytelnicyLayout.createSequentialGroup()
                .addComponent(jPanelMenuK6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addComponent(jPanelConsoleK2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPanelBibliotekarz.addTab("Czytelnicy", jPanelCzytelnicy);

        jPanelWZW.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelWZWComponentShown(evt);
            }
        });

        jPanelMenuK7.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj czytelnika"));

        jLabelImieWZW.setText("Imię");

        jTextFieldImieWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldImieWZWActionPerformed(evt);
            }
        });

        jLabelNazwiskoC1.setText("Nazwisko");

        jButtonWyszukajWZW.setText("WYSZUKAJ");
        jButtonWyszukajWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonWyszukajWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonWyszukajWZWMouseExited(evt);
            }
        });
        jButtonWyszukajWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyszukajWZWActionPerformed(evt);
            }
        });

        jButtonEmailWZW.setText("jButton1");
        jButtonEmailWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonEmailWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonEmailWZWMouseExited(evt);
            }
        });
        jButtonEmailWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmailWZWActionPerformed(evt);
            }
        });

        jButtonNazwiskoWZW.setText("jButton1");
        jButtonNazwiskoWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonNazwiskoWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonNazwiskoWZWMouseExited(evt);
            }
        });
        jButtonNazwiskoWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNazwiskoWZWActionPerformed(evt);
            }
        });

        jButtonImieWZW.setText("jButton1");
        jButtonImieWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonImieWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonImieWZWMouseExited(evt);
            }
        });
        jButtonImieWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImieWZWActionPerformed(evt);
            }
        });

        jLabelEmailWZW.setText("Email");

        jButtonWyswielWZW.setText("W. WSZYSTKICH");
        jButtonWyswielWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielWZWActionPerformed(evt);
            }
        });

        jTextFieldINazwiskoWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldINazwiskoWZWActionPerformed(evt);
            }
        });

        jTextFieldIEmailWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIEmailWZWActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuK7Layout = new javax.swing.GroupLayout(jPanelMenuK7);
        jPanelMenuK7.setLayout(jPanelMenuK7Layout);
        jPanelMenuK7Layout.setHorizontalGroup(
            jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuK7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuK7Layout.createSequentialGroup()
                        .addGroup(jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelImieWZW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNazwiskoC1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(jLabelEmailWZW, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldImieWZW, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jTextFieldINazwiskoWZW)
                            .addComponent(jTextFieldIEmailWZW))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonImieWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonNazwiskoWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEmailWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMenuK7Layout.createSequentialGroup()
                        .addComponent(jButtonWyswielWZW)
                        .addGap(11, 11, 11)
                        .addComponent(jButtonWyszukajWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMenuK7Layout.setVerticalGroup(
            jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuK7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImieWZW)
                    .addComponent(jTextFieldImieWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImieWZW))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNazwiskoC1)
                    .addComponent(jButtonNazwiskoWZW)
                    .addComponent(jTextFieldINazwiskoWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEmailWZW)
                    .addComponent(jTextFieldIEmailWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEmailWZW))
                .addGap(6, 6, 6)
                .addGroup(jPanelMenuK7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonWyszukajWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonWyswielWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelConsoleK11.setBorder(javax.swing.BorderFactory.createTitledBorder("DB Console"));

        jTextAreaWZW.setEditable(false);
        jTextAreaWZW.setColumns(20);
        jTextAreaWZW.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextAreaWZW.setRows(5);
        jTextAreaWZW.setToolTipText("");
        jTextAreaWZW.setDoubleBuffered(true);
        jScrollPaneDB_Console17.setViewportView(jTextAreaWZW);

        javax.swing.GroupLayout jPanelConsoleK11Layout = new javax.swing.GroupLayout(jPanelConsoleK11);
        jPanelConsoleK11.setLayout(jPanelConsoleK11Layout);
        jPanelConsoleK11Layout.setHorizontalGroup(
            jPanelConsoleK11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneDB_Console17, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelConsoleK11Layout.setVerticalGroup(
            jPanelConsoleK11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneDB_Console17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        jTableFastWZW.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Imię", "Nazwisko", "Email"
            }
        ));
        jTableFastWZW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableFastWZWFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableFastWZWFocusLost(evt);
            }
        });
        jTableFastWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFastWZWMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTableFastWZWMouseExited(evt);
            }
        });
        jTableFastWZW.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFastWZWPropertyChange(evt);
            }
        });
        jTableFastWZW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableFastWZWKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTableFastWZW);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanelMenuWZW.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Książki"));

        jPanelMenuKWZW.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj"));

        jLabelImieWZW1.setText("Tytuł");

        jTextFieldTytulWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTytulWZWActionPerformed(evt);
            }
        });

        jLabelNazwiskoC2.setText("Autor");

        jButtonNazwiskoWZW1.setText("jButton1");
        jButtonNazwiskoWZW1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonNazwiskoWZW1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonNazwiskoWZW1MouseExited(evt);
            }
        });
        jButtonNazwiskoWZW1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNazwiskoWZW1ActionPerformed(evt);
            }
        });

        jButtonITytulWZW.setText("jButton1");
        jButtonITytulWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonITytulWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonITytulWZWMouseExited(evt);
            }
        });
        jButtonITytulWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonITytulWZWActionPerformed(evt);
            }
        });

        jButtonWyswielKsiazkiWZW.setText("W. WSZYSTKIE");
        jButtonWyswielKsiazkiWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielKsiazkiWZWActionPerformed(evt);
            }
        });

        jComboBoxAutorWZW.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxAutorWZWItemStateChanged(evt);
            }
        });
        jComboBoxAutorWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAutorWZWActionPerformed(evt);
            }
        });

        jButtonWypozyczWZW.setText("WYPOŻYCZ");
        jButtonWypozyczWZW.setEnabled(false);
        jButtonWypozyczWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonWypozyczWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonWypozyczWZWMouseExited(evt);
            }
        });
        jButtonWypozyczWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWypozyczWZWActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuKWZWLayout = new javax.swing.GroupLayout(jPanelMenuKWZW);
        jPanelMenuKWZW.setLayout(jPanelMenuKWZWLayout);
        jPanelMenuKWZWLayout.setHorizontalGroup(
            jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuKWZWLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuKWZWLayout.createSequentialGroup()
                        .addGroup(jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelImieWZW1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNazwiskoC2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldTytulWZW)
                            .addComponent(jComboBoxAutorWZW, 0, 147, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNazwiskoWZW1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonITytulWZW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMenuKWZWLayout.createSequentialGroup()
                        .addComponent(jButtonWyswielKsiazkiWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonWypozyczWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMenuKWZWLayout.setVerticalGroup(
            jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuKWZWLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImieWZW1)
                    .addComponent(jTextFieldTytulWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonITytulWZW))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNazwiskoC2)
                    .addComponent(jButtonNazwiskoWZW1)
                    .addComponent(jComboBoxAutorWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelMenuKWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonWyswielKsiazkiWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonWypozyczWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jScrollPane5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jScrollPane5ComponentShown(evt);
            }
        });

        jTableWypozyczeniaKsiazkiWZW.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tytuł", "Imię autora", "Nazwisko autora", "Liczba sztuk"
            }
        ));
        jTableWypozyczeniaKsiazkiWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableWypozyczeniaKsiazkiWZWMouseClicked(evt);
            }
        });
        jTableWypozyczeniaKsiazkiWZW.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableWypozyczeniaKsiazkiWZWPropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(jTableWypozyczeniaKsiazkiWZW);

        jTabbedPane2.addTab("Wszystkie książki", jScrollPane5);

        jPanelListaZyczen.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelListaZyczenComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelListaZyczenComponentShown(evt);
            }
        });

        jTableWypozyczeniaListaZyczenWZW.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tytuł"
            }
        ));
        jTableWypozyczeniaListaZyczenWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableWypozyczeniaListaZyczenWZWMouseClicked(evt);
            }
        });
        jTableWypozyczeniaListaZyczenWZW.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableWypozyczeniaListaZyczenWZWPropertyChange(evt);
            }
        });
        jScrollPane7.setViewportView(jTableWypozyczeniaListaZyczenWZW);

        javax.swing.GroupLayout jPanelListaZyczenLayout = new javax.swing.GroupLayout(jPanelListaZyczen);
        jPanelListaZyczen.setLayout(jPanelListaZyczenLayout);
        jPanelListaZyczenLayout.setHorizontalGroup(
            jPanelListaZyczenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
            .addGroup(jPanelListaZyczenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelListaZyczenLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanelListaZyczenLayout.setVerticalGroup(
            jPanelListaZyczenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
            .addGroup(jPanelListaZyczenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelListaZyczenLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Z listy życzeń", jPanelListaZyczen);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuKWZW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMenuKWZW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseEntered(evt);
            }
        });

        jPanelWypozyczenia.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelWypozyczeniaComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelWypozyczeniaComponentShown(evt);
            }
        });

        jTableWypozyczeniaWZW.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu", "Status"
            }
        ));
        jTableWypozyczeniaWZW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableWypozyczeniaWZWFocusLost(evt);
            }
        });
        jTableWypozyczeniaWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableWypozyczeniaWZWMouseClicked(evt);
            }
        });
        jTableWypozyczeniaWZW.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableWypozyczeniaWZWPropertyChange(evt);
            }
        });
        jScrollPane6.setViewportView(jTableWypozyczeniaWZW);

        jPanelMenuKWZW1.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj"));

        jLabelImieWZW2.setText("Tytuł");

        jTextFieldTytulWWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTytulWWZWActionPerformed(evt);
            }
        });

        jLabelNazwiskoC3.setText("Data wyp.");

        jButtonZwrocWZW.setText("ZWRÓĆ");
        jButtonZwrocWZW.setEnabled(false);
        jButtonZwrocWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonZwrocWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonZwrocWZWMouseExited(evt);
            }
        });
        jButtonZwrocWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZwrocWZWActionPerformed(evt);
            }
        });

        jButtonDataWWZW.setText("jButton1");
        jButtonDataWWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonDataWWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonDataWWZWMouseExited(evt);
            }
        });
        jButtonDataWWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDataWWZWActionPerformed(evt);
            }
        });

        jButtonTytulWWZW.setText("jButton1");
        jButtonTytulWWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonTytulWWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonTytulWWZWMouseExited(evt);
            }
        });
        jButtonTytulWWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTytulWWZWActionPerformed(evt);
            }
        });

        jButtonWyswielWWZW.setText("W. WSZYSTKIE");
        jButtonWyswielWWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielWWZWActionPerformed(evt);
            }
        });

        jTextFieldDatalWWZW.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldDatalWWZW.setText("dd-mm-rrrr");
        jTextFieldDatalWWZW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDatalWWZWFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDatalWWZWFocusLost(evt);
            }
        });
        jTextFieldDatalWWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDatalWWZWActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuKWZW1Layout = new javax.swing.GroupLayout(jPanelMenuKWZW1);
        jPanelMenuKWZW1.setLayout(jPanelMenuKWZW1Layout);
        jPanelMenuKWZW1Layout.setHorizontalGroup(
            jPanelMenuKWZW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuKWZW1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuKWZW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuKWZW1Layout.createSequentialGroup()
                        .addComponent(jButtonWyswielWWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonZwrocWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMenuKWZW1Layout.createSequentialGroup()
                        .addGroup(jPanelMenuKWZW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelNazwiskoC3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelImieWZW2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMenuKWZW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMenuKWZW1Layout.createSequentialGroup()
                                .addComponent(jTextFieldDatalWWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDataWWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMenuKWZW1Layout.createSequentialGroup()
                                .addComponent(jTextFieldTytulWWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonTytulWWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelMenuKWZW1Layout.setVerticalGroup(
            jPanelMenuKWZW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuKWZW1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanelMenuKWZW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImieWZW2)
                    .addComponent(jTextFieldTytulWWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTytulWWZW))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKWZW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNazwiskoC3)
                    .addComponent(jButtonDataWWZW)
                    .addComponent(jTextFieldDatalWWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelMenuKWZW1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonWyswielWWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonZwrocWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanelWypozyczeniaLayout = new javax.swing.GroupLayout(jPanelWypozyczenia);
        jPanelWypozyczenia.setLayout(jPanelWypozyczeniaLayout);
        jPanelWypozyczeniaLayout.setHorizontalGroup(
            jPanelWypozyczeniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanelMenuKWZW1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelWypozyczeniaLayout.setVerticalGroup(
            jPanelWypozyczeniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelWypozyczeniaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMenuKWZW1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Wypożyczenia", jPanelWypozyczenia);

        jTableZwrotyWZW.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu", "Termin zwrotu", "Status"
            }
        ));
        jTableZwrotyWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableZwrotyWZWMouseClicked(evt);
            }
        });
        jTableZwrotyWZW.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableZwrotyWZWPropertyChange(evt);
            }
        });
        jScrollPane8.setViewportView(jTableZwrotyWZW);

        jPanelMenuKWZW2.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj"));
        jPanelMenuKWZW2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelMenuKWZW2ComponentShown(evt);
            }
        });

        jLabelImieWZW3.setText("Tytuł");

        jTextFieldTytulZWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTytulZWZWActionPerformed(evt);
            }
        });

        jLabelNazwiskoC4.setText("Data wyp.");

        jButtonDataZWZW.setText("jButton1");
        jButtonDataZWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonDataZWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonDataZWZWMouseExited(evt);
            }
        });
        jButtonDataZWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDataZWZWActionPerformed(evt);
            }
        });

        jButtonTytulZWZW.setText("jButton1");
        jButtonTytulZWZW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonTytulZWZWMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonTytulZWZWMouseExited(evt);
            }
        });
        jButtonTytulZWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTytulZWZWActionPerformed(evt);
            }
        });

        jButtonWyswielZWZW.setText("W. WSZYSTKIE");
        jButtonWyswielZWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielZWZWActionPerformed(evt);
            }
        });

        jTextFieldDataZWZW.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldDataZWZW.setText("dd-mm-rrrr");
        jTextFieldDataZWZW.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDataZWZWFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDataZWZWFocusLost(evt);
            }
        });
        jTextFieldDataZWZW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataZWZWActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuKWZW2Layout = new javax.swing.GroupLayout(jPanelMenuKWZW2);
        jPanelMenuKWZW2.setLayout(jPanelMenuKWZW2Layout);
        jPanelMenuKWZW2Layout.setHorizontalGroup(
            jPanelMenuKWZW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuKWZW2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuKWZW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonWyswielZWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMenuKWZW2Layout.createSequentialGroup()
                        .addGroup(jPanelMenuKWZW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelNazwiskoC4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelImieWZW3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMenuKWZW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMenuKWZW2Layout.createSequentialGroup()
                                .addComponent(jTextFieldDataZWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDataZWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMenuKWZW2Layout.createSequentialGroup()
                                .addComponent(jTextFieldTytulZWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonTytulZWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelMenuKWZW2Layout.setVerticalGroup(
            jPanelMenuKWZW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuKWZW2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanelMenuKWZW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImieWZW3)
                    .addComponent(jTextFieldTytulZWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTytulZWZW))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuKWZW2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNazwiskoC4)
                    .addComponent(jButtonDataZWZW)
                    .addComponent(jTextFieldDataZWZW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jButtonWyswielZWZW, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanelZwrotyLayout = new javax.swing.GroupLayout(jPanelZwroty);
        jPanelZwroty.setLayout(jPanelZwrotyLayout);
        jPanelZwrotyLayout.setHorizontalGroup(
            jPanelZwrotyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
            .addComponent(jPanelMenuKWZW2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelZwrotyLayout.setVerticalGroup(
            jPanelZwrotyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelZwrotyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMenuKWZW2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Zwroty", jPanelZwroty);

        jPanelDetailsWZW.setBorder(javax.swing.BorderFactory.createTitledBorder("Szczegóły"));

        jLabelSzczegolyInfo.setText(" ");

        jLabelSzczegolyOpl.setText(" ");

        javax.swing.GroupLayout jPanelDetailsWZWLayout = new javax.swing.GroupLayout(jPanelDetailsWZW);
        jPanelDetailsWZW.setLayout(jPanelDetailsWZWLayout);
        jPanelDetailsWZWLayout.setHorizontalGroup(
            jPanelDetailsWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsWZWLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetailsWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSzczegolyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSzczegolyOpl, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetailsWZWLayout.setVerticalGroup(
            jPanelDetailsWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsWZWLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSzczegolyInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSzczegolyOpl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDetailsWZW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDetailsWZW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelMenuWZWLayout = new javax.swing.GroupLayout(jPanelMenuWZW);
        jPanelMenuWZW.setLayout(jPanelMenuWZWLayout);
        jPanelMenuWZWLayout.setHorizontalGroup(
            jPanelMenuWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuWZWLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(jTabbedPane1))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMenuWZWLayout.setVerticalGroup(
            jPanelMenuWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuWZWLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)))
        );

        javax.swing.GroupLayout jPanelWZWLayout = new javax.swing.GroupLayout(jPanelWZW);
        jPanelWZW.setLayout(jPanelWZWLayout);
        jPanelWZWLayout.setHorizontalGroup(
            jPanelWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWZWLayout.createSequentialGroup()
                .addGroup(jPanelWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelWZWLayout.createSequentialGroup()
                        .addGroup(jPanelWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelWZWLayout.createSequentialGroup()
                                .addComponent(jPanelConsoleK11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanelWZWLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12)))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelMenuK7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMenuWZW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelWZWLayout.setVerticalGroup(
            jPanelWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWZWLayout.createSequentialGroup()
                .addComponent(jPanelMenuK7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelWZWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelConsoleK11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanelMenuWZW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPanelBibliotekarz.addTab("Wypożyczenia i zwroty", jPanelWZW);

        jLabel1.setText("zalogowany jako: ");

        jTextFieldEmail.setEditable(false);
        jTextFieldEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextFieldEmail.setBorder(null);
        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });

        jButton1.setText("WYLOGUJ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmail)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPanelBibliotekarz)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPanelBibliotekarz, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        queryToSend.add("Exit");
        if(Client.readWriteServer(this, queryToSend, null, null) == 1){
            LoginForm form = new LoginForm();
            form.setVisible(true);
            form.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldIEmailCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIEmailCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIEmailCActionPerformed

    private void jTextFieldITelefonCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldITelefonCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldITelefonCActionPerformed

    private void jTextFieldINazwiskoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldINazwiskoCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldINazwiskoCActionPerformed

    private void jButtonWyswielCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielCActionPerformed
        queryToSend.add("35");
        Client.readWriteServer(this, queryToSend, jTableCzytelnicyCz, null);
        
    }//GEN-LAST:event_jButtonWyswielCActionPerformed

    private void jButtonKrajA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKrajA3ActionPerformed
        // TODO add your handling code here:
        String email = jTextFieldIEmailC.getText();

        searchTable(email, "o.email", "36", jTableCzytelnicyCz);
    }//GEN-LAST:event_jButtonKrajA3ActionPerformed

    private void jButtonKrajA3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKrajA3MouseExited
        jTextFieldIEmailC.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonKrajA3MouseExited

    private void jButtonKrajA3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKrajA3MouseEntered
        jTextFieldIEmailC.setBackground(newBlue);
    }//GEN-LAST:event_jButtonKrajA3MouseEntered

    private void jButtonImieCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImieCActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldImieC.getText();

        searchTable(imie, "o.imie", "36", jTableCzytelnicyCz);
    }//GEN-LAST:event_jButtonImieCActionPerformed

    private void jButtonImieCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonImieCMouseExited
        // TODO add your handling code here:
        jTextFieldImieC.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonImieCMouseExited

    private void jButtonImieCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonImieCMouseEntered
        // TODO add your handling code here:
        jTextFieldImieC.setBackground(newBlue);
    }//GEN-LAST:event_jButtonImieCMouseEntered

    private void jButtonNazwiskoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNazwiskoCActionPerformed
        // TODO add your handling code here:
        String nazwisko = jTextFieldINazwiskoC.getText();

        searchTable(nazwisko, "o.nazwisko", "36", jTableCzytelnicyCz);
    }//GEN-LAST:event_jButtonNazwiskoCActionPerformed

    private void jButtonNazwiskoCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNazwiskoCMouseExited
        jTextFieldINazwiskoC.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonNazwiskoCMouseExited

    private void jButtonNazwiskoCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNazwiskoCMouseEntered
        jTextFieldINazwiskoC.setBackground(newBlue);
    }//GEN-LAST:event_jButtonNazwiskoCMouseEntered

    private void jButtonTelefonCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTelefonCActionPerformed
        // TODO add your handling code here:
        String telefon = jTextFieldITelefonC.getText();

        searchTable(telefon, "o.telefon", "36", jTableCzytelnicyCz);
    }//GEN-LAST:event_jButtonTelefonCActionPerformed

    private void jButtonTelefonCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTelefonCMouseExited
        jTextFieldITelefonC.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonTelefonCMouseExited

    private void jButtonTelefonCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTelefonCMouseEntered
        jTextFieldITelefonC.setBackground(newBlue);
    }//GEN-LAST:event_jButtonTelefonCMouseEntered

    private void jButtonAktualizujCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAktualizujCActionPerformed
        // TODO add your handling code here:
        String telefon = jTextFieldITelefonC.getText().replaceAll("\\s+","");
        String email = jTextFieldIEmailC.getText().replaceAll("\\s+","");

        if(telefon.trim().equals("") || email.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Jedno lub więcej pól jest puste","Uzupelnij puste pola",2);
        }else if(telefon.length() > 12){
            JOptionPane.showMessageDialog(null, "Podany numer telefonu jest za dlugi","Podaj poprawny numer telefonu",2);
        }else{
            queryToSend.add("40");                          //checkOsoba
            queryToSend.add(email);

            int checkId = Client.readWriteServer(this, queryToSend, null, null);
            //System.out.println(checkId);
            if(checkId > 0){
                System.out.println("istnieje juz czytelnik o podanych danych\n");

                queryToSend.add("41");
                queryToSend.add(telefon);
                queryToSend.add(String.valueOf(checkId));  //idCzytelnik

                if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                    String logInfo = "Bibliotekarz [" + emailUser + "] zauktualizowal dane czytelnika o ID: [" + checkId + "]";
                    sendLog(logInfo, jTextAreaC);

                    queryToSend.add("35");
                    Client.readWriteServer(this, queryToSend, jTableCzytelnicyCz, null);
                }

            }else{
                System.out.println("nie ma takiego czytelnika");
            }
        }
    }//GEN-LAST:event_jButtonAktualizujCActionPerformed

    private void jButtonAktualizujCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAktualizujCMouseExited
        // TODO add your handling code here:
        jTextFieldITelefonC.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonAktualizujCMouseExited

    private void jButtonAktualizujCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAktualizujCMouseEntered
        // TODO add your handling code here:
        jTextFieldITelefonC.setBackground(newBlue);
    }//GEN-LAST:event_jButtonAktualizujCMouseEntered

    private void jTextFieldImieCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldImieCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldImieCActionPerformed

    private void jTextFieldIKrajAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIKrajAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIKrajAActionPerformed

    private void jTextFieldIMiastoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIMiastoAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIMiastoAActionPerformed

    private void jTextFieldINazwiskoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldINazwiskoAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldINazwiskoAActionPerformed

    private void jButtonWyswielSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielSActionPerformed
        queryToSend.add("30");

        Client.readWriteServer(this, queryToSend, jTableAutorzyA, null);
    }//GEN-LAST:event_jButtonWyswielSActionPerformed

    private void jTextFieldIDataSmAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDataSmAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDataSmAActionPerformed

    private void jTextFieldDataUrAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataUrAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDataUrAActionPerformed

    private void jButtonKrajAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKrajAActionPerformed
        // TODO add your handling code here:
        String kraj = jTextFieldIKrajA.getText();

        searchTable("'" + kraj + "'", "LOWER(kraj) =", "31", jTableAutorzyA);
    }//GEN-LAST:event_jButtonKrajAActionPerformed

    private void jButtonKrajAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKrajAMouseExited
        jTextFieldIKrajA.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonKrajAMouseExited

    private void jButtonKrajAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKrajAMouseEntered
        jTextFieldIKrajA.setBackground(newBlue);
    }//GEN-LAST:event_jButtonKrajAMouseEntered

    private void jButtonImieAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImieAActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldImieA.getText();

        searchTable("'" + imie + "'", "LOWER(imie) =", "31", jTableAutorzyA);
    }//GEN-LAST:event_jButtonImieAActionPerformed

    private void jButtonImieAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonImieAMouseExited
        jTextFieldImieA.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonImieAMouseExited

    private void jButtonImieAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonImieAMouseEntered
        jTextFieldImieA.setBackground(newBlue);
    }//GEN-LAST:event_jButtonImieAMouseEntered

    private void jButtonNazwiskoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNazwiskoAActionPerformed
        // TODO add your handling code here:
        String nazwisko = jTextFieldINazwiskoA.getText();

        searchTable("'" + nazwisko + "'", "LOWER(nazwisko) =", "31", jTableAutorzyA);
    }//GEN-LAST:event_jButtonNazwiskoAActionPerformed

    private void jButtonNazwiskoAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNazwiskoAMouseExited
        jTextFieldINazwiskoA.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonNazwiskoAMouseExited

    private void jButtonNazwiskoAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNazwiskoAMouseEntered
        jTextFieldINazwiskoA.setBackground(newBlue);
    }//GEN-LAST:event_jButtonNazwiskoAMouseEntered

    private void jButtonMiastoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMiastoAActionPerformed
        // TODO add your handling code here:
        String miasto = jTextFieldIMiastoA.getText();

        searchTable("'" + miasto + "'", "LOWER(miasto) =", "31", jTableAutorzyA);
    }//GEN-LAST:event_jButtonMiastoAActionPerformed

    private void jButtonMiastoAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonMiastoAMouseExited
        jTextFieldIMiastoA.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonMiastoAMouseExited

    private void jButtonMiastoAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonMiastoAMouseEntered
        jTextFieldIMiastoA.setBackground(newBlue);
    }//GEN-LAST:event_jButtonMiastoAMouseEntered

    private void jButtonDataUrAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDataUrAActionPerformed
        String dataU = jTextFieldDataUrA.getText();
        if(!isCorrectFullDate(dataU)){
                JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
        }
        else{
            dataU = "TO_DATE('" + dataU + "', 'dd-mm-yyyy')";

            searchTable(dataU, "data_urodzenia = ", "31", jTableAutorzyA);
        }
    }//GEN-LAST:event_jButtonDataUrAActionPerformed

    private void jButtonDataUrAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataUrAMouseExited
        jTextFieldDataUrA.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonDataUrAMouseExited

    private void jButtonDataUrAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataUrAMouseEntered
        jTextFieldDataUrA.setBackground(newBlue);
    }//GEN-LAST:event_jButtonDataUrAMouseEntered

    private void jButtonDataSmAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDataSmAActionPerformed
        String dataS = jTextFieldIDataSmA.getText();
        String dateS2 = "IS NULL";
            
        if(!dataS.equals("-") && !dataS.equals("dd-mm-rrrr")) {
            if(!isCorrectFullDate(dataS)){
                JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
                dateS2 = "0";
            }else{
                dateS2 = "= to_date('" + dataS + "', 'DD-MM-YYYY')";
            }     
        }
        if(!dateS2.equals("0")){
            searchTable(dateS2, "data_smierci ", "31", jTableAutorzyA);
        }
        
    }//GEN-LAST:event_jButtonDataSmAActionPerformed

    private void jButtonDataSmAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataSmAMouseExited
        jTextFieldIDataSmA.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonDataSmAMouseExited

    private void jButtonDataSmAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataSmAMouseEntered
        jTextFieldIDataSmA.setBackground(newBlue);
    }//GEN-LAST:event_jButtonDataSmAMouseEntered

    private void jButtonUsunSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsunSActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldImieA.getText().replaceAll("\\s+","");
        String nazwisko = jTextFieldINazwiskoA.getText().replaceAll("\\s+","");
        String dateU = jTextFieldDataUrA.getText();
        String dateS = jTextFieldIDataSmA.getText();

        if(imie.trim().equals("") || nazwisko.trim().equals("")
            ||dateU.trim().equals("dd-mm-rrrr") || dateS.trim().equals("dd-mm-rrrr"))
        {
            JOptionPane.showMessageDialog(null, "Jedno lub więcej pól jest puste","Uzupelnij puste pola",2);
        }else if(!isCorrectFullDate(dateU)){
                JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
            }else{
            String dateS2 = "NULL";
            
            if(!dateS.equals("-") && !dateS.equals("dd-mm-rrrr")) {
                if(!isCorrectFullDate(dateS)){
                    JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
                    dateS2 = "0";
                }else{
                    dateS2 = "to_date('" + dateS + "', 'DD-MM-YYYY')";
                }     
            }
            if(!dateS2.equals("0")){
                queryToSend.add("32");              //checkDateAutor
                queryToSend.add(imie);
                queryToSend.add(nazwisko);
                queryToSend.add(dateU);

                if(!dateS.equals("-") && !dateS.equals("0")) {
                    dateS2 = "to_date('" + dateS + "', 'DD.MM.YYYY')";
                    queryToSend.add("= " + dateS2);
                } else {
                    dateS2 = "NULL";
                    queryToSend.add("IS " + dateS2);
                }


                int checkQ = Client.readWriteServer(this, queryToSend, null, null);
                if(checkQ > 0){
                    System.out.println("mamy id, usuwam: " + checkQ);

                    queryToSend.add("34");
                    queryToSend.add(String.valueOf(checkQ));
                    if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                        String logInfo = "Bibliotekarz [" + emailUser + "] usunal autora: [" + imie + " " + nazwisko + "]";
                        sendLog(logInfo, jTextAreaK);

                        queryToSend.add("30");
                        Client.readWriteServer(this, queryToSend, jTableAutorzyA, null);

                        queryToSend.add("16");
                        Client.readWriteServer(this, queryToSend, null, jComboBoxAutorK);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonUsunSActionPerformed

    private void jButtonUsunSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUsunSMouseExited
        jTextFieldImieA.setBackground(Color.WHITE);
        jTextFieldINazwiskoA.setBackground(Color.WHITE);
        jTextFieldDataUrA.setBackground(Color.WHITE);
        jTextFieldIDataSmA.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonUsunSMouseExited

    private void jButtonUsunSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUsunSMouseEntered
        jTextFieldImieA.setBackground(newBlue);
        jTextFieldINazwiskoA.setBackground(newBlue);
        jTextFieldDataUrA.setBackground(newBlue);
        jTextFieldIDataSmA.setBackground(newBlue);
    }//GEN-LAST:event_jButtonUsunSMouseEntered

    private void jButtonAktualizujSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAktualizujSActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldImieA.getText().replaceAll("\\s+","");
        String nazwisko = jTextFieldINazwiskoA.getText().replaceAll("\\s+","");
        String miasto = jTextFieldIMiastoA.getText().replaceAll("\\s+","");
        String kraj = jTextFieldIKrajA.getText().replaceAll("\\s+","");
        String dateU = jTextFieldDataUrA.getText();
        String dateS = jTextFieldIDataSmA.getText();

        if(imie.trim().equals("") || nazwisko.trim().equals("")
            || miasto.trim().equals("") || kraj.trim().equals("")
            ||dateU.trim().equals("dd-mm-rrrr") || dateS.trim().equals("dd-mm-rrrr"))
        {
            JOptionPane.showMessageDialog(null, "Jedno lub więcej pól jest puste","Uzupelnij puste pola",2);
        }else{

            queryToSend.add("32");                  //checkDateAutor
            queryToSend.add(imie);
            queryToSend.add(nazwisko);
            queryToSend.add(dateU);

            int checkId = Client.readWriteServer(this, queryToSend, null, null);
            System.out.println(checkId);
            if(checkId > 0){
                System.out.println("istnieje juz autor o podanych danych\n");

                String dateS2 = "NULL";
                
                if(!dateS.equals("-") && !dateS.equals("dd-mm-rrrr")) {
                if(!isCorrectFullDate(dateS)){
                    JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
                    dateS2 = "0";
                }else{
                    dateS2 = "to_date('" + dateS + "', 'DD-MM-YYYY')";
                }     
            }
            if(!dateS2.equals("0")){
                    queryToSend.add("38");              //updateAutor
                    queryToSend.add(miasto);
                    queryToSend.add(kraj);
                    queryToSend.add(dateS2);

                    queryToSend.add(String.valueOf(checkId));  //idAutor

                    if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                        String logInfo = "Bibliotekarz [" + emailUser + "] Zauktualizowal dane autora o ID: [" + checkId + "]";
                        sendLog(logInfo, jTextAreaK);

                        queryToSend.add("30");

                        Client.readWriteServer(this, queryToSend, jTableAutorzyA, null);
                    }
                }
            }else{
                System.out.println("nie ma takiego autora");
            }
        }

    }//GEN-LAST:event_jButtonAktualizujSActionPerformed

    private void jButtonAktualizujSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAktualizujSMouseExited
        // TODO add your handling code here:
        jTextFieldIMiastoA.setBackground(Color.WHITE);
        jTextFieldIKrajA.setBackground(Color.WHITE);
        jTextFieldIDataSmA.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonAktualizujSMouseExited

    private void jButtonAktualizujSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAktualizujSMouseEntered
        // TODO add your handling code here:
        jTextFieldIMiastoA.setBackground(newBlue);
        jTextFieldIKrajA.setBackground(newBlue);
        jTextFieldIDataSmA.setBackground(newBlue);
    }//GEN-LAST:event_jButtonAktualizujSMouseEntered

    private void jButtonUDodajSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUDodajSActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldImieA.getText().replaceAll("\\s+","");
        String nazwisko = jTextFieldINazwiskoA.getText().replaceAll("\\s+","");
        String miasto = jTextFieldIMiastoA.getText().replaceAll("\\s+","");
        String kraj = jTextFieldIKrajA.getText().replaceAll("\\s+","");
        String dateU = jTextFieldDataUrA.getText();
        String dateS = jTextFieldIDataSmA.getText();

        
            if(imie.trim().equals("") || nazwisko.trim().equals("")
            || miasto.trim().equals("") || kraj.trim().equals("")
            || dateU.trim().equals("dd-mm-rrrr"))
            {
            JOptionPane.showMessageDialog(null, "Jedno lub więcej pól jest puste","Uzupelnij puste pola",2);
            }else if(!isCorrectFullDate(dateU)){
                JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
            }else{
                
            queryToSend.add("32");                      //checkDateAutor
            queryToSend.add(imie);
            queryToSend.add(nazwisko);
            queryToSend.add(dateU);

            int checkId = Client.readWriteServer(this, queryToSend, null, null);
            System.out.println(checkId);
            if(checkId > 0){
            JOptionPane.showMessageDialog(null, imie + nazwisko + " widnieje juz w spisie autorow","Wpisz inne dane",2);
            }else{
            System.out.println("nie ma takiego autora");
            
            String dateS2 = "NULL";
            
            queryToSend.add("33");                      //insertAutor
            queryToSend.add(imie);
            queryToSend.add(nazwisko);
            queryToSend.add(miasto);
            queryToSend.add(kraj);
            queryToSend.add(dateU);
                
            if(!dateS.equals("-") && !dateS.equals("dd-mm-rrrr")) {
                if(!isCorrectFullDate(dateS)){
                    JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
                    dateS2 = "0";
                }else{
                    dateS2 = "to_date('" + dateS + "', 'DD-MM-YYYY')";
                }     
            }
            if(!dateS2.equals("0")){
            queryToSend.add(dateS2);
            
            if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                String logInfo = "Bibliotekarz [" + emailUser + "] dodal nowego autora: [" + imie + " " + nazwisko + "]";
                sendLog(logInfo, jTextAreaK);
                }
            }
            
            }
            queryToSend.add("30");
            Client.readWriteServer(this, queryToSend, jTableAutorzyA, null);

            queryToSend.add("16");
            Client.readWriteServer(this, queryToSend, null, jComboBoxAutorK);
            }
    }//GEN-LAST:event_jButtonUDodajSActionPerformed

    private void jTextFieldImieAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldImieAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldImieAActionPerformed

    private void jTableAutorzyAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAutorzyAMouseClicked
        // TODO add your handling code here:
        int indexArr = jTableAutorzyA.getSelectedRow();
        TableModel model = jTableAutorzyA.getModel();

        jTextFieldImieA.setText(model.getValueAt(indexArr, 1).toString().replaceAll("\\s+",""));
        jTextFieldINazwiskoA.setText(model.getValueAt(indexArr, 2).toString().replaceAll("\\s+",""));
        jTextFieldIMiastoA.setText(model.getValueAt(indexArr, 3).toString().replaceAll("\\s+",""));
        jTextFieldIKrajA.setText(model.getValueAt(indexArr, 4).toString().replaceAll("\\s+",""));
        jTextFieldDataUrA.setForeground(Color.black);
        jTextFieldDataUrA.setText(model.getValueAt(indexArr, 5).toString());
        jTextFieldIDataSmA.setForeground(Color.black);
        jTextFieldIDataSmA.setText(model.getValueAt(indexArr, 6).toString());
    }//GEN-LAST:event_jTableAutorzyAMouseClicked

    private void jTableKsiazkiKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKsiazkiKMouseClicked
        // TODO add your handling code here:
        int indexArr = jTableKsiazkiK.getSelectedRow();
        TableModel model = jTableKsiazkiK.getModel();

        jTextFieldTytulK.setText(model.getValueAt(indexArr, 1).toString());
        jComboBoxAutorK.getModel().setSelectedItem(model.getValueAt(indexArr, 2).toString() + " " + model.getValueAt(indexArr, 3).toString());
        jComboBoxKategoriaK.getModel().setSelectedItem(model.getValueAt(indexArr, 4).toString());
        jComboBoxWydawnictwoK.getModel().setSelectedItem(model.getValueAt(indexArr, 5).toString());
        jTextFieldRokWydaniaK.setForeground(Color.black);
        jTextFieldRokWydaniaK.setText(model.getValueAt(indexArr, 6).toString());
        jTextFieldIloscK.setText(model.getValueAt(indexArr, 7).toString());
        //jTextFieldLogin_username.setText("email uzytkownika");
        System.out.println(model.getValueAt(indexArr, 0).toString());
    }//GEN-LAST:event_jTableKsiazkiKMouseClicked

    private void jButtonWyswielKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielKActionPerformed
        queryToSend.add("11");

        Client.readWriteServer(this, queryToSend, jTableKsiazkiK, null);
    }//GEN-LAST:event_jButtonWyswielKActionPerformed

    private void jTextFieldIloscKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIloscKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIloscKActionPerformed

    private void jTextFieldRokWydaniaKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRokWydaniaKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRokWydaniaKActionPerformed

    private void jButtonWydawnictwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWydawnictwoActionPerformed
        // TODO add your handling code here:
        AddWydForm AddWydF = new AddWydForm();
        AddWydF.setVisible(true);
        AddWydF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButtonWydawnictwoActionPerformed

    private void jComboBoxWydawnictwoKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWydawnictwoKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxWydawnictwoKActionPerformed

    private void jComboBoxWydawnictwoKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxWydawnictwoKItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxWydawnictwoKItemStateChanged

    private void jButtonWydawnictwoSKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWydawnictwoSKActionPerformed
        // TODO add your handling code here:
        String wydawnictwo = (String)jComboBoxWydawnictwoK.getSelectedItem();

        searchTable(wydawnictwo, "wd.nazwa_wydawnictwa", "12", jTableKsiazkiK);
    }//GEN-LAST:event_jButtonWydawnictwoSKActionPerformed

    private void jButtonWydawnictwoSKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWydawnictwoSKMouseExited
        // TODO add your handling code here:
        //jTextFieldTytulK.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonWydawnictwoSKMouseExited

    private void jButtonWydawnictwoSKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWydawnictwoSKMouseEntered
        // TODO add your handling code here:
        //jTextFieldTytulK.setBackground(newBlue);
    }//GEN-LAST:event_jButtonWydawnictwoSKMouseEntered

    private void jButtonTytulSKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTytulSKActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulK.getText();

        searchTable(tytul, "tytul", "12", jTableKsiazkiK);
    }//GEN-LAST:event_jButtonTytulSKActionPerformed

    private void jButtonTytulSKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulSKMouseExited
        // TODO add your handling code here:
        jTextFieldTytulK.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonTytulSKMouseExited

    private void jButtonTytulSKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulSKMouseEntered
        // TODO add your handling code here:
        jTextFieldTytulK.setBackground(newBlue);
    }//GEN-LAST:event_jButtonTytulSKMouseEntered

    private void jButtonAutorSKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutorSKActionPerformed
        // TODO add your handling code here:
        String autor = (String)jComboBoxAutorK.getSelectedItem();

        autor = autor.trim();
        String[] newStr = autor.split(" ", 2);

        String queryP = "au.imie) = '"+ newStr[0].toLowerCase() + "' AND LOWER(au.nazwisko";

        searchTable(newStr[1], queryP, "12", jTableKsiazkiK);
    }//GEN-LAST:event_jButtonAutorSKActionPerformed

    private void jButtonAutorSKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAutorSKMouseExited
        // TODO add your handling code here:
        //jTextFieldTytulK.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonAutorSKMouseExited

    private void jButtonAutorSKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAutorSKMouseEntered
        // TODO add your handling code here:
        //jTextFieldTytulK.setBackground(newBlue);
    }//GEN-LAST:event_jButtonAutorSKMouseEntered

    private void jButtonKategoriaSKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKategoriaSKActionPerformed
        // TODO add your handling code here:
        String kategoria = (String)jComboBoxKategoriaK.getSelectedItem();

        searchTable(kategoria, "kt.nazwa_kategorii", "12", jTableKsiazkiK);
    }//GEN-LAST:event_jButtonKategoriaSKActionPerformed

    private void jButtonKategoriaSKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKategoriaSKMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonKategoriaSKMouseExited

    private void jButtonKategoriaSKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKategoriaSKMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonKategoriaSKMouseEntered

    private void jButtonRokSKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRokSKActionPerformed
        String rokwy = jTextFieldRokWydaniaK.getText();
        if(!isCorrectYear(rokwy)){
            JOptionPane.showMessageDialog(null, "Format daty jest nieprawidlowy","Wpisz poprawny rok",2);
        }else{
            searchTable(rokwy, "ks.rok_wydania", "12", jTableKsiazkiK);
        }
    }//GEN-LAST:event_jButtonRokSKActionPerformed

    private void jButtonRokSKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRokSKMouseExited
        jTextFieldRokWydaniaK.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonRokSKMouseExited

    private void jButtonRokSKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRokSKMouseEntered
        jTextFieldRokWydaniaK.setBackground(newBlue);
    }//GEN-LAST:event_jButtonRokSKMouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String ilosc = jTextFieldIloscK.getText();

        searchTable(ilosc, "ks.liczba_sztuk", "12", jTableKsiazkiK);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jTextFieldIloscK.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jTextFieldIloscK.setBackground(newBlue);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jComboBoxKategoriaKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxKategoriaKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxKategoriaKActionPerformed

    private void jComboBoxKategoriaKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxKategoriaKItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxKategoriaKItemStateChanged

    private void jButtonUsunKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsunKActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulK.getText();
        String autor = (String)jComboBoxAutorK.getSelectedItem();

        if(tytul.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Musisz podac tytul ksiazki, ktora chcesz usunac","Uzupelnij puste pole",2);
        } else {
            queryToSend.add("25");                  //checkKsiazka
            queryToSend.add(tytul);
            if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                System.out.println("jest taka ksiazka");

                autor = autor.trim();
                String[] autorStr = autor.split(" ", 2);

                queryToSend.add("23");              //checkAutorKsiazka
                queryToSend.add(tytul);
                queryToSend.add(autorStr[0]);
                queryToSend.add(autorStr[1]);

                if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                    System.out.println("jest taki autor tej ksiazki");

                    queryToSend.add("29");          //deleteAutKsiazka
                    queryToSend.add(tytul);
                    queryToSend.add(autorStr[0]);
                    queryToSend.add(autorStr[1]);

                    if(Client.readWriteServer(this, queryToSend, null, null)  == 1){
                        System.out.println("usunieto autora");
                        String logInfo = "Bibliotekarz [" + emailUser + "] usunal autora [" + autor + "] z ksiazki [" + tytul + "]";
                        sendLog(logInfo, jTextAreaS);
                    }
                } else {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(this, "Uwaga! Nie wybrano prawidlowego autora. Spowoduje to usuniecie wszystkich wpisow dotyczacych wybranej ksiazki. Kontynuować?", "", dialogButton);
                    if(dialogResult == 0) {
                        System.out.println("TAK");

                        queryToSend.add("28");          //deleteKsiazka
                        queryToSend.add(tytul);

                        if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                            String logInfo = "Bibliotekarz [" + emailUser + "] usunal ksiazke o tytule [" + tytul + "]";
                            sendLog(logInfo, jTextAreaS);
                        }
                    } else {
                        System.out.println("NIE");
                    }
                }
            } else {
                LocalDateTime date = LocalDateTime.now();
                jTextAreaS.append(date.format(dateForm) + ": Nie ma ksiazki o tytule '" + tytul + "'\n");
            }
            queryToSend.add("11");
            Client.readWriteServer(this, queryToSend, jTableKsiazkiK, null);
        }
    }//GEN-LAST:event_jButtonUsunKActionPerformed

    private void jButtonUsunKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUsunKMouseExited
        jTextFieldTytulK.setBackground(Color.WHITE);
        jComboBoxAutorK.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonUsunKMouseExited

    private void jButtonUsunKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUsunKMouseEntered
        jTextFieldTytulK.setBackground(newBlue);
        jComboBoxAutorK.setBackground(newBlue);
    }//GEN-LAST:event_jButtonUsunKMouseEntered

    private void jButtonAktualizujKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAktualizujKActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulK.getText();
        String autor = (String)jComboBoxAutorK.getSelectedItem();
        String kateg = (String)jComboBoxKategoriaK.getSelectedItem();
        String wydaw = (String)jComboBoxWydawnictwoK.getSelectedItem();
        String rokwy = jTextFieldRokWydaniaK.getText();
        String ilosc = jTextFieldIloscK.getText();

        if(tytul.trim().equals("") || autor.trim().equals("")
            || kateg.trim().equals("") || wydaw.trim().equals("")
            ||rokwy.trim().equals("rrrr") || ilosc.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Jedno lub więcej pól jest puste","Uzupelnij puste pola",2);
        }else if(!isCorrectYear(rokwy)){
            JOptionPane.showMessageDialog(null, "Format daty jest nieprawidlowy","Wpisz poprawny rok",2);
        }else{

            queryToSend.add("25");                  //checkKsiazka
            queryToSend.add(tytul);
            if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                System.out.println("jest taka ksiazka");

                autor = autor.trim();
                String[] newStr = autor.split(" ", 2);

                int indexArr = jTableKsiazkiK.getSelectedRow();
                TableModel model = jTableKsiazkiK.getModel();

                String name2;
                String sname2;

                if(indexArr > 0) {
                    name2 = model.getValueAt(indexArr, 2).toString();      //do zamienienia konkretnego
                    sname2 = model.getValueAt(indexArr, 3).toString();

                    queryToSend.add("23");              //checkAutorKsiazka
                    queryToSend.add(tytul);
                    queryToSend.add(newStr[0]);
                    queryToSend.add(newStr[1]);

                    if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                        System.out.println("jest taki autor ksiazki");     //jesli jest to rownamy je zeby nie zmieniac
                        newStr[0] = name2;
                        newStr[1] = sname2;
                    }
                } else {
                    name2 = newStr[0];
                    sname2 = newStr[1];
                }

                queryToSend.add("27");
                queryToSend.add(kateg);             //id kat
                queryToSend.add(wydaw);             //id wyd
                queryToSend.add(tytul);
                queryToSend.add(rokwy);
                queryToSend.add(ilosc);
                queryToSend.add(newStr[0]);
                queryToSend.add(newStr[1]);
                queryToSend.add(name2);
                queryToSend.add(sname2);

                if(indexArr > 0) {
                    queryToSend.add(model.getValueAt(indexArr, 2).toString());
                    queryToSend.add(model.getValueAt(indexArr, 3).toString());
                } else {
                    queryToSend.add(newStr[0]);
                    queryToSend.add(newStr[1]);
                }

                if(Client.readWriteServer(this, queryToSend, null, null) == 0){
                    String logInfo = "Bibliotekarz [" + emailUser + "] zaktualizowal ksiazke [" + tytul + "]";
                    sendLog(logInfo, jTextAreaS);
                }
            }else{
                jTextAreaS.append("Nie ma ksiazki o tytule '" + tytul + "' do zaktualizowania\n");
            }

            queryToSend.add("11");
            Client.readWriteServer(this, queryToSend, jTableKsiazkiK, null);
        }
    }//GEN-LAST:event_jButtonAktualizujKActionPerformed

    private void jButtonAktualizujKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAktualizujKMouseExited
        // TODO add your handling code here:
        jComboBoxAutorK.setBackground(Color.WHITE);
        jComboBoxKategoriaK.setBackground(Color.WHITE);
        jComboBoxWydawnictwoK.setBackground(Color.WHITE);
        jTextFieldRokWydaniaK.setBackground(Color.WHITE);
        jTextFieldIloscK.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonAktualizujKMouseExited

    private void jButtonAktualizujKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAktualizujKMouseEntered
        // TODO add your handling code here:
        jComboBoxKategoriaK.setBackground(newBlue);
        jComboBoxWydawnictwoK.setBackground(newBlue);
        jTextFieldRokWydaniaK.setBackground(newBlue);
        jTextFieldIloscK.setBackground(newBlue);
    }//GEN-LAST:event_jButtonAktualizujKMouseEntered

    private void jButtonUDodajKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUDodajKActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulK.getText();
        String autor = (String)jComboBoxAutorK.getSelectedItem();
        String kateg = (String)jComboBoxKategoriaK.getSelectedItem();
        String wydaw = (String)jComboBoxWydawnictwoK.getSelectedItem();
        String rokwy = jTextFieldRokWydaniaK.getText();
        String ilosc = jTextFieldIloscK.getText();

        if(tytul.trim().equals("") || autor.trim().equals("")
            || kateg.trim().equals("") || wydaw.trim().equals("")
            || ilosc.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Jedno lub więcej pól jest puste","Uzupelnij puste pola",2);
        }else if(!isCorrectYear(rokwy)){
            JOptionPane.showMessageDialog(null, "Format daty jest nieprawidlowy","Wpisz poprawny rok",2);
        }else{

            autor = autor.trim();
            String[] newStr = autor.split(" ", 2);

            queryToSend.add("25");                  //checkKsiazka
            queryToSend.add(tytul);
            if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                System.out.println("istnieje juz ksiazka o tytule: '" + tytul + "'\n");

                queryToSend.add("23");              //checkAutorKsiazka
                queryToSend.add(tytul);
                queryToSend.add(newStr[0]);
                queryToSend.add(newStr[1]);

                if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                    JOptionPane.showMessageDialog(null, autor + " jest juz autorem ksiazki '" + tytul + "'","Wpisz poprawnego autora",2);
                }else{

                    queryToSend.add("24");          //insertAutKsiazka
                    queryToSend.add(kateg);
                    queryToSend.add(wydaw);
                    queryToSend.add(tytul);
                    queryToSend.add(rokwy);
                    queryToSend.add(ilosc);
                    queryToSend.add(newStr[0]);
                    queryToSend.add(newStr[1]);

                    if(Client.readWriteServer(this, queryToSend, null, null) == 0){
                        String logInfo = "Bibliotekarzowi [" + emailUser + "] nie udalo sie dodac uzytlkownika";
                        sendLog(logInfo, jTextAreaS);
                    }else{
                        String logInfo = "Bibliotekarz [" + emailUser + "] dodal autora [" + autor + "] do ksiazki [" + tytul + "]";
                        sendLog(logInfo, jTextAreaS);
                    }
                }
            }else{
                System.out.println("nie ma takiej ksiazki");

                queryToSend.add("26");                  //insertKsiazka
                queryToSend.add(kateg);
                queryToSend.add(wydaw);
                queryToSend.add(tytul);
                queryToSend.add(rokwy);
                queryToSend.add(ilosc);
                queryToSend.add(newStr[0]);
                queryToSend.add(newStr[1]);

                if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                    String logInfo = "Bibliotekarz [" + emailUser + "] dodal nowa ksiazke: [" + tytul + "]";
                    sendLog(logInfo, jTextAreaS);
                }

            }
            queryToSend.add("11");
            Client.readWriteServer(this, queryToSend, jTableKsiazkiK, null);
        }
    }//GEN-LAST:event_jButtonUDodajKActionPerformed

    private void jComboBoxAutorKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAutorKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAutorKActionPerformed

    private void jComboBoxAutorKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxAutorKItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAutorKItemStateChanged

    private void jTextFieldTytulKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTytulKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTytulKActionPerformed

    private void jButtonUsunCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsunCActionPerformed
        // TODO add your handling code here:
        String email = jTextFieldIEmailC.getText().replaceAll("\\s+","");

        if(email.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Wymagane pole jest puste","Uzupelnij puste pole",2);
        }
        else{

            queryToSend.add("2");                   //checkOsoba
            queryToSend.add(email);

            int checkId = Client.readWriteServer(this, queryToSend, null, null);
            if(checkId > 0){

                queryToSend.add("37");              //deleteOsoba
                queryToSend.add(String.valueOf(checkId));

                if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                    String logInfo = "Bibliotekarz [" + emailUser + "] usunal czytelnika z emailem: [" + email + "]";
                    sendLog(logInfo, jTextAreaC);

                    queryToSend.add("35");
                    Client.readWriteServer(this, queryToSend, jTableCzytelnicyCz, null);
                }
            } else {
                System.out.println("nie ma takiej osoby");
            }
        }
    }//GEN-LAST:event_jButtonUsunCActionPerformed

    private void jButtonUsunCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUsunCMouseExited
        jTextFieldIEmailC.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonUsunCMouseExited

    private void jButtonUsunCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUsunCMouseEntered
        jTextFieldIEmailC.setBackground(newBlue);
    }//GEN-LAST:event_jButtonUsunCMouseEntered

    private void jTableCzytelnicyCzMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCzytelnicyCzMouseClicked
        // TODO add your handling code here:
        int indexArr = jTableCzytelnicyCz.getSelectedRow();
        TableModel model = jTableCzytelnicyCz.getModel();

        jTextFieldImieC.setText(model.getValueAt(indexArr, 1).toString().replaceAll("\\s+",""));
        jTextFieldINazwiskoC.setText(model.getValueAt(indexArr, 2).toString().replaceAll("\\s+",""));
        jTextFieldITelefonC.setText(model.getValueAt(indexArr, 5).toString().replaceAll("\\s+",""));
        jTextFieldIEmailC.setText(model.getValueAt(indexArr, 6).toString().replaceAll("\\s+",""));
    }//GEN-LAST:event_jTableCzytelnicyCzMouseClicked

    private void jTableFastWZWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFastWZWMouseClicked
        // TODO add your handling code here:
        int indexArr = jTableFastWZW.getSelectedRow();
        TableModel model = jTableFastWZW.getModel();

        jTextFieldImieWZW.setText(model.getValueAt(indexArr, 0).toString().replaceAll("\\s+",""));
        jTextFieldINazwiskoWZW.setText(model.getValueAt(indexArr, 1).toString().replaceAll("\\s+",""));
        jTextFieldIEmailWZW.setText(model.getValueAt(indexArr, 2).toString().replaceAll("\\s+",""));
        
        jTextFieldTytulWWZW.setText("");
        
        jTextFieldDatalWWZW.setForeground(new Color(153, 153, 153));
        jTextFieldDatalWWZW.setText("dd-mm-rrrr");
        
        jPanelMenuWZW.setVisible(true);
        
        queryToSend.add("16");        
        Client.readWriteServer(this, queryToSend, null, jComboBoxAutorWZW);
        
        queryToSend.add("43");
        Client.readWriteServer(this, queryToSend, jTableWypozyczeniaKsiazkiWZW, null);
        
        readTableWZW(jTableWypozyczeniaWZW, "wypożyczona", "46");
        readTableWZW(jTableZwrotyWZW, "zwrócona", "51");
        
    }//GEN-LAST:event_jTableFastWZWMouseClicked

    private void jTextFieldIEmailWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIEmailWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIEmailWZWActionPerformed

    private void jTextFieldINazwiskoWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldINazwiskoWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldINazwiskoWZWActionPerformed

    private void jButtonWyswielWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielWZWActionPerformed
        // TODO add your handling code here:
        queryToSend.add("39");

        Client.readWriteServer(this, queryToSend, jTableFastWZW, null);
    }//GEN-LAST:event_jButtonWyswielWZWActionPerformed

    private void jButtonImieWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImieWZWActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldImieWZW.getText();

        searchTable(imie, "o.imie", "42", jTableFastWZW);
    }//GEN-LAST:event_jButtonImieWZWActionPerformed

    private void jButtonImieWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonImieWZWMouseExited
        jTextFieldImieWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonImieWZWMouseExited

    private void jButtonImieWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonImieWZWMouseEntered
        jTextFieldImieWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonImieWZWMouseEntered

    private void jButtonNazwiskoWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNazwiskoWZWActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldINazwiskoWZW.getText();

        searchTable(imie, "o.nazwisko", "42", jTableFastWZW);
    }//GEN-LAST:event_jButtonNazwiskoWZWActionPerformed

    private void jButtonNazwiskoWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNazwiskoWZWMouseExited
        jTextFieldINazwiskoWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonNazwiskoWZWMouseExited

    private void jButtonNazwiskoWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNazwiskoWZWMouseEntered
        jTextFieldINazwiskoWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonNazwiskoWZWMouseEntered

    private void jButtonEmailWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmailWZWActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldIEmailWZW.getText();                                                        /////////////////////POPRAWIC WYSWIETLANIE

        searchTable(imie, "o.email", "42", jTableFastWZW);
    }//GEN-LAST:event_jButtonEmailWZWActionPerformed

    private void jButtonEmailWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEmailWZWMouseExited
        jTextFieldIEmailWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonEmailWZWMouseExited

    private void jButtonEmailWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEmailWZWMouseEntered
        jTextFieldIEmailWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonEmailWZWMouseEntered

    private void jButtonWyszukajWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyszukajWZWActionPerformed
        // TODO add your handling code here:
        String imie = jTextFieldImieWZW.getText().replaceAll("\\s+","");
        String nazwisko = jTextFieldINazwiskoWZW.getText().replaceAll("\\s+","");

        if(imie.trim().equals("") || nazwisko.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Wymagane pole jest puste","Uzupelnij puste pole",2);
        }
        else{
            queryToSend.add("42");                          //checkOsoba
            queryToSend.add("o.imie");
            queryToSend.add(imie + "' AND LOWER(o.nazwisko) = '" + nazwisko);

            Client.readWriteServer(this, queryToSend, jTableFastWZW, null);

        }
    }//GEN-LAST:event_jButtonWyszukajWZWActionPerformed

    private void jButtonWyszukajWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWyszukajWZWMouseExited
        jTextFieldImieWZW.setBackground(Color.WHITE);
        jTextFieldINazwiskoWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonWyszukajWZWMouseExited

    private void jButtonWyszukajWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWyszukajWZWMouseEntered
        jTextFieldImieWZW.setBackground(newBlue);
        jTextFieldINazwiskoWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonWyszukajWZWMouseEntered

    private void jTextFieldImieWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldImieWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldImieWZWActionPerformed

    private void jTextFieldTytulWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTytulWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTytulWZWActionPerformed

    private void jButtonNazwiskoWZW1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNazwiskoWZW1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNazwiskoWZW1MouseEntered

    private void jButtonNazwiskoWZW1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonNazwiskoWZW1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNazwiskoWZW1MouseExited

    private void jButtonNazwiskoWZW1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNazwiskoWZW1ActionPerformed
        String autor = (String)jComboBoxAutorWZW.getSelectedItem();

        autor = autor.trim();
        String[] newStr = autor.split(" ", 2);

        String queryP = "au.imie) = '"+ newStr[0].toLowerCase() + "' AND LOWER(au.nazwisko";
        searchTable(newStr[1], queryP, "44", jTableWypozyczeniaKsiazkiWZW);
    }//GEN-LAST:event_jButtonNazwiskoWZW1ActionPerformed

    private void jButtonITytulWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonITytulWZWMouseEntered
        jTextFieldTytulWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonITytulWZWMouseEntered

    private void jButtonITytulWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonITytulWZWMouseExited
        jTextFieldTytulWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonITytulWZWMouseExited

    private void jButtonITytulWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonITytulWZWActionPerformed
        String tytul = jTextFieldTytulWZW.getText();
        
        if(jScrollPane5.isVisible()){
            searchTable(tytul, "tytul", "44", jTableWypozyczeniaKsiazkiWZW);
        }else{
            int indexArr = jTableFastWZW.getSelectedRow();
            TableModel model = jTableFastWZW.getModel();
            String emailCzyt = (model.getValueAt(indexArr, 2).toString());
            
            searchTable(" AND ks.tytul = '" + tytul + "'", emailCzyt, "65", jTableWypozyczeniaListaZyczenWZW);
         }
    }//GEN-LAST:event_jButtonITytulWZWActionPerformed

    private void jButtonWyswielKsiazkiWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielKsiazkiWZWActionPerformed
        
        if(jScrollPane5.isVisible()){
            queryToSend.add("43");

            Client.readWriteServer(this, queryToSend, jTableWypozyczeniaKsiazkiWZW, null);
        }else{
            readTableWZW(jTableWypozyczeniaListaZyczenWZW, "AND lz.nazwa_statusu = 'Gotowa do odbioru'", "64");
         }
    }//GEN-LAST:event_jButtonWyswielKsiazkiWZWActionPerformed

    private void jComboBoxAutorWZWItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxAutorWZWItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAutorWZWItemStateChanged

    private void jComboBoxAutorWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAutorWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAutorWZWActionPerformed

    private void jTableFastWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFastWZWMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableFastWZWMouseExited

    private void jTabbedPanelBibliotekarzMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPanelBibliotekarzMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPanelBibliotekarzMouseClicked

    private void jTableFastWZWFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableFastWZWFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableFastWZWFocusLost

    private void jTableFastWZWFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableFastWZWFocusGained

    }//GEN-LAST:event_jTableFastWZWFocusGained

    private void jTableFastWZWKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableFastWZWKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableFastWZWKeyTyped

    private void jTableFastWZWPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFastWZWPropertyChange
        jPanelMenuWZW.setVisible(false);
    }//GEN-LAST:event_jTableFastWZWPropertyChange

    private void jTextFieldRokWydaniaKFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldRokWydaniaKFocusGained
        dateGained(jTextFieldRokWydaniaK, "rrrr");
    }//GEN-LAST:event_jTextFieldRokWydaniaKFocusGained

    private void jTextFieldRokWydaniaKFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldRokWydaniaKFocusLost
        dateLost(jTextFieldRokWydaniaK, "rrrr");
    }//GEN-LAST:event_jTextFieldRokWydaniaKFocusLost

    private void jTextFieldDataUrAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDataUrAFocusGained
        dateGained(jTextFieldDataUrA, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldDataUrAFocusGained

    private void jTextFieldDataUrAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDataUrAFocusLost
        dateLost(jTextFieldDataUrA, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldDataUrAFocusLost

    private void jTextFieldIDataSmAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDataSmAFocusGained
        dateGained(jTextFieldIDataSmA, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldIDataSmAFocusGained

    private void jTextFieldIDataSmAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDataSmAFocusLost
        dateLost(jTextFieldIDataSmA, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldIDataSmAFocusLost

    private void jButtonWypozyczWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWypozyczWZWActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulWZW.getText();       

        if(tytul.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Wymagane pole jest puste","Uzupelnij puste pole",2);
        }else{
            queryToSend.add("25");                      //checkKsiazka
            queryToSend.add(tytul);
            if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                System.out.println("istnieje juz ksiazka o tytule i jes jej wiecej niz 0: '" + tytul + "'\n");      //czy 0 bedzie blokowac przycisk 
                
                int indexArr = jTableFastWZW.getSelectedRow();
                TableModel model2 = jTableFastWZW.getModel();

                String emailCzyt = (model2.getValueAt(indexArr, 2).toString()); 
                
                queryToSend.add("50");                  //checkWYpozyczenie
                queryToSend.add(emailCzyt);
                queryToSend.add(tytul);
                queryToSend.add("wypożyczona");
                
                if(Client.readWriteServer(this, queryToSend, null, null) == 0){
                    System.out.println("nie istnieje ksiazka w wypozyczeniu");
                    
                    TableModel model1;

                    if(jScrollPane5.isVisible()){
                        indexArr = jTableWypozyczeniaKsiazkiWZW.getSelectedRow();
                        model1 = jTableWypozyczeniaKsiazkiWZW.getModel();
                    }else{
                        indexArr = jTableWypozyczeniaListaZyczenWZW.getSelectedRow();
                        model1 = jTableWypozyczeniaListaZyczenWZW.getModel();
                    }
                    
                    String tytulKsi = (model1.getValueAt(indexArr, 0).toString());
                          
                    String emailBibl = jTextFieldEmail.getText();

                        queryToSend.add("45");                  //insertWypozyczenie
                        queryToSend.add("- 1");
                        queryToSend.add(tytulKsi);
                        queryToSend.add(emailCzyt);
                        queryToSend.add(emailBibl);
                        queryToSend.add("wypożyczona");

                        if(Client.readWriteServer(this, queryToSend, jTableWypozyczeniaWZW, null) == 0){
                            String logInfo = "Bibliotekarzowi [" + emailUser + "] nie udalo sie dodac wypozyczenia";
                            sendLog(logInfo, jTextAreaWZW);
                        }else{
                            String logInfo = "Bibliotekarz [" + emailUser + "] wydal ksiazke [" + tytul + "] czytelnikowi: [" + emailCzyt + "]";
                            sendLog(logInfo, jTextAreaWZW);
                            
                            
                            queryToSend.add("63");              //checkListaZyczen
                            queryToSend.add(tytulKsi);
                            queryToSend.add(emailCzyt);
                            
                            if(Client.readWriteServer(this, queryToSend, jTableWypozyczeniaListaZyczenWZW, null) == 1){
                                System.out.println("jest na liscie");
                                
                                queryToSend.add("66");          //deleteListaZyczen
                                queryToSend.add(tytulKsi);
                                queryToSend.add(emailCzyt);
                            
                                 if(Client.readWriteServer(this, queryToSend, jTableWypozyczeniaListaZyczenWZW, null) == 1){
                                    System.out.println("usunieto");   
                                    readTableWZW(jTableWypozyczeniaListaZyczenWZW, "AND lz.nazwa_statusu = 'Gotowa do odbioru'", "64");
                                 }else{
                                     System.out.println("nie usunieto");    
                                 }
                                
                                
                            }

                            queryToSend.add("46");
                            queryToSend.add(emailCzyt);
                            queryToSend.add("wypożyczona");
                            Client.readWriteServer(this, queryToSend, jTableWypozyczeniaWZW, null);

                            queryToSend.add("43");
                            Client.readWriteServer(this, queryToSend, jTableWypozyczeniaKsiazkiWZW, null);
                        }
                }else{
                     JOptionPane.showMessageDialog(null, "Ten czytelnik już wypożyczył tę książkę","Zmień książkę",2);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Nie ma takiej pozycji na liscie","Wpisz poprawna nazwe lub wybierz ja z listy",2);
            }
        }
    }//GEN-LAST:event_jButtonWypozyczWZWActionPerformed

    private void jTextFieldTytulZWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTytulZWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTytulZWZWActionPerformed

    private void jButtonDataZWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataZWZWMouseEntered
        jTextFieldDataZWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonDataZWZWMouseEntered

    private void jButtonDataZWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataZWZWMouseExited
        jTextFieldDataZWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonDataZWZWMouseExited

    private void jButtonDataZWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDataZWZWActionPerformed
        String dataU = jTextFieldDataZWZW.getText();
        if(!isCorrectFullDate(dataU)){
                JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
        }
        else{
            dataU = "TO_DATE('" + dataU + "', 'dd-mm-yyyy')";

            searchParamTable(dataU + " AND nazwa_statusu = 'zwrócona'", "data_wypozyczenia", 2, "52", jTableFastWZW, jTableZwrotyWZW);
        }
    }//GEN-LAST:event_jButtonDataZWZWActionPerformed

    private void jButtonTytulZWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulZWZWMouseEntered
        jTextFieldTytulZWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonTytulZWZWMouseEntered

    private void jButtonTytulZWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulZWZWMouseExited
        jTextFieldTytulZWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonTytulZWZWMouseExited

    private void jButtonTytulZWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTytulZWZWActionPerformed
        String tytul = jTextFieldTytulZWZW.getText();

        searchParamTable("'" + tytul + "' AND nazwa_statusu = 'zwrócona'", "tytul", 2, "52", jTableFastWZW, jTableZwrotyWZW);
    }//GEN-LAST:event_jButtonTytulZWZWActionPerformed

    private void jButtonWyswielZWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielZWZWActionPerformed
        readTableWZW(jTableZwrotyWZW, "zwrócona", "53");
    }//GEN-LAST:event_jButtonWyswielZWZWActionPerformed

    private void jTextFieldDataZWZWFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDataZWZWFocusGained
        dateGained(jTextFieldDataZWZW, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldDataZWZWFocusGained

    private void jTextFieldDataZWZWFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDataZWZWFocusLost
        dateLost(jTextFieldDataZWZW, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldDataZWZWFocusLost

    private void jTextFieldDataZWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataZWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDataZWZWActionPerformed

    private void jButtonWypozyczWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWypozyczWZWMouseEntered
        jTextFieldTytulWZW.setBackground(newBlue);
        jComboBoxAutorWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonWypozyczWZWMouseEntered

    private void jButtonWypozyczWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWypozyczWZWMouseExited
        jTextFieldTytulWZW.setBackground(Color.WHITE);
        jComboBoxAutorWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonWypozyczWZWMouseExited

    private void jTableZwrotyWZWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableZwrotyWZWMouseClicked
        int indexArr = jTableZwrotyWZW.getSelectedRow();
        TableModel model = jTableZwrotyWZW.getModel();

        jTextFieldTytulZWZW.setText(model.getValueAt(indexArr, 0).toString());
        jTextFieldDataZWZW.setForeground(Color.black);
        jTextFieldDataZWZW.setText(model.getValueAt(indexArr, 2).toString());
        jPanelDetailsWZW.setVisible(true);
    }//GEN-LAST:event_jTableZwrotyWZWMouseClicked

    private void jTableZwrotyWZWPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableZwrotyWZWPropertyChange
        jPanelDetailsWZW.setVisible(false);
    }//GEN-LAST:event_jTableZwrotyWZWPropertyChange

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseEntered

    private void jTextFieldDatalWWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDatalWWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDatalWWZWActionPerformed

    private void jTextFieldDatalWWZWFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDatalWWZWFocusLost
        dateLost(jTextFieldDatalWWZW, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldDatalWWZWFocusLost

    private void jTextFieldDatalWWZWFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDatalWWZWFocusGained
        dateGained(jTextFieldDatalWWZW, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldDatalWWZWFocusGained

    private void jButtonWyswielWWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielWWZWActionPerformed
        // TODO add your handling code here:
        readTableWZW(jTableWypozyczeniaWZW, "wypożyczona", "46");
    }//GEN-LAST:event_jButtonWyswielWWZWActionPerformed

    private void jButtonTytulWWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTytulWWZWActionPerformed
        String tytul = jTextFieldTytulWWZW.getText();

        searchParamTable("'" + tytul + "' AND nazwa_statusu = 'wypożyczona'", "tytul", 2, "47", jTableFastWZW, jTableWypozyczeniaWZW);
    }//GEN-LAST:event_jButtonTytulWWZWActionPerformed

    private void jButtonTytulWWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulWWZWMouseExited
        jTextFieldTytulWWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonTytulWWZWMouseExited

    private void jButtonTytulWWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulWWZWMouseEntered
        jTextFieldTytulWWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonTytulWWZWMouseEntered

    private void jButtonDataWWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDataWWZWActionPerformed
        String dataU = jTextFieldDatalWWZW.getText();
        if(!isCorrectFullDate(dataU)){
            JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
        }
        else{
            dataU = "TO_DATE('" + dataU + "', 'dd-mm-yyyy')";

            searchParamTable(dataU + " AND nazwa_statusu = 'wypożyczona'", "data_wypozyczenia", 2, "47", jTableFastWZW, jTableWypozyczeniaWZW);
        }
    }//GEN-LAST:event_jButtonDataWWZWActionPerformed

    private void jButtonDataWWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataWWZWMouseExited
        jTextFieldDatalWWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonDataWWZWMouseExited

    private void jButtonDataWWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataWWZWMouseEntered
        jTextFieldDatalWWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonDataWWZWMouseEntered

    private void jButtonZwrocWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZwrocWZWActionPerformed
        String tytul = jTextFieldTytulWWZW.getText();
        String dateW = jTextFieldDatalWWZW.getText();

        if(tytul.trim().equals("") || dateW.trim().equals("dd-mm-rrrr"))
        {
            JOptionPane.showMessageDialog(null, "Jedno lub więcej pól jest puste","Uzupelnij puste pola",2);
        }else if(!isCorrectFullDate(dateW)){
            JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
        }else{

            int indexArr = jTableFastWZW.getSelectedRow();
            TableModel model2 = jTableFastWZW.getModel();

            String emailCzyt = (model2.getValueAt(indexArr, 2).toString());

            queryToSend.add("50");                  //checkWYpozyczenie
            queryToSend.add(emailCzyt);
            queryToSend.add(tytul);
            queryToSend.add("wypożyczona");

            int checkId = Client.readWriteServer(this, queryToSend, null, null);
            if(checkId > 0){
                System.out.println("istnieje juz ksiazka w wypozyczeniu: '" + tytul + "'\n");      //czy 0 bedzie blokowac przycisk
                LocalDate dateUp = LocalDate.now();

                queryToSend.add("48");              //updateWypozyczenie na podstawie tytulu bo tylko raz mozna wypozyczyc
                queryToSend.add("+ 1");
                queryToSend.add(tytul);
                queryToSend.add(String.valueOf(checkId));
                queryToSend.add("termin_zwrotu = to_date('" + dateUp.format(dateUpdate) + "', 'DD.MM.YYYY')");
                queryToSend.add(", nazwa_statusu = 'zwrócona'");

                if(Client.readWriteServer(this, queryToSend, jTableWypozyczeniaWZW, null) == 0){
                    String logInfo = "Bibliotekarzowi [" + emailUser + "] Nie udalo sie zwrocic ksiazki";
                    sendLog(logInfo, jTextAreaWZW);
                }else{
                    String logInfo = "Bibliotekarz ["+ emailUser + "] przyjal zwrot ksiazki [" + tytul + "] czytelnika: [" + emailCzyt + "]";                  
                    sendLog(logInfo, jTextAreaWZW);
                    
                    queryToSend.add("46");          //selectAllWypozyczenie
                    queryToSend.add(emailCzyt);
                    queryToSend.add("wypożyczona");
                    Client.readWriteServer(this, queryToSend, jTableWypozyczeniaWZW, null);

                    queryToSend.add("43");          //selectAllKsiazka
                    Client.readWriteServer(this, queryToSend, jTableWypozyczeniaKsiazkiWZW, null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nie ma takiej pozycji na liscie","Wpisz poprawna nazwe lub wybierz ja z listy",2);
            }
        }
    }//GEN-LAST:event_jButtonZwrocWZWActionPerformed

    private void jButtonZwrocWZWMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonZwrocWZWMouseExited
        jTextFieldTytulWWZW.setBackground(Color.WHITE);
        jTextFieldDatalWWZW.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonZwrocWZWMouseExited

    private void jButtonZwrocWZWMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonZwrocWZWMouseEntered
        jTextFieldTytulWWZW.setBackground(newBlue);
        jTextFieldDatalWWZW.setBackground(newBlue);
    }//GEN-LAST:event_jButtonZwrocWZWMouseEntered

    private void jTextFieldTytulWWZWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTytulWWZWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTytulWWZWActionPerformed

    private void jTableWypozyczeniaWZWPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaWZWPropertyChange
        jButtonZwrocWZW.setEnabled(false);
        jPanelDetailsWZW.setVisible(false);
    }//GEN-LAST:event_jTableWypozyczeniaWZWPropertyChange

    private void jTableWypozyczeniaWZWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaWZWMouseClicked
        int indexArr = jTableWypozyczeniaWZW.getSelectedRow();
        TableModel model = jTableWypozyczeniaWZW.getModel();

        jTextFieldTytulWWZW.setText(model.getValueAt(indexArr, 0).toString());
        jTextFieldDatalWWZW.setForeground(Color.black);
        jTextFieldDatalWWZW.setText(model.getValueAt(indexArr, 2).toString());

        jButtonZwrocWZW.setEnabled(true);
        jPanelDetailsWZW.setVisible(true);

        LocalDate dOne = LocalDate.now();
        LocalDate dTwo = LocalDate.parse(model.getValueAt(indexArr, 3).toString(), dateUpdate);
        int diff = dTwo.compareTo(dOne);
        String day;

        if(diff > 1 || diff < -1){
            day = " dni";
        }else if(diff == 1 || diff == -1){
            day = " dzień";
        }else{
            day = " dnia";
        }

        jLabelSzczegolyInfo.setText("Do zwrotu książki pozostało: " + diff + day);

        if(diff < 0){
            jLabelSzczegolyOpl.setText("Naliczona opłata: ");
        } else {
            jLabelSzczegolyOpl.setText("");
        }

    }//GEN-LAST:event_jTableWypozyczeniaWZWMouseClicked

    private void jTableWypozyczeniaWZWFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaWZWFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableWypozyczeniaWZWFocusLost

    private void jPanelWypozyczeniaComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelWypozyczeniaComponentShown

    }//GEN-LAST:event_jPanelWypozyczeniaComponentShown

    private void jPanelWypozyczeniaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelWypozyczeniaComponentHidden
        System.out.println("jest nieaktywna");
    }//GEN-LAST:event_jPanelWypozyczeniaComponentHidden

    private void jPanelMenuKWZW2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelMenuKWZW2ComponentShown
        // TODO add your handling code here:
        readTableWZW(jTableZwrotyWZW, "zwrócona", "53");
    }//GEN-LAST:event_jPanelMenuKWZW2ComponentShown

    private void jTableWypozyczeniaListaZyczenWZWPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaListaZyczenWZWPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableWypozyczeniaListaZyczenWZWPropertyChange

    private void jTableWypozyczeniaListaZyczenWZWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaListaZyczenWZWMouseClicked
        // TODO add your handling code here:
        int indexArr = jTableWypozyczeniaListaZyczenWZW.getSelectedRow();
        TableModel model = jTableWypozyczeniaListaZyczenWZW.getModel();

        jTextFieldTytulWZW.setText(model.getValueAt(indexArr, 0).toString());     
        jButtonWypozyczWZW.setEnabled(true);
    }//GEN-LAST:event_jTableWypozyczeniaListaZyczenWZWMouseClicked

    private void jPanelListaZyczenComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelListaZyczenComponentShown
        // TODO add your handling code here:
        readTableWZW(jTableWypozyczeniaListaZyczenWZW, "AND lz.nazwa_statusu = 'Gotowa do odbioru'", "64");
        
        jButtonNazwiskoWZW1.setEnabled(false);
        jComboBoxAutorWZW.setEnabled(false);
        jLabelNazwiskoC2.setForeground(Color.gray);
    }//GEN-LAST:event_jPanelListaZyczenComponentShown

    private void jPanelListaZyczenComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelListaZyczenComponentHidden
        // TODO add your handling code here:
        jButtonNazwiskoWZW1.setEnabled(true);
        jComboBoxAutorWZW.setEnabled(true);
        jLabelNazwiskoC2.setForeground(Color.black);
    }//GEN-LAST:event_jPanelListaZyczenComponentHidden

    private void jTableWypozyczeniaKsiazkiWZWPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaKsiazkiWZWPropertyChange
        jButtonWypozyczWZW.setEnabled(false);
    }//GEN-LAST:event_jTableWypozyczeniaKsiazkiWZWPropertyChange

    private void jTableWypozyczeniaKsiazkiWZWMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaKsiazkiWZWMouseClicked
        int indexArr = jTableWypozyczeniaKsiazkiWZW.getSelectedRow();
        TableModel model = jTableWypozyczeniaKsiazkiWZW.getModel();

        jTextFieldTytulWZW.setText(model.getValueAt(indexArr, 0).toString());
        jComboBoxAutorWZW.getModel().setSelectedItem(model.getValueAt(indexArr, 1).toString() + " " + model.getValueAt(indexArr, 2).toString());

        jButtonWypozyczWZW.setEnabled(true);
    }//GEN-LAST:event_jTableWypozyczeniaKsiazkiWZWMouseClicked

    private void jScrollPane5ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane5ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane5ComponentShown

    private void jPanelKsiazkiComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelKsiazkiComponentShown
        // TODO add your handling code here:
        queryToSend.add("11");

        Client.readWriteServer(this, queryToSend, jTableKsiazkiK, null);
    }//GEN-LAST:event_jPanelKsiazkiComponentShown

    private void jPanelAutorzyComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAutorzyComponentShown
        // TODO add your handling code here:
        queryToSend.add("30");

        Client.readWriteServer(this, queryToSend, jTableAutorzyA, null);
    }//GEN-LAST:event_jPanelAutorzyComponentShown

    private void jPanelCzytelnicyComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelCzytelnicyComponentShown
        // TODO add your handling code here:
        queryToSend.add("35");
        Client.readWriteServer(this, queryToSend, jTableCzytelnicyCz, null);
    }//GEN-LAST:event_jPanelCzytelnicyComponentShown

    private void jPanelWZWComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelWZWComponentShown
        // TODO add your handling code here:
        queryToSend.add("39");

        Client.readWriteServer(this, queryToSend, jTableFastWZW, null);
    }//GEN-LAST:event_jPanelWZWComponentShown

    /** 
    * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAktualizujC;
    private javax.swing.JButton jButtonAktualizujK;
    private javax.swing.JButton jButtonAktualizujS;
    private javax.swing.JButton jButtonAutorSK;
    private javax.swing.JButton jButtonDataSmA;
    private javax.swing.JButton jButtonDataUrA;
    private javax.swing.JButton jButtonDataWWZW;
    private javax.swing.JButton jButtonDataZWZW;
    private javax.swing.JButton jButtonEmailWZW;
    private javax.swing.JButton jButtonITytulWZW;
    private javax.swing.JButton jButtonImieA;
    private javax.swing.JButton jButtonImieC;
    private javax.swing.JButton jButtonImieWZW;
    private javax.swing.JButton jButtonKategoriaSK;
    private javax.swing.JButton jButtonKrajA;
    private javax.swing.JButton jButtonKrajA3;
    private javax.swing.JButton jButtonMiastoA;
    private javax.swing.JButton jButtonNazwiskoA;
    private javax.swing.JButton jButtonNazwiskoC;
    private javax.swing.JButton jButtonNazwiskoWZW;
    private javax.swing.JButton jButtonNazwiskoWZW1;
    private javax.swing.JButton jButtonRokSK;
    private javax.swing.JButton jButtonTelefonC;
    private javax.swing.JButton jButtonTytulSK;
    private javax.swing.JButton jButtonTytulWWZW;
    private javax.swing.JButton jButtonTytulZWZW;
    private javax.swing.JButton jButtonUDodajK;
    private javax.swing.JButton jButtonUDodajS;
    private javax.swing.JButton jButtonUsunC;
    private javax.swing.JButton jButtonUsunK;
    private javax.swing.JButton jButtonUsunS;
    private javax.swing.JButton jButtonWydawnictwo;
    private javax.swing.JButton jButtonWydawnictwoSK;
    private javax.swing.JButton jButtonWypozyczWZW;
    private javax.swing.JButton jButtonWyswielC;
    private javax.swing.JButton jButtonWyswielK;
    private javax.swing.JButton jButtonWyswielKsiazkiWZW;
    private javax.swing.JButton jButtonWyswielS;
    private javax.swing.JButton jButtonWyswielWWZW;
    private javax.swing.JButton jButtonWyswielWZW;
    private javax.swing.JButton jButtonWyswielZWZW;
    private javax.swing.JButton jButtonWyszukajWZW;
    private javax.swing.JButton jButtonZwrocWZW;
    private javax.swing.JComboBox<String> jComboBoxAutorK;
    private javax.swing.JComboBox<String> jComboBoxAutorWZW;
    private javax.swing.JComboBox<String> jComboBoxKategoriaK;
    private javax.swing.JComboBox<String> jComboBoxWydawnictwoK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAutor;
    private javax.swing.JLabel jLabelDataSmA;
    private javax.swing.JLabel jLabelDataUrA;
    private javax.swing.JLabel jLabelEmailC;
    private javax.swing.JLabel jLabelEmailWZW;
    private javax.swing.JLabel jLabelIlosc;
    private javax.swing.JLabel jLabelImieA;
    private javax.swing.JLabel jLabelImieC;
    private javax.swing.JLabel jLabelImieWZW;
    private javax.swing.JLabel jLabelImieWZW1;
    private javax.swing.JLabel jLabelImieWZW2;
    private javax.swing.JLabel jLabelImieWZW3;
    private javax.swing.JLabel jLabelKategoria;
    private javax.swing.JLabel jLabelKrajA;
    private javax.swing.JLabel jLabelMiastoA;
    private javax.swing.JLabel jLabelNazwiskoA;
    private javax.swing.JLabel jLabelNazwiskoC;
    private javax.swing.JLabel jLabelNazwiskoC1;
    private javax.swing.JLabel jLabelNazwiskoC2;
    private javax.swing.JLabel jLabelNazwiskoC3;
    private javax.swing.JLabel jLabelNazwiskoC4;
    private javax.swing.JLabel jLabelRokWydania;
    private javax.swing.JLabel jLabelSzczegolyInfo;
    private javax.swing.JLabel jLabelSzczegolyOpl;
    private javax.swing.JLabel jLabelTelefonC;
    private javax.swing.JLabel jLabelTytul;
    private javax.swing.JLabel jLabelWydawnictwo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAutorzy;
    private javax.swing.JPanel jPanelConsoleK;
    private javax.swing.JPanel jPanelConsoleK1;
    private javax.swing.JPanel jPanelConsoleK11;
    private javax.swing.JPanel jPanelConsoleK2;
    private javax.swing.JPanel jPanelCzytelnicy;
    private javax.swing.JPanel jPanelDetailsWZW;
    private javax.swing.JPanel jPanelKsiazki;
    private javax.swing.JPanel jPanelListaZyczen;
    private javax.swing.JPanel jPanelMenuK;
    private javax.swing.JPanel jPanelMenuK3;
    private javax.swing.JPanel jPanelMenuK6;
    private javax.swing.JPanel jPanelMenuK7;
    private javax.swing.JPanel jPanelMenuKWZW;
    private javax.swing.JPanel jPanelMenuKWZW1;
    private javax.swing.JPanel jPanelMenuKWZW2;
    private javax.swing.JPanel jPanelMenuWZW;
    private javax.swing.JPanel jPanelWZW;
    private javax.swing.JPanel jPanelWypozyczenia;
    private javax.swing.JPanel jPanelZwroty;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPaneDB_Console17;
    private javax.swing.JScrollPane jScrollPaneDB_Console3;
    private javax.swing.JScrollPane jScrollPaneDB_Console5;
    private javax.swing.JScrollPane jScrollPaneDB_Console8;
    private javax.swing.JScrollPane jScrollPaneTableB;
    private javax.swing.JScrollPane jScrollPaneTableB5;
    private javax.swing.JScrollPane jScrollPaneTableB6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPanelBibliotekarz;
    private javax.swing.JTable jTableAutorzyA;
    private javax.swing.JTable jTableCzytelnicyCz;
    private javax.swing.JTable jTableFastWZW;
    private javax.swing.JTable jTableKsiazkiK;
    private javax.swing.JTable jTableWypozyczeniaKsiazkiWZW;
    private javax.swing.JTable jTableWypozyczeniaListaZyczenWZW;
    private javax.swing.JTable jTableWypozyczeniaWZW;
    private javax.swing.JTable jTableZwrotyWZW;
    private javax.swing.JTextArea jTextAreaC;
    private javax.swing.JTextArea jTextAreaK;
    private javax.swing.JTextArea jTextAreaS;
    private javax.swing.JTextArea jTextAreaWZW;
    private javax.swing.JTextField jTextFieldDataUrA;
    private javax.swing.JTextField jTextFieldDataZWZW;
    private javax.swing.JTextField jTextFieldDatalWWZW;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldIDataSmA;
    private javax.swing.JTextField jTextFieldIEmailC;
    private javax.swing.JTextField jTextFieldIEmailWZW;
    private javax.swing.JTextField jTextFieldIKrajA;
    private javax.swing.JTextField jTextFieldIMiastoA;
    private javax.swing.JTextField jTextFieldINazwiskoA;
    private javax.swing.JTextField jTextFieldINazwiskoC;
    private javax.swing.JTextField jTextFieldINazwiskoWZW;
    private javax.swing.JTextField jTextFieldITelefonC;
    private javax.swing.JTextField jTextFieldIloscK;
    private javax.swing.JTextField jTextFieldImieA;
    private javax.swing.JTextField jTextFieldImieC;
    private javax.swing.JTextField jTextFieldImieWZW;
    private javax.swing.JTextField jTextFieldRokWydaniaK;
    private javax.swing.JTextField jTextFieldTytulK;
    private javax.swing.JTextField jTextFieldTytulWWZW;
    private javax.swing.JTextField jTextFieldTytulWZW;
    private javax.swing.JTextField jTextFieldTytulZWZW;
    // End of variables declaration//GEN-END:variables
}

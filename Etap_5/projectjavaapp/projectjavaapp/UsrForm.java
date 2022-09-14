package projectjavaapp;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import static projectjavaapp.LibForm.dateForm;
import static projectjavaapp.LoginForm.queryToSend;
/**
 *
 * @author CLEVO
 */
public class UsrForm extends javax.swing.JFrame {
    
    static String emailUser = null;

    /**
     * Creates new form CztForm
     * @param emailUser
     */
    public UsrForm(String emailUser) {
        UsrForm.emailUser = emailUser;
        initComponents();
        initialInit();
    }
    
    public void clearAllFields(){
        jTextFieldTytulWCZ.setText("");
        jTextFieldEmailPracWCZ.setText("");
        jTextFieldDataWypCZ.setForeground(Color.gray);
        jTextFieldDataWypCZ.setText("dd-mm-rrrr");
        jTextFieldTerminCZ.setForeground(Color.gray);
        jTextFieldTerminCZ.setText("dd-mm-rrrr");     
        jTextFieldTerminZwrCZ.setForeground(Color.gray);
        jTextFieldTerminZwrCZ.setText("dd-mm-rrrr");
    }
    
    private void searchParamTable(String sear, String text, String que, String parm, JTable table){
    if(sear.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Pole do wyszukiwania jest puste.","Uzupelnij puste pole",2);
        }
        else{      
            queryToSend.add(que);
            queryToSend.add(parm);
            queryToSend.add(text);
            queryToSend.add(sear);
            
            System.out.println(parm);
            System.out.println(text);
            System.out.println(sear);
            
            Client.readWriteServer(this, queryToSend, table, null);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    
    public void sendLog(String logInfo, JTextArea textArea){
                    LocalDateTime date = LocalDateTime.now();
                    queryToSend.add("60");                  //sendLog
                    queryToSend.add(date.format(dateForm));
                    queryToSend.add(logInfo);
                     if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                         textArea.append(date.format(dateForm) + ": " + logInfo + "\n");
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
    
    private boolean isCorrectFullDate(String string){
    boolean isValidFormat = string.matches("([0-9]{2})-([0-1][0-9])-([0-9]{4})");
    return isValidFormat;
  }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void checkAvailability(){  
        
        queryToSend.add("67");  //0     czechListaZyczen
        queryToSend.add(emailUser);
        queryToSend.add("Obecnie niedostępna");
        if(Client.readWriteServer(this, queryToSend, null, null) == 1){
            System.out.println("brak niedostepnych");
            
        }else{
            System.out.println("sa niedostepne");
        }
    }

    private void initialInit(){
        jTextFieldEmailCZ.setText(emailUser);
        
        queryToSend.add("16");        
        Client.readWriteServer(this, queryToSend, null, jComboBoxAutorCZ);
        
        queryToSend.add("17");        
        Client.readWriteServer(this, queryToSend, null, jComboBoxWydawnictwoCZ); 
        
        queryToSend.add("18");        
        Client.readWriteServer(this, queryToSend, null, jComboBoxKategoriaCZ);
        
        queryToSend.add("51");
        queryToSend.add(emailUser);
        queryToSend.add("zwrócona");
        Client.readWriteServer(this, queryToSend, jTableZwrotyCZ, null);     
        
        queryToSend.add("46");
        queryToSend.add(emailUser);
        queryToSend.add("wypożyczona");
        Client.readWriteServer(this, queryToSend, jTableWypozyczeniaCZ, null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEmailCZ = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTabbedPaneCzytelnik = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelMenuCZ = new javax.swing.JPanel();
        jLabelTytul = new javax.swing.JLabel();
        jTextFieldTytulCZ = new javax.swing.JTextField();
        jLabelAutor = new javax.swing.JLabel();
        jComboBoxAutorCZ = new javax.swing.JComboBox<>();
        jLabelKategoria = new javax.swing.JLabel();
        jComboBoxKategoriaCZ = new javax.swing.JComboBox<>();
        jButtonIloscSCZ = new javax.swing.JButton();
        jButtonRokSCZ = new javax.swing.JButton();
        jButtonKategoriaSCZ = new javax.swing.JButton();
        jButtonAutorSCZ = new javax.swing.JButton();
        jButtonTytulSCZ = new javax.swing.JButton();
        jButtonWydawnictwoSCZ = new javax.swing.JButton();
        jLabelWydawnictwo = new javax.swing.JLabel();
        jComboBoxWydawnictwoCZ = new javax.swing.JComboBox<>();
        jLabelRokWydania = new javax.swing.JLabel();
        jTextFieldRokWydaniaCZ = new javax.swing.JTextField();
        jLabelIlosc = new javax.swing.JLabel();
        jTextFieldIloscCZ = new javax.swing.JTextField();
        jButtonWyswielCZ = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanelSubmenuK = new javax.swing.JPanel();
        jButtonUDodajCZ = new javax.swing.JButton();
        jLabelTytul2 = new javax.swing.JLabel();
        jTextFieldTytulSelectCZ = new javax.swing.JTextField();
        jScrollPaneTableB = new javax.swing.JScrollPane();
        jTableKsiazkiCZ = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanelMenuCZ1 = new javax.swing.JPanel();
        jLabelTytulCZ = new javax.swing.JLabel();
        jTextFieldTytulWCZ = new javax.swing.JTextField();
        jLabelEmailPracCZ = new javax.swing.JLabel();
        jLabelDataWypCZ = new javax.swing.JLabel();
        jButtonTerminZwrSWCZ = new javax.swing.JButton();
        jButtonDataWypSWCZ = new javax.swing.JButton();
        jButtonEmailPracSWCZ = new javax.swing.JButton();
        jButtonTytulSWCZ = new javax.swing.JButton();
        jButtonTerminSWCZ = new javax.swing.JButton();
        jLabelTerminCZ = new javax.swing.JLabel();
        jLabelTerminZwrCZ = new javax.swing.JLabel();
        jTextFieldEmailPracWCZ = new javax.swing.JTextField();
        jTextFieldDataWypCZ = new javax.swing.JTextField();
        jTextFieldTerminCZ = new javax.swing.JTextField();
        jTextFieldTerminZwrCZ = new javax.swing.JTextField();
        jButtonWyswielLCZ = new javax.swing.JTabbedPane();
        jPanelMenuWypCZ = new javax.swing.JPanel();
        jButtonWyswielWCZ = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableWypozyczeniaCZ = new javax.swing.JTable();
        jPanelMenuZwrCZ = new javax.swing.JPanel();
        jButtonWyswielZCZ = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableZwrotyCZ = new javax.swing.JTable();
        jPanelMenuLisZyczCZ = new javax.swing.JPanel();
        jButtonWyswielL = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableListaZyczenCZ = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("zalogowany jako: ");

        jTextFieldEmailCZ.setEditable(false);
        jTextFieldEmailCZ.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextFieldEmailCZ.setBorder(null);
        jTextFieldEmailCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailCZActionPerformed(evt);
            }
        });

        jButton3.setText("WYLOGUJ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmailCZ)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldEmailCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });

        jPanelMenuCZ.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj"));

        jLabelTytul.setText("Tytuł");

        jTextFieldTytulCZ.setPreferredSize(new java.awt.Dimension(120, 22));
        jTextFieldTytulCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTytulCZActionPerformed(evt);
            }
        });

        jLabelAutor.setText("Autor");

        jComboBoxAutorCZ.setPreferredSize(new java.awt.Dimension(120, 22));
        jComboBoxAutorCZ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxAutorCZItemStateChanged(evt);
            }
        });
        jComboBoxAutorCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAutorCZActionPerformed(evt);
            }
        });

        jLabelKategoria.setText("Kategoria");

        jComboBoxKategoriaCZ.setPreferredSize(new java.awt.Dimension(120, 22));
        jComboBoxKategoriaCZ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxKategoriaCZItemStateChanged(evt);
            }
        });
        jComboBoxKategoriaCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxKategoriaCZActionPerformed(evt);
            }
        });

        jButtonIloscSCZ.setText("jButton1");
        jButtonIloscSCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonIloscSCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonIloscSCZMouseExited(evt);
            }
        });
        jButtonIloscSCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIloscSCZActionPerformed(evt);
            }
        });

        jButtonRokSCZ.setText("jButton1");
        jButtonRokSCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonRokSCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonRokSCZMouseExited(evt);
            }
        });
        jButtonRokSCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRokSCZActionPerformed(evt);
            }
        });

        jButtonKategoriaSCZ.setText("jButton1");
        jButtonKategoriaSCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonKategoriaSCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonKategoriaSCZMouseExited(evt);
            }
        });
        jButtonKategoriaSCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKategoriaSCZActionPerformed(evt);
            }
        });

        jButtonAutorSCZ.setText("jButton1");
        jButtonAutorSCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonAutorSCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonAutorSCZMouseExited(evt);
            }
        });
        jButtonAutorSCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAutorSCZActionPerformed(evt);
            }
        });

        jButtonTytulSCZ.setText("jButton1");
        jButtonTytulSCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonTytulSCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonTytulSCZMouseExited(evt);
            }
        });
        jButtonTytulSCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTytulSCZActionPerformed(evt);
            }
        });

        jButtonWydawnictwoSCZ.setText("jButton1");
        jButtonWydawnictwoSCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonWydawnictwoSCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonWydawnictwoSCZMouseExited(evt);
            }
        });
        jButtonWydawnictwoSCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWydawnictwoSCZActionPerformed(evt);
            }
        });

        jLabelWydawnictwo.setText("Wydawnictwo");

        jComboBoxWydawnictwoCZ.setPreferredSize(new java.awt.Dimension(120, 22));
        jComboBoxWydawnictwoCZ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxWydawnictwoCZItemStateChanged(evt);
            }
        });
        jComboBoxWydawnictwoCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWydawnictwoCZActionPerformed(evt);
            }
        });

        jLabelRokWydania.setText("Rok wydania");

        jTextFieldRokWydaniaCZ.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldRokWydaniaCZ.setText("rrrr");
        jTextFieldRokWydaniaCZ.setPreferredSize(new java.awt.Dimension(120, 22));
        jTextFieldRokWydaniaCZ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldRokWydaniaCZFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldRokWydaniaCZFocusLost(evt);
            }
        });
        jTextFieldRokWydaniaCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRokWydaniaCZActionPerformed(evt);
            }
        });

        jLabelIlosc.setText("Ilość");

        jTextFieldIloscCZ.setPreferredSize(new java.awt.Dimension(120, 22));
        jTextFieldIloscCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIloscCZActionPerformed(evt);
            }
        });

        jButtonWyswielCZ.setText("W. WSZYSTKIE");
        jButtonWyswielCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielCZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuCZLayout = new javax.swing.GroupLayout(jPanelMenuCZ);
        jPanelMenuCZ.setLayout(jPanelMenuCZLayout);
        jPanelMenuCZLayout.setHorizontalGroup(
            jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuCZLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonWyswielCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMenuCZLayout.createSequentialGroup()
                        .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelIlosc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTytul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelAutor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelKategoria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelWydawnictwo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelRokWydania, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxKategoriaCZ, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTytulCZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAutorCZ, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxWydawnictwoCZ, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRokWydaniaCZ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIloscCZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonTytulSCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAutorSCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonKategoriaSCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonWydawnictwoSCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRokSCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonIloscSCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        jPanelMenuCZLayout.setVerticalGroup(
            jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuCZLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTytul)
                    .addComponent(jTextFieldTytulCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTytulSCZ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAutor)
                    .addComponent(jComboBoxAutorCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAutorSCZ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelKategoria)
                    .addComponent(jComboBoxKategoriaCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKategoriaSCZ))
                .addGap(11, 11, 11)
                .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxWydawnictwoCZ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelWydawnictwo)
                        .addComponent(jButtonWydawnictwoSCZ)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelRokWydania)
                    .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldRokWydaniaCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonRokSCZ)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIlosc)
                    .addGroup(jPanelMenuCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonIloscSCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldIloscCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonWyswielCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSubmenuK.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));

        jButtonUDodajCZ.setText("DODAJ");
        jButtonUDodajCZ.setEnabled(false);
        jButtonUDodajCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonUDodajCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonUDodajCZMouseExited(evt);
            }
        });
        jButtonUDodajCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUDodajCZActionPerformed(evt);
            }
        });

        jLabelTytul2.setForeground(new java.awt.Color(153, 153, 153));
        jLabelTytul2.setText("Wybrano:");

        jTextFieldTytulSelectCZ.setEditable(false);
        jTextFieldTytulSelectCZ.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextFieldTytulSelectCZ.setBorder(null);
        jTextFieldTytulSelectCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTytulSelectCZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSubmenuKLayout = new javax.swing.GroupLayout(jPanelSubmenuK);
        jPanelSubmenuK.setLayout(jPanelSubmenuKLayout);
        jPanelSubmenuKLayout.setHorizontalGroup(
            jPanelSubmenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSubmenuKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSubmenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSubmenuKLayout.createSequentialGroup()
                        .addComponent(jLabelTytul2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTytulSelectCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonUDodajCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanelSubmenuKLayout.setVerticalGroup(
            jPanelSubmenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSubmenuKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSubmenuKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTytul2)
                    .addComponent(jTextFieldTytulSelectCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButtonUDodajCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTableKsiazkiCZ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nr Katalogowy", "Tytuł", "Imię autora", "Nazwisko autora", "Kategoria", "Wydawnictwo", "Rok wydania", "Liczba sztuk"
            }
        ));
        jTableKsiazkiCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKsiazkiCZMouseClicked(evt);
            }
        });
        jScrollPaneTableB.setViewportView(jTableKsiazkiCZ);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSubmenuK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneTableB))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSubmenuK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTableB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelMenuCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelMenuCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 250, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneCzytelnik.addTab("Przeglądaj książki", jPanel1);

        jPanelMenuCZ1.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj"));

        jLabelTytulCZ.setText("Tytuł");

        jTextFieldTytulWCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTytulWCZActionPerformed(evt);
            }
        });

        jLabelEmailPracCZ.setText("Email prac.");

        jLabelDataWypCZ.setText("Data wyp.");

        jButtonTerminZwrSWCZ.setText("jButton1");
        jButtonTerminZwrSWCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonTerminZwrSWCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonTerminZwrSWCZMouseExited(evt);
            }
        });
        jButtonTerminZwrSWCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminZwrSWCZActionPerformed(evt);
            }
        });

        jButtonDataWypSWCZ.setText("jButton1");
        jButtonDataWypSWCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonDataWypSWCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonDataWypSWCZMouseExited(evt);
            }
        });
        jButtonDataWypSWCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDataWypSWCZActionPerformed(evt);
            }
        });

        jButtonEmailPracSWCZ.setText("jButton1");
        jButtonEmailPracSWCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonEmailPracSWCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonEmailPracSWCZMouseExited(evt);
            }
        });
        jButtonEmailPracSWCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmailPracSWCZActionPerformed(evt);
            }
        });

        jButtonTytulSWCZ.setText("jButton1");
        jButtonTytulSWCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonTytulSWCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonTytulSWCZMouseExited(evt);
            }
        });
        jButtonTytulSWCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTytulSWCZActionPerformed(evt);
            }
        });

        jButtonTerminSWCZ.setText("jButton1");
        jButtonTerminSWCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonTerminSWCZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonTerminSWCZMouseExited(evt);
            }
        });
        jButtonTerminSWCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminSWCZActionPerformed(evt);
            }
        });

        jLabelTerminCZ.setText("Termin");

        jLabelTerminZwrCZ.setText("Termin zwr.");

        jTextFieldEmailPracWCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailPracWCZActionPerformed(evt);
            }
        });

        jTextFieldDataWypCZ.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldDataWypCZ.setText("dd-mm-rrrr");
        jTextFieldDataWypCZ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDataWypCZFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDataWypCZFocusLost(evt);
            }
        });
        jTextFieldDataWypCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataWypCZActionPerformed(evt);
            }
        });

        jTextFieldTerminCZ.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldTerminCZ.setText("dd-mm-rrrr");
        jTextFieldTerminCZ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTerminCZFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldTerminCZFocusLost(evt);
            }
        });
        jTextFieldTerminCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTerminCZActionPerformed(evt);
            }
        });

        jTextFieldTerminZwrCZ.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldTerminZwrCZ.setText("dd-mm-rrrr");
        jTextFieldTerminZwrCZ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTerminZwrCZFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldTerminZwrCZFocusLost(evt);
            }
        });
        jTextFieldTerminZwrCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTerminZwrCZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuCZ1Layout = new javax.swing.GroupLayout(jPanelMenuCZ1);
        jPanelMenuCZ1.setLayout(jPanelMenuCZ1Layout);
        jPanelMenuCZ1Layout.setHorizontalGroup(
            jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuCZ1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelTytulCZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelEmailPracCZ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDataWypCZ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTerminCZ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTerminZwrCZ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldTytulWCZ)
                    .addComponent(jTextFieldEmailPracWCZ)
                    .addComponent(jTextFieldDataWypCZ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jTextFieldTerminCZ, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jTextFieldTerminZwrCZ, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonTytulSWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEmailPracSWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDataWypSWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTerminSWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTerminZwrSWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanelMenuCZ1Layout.setVerticalGroup(
            jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuCZ1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTytulCZ)
                    .addComponent(jTextFieldTytulWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTytulSWCZ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmailPracCZ)
                    .addComponent(jButtonEmailPracSWCZ)
                    .addComponent(jTextFieldEmailPracWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDataWypCZ)
                    .addComponent(jButtonDataWypSWCZ)
                    .addComponent(jTextFieldDataWypCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTerminCZ)
                    .addComponent(jButtonTerminSWCZ)
                    .addComponent(jTextFieldTerminCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuCZ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelTerminZwrCZ)
                        .addComponent(jTextFieldTerminZwrCZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonTerminZwrSWCZ))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonWyswielLCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonWyswielLCZMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonWyswielLCZMouseEntered(evt);
            }
        });
        jButtonWyswielLCZ.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jButtonWyswielLCZComponentShown(evt);
            }
        });

        jPanelMenuWypCZ.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj"));
        jPanelMenuWypCZ.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelMenuWypCZComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelMenuWypCZComponentShown(evt);
            }
        });

        jButtonWyswielWCZ.setText("W. WSZYSTKIE");
        jButtonWyswielWCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielWCZActionPerformed(evt);
            }
        });

        jTableWypozyczeniaCZ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu", "Status"
            }
        ));
        jTableWypozyczeniaCZ.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableWypozyczeniaCZFocusLost(evt);
            }
        });
        jTableWypozyczeniaCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableWypozyczeniaCZMouseClicked(evt);
            }
        });
        jTableWypozyczeniaCZ.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableWypozyczeniaCZPropertyChange(evt);
            }
        });
        jScrollPane7.setViewportView(jTableWypozyczeniaCZ);

        javax.swing.GroupLayout jPanelMenuWypCZLayout = new javax.swing.GroupLayout(jPanelMenuWypCZ);
        jPanelMenuWypCZ.setLayout(jPanelMenuWypCZLayout);
        jPanelMenuWypCZLayout.setHorizontalGroup(
            jPanelMenuWypCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuWypCZLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonWyswielWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanelMenuWypCZLayout.setVerticalGroup(
            jPanelMenuWypCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuWypCZLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonWyswielWCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonWyswielLCZ.addTab("Wypożyczenia", jPanelMenuWypCZ);

        jPanelMenuZwrCZ.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj"));
        jPanelMenuZwrCZ.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelMenuZwrCZComponentShown(evt);
            }
        });

        jButtonWyswielZCZ.setText("W. WSZYSTKIE");
        jButtonWyswielZCZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielZCZActionPerformed(evt);
            }
        });

        jTableZwrotyCZ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu", "Termin zwrotu", "Status"
            }
        ));
        jTableZwrotyCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableZwrotyCZMouseClicked(evt);
            }
        });
        jTableZwrotyCZ.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableZwrotyCZPropertyChange(evt);
            }
        });
        jScrollPane8.setViewportView(jTableZwrotyCZ);

        javax.swing.GroupLayout jPanelMenuZwrCZLayout = new javax.swing.GroupLayout(jPanelMenuZwrCZ);
        jPanelMenuZwrCZ.setLayout(jPanelMenuZwrCZLayout);
        jPanelMenuZwrCZLayout.setHorizontalGroup(
            jPanelMenuZwrCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuZwrCZLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonWyswielZCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
            .addGroup(jPanelMenuZwrCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE))
        );
        jPanelMenuZwrCZLayout.setVerticalGroup(
            jPanelMenuZwrCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuZwrCZLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonWyswielZCZ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(407, Short.MAX_VALUE))
            .addGroup(jPanelMenuZwrCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuZwrCZLayout.createSequentialGroup()
                    .addGap(0, 133, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButtonWyswielLCZ.addTab("Zwroty", jPanelMenuZwrCZ);

        jPanelMenuLisZyczCZ.setBorder(javax.swing.BorderFactory.createTitledBorder("Szukaj"));
        jPanelMenuLisZyczCZ.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanelMenuLisZyczCZComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelMenuLisZyczCZComponentShown(evt);
            }
        });

        jButtonWyswielL.setText("W. WSZYSTKIE");
        jButtonWyswielL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWyswielLActionPerformed(evt);
            }
        });

        jTableListaZyczenCZ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tytuł", "Status"
            }
        ));
        jTableListaZyczenCZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaZyczenCZMouseClicked(evt);
            }
        });
        jTableListaZyczenCZ.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableListaZyczenCZPropertyChange(evt);
            }
        });
        jScrollPane9.setViewportView(jTableListaZyczenCZ);

        javax.swing.GroupLayout jPanelMenuLisZyczCZLayout = new javax.swing.GroupLayout(jPanelMenuLisZyczCZ);
        jPanelMenuLisZyczCZ.setLayout(jPanelMenuLisZyczCZLayout);
        jPanelMenuLisZyczCZLayout.setHorizontalGroup(
            jPanelMenuLisZyczCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLisZyczCZLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonWyswielL, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
            .addGroup(jPanelMenuLisZyczCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE))
        );
        jPanelMenuLisZyczCZLayout.setVerticalGroup(
            jPanelMenuLisZyczCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLisZyczCZLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonWyswielL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(407, Short.MAX_VALUE))
            .addGroup(jPanelMenuLisZyczCZLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLisZyczCZLayout.createSequentialGroup()
                    .addGap(0, 133, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButtonWyswielLCZ.addTab("Lista życzeń", jPanelMenuLisZyczCZ);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanelMenuCZ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jButtonWyswielLCZ))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonWyswielLCZ)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanelMenuCZ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPaneCzytelnik.addTab("Wypożyczenia i zwroty", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPaneCzytelnik)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneCzytelnik, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldEmailCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailCZActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        queryToSend.add("Exit");
        if(Client.readWriteServer(this, queryToSend, null, null) == 1){
            LoginForm form = new LoginForm();
            form.setVisible(true);
            form.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldTytulWCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTytulWCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTytulWCZActionPerformed

    private void jButtonDataWypSWCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataWypSWCZMouseEntered
        // TODO add your handling code here:
        jTextFieldDataWypCZ.setBackground(LibForm.newBlue);
    }//GEN-LAST:event_jButtonDataWypSWCZMouseEntered

    private void jButtonDataWypSWCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDataWypSWCZMouseExited
        // TODO add your handling code here:
        jTextFieldDataWypCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonDataWypSWCZMouseExited

    private void jButtonDataWypSWCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDataWypSWCZActionPerformed
        // TODO add your handling code here:
         String dataU = jTextFieldDataWypCZ.getText();
        if(!isCorrectFullDate(dataU)){
                JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
        }
        else{
            dataU = "TO_DATE('" + dataU + "', 'dd-mm-yyyy')";

            if(jPanelMenuWypCZ.isVisible()){
                searchParamTable(dataU + " AND nazwa_statusu = 'wypożyczona'", "data_wypozyczenia", "47", emailUser, jTableWypozyczeniaCZ);
            }else if(jPanelMenuZwrCZ.isVisible()){
                searchParamTable(dataU + " AND nazwa_statusu = 'zwrócona'", "data_wypozyczenia", "52", emailUser, jTableZwrotyCZ);
                System.out.println("teraz");
            }
        }
        
    }//GEN-LAST:event_jButtonDataWypSWCZActionPerformed

    private void jButtonEmailPracSWCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEmailPracSWCZMouseEntered
        // TODO add your handling code here:
        jTextFieldEmailPracWCZ.setBackground(LibForm.newBlue);
    }//GEN-LAST:event_jButtonEmailPracSWCZMouseEntered

    private void jButtonEmailPracSWCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEmailPracSWCZMouseExited
        // TODO add your handling code here:
        jTextFieldEmailPracWCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonEmailPracSWCZMouseExited

    private void jButtonEmailPracSWCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmailPracSWCZActionPerformed
        // TODO add your handling code here:
        String email = jTextFieldEmailPracWCZ.getText();
        
        if(jPanelMenuWypCZ.isVisible()){
            
            searchParamTable("'" + email + "' AND nazwa_statusu = 'wypożyczona'", "email", "47", emailUser, jTableWypozyczeniaCZ);
        }else if(jPanelMenuZwrCZ.isVisible()){
            searchParamTable("'" + email + "' AND nazwa_statusu = 'zwrócona'", "email", "68", emailUser, jTableZwrotyCZ);
        }
    }//GEN-LAST:event_jButtonEmailPracSWCZActionPerformed

    private void jButtonTytulSWCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulSWCZMouseEntered
        // TODO add your handling code here:
        jTextFieldTytulWCZ.setBackground(LibForm.newBlue);
    }//GEN-LAST:event_jButtonTytulSWCZMouseEntered

    private void jButtonTytulSWCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulSWCZMouseExited
        // TODO add your handling code here:
        jTextFieldTytulWCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonTytulSWCZMouseExited

    private void jButtonTytulSWCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTytulSWCZActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulWCZ.getText();
        
        if(jPanelMenuWypCZ.isVisible()){
            searchParamTable("'" + tytul + "' AND nazwa_statusu = 'wypożyczona'", "tytul", "47", emailUser, jTableWypozyczeniaCZ);
        }else if(jPanelMenuZwrCZ.isVisible()){
            searchParamTable("'" + tytul + "' AND nazwa_statusu = 'zwrócona'", "tytul", "52", emailUser, jTableZwrotyCZ);
        }else if(jPanelMenuLisZyczCZ.isVisible()){
            searchParamTable(".", " AND ks.tytul = '" + tytul + "'", "65", emailUser, jTableListaZyczenCZ);
        }
    }//GEN-LAST:event_jButtonTytulSWCZActionPerformed

    private void jButtonTerminSWCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTerminSWCZMouseEntered
        // TODO add your handling code here:
        jTextFieldTerminCZ.setBackground(LibForm.newBlue);
    }//GEN-LAST:event_jButtonTerminSWCZMouseEntered

    private void jButtonTerminSWCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTerminSWCZMouseExited
        // TODO add your handling code here:
        jTextFieldTerminCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonTerminSWCZMouseExited

    private void jButtonTerminSWCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminSWCZActionPerformed
        // TODO add your handling code here:
        String dataU = jTextFieldTerminCZ.getText();
        if(!isCorrectFullDate(dataU)){
                JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
        }
        else{
            dataU = "TO_DATE('" + dataU + "', 'dd-mm-yyyy')";

            if(jPanelMenuWypCZ.isVisible()){
                searchParamTable(dataU + " AND nazwa_statusu = 'wypożyczona'", "termin_do_zwrotu", "47", emailUser, jTableWypozyczeniaCZ);
            }else{
                searchParamTable(dataU + " AND nazwa_statusu = 'zwrócona'", "termin_do_zwrotu", "52", emailUser, jTableZwrotyCZ);
            }
        }
    }//GEN-LAST:event_jButtonTerminSWCZActionPerformed

    private void jButtonTerminZwrSWCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminZwrSWCZActionPerformed
        // TODO add your handling code here:
        String dataU = jTextFieldTerminZwrCZ.getText();
        if(!isCorrectFullDate(dataU)){
                JOptionPane.showMessageDialog(null, "Wpisz poprawne dane wedlug wymaganego formatu","Format daty jest nieprawidlowy",2);
        }
        else{
            dataU = "TO_DATE('" + dataU + "', 'dd-mm-yyyy')";

            if(jPanelMenuZwrCZ.isVisible()){
                searchParamTable(dataU + " AND nazwa_statusu = 'zwrócona'", "termin_zwrotu", "52", emailUser, jTableZwrotyCZ);
            }
        }
    }//GEN-LAST:event_jButtonTerminZwrSWCZActionPerformed

    private void jButtonTerminZwrSWCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTerminZwrSWCZMouseExited
        // TODO add your handling code here:
        jTextFieldTerminZwrCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonTerminZwrSWCZMouseExited

    private void jButtonTerminZwrSWCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTerminZwrSWCZMouseEntered
        // TODO add your handling code here:
        jTextFieldTerminZwrCZ.setBackground(LibForm.newBlue);
    }//GEN-LAST:event_jButtonTerminZwrSWCZMouseEntered

    private void jTextFieldEmailPracWCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailPracWCZActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulWCZ.getText();
        
        searchParamTable("'" + tytul + "' AND nazwa_statusu = 'wypożyczona'", "tytul", "47", emailUser, jTableWypozyczeniaCZ);
    }//GEN-LAST:event_jTextFieldEmailPracWCZActionPerformed

    private void jButtonWyswielWCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielWCZActionPerformed
        // TODO add your handling code here:
        queryToSend.add("46");
        queryToSend.add(emailUser);
        queryToSend.add("wypożyczona");
        Client.readWriteServer(this, queryToSend, jTableWypozyczeniaCZ, null);
    }//GEN-LAST:event_jButtonWyswielWCZActionPerformed

    private void jPanelMenuWypCZComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelMenuWypCZComponentShown
        // TODO add your handling code here:
        clearAllFields();
        
        jTextFieldTerminZwrCZ.setEnabled(false);
        jButtonTerminZwrSWCZ.setEnabled(false);
        jLabelTerminZwrCZ.setForeground(Color.gray);
    }//GEN-LAST:event_jPanelMenuWypCZComponentShown

    private void jButtonWyswielZCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielZCZActionPerformed
        // TODO add your handling code here:
        queryToSend.add("53");
        queryToSend.add(emailUser);
        queryToSend.add("zwrócona");
        Client.readWriteServer(this, queryToSend, jTableZwrotyCZ, null);
    }//GEN-LAST:event_jButtonWyswielZCZActionPerformed

    private void jPanelMenuZwrCZComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelMenuZwrCZComponentShown
        // TODO add your handling code here:
        clearAllFields();
    }//GEN-LAST:event_jPanelMenuZwrCZComponentShown

    private void jButtonWyswielLCZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWyswielLCZMouseClicked

    }//GEN-LAST:event_jButtonWyswielLCZMouseClicked

    private void jButtonWyswielLCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWyswielLCZMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonWyswielLCZMouseEntered

    private void jButtonWyswielLCZComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jButtonWyswielLCZComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonWyswielLCZComponentShown

    private void jTableWypozyczeniaCZFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaCZFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableWypozyczeniaCZFocusLost

    private void jTableWypozyczeniaCZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaCZMouseClicked
        // TODO add your handling code here:
        int indexArr = jTableWypozyczeniaCZ.getSelectedRow();
        TableModel model = jTableWypozyczeniaCZ.getModel();

        jTextFieldTytulWCZ.setText(model.getValueAt(indexArr, 0).toString());
        jTextFieldEmailPracWCZ.setText(model.getValueAt(indexArr, 1).toString());
        jTextFieldDataWypCZ.setForeground(Color.black);
        jTextFieldDataWypCZ.setText(model.getValueAt(indexArr, 2).toString());
        jTextFieldTerminCZ.setForeground(Color.black);
        jTextFieldTerminCZ.setText(model.getValueAt(indexArr, 3).toString());
    }//GEN-LAST:event_jTableWypozyczeniaCZMouseClicked

    private void jTableWypozyczeniaCZPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableWypozyczeniaCZPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableWypozyczeniaCZPropertyChange

    private void jTableZwrotyCZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableZwrotyCZMouseClicked
        int indexArr = jTableZwrotyCZ.getSelectedRow();
        TableModel model = jTableZwrotyCZ.getModel();

        jTextFieldTytulWCZ.setText(model.getValueAt(indexArr, 0).toString());
        jTextFieldEmailPracWCZ.setText(model.getValueAt(indexArr, 1).toString());
        jTextFieldDataWypCZ.setForeground(Color.black);
        jTextFieldDataWypCZ.setText(model.getValueAt(indexArr, 2).toString());
        jTextFieldTerminCZ.setForeground(Color.black);
        jTextFieldTerminCZ.setText(model.getValueAt(indexArr, 3).toString());
        jTextFieldTerminZwrCZ.setForeground(Color.black);
        jTextFieldTerminZwrCZ.setText(model.getValueAt(indexArr, 4).toString());
    }//GEN-LAST:event_jTableZwrotyCZMouseClicked

    private void jTableZwrotyCZPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableZwrotyCZPropertyChange
        //jPanelDetailsWZW.setVisible(false);
    }//GEN-LAST:event_jTableZwrotyCZPropertyChange

    private void jTextFieldDataWypCZFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDataWypCZFocusGained
        dateGained(jTextFieldDataWypCZ, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldDataWypCZFocusGained

    private void jTextFieldDataWypCZFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDataWypCZFocusLost
        dateLost(jTextFieldDataWypCZ, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldDataWypCZFocusLost

    private void jTextFieldDataWypCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataWypCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDataWypCZActionPerformed

    private void jTextFieldTerminCZFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTerminCZFocusGained
        // TODO add your handling code here:
        dateGained(jTextFieldTerminCZ, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldTerminCZFocusGained

    private void jTextFieldTerminCZFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTerminCZFocusLost
        // TODO add your handling code here:
        dateLost(jTextFieldTerminCZ, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldTerminCZFocusLost

    private void jTextFieldTerminCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTerminCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTerminCZActionPerformed

    private void jTextFieldTerminZwrCZFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTerminZwrCZFocusGained
        // TODO add your handling code here:
        dateGained(jTextFieldTerminZwrCZ, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldTerminZwrCZFocusGained

    private void jTextFieldTerminZwrCZFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTerminZwrCZFocusLost
        // TODO add your handling code here:
         dateLost(jTextFieldTerminZwrCZ, "dd-mm-rrrr");
    }//GEN-LAST:event_jTextFieldTerminZwrCZFocusLost

    private void jTextFieldTerminZwrCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTerminZwrCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTerminZwrCZActionPerformed

    private void jButtonWyswielLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielLActionPerformed
        // TODO add your handling code here:
        queryToSend.add("65");
        queryToSend.add(emailUser);
        queryToSend.add("AND (lz.nazwa_statusu = 'Gotowa do odbioru' OR lz.nazwa_statusu = 'Tymczasowy brak')");
        
        Client.readWriteServer(this, queryToSend, jTableListaZyczenCZ, null);
    }//GEN-LAST:event_jButtonWyswielLActionPerformed

    private void jTableListaZyczenCZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaZyczenCZMouseClicked
        // TODO add your handling code here:
        int indexArr = jTableListaZyczenCZ.getSelectedRow();
        TableModel model = jTableListaZyczenCZ.getModel();

        jTextFieldTytulWCZ.setText(model.getValueAt(indexArr, 0).toString());
    }//GEN-LAST:event_jTableListaZyczenCZMouseClicked

    private void jTableListaZyczenCZPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableListaZyczenCZPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableListaZyczenCZPropertyChange

    private void jPanelMenuLisZyczCZComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelMenuLisZyczCZComponentShown
        // TODO add your handling code here:
        jTextFieldEmailPracWCZ.setEnabled(false);
        jButtonEmailPracSWCZ.setEnabled(false);
        jLabelEmailPracCZ.setForeground(Color.gray);
        
        jTextFieldDataWypCZ.setEnabled(false);
        jButtonDataWypSWCZ.setEnabled(false);
        jLabelDataWypCZ.setForeground(Color.gray);
        
        jTextFieldTerminCZ.setEnabled(false);
        jButtonTerminSWCZ.setEnabled(false);
        jLabelTerminCZ.setForeground(Color.gray);
        
        jTextFieldTerminZwrCZ.setEnabled(false);
        jButtonTerminZwrSWCZ.setEnabled(false);
        jLabelTerminZwrCZ.setForeground(Color.gray);     
        
        queryToSend.add("65");
        queryToSend.add(emailUser);
        queryToSend.add("AND (lz.nazwa_statusu = 'Gotowa do odbioru' OR lz.nazwa_statusu = 'Tymczasowy brak')");
        
        Client.readWriteServer(this, queryToSend, jTableListaZyczenCZ, null);
    }//GEN-LAST:event_jPanelMenuLisZyczCZComponentShown

    private void jPanelMenuLisZyczCZComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelMenuLisZyczCZComponentHidden
        // TODO add your handling code here:      
        jTextFieldEmailPracWCZ.setEnabled(true);
        jButtonEmailPracSWCZ.setEnabled(true);
        jLabelEmailPracCZ.setForeground(Color.black);
        
        jTextFieldDataWypCZ.setEnabled(true);
        jButtonDataWypSWCZ.setEnabled(true);
        jLabelDataWypCZ.setForeground(Color.black);
        
        jTextFieldTerminCZ.setEnabled(true);
        jButtonTerminSWCZ.setEnabled(true);
        jLabelTerminCZ.setForeground(Color.black);
    }//GEN-LAST:event_jPanelMenuLisZyczCZComponentHidden

    private void jPanelMenuWypCZComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelMenuWypCZComponentHidden
        // TODO add your handling code here:
        jTextFieldTerminZwrCZ.setEnabled(true);
        jButtonTerminZwrSWCZ.setEnabled(true);
        jLabelTerminZwrCZ.setForeground(Color.black);
    }//GEN-LAST:event_jPanelMenuWypCZComponentHidden

    private void jTableKsiazkiCZMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKsiazkiCZMouseClicked
        // TODO add your handling code here:
        int indexArr = jTableKsiazkiCZ.getSelectedRow();
        TableModel model = jTableKsiazkiCZ.getModel();

        jTextFieldTytulCZ.setText(model.getValueAt(indexArr, 1).toString());
        jComboBoxAutorCZ.getModel().setSelectedItem(model.getValueAt(indexArr, 2).toString() + " " + model.getValueAt(indexArr, 3).toString());
        jComboBoxKategoriaCZ.getModel().setSelectedItem(model.getValueAt(indexArr, 4).toString());
        jComboBoxWydawnictwoCZ.getModel().setSelectedItem(model.getValueAt(indexArr, 5).toString());
        jTextFieldRokWydaniaCZ.setForeground(Color.black);
        jTextFieldRokWydaniaCZ.setText(model.getValueAt(indexArr, 6).toString());
        jTextFieldIloscCZ.setText(model.getValueAt(indexArr, 7).toString());
        jTextFieldTytulSelectCZ.setText(model.getValueAt(indexArr, 1).toString());

        //System.out.println(model.getValueAt(indexArr, 0).toString());
        jButtonUDodajCZ.setEnabled(true);
        jLabelTytul2.setForeground(Color.black);
    }//GEN-LAST:event_jTableKsiazkiCZMouseClicked

    private void jTextFieldTytulSelectCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTytulSelectCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTytulSelectCZActionPerformed

    private void jButtonUDodajCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUDodajCZActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulSelectCZ.getText();

        if(tytul.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnej książki", "Wybież książkę z listy",2);
        }else{
            //System.out.println(tytul);
            queryToSend.add("25");                  //checkKsiazka
            queryToSend.add(tytul);
            if(Client.readWriteServer(this, queryToSend, null, null) == 1){ //jest ksiazka

                String info;

                queryToSend.add("25");              //checkKsiazkaIlosc
                queryToSend.add(tytul + "' AND liczba_sztuk >= '1");

                if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                    System.out.println("jest wiecej niz 0");
                    info = "Gotowa do odbioru";

                }else{
                    System.out.println("jest mniej niz 1");
                    info = "Tymczasowy brak";
                }

                queryToSend.add("63");              //czechListaZyczen
                queryToSend.add(tytul);
                queryToSend.add(emailUser);
                if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                    System.out.println("jest na liscie");
                    JOptionPane.showMessageDialog(null, "Wybrana książka jest już na liśnie życzeń.","",2);
                }else{
                    System.out.println("nie ma na liscie");

                    queryToSend.add("50");          //checkWYpozyczenie
                    queryToSend.add(emailUser);
                    queryToSend.add(tytul);
                    queryToSend.add("wypożyczona");

                    if(Client.readWriteServer(this, queryToSend, null, null) != 0){
                        System.out.println("jest wypozyczona");
                        JOptionPane.showMessageDialog(null, "Wybrana książka jest już przez ciebie wypożyczona.","",2);
                    }else{

                        queryToSend.add("62");      //addListaZyczen
                        queryToSend.add(tytul);
                        queryToSend.add(emailUser);
                        queryToSend.add(info);

                        if(Client.readWriteServer(this, queryToSend, null, null) == 1){
                            System.out.println("dodano do listy");
                        }
                    }
                }

            }
            queryToSend.add("11");
            Client.readWriteServer(this, queryToSend, jTableKsiazkiCZ, null);
        }
    }//GEN-LAST:event_jButtonUDodajCZActionPerformed

    private void jButtonUDodajCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUDodajCZMouseExited
        // TODO add your handling code here:
        jTextFieldTytulCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonUDodajCZMouseExited

    private void jButtonUDodajCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUDodajCZMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUDodajCZMouseEntered

    private void jButtonWyswielCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWyswielCZActionPerformed
        queryToSend.add("11");

        Client.readWriteServer(this, queryToSend, jTableKsiazkiCZ, null);
    }//GEN-LAST:event_jButtonWyswielCZActionPerformed

    private void jTextFieldIloscCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIloscCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIloscCZActionPerformed

    private void jTextFieldRokWydaniaCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRokWydaniaCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRokWydaniaCZActionPerformed

    private void jTextFieldRokWydaniaCZFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldRokWydaniaCZFocusLost
        dateLost(jTextFieldRokWydaniaCZ, "rrrr");
    }//GEN-LAST:event_jTextFieldRokWydaniaCZFocusLost

    private void jTextFieldRokWydaniaCZFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldRokWydaniaCZFocusGained
        dateGained(jTextFieldRokWydaniaCZ, "rrrr");
    }//GEN-LAST:event_jTextFieldRokWydaniaCZFocusGained

    private void jComboBoxWydawnictwoCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWydawnictwoCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxWydawnictwoCZActionPerformed

    private void jComboBoxWydawnictwoCZItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxWydawnictwoCZItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxWydawnictwoCZItemStateChanged

    private void jButtonWydawnictwoSCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWydawnictwoSCZActionPerformed
        // TODO add your handling code here:
        String wydawnictwo = (String)jComboBoxWydawnictwoCZ.getSelectedItem();

        searchTable(wydawnictwo, "wd.nazwa_wydawnictwa", "12", jTableKsiazkiCZ);
    }//GEN-LAST:event_jButtonWydawnictwoSCZActionPerformed

    private void jButtonWydawnictwoSCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWydawnictwoSCZMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonWydawnictwoSCZMouseExited

    private void jButtonWydawnictwoSCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonWydawnictwoSCZMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonWydawnictwoSCZMouseEntered

    private void jButtonTytulSCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTytulSCZActionPerformed
        // TODO add your handling code here:
        String tytul = jTextFieldTytulCZ.getText();

        searchTable(tytul, "tytul", "12", jTableKsiazkiCZ);
    }//GEN-LAST:event_jButtonTytulSCZActionPerformed

    private void jButtonTytulSCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulSCZMouseExited
        // TODO add your handling code here:
        jTextFieldTytulCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonTytulSCZMouseExited

    private void jButtonTytulSCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTytulSCZMouseEntered
        // TODO add your handling code here:
        jTextFieldTytulCZ.setBackground(LibForm.newBlue);
    }//GEN-LAST:event_jButtonTytulSCZMouseEntered

    private void jButtonAutorSCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutorSCZActionPerformed
        // TODO add your handling code here:
        String autor = (String)jComboBoxAutorCZ.getSelectedItem();

        autor = autor.trim();
        String[] newStr = autor.split(" ", 2);
        String queryP = "au.imie) = '"+ newStr[0].toLowerCase() + "' AND LOWER(au.nazwisko";
        searchTable(newStr[1], queryP, "12", jTableKsiazkiCZ);
    }//GEN-LAST:event_jButtonAutorSCZActionPerformed

    private void jButtonAutorSCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAutorSCZMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAutorSCZMouseExited

    private void jButtonAutorSCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAutorSCZMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAutorSCZMouseEntered

    private void jButtonKategoriaSCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKategoriaSCZActionPerformed
        // TODO add your handling code here:
        String kategoria = (String)jComboBoxKategoriaCZ.getSelectedItem();

        searchTable(kategoria, "kt.nazwa_kategorii", "12", jTableKsiazkiCZ);
    }//GEN-LAST:event_jButtonKategoriaSCZActionPerformed

    private void jButtonKategoriaSCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKategoriaSCZMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonKategoriaSCZMouseExited

    private void jButtonKategoriaSCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKategoriaSCZMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonKategoriaSCZMouseEntered

    private void jButtonRokSCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRokSCZActionPerformed
        String rokwy = jTextFieldRokWydaniaCZ.getText();
        if(!isCorrectYear(rokwy)){
            JOptionPane.showMessageDialog(null, "Format daty jest nieprawidlowy","Wpisz poprawny rok",2);
        }else{
            searchTable(rokwy, "ks.rok_wydania", "12", jTableKsiazkiCZ);
        }
    }//GEN-LAST:event_jButtonRokSCZActionPerformed

    private void jButtonRokSCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRokSCZMouseExited
        jTextFieldRokWydaniaCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonRokSCZMouseExited

    private void jButtonRokSCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRokSCZMouseEntered
        jTextFieldRokWydaniaCZ.setBackground(LibForm.newBlue);
    }//GEN-LAST:event_jButtonRokSCZMouseEntered

    private void jButtonIloscSCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIloscSCZActionPerformed
        // TODO add your handling code here:
        String ilosc = jTextFieldIloscCZ.getText();

        searchTable(ilosc, "ks.liczba_sztuk", "12", jTableKsiazkiCZ);
    }//GEN-LAST:event_jButtonIloscSCZActionPerformed

    private void jButtonIloscSCZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonIloscSCZMouseExited
        jTextFieldIloscCZ.setBackground(Color.WHITE);
    }//GEN-LAST:event_jButtonIloscSCZMouseExited

    private void jButtonIloscSCZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonIloscSCZMouseEntered
        jTextFieldIloscCZ.setBackground(LibForm.newBlue);
    }//GEN-LAST:event_jButtonIloscSCZMouseEntered

    private void jComboBoxKategoriaCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxKategoriaCZActionPerformed
        // TODO add your handling code here:
        //System.out.println(jComboBoxKategoriaCZ.getSelectedIndex());
    }//GEN-LAST:event_jComboBoxKategoriaCZActionPerformed

    private void jComboBoxKategoriaCZItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxKategoriaCZItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxKategoriaCZItemStateChanged

    private void jComboBoxAutorCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAutorCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAutorCZActionPerformed

    private void jComboBoxAutorCZItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxAutorCZItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxAutorCZItemStateChanged

    private void jTextFieldTytulCZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTytulCZActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTytulCZActionPerformed

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown
        // TODO add your handling code here:
        queryToSend.add("11");

        Client.readWriteServer(this, queryToSend, jTableKsiazkiCZ, null);
    }//GEN-LAST:event_jPanel1ComponentShown

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
            java.util.logging.Logger.getLogger(UsrForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsrForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsrForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsrForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UsrForm(emailUser).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAutorSCZ;
    private javax.swing.JButton jButtonDataWypSWCZ;
    private javax.swing.JButton jButtonEmailPracSWCZ;
    private javax.swing.JButton jButtonIloscSCZ;
    private javax.swing.JButton jButtonKategoriaSCZ;
    private javax.swing.JButton jButtonRokSCZ;
    private javax.swing.JButton jButtonTerminSWCZ;
    private javax.swing.JButton jButtonTerminZwrSWCZ;
    private javax.swing.JButton jButtonTytulSCZ;
    private javax.swing.JButton jButtonTytulSWCZ;
    private javax.swing.JButton jButtonUDodajCZ;
    private javax.swing.JButton jButtonWydawnictwoSCZ;
    private javax.swing.JButton jButtonWyswielCZ;
    private javax.swing.JButton jButtonWyswielL;
    private javax.swing.JTabbedPane jButtonWyswielLCZ;
    private javax.swing.JButton jButtonWyswielWCZ;
    private javax.swing.JButton jButtonWyswielZCZ;
    private javax.swing.JComboBox<String> jComboBoxAutorCZ;
    private javax.swing.JComboBox<String> jComboBoxKategoriaCZ;
    private javax.swing.JComboBox<String> jComboBoxWydawnictwoCZ;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAutor;
    private javax.swing.JLabel jLabelDataWypCZ;
    private javax.swing.JLabel jLabelEmailPracCZ;
    private javax.swing.JLabel jLabelIlosc;
    private javax.swing.JLabel jLabelKategoria;
    private javax.swing.JLabel jLabelRokWydania;
    private javax.swing.JLabel jLabelTerminCZ;
    private javax.swing.JLabel jLabelTerminZwrCZ;
    private javax.swing.JLabel jLabelTytul;
    private javax.swing.JLabel jLabelTytul2;
    private javax.swing.JLabel jLabelTytulCZ;
    private javax.swing.JLabel jLabelWydawnictwo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelMenuCZ;
    private javax.swing.JPanel jPanelMenuCZ1;
    private javax.swing.JPanel jPanelMenuLisZyczCZ;
    private javax.swing.JPanel jPanelMenuWypCZ;
    private javax.swing.JPanel jPanelMenuZwrCZ;
    private javax.swing.JPanel jPanelSubmenuK;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jScrollPaneTableB;
    private javax.swing.JTabbedPane jTabbedPaneCzytelnik;
    private javax.swing.JTable jTableKsiazkiCZ;
    private javax.swing.JTable jTableListaZyczenCZ;
    private javax.swing.JTable jTableWypozyczeniaCZ;
    private javax.swing.JTable jTableZwrotyCZ;
    private javax.swing.JTextField jTextFieldDataWypCZ;
    private javax.swing.JTextField jTextFieldEmailCZ;
    private javax.swing.JTextField jTextFieldEmailPracWCZ;
    private javax.swing.JTextField jTextFieldIloscCZ;
    private javax.swing.JTextField jTextFieldRokWydaniaCZ;
    private javax.swing.JTextField jTextFieldTerminCZ;
    private javax.swing.JTextField jTextFieldTerminZwrCZ;
    private javax.swing.JTextField jTextFieldTytulCZ;
    private javax.swing.JTextField jTextFieldTytulSelectCZ;
    private javax.swing.JTextField jTextFieldTytulWCZ;
    // End of variables declaration//GEN-END:variables
}

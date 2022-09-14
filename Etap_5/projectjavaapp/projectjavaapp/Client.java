package projectjavaapp;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static projectjavaapp.LoginForm.s;


public class Client 
{
   public static int readWriteServer(JFrame Frame, ArrayList<String> queryToSend, JTable table, JComboBox box){      //metoda odpytujaca serwer po TCP
        try
        {
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            String dataFSTable[] = null;       //tablica z otrzymanym wynikiem 
            String numQuery = "";
            
            //////////////////////////////WYSYLANIE//////////////////////////////////////
            if(!queryToSend.isEmpty()) {
                numQuery = queryToSend.get(0);
                dos.writeInt(queryToSend.size());   
                byte[] message;
                    for (String i : queryToSend) {
                        message = i.getBytes(StandardCharsets.UTF_8);
                        dos.writeInt(message.length);
                        dos.write(message);
                    }
                queryToSend.clear();
            }
            /////////////////////////////ODBIERANIE/////////////////////////////////////
            switch(numQuery) {
                 case "Exit":
                      s.close();
                      return 1;
                 case "1":
                 case "2":
                 case "3":
                 case "10":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "11":
                 case "12":
                 case "13":
                 case "14":
                 case "15":
                     String[] arrKsg = {"Nr katalogowy", "Tytuł", "Imię autora", "Nazwisko autora", "Kategoria", "Wydawnictwo", "Rok wydania", "Liczba sztuk"};
                     readLoopTable(dis, 8, table, arrKsg, 1);
                     break;
                 case "16":
                     readLoopBox(dis, 7, box, 2);
                     break;
                 case "17":
                     readLoopBox(dis, 3, box, 1);
                     break;
                 case "18":
                     readLoopBox(dis, 3, box, 1);
                     break;
                 case "19":
                 ///    
                 case "20":
                 ///    
                 case "21":
                 ///    
                 case "22":
                 case "23":
                 case "24":
                 case "25":
                 case "26":
                 case "27":
                 case "28":
                 case "29":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "30":
                 case "31":
                     String[] arrAut = {"Nr ID", "Imię", "Nazwisko", "Miasto", "Kraj pochodzenia", "Data urodzenia", "Data śmierci"};
                     readLoopTable(dis, 7, table, arrAut ,1);
                     break;
                 case "32":
                 case "33":
                 case "34":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "35":
                 case "36":
                     String[] arrCzyt = {"Nr ID", "Imię", "Nazwisko", "Data urodzenia", "Płeć", "Telefon", "Email"};
                     readLoopTable(dis, 7, table, arrCzyt, 1);
                     break;
                 case "37":
                 case "38":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "39":
                     String[] arrCzyt2 = {"Imię", "Nazwisko", "Email"};
                     readLoopTableThreeAng(dis, 7, table, arrCzyt2);
                     break;
                 case "41":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "42":
                     String[] arrCzyt3 = {"Imię", "Nazwisko", "Email"};
                     readLoopTableThreeAng(dis, 7, table, arrCzyt3);
                     break;
                 case "40":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "43":
                 case "44":
                     String[] arrKsg2 = {"Tytuł", "Imię autora", "Nazwisko autora", "Liczba sztuk"};
                     readLoopTableFourAng(dis, 8, table, arrKsg2);
                     break;
                 case "45":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "46":                 //WYPOZYCZENIA
                     String[] arrWyp = {"Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu", "Status"};
                     readLoopTableFiveAng(dis, 6, table, arrWyp, 0);
                     break;
                 case "47":
                     String[] arrWyp2 = {"Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu", "Status"};//////////////////////
                     readLoopTableFiveAng(dis, 6, table, arrWyp2, 1);
                     break;
                 case "48":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "49":
                     String[] arrWyp3 = {"Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu", "Status"};
                     readLoopTable(dis, 5, table, arrWyp3, 1);
                     break;
                 case "50":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "51"://BEZ MES
                     String[] arrWyp4 = {"Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu",  "Termin zwrotu", "Status"};
                     readLoopTable(dis, 6, table, arrWyp4, 0);
                     break;
                 case "52":
                     String[] arrWyp5 = {"Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu",  "Termin zwrotu", "Status"};
                     readLoopTable(dis, 6, table, arrWyp5, 1);
                     break;
                 case "53":                     //ZWROT ALL
                     String[] arrWyp6 = {"Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu",  "Termin zwrotu", "Status"};
                     readLoopTable(dis, 6, table, arrWyp6, 1);
                     break;
                 case "54":
                 case "55":
                     String[] arrCzyt4 = {"Nr ID", "Imię", "Nazwisko", "Data urodzenia", "Płeć", "Telefon", "Email"};
                     readLoopTable(dis, 7, table, arrCzyt4, 1);
                     break;
                 case "56":
                 case "57":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "60":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "61":
                     String[] arrLogs = {"Data", "Informacje"};
                     readLoopTable(dis, 2, table, arrLogs, 1);
                     break;
                 case "62":
                 case "63":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "64":
                     String[] arrLisZ = {"Tytuł"};
                     readLoopTable(dis, 2, table, arrLisZ, 1);
                     break;
                 case "65":
                     String[] arrLisZ2 = {"Tytuł", "Status"};
                     readLoopTable(dis, 2, table, arrLisZ2, 1);
                     break;
                 case "66":
                     dataFSTable = readMsgFromServer(dis, 1);
                     return Integer.parseInt(dataFSTable[0]);
                 case "67":
                     ///
                 case "68":
                     String[] arrWyp7 = {"Tytuł", "Email pracownika", "Data wypożyczenia", "Termin do zwrotu",  "Termin zwrotu", "Status"};
                     readLoopTable(dis, 6, table, arrWyp7, 1);                  
                     break;
                 default:
                     break;
                }
            
        }catch(IOException e){
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    } 
   
    public static String[] readMsgFromServer(DataInputStream dis, int size) throws IOException
    {
        byte[] msg;
        String data[] = new String[size];           //ilosc elementow tablicy
        for(int i = 0; i < size; i++) {
            var length = dis.readInt();             //długość wiadomości przychodzącej
            msg = new byte[length];
            if (length > 0) {
                dis.readFully(msg, 0, msg.length);  // czytaj calosc
                }
            data[i] = new String(msg, StandardCharsets.UTF_8);

        }
        return data;
    }
    
    public static void readLoopTable(DataInputStream dis, int size, JTable table, String[] arr, int mess) throws IOException
    {
        String dataFSTable[];
        int arraySize = dis.readInt();
        
        table.setModel(new DefaultTableModel(null, arr));
        
        if(arraySize > 0){
            for(int j = 0; j < arraySize; j ++){        
                dataFSTable = readMsgFromServer(dis, size);  //od razu ma wpisywac do podanej tablicy
                DefaultTableModel tblModel = (DefaultTableModel)table.getModel();       
                tblModel.addRow(dataFSTable);
            } 
        } else {
            if(mess != 0){
                JOptionPane.showMessageDialog(null, "Brak wynikow do wyswietlenia.","",2);
            }          
        }
    }
    
    public static void readLoopTableThreeAng(DataInputStream dis, int size, JTable table, String[] arr) throws IOException
    {
        String dataFSTable[];
        int arraySize = dis.readInt();
        
        table.setModel(new DefaultTableModel(null, arr));
        
        if(arraySize > 0){
            for(int j = 0; j < arraySize; j ++){        
                dataFSTable = readMsgFromServer(dis, size);           
                String dataTable[] = {dataFSTable[1], dataFSTable[2], dataFSTable[6]};               
                DefaultTableModel tblModel = (DefaultTableModel)table.getModel();       
                tblModel.addRow(dataTable);
            } 
        } else {
            JOptionPane.showMessageDialog(null, "Brak wynikow do wyswietlenia.","",2);
        }
    }
    
    public static void readLoopTableFourAng(DataInputStream dis, int size, JTable table, String[] arr) throws IOException
    {
        String dataFSTable[];
        int arraySize = dis.readInt();
        
        table.setModel(new DefaultTableModel(null, arr));
        
        if(arraySize > 0){
            for(int j = 0; j < arraySize; j ++){        
                dataFSTable = readMsgFromServer(dis, size);    
                String dataTable[] = {dataFSTable[1], dataFSTable[2], dataFSTable[3], dataFSTable[7]};
                DefaultTableModel tblModel = (DefaultTableModel)table.getModel();       
                tblModel.addRow(dataTable);
            } 
        } else {
            JOptionPane.showMessageDialog(null, "Brak wynikow do wyswietlenia.","",2);
        }
    }
    
     public static void readLoopTableFiveAng(DataInputStream dis, int size, JTable table, String[] arr, int mess) throws IOException
    {
        String dataFSTable[];
        int arraySize = dis.readInt();
        
        table.setModel(new DefaultTableModel(null, arr));
        
        if(arraySize > 0){
            for(int j = 0; j < arraySize; j ++){        
                dataFSTable = readMsgFromServer(dis, size);
                String dataTable[] = {dataFSTable[0], dataFSTable[1], dataFSTable[2], dataFSTable[3], dataFSTable[5]};
                DefaultTableModel tblModel = (DefaultTableModel)table.getModel();       
                tblModel.addRow(dataTable);
            } 
        } else {
            if(mess != 0){
                JOptionPane.showMessageDialog(null, "Brak wynikow do wyswietlenia.","",2);
            } 
        }
    }
        
    public static void readLoopBox(DataInputStream dis, int size, JComboBox box, int element) throws IOException
    {
        String dataFSTable[];
        
        String firstE;
        String secondE;
        int arraySize = dis.readInt();
        
        for(int j = 0; j < arraySize; j ++){        
            dataFSTable = readMsgFromServer(dis, size);
            
            firstE = dataFSTable[1];
            secondE = dataFSTable[2];
            if(element > 1) {
                box.addItem(firstE + " " + secondE);
            }else{
                box.addItem(firstE);
            }
        }        
    }
}
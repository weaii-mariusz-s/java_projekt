package projectjavaserver;

//glowne metody odpytujace baze danych dla uzytkownikow

import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ThreadMethods {
    
    public static ArrayList<String[]> arrayString = new ArrayList<>();
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
    static DateTimeFormatter dateFormatT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    private static byte[][] convertToBytes(String[] strings) {
        byte[][] data = new byte[strings.length][];
        String string;
        for (int i = 0; i < strings.length; i++) {
            if(strings[i] != null){
                string = strings[i];
            }else{
                string = "0";
            }
            data[i] = string.getBytes(StandardCharsets.UTF_8);
        }
        return data;
    }
    
    static synchronized boolean checkQuer(PreparedStatement pst, ResultSet rs, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 
        rs = pst.executeQuery();
        
        if(rs.next()) {
            return true;
        }
        } catch (SQLException ex) {
       }
        return false;
 }
     
    static synchronized void readQAllKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 

        rs = pst.executeQuery();
        
        byte[][] byteTable;

        while (rs.next()) {
           // Pobierz wedlug nazwy kolumny
            String id =  valueOf(rs.getInt("ksiazka_id"));
            String tytul = rs.getString("tytul");
            String imie = rs.getString("imie");
            String nazwisko = rs.getString("nazwisko");
            String kategoria = rs.getString("nazwa_kategorii");
            String wydawnictwo = rs.getString("nazwa_wydawnictwa");
            String rok_wydania = valueOf(rs.getInt("rok_wydania"));
            String licznba_sztuk = valueOf(rs.getInt("liczba_sztuk"));

            String dataTable[] = {id, tytul, imie, nazwisko, kategoria, wydawnictwo, rok_wydania, licznba_sztuk};
            arrayString.add(dataTable);
        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 8; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }
    
    
    
    static synchronized void readQAllAutor(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 

        rs = pst.executeQuery();
        
        byte[][] byteTable;
        
        String data_smierciS; 

        while (rs.next()) {
            String id =  valueOf(rs.getInt("autor_id"));
            String imie = rs.getString("imie");
            String nazwisko = rs.getString("nazwisko");
            String miasto = rs.getString("miasto");
            String kraj = rs.getString("kraj");
            String data_urodzenia = dateFormat.format(rs.getDate("data_urodzenia"));
            Date data_smierci = rs.getDate("data_smierci");
            
            
            if(data_smierci != null){
                data_smierciS = dateFormat.format(rs.getDate("data_smierci"));
            } else {
                data_smierciS = "-";
            }

            String dataTable[] = {id, imie, nazwisko, miasto, kraj, data_urodzenia, data_smierciS};
            arrayString.add(dataTable);

        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 7; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }
    
    static synchronized void readQAllWydawnictwo(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 

        rs = pst.executeQuery();
        
        byte[][] byteTable;

        while (rs.next()) {
            String id =  valueOf(rs.getInt("wydawnictwo_id"));
            String nazwa_wydawnictwa = rs.getString("nazwa_wydawnictwa");
            String miasto = rs.getString("miasto");

            String dataTable[] = {id, nazwa_wydawnictwa, miasto};
            arrayString.add(dataTable);
        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 3; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }
    
    static synchronized void readQAllKategoria(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 

        rs = pst.executeQuery();
        
        byte[][] byteTable;

        while (rs.next()) {
            String id =  valueOf(rs.getInt("kategoria_id"));
            String nazwa_kategorii = rs.getString("nazwa_kategorii");
            String rodzic_id = valueOf(rs.getInt("rodzic_id"));

            String dataTable[] = {id, nazwa_kategorii, rodzic_id};
            arrayString.add(dataTable);
        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 3; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }
    
    static synchronized void readQAllOsoba(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY);

        rs = pst.executeQuery();
        
        byte[][] byteTable;

        while (rs.next()) {
            String id =  valueOf(rs.getInt("osoba_id"));
            String imie = rs.getString("imie");
            String nazwisko = rs.getString("nazwisko");
            String data_urodzenia = dateFormat.format(rs.getDate("data_urodzenia"));
            String plec = rs.getString("plec");
            String telefon = rs.getString("telefon");
            String email = rs.getString("email");

            String dataTable[] = {id, imie, nazwisko, data_urodzenia, plec, telefon, email};
            arrayString.add(dataTable);
        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 7; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }
    
    static synchronized void readQAllWypozyczenie(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 

        rs = pst.executeQuery();
        
        byte[][] byteTable;
        
        String termin_zwrotuS;
        
        while (rs.next()) {
            String tytul =  rs.getString("tytul");
            String email = rs.getString("email");
            String data_wypozyczenia = dateFormat.format(rs.getDate("data_wypozyczenia"));
            String termin_do_zwrotu = dateFormat.format(rs.getDate("termin_do_zwrotu"));
            Date termin_zwrotu = rs.getDate("termin_zwrotu");
            String nazwa_statusu = rs.getString("nazwa_statusu");
            
            if(termin_zwrotu != null){
                termin_zwrotuS = dateFormat.format(rs.getDate("termin_zwrotu"));
            } else {
                termin_zwrotuS = "-";
            }

            String dataTable[] = {tytul, email, data_wypozyczenia, termin_do_zwrotu, termin_zwrotuS, nazwa_statusu};
            arrayString.add(dataTable);
        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 6; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }
    
    static synchronized void readQAllLogs(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 

        rs = pst.executeQuery();
        
        byte[][] byteTable;

        while (rs.next()) {
            String data =  rs.getString("data");
            String info = rs.getString("info");

            String dataTable[] = {data, info};
            arrayString.add(dataTable);
        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 2; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }
    
    static synchronized void readQAllListaZyczen(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 

        rs = pst.executeQuery();
        
        byte[][] byteTable;

        while (rs.next()) {
            String tytul =  rs.getString("tytul");
            String nazwa_statusu = rs.getString("nazwa_statusu");

            String dataTable[] = {tytul, nazwa_statusu};
            arrayString.add(dataTable);
        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 2; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }
    
    static synchronized void checkQAllListaZyczen(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn,  String QUERY) throws IOException{
        try {
        pst = conn.prepareStatement(QUERY); 

        rs = pst.executeQuery();
        
        byte[][] byteTable;

        while (rs.next()) {
            String tytul =  rs.getString("tytul");
            String nazwa_statusu = rs.getString("nazwa_statusu");

            String dataTable[] = {tytul, nazwa_statusu};
            arrayString.add(dataTable);
        }
        
        dos.writeInt(arrayString.size());
        
         for (int i = 0; i < arrayString.size(); i++){
            String stringTable[] = arrayString.get(i);
            byteTable = convertToBytes(stringTable);
            for(int j = 0; j < 2; j++)
            {
                dos.writeInt(byteTable[j].length);
                dos.write(byteTable[j]);
            }
        }
         
         arrayString.clear();

        } catch (SQLException ex) {
       }
 }

    
    static synchronized boolean updateQ(PreparedStatement pst, Connection conn, String Query) throws IOException {
        try {
        pst = conn.prepareStatement(Query);    
        
        if(pst.executeUpdate() != 0)
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }
    
    static synchronized void sendInt(DataOutputStream dos, String num) throws IOException{
        byte[] mess = num.getBytes(StandardCharsets.UTF_8);
        dos.writeInt(mess.length);
        dos.write(mess);
    }
    
    static synchronized int returnQVar(PreparedStatement pst, ResultSet rs, Connection conn, String QUERY, String text) throws IOException{
        try {
            pst = conn.prepareStatement(QUERY); 
            rs = pst.executeQuery();

            if(rs.next()) {
                return rs.getInt(text);
            }
        } catch (SQLException ex) {
       }
        return 0;
 }
    
    
    //////////////////////////////////////////////////////////
    public static int oneArgQue(PreparedStatement pst, ResultSet rs, Connection conn, 
         String text1, String text2, String text3, String text4) throws IOException{
         String QUERY = "SELECT " + text1 + " FROM " + text2 + " WHERE " + text3 + " = '" + text4 + "'";
         
         //System.out.println(QUERY);
         return returnQVar(pst, rs, conn, QUERY, text1);
     }
     
     public static int twoArgQue(PreparedStatement pst, ResultSet rs, Connection conn, 
         String text1, String text2, String text3, String text4, String text5, String text6) throws IOException{
         String QUERY = "SELECT " + text1 + " FROM " + text2 + " WHERE " + text3 + " = '" + text4 + "' AND " + text5 + " = '" + text6 + "'";

         return returnQVar(pst, rs, conn, QUERY, text1);
     }
     ///////////////////////////////////////////////////////
     public static void sendLog(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "INSERT INTO LOGS(data, info)\n"
                                 + "VALUES ('" + queryReceived.get(1) + "','\n"
                                 +queryReceived.get(2) + "')";   

         //System.out.println(Q1);
          if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
     
     public static void readLogs(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn) throws IOException{
          String Query = "SELECT data, info FROM logs ORDER BY data DESC";

         readQAllLogs(pst, rs, dos, conn, Query);
     }
     
     public static void selectLogOsoba(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
     String Query = "SELECT * FROM OSOBA WHERE email = '" + queryReceived.get(1) + "' AND haslo = '" + queryReceived.get(2) +"'";   
     //System.out.println(Query);
     
      if(checkQuer(pst, rs, conn, Query)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
 }
     
     public static void selectLogCheck(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
        int idO = twoArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(1), "haslo", queryReceived.get(2));
         
      if(checkQuer(pst, rs, conn, "SELECT * FROM Administrator WHERE osoba_id = '" + idO + "'")){
             sendInt(dos, "1");           
      } else if(checkQuer(pst, rs, conn, "SELECT * FROM Bibliotekarz WHERE osoba_id = '" + idO + "'")){
             sendInt(dos, "2");    
      } else if(checkQuer(pst, rs, conn, "SELECT * FROM Czytelnik WHERE osoba_id = '" + idO + "'")){
             sendInt(dos, "3");
      } else {
             sendInt(dos, "0");
      }
 }
     
     public static void checkEmailOsoba(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT osoba_id FROM OSOBA WHERE LOWER(email) = '" + queryReceived.get(1).toLowerCase() +"'";
         
         
         sendInt(dos, String.valueOf(returnQVar(pst, rs, conn, Query, "osoba_id")));
     }
     
     public static void checkNazwaWydawnictwo(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT nazwa_wydawnictwa FROM WYDAWNICTWO WHERE LOWER(nazwa_wydawnictwa) = '" + queryReceived.get(1).toLowerCase() +"'";
         if(checkQuer(pst, rs, conn, Query)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
     
     public static void checkAllKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT nazwa_wydawnictwa FROM WYDAWNICTWO WHERE LOWER(nazwa_wydawnictwa) = '" + queryReceived.get(1).toLowerCase() +"'";
         if(checkQuer(pst, rs, conn, Query)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }

     public static void insertOsoba(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "INSERT INTO OSOBA(imie, nazwisko, data_urodzenia, plec, telefon, email, haslo)\n"
                                 + "VALUES ( '" + queryReceived.get(1) + "', '" + queryReceived.get(2) + "', \n"
                                 + " to_date('" + queryReceived.get(3) + "', 'DD.MM.YYYY'), '"  + queryReceived.get(4) + "', \n"
                                 + queryReceived.get(5) + ", '" + queryReceived.get(6) + "', '" + queryReceived.get(7) + "')";   

         //System.out.println(Q1);
         if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
     
     public static void insertCzytelnik(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idP = twoArgQue(pst, rs, conn, "osoba_id", "osoba", "imie", queryReceived.get(1), "nazwisko", queryReceived.get(2));
         
         String Q1 = "INSERT INTO CZYTELNIK(osoba_id)\n"
                   + "VALUES ('" + idP + "')";
         
         if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     } 
     
     public static void insertWydawnictwo(PreparedStatement pst, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "INSERT INTO WYDAWNICTWO(nazwa_wydawnictwa, miasto)\n"
                                 + "VALUES ( '" + queryReceived.get(1) + "', '" + queryReceived.get(2) + "')";   

         //System.out.println(Q1);
         if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }     

     public static void selectAllKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn) throws IOException{
         String Query = "SELECT ks.ksiazka_id, ks.tytul, au.imie, au.nazwisko, kt.nazwa_kategorii, wd.nazwa_wydawnictwa, ks.rok_wydania, ks.liczba_sztuk\n" +
                                        "FROM ksiazka ks, kategoria kt, wydawnictwo wd, autor au, autor_ksiazka ak\n" +
                                        "WHERE ks.kategoria_id = kt.kategoria_id\n" +
                                        "AND ks.wydawnictwo_id = wd.wydawnictwo_id\n" +
                                        "AND ak.autor_id = au.autor_id\n" +
                                        "AND ak.ksiazka_id = ks.ksiazka_id\n" +
                                        "ORDER BY ks.ksiazka_id";
         
         //System.out.println(Query);

         readQAllKsiazka(pst, rs, dos, conn, Query);
     }

     public static void selectOneArgKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT ks.ksiazka_id, ks.tytul, au.imie, au.nazwisko, kt.nazwa_kategorii, wd.nazwa_wydawnictwa, ks.rok_wydania, ks.liczba_sztuk\n" +
                                        "FROM ksiazka ks, kategoria kt, wydawnictwo wd, autor au, autor_ksiazka ak\n" +
                                        "WHERE ks.kategoria_id = kt.kategoria_id\n" +
                                        "AND ks.wydawnictwo_id = wd.wydawnictwo_id\n" +
                                        "AND ak.autor_id = au.autor_id\n" +
                                        "AND ak.ksiazka_id = ks.ksiazka_id\n" +
                                        "AND LOWER(" + queryReceived.get(1) + ") = '" + queryReceived.get(2).toLowerCase() + "'\n" +
                                        "ORDER BY ks.ksiazka_id";

         //System.out.println(Query);
         readQAllKsiazka(pst, rs, dos, conn, Query);
     }
     
     public static void selectAllWydawnictwo(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn) throws IOException{
         String Query = "SELECT wydawnictwo_id, nazwa_wydawnictwa, miasto FROM wydawnictwo ORDER BY wydawnictwo_id";

         readQAllWydawnictwo(pst, rs, dos, conn, Query);
     }
     
     public static void selectAllKategoria(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn) throws IOException{
         String Query = "SELECT kategoria_id, nazwa_kategorii, rodzic_id FROM kategoria ORDER BY kategoria_id";

         readQAllKategoria(pst, rs, dos, conn, Query);
     }
     
    //////////////////////////////////////////// 
     public static void checkAutorKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT k.ksiazka_id FROM ksiazka k, autor_ksiazka ak, autor a\n" +
                        "WHERE k.ksiazka_id = ak.ksiazka_id\n" +
                        "AND a.autor_id = ak.autor_id\n" +
                        "AND k.tytul = '" + queryReceived.get(1) + "'\n" +
                        "AND a.imie = '" + queryReceived.get(2) + "'\n" +
                        "AND a.nazwisko = '" + queryReceived.get(3) + "'";

         //System.out.println(Query);
         if(checkQuer(pst, rs, conn, Query)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
     
     public static void checkKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT ksiazka_id FROM ksiazka\n" +
                        "WHERE tytul = '" + queryReceived.get(1) + "'";

         //System.out.println(Query);
         if(checkQuer(pst, rs, conn, Query)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }

     public static boolean insertKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idKt = oneArgQue(pst, rs, conn, "kategoria_id", "kategoria", "nazwa_kategorii", queryReceived.get(1));         //id kategorii
         int idW = oneArgQue(pst, rs, conn, "wydawnictwo_id", "wydawnictwo", "nazwa_wydawnictwa", queryReceived.get(2));    //id wydawnictwa kategorii
        
         String Q1 = "INSERT INTO KSIAZKA(tytul, kategoria_id, wydawnictwo_id, rok_wydania, liczba_sztuk)\n" +
                        "VALUES('" + queryReceived.get(3) + "', '" + idKt + "', '" + idW + "', \n" +
                        "'" + queryReceived.get(4) + "', '" + queryReceived.get(5) + "')";

        return updateQ(pst, conn, Q1);
     }
     
     public static void insertAutKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idA = twoArgQue(pst, rs, conn, "autor_id", "autor", "imie", queryReceived.get(6), "nazwisko", queryReceived.get(7));
         int idK = oneArgQue(pst, rs, conn, "ksiazka_id", "ksiazka", "tytul", queryReceived.get(3));
        
         String Q1 = "INSERT INTO AUTOR_KSIAZKA(autor_id, ksiazka_id)\n" +
                     "VALUES(" + idA + " , " + idK + ")";
         
         if(updateQ(pst, conn, Q1)){
                sendInt(dos, "1");           
            } else {
                sendInt(dos, "0");
            }
     }
      
     public static void deleteAutKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
          int idK = oneArgQue(pst, rs, conn, "ksiazka_id", "ksiazka", "tytul", queryReceived.get(1));
          int idA = twoArgQue(pst, rs, conn, "autor_id", "autor", "imie", queryReceived.get(2), "nazwisko", queryReceived.get(3));
        
         String Q1 = "DELETE FROM autor_ksiazka \n" +
                        "WHERE autor_id = '" + idA + "'\n" +
                        "AND ksiazka_id = '" + idK + "'";
         
         //System.out.println(Q1);
         if(updateQ(pst, conn, Q1)){
                sendInt(dos, "1");           
            } else {
                sendInt(dos, "0");
            }
     }
      
     public static void updateKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idKt = oneArgQue(pst, rs, conn, "kategoria_id", "kategoria", "nazwa_kategorii", queryReceived.get(1));
         int idW = oneArgQue(pst, rs, conn, "wydawnictwo_id", "wydawnictwo", "nazwa_wydawnictwa", queryReceived.get(2));
         int idK = oneArgQue(pst, rs, conn, "ksiazka_id", "ksiazka", "tytul", queryReceived.get(3));
         
         //podmienia pierwsza ksiazke 
         String Q1 = "UPDATE KSIAZKA\n" +
                    //"SET tytul = '" + queryReceived.get(3) + "',\n" +
                    "SET kategoria_id = '" + idKt + "',\n" +
                    "wydawnictwo_id = '" + idW + "',\n" +
                    "rok_wydania = '" + queryReceived.get(4) + "',\n" +
                    "liczba_sztuk = '" + queryReceived.get(5) + "'\n" +
                    "WHERE ksiazka_id = '" + idK + "'";

         if(updateQ(pst, conn, Q1) && (!queryReceived.get(6).equals(queryReceived.get(8))) && (!queryReceived.get(7).equals(queryReceived.get(9)))){
         
            int idA = twoArgQue(pst, rs, conn, "autor_id", "autor", "imie", queryReceived.get(6), "nazwisko", queryReceived.get(7));
            int ioA = twoArgQue(pst, rs, conn, "autor_id", "autor", "imie", queryReceived.get(8), "nazwisko", queryReceived.get(9));

            String Q2 = "UPDATE AUTOR_KSIAZKA\n" +
                        "SET autor_id = '" + idA + "'\n" +
                        "WHERE autor_id = '" + ioA + "'\n"+
                        "AND ksiazka_id = '" + idK + "'";
            
            if(updateQ(pst, conn, Q2)){
                   sendInt(dos, "1");           
               } else {
                   sendInt(dos, "0");
               }

         } else {
             System.out.println("nie aktualizuje autora");
             sendInt(dos, "0");
         }
     }
     
     public static void deleteKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idK = oneArgQue(pst, rs, conn, "ksiazka_id", "ksiazka", "tytul", queryReceived.get(1));

         String Q1 = "DELETE FROM autor_ksiazka  \n" +
                        "WHERE ksiazka_id = '" + idK + "'";

         if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
     //////////////////////////////////////////////////////////
     public static void selectAllAutor(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn) throws IOException{
         String Query = "SELECT autor_id, imie, nazwisko, miasto, kraj, data_urodzenia, data_smierci\n" +
                        "FROM autor\n" +
                        "ORDER BY autor_id";

         readQAllAutor(pst, rs, dos, conn, Query);
     }
     
     public static void selectOneArgAutor(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT autor_id, imie, nazwisko, miasto, kraj, data_urodzenia, data_smierci\n" +
                        "FROM autor\n" +
                        "WHERE " + queryReceived.get(1) + queryReceived.get(2).toLowerCase() + "\n" +
                        "ORDER BY autor_id";

         //System.out.println(Query);
         readQAllAutor(pst, rs, dos, conn, Query);
     }
     
     public static void checkDateAutor(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT autor_id FROM autor\n" +
                        "WHERE imie = '" + queryReceived.get(1) + "'\n" +
                        "AND nazwisko = '" + queryReceived.get(2) + "'\n" +
                        "AND data_urodzenia = TO_DATE('" + queryReceived.get(3) + "', 'dd-mm-yyyy')\n";

         //System.out.println(Query);
         sendInt(dos, String.valueOf(returnQVar(pst, rs, conn, Query, "autor_id")));
         //System.out.println(returnQVar(pst, rs, conn, Query, "autor_id"));
     }
     
     public static void insertAutor(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "INSERT INTO AUTOR(imie, nazwisko, miasto, kraj, data_urodzenia, data_smierci)\n"
                                 + "VALUES ( '" + queryReceived.get(1) + "', '" + queryReceived.get(2) + "', '" + queryReceived.get(3) + "', '" + queryReceived.get(4) + "', \n"
                                 + " to_date('" + queryReceived.get(5) + "', 'DD.MM.YYYY'), " + queryReceived.get(6) + ")";

         //System.out.println(Q1);
         if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
     
     public static void deleteAutor(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "DELETE FROM autor  \n" +
                        "WHERE autor_id = " + queryReceived.get(1);

         //System.out.println(Q1);
         if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
     
     public static void updateAutor(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "UPDATE AUTOR\n" +
                    "SET miasto = '" + queryReceived.get(1) + "',\n" +
                    "kraj = '" + queryReceived.get(2) + "',\n" +
                    //"data_urodzenia = to_date('" + queryReceived.get(3) + "', 'DD.MM.YYYY'),\n" +
                    "data_smierci = " + queryReceived.get(3) + "\n" +
                    "WHERE autor_id = '" + queryReceived.get(4) + "'";

         if(updateQ(pst, conn, Q1)){
                   sendInt(dos, "1");           
               } else {
                   sendInt(dos, "0");
               }
     }
     //////////////////////////////////////////////////////////////////////////    
     public static void selectAllCzytelnik(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn) throws IOException{
         String Query = "SELECT o.osoba_id, o.imie, o.nazwisko, o.data_urodzenia, o.plec, o.telefon, o.email\n" +
                        "FROM osoba o, czytelnik cz\n" +
                        "WHERE o.osoba_id = cz.osoba_id\n" +
                        "ORDER BY o.osoba_id";

         //System.out.println(Query);
         readQAllOsoba(pst, rs, dos, conn, Query);
     }
     
     public static void selectOneArgCzytelnik(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT o.osoba_id, o.imie, o.nazwisko, o.data_urodzenia, o.plec, o.telefon, o.email\n" +
                        "FROM osoba o, czytelnik cz\n" +
                        "WHERE o.osoba_id = cz.osoba_id\n" +
                        "AND LOWER(" + queryReceived.get(1) + ") = '" + queryReceived.get(2).toLowerCase() + "'\n" +
                        "ORDER BY osoba_id";

         //System.out.println(Query);
         readQAllOsoba(pst, rs, dos, conn, Query);
     }
     
     public static void deleteOsoba(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "DELETE FROM osoba  \n" +
                        "WHERE osoba_id = '" + queryReceived.get(1) + "'";

         if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
     
     //////////////////////////////////////////////////////////////////////////    
     public static void selectAllBibliotekarz(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn) throws IOException{
         String Query = "SELECT o.osoba_id, o.imie, o.nazwisko, o.data_urodzenia, o.plec, o.telefon, o.email\n" +
                        "FROM osoba o, bibliotekarz bl\n" +
                        "WHERE o.osoba_id = bl.osoba_id\n" +
                        "ORDER BY o.osoba_id";

         //System.out.println(Query);
         readQAllOsoba(pst, rs, dos, conn, Query);
     }
     
     public static void selectOneArgBibliotekarz(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT o.osoba_id, o.imie, o.nazwisko, o.data_urodzenia, o.plec, o.telefon, o.email\n" +
                        "FROM osoba o, bibliotekarz bl\n" +
                        "WHERE o.osoba_id = bl.osoba_id\n" +
                        "AND LOWER(" + queryReceived.get(1) + ") = '" + queryReceived.get(2).toLowerCase() + "'\n" +
                        "ORDER BY osoba_id";

         //System.out.println(Query);
         readQAllOsoba(pst, rs, dos, conn, Query);
     }
     
     public static boolean insertBibliotekarz(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "INSERT INTO BIBLIOTEKARZ(osoba_id)\n"
                   + "VALUES ('" + queryReceived.get(1) + "')";
         
         return updateQ(pst, conn, Q1);    
     }
     
     public static void insertIdCzytenik(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "INSERT INTO CZYTELNIK(osoba_id)\n"
                   + "VALUES ('" + queryReceived.get(1) + "')";
         
         if(updateQ(pst, conn, Q1)){
                sendInt(dos, "1");           
            } else {
                sendInt(dos, "0");
            }
     }
     
     public static boolean deleteBibliotekarz(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
          String Q1 = "DELETE FROM BIBLIOTEKARZ \n" +
                        "WHERE osoba_id = '" + queryReceived.get(1) + "'\n";
         
         return updateQ(pst, conn, Q1);       
     }
     
     public static void deleteCzytelnik(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
          String Q1 = "DELETE FROM CZYTELNIK \n" +
                        "WHERE osoba_id = '" + queryReceived.get(1) + "'\n";
         
         if(updateQ(pst, conn, Q1)){
                sendInt(dos, "1");           
            } else {
                sendInt(dos, "0");
            }         
     }
     
     public static void checkOsoba(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "SELECT osoba_id FROM osoba\n" +
                        "WHERE email = '" + queryReceived.get(1) + "'\n";

         //System.out.println(Query);
         sendInt(dos, String.valueOf(returnQVar(pst, rs, conn, Query, "osoba_id")));
     }
     
     public static void updateOsoba(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "UPDATE OSOBA\n" +
                    "SET telefon = '" + queryReceived.get(1) + "'\n" +
                    "WHERE osoba_id = '" + queryReceived.get(2) + "'";

         if(updateQ(pst, conn, Q1)){
                   sendInt(dos, "1");           
               } else {
                   sendInt(dos, "0");
               }
     }
     ////////////////////////////////////////////////////////////////////////
     
     public static boolean insertWypozyczenie(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idKst = oneArgQue(pst, rs, conn, "ksiazka_id", "ksiazka", "tytul", queryReceived.get(2));         //id ksiazki
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(3));    //id os czytelnika
         int idOsBib = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(4));    //id os bibliotekarza
         
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         int idBib = oneArgQue(pst, rs, conn, "bibliotekarz_id", "bibliotekarz", "osoba_id", String.valueOf(idOsBib));    //id bibliotekarza
         
          LocalDate lDate = LocalDate.now();
          LocalDate lDateP = lDate.plusDays(14);
          
          String dateL = lDate.format(dateFormatT);
          String dateP = lDateP.format(dateFormatT);
          
         String Q1 = "INSERT INTO WYPOZYCZENIE(ksiazka_id, czytelnik_id, bibliotekarz_id, data_wypozyczenia, termin_do_zwrotu, termin_zwrotu, oplata, nazwa_statusu)\n" +
                        "VALUES('" + idKst + "', '" + idCzy + "', '" + idBib + "', \n" +
                        "to_date('" + dateL + "', 'DD.MM.YYYY'), to_date('" + dateP + "', 'DD.MM.YYYY'), NULL, 0, \n" +
                        "'" + queryReceived.get(5) + "')";
         
          //System.out.println(Q1);
        
        return updateQ(pst, conn, Q1);
     }
     
     public static void updateIloscKsiazka(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Q1 = "UPDATE KSIAZKA\n" +
                    "SET liczba_sztuk = liczba_sztuk " + queryReceived.get(1) + "\n" +
                    "WHERE tytul = '" + queryReceived.get(2) + "'";

         if(updateQ(pst, conn, Q1)){
                   sendInt(dos, "1");
               } else {
                   sendInt(dos, "0");
               }
     }
     
     public static void selectAllWypozyczenie(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(1));    //id os czytelnika
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         
         String Query = "SELECT ks.tytul, os.email, wy.data_wypozyczenia, wy.termin_do_zwrotu, wy.termin_zwrotu, wy.nazwa_statusu\n" +
                        "FROM ksiazka ks, wypozyczenie wy, czytelnik cz, bibliotekarz bl, osoba os\n" +
                        "WHERE wy.ksiazka_id = ks.ksiazka_id\n" +
                        "AND wy.bibliotekarz_id = bl.bibliotekarz_id\n" +
                        "AND wy.czytelnik_id = cz.czytelnik_id\n" +
                        "AND os.osoba_id = bl.osoba_id\n" +
                        "AND wy.czytelnik_id = " + idCzy + "\n" +
                        "AND wy.nazwa_statusu = '" + queryReceived.get(2) + "'\n" +
                        "ORDER BY wy.wypozyczenie_id";
         
         //System.out.println(Query);

         readQAllWypozyczenie(pst, rs, dos, conn, Query);
     }
     
     public static void selectOneArgWypozyczenie(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(1));    //id os czytelnika
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         
         String Query = "SELECT ks.tytul, os.email, wy.data_wypozyczenia, wy.termin_do_zwrotu, wy.termin_zwrotu, wy.nazwa_statusu\n" +
                        "FROM ksiazka ks, wypozyczenie wy, czytelnik cz, bibliotekarz bl, osoba os\n" +
                        "WHERE wy.ksiazka_id = ks.ksiazka_id\n" +
                        "AND wy.bibliotekarz_id = bl.bibliotekarz_id\n" +
                        "AND wy.czytelnik_id = cz.czytelnik_id\n" +
                        "AND os.osoba_id = bl.osoba_id\n" +
                        "AND wy.czytelnik_id = " + idCzy + "\n" +
                        "AND LOWER(" + queryReceived.get(2) + ") = " + queryReceived.get(3).toLowerCase() + "\n" +
                        "ORDER BY wy.wypozyczenie_id";
         
         //System.out.println(Query);

         readQAllWypozyczenie(pst, rs, dos, conn, Query);
     }
     
     public static void selectEmailWypozyczenie(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(1));    //id os czytelnika
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         
         int idOsBib = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(4));    //id os bibliotekarza
         int idBib = oneArgQue(pst, rs, conn, "bibliotekarz_id", "bibliotekarz", "osoba_id", String.valueOf(idOsBib));    //id bibliotekarza
         
         String Query = "SELECT ks.tytul, os.email, wy.data_wypozyczenia, wy.termin_do_zwrotu, wy.termin_zwrotu, wy.nazwa_statusu\n" +
                        "FROM ksiazka ks, wypozyczenie wy, czytelnik cz, bibliotekarz bl, osoba os\n" +
                        "WHERE wy.ksiazka_id = ks.ksiazka_id\n" +
                        "AND wy.bibliotekarz_id = " + idBib + "\n" +
                        "AND wy.czytelnik_id = cz.czytelnik_id\n" +
                        "AND os.osoba_id = bl.osoba_id\n" +
                        "AND wy.czytelnik_id = " + idCzy + "\n" +
                        "AND LOWER(" + queryReceived.get(2) + ") = " + queryReceived.get(3).toLowerCase() + "\n" +
                        "ORDER BY wy.wypozyczenie_id";
         
         //System.out.println(Query);

         readQAllWypozyczenie(pst, rs, dos, conn, Query);
     }
     
     public static boolean updateWypozyczenie(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         String Query = "UPDATE WYPOZYCZENIE\n" +
                        "SET " + queryReceived.get(4) + queryReceived.get(5) + "\n" +
                        "WHERE wypozyczenie_id = '" + queryReceived.get(3) +"'";
         
         //System.out.println(Query);

         return updateQ(pst, conn, Query);
     }
     
     public static void checkWypozyczenie(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(1));    //id os czytelnika
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         
         String Query = "SELECT wypozyczenie_id FROM wypozyczenie wy, ksiazka ks\n" +
                        "WHERE wy.ksiazka_id = ks.ksiazka_id\n" +
                        "AND ks.tytul = '" + queryReceived.get(2) + "'\n" +
                        "AND wy.czytelnik_id = '" + idCzy + "'\n" +
                        "AND wy.nazwa_statusu = '" + queryReceived.get(3) + "'";
                        
         //System.out.println(Query);
         sendInt(dos, String.valueOf(returnQVar(pst, rs, conn, Query, "wypozyczenie_id")));
     }
     
     public static void insertListaZyczen(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idKst = oneArgQue(pst, rs, conn, "ksiazka_id", "ksiazka", "tytul", queryReceived.get(1));
        //int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(2));
        int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(2));    //id os czytelnika
        int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         
         String Q1 = "INSERT INTO LISTA_ZYCZEN(czytelnik_id, ksiazka_id, nazwa_statusu)\n"
                   + "VALUES (" + idCzy + ", " + idKst + ", '" + queryReceived.get(3) + "' )";
         
         //System.out.println(Q1);
         
         if(updateQ(pst, conn, Q1)){
                sendInt(dos, "1");           
            } else {
                sendInt(dos, "0");
            }
     }
     
     public static void checkListaZyczen(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idKst = oneArgQue(pst, rs, conn, "ksiazka_id", "ksiazka", "tytul", queryReceived.get(1));
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(2));    //id os czytelnika
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         
         String Query = "SELECT czytelnik_id FROM LISTA_ZYCZEN WHERE czytelnik_id = " + idCzy +" AND ksiazka_id = " + idKst;   
         
         sendInt(dos, String.valueOf(returnQVar(pst, rs, conn, Query, "czytelnik_id")));
     }
     
     public static void SelectAllListaZyczen(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(1));    //id os czytelnika
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         
         String Query = "SELECT ks.tytul, lz.nazwa_statusu FROM ksiazka ks, lista_zyczen lz WHERE\n" +
                        "ks.ksiazka_id = lz.ksiazka_id\n" +
                        "AND lz.czytelnik_id = " + idCzy + "\n" +
                        queryReceived.get(2);

         readQAllListaZyczen(pst, rs, dos, conn, Query);
     }
     ///////////////////////////////
     public static void CheckAllListaZyczen(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(1));    //id os czytelnika
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));    //id czytelnika
         
         String Query = "SELECT ks.tytul, lz.nazwa_statusu FROM ksiazka ks, lista_zyczen lz WHERE\n" +
                        "ks.ksiazka_id = lz.ksiazka_id\n" +
                        "AND lz.czytelnik_id = " + idCzy + "\n" +
                        "AND lz.nazwa_statusu = '" + queryReceived.get(2) + "'";
         
         //System.out.println(Query);

         checkQAllListaZyczen(pst, rs, dos, conn, Query);
     }
     ////////////////////////////////////
     
     public static void deleteListaZyczen(PreparedStatement pst, ResultSet rs, DataOutputStream dos, Connection conn, ArrayList<String> queryReceived) throws IOException{
         int idK = oneArgQue(pst, rs, conn, "ksiazka_id", "ksiazka", "tytul", queryReceived.get(1));
         int idOsCzy = oneArgQue(pst, rs, conn, "osoba_id", "osoba", "email", queryReceived.get(2));
         int idCzy = oneArgQue(pst, rs, conn, "czytelnik_id", "czytelnik", "osoba_id", String.valueOf(idOsCzy));

         String Q1 = "DELETE FROM lista_zyczen  \n" +
                        "WHERE ksiazka_id = '" + idK + "'\n" +
                        "AND czytelnik_id = '" + idCzy + "'";
         
         //System.out.println(Q1);

         if(updateQ(pst, conn, Q1)){
             sendInt(dos, "1");           
         } else {
             sendInt(dos, "0");
         }
     }
}

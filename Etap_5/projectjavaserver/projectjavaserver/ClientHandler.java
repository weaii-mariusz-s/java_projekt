package projectjavaserver;

//watek dla pojedynczego uzytkownika

import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClientHandler extends Thread 
{
 final DataInputStream dis;
 final DataOutputStream dos;
 final Socket s;
   
 // Konstruktor
 public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) throws IOException 
 {
     this.s = s;
     this.dis = dis;
     this.dos = dos;
 }

 @Override
 public void run()                                          //uruchomiony przez start(), petla dla Threda
 {
     PreparedStatement pst = null;
     ResultSet rs = null;
    
     int lenMesg;
     byte[] message;
     ArrayList<String> queryReceived = new ArrayList<>();   //tablica z otrzymanym numerem pytania i argumentami
     
     Connection conn = DBConnect.getConnection();
     
     while (true) 
     {
         try {
            /////////////////////ODCZYTANIE////////////////////////////////////
            lenMesg = dis.readInt();
            do{
                var length = dis.readInt(); //dlugosc wiadomosci przychodzacej
                message = new byte[length];
                if (length > 0) {
                    dis.readFully(message, 0, message.length);
                }
                queryReceived.add(new String(message, StandardCharsets.UTF_8));
                lenMesg --;
                //System.out.println(new String(message, StandardCharsets.UTF_8));
              }while(lenMesg > 0);   
            /////////////////////ODPYTYWANIE////////////////////////////////////
            switch(queryReceived.get(0)) {
                 case "Exit":
                    try (this.s) {
                        System.out.println("Client " + this.s + " wysyla wyjscie...");
                        System.out.println("Zamykam to polaczenie.");
                    }
                    System.out.println("Polaczenie zamkniete");
                    break;
                 case "1":
                     ThreadMethods.selectLogCheck(pst, rs, dos, conn, queryReceived);
                     break;
                 case "2":
                     ThreadMethods.checkEmailOsoba(pst, rs, dos, conn, queryReceived);
                     break;
                 case "3":
                     ThreadMethods.checkNazwaWydawnictwo(pst, rs, dos, conn, queryReceived);
                     break;
                 case "10":
                     ThreadMethods.insertOsoba(pst, rs, dos, conn, queryReceived);
                     ThreadMethods.insertCzytelnik(pst, rs, dos, conn, queryReceived);
                     break;
                 case "11":
                     ThreadMethods.selectAllKsiazka(pst, rs, dos, conn);
                     break;
                 case "12":
                     ThreadMethods.selectOneArgKsiazka(pst, rs, dos, conn, queryReceived);
                     break;
                 case "16":
                     ThreadMethods.selectAllAutor(pst, rs, dos, conn);
                     break;
                 case "17":
                     ThreadMethods.selectAllWydawnictwo(pst, rs, dos, conn);
                     break;
                 case "18":
                     ThreadMethods.selectAllKategoria(pst, rs, dos, conn);
                     break;
                 case "19":
                     ////
                     break;
                 case "20":
                     ////
                     break;
                 case "21":
                     ////
                     break;
                  case "22":
                     ThreadMethods.insertWydawnictwo(pst, dos, conn, queryReceived);
                     break;
                  case "23":
                     ThreadMethods.checkAutorKsiazka(pst, rs, dos, conn, queryReceived);
                     break;
                  case "24":
                     ThreadMethods.insertAutKsiazka(pst, rs, dos, conn, queryReceived);
                     break;
                  case "25":
                     ThreadMethods.checkKsiazka(pst, rs, dos, conn, queryReceived);
                     break;
                  case "26":
                     if(ThreadMethods.insertKsiazka(pst, rs, dos, conn, queryReceived)){
                         ThreadMethods.insertAutKsiazka(pst, rs, dos, conn, queryReceived);
                     }
                     break;
                  case "27":
                     ThreadMethods.updateKsiazka(pst, rs, dos, conn, queryReceived);
                     break;
                  case "28":
                     ThreadMethods.deleteKsiazka(pst, rs, dos, conn, queryReceived);
                     break;
                  case "29":
                     ThreadMethods.deleteAutKsiazka(pst, rs, dos, conn, queryReceived);
                     break;
                  case "30":
                     ThreadMethods.selectAllAutor(pst, rs, dos, conn);
                     break;
                  case "31":
                     ThreadMethods.selectOneArgAutor(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "32":
                     ThreadMethods.checkDateAutor(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "33":
                     ThreadMethods.insertAutor(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "34":
                     ThreadMethods.deleteAutor(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "35":
                     ThreadMethods.selectAllCzytelnik(pst, rs, dos, conn);
                     break;
                  case "36":
                     ThreadMethods.selectOneArgCzytelnik(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "37":
                     ThreadMethods.deleteOsoba(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "38":
                     ThreadMethods.updateAutor(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "39":
                     ThreadMethods.selectAllCzytelnik(pst, rs, dos, conn);
                     break;
                  case "40":
                     ThreadMethods.checkOsoba(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "41":
                     ThreadMethods.updateOsoba(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "42":
                     ThreadMethods.selectOneArgCzytelnik(pst, rs, dos, conn, queryReceived);
                     break;
                  case "43":
                     ThreadMethods.selectAllKsiazka(pst, rs, dos, conn);
                     break;
                  case "44":
                     ThreadMethods.selectOneArgKsiazka(pst, rs, dos, conn, queryReceived);
                     break;
                  case "45":
                     if(ThreadMethods.insertWypozyczenie(pst, rs, dos, conn, queryReceived)){
                         ThreadMethods.updateIloscKsiazka(pst, rs, dos, conn, queryReceived);
                     }
                     break;
                  case "46":
                     ThreadMethods.selectAllWypozyczenie(pst, rs, dos, conn, queryReceived);
                     break;
                  case "47":
                     ThreadMethods.selectOneArgWypozyczenie(pst, rs, dos, conn, queryReceived);
                     break;
                  case "48":
                     if(ThreadMethods.updateWypozyczenie(pst, rs, dos, conn, queryReceived)){
                         ThreadMethods.updateIloscKsiazka(pst, rs, dos, conn, queryReceived);
                     }
                     break;
                  case "49":
                     ThreadMethods.selectEmailWypozyczenie(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "50":
                     ThreadMethods.checkWypozyczenie(pst, rs, dos, conn, queryReceived);
                     break;
                  case "51":
                     ThreadMethods.selectAllWypozyczenie(pst, rs, dos, conn, queryReceived);
                     break;
                  case "52":
                     ThreadMethods.selectOneArgWypozyczenie(pst, rs, dos, conn, queryReceived);
                     break;
                  case "53":
                     ThreadMethods.selectAllWypozyczenie(pst, rs, dos, conn, queryReceived);
                     break;
                  case "54":
                     ThreadMethods.selectAllBibliotekarz(pst, rs, dos, conn);
                     break;
                  case "55":
                     ThreadMethods.selectOneArgBibliotekarz(pst, rs, dos, conn, queryReceived); 
                     break;
                  case "56":
                      if(ThreadMethods.insertBibliotekarz(pst, rs, dos, conn, queryReceived)){
                         ThreadMethods.deleteCzytelnik(pst, rs, dos, conn, queryReceived);
                     }
                     break;
                  case "57":
                      if(ThreadMethods.deleteBibliotekarz(pst, rs, dos, conn, queryReceived)){
                         ThreadMethods.insertIdCzytenik(pst, rs, dos, conn, queryReceived);
                     }
                     break;
                  case "60":
                      ThreadMethods.sendLog(pst, rs, dos, conn, queryReceived); 
                      break;
                  case "61":
                      ThreadMethods.readLogs(pst, rs, dos, conn);
                      break;
                  case "62":
                      ThreadMethods.insertListaZyczen(pst, rs, dos, conn, queryReceived);
                      break;
                  case "63":
                      ThreadMethods.checkListaZyczen(pst, rs, dos, conn, queryReceived);
                      break;
                  case "64":
                  case "65":
                      ThreadMethods.SelectAllListaZyczen(pst, rs, dos, conn, queryReceived);
                      break;
                  case "66":
                      ThreadMethods.deleteListaZyczen(pst, rs, dos, conn, queryReceived);
                      break;
                  case "67":
                      ThreadMethods.checkListaZyczen(pst, rs, dos, conn, queryReceived);
                      break;
                  case "68":
                      ThreadMethods.selectEmailWypozyczenie(pst, rs, dos, conn, queryReceived); 
                      break;
                 default:
                     break;
             }
            queryReceived.clear();
            
                
         } catch (IOException e) {
             try {
                 this.dis.close();
                 this.dos.close();
                 break;
             } catch (IOException ex) {
                 Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
 }
}
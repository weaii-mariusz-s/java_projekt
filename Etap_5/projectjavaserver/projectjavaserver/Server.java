package projectjavaserver;
//glowna klasa serwera

import java.io.*;
import java.net.*;


public class Server 
{    
    public static void main(String[] args) throws IOException 
    {
        // serwer nasluchuje na porcie 5056
        ServerSocket ss = new ServerSocket(5056);
        
        srvFrame form = new srvFrame();
        form.setVisible(true);
        form.setLocationRelativeTo(null);
          
        // w petli czeka na nowych uzytkownikow
        while (true) 
        {
            Socket s = null;
              
            try 
            {
                // obiekt socket do odbierania przychodzacych ządan klientow
                s = ss.accept();
                  
                System.out.println("Nowy klient zostal polaczony : " + s);
                  
                // pozyskiwanie strumieni wejsciowych i wyjsciowych
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                
                System.out.println("Przypisanie nowego watku do tego klienta");
  
                // tworzy nowy watek klienta
                Thread t = new ClientHandler(s, dis, dos);
  
                t.start();                  
            }
            catch (IOException e){
                s.close();
            }
        }
    }
}
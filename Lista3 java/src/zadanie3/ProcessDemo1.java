package zadanie3;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessDemo1 {

   public static void main(String[] args) {
      try {
         // Tworzenie nowego procesu wywolujacego program pr2.exe z parametrami

         final String wiersz = JOptionPane.showInputDialog("Podaj numer wiersza");
         final String index = JOptionPane.showInputDialog("Podaj numer indeksu");
         System.out.println("Creating Process...");
         Process p = Runtime.getRuntime().exec("C:\\Users\\User\\OneDrive - Politechnika Wroclawska\\intellij\\Lista3 java\\src\\zadanie3\\lista1\\main.exe " + wiersz + " " + index);
         System.out.println(p.getOutputStream());

         // Tworznie obiektu BufferedReader  zeby wyswietlic z niego na konsoli
         // Zeby go zrobic nalezy dostac sie do tego co zwraca proces. Robi sie
         // to za pomoca getInputStream
         
         BufferedReader reader = new BufferedReader(
                 new InputStreamReader(p.getInputStream()));
         String line;
         while ((line = reader.readLine()) != null) {
             System.out.println(line);
         }
      
         reader.close();

      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }
}
package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6000);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream output = new DataOutputStream(s.getOutputStream());
            BufferedReader inServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String readline;
            System.out.println("Connessione effettuata.");
            do {
                 System.out.println("Digita Aggiunta nota o Lista note per continuare o ESCI per uscire.");
                  readline = input.readLine();
                if (readline.equals("Aggiunta nota")) 
                {
                    System.out.println("Digita la nota");
                    output.writeBytes(input.readLine() + '\n');
                    System.out.println("Nota salvata");

                }
                else if (readline.equals("Lista note")) 
                {
                    output.writeBytes("@" + '\n');
                    System.out.println(inServer.readLine());
                }
            } while (!readline.equals("ESCI"));
            output.writeBytes(readline + '\n');
            s.close();
            System.out.println("Disconnessione");
        } catch (Exception e) {
            System.out.println("Errore");
            System.exit(1);
        }
    }
}
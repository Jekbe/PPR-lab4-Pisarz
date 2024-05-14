import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try(Socket socket = new Socket("localhost", 8001);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())){
            System.out.println(bufferedReader.readLine());

            boolean dziala = true;
            while(dziala){
                System.out.println(bufferedReader.readLine());
                switch(scanner.nextLine()){
                    case "koniec" -> {
                        dziala = false;
                        printWriter.println(0);
                        System.out.println(bufferedReader.readLine());
                    }
                    case "dodaj" -> {
                        printWriter.println(1);
                        System.out.println(bufferedReader.readLine());
                        objectOutputStream.writeObject(new Ksiazka(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
                        System.out.println(bufferedReader.readLine());
                    }
                    case "zmien" -> {
                        printWriter.println(2);
                        System.out.println(bufferedReader.readLine());
                        printWriter.println(scanner.nextInt());
                        System.out.println(bufferedReader.readLine());
                    }
                    case "nieoddane" -> {
                        printWriter.println(3);
                        System.out.println(bufferedReader.readLine());
                    }
                    case "usun" -> {
                        printWriter.println(4);
                        System.out.println(bufferedReader.readLine());
                        printWriter.println(scanner.nextInt());
                        System.out.println(bufferedReader.readLine());
                    }
                    case "aktualizuj" -> {
                        printWriter.println(5);
                        System.out.println(bufferedReader.readLine());
                        printWriter.println(scanner.nextInt());
                        objectOutputStream.writeObject(new Ksiazka(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
                        System.out.println(bufferedReader.readLine());
                    }
                    case "lista" -> {
                        printWriter.println(6);
                        System.out.println(bufferedReader.readLine());
                    }
                    default -> {
                        printWriter.println(7);
                        System.out.println("Nieznana komenda");
                    }
                }
            }
        } catch(IOException e) {
            System.out.println("błąd: " + e);
        }
    }
}
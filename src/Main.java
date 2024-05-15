import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static PrintWriter printWriter;
    private static ObjectOutputStream objectOutputStream;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try(Socket socket = new Socket("localhost", 8001)){
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(bufferedReader.readLine());

            boolean dziala = true;
            while(dziala){
                System.out.println(bufferedReader.readLine());
                switch(scanner.nextLine()){
                    case "koniec" -> {
                        dziala = false;
                        print(Integer.toString(0));
                        System.out.println(bufferedReader.readLine());
                    }
                    case "dodaj" -> {
                        print(Integer.toString(1));
                        System.out.println(bufferedReader.readLine());
                        outObject(new Ksiazka(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
                        System.out.println(bufferedReader.readLine());
                    }
                    case "zmien" -> {
                        print(Integer.toString(2));
                        System.out.println(bufferedReader.readLine());
                        print(scanner.nextLine());
                        System.out.println(bufferedReader.readLine());
                    }
                    case "nieoddane" -> {
                        print(Integer.toString(3));
                        System.out.println(bufferedReader.readLine());
                    }
                    case "usun" -> {
                        print(Integer.toString(4));
                        System.out.println(bufferedReader.readLine());
                        print(scanner.nextLine());
                        System.out.println(bufferedReader.readLine());
                    }
                    case "aktualizuj" -> {
                        print(Integer.toString(5));
                        System.out.println(bufferedReader.readLine());
                        print(scanner.nextLine());
                        outObject(new Ksiazka(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
                        System.out.println(bufferedReader.readLine());
                    }
                    case "lista" -> {
                        print(Integer.toString(6));
                        String line;
                        while(!(line = bufferedReader.readLine()).equals("null"))
                            System.out.println(line);
                    }
                    default -> {
                        print(Integer.toString(7));
                        System.out.println("Nieznana komenda");
                    }
                }
            }
        } catch(IOException e) {
            System.out.println("błąd: " + e);
        }
    }

    private static void print(String s){
        printWriter.println(s);
        printWriter.flush();
    }

    private static void outObject(Object object) throws IOException {
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }
}
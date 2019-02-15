import java.io.*;
import java.net.*;
import java.util.*;

public class RockPaperScissors{

    static void startServer(int portNumber){

        System.out.println("Waiting for client...");

        while(true){

            int tie = 0, client = 0, server = 0;
            int rounds = 0;

            try{

                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket socket = serverSocket.accept();

                if(socket != null){
                    System.out.println("Client connected");
                }


                BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String tmp = lector.readLine();
                String delims = "/";
                String[] tokens = tmp.split(delims);
                rounds = Integer.parseInt(tokens[0]);
                System.out.println("Client has sent " + rounds + " shapes...");
                String[] userEntries = tmp.split(delims);
                for(int i = 1; i < (rounds + 1); i++){
                    userEntries[i - 1] = tokens[i];
                }

                Random rg = new Random();
                int[] randomArray = new int [rounds];
                for(int i = 0; i < rounds; i++){
                    randomArray[i] = rg.nextInt(3) + 1;
                }

                String[] programEntries = new String[rounds];
                for(int i = 0; i < rounds; i++){
                    if(randomArray[i] == 1)
                        programEntries[i] = "rock";
                    else if (randomArray[i] == 2)
                        programEntries[i] = "paper";
                    else if (randomArray[i] == 3)
                        programEntries[i] = "scissors";
                }

                System.out.println("Shapes are chosen...");

                String[] resultArray = new String[rounds];

                for(int i = 0; i < rounds; i++){

                    if(userEntries[i].toLowerCase().equals("rock")){
                        if(programEntries[i].equals("rock")){
                            resultArray[i] = "(tie)";
                            tie++;
                        }
                        else if(programEntries[i].equals("scissors")){
                            resultArray[i] = "(client wins)";
                            client++;
                        }
                        else if(programEntries[i].equals("paper")){
                            resultArray[i] = "(server wins)";
                            server++;
                        }
                    }
                    else if(userEntries[i].toLowerCase().equals("scissors")){
                        if(programEntries[i].equals("rock")){
                            resultArray[i] = "(server wins)";
                            server++;
                        }
                        else if(programEntries[i].equals("scissors")){
                            resultArray[i] = "(tie)";
                            tie++;
                        }
                        else if(programEntries[i].equals("paper")){
                            resultArray[i] = "(client wins)";
                            client++;
                        }
                    }
                    else if(userEntries[i].toLowerCase().equals("paper")){
                        if(programEntries[i].equals("rock")){
                            resultArray[i] = "(client wins)";
                            client++;
                        }
                        else if(programEntries[i].equals("scissors")){
                            resultArray[i] = "(server wins)";
                            server++;
                        }
                        else if(programEntries[i].equals("paper")){
                            resultArray[i] = "(tie)";
                            tie++;
                        }
                    }
                }

                System.out.println("Results are as follows: ");
                for(int i = 0; i < rounds; i++){
                    System.out.println("Round-" + (i + 1) + ": " + "server chooses " + programEntries[i] + " " + resultArray[i]);
                }
                System.out.println("Client: " + client + "\n" + "Tie: " + tie + "\n" + "Server: " + server);

                PrintStream pr = new PrintStream(socket.getOutputStream());

                String msgToClient = "";
                for(int i = 0; i < rounds; i++){
                    msgToClient += ("Round-" + (i + 1) + ": " + "server chooses " + programEntries[i] + " " + resultArray[i] + "/");
                }
                msgToClient += ("Client: " + client + "/" + "Tie: " + tie + "/" + "Server: " + server);
                pr.println(msgToClient);

                serverSocket.close();

            }catch(Exception ex){
                break;
            }
        }

        System.out.println("Client disconnected");
        System.out.println("Server terminating...");
    }
    /* ------------------------------------------ */

    static void startClient(InetAddress ipAddress, int portNumber){


        int rounds = 0;
        String roundNumber = "a";
        Scanner scanner = null;

        BYEBYE: while(!roundNumber.toLowerCase().equals("q")){
            scanner = new Scanner(System.in);
            try
            {
                Socket sock = new Socket(ipAddress, portNumber);
                PrintStream pr = new PrintStream(sock.getOutputStream());

                System.out.println("Enter the number of rounds (Press Q to quit): ");

                roundNumber = scanner.nextLine();

                if(roundNumber.toLowerCase().equals("q")){
                    break BYEBYE;
                }else{
                    rounds = Integer.parseInt(roundNumber);
                    if(rounds <= 0){
                        System.out.println("You cannot enter 0 or lower.");
                    }
                }

                String[] userEntries = new String[rounds];
                for(int i = 0; i < rounds; i++){
                    System.out.print("Round-" + (i + 1) + ": ");
                    userEntries[i] = scanner.nextLine();
                }

                String msgToServer = roundNumber;
                for(int i = 0; i < rounds; i++){
                    msgToServer += '/' + userEntries[i];
                }

                InputStream stream = new ByteArrayInputStream(msgToServer.getBytes("UTF-8"));

                InputStreamReader rd = new InputStreamReader(stream);
                BufferedReader ed = new BufferedReader(rd);

                String temp = ed.readLine();

                pr.println(temp);

                // Getting the result from server
                BufferedReader gt = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String tm = gt.readLine();
                String[] tokens = tm.split("/");

                for(String t : tokens){
                    System.out.println(t);
                }

                sock.close();

            }

            catch(Exception e){
                System.out.println(e.getMessage());

            }
        }

        System.out.println("Client is terminated");

        scanner.close();
    }

    public interface Jugador {
        String nombre();
        //@TODO We need to know the difference between registered players and guest players and welcome registered ones with a message
    }

    public static String saludar(Jugador jugador){
        try{
            return jugador.nombre();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            return "";
        }
    }

    public class RegisteredPlayer {
        private String PlayerName;

        public RegisteredPlayer(String name) {
            PlayerName = name;
        }

        public void setName(String name) {
            this.PlayerName = name;
        }

        public String getName() {
            return PlayerName;
        }
    }

    public class GuestPlayer {
        private String PlayerName;

        public GuestPlayer(String name) {
            PlayerName = name;
        }

        public void setName(String name) {
            this.PlayerName = name;
        }

        public String getName() {
            return PlayerName;
        }
    }

    public static void main(String[] args){

        InetAddress ipAddress;
        int portNumber;

        if(args.length == 2){
            try{
                ipAddress = InetAddress.getByName(args[0]);
            }catch(UnknownHostException e){
                System.out.println(e.getMessage());
                ipAddress = null;
            }
            portNumber = Integer.parseInt(args[1]);

            try{
                startClient(ipAddress, portNumber);

            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
        else if(args.length == 1){
            portNumber = Integer.parseInt(args[0]);

            try{
                startServer(portNumber);

            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Invalid arguments!");
        }
    }
}

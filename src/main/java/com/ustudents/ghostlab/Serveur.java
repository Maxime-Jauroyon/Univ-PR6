import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    private static void callGameMessage(PrintWriter pw){
        pw.print("GAMES 1***\n");
        pw.flush();
        pw.print("OGAMES 0 2***\n");
        pw.flush();
    }

    private static void callListMessage(PrintWriter pw){
        pw.print("LIST! 0 2***\n");
        pw.flush();
        pw.print("PLAYR leo***\n");
        pw.flush();
        pw.print("PLAYR kevin***\n");
        pw.flush();
    }

    private static void callSizeMessage(PrintWriter pw){
        pw.print("SIZE! 0 2 2***\n");
        pw.flush();
    }

    private static void callUnregister(PrintWriter pw){
        pw.print("UNROK 0***\n");
        pw.flush();
    }

    private static void callNewGame(PrintWriter pw){
        pw.print("REGOK 0***\n");
        pw.flush();
    }

    private static void someCommands(BufferedReader br, PrintWriter pw) throws IOException {
        while(true){
            String clientMessage = br.readLine();
            if(clientMessage.startsWith("REGIS") || clientMessage.startsWith("NEWPL")){
                pw.print("REGOK 0***\n");
            }else if(clientMessage.startsWith("UNREG")){
                pw.print("UNROK 0***\n");
            }else if(clientMessage.startsWith("SIZE?")){
                String idGame = clientMessage.substring(6, Utils.findEndIndex(clientMessage, 6, '*'));
                pw.print("SIZE! " + idGame + " 10 10***\n");
            }else if(clientMessage.startsWith("LIST?")){
                callListMessage(pw);
            }else if(clientMessage.startsWith("GAME?")){
                callGameMessage(pw);
            }

            pw.flush();
            if(clientMessage.startsWith("UNREG") || clientMessage.startsWith("LIST?")){
                callGameMessage(pw);
            }
        }
    }

    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(5557);

            while(true){
                Socket socket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                callGameMessage(pw); //game command
                someCommands(br, pw);
                //System.out.println("Received message : " + br.readLine());
                //callGameMessage(pw);
                //callListMessage(pw);//list command
                //callSizeMessage(pw);//size command
                //callUnregister(pw);//unregister command
                //callNewGame(pw);//new game command

            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
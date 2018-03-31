import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        HashMap<Integer, HostPort> userMap = new HashMap<>();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            System.out.println("Listening on " + args[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        String message;
        while(true) {
            try {
                if (serverSocket != null) {
                    Socket connSocket = serverSocket.accept();
                    BufferedReader incoming = new BufferedReader(
                        new InputStreamReader(connSocket.getInputStream()));
                    message = incoming.readLine();
                    if (message.split(":")[0].equals("info")) {
                        int id = Integer.parseInt(message.split(":")[1].split(",")[0]);
                        String hostName = message.split(":")[1].split(",")[1];
                        int port = Integer.parseInt(message.split(":")[1].split(",")[2]);
                        userMap.put(id, new HostPort(hostName, port));               
                    }
                    /*Set s = userMap.entrySet();
                    Iterator i = s.iterator();
                    while (i.hasNext()) {
                        Map.Entry me = (Map.Entry)i.next();
                        System.out.println("Key: " + me.getKey() + " Value: " + me.getValue().toString());
                    }*/
                    System.out.println(">>> " + message);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    private static class HostPort {
        private String hostName;
        private int portNumber;
        public HostPort(String hostName, int portNumber) {
            this.hostName = hostName;
            this.portNumber = portNumber;
        }

        public String toString() {
            return hostName + "," + String.valueOf(portNumber);
        }
    }

}

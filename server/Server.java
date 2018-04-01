import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Arrays;
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
                    System.out.println(">>> " + message);
                    if (message.split(":")[0].equals("info")) {
                        int id = Integer.parseInt(message.split(":")[1].split(",")[0]);
                        String hostName = message.split(":")[1].split(",")[1];
                        int port = Integer.parseInt(message.split(":")[1].split(",")[2]);
                        if (userMap.get(id) == null)
                            userMap.put(id, new HostPort(hostName, port));
                    } else if (message.split(":")[0].equals("recipients")) {
                        String[] recipients = message.split(":")[1].split(",");
                        String sender = message.split(":")[3];
                        String[] splitMessage = Arrays.copyOfRange(
                            message.split(":"), 5, message.split(":").length);
                        String outMessage = "";
                        for (String m : splitMessage)
                            outMessage += m;
                        for (String recipient : recipients) {
                            int recipient_id = Integer.parseInt(recipient);
                            HostPort user = userMap.get(recipient_id);
                            try {
                                Socket socket = new Socket(
                                    user.getHostName(), user.getPortNumber());
                                DataOutputStream outgoing = new DataOutputStream(socket.getOutputStream());
                                outgoing.writeBytes(sender + ":" + outMessage);
                                System.out.println("<<< " + sender + ":" + outMessage);
                                socket.close();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
            } catch (Exception e) {
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

        public String getHostName() { return hostName; }
        public int getPortNumber() { return portNumber; }
    }

}

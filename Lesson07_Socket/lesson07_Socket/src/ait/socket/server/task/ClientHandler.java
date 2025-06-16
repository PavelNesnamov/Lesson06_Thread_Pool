package ait.socket.server.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
        while (true) {
            String message = socketReader.readLine();
            if (message == null) {
                System.out.println("Conection: " + socket.getInetAddress() + ":" + socket.getPort());
            }
            System.out.println("Server received: " + message);
            socketWriter.println(message + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        }
    }

}

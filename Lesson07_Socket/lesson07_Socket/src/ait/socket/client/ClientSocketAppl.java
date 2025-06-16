package ait.socket.client;

import ait.socket.server.task.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientSocketAppl {
    public static void main(String[] args) {
        String serverHost = "127.0.0.1"; // localhost
        int port = 9000;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try (Socket socket = new Socket(serverHost, port);){
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));
            Scanner consoleScanner = new Scanner(System.in);
            System.out.println("Enter your message, or type exit for quit");
            String massage = consoleScanner.nextLine();
            while (!"exit".equalsIgnoreCase(massage)) {
                socketWriter.println(massage);
                socketWriter.flush();
                String response = socketReader.readLine();
                System.out.println(response);
                System.out.println("Enter your message, or type exit for quit");
                executorService.execute(new ClientHandler(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

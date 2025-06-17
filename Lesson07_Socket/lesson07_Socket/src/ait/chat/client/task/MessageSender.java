package ait.chat.client.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MessageSender implements Runnable {
    private final Socket socket;

    public MessageSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Socket socket = this.socket) {
            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
            Scanner consoleScanner = new Scanner(System.in);
            System.out.println("Enter your name");
            String name = consoleScanner.nextLine();
            System.out.println("Enter your message, or type exit for quit");
            String message = consoleScanner.nextLine();
            while (!"exit".equalsIgnoreCase(message)) {
                socketWriter.printf("%s [%s] %s\n", name, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), message);
                message = consoleScanner.nextLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
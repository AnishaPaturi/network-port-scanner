import java.util.Scanner;

public class PortScanner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== JAVA PORT SCANNER =====");

        System.out.print("Enter target host (IP or domain): ");
        String host = sc.nextLine();

        System.out.print("Enter start port: ");
        int startPort = sc.nextInt();

        System.out.print("Enter end port: ");
        int endPort = sc.nextInt();

        System.out.println("\nScanning " + host + "...\n");

        long startTime = System.currentTimeMillis();

        for (int port = startPort; port <= endPort; port++) {

            PortScannerThread thread =
                    new PortScannerThread(host, port);

            thread.start();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\nScan launched...");
        System.out.println("Time taken to launch threads: "
                + (endTime - startTime) + " ms");

    }
}
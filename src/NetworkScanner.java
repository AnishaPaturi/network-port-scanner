import java.net.InetAddress;

public class NetworkScanner {

    public static void main(String[] args) {

        String subnet = "192.168.1.";

        System.out.println("Scanning network...\n");

        for (int i = 1; i < 255; i++) {

            String host = subnet + i;

            try {

                InetAddress address = InetAddress.getByName(host);

                if (address.isReachable(100)) {

                    System.out.println(host + " is ACTIVE");

                }

            } catch (Exception ignored) {}

        }

        System.out.println("\nScan complete.");

    }
}
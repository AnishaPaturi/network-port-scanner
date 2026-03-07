import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScannerThread extends Thread {

    private String host;
    private int port;

    public PortScannerThread(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {

        try {

            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 200);

            String service = ServiceMapper.getService(port);

            System.out.println(
                    "Port " + port + " OPEN (" + service + ")"
            );

            socket.close();

        } catch (Exception e) {
            // port closed
        }
    }
}
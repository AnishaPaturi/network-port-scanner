import java.net.InetSocketAddress;
import java.net.Socket;

public class PortScannerUtil {

    public static boolean scanPort(String host, int port) {

        try {

            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 200);
            socket.close();

            return true;

        } catch (Exception e) {

            return false;

        }
    }
}
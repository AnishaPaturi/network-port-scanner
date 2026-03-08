import java.io.InputStream;
import java.net.Socket;

public class BannerGrabber {

    public static String grabBanner(String host, int port) {

        try {

            Socket socket = new Socket(host, port);

            InputStream in = socket.getInputStream();

            byte[] buffer = new byte[1024];

            int bytesRead = in.read(buffer);

            socket.close();

            if (bytesRead > 0) {
                return new String(buffer, 0, bytesRead).trim();
            }

        } catch (Exception ignored) {}

        return "No banner";
    }
}
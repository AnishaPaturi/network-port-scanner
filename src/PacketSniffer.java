import org.pcap4j.core.*;
import org.pcap4j.packet.*;
import org.pcap4j.packet.namednumber.IpNumber;

import java.util.List;

public class PacketSniffer {

    public static void main(String[] args) throws Exception {

        System.out.println("Listing network interfaces...\n");

        List<PcapNetworkInterface> interfaces = Pcaps.findAllDevs();

        if (interfaces == null || interfaces.isEmpty()) {
            System.out.println("No network interfaces found.");
            return;
        }

        int i = 0;
        for (PcapNetworkInterface nif : interfaces) {
            System.out.println(i + ": " + nif.getName() + " - " + nif.getDescription());
            i++;
        }

        // CHANGE THIS if needed (your WiFi adapter index)
        PcapNetworkInterface nif = interfaces.get(5);

        int snapLen = 65536;
        int timeout = 10;

        PcapHandle handle =
                nif.openLive(
                        snapLen,
                        PcapNetworkInterface.PromiscuousMode.PROMISCUOUS,
                        timeout
                );

        System.out.println("\nStarting packet capture...\n");

        PacketListener listener = packet -> {

            IpV4Packet ipv4 = packet.get(IpV4Packet.class);

            if (ipv4 != null) {

                String srcIP = ipv4.getHeader().getSrcAddr().getHostAddress();
                String dstIP = ipv4.getHeader().getDstAddr().getHostAddress();

                String protocol = ipv4.getHeader().getProtocol().name();

                int length = packet.length();

                System.out.println(
                        srcIP + "  →  " + dstIP + "  →  " + protocol + "  →  " + length + " bytes"
                );
            }

        };

        handle.loop(-1, listener);

        handle.close();
    }
}
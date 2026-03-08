import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScannerGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Network Security Port Scanner");

        JLabel ipLabel = new JLabel("Target IP:");
        JTextField ipField = new JTextField();

        JLabel startLabel = new JLabel("Start Port:");
        JTextField startField = new JTextField("1");

        JLabel endLabel = new JLabel("End Port:");
        JTextField endField = new JTextField("1024");

        JButton scanButton = new JButton("Start Scan");

        JTextArea resultArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultArea);

        frame.setLayout(null);

        ipLabel.setBounds(20, 20, 100, 30);
        ipField.setBounds(100, 20, 200, 30);

        startLabel.setBounds(20, 60, 100, 30);
        startField.setBounds(100, 60, 80, 30);

        endLabel.setBounds(190, 60, 80, 30);
        endField.setBounds(260, 60, 80, 30);

        scanButton.setBounds(120, 100, 120, 30);

        scrollPane.setBounds(20, 150, 320, 200);

        frame.add(ipLabel);
        frame.add(ipField);
        frame.add(startLabel);
        frame.add(startField);
        frame.add(endLabel);
        frame.add(endField);
        frame.add(scanButton);
        frame.add(scrollPane);

        frame.setSize(380, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        scanButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String host = ipField.getText();
                int startPort = Integer.parseInt(startField.getText());
                int endPort = Integer.parseInt(endField.getText());

                resultArea.setText("Scanning " + host + "...\n");

                for (int port = startPort; port <= endPort; port++) {

                    int finalPort = port;

                    new Thread(() -> {

                        boolean open = PortScannerUtil.scanPort(host, finalPort);

                        if (open) {

                            String service = ServiceMapper.getService(finalPort);

                            resultArea.append(
                                    "Port " + finalPort + " OPEN (" + service + ")\n"
                            );

                            String banner = BannerGrabber.grabBanner(host, finalPort);

                            if (!banner.equals("No banner")) {

                                resultArea.append("Banner: " + banner + "\n");

                            }

                        }

                    }).start();

                }

            }

        });

    }
}
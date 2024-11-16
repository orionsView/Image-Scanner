import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageScannerInterface implements ActionListener {
  JButton[] butList = new JButton[5];

  public static void main(String[] args) throws Exception {
    new ImageScannerInterface();
  }

  public ImageScannerInterface() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    panel.setLayout(new GridLayout(1, 5));

    frame.add(panel);
    frame.setSize(500, 300);
    frame.setLocation(800, 500);

    butList[0] = new JButton("Apperature");
    butList[1] = new JButton("Shutter Speed");
    butList[2] = new JButton("ISO");
    butList[3] = new JButton("Focal Length");
    butList[4] = new JButton("Tele Focal Length");

    panel.add(butList[0]);
    panel.add(butList[1]);
    panel.add(butList[2]);
    panel.add(butList[3]);
    panel.add(butList[4]);

    butList[0].addActionListener(this);
    butList[1].addActionListener(this);
    butList[2].addActionListener(this);
    butList[3].addActionListener(this);
    butList[4].addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < butList.length; i++) {
      if (butList[i] == e.getSource()) {
        try {
          new ImageDataScanner(i);
        } catch (Exception j) {
          System.out.println("error with image data scanner");
        }
      }
    }
  }
}
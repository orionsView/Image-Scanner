import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class ImageDataScanner extends JFrame implements KeyListener, ActionListener {
    String[] names = { "F Number", "Shutter Speed", "ISO", "Focal Length", "Focal Length Tele" };
    HashMap<String, Integer> map;
    String[][] stops;

    double minKey;
    double maxKey;
    double step;

    int selection;
    int len;

    Boolean showNums = false;

    public ImageDataScanner(int num) throws Exception {
        System.out.println("test");

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0, 0);
        this.setSize(2050, 1200);
        this.setBackground(Color.white);

        this.addKeyListener(this);

        selection = num;

        // stop numbers
        stops = new String[][] {
                // f-stop
                { "1.0", "1.4", "1.6", "1.8", "2.0", "2.2", "2.5", "2.8", "3.2", "3.5", "4.0", "4.5", "5.0", "5.6",
                        "6.3", "7.1", "8.0", "9.0", "10.0", "11.0", "13.0", "14.0", "16.0", "22.0", "32.0", },
                // shutter-speed
                { "1/8000", "1/4000", "1/3200", "1/2500", "1/2000", "1/1600", "1/1250", "1/1000", "1/800", "1/640",
                        "1/500", "1/400", "1/320", "1/250", "1/200", "1/160", "1/125", "1/100", "1/80", "1/60", "1/50",
                        "1/40",
                        "1/30", "1/25", "1/20", "1/15", "1/13", "1/10", "1/8", "1/6", "1/5", "1/4", "1/3", "0.4", "0.5",
                        "0.6", "0.8", "1", "1.3", "1.6", "2", "2.5", "3.2", "4", "5", "6", "8", "10", "13", "15", "20",
                        "25", "30", "30+" },
                // iso
                { "0", "100", "125", "160", "200", "250", "320", "400", "500", "640", "800", "1000", "1250", "1600",
                        "2000", "2500", "3200", "4000", "5000", "6400", "12800" },
                // focal-length

                { "15.0", "16.0", "17.0", "18.0", "19.0", "20.0", "21.0", "22.0", "23.0", "24.0", "25.0", "26.0",
                        "27.0", "28.0", "29.0", "30.0", "31.0", "32.0", "33.0", "34.0", "35.0", "36.0", "37.0", "38.0",
                        "39.0", "40.0", "41.0", "42.0", "43.0", "44.0", "45.0", "46.0", "47.0", "48.0", "49.0", "50.0",
                        "51.0",
                        "52.0", "53.0", "54.0", "55.0", "56.0", "57.0", "58.0", "59.0", "60.0", "61.0", "62.0", "63.0",
                        "64.0", "65.0", "66.0", "67.0", "68.0", "69.0", "70.0", "71.0", "72.0", "73.0", "74.0", "75.0",
                        "76.0",
                        "77.0", "78.0", "79.0", "80.0", "81.0", "82.0", "83.0", "84.0", "85.0", "86.0" },

                // tele focal length

                {
                        "99.0", "100.0", "101.0", "102.0", "103.0", "104.0", "105.0", "106.0", "107.0", "108.0",
                        "109.0", "110.0", "111.0", "112.0", "113.0", "114.0", "115.0", "116.0", "117.0",
                        "118.0", "119.0", "120.0", "121.0", "122.0", "123.0", "124.0", "125.0", "126.0", "127.0",
                        "128.0", "129.0", "130.0", "131.0", "132.0", "133.0", "134.0", "135.0", "136.0",
                        "137.0", "138.0", "139.0", "140.0", "141.0", "142.0", "143.0", "144.0", "145.0", "146.0",
                        "147.0", "148.0", "149.0", "150.0", "151.0", "152.0", "153.0", "154.0", "155.0",
                        "156.0", "157.0", "158.0", "159.0", "160.0", "161.0", "162.0", "163.0", "164.0", "165.0",
                        "166.0", "167.0", "168.0", "169.0", "170.0", "171.0", "172.0", "173.0", "174.0",
                        "175.0", "176.0", "177.0", "178.0", "179.0", "180.0", "181.0", "182.0", "183.0", "184.0",
                        "185.0", "186.0", "187.0", "188.0", "189.0", "190.0", "191.0", "192.0", "193.0",
                        "194.0", "195.0", "196.0", "197.0", "198.0", "199.0", "200.0", "201.0", "202.0", "203.0",
                        "204.0", "205.0", "206.0", "207.0", "208.0", "209.0", "210.0", "211.0", "212.0",
                        "213.0", "214.0", "215.0", "216.0", "217.0", "218.0", "219.0", "220.0", "221.0", "222.0",
                        "223.0", "224.0", "225.0", "226.0", "227.0", "228.0", "229.0", "230.0", "231.0",
                        "232.0", "233.0", "234.0", "235.0", "236.0", "237.0", "238.0", "239.0", "240.0", "241.0",
                        "242.0", "243.0", "244.0", "245.0", "246.0", "247.0", "248.0", "249.0", "250.0",
                        "251.0", "252.0", "253.0", "254.0", "255.0", "256.0", "257.0", "258.0", "259.0", "260.0",
                        "261.0", "262.0", "263.0", "264.0", "265.0", "266.0", "267.0", "268.0", "269.0",
                        "270.0", "271.0", "272.0", "273.0", "274.0", "275.0", "276.0", "277.0", "278.0", "279.0",
                        "280.0", "281.0", "282.0", "283.0", "284.0", "285.0", "286.0", "287.0", "288.0",
                        "289.0", "290.0", "291.0", "292.0", "293.0", "294.0", "295.0", "296.0", "297.0", "298.0",
                        "299.0", "300.0", "301.0", "302.0", "303.0", "304.0", "305.0", "306.0", "307.0",
                        "308.0", "309.0", "310.0", "311.0", "312.0", "313.0", "314.0", "315.0", "316.0", "317.0",
                        "318.0", "319.0", "320.0", "321.0", "322.0", "323.0", "324.0", "325.0", "326.0",
                        "327.0", "328.0", "329.0", "330.0", "331.0", "332.0", "333.0", "334.0", "335.0", "336.0",
                        "337.0", "338.0", "339.0", "340.0", "341.0", "342.0", "343.0", "344.0", "345.0",
                        "346.0", "347.0", "348.0", "349.0", "350.0", "351.0", "352.0", "353.0", "354.0", "355.0",
                        "356.0", "357.0", "358.0", "359.0", "360.0", "361.0", "362.0", "363.0", "364.0",
                        "365.0", "366.0", "367.0", "368.0", "369.0", "370.0", "371.0", "372.0", "373.0", "374.0",
                        "375.0", "376.0", "377.0", "378.0", "379.0", "380.0", "381.0", "382.0", "383.0",
                        "384.0", "385.0", "386.0", "387.0", "388.0", "389.0", "390.0", "391.0", "392.0", "393.0",
                        "394.0", "395.0", "396.0", "397.0", "398.0", "399.0", "400.0" }

        };

        len = stops[selection].length;

        // scanning database
        Scanner scan = new Scanner(new File("data2024.txt"));
        String target = names[selection];
        int wordCount = 1;

        if (target.indexOf(" ") != -1) {
            wordCount = 2;
            target = target.substring(0, target.indexOf(" "));
        }
        // System.out.println("target: " + target);
        map = new HashMap<>();

        while (scan.hasNextLine()) {
            if (scan.next().equals(target)) {
                if (wordCount == 2) {
                    scan.next();
                }
                scan.next();
                String result = scan.next();
                // System.out.println(result);
                if (selection == 4) {
                    // round to whole num, add(.0)
                    result = roundVal(result);
                }
                if (map.containsKey(result)) {
                    map.put(result, map.get(result) + 1);
                } else {
                    map.put(result, 1);
                }
            }
            scan.nextLine();

        }

        // System.out.println(map);
        scan.close();

    }

    public String roundVal(String result) {
        double val = Double.parseDouble(result);

        return String.valueOf(Math.round(val)) + ".0";
    }

    public int findIndex(String key) {
        for (int i = 0; i < len; i++) {
            if (key.equals(stops[selection][i])) {
                return i;
            }
        }

        if (key.equals("0.0")) {
            System.out.println("Error: " + key);
            return -1;
        }
        return len - 1;
    }

    public double findMean() {
        double total = 0;
        int pop = 0;
        for (String key : map.keySet()) {
            total += fracToDouble(key) * map.get(key);
            pop += map.get(key);
        }

        return total / pop;
    }

    // [mode, num of mode]
    public double[] findMode() {
        int max = 0;
        double mode = 0;
        for (String key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
                mode = fracToDouble(key);
            }
        }
        double[] ret = { mode, max };

        return ret;
    }

    // standard deviation
    public double findSD() {
        double SD = 0.0;
        double mean = findMean();
        int size = 0;

        for (String key : map.keySet()) {
            for (int i = 0; i < map.get(key); i++) {
                SD += Math.pow(fracToDouble(key) - mean, 2);
                size++;
            }
        }
        SD /= size - 1;

        SD = Math.sqrt(SD);

        return SD;
    }

    public double fracToDouble(String str) {
        String[] splited = str.split("/");

        return splited.length > 1 ? Double.parseDouble(splited[0]) / Double.parseDouble(splited[1])
                : Double.parseDouble(str);
    }

    public int findPos(double mean) {
        double a = 0;
        double b = 0;
        int i = 0;
        for (i = 1; i < len; i++) {
            if (mean < fracToDouble(stops[selection][i])) {
                System.out.println(i);
                b = fracToDouble(stops[selection][i]);
                a = fracToDouble(stops[selection][i - 1]);
                break;
            }

        }

        int space = 2000 / len;
        // System.out.println("space" + space);
        // System.out.println((int)((mean-a)/(b-a) * space));
        return (int) ((mean - a) / (b - a) * space) + space * (i - 1) + 30;
    }

    public void paint(Graphics g) {

        boolean flip = false;

        for (int i = 0; i < len; i++) {
            if (flip) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.black);
            }
            g.drawString("" + stops[selection][i], i * (2000 / len) - 5 + 30, 1120);
            flip = !flip;
        }

        g.setColor(Color.black);

        for (String key : map.keySet()) {
            Random rando = new Random();

            g.setColor(new Color(rando.nextInt(255), rando.nextInt(255), rando.nextInt(255)));
            g.drawString("Add number per collum? (y/n)", 950, 100);
            g.setColor(Color.BLACK);

            // g.drawLine(0, 1100, 2000, 1100);
            int index = findIndex(key);
            int hei = (int) ((map.get(key) / findMode()[1]) * 800);
            g.drawLine(index * (2000 / len) + 30, 1100, index * (2000 / len) + 30, 1100 - hei);

            // System.out.println(key + ": " + (1100 - (hei)));

            int numOfDigits = 0;
            int temp = map.get(key);

            while (temp > 0) {
                numOfDigits++;
                temp /= 10;
            }

            if (showNums) {
                g.fillRect((2000 / len) * index + 40, 1100 - (map.get(key) / 2) - 11, numOfDigits * 7, 15);
                g.setColor(Color.white);
                g.drawString("" + map.get(key), (2000 / len) * index + 40, 1100 - (map.get(key) / 2));
                g.setColor(Color.black);
                // System.out.println("triggered");

            } else {
                g.setColor(Color.white);
                g.fillRect((2000 / len) * index + 40, 1100 - (map.get(key) / 2) - 11, numOfDigits * 7, 15);
                g.setColor(Color.BLACK);

            }
        }
        // System.out.println("________________________________________");

        Color SDColor = new Color(255, 102, 0);

        // draw mode, mean and SD values
        if (showNums) {
            g.setColor(Color.blue);
            g.drawString(String.format("Mean: %.2f", findMean()), 900, 200);

            g.setColor(SDColor);
            g.drawString(String.format("SD: %.2f", findSD()) + "", 1100, 200);

            g.setColor(Color.black);
            g.drawString("Mode: " + findMode()[0] + "", 1000, 200);

        } else {
            g.setColor(Color.white);
            g.fillRect(900, 150, 500, 50);
        }

        if (showNums) {
            // draw mean line
            int meanPos = findPos(findMean());
            // System.out.println(meanPos);
            g.setColor(Color.blue);
            g.drawLine(meanPos, 1100, meanPos, 1100 - 800);

            // draw SD lines
            g.setColor(SDColor);
            g.drawLine((findPos(findMean() + findSD())), 1100, (findPos(findMean() + findSD())), 1100 - 800);
            g.drawLine((findPos(findMean() - findSD())), 1100, (findPos(findMean() - findSD())), 1100 - 800);
        } else {

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyChar());
        if (e.getKeyChar() == 'y') {
            // System.out.println("test1");
            showNums = true;
            repaint();
        } else if (e.getKeyChar() == 'n') {
            // System.out.println("test2");
            showNums = false;
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("Released: " + e.getKeyChar());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
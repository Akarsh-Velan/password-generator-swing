import org.apache.commons.lang3.RandomStringUtils;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class swing {
    public static void main(String[] args) {
        JFrame f = new JFrame("Random Password Generator");

        f.getContentPane().setBackground(new java.awt.Color(229, 201, 190));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel l1, l2, l3, l4,l5;
        l1 = new JLabel("Welcome to Random Password Generator!");
        l1.setBounds(120, 30, 1000, 30);
        l2 = new JLabel("With this tool, you can generate a random password of any length.");
        l2.setBounds(55, 50, 1000, 30);
        l3 = new JLabel("Enter Required Length of Password: ");
        l3.setBounds(55, 90, 1000, 30);
        l4 = new JLabel("");
        l4.setBounds(55, 130, 1000, 30);
        l5 = new JLabel("");
        l5.setBounds(55, 168,1000,30);


        JTextField tf1 = new JTextField();
        tf1.setBounds(285, 90, 50, 30);
        JTextField tf2 = new JTextField();
        tf2.setBounds(190, 130, 175, 30);
        tf2.setVisible(false);
        JTextField tf3 = new JTextField();
        tf3.setBounds(277,170,175,30);
        tf3.setVisible(false);

        JButton b1 = new JButton("Generate Password");
        b1.setFocusable(false);
        b1.setBounds(67, 175, 179, 58);

        JButton b2 = new JButton("Save Password");
        b2.setBounds(252, 175, 179, 58);
        b2.setFocusable(false);

        JButton b3 = new JButton("View Saved Passwords");
        b3.setBounds(67, 237, 179, 58);
        b3.setFocusable(false);

        JButton b4 = new JButton("Exit");
        b4.setBounds(252, 237, 179, 58);
        b4.setFocusable(false);

        JButton b5 = new JButton("Copy");
        b5.setBounds(375, 130, 60, 30);
        b5.setFocusable(false);
        b5.setVisible(false);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String no = tf1.getText();
                String numbers = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-_|;:,<.>/~`+=\\'\"?";
                boolean check = false;

                for (int i = 0; i < no.length(); ++i) {
                    String t1 = String.valueOf(no.charAt(i));
                    if (numbers.contains(t1)) {
                        check = true;
                    }
                }
                if (!check & !no.contains(" ")) {
                    if (!no.isEmpty()) {
                        int length = Integer.parseInt(no);
                        if (length == 0) {
                            tf2.setVisible(false);
                            l4.setText("Error: Please Enter Only An Number To Generate Password!");
                            l4.setForeground(new Color(255, 0, 0));
                            b5.setVisible(false);
                        } else {

                            b1.setBounds(67, 205, 179, 58);
                            b2.setBounds(252, 205, 179, 58);
                            b3.setBounds(67, 267, 179, 58);
                            b4.setBounds(252, 267, 179, 58);

                            l4.setVisible(true);
                            tf2.setVisible(true);
                            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_|;:,<.>/~`+=\\'\"?";
                            String pswd = RandomStringUtils.random(length, characters);
                            l4.setForeground(new Color(0, 0, 0));
                            l4.setText("Generated Password: ");
                            tf2.setText(pswd);
                            tf2.setVisible(true);tf3.setVisible(true);
                            b5.setVisible(true);
                            l5.setVisible(true);
                            l5.setText("Reference To Save With Password: ");

                        }
                    } else {
                        tf2.setVisible(false);
                        b5.setVisible(false);
                        l5.setVisible(false);tf3.setVisible(false);
                        l4.setText("Error: Please Enter Only An Number To Generate Password!");
                        l4.setForeground(new Color(255, 0, 0));

                    }
                } else {
                    tf2.setVisible(false);
                    b5.setVisible(false);
                    l5.setVisible(false);tf3.setVisible(false);
                    l4.setText("Error: Please Enter Only An Number To Generate Password!");
                    l4.setForeground(new Color(255, 0, 0));

                }
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Enter file path to save the generated password.
                    //Ensure the file is read and write accessible.
                    FileWriter writeobj = new FileWriter("/Users/username/filepath/textfile.txt/", true);
                    String pswd = tf2.getText();
                    String info = tf3.getText();
                    writeobj.write(info+" "+pswd + "\n\n");
                    writeobj.close();
                } catch (IOException ex) {
                    System.out.println("An Error Occured.");
                    ex.printStackTrace();
                }
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Edit file path to a text file to save the generated password.
                    //Ensure the file is read and write accessible.
                    File fileobj = new File("/Users/username/filepath/textfile.txt/");
                    if (!Desktop.isDesktopSupported()) {
                        System.out.println("Not Supported!");
                        return;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    if (fileobj.exists())
                        desktop.open(fileobj);
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(tf2.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });

        f.add(b1);f.add(b2);f.add(b3);f.add(b4);f.add(b5);
        f.add(tf1);f.add(tf2);f.add(tf3);
        f.add(l1);f.add(l2);f.add(l3);f.add(l4);f.add(l5);
        f.setSize(500, 405);
        f.setLayout(null);
        f.setVisible(true);
    }
}
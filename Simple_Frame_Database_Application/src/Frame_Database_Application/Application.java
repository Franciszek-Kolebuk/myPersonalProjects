package Frame_Database_Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Application extends JFrame implements ActionListener{
    public static void main(String[] args) throws IOException {

        ArrayList<ID> listOfIDs = new ArrayList<ID>(); //array to store all IDs

        //label for showing all needed data
        JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setFont(new Font("Times New Roman", Font.PLAIN,30));
        label1.setForeground(Color.BLUE);


        //textfields
        JTextField enterID = new JTextField("Enter ID");
        enterID.setPreferredSize(new Dimension(250, 25));
        JTextField redText = new JTextField("R");
        redText.setPreferredSize(new Dimension(50, 25));
        JTextField greenText = new JTextField("G");
        greenText.setPreferredSize(new Dimension(50, 25));
        JTextField blueText = new JTextField("B");
        blueText.setPreferredSize(new Dimension(50, 25));


        //buttons
        JButton AddID = new JButton("Add ID");
        JButton DeleteID = new JButton("Delete ID");
        JButton ShowIDs = new JButton("Show IDs");
        JButton SortIDs = new JButton("Sort IDs");
        JButton ClearAll = new JButton("Clear All");


        AddID.addActionListener(new ActionListener() { //button that adds new ID number
            @Override
            public void actionPerformed(ActionEvent a) {
                if(a.getSource()==AddID){
                    try {
                        String getId = new String();
                        getId = enterID.getText();
                        Integer intID = Integer.valueOf(getId);
                        if (getId.length() == 6) {
                            listOfIDs.add(new ID(intID));
                            label1.setText("ID " + intID + " has been added to the list");
                        } else {
                            label1.setText("ID " + intID + " has not been added to the list as it not a valid ID");
                        }
                    }catch(Exception e){
                        label1.setText("Data type is incorrect");
                    }
                }

            }
        });

        DeleteID.addActionListener(new ActionListener() { //button that removes ID numbers
            @Override
            public void actionPerformed(ActionEvent a) {
                if(a.getSource()==DeleteID){
                    try {
                    String getId = new String();
                    getId = enterID.getText();
                    Integer intID =Integer.valueOf(getId);

                    if(listOfIDs.size()==0){
                            label1.setText("List is empty, can not remove value");
                        }
                    else {
                        for (int i = 0; i < listOfIDs.size(); i++) {
                            if (listOfIDs.get(i).getID() == intID) {
                                listOfIDs.remove(i);
                                label1.setText("ID " + intID + " has been removed from the list");
                                i--;
                            } else {
                                label1.setText("ID " + intID + " does not belong to the list");
                            }
                        }
                    }
                    }catch(Exception e){
                        label1.setText("Entered value not found inside list, or incorrect data type");
                    }
                }

            }
        });

        SortIDs.addActionListener(new ActionListener() { //button that sorts all ID numbers
            @Override
            public void actionPerformed(ActionEvent a) {
                if(a.getSource()==SortIDs){
                    if(listOfIDs.size()==0) {
                        label1.setText("List is empty, can not be sorted");
                    }
                    else {
                        Collections.sort(listOfIDs);
                        label1.setText("IDs has been sorted ascending");
                    }
                }

            }
        });

        ShowIDs.addActionListener(new ActionListener() { //button that shows all ID numbers and sets the color of the message
            @Override
            public void actionPerformed(ActionEvent a) {
                if(a.getSource()==ShowIDs) {
                    if (listOfIDs.size() == 0) {
                        label1.setText("List is empty");
                    } else {
                        try {
                            String getRed = new String();
                            getRed = redText.getText();
                            Integer redInt = Integer.valueOf(getRed);

                            String getGreen = new String();
                            getGreen = greenText.getText();
                            Integer greenInt = Integer.valueOf(getGreen);

                            String getBlue = new String();
                            getBlue = blueText.getText();
                            Integer blueInt = Integer.valueOf(getBlue);

                            if (255 >= redInt && redInt >= 0 && 255 >= greenInt && greenInt >= 0 && 255 >= blueInt && blueInt >= 0) {
                                label1.setForeground(new Color(redInt, greenInt, blueInt));
                                label1.setText(listOfIDs.toString());
                            } else {
                                label1.setForeground(Color.BLACK);
                                label1.setText("Value of Red ,Green, or Blue is greater than 255, or less than 0");
                            }
                        } catch (Exception e) {
                            label1.setForeground(Color.BLACK);
                            label1.setText("Data type of Red, Green, or Blue is incorrect");
                        }
                    }
                }

            }
        });

        ClearAll.addActionListener(new ActionListener() { //buttons that clears list of ID numbers
            @Override
            public void actionPerformed(ActionEvent a) {
                if(a.getSource()==ClearAll) {
                    if (listOfIDs.size() == 0) {
                        label1.setText("List is empty");
                    } else{
                        listOfIDs.clear();
                        label1.setText("List of IDs has been cleared");
                    }
                }

            }
        });


        //panel for buttons
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel1.add(AddID);
        panel1.add(DeleteID);
        panel1.add(ShowIDs);
        panel1.add(SortIDs);
        panel1.add(ClearAll);

        //panel for textfields
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.darkGray);
        panel2.add(enterID);
        panel2.add(redText);
        panel2.add(greenText);
        panel2.add(blueText);

        //panel for label1
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.lightGray);
        panel3.add(label1);


        //frame
        JFrame frame = new JFrame();
        frame.setTitle("App Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.orange);
        frame.add(panel1, BorderLayout.SOUTH);
        frame.add(panel2, BorderLayout.NORTH);
        frame.add(panel3, BorderLayout.CENTER);

        BufferedImage img = ImageIO.read(new URL(
                "http://www.java2s.com/style/download.png"));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}

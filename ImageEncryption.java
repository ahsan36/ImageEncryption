/*Image encryption using Java Swing involves creating a GUI interface to allow
* the user to select an image and encrypt the image. 
* We can encrypt the image using a specific key and decrypt that image using same key. */

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageEncryption {

    public static void operater(int key){

        // for open file windows
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.showOpenDialog(null); // show file
        File file = fileChooser.getSelectedFile(); // hava a selected file

        
        try{

            //Read data from file
            FileInputStream fileInput=new FileInputStream(file);

            byte[] data = new byte[fileInput.available()];
            fileInput.read(data);

            int i=0; // For each data encrypt
            for(byte x : data)
            {
                System.out.println(x);
                data[i]=(byte)(x^key); //XOR operation for encryption 
                i++;
            }

            // Write this data 
            FileOutputStream fileOutput = new FileOutputStream(file);
            fileOutput.write(data); // change data will be write and old data will be delete
            fileOutput.close();
            fileInput.close();

            JOptionPane.showMessageDialog(null, "Done"); // Show message

        }catch(Exception e)
        {
            e.printStackTrace(); //If any exception came throw at console
        }

    }
    public static void main(String args[]){

        JFrame f = new JFrame();
        f.setTitle("Image Encryption");
        f.setSize(400, 400);
        f.setLocationRelativeTo(null); // for center
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Easy to close windows
        
        Font font = new Font("Roboto", Font.BOLD, 25);

        // Creating button
        JButton button = new JButton();
        button.setText("Open Image");
        button.setFont(font);          

        // Creating text field
        JTextField textField = new JTextField(10);
        textField.setFont(font);

        // for clicking
        button.addActionListener(e->{ //Lamda function
            String text = textField.getText(); // For encryption key number
            int input = Integer.parseInt(text); // Convert String text into integer
            operater(input); // which number taken by user placed in key
        });


        f.setLayout(new FlowLayout());
        f.add(button);
        f.add(textField);

        f.setVisible(true);
    }
    
}

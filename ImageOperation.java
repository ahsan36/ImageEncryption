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

public class ImageOperation {

    public static void operate(int key){

        // for open file windows
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.showOpenDialog(null); // show file
        File file = fileChooser.getSelectedFile(); // hava a selected file

        //Read data from file
        try{

            FileInputStream fis=new FileInputStream(file);

            byte[] data=new byte[fis.available()];
            fis.read(data);

            int i=0; // For each data encrypt
            for(byte b : data)
            {
                System.out.println(b);
                data[i]=(byte)(b^key); //XOR operation for encryption 
                i++;
            }

            // Write this data 
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data); // change data will be write and old data will be delete
            fos.close();
            fis.close();

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
            int temp = Integer.parseInt(text); // Convert String text into integer
            operate(temp); // which number taken by user placed in key
        });


        f.setLayout(new FlowLayout());
        f.add(button);
        f.add(textField);

        f.setVisible(true);
    }
    
}

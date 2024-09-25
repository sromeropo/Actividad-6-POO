/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contactos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CRUD {

    public void Agregar(String newName, long newNumber){
        try {
            String nameNumberString;
            String name;
            long number;
            int index;
 
            File file = new File("contactos.txt");
 
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
                
                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }
 
            if (found == false) {
                
                nameNumberString = newName + "!" + String.valueOf(newNumber);
                raf.writeBytes(nameNumberString);
                raf.writeBytes(System.lineSeparator());
                
                JOptionPane.showMessageDialog(new JFrame(), "Contacto agregado");
                raf.close();
                
            } else {
                
                raf.close();
                JOptionPane.showMessageDialog(new JFrame(), "El contacto que deseas agregar ya existe");
                
            }
        }
 
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(new JFrame(), ioe);
        }
        catch (NumberFormatException nef) {
            JOptionPane.showMessageDialog(new JFrame(), nef);
        }
    }
    
    public void Contactos(String Name, JTextField nombre, JTextField numero){
        try{
            String nameNumberString;
            String name;
            long number;
            int index;
            
            
            File file = new File("contactos.txt");
                if (!file.exists()) {
                file.createNewFile();
            }
            
            RandomAccessFile raf=new RandomAccessFile(file,"rw");
            boolean found=false;
            
            while (raf.getFilePointer()< raf.length()){
                nameNumberString= raf.readLine();
                String[] lineSplit= nameNumberString.split("!");
                name=lineSplit[0];
                number=Long.parseLong(lineSplit[1]);

                if (name.equals(Name)){
                    found=true;
                    nombre.setText(name);
                    numero.setText(String.valueOf(number));
                    break;
                }
            
            }
            if (found==false){
                JOptionPane.showMessageDialog(new JFrame(), "El contacto con nombre " + Name + " no fue encontrado");
            }
        }
        catch (IOException ioe){
            JOptionPane.showMessageDialog(new JFrame(), ioe);
        }
        catch(NumberFormatException nef){
            JOptionPane.showMessageDialog(new JFrame(), nef);
        }
    }
    
    public void Actualizar(String newName, long newNumber){
        try{
            String nameNumberString;
            String name;
            long number;
            int index;
            
            File file = new File("contactos.txt");
 
            if (!file.exists()) {
                file.createNewFile();
            }
            
            RandomAccessFile raf=new RandomAccessFile(file,"rw");
            boolean found=false;
            
            while (raf.getFilePointer()< raf.length()){
                
                nameNumberString=raf.readLine();
                String[] lineSplit=nameNumberString.split("!");
                name=lineSplit[0];
                number=Long.parseLong(lineSplit[1]);
                
                if ((name.equals(newName))|| (number == newNumber)){
                    found=true;
                    break;
                }
            }
            
            if (found==true){
               File tmpFile=new File("temp.txt");
               
               RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
                raf.seek(0);
                while (raf.getFilePointer() < raf.length()) {
                    
                    nameNumberString = raf.readLine();
                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(0, index);
                    number=Long.parseLong(nameNumberString.substring(index+1));

                    if (name.equals(newName) || number==newNumber) {

                        nameNumberString = newName + "!" + String.valueOf(newNumber);
                    }

                    tmpraf.writeBytes(nameNumberString);

                    tmpraf.writeBytes(System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }
 
                raf.setLength(tmpraf.length());

                tmpraf.close();
                raf.close();

                tmpFile.delete();
                JOptionPane.showMessageDialog(new JFrame(), "Contacto actualizado");
            }

            else {

                raf.close();

                JOptionPane.showMessageDialog(new JFrame(), "El contacto con nombre " + newName + " no fue encontrado");  
            }
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(new JFrame(),
                        ioe);
            //System.out.println(ioe);
        }
 
        catch (NumberFormatException nef) {
            JOptionPane.showMessageDialog(new JFrame(),
                        nef);
            //System.out.println(nef);
        }
    }
    
    public void Eliminar(String newName){
        try {
 
            String nameNumberString;
            String name;
            long number;
            int index;
 
            // Using file pointer creating the file.
            File file = new File("contactos.txt");
 
            if (!file.exists()) {
 
                // Create a new file if not exists.
                file.createNewFile();
            }
 
            // Opening file in reading and write mode.
            RandomAccessFile raf
                = new RandomAccessFile(file, "rw");
            boolean found = false;
 
            // Checking whether the name of contact exists.
            // getFilePointer() give the current offset
            // value from start of the file.
            while (raf.getFilePointer() < raf.length()) {
 
                // reading line from the file.
                nameNumberString = raf.readLine();
 
                // splitting the string to get name and
                // number
                String[] lineSplit
                    = nameNumberString.split("!");
 
                // separating name and number.
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
 
                // if condition to find existence of record.
                if (name.equals(newName)) {
                    found = true;
                    break;
                }
            }
 
            // Delete the contact if record exists.
            if (found == true) {
 
                // Creating a temporary file
                // with file pointer as tmpFile.
                File tmpFile = new File("temp.txt");
 
                // Opening this temporary file
                // in ReadWrite Mode
                RandomAccessFile tmpraf
                    = new RandomAccessFile(tmpFile, "rw");
 
                // Set file pointer to start
                raf.seek(0);
 
                // Traversing the friendsContact.txt file
                while (raf.getFilePointer()
                       < raf.length()) {
 
                    // Reading the contact from the file
                    nameNumberString = raf.readLine();
 
                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(
                        0, index);
 
                    // Check if the fetched contact
                    // is the one to be deleted
                    if (name.equals(newName)) {
 
                        // Skip inserting this contact
                        // into the temporary file
                        continue;
                    }
 
                    // Add this contact in the temporary
                    // file
                    tmpraf.writeBytes(nameNumberString);
 
                    // Add the line separator in the
                    // temporary file
                    tmpraf.writeBytes(
                        System.lineSeparator());
                }
 
                // The contact has been deleted now
                // So copy the updated content from
                // the temporary file to original file.
 
                // Set both files pointers to start
                raf.seek(0);
                tmpraf.seek(0);
 
                // Copy the contents from
                // the temporary file to original file.
                while (tmpraf.getFilePointer()
                       < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }
 
                // Set the length of the original file
                // to that of temporary.
                raf.setLength(tmpraf.length());
 
                // Closing the resources.
                tmpraf.close();
                raf.close();
 
                // Deleting the temporary file
                tmpFile.delete();
                JOptionPane.showMessageDialog(new JFrame(),
                        "Contacto eliminado");
                ///System.out.println(" Friend deleted. ");
            }
 
            // The contact to be deleted
            // could not be found
            else {
 
                // Closing the resources.
                raf.close();
 
                // Print the message
                JOptionPane.showMessageDialog(new JFrame(),
                        "El contacto no existe");
                //System.out.println(" Input name"
                //                   + " does not exists. ");
            }
        }
 
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(new JFrame(),
                        ioe);
            //System.out.println(ioe);
        }
    }
       
}
    

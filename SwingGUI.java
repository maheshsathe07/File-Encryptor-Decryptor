import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SwingGUI implements ActionListener {
    JFrame f;
    JButton b;
    JTextField l;
    JButton aes;
    JButton bf;
    JButton cast;
    JButton save;
    JTextField fpath;
    JFileChooser saveFile;
    String secretKey = "HelloWorld";
    File fileO;
    File file1;
    JButton aesDecrypt;
    JButton bfDecrypt;
    JButton cascadeDecrypt;
    JFileChooser file;
    public SwingGUI(){

        f = new JFrame("File Encryption and Decryption System");
        b = new JButton("Select Files");
        l = new JTextField("NO File selected");
        aes = new JButton("Encrypt using AES Algorithm");
        bf = new JButton("Encrypt using Blowfish Algorithm");
        cast = new JButton("Encrypt using Cascading");
        fpath = new JTextField();
        aesDecrypt = new JButton("Decrypt AES Encryption");
        bfDecrypt = new JButton("Decrypt Blowfish Encryption");
        cascadeDecrypt = new JButton("Decrypt Cascade Encryption");

        l.setBounds(125,20,180,30);
        l.setEditable(false);


        b.setBounds(175,70,100,30);
        b.addActionListener(this);


        aes.setBounds(100,130,250,30);
        bf.setBounds(100,190,250,30);
        cast.setBounds(100,250,250,30);

        aes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile = new JFileChooser("D:\\");
                try{
                    int saveResp = saveFile.showSaveDialog(null);
                    if(saveResp==JFileChooser.APPROVE_OPTION){
                        file1 = new File(saveFile.getSelectedFile().getAbsolutePath());
                    }
                    FileAES.encrypt(fileO.getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                    l.setText("AES Encryption successful");
                }
                catch(Exception ne){
                    l.setText("AES Encryption Cancelled");
                }
            }
        });

        bf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile = new JFileChooser("D:\\");
                try{
                    int saveResp = saveFile.showSaveDialog(f);
                    if(saveResp==JFileChooser.APPROVE_OPTION){
                        FileBlowfish.encrypt(file.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                        fpath.setText("File Saved Successfully");
                        l.setText("Blowfish Encryption successful");
                    }
                }
                catch (Exception ne){
                    l.setText("Blowfish Encryption canceled");
                }

            }

        });

        cast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile = new JFileChooser("D:\\");
                try{
                    int saveResp = saveFile.showSaveDialog(f);
                    if(saveResp==JFileChooser.APPROVE_OPTION){
                        FileAES.encrypt(file.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                        FileBlowfish.encrypt(saveFile.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                        fpath.setText("File Saved Successfully");
                        l.setText("AES Encryption successful");
                    }
                }
                catch (Exception ne){
                    l.setText("Cascade Encryption canceled");
                }

            }
        });

        aesDecrypt.setBounds(100,310,250,30);
        aesDecrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile = new JFileChooser("D:\\");
                try{
                    int saveResp = saveFile.showSaveDialog(f);
                    if(saveResp==JFileChooser.APPROVE_OPTION){
                        file1 = new File(saveFile.getSelectedFile().getAbsolutePath());
                    }
                    FileAES.decrypt(fileO.getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                    l.setText("AES Decryption successful");
                }
                catch (Exception ne){
                    l.setText("AES Decryption cancelled");
                }
            }
        });

        bfDecrypt.setBounds(100,370,250,30);
        bfDecrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile = new JFileChooser("D:\\");
                try{
                    int saveResp = saveFile.showSaveDialog(f);
                    if(saveResp==JFileChooser.APPROVE_OPTION){
                        FileBlowfish.decrypt(file.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                        fpath.setText("File Saved Successfully");
                        l.setText("Blowfish Decryption successful");
                    }
                }
                catch(Exception ne){
                    l.setText("Blowfish Decryption cancelled");
                }

            }

        });

        cascadeDecrypt.setBounds(100,430,250,30);
        cascadeDecrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile = new JFileChooser("D:\\");
                try {
                    int saveResp = saveFile.showSaveDialog(f);
                    if(saveResp==JFileChooser.APPROVE_OPTION){
                        FileBlowfish.decrypt(file.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                        FileAES.decrypt(saveFile.getSelectedFile().getAbsolutePath(), secretKey, saveFile.getSelectedFile().getAbsolutePath());
                        fpath.setText("File Saved Successfully");
                        l.setText("Cascade Decryption successful");
                    }
                }
                catch (Exception ne){
                    l.setText("Cascade Decryption canceled");
                }

            }
        });
        fpath.setBounds(50,490,400,30);
        fpath.setEditable(false);
        fpath.setText("No files selected");

        f.add(b);
        f.add(aes);
        f.add(bf);
        f.add(cast);
        f.add(l);
        f.add(fpath);
        f.add(aesDecrypt);
        f.add(bfDecrypt);
        f.add(cascadeDecrypt);

        f.setSize(480,580);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }

    public static void main(String[] args) {
        new SwingGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        file = new JFileChooser("D:\\");
        int resp = file.showOpenDialog(null);
        if(resp == JFileChooser.APPROVE_OPTION) {
            fileO = new File(file.getSelectedFile().getAbsolutePath());
            fpath.setText("File Selected: " + fileO);
            l.setText("File Selected ");
        }
        }
    }


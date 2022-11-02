import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class DLG extends JDialog {
    private JPanel contentPane;
    private JTextField Text;
    private JButton Button1;
    private JButton Button2;
    private JButton Button3;

    static RandomAccessFile raf;
    static byte [] u;

    String Path;

    public DLG() {
        setContentPane(contentPane);
        setModal(true);



        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path = Text.getText();
                try {

                   raf = new RandomAccessFile(Path,"rw");

                    u = new byte[(int) raf.length()];

                    raf.read(u,0,(int)raf.length());
                    raf.seek(0);

                    for (int i = 0; i < raf.length();i++)
                    {
                            u[i]=(byte) ~u[i];
                    }

                    raf.write(u,0,(int) raf.length());

                    raf.close();

                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showConfirmDialog(null,fileNotFoundException.getMessage(),"Ошибка",JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                } catch (IOException ioException) {
                    JOptionPane.showConfirmDialog(null,ioException.getMessage(),"Ошиба",JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path = Text.getText();
                try {
                    raf = new RandomAccessFile(Path,"rws");
                    u = new byte[(int) raf.length()];
                    raf.read(u,0,(int)raf.length());
                    raf.seek(0);
                    for (int i = 0; i < raf.length()-1;i++)
                    {
                        u[i]=(byte)(u[i] << 4 &0xff| u[i] >> 4 &0xf);

                    }
                    raf.write(u,0,(int) raf.length());
                    raf.close();

                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showConfirmDialog(null,fileNotFoundException.getMessage(),"Ошибка",JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                } catch (IOException ioException) {
                    JOptionPane.showConfirmDialog(null,ioException.getMessage(),"Ошибка",JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path = Text.getText();
                try {
                    raf = new RandomAccessFile(Path,"rw");

                    u = new byte[(int) raf.length()];

                    raf.read(u,0,(int)raf.length());
                    raf.seek(0);

                    for (int i = 1; i < raf.length()/2;i++)
                    {
                        int mid = (int) u.length / 2;

                        byte s = u[mid - i];
                        u[mid - i] = u[mid + i];
                        u[mid + i] = s;

                    }
                    raf.write(u,0,(int) raf.length());
                    raf.close();

                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showConfirmDialog(null,fileNotFoundException.getMessage(),"Ошибка",JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                } catch (IOException ioException) {
                    JOptionPane.showConfirmDialog(null,ioException.getMessage(),"Ошибка",JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

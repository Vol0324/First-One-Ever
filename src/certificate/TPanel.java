package certificate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

public class TPanel extends JPanel {
    private GetPicture gp = new GetPicture();
    private JTextArea md11Info;
    private JLabel md11Label;

    public TPanel(){
        setLayout(null);
        setBounds(0, 0, 800, 600);
        md11Label = new JLabel("New label");
        md11Label.setBounds(267, 28, 423, 350);
        add(md11Label);

        JButton btnNewButton = new JButton("Refresh");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refresh();
                setInfo(gp.getInfo_pic());
                setImage(gp.getJpg_file());
            }
        });
        btnNewButton.setBounds(677, 542, 117, 29);
        add(btnNewButton);

        md11Info = new JTextArea();
        md11Info.setBounds(6, 28, 173, 342);
        add(md11Info);
        md11Info.setLineWrap(true);
        md11Info.setEditable(false);
        md11Info.setBackground(getBackground());
    }

    public void setImage(String path){
        ImageIcon ii;
        try {
            java.net.URL url = new java.net.URL(path);
            if(url != null){
                ii = new ImageIcon(url);
                md11Label.setIcon(ii);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    public void refresh(){
        gp.randomPage();
        gp.getInfo();
    }
    public void setInfo(ArrayList<String> infos){
        Iterator<String> itr = infos.iterator();
        String info = "";
        while(itr.hasNext()){
            info += itr.next() + "\n" + "\n";
        }
        md11Info.setText(info);

    }
}

package certificate;

import javax.swing.*;
import java.awt.*;

public class TFrame extends JFrame {
    private JPanel panel = new TPanel();

    public TFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 600);
        setResizable(false);
        setContentPane(panel);

    }
}

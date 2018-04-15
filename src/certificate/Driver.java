package certificate;
import java.awt.*;


public class Driver {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    TFrame t = new TFrame();
                    t.setVisible(true);
                }
                catch(Exception e){
                    System.exit(0);
                }

            }}
        );




    }

}

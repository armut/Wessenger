import fenestra.Palette;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

/**
 * zamma on 3/29/18.
 */
public class Main {
    private static Messaging m;
    private static Login l;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //m = new Messaging(Palette.deepTaupe, Palette.paynesGrey,
                //        Palette.middleRedPurple, "Messaging Application", WIDTH, HEIGHT);
                l = new Login(Palette.deepTaupe, Palette.paynesGrey,
                        Palette.middleRedPurple, "Log In", WIDTH/4, HEIGHT/4);
            }
        });
    }
}

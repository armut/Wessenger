import fenestra.Fenestra;
import fenestra.Palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * zamma on 3/29/18.
 */
public class MessagingFrame extends Fenestra {
    private SessionPane sessionPane;
    private SessionHistoryPane sessionHistoryPane;
    
    public MessagingFrame(Color bgColor, Color captionColor, Color titleColor,
                          String title, int width, int height) {
        super(bgColor, captionColor, titleColor, title, width, height);
        
        sessionPane = new SessionPane(Palette.mistyRose, 200, height);
        sessionHistoryPane = new SessionHistoryPane();
        
        JPanel jpnlLoom = new JPanel(new BorderLayout());
        jpnlLoom.setBackground(bgColor);
        jpnlLoom.add(new MainMenu(), BorderLayout.PAGE_START);
        jpnlLoom.add(sessionPane, BorderLayout.LINE_START);
        
        JPanel jpnlInnerLoom = new JPanel(new BorderLayout());
        jpnlInnerLoom.add(new InputPane(), BorderLayout.PAGE_START);
        jpnlInnerLoom.add(sessionHistoryPane, BorderLayout.CENTER);
        
        jpnlLoom.add(jpnlInnerLoom, BorderLayout.CENTER);
        
        add(jpnlLoom, BorderLayout.CENTER);
        
        Main.loadSessions(sessionPane);
        
        // Finish the server thread after closing this frame
        // to exit the application.
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
                try {
                    Main.serverSocket.close();
                    Main.serverSocket = null;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
    
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.out.println("meraba");
    
                
            }
        });
    }
     
    public SessionHistoryPane getSessionHistoryPane() { return sessionHistoryPane; }
    
    public SessionPane getSessionPane() { return sessionPane; }
}

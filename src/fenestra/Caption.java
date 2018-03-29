package fenestra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Caption extends JPanel {
    private static ImageIcon imgClose = new ImageIcon("res/close.png");
    private static ImageIcon imgCloseOver = new ImageIcon("res/close-over.png");
    private static ImageIcon imgMin = new ImageIcon("res/min.png");
    private static ImageIcon imgMinOver = new ImageIcon("res/min-over.png");
    private int posX, posY;

    public Caption(JFrame frame, Color bgColor, Color titleColor, String title) {
        setBackground(bgColor);
        setLayout(new BorderLayout());
        dragWindow(frame);

        JPanel jpnlControl = new JPanel();
        jpnlControl.setPreferredSize(new Dimension(50, 25));
        jpnlControl.setOpaque(false);
        jpnlControl.add(initCloseButton(frame));
        jpnlControl.add(initMinimizeButton(frame));
        add(jpnlControl, BorderLayout.LINE_START);

        // Add a filler panel to center the caption title.
        JPanel jpnlFiller = new JPanel();
        jpnlFiller.setPreferredSize(new Dimension(50, 25));
        jpnlFiller.setBackground(bgColor);
        jpnlFiller.setOpaque(true);
        add(jpnlFiller, BorderLayout.LINE_END);

        add(initCaptionTitle(title, titleColor));
    }

    public Caption(JDialog frame, Color bgColor, Color titleColor, String title) {
        setBackground(bgColor);
        setLayout(new BorderLayout());
        dragWindow(frame);

        JPanel jpnlControl = new JPanel();
        jpnlControl.setPreferredSize(new Dimension(25, 25));
        jpnlControl.setOpaque(false);
        jpnlControl.add(initCloseButton(frame));
        add(jpnlControl, BorderLayout.LINE_START);

        // Add a filler panel to center the caption title.
        JPanel jpnlFiller = new JPanel();
        jpnlFiller.setPreferredSize(new Dimension(25, 25));
        jpnlFiller.setBackground(bgColor);
        jpnlFiller.setOpaque(true);
        add(jpnlFiller, BorderLayout.LINE_END);

        add(initCaptionTitle(title, titleColor));
    }

    private JButton initCloseButton(Window window) {
        JButton jbClose = new JButton(imgClose);
        jbClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbClose.setToolTipText("Close");
        jbClose.setOpaque(false);
        jbClose.setFocusPainted(false);
        jbClose.setBorderPainted(false);
        jbClose.setContentAreaFilled(false);
        jbClose.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jbClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                window.dispose();
            }
        });
        jbClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                jbClose.setIcon(imgCloseOver);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                jbClose.setIcon(imgClose);
            }
        });
        return jbClose;
    }

    private JButton initMinimizeButton(JFrame frame) {
        JButton jbMin = new JButton(imgMin);
        jbMin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jbMin.setToolTipText("Minimize");
        jbMin.setOpaque(false);
        jbMin.setFocusPainted(false);
        jbMin.setBorderPainted(false);
        jbMin.setContentAreaFilled(false);
        jbMin.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jbMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setExtendedState(Frame.ICONIFIED);
            }
        });
        jbMin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                jbMin.setIcon(imgMinOver);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                jbMin.setIcon(imgMin);
            }
        });
        return jbMin;
    }

    private JPanel initCaptionTitle(String title, Color titleColor) {
        JPanel jpnlTitle = new JPanel();
        jpnlTitle.setOpaque(false);
        JLabel jlblTitle = new JLabel(title);
        jlblTitle.setForeground(titleColor);
        jpnlTitle.add(jlblTitle);
        return jpnlTitle;
    }

    private void dragWindow(Window window) {
        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                posX = mouseEvent.getX();
                posY = mouseEvent.getY();
            }
        });
        window.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                window.setLocation(mouseEvent.getXOnScreen() - posX,
                        mouseEvent.getYOnScreen() - posY);
            }
        });
    }
}

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DrawPanel panel = new DrawPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.addKeyListener(panel);
    }
}

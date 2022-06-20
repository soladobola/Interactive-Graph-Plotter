import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow window = new MainWindow(800, 500, "GraphDrawer by Mladen");
                window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                window.setMinimumSize(new Dimension(100, 500));
                window.setVisible(true);

            }
        });

    }
}

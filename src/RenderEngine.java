import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RenderEngine implements ActionListener {

    private int delay = 10;
    private Timer timer;

    private Updatable target;

    public RenderEngine(Updatable target){
        // Add reference to canvas, direct communication
        this.target = target;

        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        target.update();
    }
}

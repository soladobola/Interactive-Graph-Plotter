import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private UserInterface userInterface;
    // public for communication with drawingOptions
    public MainCanvas canvas;

    private Communicator communicator;

    private CanvasInput canvasInput;

    private RenderEngine render;


    public MainWindow(int windowWidth, int windowHeight, String title)
    {
        super(title);
        this.setSize(new Dimension(windowWidth, windowHeight));

        // Layout settings
        this.setLayout(new BorderLayout());

        // Adding UserInterface

        this.userInterface = new UserInterface(windowWidth, windowHeight);
        this.add(userInterface, BorderLayout.WEST);

        // Adding Canvas, rest of space
        this.canvas = new MainCanvas(windowWidth, windowHeight);
        this.add(canvas);
        this.pack();

        //set up Communicator
        this.communicator = new Communicator(canvas, userInterface);

        // Set up Render
        this.render = new RenderEngine(communicator);

        // Set up Event listeners
        this.canvasInput = new CanvasInput(this.canvas, this.communicator);

        this.userInterface.setCommunicator(communicator);

    }

}

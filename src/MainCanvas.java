import java.awt.*;
import java.awt.image.BufferStrategy;

public class MainCanvas extends Canvas {


    // Default colors
    private Color backgroundColor = new Color(45,56,60);
    private Color drawColor = Color.YELLOW;
    private Color axisColor = Color.WHITE;


    public BufferStrategy bs;
    public Graphics g;


    public MainCanvas(int WindowdWidth, int WindowHeight){
        super();
        Dimension dim = new Dimension(WindowdWidth*2/3, WindowHeight);
        this.setPreferredSize(dim);

        //default background
        this.setBackground(backgroundColor);

    }


    // Setters for colors
    public void setAxisColor(Color axisColor) {
        this.axisColor = axisColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.setBackground(this.backgroundColor);
    }

    public void setDrawColor(Color drawColor) {
        this.drawColor = drawColor;
    }


    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getDrawColor() {
        return drawColor;
    }

    public Color getAxisColor() {
        return axisColor;
    }

}

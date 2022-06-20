import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Communicator implements Updatable {

    public MainCanvas canvas;


    // Wait for passing!
    public Computable funkcija;

    public double step = 0.05;

    // For faster entry
    public int canvasWidth;
    public int canvasHeight;

    // Public for direct communication
    // Default is 10 in every direction
    public double minX = -5;
    public double maxX = 5;

    public double minY = -5;
    public double maxY = 5;

    public boolean showXaxis = true;
    public boolean showYaxis = true;

    public int numOfPixelsX;
    public int numOfPixelsY;

    // just test for now!
    public int scalex = 1;
    public int scaley = 1;


    private UserInterface userInterface;
    private  ArrayList<Point> dataBuffer;

    // Need to be public for other classes do chose when to update this
    public void fillBuffer() {

        if(dataBuffer == null) dataBuffer = new ArrayList<>();
        if(funkcija == null) return;
        // buffer size has points of range size for both ends
        dataBuffer.clear();
        double x = (double) minX - Math.abs(minX - maxX);

        try {
            while (x <= (double) maxX + Math.abs(minX - maxX)) {
                dataBuffer.add(new Point(x, funkcija.compute(x)));
                x += scalex*step;
            }
        }
        catch(Exception ex){
            userInterface.functionInput.setBackground(Color.ORANGE);
            JOptionPane.showMessageDialog(null, "Invalid input for a function.");
            dataBuffer = null;
            funkcija = null;
        }
    }

    public Communicator(MainCanvas canvas, UserInterface userInterface)
    {
        this.canvas = canvas;
        this.userInterface = userInterface;

        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();

        numOfPixelsX = (int)(canvas.getWidth()/Math.abs(maxX - minX));
        numOfPixelsY = (int)(canvas.getHeight()/Math.abs(maxY - minY));

        this.dataBuffer = new ArrayList<>();
    }


    @Override
    public void update() {
        canvas.bs = canvas.getBufferStrategy();
        if(canvas.bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        canvas.g = canvas.bs.getDrawGraphics();
        //canvas.g.setColor(canvas.getBackgroundColor());
        canvas.g.clearRect(0, 0, canvasWidth, canvasHeight);
        // Draw here!!!

        //////////////////////////////////////////////////////////////////////////////////////////////////
        //draw axis! Support scaling

        double rangeX = Math.abs(maxX - minX);
        double rangeY = Math.abs(maxY - minY);

        // Calculate scale for X
        scalex = 1;
        while(rangeX / scalex > 10){
            scalex++;
        }

        // Calculate scale for Y
        scaley = 1;
        while(rangeY / scaley > 10){
            scaley++;
        }

        double scaledRangeX = rangeX/scalex;
        double scaledRangeY = rangeY/scaley;
        numOfPixelsX = (int)(canvas.getWidth()/scaledRangeX);
        numOfPixelsY = (int)(canvas.getHeight()/scaledRangeY);

        canvas.g.setColor(canvas.getAxisColor());


        // Draw X axis
        canvas.g.drawLine(0, (int)(maxY/scaley*numOfPixelsY), canvasWidth, (int)(maxY/scaley*numOfPixelsY));
        // Draw Y axis
        canvas.g.drawLine(-(int)(minX/scalex*numOfPixelsX) , 0, -(int)(minX/scalex*numOfPixelsX), canvasHeight);


        // Draw numerals for X-axis
        int startNumberX = (int)(minX);
        while(startNumberX % scalex != 0){startNumberX++;}

        if(minX > 0){
            if((int)(minX) < startNumberX) {startNumberX -= scalex;}
        }


        for(int i = 0; i <= (int)scaledRangeX; i++) {
            if (i != (int) (Math.abs(minX) / scalex) || minX > 0)
                canvas.g.fillRect(i * numOfPixelsX - (int) ((minX / scalex - (int) (minX / scalex)) * numOfPixelsX) - 1, (int) (maxY / scaley * numOfPixelsY) - 3, 3, 6);

            if (showXaxis) {
                canvas.g.drawString(String.valueOf(startNumberX), i * numOfPixelsX - (int) ((minX / scalex - (int) (minX / scalex)) * numOfPixelsX), (int) (maxY / scaley * numOfPixelsY) + 15);
                startNumberX += scalex;
            } else startNumberX += scalex;
        }
        // Numerals for X-axis END


        // Draw numerals for Y-axis
        int startNumberY = (int)(maxY);
        while(startNumberY % scaley != 0){startNumberY--;}

        if(maxY < 0){
            if((int)(maxY) > startNumberY) {startNumberY += scaley;}
        }

        for(int i = 0; i <= (int)scaledRangeY; i++){
            if(i!=(int)(Math.abs(maxY/scaley)) || maxY < 0)
                canvas.g.fillRect(-(int)(minX/scalex*numOfPixelsX) - 3, i*numOfPixelsY + (int)((maxY/scaley - (int)(maxY/scaley))*numOfPixelsY) - 1, 6, 3);

            if(startNumberY == 0){ startNumberY-=scaley; continue;}
            if(showYaxis) {
                canvas.g.drawString(String.valueOf(startNumberY), -(int) (minX/scalex * numOfPixelsX) - 30 , i * numOfPixelsY + (int) ((maxY/scaley - (int)(maxY/scaley)) * numOfPixelsY) + 5);
                startNumberY -= scaley;}
            else startNumberY-= scaley;
        }

        // Numerals for Y-axis END

        // Draw points of a function BEGIN

        canvas.g.setColor(canvas.getDrawColor());
        if(dataBuffer != null && dataBuffer.size() > 1) {
            Point first = dataBuffer.get(0);
            Point second = dataBuffer.get(1);
            for (int i = 2; i < dataBuffer.size(); i++) {

                canvas.g.drawLine((int) (first.x * numOfPixelsX/scalex - minX/scalex * numOfPixelsX), -(int) (first.y * numOfPixelsY/scaley - maxY/scaley * numOfPixelsY), (int) (second.x * numOfPixelsX/scalex - minX/scalex * numOfPixelsX), -(int) (second.y * numOfPixelsY/scaley - maxY/scaley * numOfPixelsY));
                Point temp = dataBuffer.get(i);
                first = second;
                second = temp;
            }
        }

        // Draw points END

        // End drawing
        canvas.bs.show();
        canvas.g.dispose();
    }
}

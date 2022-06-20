import java.awt.event.*;

public class CanvasInput {

    private MainCanvas canvas;
    private Communicator communicator;

    // Needed for dragging
    private double minXprev;
    private double maxXprev;

    private double minYprev;
    private double maxYprev;

    private int mousePreviosX;
    private int mousePreviosY;

    private boolean dragged = false;

    public CanvasInput(MainCanvas canvas, Communicator communicator){
        this.canvas = canvas;
        this.communicator = communicator;

        // Initialize
        minXprev = communicator.minX;
        maxXprev = communicator.maxX;

        minYprev = communicator.minY;
        maxYprev = communicator.maxY;

        // Create event listeners!
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                minXprev = communicator.minX;
                maxXprev = communicator.maxX;

                minYprev = communicator.minY;
                maxYprev = communicator.maxY;
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                minXprev = communicator.minX;
                maxXprev = communicator.maxX;

                minYprev = communicator.minY;
                maxYprev = communicator.maxY;

                if(dragged) {
                    communicator.fillBuffer();
                    dragged = false;
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // Logic for dragging. Done
        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                double offsetX = e.getX() - mousePreviosX;
                communicator.minX = minXprev - communicator.scalex*(offsetX / communicator.numOfPixelsX);
                communicator.maxX = maxXprev - communicator.scalex*(offsetX / communicator.numOfPixelsX);

                double offsetY = e.getY() - mousePreviosY;
                communicator.minY = minYprev + communicator.scaley*(offsetY / communicator.numOfPixelsY);
                communicator.maxY = maxYprev + communicator.scaley*(offsetY / communicator.numOfPixelsY);

                dragged = true;

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                mousePreviosX = e.getX();
                mousePreviosY = e.getY();
                maxXprev = communicator.maxX;
                minXprev = communicator.minX;
                maxYprev = communicator.maxY;
                minYprev = communicator.minY;

            }
        });


        // scrool options!
        canvas.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double scalex = Math.abs(communicator.maxX - communicator.minX);
                double scaley = Math.abs(communicator.maxY - communicator.minY);

                minXprev =  communicator.minX;
                maxXprev =  communicator.maxX;

                minYprev =  communicator.minY;
                maxYprev =  communicator.maxY;

                if(e.getWheelRotation() == 1){
                    communicator.minX -= scalex*0.02;
                    communicator.maxX += scalex*0.02;
                    communicator.minY -= scaley*0.02;
                    communicator.maxY += scaley*0.02;

                }
                else {
                    communicator.minX += scalex * 0.02;
                    communicator.maxX -= scalex * 0.02;
                    communicator.minY += scaley * 0.02;
                    communicator.maxY -= scaley * 0.02;

                }

                communicator.fillBuffer();


            }

        });

        canvas.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                communicator.canvasWidth = e.getComponent().getWidth();
                communicator.canvasHeight = e.getComponent().getHeight();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

    }

}

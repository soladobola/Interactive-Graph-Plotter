import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JPanel{

    public JTextField functionInput;

    private int uiWidth;
    private int uiHeight;


    private Communicator communicator;

    private DrawingOptionsWindow optionWindow;

    public void setCommunicator(Communicator comm){
        this.communicator = comm;
        this.optionWindow.comm = comm;
    }

    public UserInterface(int windowWidth, int windowHeight) {
        super();
        GridBagConstraints gc = new GridBagConstraints();

        this.uiWidth = (int) (windowWidth / 3.5);
        this.uiHeight = windowHeight;
        this.setPreferredSize(new Dimension(this.uiWidth, this.uiHeight));
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("Options"));
        this.setBackground(Color.lightGray);
        this.optionWindow = new DrawingOptionsWindow();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0.9;
        gc.weighty = 0.9;
        gc.insets.top = 15;
        JButton optionsBtn = new JButton("Drawing Options");
        optionsBtn.setPreferredSize(new Dimension(130, 30));
        this.add(optionsBtn, gc);


        JPanel inputFunctionPanel = new JPanel();
        inputFunctionPanel.setPreferredSize(new Dimension(200, 100));
        inputFunctionPanel.setLayout(new GridBagLayout());
        inputFunctionPanel.setBorder(BorderFactory.createTitledBorder("Input function "));
        GridBagConstraints gcf = new GridBagConstraints();

        gcf.gridx = 0;
        gcf.gridy = 0;
        gcf.weightx = 0.8;
        gcf.weighty = 0.8;
        gcf.insets.top = 10;
        gcf.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel labelForFunction = new JLabel("Function:  ");
        inputFunctionPanel.add(labelForFunction, gcf);

        gcf.insets.top = 0;
        gcf.gridx = 1;
        gcf.gridy = 0;
        gcf.anchor = GridBagConstraints.LINE_END;
        functionInput = new JTextField(10);
        functionInput.setPreferredSize(new Dimension(100, 25));
        inputFunctionPanel.add(functionInput, gcf);

        gcf.gridx = 0;
        gcf.gridy = 1;
        gcf.gridwidth = 2;
        gcf.anchor = GridBagConstraints.LAST_LINE_END;
        JButton drawBtn = new JButton("Draw");
        drawBtn.setBackground(Color.green);
        inputFunctionPanel.add(drawBtn, gcf);

        gc.gridx = 0;
        gc.gridy = 1;

        this.add(inputFunctionPanel, gc);

        // set boundaries logic
        JPanel boundariesPanel = new JPanel();
        boundariesPanel.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints gcb = new GridBagConstraints();
        boundariesPanel.setLayout(new GridBagLayout());
        boundariesPanel.setBorder(BorderFactory.createTitledBorder("Set boundaries for X "));

        gcb.gridx = 0;
        gcb.gridy = 0;
        gcb.weightx = 0.9;
        gcb.weighty = 0.9;
        gcb.insets.top = 10;
        gcb.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel labelForMin = new JLabel("X Min: ");
        boundariesPanel.add(labelForMin, gcb);

        gcb.gridx = 1;
        gcb.gridy = 0;
        gcb.anchor = GridBagConstraints.FIRST_LINE_END;
        JSpinner minSpinnerX = new JSpinner(new SpinnerNumberModel(-5, Integer.MIN_VALUE, Integer.MAX_VALUE, 0.5 ));
        minSpinnerX.setPreferredSize(new Dimension(80, 25));
        boundariesPanel.add(minSpinnerX, gcb);


        gcb.gridx = 0;
        gcb.gridy = 1;
        gcb.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel labelForMax = new JLabel("X Max: ");
        boundariesPanel.add(labelForMax, gcb);

        gcb.gridx = 1;
        gcb.gridy = 1;
        gcb.anchor = GridBagConstraints.LINE_END;
        JSpinner maxSpinnerX = new JSpinner(new SpinnerNumberModel(5, Integer.MIN_VALUE, Integer.MAX_VALUE, 0.5 ));
        maxSpinnerX.setPreferredSize(new Dimension(80, 25));
        boundariesPanel.add(maxSpinnerX, gcb);

        gc.gridx = 0;
        gc.gridy = 2;

        this.add(boundariesPanel, gc);

        // set boundaries for Y logic here
        JPanel boundariesPanel2 = new JPanel();
        boundariesPanel2.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints gcb2 = new GridBagConstraints();
        boundariesPanel2.setLayout(new GridBagLayout());
        boundariesPanel2.setBorder(BorderFactory.createTitledBorder("Set boundaries for Y "));

        gcb2.gridx = 0;
        gcb2.gridy = 0;
        gcb2.weightx = 0.9;
        gcb2.weighty = 0.9;
        gcb2.insets.top = 10;
        gcb2.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel labelForMin2 = new JLabel("Y Min: ");
        boundariesPanel2.add(labelForMin2, gcb2);

        gcb2.gridx = 1;
        gcb2.gridy = 0;
        gcb2.anchor = GridBagConstraints.FIRST_LINE_END;
        JSpinner minSpinnerY = new JSpinner(new SpinnerNumberModel(-5, Integer.MIN_VALUE, Integer.MAX_VALUE, 0.5 ));
        minSpinnerY.setPreferredSize(new Dimension(80, 25));
        boundariesPanel2.add(minSpinnerY, gcb2);

        gcb2.gridx = 0;
        gcb2.gridy = 1;
        gcb2.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel labelForMax2 = new JLabel("Y Max: ");
        boundariesPanel2.add(labelForMax2, gcb2);

        gcb2.gridx = 1;
        gcb2.gridy = 1;
        gcb2.anchor = GridBagConstraints.LINE_END;
        JSpinner maxSpinnerY = new JSpinner(new SpinnerNumberModel(5, Integer.MIN_VALUE, Integer.MAX_VALUE, 0.5 ));
        maxSpinnerY.setPreferredSize(new Dimension(80, 25));
        boundariesPanel2.add(maxSpinnerY, gcb2);

        gc.gridx = 0;
        gc.gridy = 3;

        this.add(boundariesPanel2, gc);






        drawBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               communicator.funkcija = new Function(functionInput.getText());
               communicator.fillBuffer();
           }
       });



        minSpinnerX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double value = (double)minSpinnerX.getValue();
                if(communicator.maxX > value) {
                    communicator.minX = value;
                } else {
                    JOptionPane.showMessageDialog(null, "Value must be less then maximum X");
                    communicator.minX = communicator.maxX - 1;
                    minSpinnerX.setValue(communicator.minX);
                }
                communicator.maxX = (double)maxSpinnerX.getValue();
                communicator.minY = (double)minSpinnerY.getValue();
                communicator.maxY = (double)maxSpinnerY.getValue();
            }
        });




        maxSpinnerX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double value = (double)maxSpinnerX.getValue();
                if(communicator.minX < value){
                    communicator.maxX = value;
                } else {
                    JOptionPane.showMessageDialog(null, "Value must be greater then minimal X");
                    communicator.maxX = communicator.minX + 1;
                    maxSpinnerX.setValue(communicator.maxX);
                }
                communicator.minX = (double)minSpinnerX.getValue();
                communicator.minY = (double)minSpinnerY.getValue();
                communicator.maxY = (double)maxSpinnerY.getValue();

            }
        });



        minSpinnerY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                double value = (double)minSpinnerY.getValue();
                if(communicator.maxY > value) {
                    communicator.minY = value;
                } else {
                    JOptionPane.showMessageDialog(null, "Value must be less then maximum Y");
                    communicator.minY = communicator.maxY - 1;
                    minSpinnerY.setValue(communicator.minY);
                }
                communicator.minX = (double)minSpinnerX.getValue();
                communicator.maxX = (double)maxSpinnerX.getValue();
                communicator.maxY = (double)maxSpinnerY.getValue();
            }
        });

        maxSpinnerY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                double value = (double)maxSpinnerY.getValue();
                if(communicator.minY < value){
                    communicator.maxY = value;
                } else {
                    JOptionPane.showMessageDialog(null, "Value must be greater then minimal Y");
                    communicator.maxY = communicator.minY + 1;
                    maxSpinnerY.setValue(communicator.maxY);
                }
                communicator.minX = (double)minSpinnerX.getValue();
                communicator.maxX = (double)maxSpinnerX.getValue();
                communicator.minY = (double)minSpinnerY.getValue();
            }
        });

        optionsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionWindow.setVisible(true);
            }
        });

        functionInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functionInput.setBackground(Color.WHITE);
            }
        });

        functionInput.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               functionInput.setBackground(Color.WHITE);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        functionInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    communicator.funkcija = new Function(functionInput.getText());
                    communicator.fillBuffer();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

}

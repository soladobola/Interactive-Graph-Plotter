import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;


public class DrawingOptionsWindow extends JFrame {

    public Communicator comm;


    private void hideWindow(){
        this.setVisible(false);
    }

    public DrawingOptionsWindow()
    {
        super("Drawing Options");
        GridBagConstraints gc = new GridBagConstraints();
        this.setSize(new Dimension(400, 280));
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        JPanel chooseColorPanel = new JPanel();
        chooseColorPanel.setPreferredSize(new Dimension(150, 150));
        chooseColorPanel.setBorder(BorderFactory.createTitledBorder("Choose colors "));

        GridBagConstraints gcp = new GridBagConstraints();
        chooseColorPanel.setLayout(new GridBagLayout());
        JButton canvasBackgroundBtn = new JButton("Background");
        JButton canvasAxisBtn = new JButton("Axis");
        JButton canvasFunctionBtn = new JButton("Function");
        gcp.gridx = 0;
        gcp.gridy = 0;
        canvasBackgroundBtn.setPreferredSize(new Dimension(120, 25));
        chooseColorPanel.add(canvasBackgroundBtn, gcp);

        gcp.gridx = 0;
        gcp.gridy = 1;
        canvasAxisBtn.setPreferredSize(new Dimension(120, 25));
        chooseColorPanel.add(canvasAxisBtn, gcp);

        gcp.gridx = 0;
        gcp.gridy = 2;
        canvasFunctionBtn.setPreferredSize(new Dimension(120, 25));
        chooseColorPanel.add(canvasFunctionBtn, gcp);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets.right = 30;
       // gc.weighty = 0.9;
       // gc.weightx = 0.9;
        this.add(chooseColorPanel, gc);
        gc.insets.right = 0;

        // Creating panel for step drawing!
        JPanel stepChooser = new JPanel();
        stepChooser.setBorder(BorderFactory.createTitledBorder("Choose step "));
        stepChooser.setLayout(new GridBagLayout());
        stepChooser.setPreferredSize(new Dimension(130, 80));
        GridBagConstraints gcp2 = new GridBagConstraints();
        JLabel stepLabel = new JLabel("Step:  ");
        JSpinner stepSpinner = new JSpinner(new SpinnerNumberModel(0.05, 0.01, 1, 0.01));
        stepSpinner.setPreferredSize(new Dimension(60, 25));
        JButton spinerBtn = new JButton("Ok");
        spinerBtn.setPreferredSize(new Dimension(60, 20));
        gcp2.gridx = 0;
        gcp2.gridy = 0;
        stepChooser.add(stepLabel, gcp2);
        gcp2.gridx = 1;
        gcp2.gridy = 0;
        stepChooser.add(stepSpinner, gcp2);
        gcp2.gridx = 1;
        gcp2.gridy = 1;
        gcp2.insets.top = 10;
        stepChooser.add(spinerBtn, gcp2);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(stepChooser, gc);

        //toggle axis numbers
        JCheckBox axisNumberX = new JCheckBox("Show x-axis");
        axisNumberX.setSelected(true);
        gc.gridx = 1;
        gc.gridy = 0;

        gc.insets.top = 50;
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(axisNumberX, gc);

        JCheckBox axisNumberY = new JCheckBox("Show y-axis");
        axisNumberY.setSelected(true);
        gc.gridx = 1;
        gc.gridy = 0;

        gc.insets.top = 100;
        gc.anchor = GridBagConstraints.LINE_START;
        this.add(axisNumberY, gc);


        JButton closeBtn = new JButton("Close");
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.SOUTHEAST;
        gc.insets.top = 20;
        this.add(closeBtn, gc);

        this.comm = comm;


        canvasBackgroundBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog((Component)e.getSource(), "Background color", comm.canvas.getBackgroundColor());
                if(color != null)
                    comm.canvas.setBackgroundColor(color);
            }
        });

        canvasAxisBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog((Component)e.getSource(), "Axis color", comm.canvas.getAxisColor());
                if(color != null)
                    comm.canvas.setAxisColor(color);
            }
        });

        canvasFunctionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog((Component)e.getSource(), "Function color", comm.canvas.getDrawColor());
                if(color != null)
                    comm.canvas.setDrawColor(color);
            }
        });


        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideWindow();
            }
        });

        axisNumberX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                comm.showXaxis = axisNumberX.isSelected();
            }
        });

        axisNumberY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                comm.showYaxis = axisNumberY.isSelected();
            }
        });

        spinerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comm.step = (double)stepSpinner.getValue();
                comm.fillBuffer();
            }
        });



    }
}

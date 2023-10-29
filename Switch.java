import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Switch extends JFrame {
    private JButton mainbtn;
    private JButton settingbtn;
    private JPanel btnPanel;
    private JPanel timePanel;
    private JSpinner spihour;
    private JSpinner spimin;
    private Controller controller;
    private Settings settings;

    Switch() {
        setSize(350, 250);
        setTitle("Switch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(360,100);
        setLayout(new BorderLayout());

        ////----- Creating the button panel -----////
        btnPanel = new JPanel(new GridLayout(2, 1, 0, 20));

        ////----- Creating the main button -----////
        mainbtn = new JButton("ON");
        mainbtn.setFont(new Font("", 1, 15));
        controller = Controller.getController();
        mainbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (mainbtn.getText().equals("ON")) {
                    handleSpinnerChange();
                    mainbtn.setText("OFF");
                } else {
                    controller.getitem(0).on(mainbtn.getText());
                    controller.getitem(1).on(mainbtn.getText());
                    controller.getitem(2).on(mainbtn.getText());
                    controller.getitem(3).on(mainbtn.getText());
                    mainbtn.setText("ON");
                }
            }
        });

        ////----- Creating the settings button -----////
        settingbtn = new JButton("Settings");
        settingbtn.setFont(new Font("", 1, 15));
        settingbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (settings == null) {
                    settings = new Settings();
                }
                settings.setVisible(true);
            }
        });

        ////----- Creating the time panel -----////
        timePanel = new JPanel(new FlowLayout());
        timePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        ////----- Creating the hour spinner -----////
        JLabel hour = new JLabel("Hour:");
        hour.setFont(new Font("", Font.BOLD, 15));
        SpinnerNumberModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
        spihour = new JSpinner(hourModel);
        JSpinner.NumberEditor hourEditor = new JSpinner.NumberEditor(spihour, "00");
        hourEditor.getTextField().setFont(new Font("", 1, 15));
        hourEditor.getTextField().setEnabled(true);
        spihour.setEditor(hourEditor);
        spihour.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                handleSpinnerChange();
            }
        });

        ////----- Creating the minute spinner -----////
        JLabel min = new JLabel("  Minutes:");
        min.setFont(new Font("", Font.BOLD, 15));
        SpinnerNumberModel minModel = new SpinnerNumberModel(0, 0, 59, 1);
        spimin = new JSpinner(minModel);
        JSpinner.NumberEditor minEditor = new JSpinner.NumberEditor(spimin, "00");
        minEditor.getTextField().setFont(new Font("", 1, 15));
        minEditor.getTextField().setEnabled(false);
        spimin.setEditor(minEditor);
        spimin.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                handleSpinnerChange();
            }
        });

        ////----- Adding components to the time panel -----////
        timePanel.add(hour);
        timePanel.add(spihour);
        timePanel.add(min);
        timePanel.add(spimin);

        ////----- Adding buttons to the button panel -----////
        btnPanel.add(mainbtn);
        btnPanel.add(settingbtn);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        ////----- Adding panels to the main frame -----////
        add(btnPanel);
        add("South", timePanel);
    }

    public void handleSpinnerChange() {
        ////----- Retrieve the selected hour and minute values from the spinners -----////
        int Hour = (int) spihour.getValue();
        int Min = (int) spimin.getValue();

        ////----- Format the time string as HH:MM -----////
        String time = String.format("%02d:%02d", Hour, Min);

        ////----- Update the settings for all items controlled by the controller -----////
        for(int i = 0; i < 4; i++) {
            controller.getitem(i).Auto(time); 
        }
    }
}

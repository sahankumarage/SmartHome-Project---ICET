import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class ItemController extends JFrame {
    private JPanel timePanel;
    private JLabel startHourLabel;
    private JSpinner startHourSpinner;
    private JLabel startMinLabel;
    private JSpinner startMinSpinner;
    private JLabel endHourLabel;
    private JSpinner endHourSpinner;
    private JLabel endMinLabel;
    private JSpinner endMinSpinner;
    private JButton btnSet;
    private String time;
    private int index;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private JToggleButton[] btnArray = new JToggleButton[0];
    private ButtonGroup btngroup = new ButtonGroup();
    private String start;
    private String end;

    public ItemController(String name) {
        setSize(800, 240);
        setTitle(name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(720,100);
        setLayout(new BorderLayout());

        ////----- Creating the time panel -----////
        timePanel = new JPanel(new FlowLayout());
        timePanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        ////----- Creating the "Start Hour" label and spinner -----////
        startHourLabel = new JLabel("Start->  Hour:");
        startHourLabel.setFont(new Font("", Font.BOLD, 15));

        SpinnerNumberModel startHourModel = new SpinnerNumberModel(0, 0, 23, 1);
        startHourSpinner = new JSpinner(startHourModel);
        JSpinner.NumberEditor startHourEditor = new JSpinner.NumberEditor(startHourSpinner, "00");
        startHourEditor.getTextField().setFont(new Font("", Font.BOLD, 15));
        startHourEditor.getTextField().setEnabled(false);
        startHourSpinner.setEditor(startHourEditor);

        ////----- Creating the "Start Minute" label and spinner -----////
        startMinLabel = new JLabel("  Minutes:");
        startMinLabel.setFont(new Font("", Font.BOLD, 15));

        SpinnerNumberModel startMinModel = new SpinnerNumberModel(0, 0, 59, 1);
        startMinSpinner = new JSpinner(startMinModel);
        JSpinner.NumberEditor startMinEditor = new JSpinner.NumberEditor(startMinSpinner, "00");
        startMinEditor.getTextField().setFont(new Font("", Font.BOLD, 15));
        startMinEditor.getTextField().setEnabled(false);
        startMinSpinner.setEditor(startMinEditor);

        ////----- Creating the "End Hour" label and spinner -----////
        endHourLabel = new JLabel("   End->  Hour:");
        endHourLabel.setFont(new Font("", Font.BOLD, 15));

        SpinnerNumberModel endHourModel = new SpinnerNumberModel(0, 0, 23, 1);
        endHourSpinner = new JSpinner(endHourModel);
        JSpinner.NumberEditor endHourEditor = new JSpinner.NumberEditor(endHourSpinner, "00");
        endHourEditor.getTextField().setFont(new Font("", Font.BOLD, 15));
        endHourEditor.getTextField().setEnabled(false);
        endHourSpinner.setEditor(endHourEditor);

        ////----- Creating the "End Minute" label and spinner -----////
        endMinLabel = new JLabel("  Minutes:");
        endMinLabel.setFont(new Font("", Font.BOLD, 15));

        SpinnerNumberModel endMinModel = new SpinnerNumberModel(0, 0, 59, 1);
        endMinSpinner = new JSpinner(endMinModel);
        JSpinner.NumberEditor endMinEditor = new JSpinner.NumberEditor(endMinSpinner, "00");
        endMinEditor.getTextField().setFont(new Font("", Font.BOLD, 15));
        endMinEditor.getTextField().setEnabled(false);
        endMinSpinner.setEditor(endMinEditor);

        ////----- Determining the index based on the item name -----////
        if (name.equals("TV Living Room")){
            index = 1;
        } else if(name.equals("Speaker Living Room")){
            index = 0;
        } else if (name.equals("Window Living Room")){
            index = 2;
        } else {
            index = 3;
        }

        ////----- Creating the button panel for toggling items -----////
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(800, 160));

        ////----- Creating a scroll pane for the button panel -----////
        scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        ////----- Creating the "Set" button -----////
        btnSet = new JButton("Set");
        btnSet.setFont(new Font("", 1, 15));
        btnSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (btnSet.getText().equals("Set")) {
                    ////----- Getting the selected start and end times from the spinners -----////
                    int startHour = (int) startHourSpinner.getValue();
                    int startMin = (int) startMinSpinner.getValue();
                    int endHour = (int) endHourSpinner.getValue();
                    int endMin = (int) endMinSpinner.getValue();
                    time = String.format("Start Time: %02d:%02d      End Time: %02d:%02d%n", startHour, startMin,endHour, endMin);
                    start = String.format("%02d:%02d", startHour, startMin);
                    end = String.format("%02d:%02d", endHour, endMin);

                    ////----- Creating a new toggle button for the time range and adding it to the button panel -----////
                    JToggleButton btn = new JToggleButton(time);
                    btngroup.add(btn);
                    btn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            ////----- Handling the "Edit" button click to update the spinners with the selected time range -----////
                            btnSet.setText("Edit");
                            for (int i = 0; i < btnArray.length; i++) {
                                if (btnArray[i].isSelected()) {
                                    ////----- Retrieving the start and end times for the selected time range -----////
                                    String[] startnewtime = Controller.getController().getitem(index).getTime("start",i).split(":");
                                    System.out.println(Arrays.toString(startnewtime));
                                    String[] endnewtime = Controller.getController().getitem(index).getTime("end",i).split(":");
                                    System.out.println(Arrays.toString(endnewtime));
                                    ////----- Updating the spinners with the selected start and end times -----////
                                    startHourSpinner.setValue(Integer.parseInt(startnewtime[0]));
                                    startMinSpinner.setValue(Integer.parseInt(startnewtime[1]));
                                    endHourSpinner.setValue(Integer.parseInt(endnewtime[0]));
                                    endMinSpinner.setValue( Integer.parseInt(endnewtime[1]));
                                }
                            }
                        }
                    });
                    btn.setFont(new Font("", 1, 15));
                    btn.setBackground(new Color(255, 255, 255));
                    btn.setBorder(null);
                    btnArray = extendToggleButtonArray(btnArray);
                    btnArray[btnArray.length - 1] = btn;
                    buttonPanel.add(btnArray[btnArray.length - 1]);

                    ////----- Updating the item's start and end times in the controller -----////
                    Controller.getController().getitem(index).setTime(start, end);

                    ////----- Updating the view -----////
                    revalidate();
                    repaint();
                } else if (btnSet.getText().equals("Edit")) {
                    ////----- Handling the "Edit" button click to update the time range in the controller -----////
                    for (int i = 0; i < btnArray.length; i++) {
                        if (btnArray[i].isSelected()) {
                            ////----- Getting the selected start and end times from the spinners -----////
                            int startHour = (int) startHourSpinner.getValue();
                            int startMin = (int) startMinSpinner.getValue();
                            int endHour = (int) endHourSpinner.getValue();
                            int endMin = (int) endMinSpinner.getValue();
                            time = String.format("Start Time: %02d:%02d      End Time: %02d:%02d%n", startHour,startMin, endHour, endMin);
                            start = String.format("%02d:%02d", startHour, startMin);
                            end = String.format("%02d:%02d", endHour, endMin);

                            ////----- Updating the start and end times for the selected time range in the controller -----////
                            Controller.getController().getitem(index).getArray("start")[i] = start;
                            Controller.getController().getitem(index).getArray("end")[i] = end;

                            ////----- Updating the button text with the updated time range -----////
                            btnArray[i].setText(time);
                            btnSet.setText("Set");

                            ////----- Updating the view -----////
                            revalidate();
                            repaint();
                            break;
                        }
                    }
                }
            }
        });

        ////----- Adding components to the time panel -----////
        timePanel.add(startHourLabel);
        timePanel.add(startHourSpinner);
        timePanel.add(startMinLabel);
        timePanel.add(startMinSpinner);
        timePanel.add(endHourLabel);
        timePanel.add(endHourSpinner);
        timePanel.add(endMinLabel);
        timePanel.add(endMinSpinner);
        timePanel.add(btnSet);

        ////----- Adding the scroll pane and time panel to the frame -----////
        add(scrollPane, BorderLayout.NORTH);
        add(timePanel, BorderLayout.SOUTH);
    }

    public void deselectButtons() {
        ////----- Clearing the selection of toggle buttons in the button group -----////
        btngroup.clearSelection();
    }

    private JToggleButton[] extendToggleButtonArray(JToggleButton[] array) {
        ////----- Extending the toggle button array by creating a new array with increased length and copying the existing elements -----////
        JToggleButton[] newArray = new JToggleButton[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }
}

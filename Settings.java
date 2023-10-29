import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Settings extends JFrame {
    private JButton btntvLivingRoom;
    private JButton btnspeakerLivingRoom;
    private JButton btnwindowLivingRoom;
    private JButton btntvDinningRoom;

    Settings() {
        setSize(380, 240);
        setTitle("Controller");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(360,360);
        setLayout(new BorderLayout());

        ////----- Creating the button panel -----////
        JPanel btnPanel = new JPanel(new GridLayout(4, 1));

        ////----- Creating the "TV Living Room" button -----////
        btntvLivingRoom = new JButton("TV Living Room");
        btntvLivingRoom.setFont(new Font("", 1, 15));
        btntvLivingRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getController().getItemController(0).setVisible(true);
            }
        });

        ////----- Creating the "Speaker Living Room" button -----////
        btnspeakerLivingRoom = new JButton("Speaker Living Room");
        btnspeakerLivingRoom.setFont(new Font("", 1, 15));
        btnspeakerLivingRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getController().getItemController(1).setVisible(true);
            }
        });

        ////----- Creating the "Window Living Room" button -----////
        btnwindowLivingRoom = new JButton("Window Living Room");
        btnwindowLivingRoom.setFont(new Font("", 1, 15));
        btnwindowLivingRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getController().getItemController(2).setVisible(true);
            }
        });

        ////----- Creating the "TV Dinning Room" button -----////
        btntvDinningRoom = new JButton("TV Dinning Room");
        btntvDinningRoom.setFont(new Font("", 1, 15));
        btntvDinningRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Controller.getController().getItemController(3).setVisible(true);
            }
        });

        ////----- Adding buttons to the button panel -----////
        btnPanel.add(btntvLivingRoom);
        btnPanel.add(btnspeakerLivingRoom);
        btnPanel.add(btnwindowLivingRoom);
        btnPanel.add(btntvDinningRoom);

        ////----- Adding the button panel to the frame -----////
        add(btnPanel);
    }
}

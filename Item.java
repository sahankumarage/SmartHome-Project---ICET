import javax.swing.*;
import java.awt.*;

public class Item extends JFrame implements Interface {
    private JLabel Label;
    private String[] starttime=new String[0];
    private String[] endtime=new String[0];

    public Item(String name,int x,int y) {
        setVisible(true);
        setSize(300, 150);
        setTitle(name);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(x,y);
        setLayout(new BorderLayout());

        Label = new JLabel("OFF");
        add(BorderLayout.NORTH, Label);
    }

    public void on(String word) {
        this.Label.setText(word);
    }

    public void setTime(String x, String y) {
        starttime=addTime(starttime, x);
        endtime=addTime(endtime, y);
    }

    public void Auto(String time) {
        String[] live = time.split(":");
            
        int liveH = Integer.parseInt(live[0]);
        int liveM = Integer.parseInt(live[1]);

        for(int i = 0; i <endtime.length; i++) {
            int startH = Integer.parseInt(starttime[i].split(":")[0]);
            int startM = Integer.parseInt(starttime[i].split(":")[1]);
            int endH = Integer.parseInt(endtime[i].split(":")[0]);
            int endM = Integer.parseInt(endtime[i].split(":")[1]);
        
            if (liveH > startH || (liveH == startH && liveM > startM)) {
                Label.setText("ON");
            }
            if (liveH > endH || (liveH == endH && liveM > endM)){
                Label.setText("OFF");
            }
        }
    }

    public String[] addTime(String[] ar, String time){
        String[] temp=new String[ar.length+1];
        for(int i=0; i<ar.length; i++){
            temp[i]=ar[i];
        }
        temp[ar.length]=time;
        return temp;
    }


    public String getTime(String word,int i){
        if (word.equals("start")){
            return starttime[i];
        }else{
            return endtime[i];
        }
    }
    public String[] getArray(String word){
        if (word.equals("start")){
            return starttime;
        }else{
            return endtime;
        }
    }
    
}

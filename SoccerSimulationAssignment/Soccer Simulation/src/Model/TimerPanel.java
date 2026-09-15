package Model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TimerPanel extends JPanel {
    private MatchTimer matchTimer;
    private JLabel timerLabel;
    private Timer timer;

    public TimerPanel(){
        matchTimer = new MatchTimer(90);
        timerLabel = new JLabel(matchTimer.getTime(), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.WHITE);
        add(timerLabel);
        timer = new Timer(1000, e -> { matchTimer.tick();
        timerLabel.setText(matchTimer.getTime());
        if(matchTimer.getSeconds() == 0){ 
            timer.stop();
        }
    });
    timer.start();
        }
    }

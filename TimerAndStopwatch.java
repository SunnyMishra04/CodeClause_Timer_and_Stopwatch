import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TimerAndStopwatch {

    private JFrame frame;
    private JTextField timeField;
    private JLabel stopwatchLabel;
    private JButton startButton, resetButton;
    private JButton startStopwatchButton, stopStopwatchButton, resetStopwatchButton;
    private Timer timer;
    private Timer stopwatchTimer;
    private int stopwatchTime;
    private boolean stopwatchRunning;

    public TimerAndStopwatch() {
        frame = new JFrame("Timer and Stopwatch");
        frame.setLayout(null);

        JLabel timeLabel = new JLabel("Time (in seconds):");
        timeLabel.setBounds(10, 10, 120, 20);
        frame.add(timeLabel);

        timeField = new JTextField();
        timeField.setBounds(140, 10, 50, 20);
        frame.add(timeField);

        JLabel stopwatchTextLabel = new JLabel("Stopwatch Time:");
        stopwatchTextLabel.setBounds(10, 100, 120, 20);
        frame.add(stopwatchTextLabel);

        stopwatchLabel = new JLabel("00:00:00");
        stopwatchLabel.setBounds(140, 100, 100, 20);
        frame.add(stopwatchLabel);

        startButton = new JButton("START TIMER");
        startButton.setBounds(10, 40, 120, 20);
        frame.add(startButton);

        resetButton = new JButton("RESET TIMER");
        resetButton.setBounds(140, 40,  120, 20);
        frame.add(resetButton);

        startStopwatchButton = new JButton("START");
        startStopwatchButton.setBounds(10, 70, 120, 20);
        frame.add(startStopwatchButton);

        stopStopwatchButton = new JButton("STOP");
        stopStopwatchButton.setBounds(140, 70, 100, 20);
        frame.add(stopStopwatchButton);

        resetStopwatchButton = new JButton("RESET");
        resetStopwatchButton.setBounds(250, 70, 120, 20);
        frame.add(resetStopwatchButton);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(timeField.getText());
                time--;
                timeField.setText(String.valueOf(time));

                if (time == 0) {
                    timer.stop();
                }
            }
        });

        stopwatchTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stopwatchRunning) {
                    stopwatchTime++;
                    int hours = stopwatchTime / 3600;
                    int minutes = (stopwatchTime % 3600) / 60;
                    int seconds = stopwatchTime % 60;
                    String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                    stopwatchLabel.setText(timeString);
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timeField.setText("0");
            }
        });

        startStopwatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatchRunning = true;
                stopwatchTimer.start();
            }
        });

        stopStopwatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatchRunning = false;
            }
        });

        resetStopwatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatchRunning = false;
                stopwatchTime = 0;
                stopwatchLabel.setText("00:00:00");
            }
        });

        frame.setSize(400, 150);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TimerAndStopwatch();
    }
}

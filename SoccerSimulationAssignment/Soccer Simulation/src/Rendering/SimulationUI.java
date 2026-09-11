/*
WILLIAM IS DOING THIS PART
The gui will be the what displays eveything it ties it all together, 
one feature ive been told to add is while simulation is running a mini print log 

NEEDS:
- main menu
    - background splash graphic 
    - start button 
        - display an overview graphic of pitch with players on it with the current formation (default formation if no changes made)
        - formation change button 
            - list of players for one team
            - displays a graphic of a pitch with symbols of what each player postion is
            - swap team formation button (arrows on each side that swaps between premade formations)
            - swap side button 
                - changes what side the user will play as, also saves the formation of the current team so that users can change what formations each team will use 
            - save button locks in the current settings (also sends the user back, no default back button so the user always saves current formations)
       
        - match settings button 
            - player emotion selection button 
                - three states default, off and extreme
            - simulation type 
                - time limit
                    - match time input selection  
                - goal limit
                    - goal limit input box (sets a max number of goals to be scored for simulation to end)
            - save button locks in the current settings (also sends the user back, no default back button so the user always saves current formations)

        - start match button 
            - begins the simulation by displaying the pitch, players and match stuff
            - menu button in the top corner 
                - pauses the simulation when clicked on
                - resume button
                - exit button 
        - return to main menu button 
    - settings button (doesnt have to be implemented settings)
        - debug mode button
                - makes it run in debug mode
        - save button
    - exit button 

 */

package Rendering;

import java.awt.*;
import javax.swing.*;

import Model.SoccerPitch;

class UIWindow{
    JFrame frame;
    Menus currentMenu;
    CircleList<String> formationCircle;
    CircleList<String> sidesCircle;
    SoccerPitch pitch;
    boolean debugMode = false;
    String backgroundColour = "#329632";
    String btnColour = "#404143";

    public UIWindow() {
        frame = new JFrame("Soccer Simulation");
        frame.setSize(ScreenSize.width, ScreenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        formationCircle = new CircleList<>(new String[] { "4-4-2", "4-3-3", "3-5-2" });
        sidesCircle = new CircleList<>(new String[] { "Home", "Away" });
        pitch = new SoccerPitch();

    }

    void setMenu(Menus menu) {
        currentMenu = menu;
        frame.setContentPane(menu);
        frame.revalidate();
        frame.repaint();
        frame.getContentPane().setBackground(Color.decode(backgroundColour));
    }

    void start() {
        setMenu(new MainMenu(this));
        frame.setVisible(true);
    }

    boolean getDebug() {
        return this.debugMode;
    }

    void setDebug(boolean b) {
        this.debugMode = b;
        frame.setTitle("Soccer Simulation" + debugString());
    }

    private String debugString() {
        if (this.getDebug() == true)
            return " (Debug)";

        return "";
    }

    void displayButtons(JButton[] buttons) {
        for (JButton btn : buttons) {
            btn.setBackground(Color.decode(btnColour));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Lexend", Font.BOLD, 16));
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    void displayCheckBox(JCheckBox[] boxes) {
        for (JCheckBox cbx : boxes) {
            cbx.setBackground(Color.decode(btnColour));
            cbx.setForeground(Color.WHITE);
            cbx.setFont(new Font("Lexend", Font.BOLD, 16));
            cbx.setBorderPainted(false);
            cbx.setFocusPainted(false);
            cbx.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    void displayLabel(JLabel[] labels) {
        for (JLabel lbl : labels) {
            lbl.setBackground(Color.decode(btnColour));
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Lexend", Font.BOLD, 16));
            lbl.setOpaque(true);
            lbl.setHorizontalAlignment(JLabel.CENTER);
            lbl.setVerticalAlignment(JLabel.CENTER);
        }
    }

    // just copied this from SoccerPitch class and fixed the magic numbers for a
    // background graphic
    void backgroundGraphic(Graphics g) {

        // Pitch
        g.setColor(new Color(50, 150, 50));
        g.fillRect(0, 0, ScreenSize.width, ScreenSize.height);

        g.setColor(Color.WHITE);

        // Outer border
        g.drawRect(0, 0, ScreenSize.width, ScreenSize.height);

        // Halfway line
        g.drawLine(ScreenSize.width / 2, 0, ScreenSize.width / 2, ScreenSize.height);

        // Centre circle
        int circleRadius = 60;
        g.drawOval(ScreenSize.width / 2 - circleRadius, 
                ScreenSize.height / 2 - circleRadius, circleRadius * 2, circleRadius * 2);

        // Centre spot
        g.fillOval(ScreenSize.width / 2 - 4, ScreenSize.height / 2 - 4, 8, 8);

        // Penalty areas
        int penaltyWidth = 100, penaltyHeight = 200;
        int penaltyY = (ScreenSize.height - penaltyHeight) / 2;
        g.drawRect(0, penaltyY, penaltyWidth, penaltyHeight);
        g.drawRect(ScreenSize.width - penaltyWidth, penaltyY, penaltyWidth, penaltyHeight);

        // Goals
        int goalWidth = 50, goalHeight = 100;
        int goalY = (ScreenSize.height - goalHeight) / 2;
        g.drawRect(0, goalY, goalWidth, goalHeight);
        g.drawRect(ScreenSize.width - goalWidth, goalY, goalWidth, goalHeight);

        // Penalty spots
        int spotOffset = 79;
        g.fillOval(spotOffset - 4, ScreenSize.height / 2 - 4, 8, 8);
        g.fillOval(ScreenSize.width - spotOffset - 4, ScreenSize.height / 2 - 4, 8, 8);
    }
}

abstract class Menus extends JPanel {
    protected UIWindow window;

    Menus(UIWindow window) {
        this.window = window;
    }

    abstract void next1();

    abstract void next2();

    abstract void next3();

    abstract void back();
}

class MainMenu extends Menus {
    MainMenu(UIWindow window) {
        super(window);
        setLayout(null);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> next1());

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> next2());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> back());

        JButton[] buttons = { startButton, settingsButton, exitButton };

        window.displayButtons(buttons);

        startButton.setBounds(ScreenSize.width / 2 - 125, ScreenSize.height / 2 - 100, 250, 50);
        settingsButton.setBounds(ScreenSize.width / 2 - 125, ScreenSize.height / 2 - 30, 250, 50);
        exitButton.setBounds(ScreenSize.width / 2 - 125, ScreenSize.height / 2 + 40, 250, 50);

        add(startButton);
        add(settingsButton);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        window.backgroundGraphic(g);
    }

    @Override
    void next1() {
        window.setMenu(new StartMenu(window));
    }

    @Override
    void next2() {
        window.setMenu(new SettingsMenu(window));
    }

    @Override
    void next3() {
    }

    @Override
    void back() {
        System.exit(0);
    }

}

/*
 * need to add the pitch with current settings loaded/saved next to buttons
 */
class StartMenu extends Menus {
    StartMenu(UIWindow window) {
        super(window);
        setLayout(null);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> next1());

        JButton formationButton = new JButton("Formation Settings");
        formationButton.addActionListener(e -> next2());

        JButton matchSettingsButton = new JButton("Match Settings");
        matchSettingsButton.addActionListener(e -> next3());

        JButton exitButton = new JButton("Back");
        exitButton.addActionListener(e -> back());

        JButton[] buttons = { startButton, formationButton, matchSettingsButton, exitButton };

        window.displayButtons(buttons);

        startButton.setBounds(ScreenSize.width / 4 - 125, ScreenSize.height / 2 - 140, 250, 50);
        formationButton.setBounds(ScreenSize.width / 4 - 125, ScreenSize.height / 2 - 70, 250, 50);
        matchSettingsButton.setBounds(ScreenSize.width / 4 - 125, ScreenSize.height / 2, 250, 50);
        exitButton.setBounds(ScreenSize.width / 4 - 125, ScreenSize.height / 2 + 70, 250, 50);

        add(startButton);
        add(formationButton);
        add(matchSettingsButton);
        add(exitButton);
    }

    @Override
    void next1() {
        window.setMenu(new SimWindowMenu(window));
    }

    @Override
    void next2() {
        window.setMenu(new FormationMenu(window));
    }

    @Override
    void next3() {
        window.setMenu(new MatchSettingsMenu(window));
    }

    @Override
    void back() {
        window.setMenu(new MainMenu(window));
    }

}

class SettingsMenu extends Menus {
    SettingsMenu(UIWindow window) {
        super(window);
        setLayout(null);

        JCheckBox debugButton = new JCheckBox("Debug Mode", window.getDebug());
        debugButton.addActionListener(e -> window.setDebug(debugButton.isSelected()));

        JButton exitButton = new JButton("Save");
        exitButton.addActionListener(e -> back());

        JButton[] buttons = { exitButton };
        JCheckBox[] boxes = { debugButton };
        window.displayCheckBox(boxes);
        window.displayButtons(buttons);

        debugButton.setBounds(ScreenSize.width / 2 - 125, ScreenSize.height / 2 - 100, 250, 50);
        exitButton.setBounds(ScreenSize.width / 2 - 125, ScreenSize.height / 2 - 30, 250, 50);

        add(debugButton);
        add(exitButton);
    }

    @Override
    void next1() {
    }

    @Override
    void next2() {

    }

    @Override
    void next3() {

    }

    @Override
    void back() {
        window.setMenu(new MainMenu(window));
    }
}

/*
 * need to add the pitch next to the buttons displaying the current formation
 * for each side
 * 
 */
class FormationMenu extends Menus {
    CircleList<String> formationCircle;
    CircleList<String> sidesCircle;

    FormationMenu(UIWindow window) {
        super(window);
        setLayout(null);

        this.formationCircle = window.formationCircle;
        this.sidesCircle = window.sidesCircle;
        JButton formPrevBtn = new JButton("<");
        JButton formNextBtn = new JButton(">");

        JLabel formationLabel = new JLabel(formationCircle.getCurrent().toString());

        formPrevBtn.addActionListener(e -> {
            formationCircle.previous();
            formationLabel.setText(formationCircle.getCurrent().toString());
        });

        formNextBtn.addActionListener(e -> {
            formationCircle.next();
            formationLabel.setText(formationCircle.getCurrent().toString());
        });

        JButton swapPrevBtn = new JButton("<");
        JButton swapNextBtn = new JButton(">");

        JLabel swapLabel = new JLabel(sidesCircle.getCurrent().toString());

        swapPrevBtn.addActionListener(e -> {
            sidesCircle.previous();
            swapLabel.setText(sidesCircle.getCurrent().toString());
        });

        swapNextBtn.addActionListener(e -> {
            sidesCircle.next();
            swapLabel.setText(sidesCircle.getCurrent().toString());
        });

        JButton exitButton = new JButton("Save");
        exitButton.addActionListener(e -> back());

        JButton[] buttons = { formPrevBtn, formNextBtn, swapPrevBtn, swapNextBtn, exitButton };
        JLabel[] labels = { formationLabel, swapLabel };
        window.displayLabel(labels);

        window.displayButtons(buttons);

        formPrevBtn.setBounds(ScreenSize.width / 4 - 125, ScreenSize.height / 2 - 140, 45, 50);
        formationLabel.setBounds(ScreenSize.width / 4 - 75, ScreenSize.height / 2 - 140, 150, 50);
        formNextBtn.setBounds(ScreenSize.width / 4 + 80, ScreenSize.height / 2 - 140, 45, 50);

        swapPrevBtn.setBounds(ScreenSize.width / 4 - 125, ScreenSize.height / 2 - 70, 45, 50);
        swapLabel.setBounds(ScreenSize.width / 4 - 75, ScreenSize.height / 2 - 70, 150, 50);
        swapNextBtn.setBounds(ScreenSize.width / 4 + 80, ScreenSize.height / 2 - 70, 45, 50);

        exitButton.setBounds(ScreenSize.width / 4 - 125, ScreenSize.height / 2, 250, 50);

        add(formPrevBtn);
        add(formationLabel);
        add(formNextBtn);

        add(swapPrevBtn);
        add(swapLabel);
        add(swapNextBtn);

        add(exitButton);
    }

    @Override
    void next1() {
    }

    @Override
    void next2() {

    }

    @Override
    void next3() {

    }

    @Override
    void back() {
        window.setMenu(new StartMenu(window));
    }
}

class MatchSettingsMenu extends Menus {
    MatchSettingsMenu(UIWindow window) {
        super(window);
        setLayout(null);

        JButton exitButton = new JButton("Save");
        exitButton.addActionListener(e -> back());

        JButton[] buttons = { exitButton };

        window.displayButtons(buttons);

        exitButton.setBounds(ScreenSize.width / 2 - 125, ScreenSize.height / 2 - 100, 250, 50);

        add(exitButton);
    }

    @Override
    void next1() {
        // empty for the time being
    }

    @Override
    void next2() {
        // empty for the time being

    }

    @Override
    void next3() {
        // empty for the time being
    }

    @Override
    void back() {
        window.setMenu(new StartMenu(window));
    }
}

// class SimWindow {

// }

class SimWindowMenu extends Menus {
    SimWindowMenu(UIWindow window) {
        super(window);
        setLayout(null);

        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(e -> back());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> back());

        JButton[] buttons = { resumeButton, exitButton };

        window.displayButtons(buttons);

        resumeButton.setBounds(ScreenSize.width / 2 - 75, ScreenSize.height / 2 - 100, 150, 50);
        exitButton.setBounds(ScreenSize.width / 2 - 75, ScreenSize.height / 2 - 30, 150, 50);

        add(resumeButton);
        add(exitButton);
    }

    @Override
    void next1() {
        // empty for the time being
    }

    @Override
    void next2() {
        // empty for the time being

    }

    @Override
    void next3() {
        // empty for the time being
    }

    @Override
    void back() {
        window.setMenu(new StartMenu(window));
    }
}

class CircleNode<T> {
    T value;
    CircleNode<T> next;
    CircleNode<T> prev;

    CircleNode(T value) {
        this.value = value;
    }
}

class CircleList<T> {
    private CircleNode<T> current;

    CircleList(T[] values) {
        CircleNode<T> start = new CircleNode<T>(values[0]);
        CircleNode<T> prevNode = start;

        for (int i = 1; i < values.length; i++) {
            CircleNode<T> node = new CircleNode<>(values[i]);
            prevNode.next = node;
            node.prev = prevNode;
            prevNode = node;
        }

        prevNode.next = start;
        start.prev = prevNode;

        this.current = start;

    }

    T getCurrent() {
        return current.value;
    }

    void next() {
        this.current = this.current.next;
    }

    void previous() {
        this.current = this.current.prev;
    }
}



public class SimulationUI {

    public static void main(String[] args) {
        new UIWindow().start();
    }

}

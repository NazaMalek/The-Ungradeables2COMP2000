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

class UIWindow {
    JFrame frame;
    Menus currentMenu;
    boolean debugMode = false;
    int width = 700;
    int height = 400;
    String backgroundColour = "#208026";
    String btnColour = "#404143";

    public UIWindow() {
        frame = new JFrame("Soccer Simulation");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        

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

    private String debugString (){
        if(this.getDebug() == true) return " (Debug)";

        return "";
    }

    void displayButtons(JButton[] buttons){
        for (JButton btn : buttons) {
            btn.setBackground(Color.decode(btnColour));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Lexend", Font.BOLD, 16));
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    void displayCheckBox(JCheckBox[] boxs) {
        for (JCheckBox btn : boxs) {
            btn.setBackground(Color.decode(btnColour));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Lexend", Font.BOLD, 16));
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
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

        startButton.setBounds(window.width/2 - 125, window.height / 2 - 100, 250, 50);
        settingsButton.setBounds(window.width / 2 - 125, window.height / 2 - 30, 250, 50);
        exitButton.setBounds(window.width / 2 - 125, window.height / 2 + 40, 250, 50);

        add(startButton);
        add(settingsButton);
        add(exitButton);
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
 * if time permits add the match settings (optional non-functional)
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

        startButton.setBounds(window.width / 4 - 125, window.height / 2 - 140, 250, 50);
        formationButton.setBounds(window.width / 4 - 125, window.height / 2 - 70, 250, 50);
        matchSettingsButton.setBounds(window.width / 4 - 125, window.height / 2 , 250, 50);
        exitButton.setBounds(window.width / 4 - 125, window.height / 2 + 70, 250, 50);

        add(startButton);
        add(formationButton);
        add(matchSettingsButton);
        add(exitButton);
    }

    @Override
    void next1() {
        // window.setMenu(new SimWindow(window));
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

        JButton[] buttons = {exitButton};
        JCheckBox[] boxes = {debugButton };
        window.displayCheckBox(boxes);
        window.displayButtons(buttons);
        

        debugButton.setBounds(window.width / 2 - 125, window.height / 2 - 100, 250, 50);
        exitButton.setBounds(window.width / 2 - 125, window.height / 2 - 30, 250, 50);

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
 * need to add the special buttons with arrows on them for the formation
 * need to add the pitch next to the buttons displaying the current formation
 * for each side
 * need to add the swap side button and have it implemented
 * 
 */
class FormationMenu extends Menus {
    FormationMenu(UIWindow window) {
        super(window);
        setLayout(null);

        JButton formationButton = new JButton("Change Formation");
        formationButton.addActionListener(e -> next1());

        JButton swapButton = new JButton("Swap Sides");
        swapButton.addActionListener(e -> next2());

        JButton exitButton = new JButton("Save");
        exitButton.addActionListener(e -> back());

        JButton[] buttons = {formationButton, swapButton, exitButton };

        window.displayButtons(buttons);

        formationButton.setBounds(window.width / 4 - 125, window.height / 2 - 140, 250, 50);
        swapButton.setBounds(window.width / 4 - 125, window.height / 2 - 70, 250, 50);
        exitButton.setBounds(window.width / 4 - 125, window.height / 2, 250, 50);

        add(formationButton);
        add(swapButton);
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

        JButton[] buttons = {exitButton };

        window.displayButtons(buttons);

        exitButton.setBounds(window.width / 2 - 125, window.height / 2 - 100, 250, 50);
       

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

// class SimWindowMenu {

// }

public class SimulationUI {

    public static void main(String[] args) {
        new UIWindow().start();
    }

}

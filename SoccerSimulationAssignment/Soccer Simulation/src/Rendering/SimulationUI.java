
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

import java.awt.*;
import javax.swing.*;

public class SimulationUI {

    public class UIWindow {
        JFrame frame;
        Menus currentMenu;

        public UIWindow() {
            frame = new JFrame("Soccer Simulation");
            frame.setSize(700, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
        }

        void setMenu(Menus menu) {
            currentMenu = menu;
            frame.setContentPane(menu);
            frame.revalidate();
            frame.repaint();
        }

        void start() {
            setMenu(new MainMenu(this));
            frame.setVisible(true);
        }
    }

    abstract class Menus extends JPanel {
        protected UIWindow window;

        Menus(UIWindow window) {
            this.window = window;
        }

        abstract void next1();

        abstract void next2();

        abstract void back();
    }

    public class MainMenu extends Menus {
        MainMenu(UIWindow window) {
            super(window);

            JButton startBtn = new JButton("Start");
            startBtn.addActionListener(e -> next1());
            JButton settingButton = new JButton("Settings");
            settingButton.addActionListener(e -> next1());
            JButton exitButton = new JButton("exit");
            exitButton.addActionListener(e -> next1());

            add(startBtn);
            add(settingButton);
            add(exitButton);
        }
        
        @Override
        void next1() {
            window.setMenu(new StartMenu(window));
        }

        @Override 
        void next2(){
            window.setMenu(new SettingsMenu(window));
        }

        @Override
        void back() {
            System.exit(0);
        }

    }

    public class StartMenu {

    }

    public class SettingsMenu {

    }

    public class FormationMenu {

    }

    public class MatchMenu {

    }

    public class SimWindow {

    }

    public class SimWindowMenu {

    }

    public static void main(String[] args) {
        new UIWindow().start();
    }

}

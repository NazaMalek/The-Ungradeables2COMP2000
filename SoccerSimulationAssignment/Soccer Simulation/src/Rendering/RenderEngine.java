package Rendering;

import java.awt.Color;
import javax.swing.JFrame;

public class RenderEngine {
public static void main(String[] args) {
        JFrame frame = new JFrame("Soccer Pitch");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.decode("#208026")); 
    }
}

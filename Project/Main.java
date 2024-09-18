package Project;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo((Component)null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }
}

package Project;

import Project.entity.Player;
import Project.tile.Tile;
import Project.tile.TileManager;

import javax.swing.*;
import java.awt.*;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 3;
    public final int titleSize = 48;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = 768;
    public final int screenHeight = 576;

    int FPS = 60;


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH );

    TileManager tileM =new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(768, 576));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void run() {
        double drawInterwal = (double)(1000000000 / this.FPS);
        double delta = 0.0;
        long lastTime = System.nanoTime();
        long timer = 0L;
        int drawCount = 0;

        while(this.gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (double)(currentTime - lastTime) / drawInterwal;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1.0) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000L) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0L;
            }
        }

    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}

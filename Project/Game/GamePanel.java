package Project.Game;

import javax.swing.*;
import java.awt.*;

    public class GamePanel extends JPanel implements Runnable {
        final int originalTitleSize = 16;
        final int scale = 3;
        final int titleSize = 48;
        final int maxScreenCol = 16;
        final int maxScreenRow = 12;
        final int screenWidth = 768;
        final int screenHeight = 576;
        int FPS = 60;
        KeyHandler keyH = new KeyHandler();
        Thread gameThread;
        int playerX = 100;
        int playerY = 100;
        int playerSpeed = 4;

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
                    this.update();
                    this.repaint();
                    --delta;
                    ++drawCount;
                }

                if (timer >= 1000000000L) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0L;
                }
            }

        }

        public void update() {
            if (this.keyH.upPressed) {
                this.playerY -= this.playerSpeed;
            } else if (this.keyH.downPressed) {
                this.playerY += this.playerSpeed;
            } else if (this.keyH.leftPressed) {
                this.playerX -= this.playerSpeed;
            } else if (this.keyH.rightPressed) {
                this.playerX += this.playerSpeed;
            }

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.WHITE);
            g2.fillRect(this.playerX, this.playerY, 48, 48);
            g2.dispose();
        }
    }
package ru.alexrov.game2d.main;

import ru.alexrov.game2d.object.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial;
    Font arial40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial = new Font("Arial", Font.PLAIN, 20);
        arial40 = new Font("Arial", Font.PLAIN, 40);
        KeyObject keyObject = new KeyObject();
        keyImage = keyObject.image;
    }

    public void showMessage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            g2.setFont(arial40);
            g2.setColor(Color.WHITE);
            String text = "Вы победили!";
            int x = gp.screenWidth / 2 - (int) (g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2);
            int y = gp.screenHeight / 2;
            g2.drawString(text, x, y);

            String textTime = "Время: " + dFormat.format(playTime);
            int x2 = gp.screenWidth / 2 - (int) (g2.getFontMetrics().getStringBounds(textTime, g2).getWidth() / 2);
            int y2 = gp.screenHeight / 2 + 50;
            g2.drawString(textTime, x2, y2);
            gp.gameThread = null;
        } else {

            playTime += (double) 1 / 60;
            g2.setFont(arial);
            g2.setColor(Color.WHITE);
            String timeText = "Время: " + dFormat.format(playTime);
            int x = gp.screenWidth - (int) (g2.getFontMetrics().getStringBounds(timeText, g2).getWidth()) - 20;
            g2.drawString(timeText, x, 30);

            g2.setFont(arial);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, 10, 10, gp.tileSize / 2, gp.tileSize / 2, null);
            g2.drawString("x " + gp.player.hasKey, 40, 30);

            if (this.messageOn) {
                g2.setFont(arial);
                g2.drawString(this.message, 50, gp.screenHeight / 2);

                messageCounter++;
                if (messageCounter > 120) {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }
}

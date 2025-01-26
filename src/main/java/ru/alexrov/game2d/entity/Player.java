package ru.alexrov.game2d.entity;

import ru.alexrov.game2d.main.GamePanel;
import ru.alexrov.game2d.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        this.solidArea = new Rectangle(10, 16, 24, 24);
        this.solidAreaDefaultX = 10;
        this.solidAreaDefaultY = 16;

        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = this.gp.tileSize * 23;
        worldY = this.gp.tileSize * 21;
        speed = 4;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                this.direction = "up";
            } else if (keyH.downPressed) {
                this.direction = "down";
            } else if (keyH.leftPressed) {
                this.direction = "left";
            } else if (keyH.rightPressed) {
                this.direction = "right";
            }

            this.collisionOn = false;
            gp.collisionDetection.detectTileCollision(this);
            int objIndex = gp.collisionDetection.detectObjectCollision(this, true);
            this.pickUpItem(objIndex);

            if (!this.collisionOn) {
                switch (this.direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            this.spriteCounter++;
            if (this.spriteCounter > 10) {
                if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (this.spriteNum == 1) {
                    image = up1;
                }
                if (this.spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (this.spriteNum == 1) {
                    image = down1;
                }
                if (this.spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (this.spriteNum == 1) {
                    image = left1;
                }
                if (this.spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (this.spriteNum == 1) {
                    image = right1;
                }
                if (this.spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void pickUpItem(int index) {
        if (index != 999) {
            String objectName = gp.obj[index].name;
            switch (objectName) {
                case "Key" -> {
                    hasKey++;
                    gp.obj[index] = null;
                    gp.playSound(1);
                    gp.ui.showMessage("Вы нашли ключ!");
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        hasKey--;
                        gp.obj[index] = null;
                        gp.playSound(3);
                    } else {
                        gp.ui.showMessage("Вам нужен ключ");
                    }
                }
                case "Boots" -> {
                    gp.obj[index] = null;
                    this.speed += 2;
                    gp.playSound(2);
                    gp.ui.showMessage("Ваша скорость увеличена!");
                }
                case "Chest" -> {
                    gp.obj[index] = null;
                    gp.playSound(4);
                    gp.ui.showMessage("Вы нашли сундук!");
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                }
            }
        }
    }
}

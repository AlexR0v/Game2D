package ru.alexrov.game2d.main;

import ru.alexrov.game2d.object.ChestObject;
import ru.alexrov.game2d.object.DoorObject;
import ru.alexrov.game2d.object.KeyObject;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        this.gp.obj[0] = new KeyObject();
        this.gp.obj[0].worldX = 23 * gp.tileSize;
        this.gp.obj[0].worldY = 7 * gp.tileSize;

        this.gp.obj[1] = new KeyObject();
        this.gp.obj[1].worldX = 23 * gp.tileSize;
        this.gp.obj[1].worldY = 40 * gp.tileSize;

        this.gp.obj[2] = new KeyObject();
        this.gp.obj[2].worldX = 38 * gp.tileSize;
        this.gp.obj[2].worldY = 8 * gp.tileSize;

        this.gp.obj[3] = new DoorObject();
        this.gp.obj[3].worldX = 10 * gp.tileSize;
        this.gp.obj[3].worldY = 11 * gp.tileSize;

        this.gp.obj[4] = new DoorObject();
        this.gp.obj[4].worldX = 8 * gp.tileSize;
        this.gp.obj[4].worldY = 28 * gp.tileSize;

        this.gp.obj[5] = new DoorObject();
        this.gp.obj[5].worldX = 12 * gp.tileSize;
        this.gp.obj[5].worldY = 22 * gp.tileSize;

        this.gp.obj[6] = new ChestObject();
        this.gp.obj[6].worldX = 10 * gp.tileSize;
        this.gp.obj[6].worldY = 7 * gp.tileSize;
    }
}

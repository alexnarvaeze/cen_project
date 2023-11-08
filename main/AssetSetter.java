package main;

import object.ObjectKey;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new ObjectKey();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new ObjectKey();
        gp.obj[1].worldX = 36 * gp.tileSize;
        gp.obj[1].worldY = 30 * gp.tileSize;
    }
}

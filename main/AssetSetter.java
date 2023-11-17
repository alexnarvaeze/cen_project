package main;

// import object.*;
import entity.*;
import monster.MON_Bat;
import monster.MON_GreenSlime;
import monster.MON_Zombie;
import object.ObjectAxe;
import object.ObjectKey;
import object.ObjectPotion;
import object.ObjectRoyalShield;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;
        gp.obj[i] = new ObjectKey(gp);
        gp.obj[i].worldX = gp.tileSize * 25;
        gp.obj[i].worldY = gp.tileSize * 23;
        i++;

        gp.obj[i] = new ObjectPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 21;
        gp.obj[i].worldY = gp.tileSize * 19;
        i++;

        gp.obj[i] = new ObjectPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[i] = new ObjectAxe(gp);
        gp.obj[i].worldX = gp.tileSize * 33;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[i] = new ObjectRoyalShield(gp);
        gp.obj[i].worldX = gp.tileSize * 35;
        gp.obj[i].worldY = gp.tileSize * 21;
        i++;

        gp.obj[i] = new ObjectPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 22;
        gp.obj[i].worldY = gp.tileSize * 27;
        i++;
    }

    public void setNPC() {
        gp.npc[0] = new Steb(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

    }

    public void setMonster() {
        int i = 0;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 21;
        gp.monster[i].worldY = gp.tileSize * 38;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 24;
        gp.monster[i].worldY = gp.tileSize * 37;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 34;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 38;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 38;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 35;
        gp.monster[i].worldY = gp.tileSize * 8;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 40;
        gp.monster[i].worldY = gp.tileSize * 9;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 35;
        gp.monster[i].worldY = gp.tileSize * 10;
        i++;

        gp.monster[i] = new MON_Zombie(gp);
        gp.monster[i].worldX = gp.tileSize * 12;
        gp.monster[i].worldY = gp.tileSize * 33;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 11;
        gp.monster[i].worldY = gp.tileSize * 32;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 41;
        gp.monster[i].worldY = gp.tileSize * 10;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 22;
        gp.monster[i].worldY = gp.tileSize * 39;
        i++;
    }
}

package object;

import entity.Entity;
import main.GamePanel;

public class ObjectPotion extends Entity {
    int value = 5;
    GamePanel gp;

    public ObjectPotion(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = typeConsumable;
        name = "Potion";
        down1 = setup("objects/potion", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals " + value + " life.";
    }

    public void use (Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + ".\nYou healed " + value + " life.";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife)
            gp.player.life = gp.player.maxLife;
        gp.soundEffect.play(2, false);
    }
    
}

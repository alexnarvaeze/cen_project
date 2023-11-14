package entity;

import java.util.Random;

import main.GamePanel;

public class Steb extends Entity { // steb is the wizard, name is cause i made him look like steve from minecraft
    public Steb(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getStebImage();
        setDialogue();
    }

    public void getStebImage() {
        up1 = setup("npcs/wizard_up_1");
        up2 = setup("npcs/wizard_up_2");
        down1 = setup("npcs/wizard_down_1");
        down2 = setup("npcs/wizard_down_2");
        right1 = setup("npcs/wizard_right_1");
        right2 = setup("npcs/wizard_right_2");
        left1 = setup("npcs/wizard_left_1");
        left2 = setup("npcs/wizard_left_2");
    }

    public void setDialogue() {
        dialogues[0] = "Greetings youngling.";
        dialogues[1] = "My name is Steb. Welcome to the \nisland.";
        dialogues[2] = "Be careful when stepping inside \nthe forest.";
        dialogues[3] = "There is an adventure around the \n corner.";
    }

    public void setAction() {
        actionCounter++;

        if (actionCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // picks number from 1 to 100

            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}

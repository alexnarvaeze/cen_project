package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTools;

import java.awt.Graphics2D;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[60];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/worldV2.txt", 0);
    }

    public void getTileImage() {
            // Placeholder tiles
            setup(0, "000", false);
            setup(1, "000", false);
            setup(2, "000", false);
            setup(3, "000", false);
            setup(4, "000", false);
            setup(5, "000", false);
            setup(6, "000", false);
            setup(7, "000", false);
            setup(8, "000", false);
            setup(9, "000", false);

            /* Actual tiles used in the future
            setup(10, "001", false);
            setup(11, "002", false);
            setup(12, "003", false);
            setup(13, "004", false);
            setup(14, "005", false);
            setup(15, "006", false);
            setup(16, "007", false);
            setup(17, "008", false);
            setup(18, "009", false);
            setup(19, "010", false);
            setup(20, "011", false);
            setup(21, "012", false);
            setup(22, "013", false);
            setup(23, "014", false);
            setup(24, "015", false);
            setup(25, "016", true);
            setup(26, "017", false);
            setup(27, "018", true);
            setup(28, "019", true);
            setup(29, "020", true);
            setup(30, "021", true);
            setup(31, "022", true);
            setup(32, "023", true);
            setup(33, "024", true);
            setup(34, "025", true);
            setup(35, "026", true);
            setup(36, "027", true);
            setup(37, "028", true);
            setup(38, "029", true);
            setup(39, "030", true);
            setup(40, "031", true);
            setup(41, "032", true);
            setup(42, "033", true);
            setup(43, "034", false);
            setup(44, "035", true);
            setup(45, "036", false);
            setup(46, "037", false);
            setup(47, "038", false);
            setup(48, "039", false);
            setup(49, "040", true);
            */

            // Grass tiles
            setup(10, "001", false);
            setup(11, "002", false);

            // Water tiles
            setup(12, "018", true);
            setup(13, "019", true);
            setup(14, "020", true);
            setup(15, "021", true);
            setup(16, "022", true);
            setup(17, "023", true);
            setup(18, "024", true);
            setup(19, "025", true);
            setup(20, "026", true);
            setup(21, "027", true);
            setup(22, "028", true);
            setup(23, "029", true);
            setup(24, "030", true);
            setup(25, "031", true);

            // Road tiles
            setup(26, "003", false);
            setup(27, "004", false);
            setup(28, "005", false);
            setup(29, "006", false);
            setup(30, "007", false);
            setup(31, "008", false);
            setup(32, "009", false);
            setup(33, "010", false);
            setup(34, "011", false);
            setup(35, "012", false);
            setup(36, "013", false);
            setup(37, "014", false);
            setup(38, "015", false);

            // Dirt, wall, and tree tiles
            setup(39, "017", false);
            setup(40, "032", true);
            setup(41, "016", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTools uTools = new UtilityTools();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTools.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                } 
                // this if statement makes it so the entire world map is not drawn even when the player's camera is not within range
                // thus, requiring less processing power and making the game run smoother if a larger world map is introduced
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

// EV
package main;

import object.*;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	public void setObject() {
		// HOUSE KEY
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 11 * gp.tileSize;
		gp.obj[0].worldY = 17 * gp.tileSize;

		// BLUE FOREST KEY
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 12 * gp.tileSize;
		gp.obj[1].worldY = 39 * gp.tileSize;

		// BLUE FOREST KEY2
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 14 * gp.tileSize;
		gp.obj[2].worldY = 32 * gp.tileSize;

		// HOUSE DOOR
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 12 * gp.tileSize;
		gp.obj[3].worldY = 20 * gp.tileSize;

		// BLUE FOREST ENTRANCE DOOR
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 25 * gp.tileSize;
		gp.obj[4].worldY = 27 * gp.tileSize;

		// BLUE FOREST DOOR2
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 28 * gp.tileSize;
		gp.obj[5].worldY = 29 * gp.tileSize;

		// BLUE FOREST DOOR3
		gp.obj[6] = new OBJ_Door();
		gp.obj[6].worldX = 29 * gp.tileSize;
		gp.obj[6].worldY = 32 * gp.tileSize;

		// BLUE FOREST BLACK KEY
		gp.obj[7] = new OBJ_BlackKey();
		gp.obj[7].worldX = 42 * gp.tileSize;
		gp.obj[7].worldY = 37 * gp.tileSize;

		// YELLOW FOREST KEY1
		gp.obj[8] = new OBJ_Key();
		gp.obj[8].worldX = 42 * gp.tileSize;
		gp.obj[8].worldY = 12 * gp.tileSize;

		// BLACK DOOR TO CHEST
		gp.obj[9] = new OBJ_BlackDoor();
		gp.obj[9].worldX = 19 * gp.tileSize;
		gp.obj[9].worldY = 10 * gp.tileSize;

		// TREASURE CHEST
		gp.obj[10] = new OBJ_RedChest();
		gp.obj[10].worldX = 19 * gp.tileSize;
		gp.obj[10].worldY = 7 * gp.tileSize;

	}
}

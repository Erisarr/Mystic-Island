// EV
package main;
import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity ent) {
		// GETS ENTITY'S COORDS
		int entityLeftWorldX = ent.worldX + ent.solidArea.x;
		int entityRightWorldX = ent.worldX + ent.solidArea.x + ent.solidArea.width;
		int entityTopWorldY = ent.worldY + ent.solidArea.y;
		int entityBottomWorldY = ent.worldY + ent.solidArea.y + ent.solidArea.height;
		
		// GETS ENTITY'S COORDS IN RELATION TO A TILE
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1 = 0;
		int tileNum2 = 0;
		
		// CHECKS WHAT TILE ENTITY IS WALKING INTO
		switch(ent.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - ent.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + ent.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - ent.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			break;
		case "right":
			entityRightCol = (entityRightWorldX + ent.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			break;
		}

		if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
			ent.collisionOn = true;
		}
	}
	
	// CHECK IF PLAYER HITS AN OBJECT, AND WHICH IT IS
	public int checkObject(Entity ent, boolean isPlayer) {
	
		int index = 999;
		
		for(int i = 0; i<gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				// GETS ENTITY'S SOLID AREA POSITION
				ent.solidArea.x = ent.worldX + ent.solidArea.x;
				ent.solidArea.y = ent.worldY + ent.solidArea.y;
				
				// GETS OBJECT'S SOLID AREA POSITION
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
			
				// CHECKS IF OBJECT AND PLAYER TOUCH
				switch(ent.direction) {
				case "up":
					
					ent.solidArea.y -= ent.speed;
					if(ent.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							ent.collisionOn = true;
						}
						if (isPlayer == true) {
							index = i;
						}
					}
					break;
				case "down":
					ent.solidArea.y += ent.speed;
					if(ent.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							ent.collisionOn = true;
						}
						if (isPlayer == true) {
							index = i;
						}
					}
					break;
				case "left":
					ent.solidArea.x -= ent.speed;
					if(ent.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							ent.collisionOn = true;
						}
						if (isPlayer == true) {
							index = i;
						}
					}
					break;
				case "right":
					ent.solidArea.x += ent.speed;
					if(ent.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision == true) {
							ent.collisionOn = true;
						}
						if (isPlayer == true) {
							index = i;
						}
					}
					break;
				} 
				
				ent.solidArea.x = ent.solidAreaDefaultX;
				ent.solidArea.y = ent.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
}

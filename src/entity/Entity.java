// EV
package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	// POSITION/SPEED
	public int worldX, worldY, speed;
	
	// SPRITES
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	// AREA OF ENTITY
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	public boolean collisionOn = false;
}

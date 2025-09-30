// EV
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public int hasKey = 0;
	public int hasBlackKey = 0;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// SCREEN COORDS
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);;
		
		// AREA OF PLAYER
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	} 
	
	public void setDefaultValues() {
		worldX = gp.tileSize*13;
		worldY = gp.tileSize*18;
		speed = 5;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/squirtle_up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/squirtle_up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/squirtle_down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/squirtle_down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/squirtle_left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/squirtle_left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/squirtle_right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/squirtle_right2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed==true) {
				direction = "up";
			} else if(keyH.downPressed==true) {
				direction = "down";
			} else if(keyH.leftPressed==true) {
				direction = "left";
			} else if(keyH.rightPressed==true) {
				direction = "right";
			}
			
			// CHECKS TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// PLAYER CAN MOVE IF NO COLLISION
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			// SPRITES UPDATE WHEN MOVEMENT
			spriteCounter++;
			if(spriteCounter > 8) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
			String objName = gp.obj[i].name;
			
			// CHECKS OBJECT TOUCHED
			switch(objName) {
			case "Key":
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a key!");
				break;
			case "Black_Key":
				hasBlackKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got the Black Key!");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("You unlocked the door!");
				} else {
					gp.ui.showMessage("You need a key!");
				}
				break;
			case "Black_Door":
				if(hasBlackKey > 0) {
					gp.obj[i] = null;
					hasBlackKey--;
					gp.ui.showMessage("You unlocked the final door!");
				} else {
					gp.ui.showMessage("You need a black key!");
				}
				break;
			case "Red_Chest": 
				gp.ui.gameDone = true;
				break; 
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		// DRAWS PLAYER
		BufferedImage image = null;
		switch(direction){
		case "up":
			if(spriteNum == 1) {
				image = up1;
			} 
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			} 
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			} 
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			} 
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}

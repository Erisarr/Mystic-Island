// EV
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameDone = false;
	public int commandNum = 0;
	
	double playTime = 0;
	// displays up to 2 places of decimals
	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		if (gameDone == true) {
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			
			String text;
			int textL;
			int x;
			int y;

			text = "You found the treasure!";
					// returns length of text
			textL = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textL/2;
			y = gp.screenWidth/2 - (gp.tileSize);
			g2.drawString(text, x, y);

			
			text = "Your Time is: " + dFormat.format(playTime) + "!";
					// returns length of text
			textL = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textL/2;
			y = gp.screenWidth/2 + (gp.tileSize*3);
			
			
			g2.drawString(text, x, y);
			g2.setFont(arial_80B);
			g2.setColor(Color.YELLOW);
			text = "Congratulations!";
					// returns length of text
			textL = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textL/2;
			y = gp.screenWidth/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);

			gp.gameThread = null;

		} else {
			if (gp.gameState == gp.titleState) {
				drawTitleScreen();
			} else {
				g2.setFont(arial_40);
				g2.setColor(Color.WHITE);
				g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
				g2.drawString("x " + gp.player.hasKey, 74, 65);


				// TIME PLAYED
				playTime += (double) 1/60;
				g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);

				// MESSAGE
				if(messageOn == true) {
					g2.setFont(g2.getFont().deriveFont(30f));
					g2.drawString(message, gp.tileSize/2, gp.tileSize*11);

					messageCounter++;

					if (messageCounter > 105) {
						messageOn = false;
						messageCounter = 0;
					}

				}
			}
			
		}
	}
	
	public void drawTitleScreen() {
		// BACKGROUND COLOR
		g2.setColor(new Color(70, 120, 180));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Mystic Island";
		int x = getCenteredTextX(text);
		int y = gp.tileSize*3;
		
		// SHADOW
		g2.setColor(new Color(0,80,0));
		g2.drawString(text, x+5, y+5);
		
		// MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// CHARACTER ICON
		x = gp.screenWidth/2 - gp.tileSize*2/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
	
		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
		
		text = "NEW GAME";
		x = getCenteredTextX(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		} 
		
		text = "QUIT";
		x = getCenteredTextX(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
	}
	
	public int getCenteredTextX(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}

// EV
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN VARIABLES
	final int ogTileSize = 16;
	final int scale = 3;
	
	public int tileSize = ogTileSize*scale; // 48x48 tile
	public int maxScreenCol = 16;
	public int maxScreenRow =  12;
	public int screenWidth = tileSize * maxScreenCol; // 760 px
	public int screenHeight = tileSize * maxScreenRow; // 576 px

	// WORLD VARIABLES
	public final int maxWorldCol = 52;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize*maxWorldCol;
	public final int worldHeight = tileSize*maxWorldRow;
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[12];
	
	// STATE OF GAME
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(0,0,40));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
		gameState = titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		double drawInterval = 1000000000/FPS; // 0.0166 SECONDS
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread!=null) {
			
			// UPDATES INFORMATION (LOGICALLY)
			update();  
		
			// UPDATES INFORMATION (GRAPICALLY)
			repaint(); 
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime/=1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		} else {
			// TILES
			tileM.draw(g2);
			
			//  OBJECTS
			for (int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			// PLAYER
			player.draw(g2); 
			
			// UI
			ui.draw(g2);
		}
		
		g2.dispose(); // SAVES MEMORY
	}
}

// EV
package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_RedChest extends SuperObject{
	// constructor for object
	public OBJ_RedChest(){
		name = "Red_Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/red_chest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

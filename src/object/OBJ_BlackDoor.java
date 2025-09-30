// EV
package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_BlackDoor extends SuperObject{
	// constructor for black door object
	public OBJ_BlackDoor(){
		name = "Black_Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/black_door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}


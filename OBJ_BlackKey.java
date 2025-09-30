// EV
package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_BlackKey extends SuperObject{
	// constructor for black key object
	public OBJ_BlackKey(){
		name = "Black_Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/black_key.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


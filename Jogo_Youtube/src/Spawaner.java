import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawaner {

	public int timer = 0;
	
	public List<RectObj> rectangles = new ArrayList<RectObj>();

	public void update() {
		timer++;
		/*a cada um segundo*/
		if(timer % 60 ==0) {
			rectangles.add(new RectObj(0, new Random().nextInt(480-40),40,40));
		}
		
		for(int i = 0; i < rectangles.size(); i++) {
			
			RectObj current = rectangles.get(i);
			rectangles.get(i).update();
			
			if(current.x > Game.WIDTH) {
				rectangles.remove(current);
				Game.contador--;
			}
		}
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < rectangles.size(); i++) {
			RectObj current = rectangles.get(i);
			Graphics2D g2 = (Graphics2D) g;
			
			/*para rotacionar*/
			g2.rotate(Math.toRadians(current.rotation),current.x+current.width/2, current.y+current.height/2);
			g2.setColor(current.color);
			g2.fillRect(current.x, current.y, current.width, current.height);
			/*desfaz a rotação*/
			g2.rotate(Math.toRadians(-current.rotation),current.x+current.width/2, current.y+current.height/2);
		}
	}
}

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class OtherObjects {
	private int velocity;
	private int x; //xPosition 
	private int y; //yPosition 
	private int width; //width of rectangle 
	private int height; //height of rectangle
	private Image img; //Enemy sprite
	private Rectangle bounds; //Image boundries
	
	public OtherObjects(int x, int y, int width, int velocity, String s) {
		this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.velocity = velocity;
        bounds = new Rectangle(x,y,height,width);
		
	}

	 //Gets the enemy sprite
    public void setImage(String s) {
            img = Toolkit.getDefaultToolkit().getImage(s);
    }

  //Moves the enemy and the collision rectangle down with it 
    public void move() {
            y += velocity;
            bounds.setLocation(x,y); //Moves the rectangle, testing collision
    }
  //Draws the rectangle
    public void draw(Graphics g) {
            g.fillRect(x, y, width, height);
    }

    //Draws the enemy sprite
    public void drawImage(Graphics g) {
                    g.drawImage(img, x, y, null);
    }


}

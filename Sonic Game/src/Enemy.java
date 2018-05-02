import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Enemy {
	private int velocity;
	private int x; //xPosition 
	private int y; //yPosition 
	private int width; //width of rectangle 
	private int height; //height of rectangle
	private Image img; //Enemy sprite
	private Rectangle bounds; //Image boundries

	public Enemy(int x, int y, int width, int velocity, String s) {
		this.x = x;
        this.setY(y);
        this.setHeight(height);
        this.width = width;
        this.velocity = velocity;
        setBounds(new Rectangle(x,y,getHeight(),width));
		
	}
	 //Gets the enemy sprite
    public void setImage(String s) {
            img = Toolkit.getDefaultToolkit().getImage(s);
    }

  //Moves the enemy and the collision rectangle down with it 
    public void move() {
            setY(getY() + velocity);
            getBounds().setLocation(x,getY()); //Moves the rectangle, testing collision
    }
  //Draws the rectangle
    public void draw(Graphics g) {
            g.fillRect(x, getY(), width, getHeight());
    }

    //Draws the enemy sprite
    public void drawImage(Graphics g) {
                    g.drawImage(img, x, getY(), null);
    }
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

}


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Player {
	private int x; //xPosition 
	private int y; //yPosition 
	private int width; //width of rectangle 
	private int height; //height of rectangle
	private Image img; //Enemy sprite
	private Rectangle bounds; //Image boundries
	private int speed;
	public Player(int speed,int x, int y, int width, String s) 
	{
		this.setX(x);
		this.setY(y);
		this.setHeight(height);
		this.setWidth(width);
		setBounds(new Rectangle(x,y,getHeight(),width));
		this.speed = 3;
	}

	//Moves the player and the collision boxes to the desired direction
	public void moveLeft() {
		setX(getX() - speed);
		getBounds().setLocation(getX(),getY());
	}

	public void moveRight() {
		setX(getX() + speed);
		getBounds().setLocation(getX(),getY());
	}

	public void moveUp() {
		setY(getY() - speed);
		getBounds().setLocation(getX(), getY());
	}

	public void moveDown() {
		setY(getY() + speed);
		getBounds().setLocation(getX(), getY());
	} 

	public void setImage(String s) {
		img = Toolkit.getDefaultToolkit().getImage(s);
	}

	//Draws the rectangle
	public void draw(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	//Draws the enemy sprite
	public void drawImage(Graphics g) {
		g.drawImage(img, getX(), getY(), null);
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

}

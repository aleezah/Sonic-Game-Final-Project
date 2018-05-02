import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Event;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyEvent;

public class Main extends Applet implements Runnable, ActionListener {

	private Graphics graphics;
	//random generator
	private	Random randomGenerator = new Random ();
	//Enemy Counter
	private	int enemyCounter;
	private int playerLives=0;
	private int windowWidth = 320; //SizeX of window
	private int windowHeight = 600;//SizeY of window
	private int numEnemies;

	//Images
	private Image image;
	private Player player; //Calls player class
	private Enemy enemy; //Calls enemy class
	private OtherObjects shield; //Calls enemy class

	//Picture Stuff
	private final String introScreen = "mainPic.png";
	private final String howToScreen = "HowTo.png";
	private final String gameScreen1 = "introPic.png";
	private final String gameOver1 = "gameOverPic.jpg";

	private	Image endPic; // game over pic
	private	Image introPic; // main screen
	private	Image battlePic;//game backround
	private	Image howToPic; // how to play picture

	private	int picWidth, picHeight;
	private	int picWidth2, picHeight2;
	private	int picWidth3, picHeight3;
	private	int picWidth4, picHeight4;

	//music for the game
	private AudioClip introMusic;
	private AudioClip battleMusic;
	private AudioClip endMusic;

	private ArrayList <OtherObjects> Shield; 
	private ArrayList <Enemy> enemies = new ArrayList <Enemy> (); 

	//Player movement and control booleans
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;

	private	Button Play; // Play button
	private Button Quit; // closes the program
	private	Button playAgain; // back to main screen
	private	Button Continue; // back to main screen



	//Screens
	private boolean mainScreen;
	private boolean howToPlay;
	private boolean gameScreen;
	private boolean gameOver;

	private Image dbImage;
	private Graphics dbg;

	public void init ()
	{
		resize (600,500);
		mainScreen=true;
		setLayout (null);
		setSize(windowWidth, windowHeight);
		setFocusable (true); //Allows the user to not have to click the screen in order to start playing

		//Adds key listener for use of keys
		//addKeyListener(this);

		//Buttons 
		Play = new Button ("Play");
		Play.setBounds (200, 250, 70, 20);
		Quit = new Button ("Quit");
		Quit.setBounds (200, 275, 70, 20);
		playAgain = new Button ("Play Again");
		playAgain.setBounds (100, 300, 100, 30);
		Continue = new Button ("Play");
		Continue.setBounds (173, 320, 60, 20);
		//Buttons Action Listener
		Continue.addActionListener (this);
		Quit.addActionListener (this);
		Play.addActionListener (this);
		playAgain.addActionListener (this);

		//Added on initial screen
		add(Play);
		add(Quit);

		//FIX MUSIC LATER
		introMusic = getAudioClip (getDocumentBase (), "opening.au");
		battleMusic = getAudioClip (getDocumentBase (), "gameMusic.au");
		endMusic = getAudioClip (getDocumentBase (), "gameoversonic.au");

		introMusic.play ();

		//Picture stuff
		battlePic = getToolkit ().getImage (gameScreen1);
		prepareImage (battlePic, this);

		endPic = getToolkit ().getImage (gameOver1);
		prepareImage (endPic, this);

		introPic = getToolkit ().getImage (introScreen);
		prepareImage (introPic, this);

		howToPic = getToolkit ().getImage (howToScreen);
		prepareImage (howToPic, this);


		MediaTracker tracker = new MediaTracker (this);
		tracker.addImage (introPic, 0);
		tracker.addImage (howToPic, 1);
		tracker.addImage (battlePic, 2);
		tracker.addImage (endPic, 3);

		// Initialize the picture size
		picWidth = introPic.getWidth (null);
		picHeight = introPic.getHeight (null);

		picWidth2 = howToPic.getWidth (null);
		picHeight2 = howToPic.getHeight (null);

		picWidth3 = battlePic.getWidth (null);
		picHeight3 = battlePic.getHeight (null);

		picWidth4 = endPic.getWidth (null);
		picHeight4 = endPic.getHeight (null);
		//the audio is taken in from here


		try
		{
			tracker.waitForAll ();
		}

		catch (InterruptedException e)
		{
		}
		// If there were any errors loading the image, then abort the program with a message.
		if (tracker.isErrorAny ())
		{
			showStatus ("Couldn't load ");
			return;
		}



	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		//Why doesnt this work

		if (e.getSource() == Quit)
		{

			System.exit (0);
		}

		else if (e.getSource () == Play)
		{

			mainScreen=false;
			howToPlay= true;
			gameScreen=false;
			gameOver = false;
			remove(Play);
			remove(Quit);
			add(Continue);
			repaint ();

		}
		else if (e.getSource () == Continue)
		{

			mainScreen=false;
			howToPlay= false;
			gameScreen=true;
			gameOver = false;
			remove(Continue);
			introMusic.stop();
			battleMusic.play();
			repaint ();

		}

	}
	public void run() {
		// TODO Auto-generated method stub

	}
	public void update (Graphics g)
	{

		// initialize buffer
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize ().width, this.getSize ().height);
			dbg = dbImage.getGraphics ();
		}

		// clear screen in background
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

		// draw elements in background
		dbg.setColor (getForeground ());
		paint (dbg);

		// draw image on the screen
		g.drawImage (dbImage, 0, 0, this);

	}

	public void update() {

		if (gameScreen ==true)
		{
			//Allows the player to move in the x and y directions
			if (moveUp && player.getY() > 0) {
				player.moveUp();
			}
			if (moveDown && player.getY() + player.getHeight() < getHeight() - 100) {
				player.moveDown();
			} 
			if (moveLeft && player.getX() > 0) {
				player.moveLeft();
			}
			if (moveRight && player.getX() + player.getWidth() < getWidth() - 38) {
				player.moveRight();
			}

			int random = randomGenerator.nextInt(10); 
			int spawnRate = 15;
			if (random < spawnRate)
			{
				numEnemies = 1;

			} 
			else 
			{
				numEnemies = 0;
			}

			if (numEnemies == 1) {
				//Spawns the enemy within the boundaries of the screen
				int enemySpawnX = randomGenerator.nextInt(getWidth() - 55);
				//Creates a new instance of the enemy 
				enemy = new Enemy(enemySpawnX, 0, 32, 10, "enemy.GIF");
				enemies.add(enemy);
				repaint();
				numEnemies++;
			}
		}

		for (int j = 0; j < enemies.size(); j++) {
			enemies.get(j).move();

			if (enemies.get(j).getY() + enemies.get(j).getHeight() > getHeight()) {
				enemies.remove(j);
				enemyCounter=enemyCounter+1;
				j--;
				continue;
			}

			//Removes enemy if they hit player, player loses 50 health
			if (enemies.get(j).getBounds().intersects(player.getBounds())) {
				enemies.remove(j);
				playerLives=playerLives-1;
				j--;
				continue;
			}
		}
		if (playerLives == 0)
		{

			mainScreen = false;
			howToPlay = false;
			gameScreen = false;
			gameOver = true;
			endMusic.play();


		}

	}







	public void paint (Graphics g)
	{
		if (mainScreen == true)
		{
			g.drawImage (introPic, 0, 0, getWidth (), getHeight (), this);
		}
		if (howToPlay == true)
		{
			g.drawImage (howToPic, 0, 0, getWidth (), getHeight (), this);
		}
		if (gameScreen == true)
		{
			g.drawImage (battlePic, 0, 0, getWidth (), getHeight (), this);


		}
		if (gameOver == true)
		{
			g.drawImage (endPic, 0, 0, getWidth (), getHeight (), this);

		}
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if (gameScreen) {

			if (key == KeyEvent.VK_W) {
				moveUp = true;
			}
			if (key == KeyEvent.VK_S) {
				moveDown = true;
			} 
			if (key == KeyEvent.VK_A) { 
				moveLeft = true;
			}
			if (key == KeyEvent.VK_D) {
				moveRight = true;
			}

		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			moveUp = false;
		}
		if (key == KeyEvent.VK_S) {
			moveDown = false;
		} 
		if (key == KeyEvent.VK_A) { 
			moveLeft = false;
		}
		if (key == KeyEvent.VK_D) {
			moveRight = false;
		}

	}
}

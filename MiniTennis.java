import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.swing.*;
import java.swing.JFrame;
import java.swing.JPanel;
import java.swing.JOptionPane;
import java.swing.JLabel;

public class MiniTennis extends JPanel{
	Racquet racquet = new Racquet(this);
	Ball ball = new Ball(this);
	int speed = 1;
	private JLabel greeting;
	
	private int getScore() {
		return speed -1;
	}
	
	public MiniTennis() {
		addKeyListener(new KeyListener () {
							public void keyTyped(KeyEvent e) {
							}
							public void keyPressed(KeyEvent e) {
								racquet.keyPressed();
							}
							public void keyReleased(KeyEvent e) {
								racquet.keyReleased();
							}
						});
		setFocusable(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		MiniTennis game = new MiniTennis();
		JFrame frame = new JFrame();
		frame.add(game);
		game.greet();
		frame.setSize(300,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true){
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
	
	public void greet(){
		greeting = new JLabel("Foundations of Programming Tennis Match");
		add(greeting);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
							RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		
		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font("Verdana", Font.BOLD, 20));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
		
	}
	
	private void move(){
		ball.move();
		racquet.move();
	}
	public void gameOver(){
		int n = JOptionPane.showConfirmDialog(
							this,
							"Would you like to play again",
							"Game Over",
							JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
			ball.yspd = -1;
		} else {
			System.exit(ABORT);
		}
	}
}
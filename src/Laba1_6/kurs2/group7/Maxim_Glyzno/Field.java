package Laba1_6.kurs2.group7.Maxim_Glyzno;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Field extends JPanel implements MouseMotionListener, MouseListener{

	private boolean paused;

	public double mouseX;
	public double mouseY;
	public boolean xar = false;


	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
	private Timer repaintTimer = new Timer(10, new ActionListener()	{ 
		public void actionPerformed(ActionEvent ev) {
			repaint();
		} 
	});

	public Field(){
		setBackground(Color.WHITE);
		repaintTimer.start();


		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D)g;
		for(BouncingBall ball: balls){
			if(xar)
			{
				ball.toMouse(mouseX,mouseY);
			}
			ball.paint(canvas);

		}
	}

	public void addBall(){
		balls.add(new BouncingBall(this));
	}

	public synchronized void pause(){
		paused = true;
	}

	public synchronized void resume(){
		paused = false;
		notifyAll();
	}
	public synchronized void canMove(BouncingBall ball) throws InterruptedException {
		if(paused){wait();}
	}
	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

}



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements ActionListener,KeyListener {
	static final int SCREEN_WIDTH=600;
	static final int SCREEN_HEIGHT=600;
	static final int UNIT_SIZE=25;
    static final int GAME_UNIT=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY=75;
	final int x[] = new int[GAME_UNIT];
	final int y[] = new int[GAME_UNIT];
	private int bodyP=6;
	private int applesEaten;
	private int appleX;
	private int appleY;
	char direction = 'R';
	boolean running= false;
	Timer timer;
	Random random;
    GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(this);
		startGame();
	}
	public void startGame() {
		newApple();
		running= true;
		timer=new Timer(DELAY,this);//timer, implementa int e un action li
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void newApple() {
		appleX=random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;//genero un numero compreso tra 0 e 24, per trasformarlo in coordinate effettive moltiplico per 25
		appleY=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;//genero un numero compreso tra 0 e 24, per trasformarlo in coordinate effettive moltiplico per 25
	}
	public void draw(Graphics g) {
		if(running) {
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
				g.drawLine(0,i*UNIT_SIZE,i*SCREEN_WIDTH,i*UNIT_SIZE);
			}
			g.setColor(Color.red);
			g.fillOval(appleX,appleY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i=0;i<bodyP;i++) {
				if(i==0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i],UNIT_SIZE,UNIT_SIZE);
				}
				else {
					g.setColor(new Color(45,100,0));
					g.fillRect(x[i], y[i],UNIT_SIZE,UNIT_SIZE);
				}
			}
		}
		else
			gameOver(g);
	}
	public void move() {
		for(int i=bodyP;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0]=y[0]-UNIT_SIZE;
			break;
		case 'D':
			y[0]=y[0]+UNIT_SIZE;
			break;
		case 'L':
			x[0]=x[0]-UNIT_SIZE;
			break;
		case 'R':
			x[0]=x[0]+UNIT_SIZE;
			break;
		}
	}
	public void checkApple() {
		if(x[0]==appleX&&y[0]==appleY) {
			bodyP++;
			newApple(); 
		}
	}
	public void checkCollisions() {
		for(int i= bodyP;i>0;i--) {
			if(x[0]==x[i]&&y[0]==y[i]) {//CONTROLLO LA COLLISIONE CON IL CORPO
				running=false;
				
			}
		}
		if(x[0]<0) {
			x[0]=SCREEN_WIDTH-UNIT_SIZE;//se tocca un bordo perdo
		}
		if(x[0]==SCREEN_WIDTH) {
//			running=false;//se tocca un bordo perdo
			x[0]=0;
		}
		
		if(y[0]<0) {
			y[0]=SCREEN_HEIGHT-UNIT_SIZE;//se tocca un bordo perdo
		}
		if(y[0]==SCREEN_HEIGHT) {
			y[0]=0;//se tocca un bordo perdo
		}
		if(!running) {
			timer.stop();
		}
		
	}
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics=getFontMetrics(g.getFont());
		g.drawString("Game Over Bitch",(SCREEN_WIDTH-metrics.stringWidth("Game Over Bitch"))/2,SCREEN_HEIGHT/2 );
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Tasto premuto: "+e.getKeyChar());
//		if(e.getKeyChar()=='W') {
//			direction='U';
//		}
//		if(e.getKeyChar()=='A') {
//			direction= 'L';
//		}
//		if(e.getKeyChar()=='S') {
//			direction= 'D';
//		}
//		if(e.getKeyChar()=='D') {
//			direction= 'R';
//		}
//		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Tasto premuto: "+e.getKeyChar());
		if(e.getKeyChar()=='W'||e.getKeyChar()=='w') {
			if(direction!='D')
				direction='U';
		}
		if(e.getKeyChar()=='A'||e.getKeyChar()=='a') {
			if(direction!='R')
				direction= 'L';
		}
		if(e.getKeyChar()=='S'||e.getKeyChar()=='s') {
			if(direction!='U')
				direction= 'D';
		}
		if(e.getKeyChar()=='D'||e.getKeyChar()=='d') {
			if(direction!='L')
				direction= 'R';
		}
		if(e.getKeyChar()=='P'||e.getKeyChar()=='p') {//mettere in pausa
			timer.stop();
		}
		if(e.getKeyChar()=='B'||e.getKeyChar()=='b') {//riprende dalla pausa
			timer.start();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//		System.out.println("Entro");
//		System.out.println("Tasto premuto: "+e.getKeyChar());
//		if(e.getKeyChar()=='W') {
//			direction='U';
//		}
//		if(e.getKeyChar()=='A') {
//			direction= 'L';
//		}
//		if(e.getKeyChar()=='S') {
//			direction= 'D';
//		}
//		if(e.getKeyChar()=='D') {
//			direction= 'R';
//		}
//		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}

}

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

import com.sun.javafx.geom.Rectangle;

public class Game extends Canvas implements Runnable, KeyListener{

	public Node[] nodeSnake = new Node[30];/*tamanho da cobrinha*/
	
	public boolean left, right, up, down;
	
	public int score = 0;
	
	/*para criar a Maça na tela*/
	public int macaX = 0, macaY=0;
	
	/*velocidade da cobra*/
	public int spd = 1;
	
	public Game() {
		this.setPreferredSize(new Dimension(240, 240));
		
		for(int i = 0; i < nodeSnake.length; i++) {
			nodeSnake[i] = new Node(0,0);
		}
		
		this.addKeyListener(this);

	}

	private void tick() {
		/**/
		for(int i = nodeSnake.length - 1; i > 0; i--) {
			nodeSnake[i].x = nodeSnake[i-1].x;
			nodeSnake[i].y = nodeSnake[i-1].y;
		}

		/*volta para tela quando a cobra sai*/
		if(nodeSnake[0].x + 10 < 0 ) {
			nodeSnake[0].x = 240;
		}else if(nodeSnake[0].x >= 240 ) {
			nodeSnake[0].x = -10;
		}
		
		if(nodeSnake[0].y + 10 < 0 ) {
			nodeSnake[0].y = 240;
		}else if(nodeSnake[0].y >= 240 ) {
			nodeSnake[0].y = -10;
		}
		
		
		/*Controla as direções da Cobrinha*/
		if(right) {
			nodeSnake[0].x+=spd;
		}else if(up){
			nodeSnake[0].y-=spd;
			
		}else if(down) {
			nodeSnake[0].y+=spd;
			
		}else if(left) {
			nodeSnake[0].x-=spd;
		}
		
		/*Se ocorrer colisão com a maça muda o local da mesma*/
		
		if(new Rectangle(nodeSnake[0].x, nodeSnake[0].y,10,10).intersection(new Rectangle(macaX, macaY, 10,10)) != null) {
			macaX = new Random().nextInt(240-10);
			macaY= new Random().nextInt(240-10);
			
			score ++;
			spd ++;/*aumenta a velocida quando pontua*/
			
			System.out.println("Pontos: " + score);
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 240, 240);
		
		for(int i = 0; i < nodeSnake.length; i++) {
			g.setColor(Color.blue);
			g.fillRect(nodeSnake[i].x, nodeSnake[i].y, 10,10);
		}
		
		g.setColor(Color.red);
		g.fillRect(macaX, macaY, 10, 10);
		
		g.dispose();
		bs.show();
	}

	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Jogo Snake");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		new Thread(game).start();
		
	}
	
	@Override
	public void run() {
	
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
			left = false;
			down = false;
			up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			right = false;
			left = true;
			down = false;
			up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			right = false;
			left = false;
			down = false;
			up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			right = false;
			left = false;
			down = true;
			up = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}





	
	
}

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	public static final int WIDTH = 640, HEIGHT = 480;
	public static int contador = 100;/*será a barra de vida - número de vidas*/
	
	public Spawaner spawaner;
	
	/*Dimensões do jogo*/
	public Game() {
		
		Dimension dimension = new Dimension(WIDTH,HEIGHT);
		this.setPreferredSize(dimension);
		
		spawaner = new Spawaner();
		
	}
	
	/*atualizações do jogo*/
	public void update() {
		
		spawaner.update();
		
		if(contador <= 0) {
			//Game Over - reinicia o jogo
			contador = 100;
		}
	}
	
	/*Renderizar*/
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		java.awt.Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		/*
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.BOLD,23));
		*/
		
		/*g.drawString("Pontos: " + contador, WIDTH/2 -60, 30);/*WIDTH/2 deixa no meio da tela*/
		
		/*criando um retangulo*/
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH/2 - 100 -70, 20, contador*3, 20);
		
		/*Criando a barra*/
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH/2 - 100 -70, 20, contador*3, 20);
		
		/*renderizar o game*/
		
		spawaner.render(g);
		
		bs.show();
	}
	
	/*Toda aplicação Java começa com o método void main*/
	public static void main(String[] args) {
		Game game = new Game();
		JFrame jframe = new JFrame("Meu Jogo");
		jframe.add(game);
		jframe.setLocationRelativeTo(null);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframe.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void run() {
		
		while(true) {
			
			update();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

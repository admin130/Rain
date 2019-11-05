package dev.jag.rain;

import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static int width = 300;
	public static int height = width * 9 / 16;
	public static int scale = 3;
	
	private Thread thread;
	private boolean running = false;
	
	private JFrame frame;
	
	public Game() {
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		
		frame = new JFrame();
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {		
		while(running) {
			System.out.println("Running...");
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}

}
package cf.aspens;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.event.Event;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeUI extends JFrame {
	
	private class Dispatch implements KeyEventDispatcher {

		@Override
		public boolean dispatchKeyEvent (KeyEvent e) {
			
			if (e.getID()!=KeyEvent.KEY_PRESSED) return false;
			Integer kc=e.getKeyCode();
			if (kc>40||kc<36) return false;
			sg.setDirection(kc-36);
			
			return true;
			
		}
		
	}
	
	private static final long serialVersionUID=1L;
	private JPanel jp;
	private SnakeGame sg;
	
	public SnakeUI () {
		
		super();
		
		this.jp=new JPanel();
		this.jp.setBorder(BorderFactory.createEmptyBorder(500,500,0,0));
		this.jp.setLayout(new GridLayout(0,1));
		this.add(jp,BorderLayout.CENTER);
		this.setTitle("Snake");
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new Dispatch());
        
		
		this.sg=new SnakeGame(3);
		new Timer().schedule (new TimerTask () {
				
	            @Override
	            public void run () { 
	           	 
	            	sg.setPos();
	            	repaint();
	           	 
	            }

		},0,133);
		
	}
	
	@Override
	public void paint (Graphics graphics) {
		
		super.paint(graphics);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0,600,600);
		graphics.setColor(Color.GREEN);
		Integer i=0;
		for (SnakeTail t:this.sg.getTails()) {
			
			graphics.fillRect(t.x,t.y,SnakeGame.snakeTailBlockSize,SnakeGame.snakeTailBlockSize);
			++i;
			
		}
		graphics.setColor(Color.RED);
		SnakeTail st=this.sg.getApple();
		graphics.fillRect(st.x,st.y,SnakeGame.snakeTailBlockSize,SnakeGame.snakeTailBlockSize);
		
	}
	
}

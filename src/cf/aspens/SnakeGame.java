package cf.aspens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.glass.ui.Application;

import cf.aspens.SnakeTail;

public class SnakeGame {
	
	private Integer size,direction=1;
	private List<SnakeTail> tails;
	private SnakeTail apple;
	private Random r;
	
	public static final Integer snakeTailBlockSize=25;
	
	/*
	 * Challenges:
	 * 
	 *  - Change colour of head
	 *  - Make proper lose game screen instead of exit
	 * 
	 */
	
	
	public SnakeGame (Integer size) {
		
		
		this.size=size; 
		this.tails=new ArrayList<SnakeTail>();
		Integer i=0;
		while (i!=this.size) {
			
			getTails().add(new SnakeTail(200+(i*30),200));
			++i;
			
		}
		this.r=new Random();
		this.apple=this.newApple();
		
	}
	
	public void setPos () {
		
		Integer i=0;
		
		//i!=0 so the head isn't selected
		i=this.tails.size()-1;//-1 so that its not out of bounds
		while (i!=0) {
			
			SnakeTail t0=this.tails.get(i-1);
			this.tails.get(i).x=t0.x;
			this.tails.get(i).y=t0.y;
			
			--i;
			
		}
		
		Integer inc=SnakeGame.snakeTailBlockSize+5;
		switch (this.direction) {
		
			case 1:
				this.getHead().x-=inc;
				break;
			case 2:
				this.getHead().y-=inc;
				break;
			case 3:
				this.getHead().x+=inc;
				break;
			case 4:
				this.getHead().y+=inc;
				break;
			
		}

		Integer headX=this.getHead().x,headY=this.getHead().y;
		
		i=0;
		for (SnakeTail t:this.getTails())
			if (Math.floor(headY)==t.y&&(Math.floor(headX)==t.x)) ++i;
		
		if (i==2)
			System.exit(0);
		
		SnakeTail st=this.getApple();
		if (Math.floor(headX)==st.x&&Math.floor(headY)==st.y) {
			
			this.apple=this.newApple();
			SnakeTail last=this.tails.get(this.tails.size()-1);
			this.tails.add(new SnakeTail(last.x+30,last.y));
			
		}
		
		if (headX>570)
			this.getHead().x=0;
		else if (headY>570)
			this.getHead().y=0;
		else if (headX<5)
			this.getHead().x=570;
		else if (headY<5)
			this.getHead().y=570;
		
	}

	public List<SnakeTail> getTails() { return this.tails; }
	public SnakeTail getHead () { return this.tails.get(0); }
	public void setDirection (Integer direction) { this.direction=direction; }
	public SnakeTail newApple () { return new SnakeTail((r.nextInt(15)*30)+20,(r.nextInt(15)*30)+20); }
	public SnakeTail getApple () { return this.apple; }
	
}

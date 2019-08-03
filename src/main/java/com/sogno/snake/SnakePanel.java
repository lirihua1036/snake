package com.sogno.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.StringContent;

public class SnakePanel extends JPanel implements KeyListener,ActionListener {

	static  ImageIcon titleImage=null;
	static  ImageIcon foodImage=null;
	static  ImageIcon bodyImage=null;
	static  ImageIcon rightImage=null;
	static int[] snakex=new int[750];
	static int[] snakey=new int[750];
	int len = 3;
	boolean isStart=false;
	Timer timer = new Timer(150, this);
	
	
	public SnakePanel() {
		
		Properties properties = new Properties();
		FileInputStream fis = null;
		String title = null;
		String right = null;
		String food = null;
		String body = null;
		this.setFocusable(true);
		this.addKeyListener(this);
	    timer.start();
		
		
		try {
			
			
			
			fis  = new FileInputStream("src/resource/Image.properties");
			properties.load(fis);
			title = properties.getProperty("title");
			right = properties.getProperty("right");
			food = properties.getProperty("food");
			body = properties.getProperty("body");
			
			titleImage = new ImageIcon(title);
			foodImage = new ImageIcon(food);
			bodyImage = new ImageIcon(body);
			rightImage = new ImageIcon(right);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public void paint(Graphics g) {
		
		
		g.setColor(Color.white);
		
	
		titleImage.paintIcon(this, g, 25, 10);
		g.fillRect(25, 100, 650, 500);
		
		

		
		
		init(g);
		g.setColor(Color.black);
		
		rightImage.paintIcon(this, g, snakex[0], snakey[0]);
		for (int i = 1; i < len; i++) {
			bodyImage.paintIcon(this, g, snakex[i], snakey[i]);
		}
		
		
		
		
		if (!isStart) {
			g.setFont(new Font("arial", Font.BOLD, 30));
			
			g.drawString("press space to start/pause", 150, 250);
		}
		
		
		
	}
	
	public void init(Graphics g) {
		
		snakex[0]=150;
		snakey[0]=150;
		snakex[1]=125;
		snakey[1]=150;
		snakex[2]=100;
		snakey[2]=150;
		
		
		
		
	
		
		
		
	}
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		
		for (int i = len-1; i > 0; i--) {
			snakex[i]=snakex[i-1];
		}
		snakex[0]=snakex[0]+25;
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		
		if (keyCode==KeyEvent.VK_SPACE) {
			isStart=!isStart;
			repaint();
			
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}

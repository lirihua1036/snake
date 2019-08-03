package com.sogno.snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

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
	static  ImageIcon leftImage=null;
	static  ImageIcon upImage=null;
	static  ImageIcon downImage=null;
	static int[] snakex=new int[750];
	static int[] snakey=new int[750];

	int len = 3;
	boolean isStart=false;

	String direction="R";
	Timer timer = new Timer(150, this);
	String title = null;
	String right = null;
	String food = null;
	String body = null;
	Random random=null;

	int foodx = -1;
	int foody = -1;

	public SnakePanel() {
		
		Properties properties = new Properties();
		FileInputStream fis = null;

		this.setFocusable(true);
		this.addKeyListener(this);





		try {

			random = new Random();



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
			leftImage = new ImageIcon(ImageChange.rotateImageBuffer(ImageIO.read(new File(right)),180,null));
			upImage = new ImageIcon(ImageChange.rotateImageBuffer(ImageIO.read(new File(right)),270,null));
			downImage = new ImageIcon(ImageChange.rotateImageBuffer(ImageIO.read(new File(right)),90,null));





		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		init();
	}
	
	
	
	
	
	public void paint(Graphics g) {
		
		//白色画笔填充一块区域26x20
		g.setColor(Color.white);

		titleImage.paintIcon(this, g, 25, 10);
		g.fillRect(25, 100, 650, 500);

		g.setColor(Color.black);


		foodImage.paintIcon(this,g,foodx,foody);


			switch (direction){
				case "U":{
					upImage.paintIcon(this, g, snakex[0], snakey[0]);
					break;
				}
				case "D":{
					downImage.paintIcon(this, g, snakex[0], snakey[0]);
					break;
				}
				case "L":{

					leftImage.paintIcon(this, g, snakex[0], snakey[0]);
					break;
				}
				case "R":{
					rightImage.paintIcon(this, g, snakex[0], snakey[0]);
					break;
				}
			}





		for (int i = 1; i < len; i++) {
			bodyImage.paintIcon(this, g, snakex[i], snakey[i]);
		}







		if (!isStart) {
			g.setFont(new Font("arial", Font.BOLD, 30));
			
			g.drawString("press space to start/pause", 150, 250);
			timer.stop();
		}else {
			timer.start();
		}



		if (snakex[0]==foodx&&snakey[0]==foody)eat();
		
	}


	//初始化
	public void init() {
		
		snakex[0]=150;
		snakey[0]=150;
		snakex[1]=125;
		snakey[1]=150;
		snakex[2]=100;
		snakey[2]=150;
		direction="R";
		len=3;

		foodx=random.nextInt(26)*25+25;
		foody=random.nextInt(20)*25+100;


	}
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		timer.start();
		
		
		for (int i = len-1; i > 0; i--) {
			snakex[i]=snakex[i-1];
			snakey[i]=snakey[i-1];
		}
		switch (direction){
			case "U":{
				snakey[0]=snakey[0]-25;
				break;
			}
			case "D":{
				snakey[0]=snakey[0]+25;
				break;
			}
			case "L":{
				snakex[0]=snakex[0]-25;
				break;
			}
			case "R":{
				snakex[0]=snakex[0]+25;
				break;
			}
		}


			boolean flag=false;
			for (int i = 1; i < len; i++) {
				if (snakex[0]==snakex[i]&&snakey[0]==snakey[i]){
					flag=true;
				}
			}





		if (snakex[0]<25||snakex[0]>650||snakey[0]<100||snakey[0]>575||flag){
			timer.stop();
			init();
			isStart=false;
		}
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




		if (keyCode==KeyEvent.VK_UP && !direction.equals("D")){
			direction="U";
		} else if (keyCode==KeyEvent.VK_SPACE ){
			isStart=!isStart;
			repaint();
		}else if (keyCode==KeyEvent.VK_DOWN && !direction.equals("U")) {
			direction="D";
		}else if (keyCode==KeyEvent.VK_RIGHT && !direction.equals("L")) {
			direction="R";
		}else if (keyCode==KeyEvent.VK_LEFT && !direction.equals("R")) {
			direction="L";
		}


	}


		
		


	@Override
	public void keyReleased(KeyEvent e) {

		
	}

	public void eat(){



		while (true){
			boolean flag=false;
			foodx=random.nextInt(26)*25+25;
			foody=random.nextInt(20)*25+100;

			for (int i = 0; i < len; i++) {
				if (foodx==snakex[i]&&foody==snakey[i]){
					flag=true;
				}
			}
			if (!flag) break;
		}

		len++;


	}








}

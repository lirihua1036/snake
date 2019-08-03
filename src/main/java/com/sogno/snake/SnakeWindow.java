package com.sogno.snake;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeWindow {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("snake");
		frame.setBounds(0, 0, 710, 640);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		
		
		JPanel jPanel = new SnakePanel();
		frame.add(jPanel);
		
		
		frame.setVisible(true);
		
		
	}

}

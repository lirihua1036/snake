package com.sogno.snake;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public  class animal {
	public static void main(String[] args) {
	
		count("src/resource/Image.properties", "1");
		
		
	}
	
	
	
	public static int count(String file,String str) {
		
		File f = new File(file);
		String readString=null;
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(file));
			
			while ( (readString= bReader.readLine())!=null) {
				System.out.println(readString);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
	
	
	public static Integer[] array() {
		int[] arr= new int[] {1,2,2,3,3,5,2,3};
		
		
		List<Integer> list = new ArrayList();
		
		for (int i = 0; i < arr.length; i++) {
			if (!list.contains(i)) {
				list.add(i);
			}
		}
		Integer[] b =new Integer[list.size()];
		return list.toArray(b);
	}
}

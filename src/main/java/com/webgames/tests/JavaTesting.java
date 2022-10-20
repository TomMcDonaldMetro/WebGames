package com.webgames.tests;

public class JavaTesting {

	public static void main(String[] args) {
		
		long n = 802743475;
		System.out.println(n);
		String binString = Long.toBinaryString(n);
		StringBuilder builder = new StringBuilder();
		
		if(binString.length() < 32) {
			for(int i = binString.length(); i < 32; i++) {
				builder.append(0);
			}
			
			binString = builder.toString() + binString;
			builder.setLength(0);
		}

        
        for(int i = 0; i<binString.length();i++) {
        	
            switch(binString.charAt(i)){
             case '0':
                 builder.append(1);
                 break;
             case '1':
                 builder.append(0);
                 break;
             
             } 
        }
        
        System.out.println(Long.parseLong(builder.toString(),2));
	}
}

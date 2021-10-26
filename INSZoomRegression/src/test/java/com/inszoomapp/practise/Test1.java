package com.inszoomapp.practise;

public class Test1 {
    
    public static void main(String[]args){
	
	int num =4566;
	int n,temp,a;
	n = num;
	while(num>0){
		a=num%10;
		System.out.print(a);
		n=n/10;
		num=n;
	}
		
	  
    }

}

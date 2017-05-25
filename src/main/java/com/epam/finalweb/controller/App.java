package com.epam.finalweb.controller;

import com.epam.finalweb.domain.BookType;

public class App  {

	public static void main(String[] args)  {
		B a=new B();
		a.setA(3);
	}
	
}
class A{
	
	private int a;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
}
class B extends A{
	
	private int b;

	public void setB(int b) {
		this.b = b;
	}

	public int getB() {
		return b;
	}
}

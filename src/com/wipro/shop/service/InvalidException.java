package com.wipro.shop.service;

public class InvalidException extends Exception {
	String str="";
	public InvalidException(String s)
	{
		str=s;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return str;
	}

}

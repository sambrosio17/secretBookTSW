package it.SecretBook.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Quote {
	
	public Quote()
	{
		
		
	}
	
	public String getQuote(String type) throws FileNotFoundException
	{
		
		String filename="";
		
		if(type.equalsIgnoreCase("ADRENALINA"))
			filename="adrenalina";
		if(type.equalsIgnoreCase("BRIVIDI LUNGO LA SCHIENA"))
			filename="horror";
		if(type.equalsIgnoreCase("DAL PROFONDO DEL CUORE"))
			filename="amore";
		if(type.equalsIgnoreCase("IN CERCA DI AVVENTURA"))
			filename="avventura";
		if(type.equalsIgnoreCase("REALITY++"))
			filename="fantasy";
		if(type.equalsIgnoreCase("home"))
			filename="frasi";
		
		ArrayList<String> frasi=new ArrayList<String>();
		
		String path="C://Users//ambro//git//repository//SecretBook//src//it//SecretBook//"+filename+".txt";
		
		File myFile= new File(path);
		Scanner myScan=new Scanner(myFile);
		
		while(myScan.hasNextLine())
		{
			frasi.add(myScan.nextLine());
		}
		
		Random r=new Random();
		int random=r.nextInt(frasi.size());
		
		return frasi.get(random);
		
	}

}

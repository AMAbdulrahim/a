package com.example.programJFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyMethods {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		Tournament b1 = new Tournament("Test1", new Game("Football1", 1), Tournament.Type.BRACKETS, 3);
//		Tournament b2 = new Tournament("Test2", new Game("Football2", 2), Tournament.Type.ROUNDROBBIN, 4);
//		
//		ArrayList<Tournament> list = new ArrayList<>();
//		list.add(b1);
//		list.add(b2);
//		printTournamentsToTxtFile("test.txt", list);
		ArrayList<Tournament> t = getTournamentsFromTxtFile("test.txt");
		Tournament t1 = t.get(0);
		Tournament t2 = t.get(1);
		System.out.println(t1.getName() + ", " + t1.getType());
		System.out.println(t2.getName() + ", " + t2.getType());
	}
	
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static Date addDaysTo(Date d, int daysToAdd) {
		Date newDate = new Date(d.getYear(), d.getMonth(), d.getDate());
		Calendar c = Calendar.getInstance(); 
		c.setTime(newDate); 
		c.add(Calendar.DATE, daysToAdd);
		newDate = c.getTime();
		return newDate;
	}
	
	public static boolean fileExists(String path) {
		File f = new File(path);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}
	
	public static void printTournamentsToTxtFile(String path, ArrayList<Tournament> tournaments) throws IOException {
		FileOutputStream out = new FileOutputStream(path);
		ObjectOutputStream objOut = new ObjectOutputStream(out);

		objOut.writeObject(tournaments);

		objOut.close();
	}
	
	public static ArrayList<Tournament> getTournamentsFromTxtFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(path));
		Object obj = objIn.readObject();
		if(obj instanceof ArrayList<?>)
		{
			objIn.close();
		    return (ArrayList<Tournament>)obj;
		}
		objIn.close();
		return null;
	}
}

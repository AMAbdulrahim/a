package com.example.programJFiles;


public class Student {
	private int id;
	private String name;
	
	public Student(int id, String name) {
		name = name.trim();
		
		if(name == "")
			throw new IllegalArgumentException("Error: Student first name missing");
		
		this.id = id;
		this.name = name;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.name;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		if(firstName == "")
			throw new IllegalArgumentException("Error: Student first name missing");
		this.name = firstName;
	}
	
	public void print() {
		System.out.print("Name: " + this.name + ", userID: " + this.id);
	}
	
	public static boolean equals(Student s1, Student s2) {
		return s1.id == s2.id;
	}
	
	public static boolean equals(int s1ID, Student s2) {
		return s1ID == s2.id;
	}
	
	public static boolean equals(Student s1, int s2ID) {
		return s1.id == s2ID;
	}
}

package com.example.programJFiles;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
	private String name;
	private ArrayList<Student> students;
	private Student teamCreator;
	private int teamCapacity;
	private boolean acceptStudents = true;
	
	public Team(String name, int teamCapacity, Student teamCreator) {		//Team creator is added automatically
		if(name == "")														//to the team
			throw new IllegalArgumentException("Error: Team name missing");
		if(teamCapacity <= 0)
			throw new IllegalArgumentException("Error: Team capacity cannot be 1 or less");
		
		this.name = name;
		this.teamCapacity = teamCapacity;
		this.students = new ArrayList<>();
		this.teamCreator = teamCreator;
		students.add(teamCreator);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	
	public Student getTeamCreator() {
		return this.teamCreator;
	}
	
	public int getTeamCapacity() {
		return this.teamCapacity;
	}
	
	public int getTeamSize() {
		return this.students.size();
	}
	
	public void setName(String name) {
		if(name == "")
			throw new IllegalArgumentException("Error: Team name missing");
		this.name = name;
	}
	
	public void setStudents(ArrayList<Student> students) {
		if(students.size() > this.teamCapacity || !this.acceptStudents) 
			throw new IllegalArgumentException("Error: Students number exceeds team capacity");
		
		this.students = students;
		this.teamCapacity = students.size();
	}
	
	public void setTeamCreator(Student teamCreator) {
		this.teamCreator = teamCreator;
		
		if(!isInTeam(teamCreator))
			addStudent(teamCreator);
	}
	
	public void setTeamCapacity(int teamCapacity) {
		if(teamCapacity <= 1)
			throw new IllegalArgumentException("Error: Team capacity cannot be 1 or less");
		this.teamCapacity = teamCapacity;
	}
	
	public void addStudent(Student newStudent) {
		if(isInTeam(newStudent))
			throw new IllegalArgumentException("Error: Student already in team");
		if(isFull())
			throw new IllegalArgumentException("Error: Team is full");
		if(!this.acceptStudents)
			throw new IllegalArgumentException("Error: Team is in tournament");
		
		students.add(newStudent);
	}
	
	public boolean removeStudent(Student studentToDel) {	//If team creator is removed
		for(Student student : students) {					//he will still be the team creator
			if(Student.equals(student, studentToDel)) {		//but not considered as a participant
				students.remove(student);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean removeStudent(int studentToDel) {
		for(Student student : students) {
			if(Student.equals(student, studentToDel)) {
				students.remove(student);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isInTeam(Student newStudent) {
		for(Student student : students) {
			if(Student.equals(student, newStudent))
				return true;
		}
		
		return false;
	}
	
	public boolean isInTeam(int newStudentID) {
		for(Student student : students) {
			if(Student.equals(student, newStudentID))
				return true;
		}
		
		return false;
	}
	
	public boolean isFull() {
		return this.students.size() == this.teamCapacity;
	}
	
	public void print() {
		System.out.println("Team " + this.name + ":");
		for(Student student: students) {
			student.print();
			System.out.println();
		}
		System.out.println("--------------------------");
	}
	
	public static boolean IsStudentIntersection(Team t1, Team t2) {
		for(Student s : t1.getStudents()) {
			if(t2.isInTeam(s))
				return true;
		}
		
		return false;
	}
	
	public static boolean IsStudentIntersection(Team t1, ArrayList<Team> teams) {
		for(Team t2 : teams) {
			boolean intersection = IsStudentIntersection(t1, t2);
			if(intersection)
				return true;
		}
		
		return false;
	}
	
	public static boolean IsDuplicateTeamName(Team t1, ArrayList<Team> teams) {
		for(Team t2 : teams) {
			if(t1.name == t2.name)
				return true;
		}
		
		return false;
	}
	
	public void stopAcceptingStudents() {
		this.acceptStudents = false;
	}
	
	public boolean equal(Team t1, Team t2) {
		return t1.name == t2.name;
	}
	
	public boolean equal(Team t1) {
		return t1.name == this.name;
	}
	
	public String toString() {
		return name;
	}
}

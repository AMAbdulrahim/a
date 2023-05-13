package com.example.programJFiles;

import java.io.Serializable;

public class Game implements Serializable {			//The game object is created on the tournament class not match class
	private String name;
	private int teamCapacity;
	
	enum Preset{
		TENNIS,
		TENNIS_DOUBLES,
		FOOTBALL,
		BASKETBALL,
		VOLLEYBALL,
		SQUASH,
		SQUASH_DOUBLES
	}
	
	public Game(String name, int teamCapacity) {
		name = name.trim();
		if(name == "")
			throw new IllegalArgumentException("Error: Game name missing");
		if(teamCapacity <= 0)
			throw new IllegalArgumentException("Error: Invalid team capacity");
		
		this.name = name;
		this.teamCapacity = teamCapacity;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getTeamCapacity() {
		return this.teamCapacity;
	}
	
	public void setName(String name) {
		if(name == "")
			throw new IllegalArgumentException("Error: Game name missing");
		
		this.name = name;
	}
	
	public void setTeamCapacity(int teamCapacity) {
		if(teamCapacity <= 0)
			throw new IllegalArgumentException("Error: Invalid team capacity");
		this.teamCapacity = teamCapacity;
	}
	
	public static Game getPreset(Preset preset) {
		switch (preset) {
	        case TENNIS:
	            return new Game("Tennis", 1);
	                
	        case TENNIS_DOUBLES:
	        	return new Game("Tennis Doubles", 2);
	                     
	        case FOOTBALL:
	        	return new Game("Football", 11);
	            
	        case BASKETBALL:
	        	return new Game("Basketball", 5);
	            
	        case VOLLEYBALL:
	        	return new Game("Volleyball", 6);
	            
	        case SQUASH:
	        	return new Game("Squash", 1);
	            
	        case SQUASH_DOUBLES:
	        	return new Game("Squash Doubles", 2);
	                    
	        default:
	        	return new Game("Unnamed", 1);
		}
	}
	
}

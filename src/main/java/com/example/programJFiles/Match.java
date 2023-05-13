package com.example.programJFiles;

import java.io.Serializable;
import java.util.Date;

public class Match implements Serializable {
	Team team1, team2;	//An individuals game is presented by a team of capacity = 1, for the sake of generalization
	int goals1 = 0;
	int goals2 = 0;
	Date matchDate = null;
	boolean matchDone = false;
	
	enum Winning{	//Used enumeration because to avoid special cases
		TEAM1,
		TEAM2,
		DRAW
	}
	
	public Match(Team team1, Team team2) {	
		if(team1 != null && team2 != null && Team.IsStudentIntersection(team1, team2))
			throw new IllegalArgumentException("Error: Cannot have player(s) participate in both teams");
		if(team1 == null || team2 == null)
			this.matchDone = true;
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public Match(Team team1, Team team2, Date matchDate) {
		this(team1, team2);
		this.matchDate = matchDate;
	}
	
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	
	public Team getTeam1() {
		return team1;
	}
	
	public Team getTeam2() {
		return team2;
	}
	
	public int getTeam1Goals() {
		return goals1;
	}
	
	public int getTeam2Goals() {
		return goals2;
	}
	
	public int getTeam1Points() {
		if(goals1 > goals2)
			return 3;
		if(goals1 < goals2)
			return 0;
		return 1;
	}
	
	public int getTeam2Points() {
		if(goals2 > goals1)
			return 3;
		if(goals2 < goals1)
			return 0;
		return 1;
	}
	
	public int getPoints(Team t) {
		if(!matchDone)
			return 0;
		if(t.equal(this.team1))
			return getTeam1Points();
		if(t.equal(this.team2))
			return getTeam2Points();
		return 0;
	}
	
	public void setTeam1(Team team1) {
		if(Team.IsStudentIntersection(team1, this.team2))
			throw new IllegalArgumentException("Error: Cannot have player(s) participate in both teams");
		this.team1 = team1;
	}
	
	public void setTeam2(Team team2) {
		if(Team.IsStudentIntersection(this.team1, team2))
			throw new IllegalArgumentException("Error: Cannot have player(s) participate in both teams");
		this.team2 = team2;
	}
	
	public void setTeam1Points(int goals1) {
		this.goals1 = goals1;
	}
	
	public void setTeam2Points(int goals2) {
		this.goals2 = goals2;
	}
	
	public void addGoals1(int goals) {
		this.goals1 += goals;
	}
	
	public void addGoals2(int goals) {
		this.goals2 += goals;
	}
	
	public void matchDone() {
		this.matchDone = true;
	}
	
	public Winning getWinner() {
		if(this.team1 == null && this.team2 == null)	//Special cases that happen in brackets tournaments 
			return Winning.TEAM1;						//when teams number is not 2^n
		if(this.team1 == null)							//
			return Winning.TEAM2;						//
		if(this.team2 == null)							//
			return Winning.TEAM1;						//
		
		if(this.goals1 == this.goals2)				//Normal cases
			return Winning.DRAW;						//
		if(this.goals1 > this.goals2)					//
			return Winning.TEAM1;						//
		
		return Winning.TEAM2;
	}
	
	public boolean isWinner(Team t) {
		if(t.equal(team1) && getWinner() == Winning.TEAM1)
			return true;
		if(t.equal(team2) && getWinner() == Winning.TEAM2)
			return true;
		return false;
	}
	
	public int getGoals(Team t) {
		if(t.equal(team1))
			return getTeam1Goals();
		if(t.equal(team2))
			return getTeam2Goals();
		return 0;
	}
	
	public int getReceivedGoals(Team t) {
		if(t.equal(team1))
			return getTeam2Goals();
		if(t.equal(team2))
			return getTeam1Goals();
		return 0;
	}
	
	public Team getWinnerTeam() {
		if(getWinner() == Winning.TEAM1)
			return team1;
		return team2;
	}
	
	public String toString() {
		String t1 = "null";
		String t2 = "null";
		int day = 0;
		int month = 0;
		int year = 0;
		if(matchDate != null) {
			day = matchDate.getDate();
			month = matchDate.getMonth();
			year = matchDate.getYear();
		}
		if(team1 != null)
			t1 = team1.getName();
		if(team2 != null)
			t2 = team2.getName();
		return t1 + " vs " + t2 + ", " + day + ", " + month + ", " + year;
	}
}

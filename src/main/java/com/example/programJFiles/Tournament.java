package com.example.programJFiles;

import java.util.ArrayList;
import java.util.Collections;

public class Tournament {
	private String name;
	private Game game;
	private Type type;
	private ArrayList<Team> teams;
	private int maxTeamsNum;
	private boolean acceptingTeams = true;	//Set to false when tournament starts
	private int currentRoundNum = 0;
	private ArrayList<ArrayList<Match>> rounds;
	
	public enum Type{
		ROUNDROBBIN,
		BRACKETS
	}
	
	public Tournament(String name, Game game, Type type, int maxTeamsNum) {
		name = name.trim();
		if(name == "")
			throw new IllegalArgumentException("Error: Game name missing");
		if(maxTeamsNum <= 2)
			throw new IllegalArgumentException("Error: Invalid team capacity");
		
		
		this.name = name;
		this.game = game;
		this.type = type;
		this.maxTeamsNum = maxTeamsNum;
		this.teams = new ArrayList<>();
	}
	
	public ArrayList<Team> getTeams(){
		return this.teams;
	}
	
	public boolean isFull() {
		return teams.size() >= maxTeamsNum;
	}
	
	//Code 0 if accepting teams has stopped
	//Code -1 for tournament is full
	//Code -2 for team capacity is not equal to the game's team capacity or Team is not full
	//Code -3 if a student already exists in another team
	//Code -4 the team name is equal or has the same name of another team in the tournament
	//Code 1 for team added successfully
	public int addTeam(Team newTeam) {
		if(!this.acceptingTeams)
			return 0;
		if(teams.size() >= maxTeamsNum)
			return -1;
		if(newTeam.getTeamCapacity() != game.getTeamCapacity() || !newTeam.isFull())
			return -2;
		if(Team.IsStudentIntersection(newTeam, teams))
			return -3;
		if(Team.IsDuplicateTeamName(newTeam, teams))
			return -4;
		
		teams.add(newTeam);
		newTeam.stopAcceptingStudents();
		return 1;
	}
	
	//Teams can't be removed when the tournament starts
	//Instead all their matches will be conceded
	public boolean removeTeam(Team teamToDel) {
		if(!acceptingTeams)
			return false;
		
		for(Team team : teams) {
			if(team.getName() == teamToDel.getName()) {
				teams.remove(teamToDel);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkTournamentDone() {
		ArrayList<Match> lastRound = getRoundMatches(getTotalRoundsNum() - 1);
		for(Match match : lastRound) {
			if(!match.matchDone)
				return false;
		}
		
		return true;
	}
	
	public Team getTournamentWinner() {
		if(!checkTournamentDone())
			throw new IllegalArgumentException("Error: Tournament still running");
		else {
			if(this.type == Type.BRACKETS) {
				Match lastMatch = getRoundMatches(getTotalRoundsNum() - 1).get(0);
				return lastMatch.getWinnerTeam();
			}
			return null;
		}
	}
	
	public void startTournament() {
		if(this.teams.size() <= 2)		//Can't start a tournament with one team
			throw new IllegalArgumentException("Error: Can't start tournaments with less than 3 teams");
		
		this.acceptingTeams = false;
		
		Collections.shuffle(teams);		//Shuffle teams to assign them to matches randomly
		
		if(this.type == Type.BRACKETS)
			startBrackets();
		else
			startRoundRobin();
	}
	
	private void startBrackets() {
		int roundsNum = getTotalRoundsNum();
		int firstRoundMatchNum = (int)Math.pow(2, roundsNum - 1);
		
		this.rounds = new ArrayList<ArrayList<Match>>(roundsNum);
		for(int i=0; i<roundsNum; i++)
			this.rounds.add(new ArrayList<Match>());
		
		//ArrayList<Match> roundOne = new ArrayList<Match>(firstRoundMatchNum);
		ArrayList<Match> roundOne = this.rounds.get(0);
		int teamCounter = 0;
		for(int i=0; i<firstRoundMatchNum; i++) {
			Team t1 = null;
			Team t2 = null;
			
			if(teamCounter < this.teams.size()) {
				t1 = this.teams.get(teamCounter);
				teamCounter++;
			}
			
			if(teamCounter < this.teams.size()) {
				t2 = this.teams.get(teamCounter);
				teamCounter++;
			}
			roundOne.add(new Match(t1, t2));
		}
	}
	
	public void checkRoundEndBrackets() {
		if(this.type != Type.BRACKETS)
			throw new IllegalArgumentException("Error: Tournament is not brackets");
		if(acceptingTeams)
			throw new IllegalArgumentException("Error: Tournament has not started");
		
		ArrayList<Match> currentRound = this.rounds.get(this.currentRoundNum);
		boolean roundDone = true;
		for(int i=0; i<currentRound.size(); i++) {
			if(!currentRound.get(i).matchDone)
				roundDone = false;
		}
		
		if(!roundDone)
			return;
		
		if(this.currentRoundNum == (getTotalRoundsNum() - 1))
			return;
		
		ArrayList<Match> nextRound = this.rounds.get(this.currentRoundNum + 1);
		this.currentRoundNum++;
		int counter = 0;
		for(int i=0; i<(currentRound.size() / 2) ; i++) {
			Team t1 = currentRound.get(counter).getWinnerTeam();
			counter++;
			Team t2 = currentRound.get(counter).getWinnerTeam();
			counter++;
			
			Match m = new Match(t1, t2);
			nextRound.add(m);
		}
	}
	
	public Match getFirstUnplayedMatch() {
		if(this.type == Type.BRACKETS)
			checkRoundEndBrackets();
		else
			checkRoundEndRoundRobin();
		
		ArrayList<Match> currentRound = this.rounds.get(this.currentRoundNum);
		
		for(Match m : currentRound) {
			if(!m.matchDone)
				return m;
		}
		
		return null;
	}
	
	private void checkRoundEndRoundRobin() {
		if(this.type != Type.ROUNDROBBIN)
			throw new IllegalArgumentException("Error: Tournament is not roundrobin");
		if(acceptingTeams)
			throw new IllegalArgumentException("Error: Tournament has not started");
		
		ArrayList<Match> currentRound = this.rounds.get(this.currentRoundNum);
		boolean roundDone = true;
		for(int i=0; i<currentRound.size(); i++) {
			if(!currentRound.get(i).matchDone)
				roundDone = false;
		}
		if(!roundDone)
			return;
		
		if(this.currentRoundNum == (getTotalRoundsNum() - 1))
			return;
		
		this.currentRoundNum++;
		
	}

	public ArrayList<Match> getMatches(int round) {
		if(round < 0 || round >= getTotalRoundsNum())
			throw new IllegalArgumentException("Error: Invalid round number");
		return this.rounds.get(round);
	}
	
	public int getTotalRoundsNum() {
		if(this.type == Type.BRACKETS)
			return (int)(Math.ceil(Math.log(this.teams.size()) / Math.log(2)));
		else {
			int roundsNum = this.teams.size() - 1;
			if(this.teams.size() % 2 == 1)
				roundsNum = this.teams.size();
			return roundsNum;
		}
	}
	
	public int getCurrentRoundNum() {
		return this.currentRoundNum;
	}
	
	public ArrayList<Match> getRoundMatches(int roundNum) {
		if(roundNum < 0 || roundNum >= getTotalRoundsNum())
			throw new IllegalArgumentException("Error: Invalid round");
		return this.rounds.get(roundNum);
	}
	
	public ArrayList<ArrayList<Match>> getAllRounds() {
		return this.rounds;
	}
	
	public Team GetWinner() {
		if(this.type == Type.BRACKETS) {
			if(this.currentRoundNum != (getTotalRoundsNum() - 1))
				throw new IllegalArgumentException("Error: Tournament still running");
			if(!this.rounds.get(currentRoundNum).get(0).matchDone)
				throw new IllegalArgumentException("Error: Final match is not finished");
			return this.rounds.get(currentRoundNum).get(0).getWinnerTeam();
		}
		
		for(ArrayList<Match> round : this.rounds) {
			for(Match match : round) {
				if(!match.matchDone)
					throw new IllegalArgumentException("Error: Tournament is still running");
			}
		}
		
		//
		// Based on Points
		//
		
		int[] points = new int[this.teams.size()];
		
		for(ArrayList<Match> round : this.rounds) {
			for(Match match : round) {
				int index1 = getTeamIndex(match.getTeam1());
				int index2 = getTeamIndex(match.getTeam2());
				
				points[index1] += match.getTeam1Points();
				points[index2] += match.getTeam2Points();
			}
		}
		
		int highestPoints = -1;
		for(int i=0; i<points.length; i++) {
			if(points[i] > highestPoints)
				highestPoints = points[i];
		}
		
		ArrayList<Team> highestTeamsPoints = new ArrayList<>();
		for(int i=0; i<points.length; i++) {
			if(points[i] == highestPoints)
				highestTeamsPoints.add(this.teams.get(i));
		}
		
		if(highestTeamsPoints.size() == 1)
			return highestTeamsPoints.get(0);
		
		return handleMultipleHighestTeams(highestTeamsPoints);
	}
	
	private Team handleMultipleHighestTeams(ArrayList<Team> highestTeamsPoints) {
		//
		// Based on Wins
		//
		
		int[] wins = new int[highestTeamsPoints.size()];
		for(int i=0; i<highestTeamsPoints.size(); i++)
			wins[i] = getWinsNumber(highestTeamsPoints.get(i));
		
		int highestWins = -1;
		for(int i=0; i<wins.length; i++) {
			if(wins[i] > highestWins)
				highestWins = wins[i];
		}
		
		ArrayList<Team> highestTeamsWins = new ArrayList<>();
		for(int i=0; i<wins.length; i++) {
			if(wins[i] == highestWins)
				highestTeamsWins.add(highestTeamsPoints.get(i));
		}
		
		if(highestTeamsWins.size() == 1)
			return highestTeamsWins.get(0);
		
		//
		// Based on Goals
		//
		
		int[] goals = new int[highestTeamsWins.size()];
		for(int i=0; i<highestTeamsWins.size(); i++)
			goals[i] = getGoalsNumber(highestTeamsWins.get(i));
		
		int highestGoals = -1;
		for(int i=0; i<goals.length; i++) {
			if(goals[i] > highestGoals)
				highestGoals = goals[i];
		}
		
		ArrayList<Team> highestTeamsGoals = new ArrayList<>();
		for(int i=0; i<goals.length; i++) {
			if(goals[i] == highestGoals)
				highestTeamsGoals.add(highestTeamsWins.get(i));
		}
		
		if(highestTeamsGoals.size() == 1)
			return highestTeamsGoals.get(0);
		
		//
		// Based on Least received Goals
		//
		
		int[] receivedGoals = new int[highestTeamsGoals.size()];
		for(int i=0; i<highestTeamsGoals.size(); i++)
			receivedGoals[i] = getReceivedGoalsNumber(highestTeamsGoals.get(i));
		
		int lowestReceivedGoals = 1000000;
		for(int i=0; i<receivedGoals.length; i++) {
			if(receivedGoals[i] < lowestReceivedGoals)
				lowestReceivedGoals = receivedGoals[i];
		}
		
		ArrayList<Team> lowestTeamsReceivedGoals = new ArrayList<>();
		for(int i=0; i<receivedGoals.length; i++) {
			if(receivedGoals[i] == lowestReceivedGoals)
				lowestTeamsReceivedGoals.add(highestTeamsGoals.get(i));
		}
		
		//There is no further decider, thus taking first team anyway
		return lowestTeamsReceivedGoals.get(0);
	}

	public int getTeamIndex(Team t) {
		return this.teams.indexOf(t);
	}
	
	public int getWinsNumber(Team t) {
		int wins = 0;
		for(ArrayList<Match> round : this.rounds) {
			for(Match match : round) {
				if(match.isWinner(t))
					wins++;
			}
		}
		
		return wins;
	}
	
	public int getGoalsNumber(Team t) {
		int goals = 0;
		for(ArrayList<Match> round : this.rounds) {
			for(Match match : round) {
				goals += match.getGoals(t);
			}
		}
		
		return goals;
	}
	
	public int getReceivedGoalsNumber(Team t) {
		int receivedGoals = 0;
		for(ArrayList<Match> round : this.rounds) {
			for(Match match : round) {
				receivedGoals += match.getReceivedGoals(t);
			}
		}
		
		return receivedGoals;
	}
	
	private void startRoundRobin() {
		int roundsNum = getTotalRoundsNum();
		int matchNumPerRound = this.teams.size() / 2;
		
		this.rounds = new ArrayList<ArrayList<Match>>(roundsNum);
		for(int i=0; i<roundsNum; i++)
			this.rounds.add(new ArrayList<Match>());
		
		generateRoundsRoundRobin(roundsNum, matchNumPerRound);
	}
	
	private void generateRoundsRoundRobin(int roundsNum, int matchNumPerRound) {
		Team[] teamArr1 = null;
		Team[] teamArr2 = null;
		int arraySize = this.teams.size();
		
		if(this.teams.size() % 2 == 1)
			arraySize++;
		
		teamArr1 = new Team[arraySize/2];
		teamArr2 = new Team[arraySize/2];
		
		for(int i=0; i<arraySize/2; i++)
			teamArr1[i] = this.teams.get(i);
		
		for(int i=arraySize/2; i<arraySize; i++)
			if(i != this.teams.size())
				teamArr2[i-arraySize/2] = this.teams.get(i);
			else
				teamArr2[i-arraySize/2] = null;
		
		for(int i=0; i<this.rounds.size(); i++) {
			for(int j=0; j<teamArr1.length; j++) {
				this.rounds.get(i).add(new Match(teamArr1[j], teamArr2[j]));
			}
			rotateArray(teamArr1, teamArr2);
		}
	}
	
	public void rotateArray(Team[] array1, Team[] array2) {
		Team tmp1 = array1[array1.length-1];
		Team tmp2 = array2[0];
		for(int i=1; i<array1.length-1; i++) {
			array1[i+1] = array1[i];
		}
		array1[1] = tmp2;
		
		for(int i=0; i<array2.length-1; i++) {
			array2[i] = array2[i+1];
		}
		array2[array2.length-1] = tmp1;
	}
	
//	private boolean teamIntersection(Match m1, Match m2) {
//		if(m1.team1.equal(m2.team1))
//			return true;
//		if(m1.team1.equal(m2.team2))
//			return true;
//		if(m1.team2.equal(m2.team1))
//			return true;
//		if(m1.team2.equal(m2.team2))
//			return true;
//		return false;
//	}
	
	public int getTeamPoints(Team t) {
		int counter = 0;
		for(int i=0; i<this.rounds.size(); i++) {
			ArrayList<Match> round = this.rounds.get(i);
			for(Match m : round) {
				counter += m.getPoints(t);
			}
		}
		return counter;
	}
	
//	private boolean teamIntersection(Match m1, ArrayList<Match> matches) {
//		for(Match m2 : matches) {
//			if(teamIntersection(m1, m2))
//				return true;
//		}
//		return false;
//	}
	
	public String getName() {
		return this.name;
	}
	
	public String teamsWithPoints() {
		String s = "";
		for(Team t : this.teams) {
			s = s + "Team: " + t.getName() + ", Points: " + getTeamPoints(t) + "\n";
		}
		return s;
	}
}

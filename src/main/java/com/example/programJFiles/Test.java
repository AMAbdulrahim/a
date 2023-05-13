package com.example.programJFiles;

import java.util.ArrayList;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		bracketsTest();
		//roundRobinTest();
		//playground();
	}
	
	private static void playground() {
		Tournament r = new Tournament("Test", new Game("Football", 1), Tournament.Type.ROUNDROBBIN, 6);
		
		Student s1 = new Student(123 ,"Ghassan");
		Student s2 = new Student(124 ,"Mohammad");
		Student s3 = new Student(125 ,"Salem");
		Student s4 = new Student(126 ,"Ahmad");
		Student s5 = new Student(127 ,"Ali");
		Student s6 = new Student(128 ,"Ali");
		
		Team t1 = new Team("Tigers", 1, s1);
		Team t2 = new Team("Fighters", 1, s2);
		Team t3 = new Team("Bees", 1, s3);
		Team t4 = new Team("Cats", 1, s4);
		Team t5 = new Team("Dogs", 1, s5);
		Team t6 = new Team("Bats", 1, s6);
		
		r.addTeam(t1);
		r.addTeam(t2);
		r.addTeam(t3);
		r.addTeam(t4);
		r.addTeam(t5);
		r.addTeam(t6);
	}
	
	public static void rotateArray(Team[] array1, Team[] array2) {
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

	public static void roundRobinTest() {
		Tournament r = new Tournament("Test", new Game("Football", 1), Tournament.Type.ROUNDROBBIN, 6);
		
		Student s1 = new Student(123 ,"Ghassan");
		Student s2 = new Student(124 ,"Mohammad");
		Student s3 = new Student(125 ,"Salem");
		Student s4 = new Student(126 ,"Ahmad");
		Student s5 = new Student(127 ,"Ali");
		Student s6 = new Student(128 ,"Ali");
		
		Team t1 = new Team("Tigers", 1, s1);
		Team t2 = new Team("Fighters", 1, s2);
		Team t3 = new Team("Bees", 1, s3);
		Team t4 = new Team("Cats", 1, s4);
		Team t5 = new Team("Dogs", 1, s5);
		Team t6 = new Team("Bats", 1, s6);
		
		r.addTeam(t1);
		r.addTeam(t2);
		r.addTeam(t3);
		r.addTeam(t4);
		r.addTeam(t5);
		r.addTeam(t6);
		
		r.setDates(new Date(2023, 5, 13), new Date(2023, 5, 30));
		
		r.startTournament();
		
		System.out.println("Teams in tourny: " + r.getTeams());
		System.out.println();
		System.out.println("All rounds matches: " + r.getAllRounds());
		System.out.println();
		System.out.println("First match: " + r.getFirstUnplayedMatch());
		
		System.out.println();
		Match m = r.getFirstUnplayedMatch();
		m.addGoals2(4);
		m.matchDone();
		System.out.println();
		System.out.println("Teams standings:");
		System.out.println(r.teamsWithPoints());
		
		handleNextMatch(r, 3, 3);
		handleNextMatch(r, 4, 2);
		handleNextMatch(r, 1, 1);
		handleNextMatch(r, 3, 2);
		handleNextMatch(r, 2, 4);
		handleNextMatch(r, 4, 2);
		handleNextMatch(r, 2, 4);
		handleNextMatch(r, 6, 2);
		handleNextMatch(r, 2, 2);
		handleNextMatch(r, 4, 2);
		handleNextMatch(r, 2, 2);
		handleNextMatch(r, 4, 2);
		handleNextMatch(r, 2, 2);
		handleNextMatch(r, 4, 2);
		handleNextMatch(r, 4, 2);
		
		System.out.println(r.GetWinner());
	}
	
	public static void handleNextMatch(Tournament t, int p1, int p2) {
		System.out.println("Next match: " + t.getFirstUnplayedMatch());
		
		System.out.println();
		Match m = t.getFirstUnplayedMatch();
		if(m == null) {
			System.out.println("Tournament Done");
			return;
		}
		m.addGoals1(p1);
		m.addGoals2(p2);
		m.matchDone();
		System.out.println();
		System.out.println("Teams standings:");
		System.out.println(t.teamsWithPoints());
	}
	
	public static void bracketsTest() {
		Tournament b = new Tournament("Test", new Game("Football", 1), Tournament.Type.BRACKETS, 7);
		
		Student s1 = new Student(123 ,"Ghassan");
		Student s2 = new Student(124 ,"Mohammad");
		Student s3 = new Student(125 ,"Salem");
		Student s4 = new Student(126 ,"Ahmad");
		Student s5 = new Student(127 ,"Ali");
		
		Team t1 = new Team("Tigers", 1, s1);
		Team t2 = new Team("Fighters", 1, s2);
		Team t3 = new Team("Bees", 1, s3);
		Team t4 = new Team("Cats", 1, s4);
		Team t5 = new Team("Dogs", 1, s5);
		
		b.addTeam(t1);
		b.addTeam(t2);
		b.addTeam(t3);
		b.addTeam(t4);
		b.addTeam(t5);
		
		b.setDates(new Date(2023, 5, 13), new Date(2023, 5, 30));
		
		b.startTournament();
		
		System.out.println("Teams in tourny: " + b.getTeams());
		System.out.println();
		System.out.println("All rounds matches: " + b.getAllRounds());
		System.out.println();
		System.out.println("First match: " + b.getFirstUnplayedMatch());
		System.out.println();
		
		Match m = b.getFirstUnplayedMatch();
		m.addGoals1(4);
		m.matchDone();
		
		b.checkRoundEndBrackets();
		
		System.out.println("All rounds matches after match 1: " + b.getAllRounds());
		
		m = b.getFirstUnplayedMatch();
		m.addGoals2(4);
		m.matchDone();
		
		b.checkRoundEndBrackets();
		
		System.out.println("All rounds matches after match 2: " + b.getAllRounds());
		
		m = b.getFirstUnplayedMatch();
		m.addGoals2(4);
		m.matchDone();
		
		b.checkRoundEndBrackets();
		System.out.println(b.checkTournamentDone());
		System.out.println("All rounds matches after match 3: " + b.getAllRounds());
		m = b.getFirstUnplayedMatch();
		m.addGoals2(4);
		m.matchDone();
		b.checkRoundEndBrackets();
		
		System.out.println(b.checkTournamentDone());
	}
}

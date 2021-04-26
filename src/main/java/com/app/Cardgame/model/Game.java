package com.app.Cardgame.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

	private User u1;
	private User u2;
	private List<Turn> turns;
	private List<String> stats;
	private List<Card> wonCards;
	private HashMap<User, Integer> points;

	public Game() {
		stats = new ArrayList();

	}

	public Game(User u1, User u2) {
		this.u1 = u1;
		this.u2 = u2;
		stats = new ArrayList();
		wonCards = new ArrayList();
		turns = new ArrayList();
		points = new HashMap(Map.of(u1, 0, u2, 0));
		// points.put(u1, 0);
		// points.put(u2, 0);
	}

	public List<Turn> getTurns() {
		return turns;
	}

	public void setTurns(List<Turn> turns) {
		this.turns = turns;
	}

	public List<String> getStats() {
		return stats;
	}

	public void setStats(List<String> stats) {
		this.stats = stats;
	}

	public User getU1() {
		return u1;
	}

	public void setU1(User u1) {
		this.u1 = u1;
	}

	public User getU2() {
		return u2;
	}

	public void setU2(User u2) {
		this.u2 = u2;
	}

	public List<Card> getWonCards() {
		return wonCards;
	}

	public void setWonCards(List<Card> wonCards) {
		this.wonCards = wonCards;
	}

	public HashMap<User, Integer> getPoints() {
		return points;
	}

	public void setPoints(HashMap<User, Integer> points) {
		this.points = points;
	}
}

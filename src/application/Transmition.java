package application;

import java.util.HashMap;

public class Transmition {
	String from;
	String to;
	String symbol;
	String Final;
	String Start;
	public Transmition() {
		
	}
	public String getFinal() {
		return Final;
	}
	public void setFinal(String final1) {
		Final = final1;
	}
	public String getStart() {
		return Start;
	}
	public void setStart(String start) {
		Start = start;
	}
	
	public Transmition(String from, String to, String symbol) {
	
		this.from = from;
		this.to = to;
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return "Transmition [from=" + from + ", to=" + to + ", symbol=" + symbol + "]";
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	

}

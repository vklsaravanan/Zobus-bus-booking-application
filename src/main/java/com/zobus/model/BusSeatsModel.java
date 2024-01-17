package com.zobus.model;

public class BusSeatsModel {
    private int seatId;
    private int busId;
    private String seatNumber;
    private Side side;
    private Deck deck;
    private int columnNum;
    private int rowNum;
    private boolean sleeper;
    
    private PassengersModel passanger;


	public enum Side {
        L, R
    }

    public enum Deck {
        UPPER, LOWER
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public boolean isSleeper() {
        return sleeper;
    }

    public void setSleeper(boolean sleeper) {
        this.sleeper = sleeper;
    }
    

    public PassengersModel getPassanger() {
		return passanger;
	}

	public void setPassanger(PassengersModel passanger) {
		this.passanger = passanger;
	}
}
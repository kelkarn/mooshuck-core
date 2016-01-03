package org.nkelkar.mooshuck;

/**
 * @author: Nishant Kelkar
 * @date: 11/2/15
 */
public class Cell {
    byte accessibility;
    int score = -1;

    public Cell(byte accessibility) {
        this.accessibility = accessibility;
    }

    public Cell() {
        // set accessibility level to: ALL ACCESSIBLE
        this.accessibility = 0b00000000;
    }

    public void blockNorth() {
        this.accessibility = (byte)(this.accessibility | 0b1000);
    }

    public void blockEast() {
        this.accessibility = (byte)(this.accessibility | 0b0100);
    }

    public void blockWest() {
        this.accessibility = (byte)(this.accessibility | 0b0010);
    }

    public void blockSouth() {
        this.accessibility = (byte)(this.accessibility | 0b0001);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public boolean isBlockedNorth() {
        return (this.accessibility & 0b1000) == 8;
    }

    public boolean isBlockedEast() {
        return (this.accessibility & 0b0100) == 4;
    }

    public boolean isBlockedWest() {
        return (this.accessibility & 0b0010) == 2;
    }

    public boolean isBlockedSouth() {
        return (this.accessibility & 0b0001) == 1;
    }
}

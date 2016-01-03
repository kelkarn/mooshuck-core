package org.nkelkar.mooshuck;

/**
 * @author: Nishant Kelkar
 * @date: 11/2/15
 */
public class BellmanFloodfillAlgorithm {

    Cell[][] maze;
    int targetX, targetY;
    int width, height;

    public BellmanFloodfillAlgorithm(int width, int height,
                                     int targetX, int targetY) {
        maze = new Cell[height][width];
        this.targetX = targetX;
        this.targetY = targetY;
        this.width = width;
        this.height = height;
    }

    public void updateMaze() {
        resetMaze();
        update(targetX, targetY, -1);
    }

    /**
     * Update routine for resetting cell score values based
     * on current wall information.
     * Note: top-left corner is ORIGIN.
     * @param x
     * @param y
     * @param prevScore
     */
    private void update(int x, int y, int prevScore) {
        if(y < 0 || y >= height || x < 0 || x >= width) {
            return;
        }
        boolean isFringeN = false, isFringeE = false;
        boolean isFringeW = false, isFringeS = false;

        // check whether you're on a border cell...
        if(y-1 < 0) {
            isFringeN = true;
        } else if(y+1 >= height) {
            isFringeS = true;
        }

        if(x-1 < 0) {
            isFringeW = true;
        } else if(x+1 >= width) {
            isFringeE = true;
        }

        maze[y][x].setScore(prevScore + 1);

        // update cells in dir N, E, W, S based on
        // if it's blocked by wall, is fringe, or was
        // visited before
        if(!maze[y][x].isBlockedNorth()) {
            if(!isFringeN && maze[y-1][x].getScore() != -1) {
                update(x, y-1, prevScore + 1);
            }
        }

        if(!maze[y][x].isBlockedEast()) {
            if(!isFringeE && maze[y][x+1].getScore() != -1) {
                update(x+1, y, prevScore + 1);
            }
        }

        if(!maze[y][x].isBlockedWest()) {
            if(!isFringeW && maze[y][x-1].getScore() != -1) {
                update(x-1, y, prevScore + 1);
            }
        }

        if(!maze[y][x].isBlockedSouth()) {
            if(!isFringeS && maze[y+1][x].getScore() != -1) {
                update(x, y+1, prevScore + 1);
            }
        }
    }

    public void resetMaze() {
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                maze[i][j].setScore(-1);
            }
        }
    }

    public void updateCell(int x, int y, byte update) {
        switch (update) {
            case 0b1000:
                maze[y][x].blockNorth();
                break;
            case 0b0100:
                maze[y][x].blockEast();
                break;
            case 0b0010:
                maze[y][x].blockWest();
                break;
            case 0b0001:
                maze[y][x].blockSouth();
                break;
        }
    }
}

package algorithm;

import algorithm.domain.LineList;
import algorithm.domain.Minutia;

import java.util.ArrayList;
import java.util.List;

public class FindMinutia1 extends Algorithm<LineList> {

    private boolean[][] mSKMatrix;
    private int height;
    private int width;

    private List<Minutia> minutiaEnd;
    private List<Minutia> minutiaBranch;

    public FindMinutia1(boolean[][] skMatrix) {
        this.mSKMatrix = skMatrix;
        this.height = skMatrix[0].length;
        this.width = skMatrix.length;

        this.minutiaEnd = new ArrayList<Minutia>();
        this.minutiaBranch = new ArrayList<Minutia>();
    }


    public List<Minutia> getMinutiaEnd() {
        return minutiaEnd;
    }

    public List<Minutia> getMinutiaBranch() {
        return minutiaBranch;
    }

    private int checkThisPoints(int x, int y) {
        int count = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (mSKMatrix[i][j] == false) {
                    count++;
                }
            }
        }
        return count - 1;
    }

    private void findCheckPoint() {
        int t = 0;
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                if (mSKMatrix[i][j] == true) {
                    continue;
                }
                t = checkThisPoints(i, j);
                if (t == 1) {
                    this.minutiaEnd.add(new Minutia(i, j));
                } else if (t == 3) {
                    this.minutiaBranch.add(new Minutia(i, j));
                }
            }
        }
    }

    @Override
    public LineList execute() {
        findCheckPoint();
        return new LineList(minutiaEnd, minutiaBranch);
    }
}



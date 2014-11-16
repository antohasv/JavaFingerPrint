package algorithm.domain;


import java.util.List;

public class LineList {
    private List<Minutia> minutiaEnd;
    private List<Minutia> minutiaBranch;

    public LineList(List<Minutia> minutiaEnd, List<Minutia> minutiaBranch) {
        this.minutiaEnd = minutiaEnd;
        this.minutiaBranch = minutiaBranch;
    }

    public List<Minutia> getMinutiaEnd() {
        return minutiaEnd;
    }

    public List<Minutia> getMinutiaBranch() {
        return minutiaBranch;
    }

}

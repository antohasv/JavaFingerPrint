package algorithm;

import algorithm.domain.LineList;
import image.ImageManager;

public class FingerPrint extends Algorithm<LineList> {
    private ImageManager imageManager;

    public FingerPrint(String linkToImage) {
         imageManager = new ImageManager(linkToImage);
    }

    public LineList execute() {
        boolean[][] binaryMatrix = new Binarization(imageManager.getImage()).executeWithTime();
        boolean[][] skMatrix = new Skeletization(binaryMatrix).executeWithTime();
        LineList lineList = new FindMinutia1(skMatrix).executeWithTime();
        return new DeleteNoise(lineList).executeWithTime();
    }
}

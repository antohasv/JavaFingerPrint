
public class Main {

    public static final String LINK_TO_IMG = "img/img1.png";


    public static void main(String[] args) {
        ImageManager imageManager = new ImageManager(LINK_TO_IMG);

        long start = System.currentTimeMillis();
        int[][] binaryMatrix = Binarization.getBinaryMatrix(imageManager.getImage());
        long result = System.currentTimeMillis() - start;
        System.out.println("Binarization: " + result + " ms.");

        start = System.currentTimeMillis();

        Skeletization sk = new Skeletization(binaryMatrix);
        int skMatrix[][] = sk.execute();

        result = System.currentTimeMillis() - start;
        System.out.println("Skeletization: " + result + " ms.");

        start = System.currentTimeMillis();

        FindMinutia1 findMinutia1 = new FindMinutia1(skMatrix);
        findMinutia1.findCheckPoint();

        new DeleteNoise(findMinutia1.getMinutiaEnd(), findMinutia1.getMinutiaBranch()).execute();


//        FindMinutia findMinutia = new FindMinutia(imageManager.getImage(), skMatrix);
//        findMinutia.perform();

//        ImageManager imageManager1 = new ImageManager(findMinutia.getmImage());

        //imageManager1.showImage();
//        imageManager1.save("img/q1.png");

        System.out.println("FindMinutia: " + result + " ms.");

//        try {
//            Skeletization sk = new Skeletization(getBinaryMatrix(bufferedImage));
//            //showImage(sk.getBinImg());
//

//
//            System.out.print("Branch:" + findMinutia.getMinutiaBranch().size() + " End:" + findMinutia.getMinutiaEnd().size());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


//    private int[][] getArrayByImg() {
//        int[][] arr = new int[binImg.getWidth()][binImg.getHeight()];
//        for (int i = 0; i < binImg.getWidth(); i++) {
//            for (int j = 0; j < binImg.getHeight(); j++) {
//                arr[i][j] = binImg.getRGB(i, j);
//            }
//        }
//        return arr;
//    }




}

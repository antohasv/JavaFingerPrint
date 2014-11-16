import algorithm.FingerPrint;

public class Main {
    public static final String LINK_TO_IMG = "img/tmp.png";

    public static void main(String[] args) {
        String imgLink = LINK_TO_IMG;
        if (args != null && args.length > 0) {
            imgLink = args[0];
        }

        new FingerPrint(LINK_TO_IMG).executeWithTime();
    }
}

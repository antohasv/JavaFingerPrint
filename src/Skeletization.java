import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Skeletization {

    private BufferedImage binImg;

    private int[][] matrix;

    public Skeletization(BufferedImage binImg) {
        this.binImg = binImg;
        int[][] arr = getArrayByImg();
        long start = System.currentTimeMillis();
        matrix = performSkeletic(arr);

        long end = System.currentTimeMillis();

        System.out.println(end - start);

//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                if (matrix[i][j] == 1) {
//                    binImg.setRGB(i, j, Color.WHITE.getRGB());
//                } else {
//                    binImg.setRGB(i, j, Color.BLACK.getRGB());
//                }
//            }
//        }
    }

    public BufferedImage getBinImg() {
        return binImg;
    }

    private int[][] getArrayByImg() {
        int[][] arr = new int[binImg.getWidth()][binImg.getHeight()];
        for (int i = 0; i < binImg.getWidth(); i++) {
            for (int j = 0; j < binImg.getHeight(); j++) {
                arr[i][j] = binImg.getRGB(i, j);
            }
        }
        return arr;
    }

    private int[][] performSkeletic(int[][] l) {
        int count = 1;
        int cc = 1;
        while (count != 0) {
            count = del(l);
            l = del_noise(l);
            cc++;
        }
        return l;
    }

    // Удаляем пиксель по основному набору
    private int del(int[][] wArr)
    {
        int count = 0;
        for (int i = 1; i < binImg.getHeight() - 1; i++)
            for (int j = 1; j < binImg.getWidth() - 1; j++)
            {
                if (wArr[j][i] == 0)
                if (deletable(wArr, j, i))
                {
                    wArr[j][i] = 1;
                    count++;
                }
            }
        return count;
    }

    // Получаем участок размером 3x3 и передаём на проверку по основному шаблону
    private boolean deletable(int[][] arr, int x, int y)
    {
        ArrayList a = new ArrayList();
        for (int i = y - 1; i < y + 2; i++)
        {
            for (int j = x - 1; j < x + 2; j++)
            {
                a.add(arr[j][i]);
            }
        }
        return check(a);
    }

    // Принадлежность к основным шаблонам
    private boolean check(ArrayList<Integer> a)
    {
        if (a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 0 && a.get(4) == 0 && a.get(5) == 1 && a.get(7) == 0)
            return true;
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 0 && a.get(7) == 0)
            return true;
        if (a.get(1) == 0 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 0 && a.get(6) == 1 && a.get(7) == 1)
            return true;
        if (a.get(1) == 0 && a.get(3) == 0 && a.get(4) == 0 && a.get(5) == 1 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 0 && a.get(4) == 0 && a.get(5) == 0 && a.get(7) == 0)
            return true;
        if (a.get(0) == 1 && a.get(1) == 0 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 0 && a.get(6) == 1 && a.get(7) == 0)
            return true;
        if (a.get(1) == 0 && a.get(3) == 0 && a.get(4) == 0 && a.get(5) == 0 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        if (a.get(1) == 0 && a.get(2) == 1 && a.get(3) == 0 && a.get(4) == 0 && a.get(5) == 1 && a.get(7) == 0 && a.get(8) == 1)
            return true;

        return false;
    }



    // Удаляем пиксель по шумовому набору
    private int[][] del_noise(int[][] wArr)
    {
        for (int i = 1; i < binImg.getHeight() - 1; i++)
            for (int j = 1; j < binImg.getWidth() - 1; j++)
            {
                if (wArr[j][i] == 0)
                if (deletable_noise(wArr, j, i))
                {
                    wArr[j][i] = 1;
                }
            }

        return wArr;
    }

    private boolean deletable_noise(int[][] arr, int x, int y) {
        ArrayList a = new ArrayList();
        for (int i = y - 1; i < y + 2; i++)
        {
            for (int j = x - 1; j < x + 2; j++)
            {
                a.add(arr[j][i]);
            }
        }
        return noise(a);
    }

    // Принадлежность к шумам
    private boolean noise(ArrayList<Integer> a)
    {
        // 1
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        // 2
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 1 && a.get(7) == 0 && a.get(8) == 0)
            return true;
        // 3
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 0 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 0 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        // 4
        if (a.get(0) == 0 && a.get(1) == 0 && a.get(2) == 1 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        // 5
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 0 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 0 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        // 6
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 0 && a.get(7) == 0 && a.get(8) == 1)
            return true;
        // 7
        if (a.get(0) == 0 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 0 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        // 8
        if (a.get(0) == 1 && a.get(1) == 0 && a.get(2) == 0 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        // 9
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 0 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 0)
            return true;
        // 10
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 0 && a.get(7) == 0 && a.get(8) == 0)
            return true;
        // 11
        if (a.get(0) == 0 && a.get(1) == 1 && a.get(2) == 1 && a.get(3) == 0 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 0 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        // 12
        if (a.get(0) == 0 && a.get(1) == 0 && a.get(2) == 0 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 1 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 1)
            return true;
        // 13
        if (a.get(0) == 1 && a.get(1) == 1 && a.get(2) == 0 && a.get(3) == 1 && a.get(4) == 0 && a.get(5) == 0 && a.get(6) == 1 && a.get(7) == 1 && a.get(8) == 0)
            return true;

        return false;
    }
}

import java.util.ArrayList;

public class Skeletization {

    private int[][] mBinMatrix;
    private int mHeight;
    private int mWidht;

    public Skeletization(int[][] binMatrix) {
        this.mBinMatrix = binMatrix;
        this.mWidht = binMatrix.length;
        this.mHeight = binMatrix[0].length;

        execute();
    }

    private int[][] execute() {
        int count = 1;
        int cc = 1;
        while (count != 0) {
            count = deletePixels();
            delNoise();
            cc++;
        }
        return mBinMatrix;
    }

    // Удаляем пиксель по основному набору
    private int deletePixels()
    {
        int count = 0;
        for (int i = 1; i < mHeight - 1; i++)
            for (int j = 1; j < mWidht - 1; j++)
            {
                if (mBinMatrix[j][i] == 0 && deletable(j, i))
                {
                    mBinMatrix[j][i] = 1;
                    count++;
                }
            }
        return count;
    }

    // Получаем участок размером 3x3 и передаём на проверку по основному шаблону
    private boolean deletable(int x, int y)
    {
        ArrayList a = new ArrayList();
        for (int i = y - 1; i < y + 2; i++)
        {
            for (int j = x - 1; j < x + 2; j++)
            {
                a.add(mBinMatrix[j][i]);
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
    private void delNoise()
    {
        for (int i = 1; i < mHeight - 1; i++)
            for (int j = 1; j < mWidht - 1; j++)
            {
                if (mBinMatrix[j][i] == 0 && deletableNoise(j, i))
                {
                    mBinMatrix[j][i] = 1;
                }
            }
    }

    private boolean deletableNoise(int x, int y) {
        ArrayList a = new ArrayList();
        for (int i = y - 1; i < y + 2; i++)
        {
            for (int j = x - 1; j < x + 2; j++)
            {
                a.add(mBinMatrix[j][i]);
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

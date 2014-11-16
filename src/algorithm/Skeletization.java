package algorithm;

public class Skeletization extends Algorithm<boolean[][]> {

    private boolean[][] mBinMatrix;
    private int mHeight;
    private int mWidht;

    public Skeletization(boolean[][] binMatrix) {
        this.mBinMatrix = binMatrix;
        this.mWidht = binMatrix.length;
        this.mHeight = binMatrix[0].length;
    }

    @Override
    public boolean[][] execute() {
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
                if (mBinMatrix[j][i] == false && deletable(j, i))
                {
                    mBinMatrix[j][i] = true;
                    count++;
                }
            }
        return count;
    }

    // Получаем участок размером 3x3 и передаём на проверку по основному шаблону
    private boolean deletable(int x, int y)
    {
        int k = 0;
        boolean arr[] = new boolean[9];
        for (int i = y - 1; i < y + 2; i++)
        {
            for (int j = x - 1; j < x + 2; j++, k++)
            {
                arr[k] = mBinMatrix[j][i];
            }
        }
        return check(arr);
    }

    // Принадлежность к основным шаблонам
    private boolean check(boolean[] a)
    {
        if (a[1] == true && a[2] == true && a[3] == false && a[4] == false && a[5] == true && a[7] == false)
            return true;
        if (a[0] == true && a[1] == true && a[3] == true && a[4] == false && a[5] == false && a[7] == false)
            return true;
        if (a[1] == false && a[3] == true && a[4] == false && a[5] == false && a[6] == true && a[7] == true)
            return true;
        if (a[1] == false && a[3] == false && a[4] == false && a[5] == true && a[7] == true && a[8] == true)
            return true;
        if (a[0] == true && a[1] == true && a[2] == true && a[3] == false && a[4] == false && a[5] == false && a[7] == false)
            return true;
        if (a[0] == true && a[1] == false && a[3] == true && a[4] == false && a[5] == false && a[6] == true && a[7] == false)
            return true;
        if (a[1] == false && a[3] == false && a[4] == false && a[5] == false && a[6] == true && a[7] == true && a[8] == true)
            return true;
        if (a[1] == false && a[2] == true && a[3] == false && a[4] == false && a[5] == true && a[7] == false && a[8] == true)
            return true;

        return false;
    }

    // Удаляем пиксель по шумовому набору
    private void delNoise()
    {
        for (int i = 1; i < mHeight - 1; i++)
            for (int j = 1; j < mWidht - 1; j++)
            {
                if (mBinMatrix[j][i] == false && deletableNoise(j, i))
                {
                    mBinMatrix[j][i] = true;
                }
            }
    }

    private boolean deletableNoise(int x, int y) {
        int k = 0;
        boolean arr[] = new boolean[9];
        for (int i = y - 1; i < y + 2; i++)
        {
            for (int j = x - 1; j < x + 2; j++, k++)
            {
                arr[k] = mBinMatrix[j][i];
            }
        }
        return noise(arr);
    }

    // Принадлежность к шумам
    private boolean noise(boolean[] a)
    {
        // true
        if (a[0] == true && a[1] == true && a[2] == true && a[3] == true && a[4] == false && a[5] == true && a[6] == true && a[7] == true && a[8] == true)
            return true;
        // 2
        if (a[0] == true && a[1] == true && a[2] == true && a[3] == true && a[4] == false && a[5] == true && a[6] == true && a[7] == false && a[8] == false)
            return true;
        // 3
        if (a[0] == true && a[1] == true && a[2] == true && a[3] == false && a[4] == false && a[5] == true && a[6] == false && a[7] == true && a[8] == true)
            return true;
        // 4
        if (a[0] == false && a[1] == false && a[2] == true && a[3] == true && a[4] == false && a[5] == true && a[6] == true && a[7] == true && a[8] == true)
            return true;
        // 5
        if (a[0] == true && a[1] == true && a[2] == false && a[3] == true && a[4] == false && a[5] == false && a[6] == true && a[7] == true && a[8] == true)
            return true;
        // 6
        if (a[0] == true && a[1] == true && a[2] == true && a[3] == true && a[4] == false && a[5] == true && a[6] == false && a[7] == false && a[8] == true)
            return true;
        // 7
        if (a[0] == false && a[1] == true && a[2] == true && a[3] == false && a[4] == false && a[5] == true && a[6] == true && a[7] == true && a[8] == true)
            return true;
        // 8
        if (a[0] == true && a[1] == false && a[2] == false && a[3] == true && a[4] == false && a[5] == true && a[6] == true && a[7] == true && a[8] == true)
            return true;
        // 9
        if (a[0] == true && a[1] == true && a[2] == true && a[3] == true && a[4] == false && a[5] == false && a[6] == true && a[7] == true && a[8] == false)
            return true;
        // truefalse
        if (a[0] == true && a[1] == true && a[2] == true && a[3] == true && a[4] == false && a[5] == true && a[6] == false && a[7] == false && a[8] == false)
            return true;
        // truetrue
        if (a[0] == false && a[1] == true && a[2] == true && a[3] == false && a[4] == false && a[5] == true && a[6] == false && a[7] == true && a[8] == true)
            return true;
        // true2
        if (a[0] == false && a[1] == false && a[2] == false && a[3] == true && a[4] == false && a[5] == true && a[6] == true && a[7] == true && a[8] == true)
            return true;
        // true3
        if (a[0] == true && a[1] == true && a[2] == false && a[3] == true && a[4] == false && a[5] == false && a[6] == true && a[7] == true && a[8] == false)
            return true;

        return false;
    }
}

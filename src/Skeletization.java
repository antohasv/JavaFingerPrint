
public class Skeletization {

    private int[][] mBinMatrix;
    private int mHeight;
    private int mWidht;

    public Skeletization(int[][] binMatrix) {
        this.mBinMatrix = binMatrix;
        this.mWidht = binMatrix.length;
        this.mHeight = binMatrix[0].length;
    }

    public int[][] execute() {
        int count = 1;
        int cc = 1;
        while (count != 0) {
            count = deletePixels();
            delNoise();
            System.out.println(count);
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
                    //mBufferedImage.setRGB(j, i, 1);
                    count++;
                }
            }
        return count;
    }

    // Получаем участок размером 3x3 и передаём на проверку по основному шаблону
    private boolean deletable(int x, int y)
    {
        int k = 0;
        int arr[] = new int[9];
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
    private boolean check(int[] a)
    {
        if (a[1] == 1 && a[2] == 1 && a[3] == 0 && a[4] == 0 && a[5] == 1 && a[7] == 0)
            return true;
        if (a[0] == 1 && a[1] == 1 && a[3] == 1 && a[4] == 0 && a[5] == 0 && a[7] == 0)
            return true;
        if (a[1] == 0 && a[3] == 1 && a[4] == 0 && a[5] == 0 && a[6] == 1 && a[7] == 1)
            return true;
        if (a[1] == 0 && a[3] == 0 && a[4] == 0 && a[5] == 1 && a[7] == 1 && a[8] == 1)
            return true;
        if (a[0] == 1 && a[1] == 1 && a[2] == 1 && a[3] == 0 && a[4] == 0 && a[5] == 0 && a[7] == 0)
            return true;
        if (a[0] == 1 && a[1] == 0 && a[3] == 1 && a[4] == 0 && a[5] == 0 && a[6] == 1 && a[7] == 0)
            return true;
        if (a[1] == 0 && a[3] == 0 && a[4] == 0 && a[5] == 0 && a[6] == 1 && a[7] == 1 && a[8] == 1)
            return true;
        if (a[1] == 0 && a[2] == 1 && a[3] == 0 && a[4] == 0 && a[5] == 1 && a[7] == 0 && a[8] == 1)
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
        int k = 0;
        int arr[] = new int[9];
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
    private boolean noise(int[] a)
    {
        // 1
        if (a[0] == 1 && a[1] == 1 && a[2] == 1 && a[3] == 1 && a[4] == 0 && a[5] == 1 && a[6] == 1 && a[7] == 1 && a[8] == 1)
            return true;
        // 2
        if (a[0] == 1 && a[1] == 1 && a[2] == 1 && a[3] == 1 && a[4] == 0 && a[5] == 1 && a[6] == 1 && a[7] == 0 && a[8] == 0)
            return true;
        // 3
        if (a[0] == 1 && a[1] == 1 && a[2] == 1 && a[3] == 0 && a[4] == 0 && a[5] == 1 && a[6] == 0 && a[7] == 1 && a[8] == 1)
            return true;
        // 4
        if (a[0] == 0 && a[1] == 0 && a[2] == 1 && a[3] == 1 && a[4] == 0 && a[5] == 1 && a[6] == 1 && a[7] == 1 && a[8] == 1)
            return true;
        // 5
        if (a[0] == 1 && a[1] == 1 && a[2] == 0 && a[3] == 1 && a[4] == 0 && a[5] == 0 && a[6] == 1 && a[7] == 1 && a[8] == 1)
            return true;
        // 6
        if (a[0] == 1 && a[1] == 1 && a[2] == 1 && a[3] == 1 && a[4] == 0 && a[5] == 1 && a[6] == 0 && a[7] == 0 && a[8] == 1)
            return true;
        // 7
        if (a[0] == 0 && a[1] == 1 && a[2] == 1 && a[3] == 0 && a[4] == 0 && a[5] == 1 && a[6] == 1 && a[7] == 1 && a[8] == 1)
            return true;
        // 8
        if (a[0] == 1 && a[1] == 0 && a[2] == 0 && a[3] == 1 && a[4] == 0 && a[5] == 1 && a[6] == 1 && a[7] == 1 && a[8] == 1)
            return true;
        // 9
        if (a[0] == 1 && a[1] == 1 && a[2] == 1 && a[3] == 1 && a[4] == 0 && a[5] == 0 && a[6] == 1 && a[7] == 1 && a[8] == 0)
            return true;
        // 10
        if (a[0] == 1 && a[1] == 1 && a[2] == 1 && a[3] == 1 && a[4] == 0 && a[5] == 1 && a[6] == 0 && a[7] == 0 && a[8] == 0)
            return true;
        // 11
        if (a[0] == 0 && a[1] == 1 && a[2] == 1 && a[3] == 0 && a[4] == 0 && a[5] == 1 && a[6] == 0 && a[7] == 1 && a[8] == 1)
            return true;
        // 12
        if (a[0] == 0 && a[1] == 0 && a[2] == 0 && a[3] == 1 && a[4] == 0 && a[5] == 1 && a[6] == 1 && a[7] == 1 && a[8] == 1)
            return true;
        // 13
        if (a[0] == 1 && a[1] == 1 && a[2] == 0 && a[3] == 1 && a[4] == 0 && a[5] == 0 && a[6] == 1 && a[7] == 1 && a[8] == 0)
            return true;

        return false;
    }
}

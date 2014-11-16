import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FindMinutia {

    private int[][] mSKMatrix;
    private int height;
    private int width;

    private List<Minutia> minutiaEnd;
    private List<Minutia> minutiaBranch;

    private BufferedImage mImage;


    public FindMinutia(BufferedImage image, int[][] skMatrix) {
        this.mImage = image;
        this.mSKMatrix = skMatrix;
        this.width = skMatrix.length;
        this.height = skMatrix[0].length;

        this.minutiaEnd = new ArrayList<Minutia>();
        this.minutiaBranch = new ArrayList<Minutia>();
    }

    public void perform() {
        findCheckPoint();
        System.out.println("Branch:" + minutiaBranch.size() + " End:" + minutiaEnd.size());
        //delNoisePoints();
        System.out.println("Branch:" + minutiaBranch.size() + " End:" + minutiaEnd.size());
    }

    public BufferedImage getmImage() {
        return mImage;
    }

    public List<Minutia> getMinutiaEnd() {
        return minutiaEnd;
    }

    public List<Minutia> getMinutiaBranch() {
        return minutiaBranch;
    }


    private void findCheckPoint()
    {

        for (int j = 1; j < width - 1; j++)
        {
            for (int i = 1; i < height - 1; i++)
            {
            /* Окончания */
                // Проверка на первый шаблон
                if (mSKMatrix[j - 1][i - 1] == 0 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 1 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(i,  j, 270, true));
                    mImage.setRGB(i, j, Color.RED.getRGB());

                }


                // Второй шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 1 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(i,  j, 225, true));
                    mImage.setRGB(i, j, Color.RED.getRGB());

                }


                // Третий шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 1 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(i,  j, 180, true));
                    mImage.setRGB(i, j, Color.RED.getRGB());

                }


                // Четвёртый шаблон
                if (mSKMatrix[j - 1][ i - 1] == 1 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(i,  j, 135, true));
                    mImage.setRGB(i, j, Color.RED.getRGB());

                }


                // Пятый шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 1 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 01 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(i,  j, 90, true));
                    mImage.setRGB(i, j, Color.RED.getRGB());

                }

                // Шестой шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 1
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(i,  j, 45, true));
                    mImage.setRGB(i, j, Color.RED.getRGB());

                }


                // Седьмой шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 1
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(i,  j, 0, true));
                    mImage.setRGB(i, j, Color.RED.getRGB());

                }

                // Восьмой шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 1)
                {
                    minutiaEnd.add(new Minutia(i,  j, 315, true));
                    mImage.setRGB(i, j, Color.RED.getRGB());

                }

            /* Раздвоения */
                // Проверка на первый шаблон
                if (mSKMatrix[j - 1][ i - 1] == 1 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 1
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(i,  j, 90, false));
                    mImage.setRGB(i, j, Color.ORANGE.getRGB());
                }

                // Второй шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 1 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 1
                        && mSKMatrix[j + 1][ i - 1] == 1 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(i,  j, 45, false));
                    mImage.setRGB(i, j, Color.ORANGE.getRGB());

                }

                // Третий шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 1
                        && mSKMatrix[j][ i - 1] == 1 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 1)
                {
                    minutiaBranch.add(new Minutia(i,  j, 0, false));
                    mImage.setRGB(i, j, Color.ORANGE.getRGB());


                }


                // Четвёртый шаблон
                if (mSKMatrix[j - 1][ i - 1] == 1 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 1
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 1 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(i,  j, 315, false));
                    mImage.setRGB(i, j, Color.ORANGE.getRGB());
                }

                // Пятый шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 1 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 1 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 1)
                {
                    minutiaBranch.add(new Minutia(i,  j, 270, false));
                    mImage.setRGB(i, j, Color.ORANGE.getRGB());

                }

                // Шестой шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 1
                        && mSKMatrix[j][ i - 1] == 1 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 1 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(i,  j, 225, false));
                    mImage.setRGB(i, j, Color.ORANGE.getRGB());

                }


                // Седьмой шаблон
                if (mSKMatrix[j - 1][ i - 1] == 1 && mSKMatrix[j - 1][ i] == 0 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 0 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 1
                        && mSKMatrix[j + 1][ i - 1] == 1 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(i,  j, 180, false));
                    mImage.setRGB(i, j, Color.ORANGE.getRGB());

                }

                // Восьмой шаблон
                if (mSKMatrix[j - 1][ i - 1] == 0 && mSKMatrix[j - 1][ i] == 1 && mSKMatrix[j - 1][ i + 1] == 0
                        && mSKMatrix[j][ i - 1] == 1 && mSKMatrix[j][ i] == 1 && mSKMatrix[j][ i + 1] == 0
                        && mSKMatrix[j + 1][ i - 1] == 0 && mSKMatrix[j + 1][ i] == 0 && mSKMatrix[j + 1][ i + 1] == 1)
                {
                    minutiaBranch.add(new Minutia(i,  j, 135, false));
                    mImage.setRGB(i, j, Color.ORANGE.getRGB());

                }
            }
        }
    }

    // Удаляем ложные минюции
    private void delNoisePoints()
    {
        ArrayList ep = new ArrayList(minutiaEnd);
        ArrayList bp = new ArrayList(minutiaBranch);

        Minutia em, em1, bm, bm1;

        // Идём по списку минюций
        // сравниваем расстояние между окончанием и раздвоением
        // если оно меньше некоторой величины (здесь 10)
        // то удаляем
        for (Minutia oep : minutiaEnd)
        {
            em = (Minutia)oep;
            for (Minutia obp : minutiaBranch)
            {
                if ((Math.abs(em.getX() - obp.getX()) < 10) && (Math.abs(em.getY() - obp.getY()) < 10))
                {
                    ep.remove(em);
                    bp.remove(obp);
                    break;
                }
            }
        }

        // Идём по списку окончаний
        // если расстояние между двумя окончаниями
        // меньше заданной величины - удаляем
        for (Minutia oep : minutiaEnd)
        {
            for(Minutia oep1 : minutiaEnd)
            {
                if (oep != oep1)
                    if ((Math.abs(oep.getX() - oep1.getX()) < 15) && (Math.abs(oep1.getY() - oep.getY()) < 15))
                    {
                        ep.remove(oep1);
                    }
            }
        }

        // Идём по списку раздвоений
        // если расстояние между двумя раздвоениями
        // меньше заданной величины - удаляем
        for (Minutia obp : minutiaBranch)
        {
            for(Minutia obp1 : minutiaBranch)
            {
                if (obp != obp1)
                    if ((Math.abs(obp1.getX() - obp.getX()) < 15) && (Math.abs(obp1.getY() - obp.getY()) < 15))
                    {
                        bp.remove(obp1);
                    }
            }
        }

        minutiaEnd = ep;
        minutiaBranch = bp;
    }

}

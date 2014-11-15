import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FindMinutia {

    private BufferedImage skelImg;

    private List<Minutia> minutiaEnd;
    private List<Minutia> minutiaBranch;


    public FindMinutia(BufferedImage skelImg) {
        this.skelImg = skelImg;
        minutiaEnd = new ArrayList<Minutia>();
        minutiaBranch = new ArrayList<Minutia>();
    }

//    private void printMatrix() {
//        for (int i = 0; i < skelImg.getWidth(); i++) {
//            for (int j = 0; j < skelImg.getHeight(); j++) {
//                System.out.print(skelImg.getRGB(i,j) + " ");
//            }
//            System.out.println("");
//        }
//    }

    public void perform() {
        findCheckPoint(getArray());
        delNoisePoints();
    }

    public List<Minutia> getMinutiaEnd() {
        return minutiaEnd;
    }

    public List<Minutia> getMinutiaBranch() {
        return minutiaBranch;
    }

    private int[][] getArray() {
        int[][] arr = new int[skelImg.getHeight()][skelImg.getWidth()];
        for (int i = 0; i < skelImg.getHeight(); i++) {
            for (int j = 0; j < skelImg.getWidth(); j++) {
                arr[j][i] = skelImg.getRGB(j, i);
            }
        }
        return arr;
    }

    private void findCheckPoint(int[][] wArr)
    {

        for (int j = 1; j < skelImg.getWidth() - 1; j++)
        {
            for (int i = 1; i < skelImg.getHeight() - 1; i++)
            {
            /* Окончания */
                // Проверка на первый шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 1 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(j, i, 270, true));
                    continue;
                }


                // Второй шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 1 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(j, i, 225, true));
                    continue;
                }


                // Третий шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 1 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(j, i, 180, true));
                    continue;
                }


                // Четвёртый шаблон
                if (wArr[j - 1][ i - 1] == 1 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(j, i, 135, true));
                    continue;
                }


                // Пятый шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 1 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 01 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(j, i, 90, true));
                    continue;
                }

                // Шестой шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 1
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(j, i, 45, true));
                    continue;
                }


                // Седьмой шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 1
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaEnd.add(new Minutia(j, i, 0, true));
                    continue;
                }

                // Восьмой шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 1)
                {
                    minutiaEnd.add(new Minutia(j, i, 315, true));
                    continue;
                }

            /* Раздвоения */
                // Проверка на первый шаблон
                if (wArr[j - 1][ i - 1] == 1 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 1
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(j, i, 90, false));
                    continue;
                }

                // Второй шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 1 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 1
                        && wArr[j + 1][ i - 1] == 1 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(j, i, 45, false));
                    continue;
                }

                // Третий шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 1
                        && wArr[j][ i - 1] == 1 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 1)
                {
                    minutiaBranch.add(new Minutia(j, i, 0, false));
                    continue;
                }


                // Четвёртый шаблон
                if (wArr[j - 1][ i - 1] == 1 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 1
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 1 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(j, i, 315, false));
                    continue;
                }

                // Пятый шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 1 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 1 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 1)
                {
                    minutiaBranch.add(new Minutia(j, i, 270, false));
                    continue;
                }

                // Шестой шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 1
                        && wArr[j][ i - 1] == 1 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 1 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(j, i, 225, false));
                    continue;
                }


                // Седьмой шаблон
                if (wArr[j - 1][ i - 1] == 1 && wArr[j - 1][ i] == 0 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 0 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 1
                        && wArr[j + 1][ i - 1] == 1 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 0)
                {
                    minutiaBranch.add(new Minutia(j, i, 180, false));
                    continue;
                }

                // Восьмой шаблон
                if (wArr[j - 1][ i - 1] == 0 && wArr[j - 1][ i] == 1 && wArr[j - 1][ i + 1] == 0
                        && wArr[j][ i - 1] == 1 && wArr[j][ i] == 1 && wArr[j][ i + 1] == 0
                        && wArr[j + 1][ i - 1] == 0 && wArr[j + 1][ i] == 0 && wArr[j + 1][ i + 1] == 1)
                {
                    minutiaBranch.add(new Minutia(j, i, 135, false));
                    continue;
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

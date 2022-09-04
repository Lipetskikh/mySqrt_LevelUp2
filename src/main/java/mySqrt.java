import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class mySqrt {
    static int countOfThreads = 4;
    static int sizeThread = 1_000_000 / countOfThreads; // ������ 1 �����
    static Vector<Double> results = new Vector<>();// = null;
    static ArrayList<Thread> threads = new ArrayList<>(countOfThreads);
    public static void main(String[] args) {
        for (int i = 0; i < countOfThreads; i++) // �������� ������
        {
            threads.add(new Thread());
        }

        startAll(threads); // �����

        for (int i = 0; i < countOfThreads; i++) // ���������� �����
        {
            int currentThread = i;
            computeSqrt(threads.get(i), currentThread);
        }

        joinAll(threads); // join

        try(FileWriter writer = new FileWriter("result.txt")) // ������ � ����
        {
            for (int i = 0; i < results.size(); i++)
            writer.write(results.get(i).toString() + "\n" + "\n");//elementAt(i));
            //writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void startAll(ArrayList<Thread> thr)
    {
        for (int i = 0; i < thr.size(); i++)
        {
            thr.get(i).start();
        }
    }

    private static void joinAll(ArrayList<Thread> thr)
    {
        for (int i = 0; i < thr.size(); i++)
        {
            try {
                thr.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void computeSqrt(Thread thread, int curThr) // ������� ���������� �����
    {
        {
            int startElement = curThr * sizeThread; // ��������� ������� �����
            int endElement = startElement + sizeThread; // �������� ������� �����
            for (int j = startElement; j < endElement; j++)
            {
                double res = Math.sqrt(j);
                results.add(j, res);
            }
        }
    }
}

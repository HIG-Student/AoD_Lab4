package edu.wpi.cs.web.cs2005.modified;

/**
 * A selection sort demonstration algorithm SortAlgorithm.java, Mon Jul 17
 * 14:26:00 2000
 *
 * @author Andreas Koeller
 * @version 1.0, 17 Jul 2000
 */
class SelectionSortAlgorithm extends SortAlgorithm
{
    void sort(int a[]) throws Exception
    {
        int j, minIndex;
        for (int i = 0; i < a.length; i++)
        {
            minIndex = i;
            for (j = i + 1; j < a.length; j++)
            {
                if (stopRequested)
                {
                    return;
                }
                if (a[j] < a[minIndex])
                    minIndex = j;
                compPause(i, j);
            }
            int T = a[i];
            swapPause(i, j);
            a[i] = a[minIndex];
            swapPause(i, j);
            a[minIndex] = T;
            swapPause(i, j);
        }
        return;
    }
}

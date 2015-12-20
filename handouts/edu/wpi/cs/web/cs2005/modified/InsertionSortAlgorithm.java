package edu.wpi.cs.web.cs2005.modified;

/**
 * A insertion sort demonstration algorithm SortAlgorithm.java, Mon Jul 17
 * 14:31:00 2000
 *
 * @author Andreas Koeller
 * @version 1.0, 17 Jul 2000
 */
class InsertionSortAlgorithm extends SortAlgorithm
{
    void sort(int a[]) throws Exception
    {
        int j, current;
        boolean whileLoopNeverUsed = true;
        for (int i = 0; i < a.length - 1; i++)
        {
            current = a[i + 1];
            j = i;
            swapPause(i, j);
            while (j >= 0 && current < a[j])
            {
                compPause(i, j);
                whileLoopNeverUsed = false;
                if (stopRequested)
                {
                    return;
                }
                a[j + 1] = a[j];
                swapPause(i, j);
                j--;
            }
            if (whileLoopNeverUsed)
            {
                compPause(i, j);
            }
            else
                whileLoopNeverUsed = true;
            a[j + 1] = current;
            swapPause(i, j);
        }
        return;
    }
}

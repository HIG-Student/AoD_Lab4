package edu.wpi.cs.web.cs2005;

/**
 * A merge sort demonstration algorithm SortAlgorithm.java
 *
 * @author James Gosling
 * @author Kevin A. Smith
 * @version @(#)QSortAlgorithm.java 1.3, 29 Feb 1996
 */
public class MergeSortAlgorithm extends SortAlgorithm
{

    /**
     * A version of pause() that makes it easier to ensure that we pause exactly
     * the right number of times.
     */
    private boolean pauseTrue(int lo, int hi) throws Exception
    {
        super.compPause(lo, hi);
        return true;
    }

    void MergeSort(int a[], int from, int to) throws Exception
    {
        if (to > from)
        {
            // mid is last index on left sub-array. If odd number of
            // elements, the left sub-array is smaller
            int mid = (to - from + 1) / 2 + from - 1;
            MergeSort(a, from, mid);
            MergeSort(a, mid + 1, to);
            merge(a, from, mid, to);
        }
    }

    // merge subarray from "from" to "mid" with subarray from "mid+1"
    // to "to" and store result back in subarray from "from"

    void merge(int data[], int from, int mid, int to) throws Exception
    {
        int n1 = mid - from + 1;
        int n2 = to - mid;

        int temp[] = new int[n1 + n2];
        int k = 0, k1 = 0, k2 = 0, i;

        while ((k1 < n1) && pauseTrue(from + k1, from + n1)
                && (k2 < n2) && pauseTrue(from + k2, from + n2))
        {
            if ((data[from + k1] < data[from + n1 + k2])
                    && pauseTrue(from + k1, from + n1 + k2))
            {
                temp[k] = data[from + k1];
                k++;
                k1++;
                swapPause(from + k, from + k1);
            }
            else
            {
                temp[k] = data[from + n1 + k2];
                k++;
                k2++;
                swapPause(from + k, from + n1 + k2);
            }
        }

        while (k1 < n1 && pauseTrue(from + k1, from + n1))
        {
            temp[k] = data[from + k1];
            k++;
            k1++;
            swapPause(from + k, from + k1);
        }
        while (k2 < n2 && pauseTrue(from + k2, from + n2))
        {
            temp[k] = data[from + n1 + k2];
            k++;
            k2++;
            swapPause(from + k, from + n1 + k2);
        }

        System.arraycopy(temp, 0, data, from, n1 + n2);
        swapPause(from, from + n1 + n2);
        /*
         * for (i=0;i<n1+n2; i++) { data[from+i]=temp[i];
         * swapPause(from+i,from+i); }
         */
    }

    public void sort(int a[]) throws Exception
    {
        MergeSort(a, 0, a.length - 1);
    }
}

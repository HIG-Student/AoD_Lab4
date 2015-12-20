package edu.wpi.cs.web.cs2005;

class HeapSortAlgorithm extends SortAlgorithm
{
    void sort(int data[]) throws Exception
    {
        int unsorted;
        makeHeap(data);
        unsorted = data.length;

        /** O(n log n) */
        while (unsorted > 1)
        {
            unsorted--;
            swap(data, 0, unsorted);
            reheapifyDown(data, 0, unsorted - 1);// used also for makeHeap
        }
    }

    /** O(n) */
    private void makeHeap(int data[]) throws Exception
    {
        for (int i = data.length / 2; i >= 0; i--)
            reheapifyDown(data, i, data.length - 1);
    }

    private void reheapifyDown(int data[], int from, int maxIndex) throws Exception
    {
        int current = from, biggerChild, left, right;
        int currentItem = data[current];
        swapPause(current, current);
        boolean heapOK = false;

        while (!heapOK)
        {
            left = leftChild(current, maxIndex);
            right = rightChild(current, maxIndex);

            if (left + right == -2)
                break; // both children are -1;
            if (left == -1)
                biggerChild = right;
            else
                if (right == -1)
                    biggerChild = left;
                else
                    biggerChild = data[left] > data[right] ? left : right;
            compPause(left, right);
            if (currentItem < data[biggerChild])
            {
                compPause(current, biggerChild);
                data[current] = data[biggerChild];
                swapPause(current, biggerChild);
                current = biggerChild;
            }
            else
            {
                heapOK = true;
            }
        }
        data[current] = currentItem;
        swapPause(current, current);
    }

    /** returns -1 if leaf */
    private int leftChild(int index, int maxIndex)
    {
        int result = 2 * index + 1;
        return result > maxIndex ? -1 : result;
    }

    /** returns -1 if leaf */
    private int rightChild(int index, int maxIndex)
    {
        int result = 2 * index + 2;
        return result > maxIndex ? -1 : result;
    }

    /** returns -1 if root */
    private int parent(int index)
    {
        if (index == 0)
            return -1;
        else
            return (index - 1) / 2;
    }

    private void swap(int a[], int i, int j) throws Exception
    {
        int T;
        T = a[i];
        swapPause(i, j);
        a[i] = a[j];
        swapPause(i, j);
        a[j] = T;
        swapPause(i, j);

    }

}

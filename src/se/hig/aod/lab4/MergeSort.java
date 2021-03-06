package se.hig.aod.lab4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Sorting with mergesort
 * 
 * @param <T>
 *            Type to sort
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 * @author Mergesort algorithm based on work by Peter Jenke
 */
public class MergeSort<T extends Comparable<? super T>>
{
    T[] data;
    T[] temporary;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    static Comparator<Comparable> natural = (a, b) -> a.compareTo(b);

    @SuppressWarnings("unchecked")
    Comparator<T> comparator = (Comparator<T>) natural;

    MergeSort(T[] array)
    {
        this(Arrays.asList(array)); // Copy hax
    }

    @SuppressWarnings("unchecked")
    MergeSort(List<T> list)
    {
        data = (T[]) list.toArray();
    }

    MergeSort<T> setSort(Comparator<T> comparator)
    {
        this.comparator = comparator;
        return this;
    }

    /**
     * Sort the list with mergesort<br>
     * <br>
     * Null is not allowed in the list
     * 
     * @param <T>
     *            Type to sort
     * @param list
     *            the list to sort
     * @return the sorted list
     */
    public static <T extends Comparable<? super T>> List<T> sort(List<T> list)
    {
        return Arrays.asList(new MergeSort<T>(list).sort());
    }

    /**
     * Sort the list with mergesort<br>
     * <br>
     * Null is not allowed in the list
     * 
     * 
     * @param <T>
     *            Type to sort
     * @param arr
     *            the array to sort
     * @return the sorted array
     */
    public static <T extends Comparable<? super T>> T[] sort(T[] arr)
    {
        return new MergeSort<T>(arr).sort();
    }

    /**
     * Sort the list with mergesort<br>
     * <br>
     * Null is not allowed in the list
     * 
     * 
     * @param <T>
     *            Type to sort
     * @param list
     *            the list to sort
     * @param comperator
     *            the comparator to use
     * @return the sorted list
     */
    public static <T extends Comparable<? super T>> List<T> sort(List<T> list, Comparator<T> comperator)
    {
        return Arrays.asList(new MergeSort<T>(list).setSort(comperator).sort());
    }

    /**
     * Sort the list with mergesort<br>
     * <br>
     * Null is not allowed in the list
     * 
     * @param <T>
     *            Type to sort
     * @param arr
     *            the array to sort
     * @param comperator
     *            the comparator to use
     * @return the sorted array
     */
    public static <T extends Comparable<? super T>> T[] sort(T[] arr, Comparator<T> comperator)
    {
        return new MergeSort<T>(arr).setSort(comperator).sort();
    }

    @SuppressWarnings("unchecked")
    T[] sort()
    {
        temporary = (T[]) new Comparable[data.length];
        subSort(0, data.length - 1);

        return data;
    }

    void subSort(int low, int high)
    {
        if (high - low >= 1)
        {
            int mid = (low + high) / 2;
            subSort(low, mid);
            subSort(mid + 1, high);

            for (int i = mid; i >= low; i--)
                temporary[i] = data[i];

            for (int i = mid + 1; i <= high; i++)
                temporary[mid + 1 + high - i] = data[i];

            int iLow = low;
            int iHigh = high;

            for (int i = low; i <= high; i++)
                if (comparator.compare(temporary[iLow], temporary[iHigh]) > 0)
                {
                    data[i] = temporary[iHigh];
                    iHigh--;
                }
                else
                {
                    data[i] = temporary[iLow];
                    iLow++;
                }
        }
    }
}

package se.hig.aod.lab4;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;

import se.hig.aod.lab4.DateEvent.DateEventInvalidParametersException;

/**
 * Testing the class "MergeSort"
 *
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class MergeSortTest
{
    long seed = 34345436;

    DateEvent[] array;
    DateEvent[] sortedArray;

    void checkArray(DateEvent[] sorted, DateEvent[] toCheck)
    {
        for (int i = 0; i < sorted.length; i++)
        {
            assertEquals("Incorrect order!", sorted[i], toCheck[i]);
        }
    }

    Comparator<DateEvent> sort = (a, b) ->
    {
        int result = a.getDate().compareTo(b.getDate());
        return result == 0 ? a.getName().compareTo(b.getName()) : result;
    };

    Comparator<DateEvent> sort_inverse = (a, b) ->
    {
        return -a.compareTo(b);
    };

    DateEvent[] getSortedArray(DateEvent[] array, Comparator<DateEvent> sorter)
    {
        List<DateEvent> list = Arrays.asList(array);
        Collections.sort(list, sorter);

        return (DateEvent[]) list.toArray();
    }

    DateEvent[] getRandomArray()
    {
        DateEvent[] array = new DateEvent[30];

        Random r = new Random(seed);

        for (int i = 0; i < array.length; i++)
            array[i] = new DateEvent(UUID.randomUUID().toString(), Date.from(Instant.ofEpochSecond((long) (r.nextDouble() * 60 * 60 * 24 * 360 * 5))));

        return array;
    }

    /**
     * Ensure null as date throws exception
     */
    @Test(expected = DateEventInvalidParametersException.class)
    public void testNullDate()
    {
        new DateEvent("Null", null);
    }

    /**
     * Ensure null as name throws exception
     */
    @Test(expected = DateEventInvalidParametersException.class)
    public void testNullName()
    {
        new DateEvent(null);
    }

    /**
     * Test natural sort
     */
    @Test
    public void testNaturalSort()
    {
        for (int seed_i = 0; seed_i < 10; seed_i++)
        {
            sortedArray = getSortedArray(array = getRandomArray(), sort);

            DateEvent[] sorted = MergeSort.sort(array);

            checkArray(sortedArray, sorted);
        }
    }

    /**
     * Test sort with comparator
     */
    @Test
    public void testNaturalComparatorSort()
    {
        for (int seed_i = 0; seed_i < 10; seed_i++)
        {
            sortedArray = getSortedArray(array = getRandomArray(), sort);

            DateEvent[] sorted = MergeSort.sort(array, sort);

            checkArray(sortedArray, sorted);
        }
    }

    /**
     * Test inverse sort
     */
    @Test
    public void testInverseSort()
    {
        for (int seed_i = 0; seed_i < 10; seed_i++)
        {
            sortedArray = getSortedArray(array = getRandomArray(), sort_inverse);

            DateEvent[] sorted = MergeSort.sort(array, sort_inverse);

            checkArray(sortedArray, sorted);
        }
    }
}

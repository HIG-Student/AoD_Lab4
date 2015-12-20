package se.hig.aod.lab4;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
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

    /**
     * Add elements with an UUID and random date
     * 
     * @param seed
     *            the seed to use
     */
    public void setUp(long seed)
    {
        array = new DateEvent[30];

        Random r = new Random(seed);

        for (int i = 0; i < array.length; i++)
            array[i] = new DateEvent(UUID.randomUUID().toString(), Date.from(Instant.ofEpochSecond((long) (r.nextDouble() * 60 * 60 * 24 * 360 * 5))));

        List<DateEvent> list = Arrays.asList(array);
        Collections.sort(list, (a, b) ->
        {
            int result = a.getDate().compareTo(b.getDate());
            return result == 0 ? a.getName().compareTo(b.getName()) : result;
        });

        sortedArray = (DateEvent[]) list.toArray();
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
     * Test sort
     */
    @Test
    public void testSort()
    {
        for (int seed_i = 0; seed_i < 10; seed_i++)
        {
            setUp(seed + seed_i);

            DateEvent[] sorted = MergeSort.sort(array);

            for (int i = 0; i < array.length; i++)
            {
                assertEquals("Incorrect order!", sortedArray[i], sorted[i]);
                System.out.println(sortedArray[i]);
            }
        }
    }
}

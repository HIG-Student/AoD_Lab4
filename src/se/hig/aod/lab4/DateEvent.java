package se.hig.aod.lab4;

import java.util.Date;

/**
 * Class that represent an event and the date it occur(ed) at
 * 
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class DateEvent implements Comparable<DateEvent>
{
    String name;
    Date date;

    /**
     * Create a new date event
     * 
     * @param name
     *            the name of the event
     * @param date
     *            the date of the event
     * @throws DateEventInvalidParametersException
     *             if parameters are invalid
     */
    public DateEvent(String name, Date date)
    {
        if (name == null || date == null)
            throw new DateEventInvalidParametersException("Null is not allowed as a parameter");

        this.name = name;
        this.date = date;
    }

    /**
     * Create a new date event with the current date
     * 
     * @param name
     *            the name of the event
     * @throws DateEventInvalidParametersException
     *             if parameters are invalid
     */
    public DateEvent(String name)
    {
        if (name == null)
            throw new DateEventInvalidParametersException("Null is not allowed as a parameter");

        this.name = name;
        this.date = new Date();
    }

    /**
     * Get the name of the event
     * 
     * @return the name of the event
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the date of the event
     * 
     * @return the date of the event
     */
    public Date getDate()
    {
        return (Date) date.clone();
    }

    /**
     * Compares based on date<br>
     * If same date; compare based on name
     * 
     * @see Comparable
     **/
    @Override
    public int compareTo(DateEvent o)
    {
        int result = date.compareTo(o.date);

        if (result != 0)
            return result;
        else
            return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || !(o instanceof DateEvent))
            return false;

        return date.equals(((DateEvent) o).date) &&
                name.equals(((DateEvent) o).name);
    }

    @Override
    public String toString()
    {
        return date.toString() + " > " + name.toString();
    }

    @SuppressWarnings("serial")
    class DateEventException extends RuntimeException
    {
        DateEventException(String msg)
        {
            super(msg);
        }
    }

    @SuppressWarnings("serial")
    class DateEventInvalidParametersException extends DateEventException
    {
        DateEventInvalidParametersException(String msg)
        {
            super(msg);
        }
    }
}

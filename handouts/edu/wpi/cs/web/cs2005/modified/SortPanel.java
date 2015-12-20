package edu.wpi.cs.web.cs2005.modified;

/*
 * @(#)SortItem.java    1.5 97/07/30
 *
 * Copyright (c) 2070, 1997 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

/**
 * A simple applet class to demonstrate a sort algorithm. You can specify a
 * sorting algorithm using the "alg" attribyte. When you click on the applet, a
 * thread is forked which animates the sorting algorithm.
 *
 * @author James Gosling
 * @version 1.17f, 10 Apr 1995
 */
@SuppressWarnings("serial")
public class SortPanel extends JPanel implements Runnable , MouseListener
{
    /**
     * The thread that is sorting (or null).
     */
    private Thread kicker;

    /**
     * The array that is being sorted.
     */
    int arr[];

    /**
     * The high water mark.
     */
    int h1 = -1;

    /**
     * The low water mark.
     */
    int h2 = -1;

    /**
     * The name of the algorithm.
     */
    String algName;

    /**
     * The sorting algorithm (or null).
     */
    SortAlgorithm algorithm;

    /**
     * counts number of swapPauses (for complexity estimation)
     */
    int swapPauseCount = 0;

    /**
     * counts number of compPauses (for complexity estimation)
     */
    int compPauseCount = 0;

    /**
     * the height of the applet minus the space needed to print the step counter
     */
    int drawHeight;

    public SortPanel()
    {
        init();
    }

    /**
     * Fill the array with random numbers from 0..n-1.
     */
    void scramble()
    {
        int a[] = new int[drawHeight / 2];
        double f = getSize().width / (double) a.length;
        for (int i = a.length; --i >= 0;)
        {
            a[i] = (int) (i * f);
        }
        for (int i = a.length; --i >= 0;)
        {
            int j = (int) (i * Math.random());
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        arr = a;
    }

    /**
     * SwapPause a while.
     * 
     * @see SortAlgorithm
     */
    void swapPause()
    {
        swapPause(-1, -1);
    }

    /**
     * SwapPause a while, and draw the high water mark.
     * 
     * @see SortAlgorithm
     */
    void swapPause(int H1)
    {
        swapPause(H1, -1);
    }

    /**
     * SwapPause a while, and draw the low&high water marks.
     * 
     * @see SortAlgorithm
     */
    void swapPause(int H1, int H2)
    {
        h1 = H1;
        h2 = H2;
        if (kicker != null)
        {
            repaint();
        }
        try
        {
            swapPauseCount++;
            Thread.sleep(20);
        }
        catch (InterruptedException e)
        {
        }
    }

    /**
     * compPause a while.
     * 
     * @see SortAlgorithm
     */
    void compPause(int H1, int H2)
    {
        h1 = H1;
        h2 = H2;
        if (kicker != null)
        {
            repaint();
        }
        try
        {
            compPauseCount++;
            Thread.sleep(10); // 1/2 of an assignment
        }
        catch (InterruptedException e)
        {
        }
    }

    /**
     * Initialize the applet.
     */
    public void init()
    {
        drawHeight = getSize().height - 28;
        scramble();

        addMouseListener(this);
    }

    public void start()
    {
        swapPauseCount = 0;
        compPauseCount = 0;
        scramble();
        repaint();
    }

    /**
     * Deallocate resources of applet.
     */
    public void destroy()
    {
        removeMouseListener(this);
    }

    /**
     * Paint the array of numbers as a list of horizontal lines of varying
     * lengths.
     */
    public void paint(Graphics g)
    {
        if (kicker == null)
            return;

        int a[] = arr;
        int y = drawHeight - 1;

        g.setColor(getBackground());
        g.fillRect(0, y + 1, getSize().width, getSize().height - 1);
        g.setColor(Color.black);
        g.drawString((new Integer(swapPauseCount)).toString() + " assignments.",
                1,
                y + 12);
        g.drawString((new Integer(compPauseCount)).toString() + " comparisons.",
                1,
                y + 26);

        // Erase old lines
        g.setColor(getBackground());
        for (int i = a.length; --i >= 0; y -= 2)
        {
            g.drawLine(arr[i] + 1, y, getSize().width, y);
        }

        // Draw new lines
        g.setColor(Color.black);
        y = drawHeight - 1;
        for (int i = a.length; --i >= 0; y -= 2)
        {
            g.drawLine(0, y, arr[i], y);
        }

        if (h1 >= 0)
        {
            g.setColor(Color.red);
            y = h1 * 2 + 1;
            g.drawLine(0, y, getSize().width, y);
        }
        if (h2 >= 0)
        {
            g.setColor(Color.blue);
            y = h2 * 2 + 1;
            g.drawLine(0, y, getSize().width, y);
        }
    }

    /**
     * Update without erasing the background.
     */
    public void update(Graphics g)
    {
        paint(g);
    }

    /**
     * Run the sorting algorithm. This method is called by class Thread once the
     * sorting algorithm is started.
     * 
     * @see java.lang.Thread#run
     * @see SortPanel#mouseUp
     */
    public void run()
    {
        try
        {
            if (algorithm == null)
            {
                algorithm = (SortAlgorithm) new HeapSortAlgorithm();
                algorithm.setParent(this);
            }
            algorithm.init();
            algorithm.sort(arr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Stop the applet. Kill any sorting algorithm that is still sorting.
     */
    public synchronized void stop()
    {
        if (algorithm != null)
        {
            try
            {
                algorithm.stop();
            }
            catch (IllegalThreadStateException e)
            {
                // ignore this exception
            }
            kicker = null;
        }
    }

    /**
     * For a Thread to actually do the sorting. This routine makes sure we do
     * not simultaneously start several sorts if the user repeatedly clicks on
     * the sort item. It needs to be synchronoized with the stop() method
     * because they both manipulate the common kicker variable.
     */
    private synchronized void startSort()
    {
        if (kicker == null || !kicker.isAlive())
        {
            kicker = new Thread(this);
            kicker.start();
        }
    }

    // 1.1 event handling

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    /**
     * The user clicked in the applet. Start the clock!
     */

    public void mouseReleased(MouseEvent e)
    {
        compPauseCount = 0;
        swapPauseCount = 0;
        startSort();
        e.consume();
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }
}

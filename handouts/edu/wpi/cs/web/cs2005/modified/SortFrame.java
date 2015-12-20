package edu.wpi.cs.web.cs2005.modified;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

/**
 * 
 *
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
@SuppressWarnings("serial")
public class SortFrame extends JFrame
{
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    SortFrame frame = new SortFrame();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SortFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(new ColumnSpec[] {
                ColumnSpec.decode("default:grow"),
                ColumnSpec.decode("default:grow"),
                ColumnSpec.decode("default:grow"),
                ColumnSpec.decode("default:grow"),
                ColumnSpec.decode("default:grow"),
                ColumnSpec.decode("default:grow"),},
            new RowSpec[] {
                FormFactory.DEFAULT_ROWSPEC,
                RowSpec.decode("default:grow"),}));
        
        
        SortPanel panel = new SortPanel();
        contentPane.add(panel, "1, 2");
    }

}

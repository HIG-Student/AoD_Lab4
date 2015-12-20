package se.hig.aod.lab4;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class to run the AOD_Lab4.exe file
 *
 * @author Viktor Hanstorp (ndi14vhp@student.hig.se)
 */
public class AOD_Lab4_Runner
{
    /**
     * Entry-point
     * 
     * @param args
     *            arguments
     * @throws Exception
     *             on error
     */
    public static void main(String[] args) throws Exception
    {
        for (Result result : benchmarkFiles())
        {
            System.out.println(result.file + ":");
            System.out.println("\tTimes: " + result.times);
            double[] means = result.getMeans();
            for (int i = 0; i < 4; i++)
                System.out.println("\tMethod" + (i + 1) + ": " + means[i]);
        }
    }

    static Result[] benchmarkFiles() throws Exception
    {
        char[] files = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };

        Result[] results = new Result[files.length];

        for (int i = 0; i < files.length; i++)
        {
            String file = "other/data/" + files[i] + ".txt";

            double[][] messures = new double[5][4];

            for (int j = 0; j < 5; j++)
            {
                messures[j] = readFile(file);
            }

            results[i] = new Result(file, 5, messures);
        }

        return results;
    }

    static class Result
    {
        String file;
        final int times;
        public final double[][] messures;

        Result(String file, int times, double[][] messures)
        {
            this.file = file;
            this.times = times;
            this.messures = messures;
        }

        double[] getMeans()
        {
            double[] results = new double[4];

            for (int i = 0; i < messures.length; i++)
                for (int j = 0; j < 4; j++)
                    results[j] += messures[i][j];

            for (int j = 0; j < 4; j++)
                results[j] /= times;

            return results;
        }
    }

    static double[] readFile(String file) throws Exception
    {
        double[] result = new double[5];

        // http://stackoverflow.com/a/5604756
        Process process = new ProcessBuilder("other/AOD_Lab4.exe", file).start();

        // http://stackoverflow.com/q/5604698
        try (InputStream is = process.getInputStream())
        {
            try (InputStreamReader isr = new InputStreamReader(is))
            {
                try (BufferedReader br = new BufferedReader(isr))
                {
                    String line;

                    if (!(line = br.readLine()).equals("Processing file " + file + ":"))
                        throw new Exception("Unexpected output:\n\t" + line);

                    for (int i = 1; i < 5; i++)
                    {
                        if (!(line = br.readLine()).equals("Sorting data, method " + i))
                            throw new Exception("Unexpected output:\n\t" + line);

                        if (!(line = br.readLine()).startsWith("Time: ") && line.endsWith(" s"))
                            throw new Exception("Unexpected output:\n\t" + line);

                        double time = Double.parseDouble(line.substring(6, line.length() - 2));

                        result[i - 1] = time;
                    }
                }
            }
        }

        return result;
    }
}

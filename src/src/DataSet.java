package learningalgorithms;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents a collection of samples in a data set and
 * contains tools for interpreting a data file
 *
 * @author  Lance Inman
 * @version 1.0
 * @see Sample, Classifier
 */
public class DataSet {
    ArrayList<Sample> samples;

    //used to make DataSets not based on a data file (test and training)
    public DataSet()
    {
        samples = new ArrayList<Sample>();
    }
    
    public DataSet(File dataFile, int classNameIndex, boolean treatQuestionMarksAsMissingValues) throws FileNotFoundException {
        samples = new ArrayList<Sample>(); //initialize the samples list
        readFile(dataFile, classNameIndex); //read in the file. Populates the samples list.
        
        //some datasets use the '?' character as a relevant data point. Only treat '?' values as "missing" if specified.
        if(treatQuestionMarksAsMissingValues)
            handleMissingValues();
    }

    public void readFile(File dataFile, int classNameIndex) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(dataFile));
            String entry;
            while ((entry = bf.readLine()) != null) {
                try {
                    Sample s = new Sample(entry, classNameIndex);
                    samples.add(s);
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    System.err.println("A sample could not be created from entry: '"+entry+"'");
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("readFile(File, int) could not File " + dataFile.getAbsolutePath());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    private void handleMissingValues() {
        // for each sample in the data set
        for(Sample s: samples) {
            // and every value in each sample
            for(int i = 0; i < s.data.size(); i++) {
                // if the value is missing (?), deal with it!
                if(s.data.get(i).equals("?")) {
                    String missingVal = s.data.get(i);
                    missingVal = fillMissingValue(i);
                }
            }
        }
    }
    
    //fills a missing value (at index mVI) in a Sample by randomly finding a real value at index mVI in another Sample
    private String fillMissingValue(int missingValueIndex)
    {        
        /* Bootstrap from the Samples in the data List.
         * Ensure that a non-missing value fills the missing value by looking until a non-missing value is found.
         * Loop continuously until a non-missing value is found.
         */
        while(true)
        {
            int completeSampleIndex = (int)(samples.size() * Math.random()); //generate a number between 0 (inclusive) and data.size() (exclusive)
            
            /* Check the missingValueIndex-th data entry of the completeSampleIndex-th Sample.
             * If the index contains a non-missing value, use that value.
             */
            if(!samples.get(completeSampleIndex).data.get(missingValueIndex).equals("?"))
            {
                return samples.get(completeSampleIndex).data.get(missingValueIndex);
            }
        }
    }
}

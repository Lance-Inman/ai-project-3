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
    ArrayList<Sample> data;

    public DataSet(File dataFile, int classNameIndex) throws FileNotFoundException {
        data = new ArrayList<>();
        readFile(dataFile, classNameIndex);
        handleMissingValues();
    }

    public void readFile(File dataFile, int classNameIndex) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(dataFile));
            String entry;
            while ((entry = bf.readLine()) != null) {
                try {
                    Sample s = new Sample(entry, classNameIndex);
                    data.add(s);
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

    public void handleMissingValues() {
        // for each sample in the data set
        for(Sample s: data) {
            // and every value in each sample
            for(String v: s.data) {
                // if the value is missing (?), deal with it!
                if(v.equals("?")) {
                    // TODO: perform data imputation
                }
            }
        }
    }
}

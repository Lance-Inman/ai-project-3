import java.util.ArrayList;

/**
 * Represents a sample of a data set that has not
 * been classified
 *
 * @author  Lance Inman
 * @version 1.0
 * @see Sample
 */
public class UnclassifiedSample extends Sample {
    public ArrayList<String> data;
    public String className;

    public UnclassifiedSample(String dataEntry, int classNameIndex) {
        super(dataEntry, classNameIndex);
    }

    public void processLine(String dataEntry) {
        String[] values = dataEntry.split(", ");
        for(String s: values) {
            data.add(s);
        }
    }



}

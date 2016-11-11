import java.util.ArrayList;

/**
 * Represents a sample of a data set
 *
 * @author  Lance Inman
 * @version 1.0
 * @see DataSet, Classifier
 */
public class Sample {
    public String className;
    public ArrayList<String> data;

    public Sample(String dataEntry, int classNameIndex) {
        data = new ArrayList<>();
        processLine(dataEntry, classNameIndex);
    }

    private void processLine(String dataEntry, int classNameIndex) throws ArrayIndexOutOfBoundsException{
        String[] values = dataEntry.split(", ");
        for(int i = 0; i < values.length; i++) {
            if(i == classNameIndex) {
                className = values[i];
            } else {
                data.add(values[i]);
            }
        }
    }

}

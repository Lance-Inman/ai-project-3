import java.util.ArrayList;

/**
 * Represents a sample of a data set
 *
 * @author  Lance Inman
 * @version 1.0
 * @seealso DataSet, Classifier
 */
public class Sample {
    private String className;
    private ArrayList<String> data;

    public Sample(String line, int classIndex) {
        data = new ArrayList<String>();
        processLine(line, classIndex);
    }

    private void processLine(String line, int classIndex) throws ArrayIndexOutOfBoundsException{
        String[] values = line.split(", ");
        for(int i = 0; i < values.length; i++) {
            if(i == classIndex) {
                className = values[i];
            } else {
                data.add(values[i]);
            }
        }

    }

}

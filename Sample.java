package learningalgorithms;

import java.util.ArrayList;

/**
 * Represents a sample of a data set
 *
 * @author  Lance Inman
 * @version 1.1
 * @see DataSet, Classifier
 */
public class Sample {
    private String className;
    private int classNameIndex;
    public ArrayList<String> data;

    public Sample(String dataEntry, int classNameIndex) {
        data = new ArrayList<>();
        this.classNameIndex = classNameIndex;
        processLine(dataEntry);
    }

    private void processLine(String dataEntry) throws ArrayIndexOutOfBoundsException{
        String[] values = dataEntry.split(",");
        for(int i = 0; i < values.length; i++) {
            if(i == classNameIndex) {
                className = values[i];
            } else {
                data.add(values[i]);
            }
        }
    }
    
    public int getClassNameIndex()
    {
        return classNameIndex;
    }
    
    public String getClassName()
    {
        return className;
    }
    public void setClassName(String n)
    {
        className = n;
    }
}

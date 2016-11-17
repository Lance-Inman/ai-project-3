package learningalgorithms;

/**
 *
 *
 * @author  Lance Inman
 * @version 1.0
 * @see Sample, DataSet
 */
public interface Classifier {
    //used by test() to actually perform the classifying. returns 1 for a successful classification, 0 for unsuccessful
    int classify(Sample s);
    
    //takes in a set of items, notes their classification(s), and uses that information to classify new cases given in test()
    void train(DataSet trainingSet);
    
    /* Uses the pre-processed training data to classify items in the given set. 
     * Returns the proportion of successfully classifies Samples to total attempted classifications.
     */
    double test(DataSet testSet);
    
    //used to ID the Classifier in the output when it's used
    void print();
    
    //used to choose when (during which fold) to print the individual classification output(s)
    void setOutputClassifications(boolean b);
}

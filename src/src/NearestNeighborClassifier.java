/**
 * A machine learning algorithm to classify objects in a given data set
 * using the NearestNeighbor algorithm
 *
 * @author  Lance Inman
 * @version 1.0
 * @see Classifier, Sample, DataSet
 */
public class NearestNeighborClassifier implements Classifier{
    public Sample classify(UnclassifiedSample us) {

    }

    public void train(DataSet trainingSet) {

    }

    double distance(Sample src, Sample dest) {
        if(src.data.size() != dest.data.size()) {
            System.err.println("Sample mismatch: The length of the two provided Samples do not match");
            return Double.MAX_VALUE;
        }
    }
    void print() {

    }
}

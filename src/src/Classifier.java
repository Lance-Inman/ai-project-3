/**
 *
 *
 * @author  Lance Inman
 * @version 1.0
 * @see Sample, DataSet
 */
public interface Classifier {
    Sample classify(UnclassifiedSample us);
    void train(DataSet trainingSet);
    void print();
}

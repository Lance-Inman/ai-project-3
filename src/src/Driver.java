package learningalgorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author black
 */
public class Driver 
{    
    /* 1. Create File objects from the paths of the .data files.
     *
     * Then, for each File:
     * 2. Make a DataSet out of the file.
     * 3. Set up training and test subsets (w/ 10-fold cross-validation).
     * 4. Run each of the algorithms on the DataSet (using the training and test subsets).
     *   -- Only output the classification results for one (of the 10) folds.
     *   -- Still average over all folds, just don't output the results. All folds are necessary for the report.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        ArrayList<Classifier> classificationAlgs = new ArrayList<Classifier>();
        classificationAlgs.add(new NaiveBayes());
        
        //1. Create file objects from the paths of the .data files.
        //2. Make a DataSet out of the files.
        ArrayList<DataSet> dataSets = new ArrayList<DataSet>();
        
        //add breast cancer dataset to the ArrayList. Classificaton index is 10. '?'s should be treated as missing values.
        dataSets.add(new DataSet(new File("C:\\Users\\black\\Documents\\NetBeansProjects\\LearningAlgorithms\\src\\learningalgorithms\\breast-cancer-wisconsin.data.txt"), 10, true));
        //add house votes dataset to te ArrayList. Classification index is 0. '?'s shouldn't be treated as missing values.
        dataSets.add(new DataSet(new File("C:\\Users\\black\\Documents\\NetBeansProjects\\LearningAlgorithms\\src\\learningalgorithms\\house-votes-84.data.txt"), 0, false));
        
        //Then, for each File:
        for(DataSet ds : dataSets)
        {            
            //4. Run each of the algorithms on the DataSet (using the training and test subsets).
            for(Classifier c : classificationAlgs)
            {
                c.print(); //IDs the current classifier
                
                //3. Set up training and test subsets (w/ 10-fold cross-validation).
                DataSet trainingSet = new DataSet(),
                        testSet = new DataSet();
                
                double sumPerformance = 0; //keeps a running total of all the performance proportions. Will be used to find the average performance score.
                
                for(int i = 0; i < 10; i++)
                {
                    if(i == 0)
                        c.setOutputClassifications(true);
                    else
                        c.setOutputClassifications(false);
                    
                    //manages 10-fold cross-validation
                    defineSets_10FoldCrossValidation(i, trainingSet, testSet, ds);
                    c.train(trainingSet);
                    sumPerformance += c.test(testSet);
                }
                System.out.println("The average performance score was " + sumPerformance / 10 + "\n");
            }
        }
    }
    
    /* Implements 10-Fold Cross Validation.
     * Helps perfrom 10 rounds of learning. On each round, 1/10th of the data is held out as a test set,
     *   the remainder belonging to the training set.
     * This functionality is achieved by passing which 1/10th of the data should be delegated as the test set (i.e. the 0th 1/10th, the 1st 1/10th, etc.).
     * With this information, each set is formulated using the desired Samples from the complete data set.
     */
    private static void defineSets_10FoldCrossValidation(int testStartIndex, DataSet trainingSet, DataSet testSet, DataSet complete)
    {
        int tenthMultiplier = complete.samples.size() / 10; //determine what a tenth of the length of the complete Sample list is. Used to index properly into the list.
        
        //clear the previous subsets
        trainingSet.samples.clear();
        testSet.samples.clear();
        
        for(int i = 0; i < complete.samples.size(); i++)
        {
            /* Only add a Sample to the test set if its index in complete.samples is in the desired tenth 
             *   (i.e. between the lower bound [inclusive] and the upper bound [exclusive]).
             * Otherwise, add the Sample to the training set.
             */
            if(i >= tenthMultiplier * testStartIndex && i < tenthMultiplier * (testStartIndex + 1))
                testSet.samples.add(complete.samples.get(i));
            else
                trainingSet.samples.add(complete.samples.get(i));
        }
    }
    
}

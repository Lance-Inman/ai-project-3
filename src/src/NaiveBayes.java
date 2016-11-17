package learningalgorithms;

import java.util.ArrayList;

/*
 * @author Ethan Peterson
 * @since Nov 16, 2016
 */
public class NaiveBayes implements Classifier
{
    public DataSet trainingSet;
    private boolean outputClassifications;
    
    //"training" Naive Bayes merely involves taking in the training set
    public void train(DataSet trainingSet)
    {
        this.trainingSet = trainingSet;
    }
    
    //test each Sample s in the test set using the training set
    public double test(DataSet testSet)
    {
        int numCorrectClassifications = 0;
        
        for(Sample s : testSet.samples)
        {
            numCorrectClassifications += classify(s);
        }
        
        return ((double) numCorrectClassifications) / testSet.samples.size();
    }
    
    public int classify(Sample s)
    {
        String classForSample = null;
        double maxProbability = Double.MIN_VALUE;
        
        //populate a list containing the possible classes that 's' may be
        ArrayList<String> possibleClasses = new ArrayList<String>();
        populatePossibleClasses(possibleClasses);
        
        //iterate through all possible classes
        for(String c : possibleClasses)    
        {
            double probability = priorProbabilityOfClass(c);
            
            /* Calculate probability using Naive Bayes model.
             * Take the product of the prior probability of the class P(c) and
             *   the prior probabilities of all the features xi given c P(xi|c).
             */
            for(int i = 0; i < s.data.size(); i++)
            {
                //skip the index containing the class name
                if(i == s.getClassNameIndex())
                    continue;
                
                probability *= priorProbabilityOfXiGivenC(i, s.data.get(i), c);
            }
            
            /* If the calculated probability for class c is higher than the current highest probability class, 
             * make c the new highest probability class.
             */
            if(probability > maxProbability)
            {
                maxProbability = probability;
                classForSample = c;
            }
        }
        
        if(outputClassifications)
            System.out.println(">>> The Sample of class " + s.getClassName() + " was classified as a " + classForSample + ".");
        if(s.getClassName().equals(classForSample))
            return 1;
        return 0;
    }
    
    //populate a list containing the possible classes based on the training data
    private void populatePossibleClasses(ArrayList<String> possibleClasses)
    {
        for(Sample s : trainingSet.samples)
        {
            if(!possibleClasses.contains(s.getClassName()))
                possibleClasses.add(s.getClassName());
        }
    }
    
    /* Returns the PRIOR probability of a class by the given class name (P(c)).
     * P(c) is estimated by dividing the number of Samples with class c by the total number of Samples.
     */
    private double priorProbabilityOfClass(String className)
    {
        int count = 0; //counts the number of times an entry that has the given class name is in the training set
        
        for(Sample s : trainingSet.samples)
        {
            if(s.getClassName().equals(className))
                count++;
        }
        
        return ((double) count) / trainingSet.samples.size();
    }
    
    /* Returns the PRIOR probability of a feature x in a class 'className'.
     * Counts the number of cases where x exists in a class 'className' and divides that by the total number of cases.
     * If count is 0 at return, instead return .0001.
     */
    private double priorProbabilityOfXiGivenC(int index, String x, String className)
    {
        int count = 0;
        
        for(Sample s : trainingSet.samples)
        {
            //if Sample s has the desired class AND has the desired data x at index, increment count
            if(s.getClassName().equals(className) && s.data.get(index).equals(x))
                count++;
        }
        
        return (count == 0 ? .0001 : (((double) count) / trainingSet.samples.size()));
    }
    
    //ID the classifier being used
    public void print() 
    {
        System.out.println("Naive Bayes Classifier:");
    }
    
    //used to determine when full classification output should be printed
    public void setOutputClassifications(boolean b)
    {
        outputClassifications = b;
    }
}
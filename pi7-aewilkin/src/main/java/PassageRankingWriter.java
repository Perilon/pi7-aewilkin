import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import rank.CompositeRanker;
import rank.IRanker;
import rank.NgramRanker;
import rank.OtherRanker;
import type.Measurement;
import type.Passage;
import type.Question;
import type.AddlStats;
import type.InputDocument;

import edu.cmu.meteor.scorer.MeteorConfiguration;
import edu.cmu.meteor.scorer.MeteorScorer;
import edu.cmu.meteor.util.Constants;

/**
 * This CAS Consumer generates the report file with the method metrics
 */
public class PassageRankingWriter extends CasConsumer_ImplBase {
  final String PARAM_OUTPUTDIR = "OutputDir";

  final String OUTPUT_FILENAME = "ErrorAnalysis.csv";

  File mOutputDir;

  IRanker ngramRanker, otherRanker;

  CompositeRanker compositeRanker;

  @Override
  public void initialize() throws ResourceInitializationException {
    String mOutputDirStr = (String) getConfigParameterValue(PARAM_OUTPUTDIR);
    if (mOutputDirStr != null) {
      mOutputDir = new File(mOutputDirStr);
      if (!mOutputDir.exists()) {
        mOutputDir.mkdirs();
      }
    }

    // Initialize rankers
    compositeRanker = new CompositeRanker();
    ngramRanker = new NgramRanker();
    otherRanker = new OtherRanker();
    compositeRanker.addRanker(ngramRanker);
    compositeRanker.addRanker(otherRanker);
  }

  @Override
  public void processCas(CAS arg0) throws ResourceProcessException {
    System.out.println(">> Passage Ranking Writer Processing");
    // Import the CAS as a aJCas
    JCas aJCas = null;
    File outputFile = null;
    PrintWriter writer = null;
    try {
      aJCas = arg0.getJCas();
      try {
        outputFile = new File(Paths.get(mOutputDir.getAbsolutePath(), OUTPUT_FILENAME).toString());
        outputFile.getParentFile().mkdirs();
        writer = new PrintWriter(outputFile);
      } catch (FileNotFoundException e) {
        System.out.printf("Output file could not be written: %s\n",
                Paths.get(mOutputDir.getAbsolutePath(), OUTPUT_FILENAME).toString());
        return;
      }

      writer.println(",Question ID,True positives,False negatives,False positives,Precision,Recall,F1 score");
      
      // Retrieve all the questions for printout
      List<Question> allQuestions = UimaUtils.getAnnotations(aJCas, Question.class);
      List<Question> subsetOfQuestions0 = RandomUtils.getRandomSubset(allQuestions, 10);
           
      int questionCounter = subsetOfQuestions0.size();
      List<Question> subsetOfQuestions = new ArrayList(questionCounter);

      // TODO: Here one needs to sort the questions in ascending order of their question ID
      
      int[] tempArray = new int[questionCounter];
      
      for (int i = 0; i < questionCounter; i++) {
        tempArray[i] = Integer.parseInt((subsetOfQuestions0.get(i)).getId());
      }
      
      Arrays.sort(tempArray);
      
      for (int id : tempArray) {
        for (Question q : subsetOfQuestions0) {
          if (Integer.parseInt(q.getId()) == id) {
            subsetOfQuestions.add(q);
          }
        }
      }
      
      
      AddlStats[] addlStats = new AddlStats[3];
      
      for (int p = 0; p < addlStats.length; p++) {
        addlStats[p] = new AddlStats(aJCas);
      }
      
      /*Initialize Meteor scorer*/
      
      MeteorConfiguration config = new MeteorConfiguration();
      config.setLanguage("en");
      config.setNormalization(Constants.NORMALIZE_NO_PUNCT);
      MeteorScorer scorer = new MeteorScorer(config);
      
      
      /*Begin main stuff*/

      for (Question question : subsetOfQuestions) {

        
        List<Passage> passages = UimaUtils.convertFSListToList(question.getPassages(), Passage.class);
        

        List<Passage> ngramRankedPassages = ngramRanker.rank(aJCas, question, passages, scorer);
        List<Passage> otherRankedPassages = otherRanker.rank(aJCas, question, passages, scorer);
        List<Passage> compositeRankedPassages = compositeRanker.rank(aJCas, question, passages, scorer);
        
        List<List<Passage>> pig = new ArrayList<List<Passage>>();
        
        pig.add(ngramRankedPassages);
        pig.add(otherRankedPassages);
        pig.add(compositeRankedPassages);
        
        for (List<Passage> passageList : pig) {
          
          int k = pig.indexOf(passageList);
          
          int numPassages = passageList.size();
          int totalNumCorrect = 0;
          int rankThreshold = 5;
          
/*          Calculate the TP, etc. for the set of ranked passages that goes with a question.*/
          
          int TP = 0;
          int FP = 0;
          int TN = 0;
          int FN = 0;
          
          for (int i = 0; i < numPassages; i++) {
            if (((((Passage) passageList.get(i)).getLabel()) == true) && i < rankThreshold) {
              TP++;
            } else if (((((Passage) passageList.get(i)).getLabel()) == false) && i < rankThreshold) {
              FP++;
            } else if (((((Passage) passageList.get(i)).getLabel()) == true) && i >= rankThreshold) {
              FN++;
            } else if (((((Passage) passageList.get(i)).getLabel()) == false) && i >= rankThreshold) {
              TN++;
            }
          }
          
          /*Calculate the reciprocal rank for the ranked passages*/
          
          double RR = 0;
          
          for (int i = 0; i < numPassages; i++) {
            if ((((Passage) passageList.get(i)).getLabel()) == true ) {
              RR = (1 / (double) (i + 1));
              break;
             }
           }
          
          /*Calculate the average precision for the ranked passages*/
          
          double numCorrectRunningTotal = 0;
          double apRunningTotal = 0;
          
          for (int i = 0; i < numPassages; i++) {
            if ((((Passage) passageList.get(i)).getLabel()) == true ) {
              numCorrectRunningTotal++;
              apRunningTotal += (numCorrectRunningTotal / (double) (i+1));
            }
          }
              
          double AP;
          
          for (int i = 0; i < numPassages; i++) {
            if ((((Passage) passageList.get(i)).getLabel()) == true) {
              totalNumCorrect++;
            }
          }
          
          if (totalNumCorrect > 0) {
            AP = apRunningTotal / (double) totalNumCorrect;
          } else {
            AP = 0;
          }
          
          /*Calculate the precision, recall, accuracy, error, and f1 for the ranked passages*/
          
          double tp = (double) TP;
          double fp = (double) FP;
          double tn = (double) TN;
          double fn = (double) FN;
          double precision = 0;
          double recall = 0;
          double accuracy = 0;
          double error = 0;
          double f1 = 0;     
          
          if ((tp + fp) != 0) {
            precision = tp / (tp + fp);
          } else {
            precision = 0;
          }
          
          if ((tp + fn) != 0) {
            recall = tp / (tp + fn);
          } else {
            recall = 0;
          }
          
          if ((tp + fp + tn + fn) != 0) {
            accuracy = (tp + tn) / (tp + fp + tn + fn);
            error = (fp + fn) / (tp + fp + tn + fn);
          } else {
            accuracy = 0;
            error = 0;
          }
  
          if ((precision + recall) != 0) {
            f1 = (2 * ((precision * recall) / (precision + recall)));
          } else {
            f1 = 0;
          }
          
          double rrrt = addlStats[k].getRrRunningTotal();
          addlStats[k].setRrRunningTotal(rrrt + RR);
          
          double aprt = addlStats[k].getApRunningTotal();
          addlStats[k].setApRunningTotal(aprt + AP);
          
          double micaf1rt = addlStats[k].getMicroAverageF1RunningTotal();
          addlStats[k].setMicroAverageF1RunningTotal(micaf1rt + f1);
          
          double tprt = addlStats[k].getTpRunningTotal();
          addlStats[k].setTpRunningTotal(tprt + TP);
          
          double fprt = addlStats[k].getFpRunningTotal();
          addlStats[k].setFpRunningTotal(fprt + FP);
          
          double fnrt = addlStats[k].getFnRunningTotal();
          addlStats[k].setFnRunningTotal(fnrt + FN);
          
          
          BigDecimal bd1 = new BigDecimal(RR).setScale(3, RoundingMode.HALF_EVEN);
          RR = bd1.doubleValue();
          BigDecimal bd2 = new BigDecimal(AP).setScale(3, RoundingMode.HALF_EVEN);
          AP = bd2.doubleValue();
          BigDecimal bd3 = new BigDecimal(precision).setScale(3, RoundingMode.HALF_EVEN);
          precision = bd3.doubleValue();
          BigDecimal bd4 = new BigDecimal(recall).setScale(3, RoundingMode.HALF_EVEN);
          recall = bd4.doubleValue();
          BigDecimal bd5 = new BigDecimal(accuracy).setScale(3, RoundingMode.HALF_EVEN);
          accuracy = bd5.doubleValue();
          BigDecimal bd6 = new BigDecimal(error).setScale(3, RoundingMode.HALF_EVEN);
          error = bd6.doubleValue();
          BigDecimal bd7 = new BigDecimal(f1).setScale(3, RoundingMode.HALF_EVEN);
          f1 = bd7.doubleValue();
          
        
          if (k == 0) {
            writer.write("Ngram ranker" + "," + question.getId() + "," + Double.toString(tp) + "," + Double.toString(fn) + "," + 
            Double.toString(fp) + "," + Double.toString(precision) + "," + Double.toString(recall) + "," + Double.toString(f1) + "\n");
          } else if (k == 1) {
            writer.write("Other ranker" + "," + "," + Double.toString(tp) + "," + Double.toString(fn) + "," + 
            Double.toString(fp) + "," + Double.toString(precision) + "," + Double.toString(recall) + "," + Double.toString(f1) + "\n");
          } else {  /*if k == 2*/
            writer.write("Composite ranker" + "," + "," + Double.toString(tp) + "," + Double.toString(fn) + "," + 
            Double.toString(fp) + "," + Double.toString(precision) + "," + Double.toString(recall) + "," + Double.toString(f1) + "\n\n");
          }
        
        }
        
      }
      
      writer.write("\n");
      
      for (int K = 0; K < addlStats.length; K++) {
        
        double MAP = addlStats[K].getApRunningTotal() / (double) questionCounter;
        addlStats[K].setMAP(MAP);
        
        double MRR = addlStats[K].getRrRunningTotal() / (double) questionCounter;
        addlStats[K].setMRR(MRR);
        
        double microAverageF1 = addlStats[K].getMicroAverageF1RunningTotal() / (double) questionCounter;
        addlStats[K].setMicroF1(microAverageF1);
        
        double macroPrecision = 0;
        double macroRecall = 0;
        double macroF1 = 0;
        
        if ((addlStats[K].getTpRunningTotal() + addlStats[K].getFpRunningTotal()) != 0) {
          macroPrecision = addlStats[K].getTpRunningTotal() / (addlStats[K].getTpRunningTotal() + addlStats[K].getFpRunningTotal());
        } else {
          macroPrecision = 0;
        }
        
        if ((addlStats[K].getTpRunningTotal() + addlStats[K].getFnRunningTotal()) != 0) {
          macroRecall = addlStats[K].getTpRunningTotal() / (addlStats[K].getTpRunningTotal() + addlStats[K].getFnRunningTotal());
        } else {
          macroRecall = 0;
        }       
        
        if ((macroPrecision + macroRecall) != 0) {
          macroF1 = (2 * ((macroPrecision * macroRecall) / (macroPrecision + macroRecall)));
        } else {
          macroF1 = 0;
        }
        
        addlStats[K].setMacroF1(macroF1);
        
      }
      
      for (int K = 0; K < addlStats.length; K++) {
        
        if (K == 0) {
          writer.write("Additional statistics for Ngram ranker:\n");
        } else if (K == 1) {
          writer.write("Additional statistics for Other ranker:\n");
        } else {
          writer.write("Additional statistics for Composite ranker:\n");
        }
        
        BigDecimal bd8 = new BigDecimal(addlStats[K].getMAP()).setScale(3, RoundingMode.HALF_EVEN);
        addlStats[K].setMAP(bd8.doubleValue());
        BigDecimal bd9 = new BigDecimal(addlStats[K].getMRR()).setScale(3, RoundingMode.HALF_EVEN);
        addlStats[K].setMRR(bd9.doubleValue());
        BigDecimal bd10 = new BigDecimal(addlStats[K].getMicroF1()).setScale(3, RoundingMode.HALF_EVEN);
        addlStats[K].setMicroF1(bd10.doubleValue());
        BigDecimal bd11 = new BigDecimal(addlStats[K].getMacroF1()).setScale(3, RoundingMode.HALF_EVEN);
        addlStats[K].setMacroF1(bd11.doubleValue());

        writer.write("\nMAP = " + Double.toString(addlStats[K].getMAP()));
        writer.write("\nMRR = " + Double.toString(addlStats[K].getMRR()));
        writer.write("\nMicro-average F1 = " + Double.toString(addlStats[K].getMicroF1()));
        writer.write("\nMacro-average F1 = " + Double.toString(addlStats[K].getMacroF1()));
        writer.write("\nTP total = " + Double.toString(addlStats[K].getTpRunningTotal()));
        writer.write("\nFP total = " + Double.toString(addlStats[K].getFpRunningTotal()));
        writer.write("\nFN total = " + Double.toString(addlStats[K].getFnRunningTotal()) + "\n\n");
        
      }
      
      
      
      
    } catch (CASException e) {
      try {
        throw new CollectionException(e);
      } catch (CollectionException e1) {
        e1.printStackTrace();
      }
    } finally {
      if (writer != null)
        writer.close();
    }
    
  }
}

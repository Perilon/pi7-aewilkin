import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
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

      writer.println(",question_id,tp,fn,fp,precision,recall,f1");
      // Retrieve all the questions for printout
      List<Question> allQuestions = UimaUtils.getAnnotations(aJCas, Question.class);
      List<Question> subsetOfQuestions = RandomUtils.getRandomSubset(allQuestions, 10);

      // TODO: Here one needs to sort the questions in ascending order of their question ID
      
//      List<Question> orderedSubsOfQs = new ArrayList<Question>();
//      
//      for (Question question : subsetOfQuestions) {
//        if (orderedSubsOfQs.size() == 0) {
//          orderedSubsOfQs.
//        }
//      }
      
      
      AddlStats[] addlStats = new AddlStats[3];

      for (Question question : subsetOfQuestions) {
        
        int questionCounter = 0;
        double apRunningTotal = 0;
        double rrRunningTotal = 0;
        double microAverageF1RunningTotal = 0;
        double tpRunningTotal = 0;
        double fpRunningTotal = 0;
        double fnRunningTotal = 0;
        
        List<Passage> passages = UimaUtils.convertFSListToList(question.getPassages(), Passage.class);
        

        List<Passage> ngramRankedPassages = ngramRanker.rank(aJCas, question, passages);
        List<Passage> otherRankedPassages = otherRanker.rank(aJCas, question, passages);
        List<Passage> compositeRankedPassages = compositeRanker.rank(aJCas, question, passages);
        
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
          
          for (int i = 0; i < numPassages; i++) {
            if ((((Passage) passageList.get(i)).getLabel()) == true ) {
              numCorrectRunningTotal++;
              apRunningTotal += (numCorrectRunningTotal / (double) (i+1));
            }
          }
              
          double AP;
          
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
          addlStats[k].setRrRunningTotal(aprt + AP);
          double micaf1rt = addlStats[k].getMicroAverageF1RunningTotal();
          addlStats[k].setMicroAverageF1RunningTotal(micaf1rt + f1);
          double tprt = addlStats[k].getRrRunningTotal();
          addlStats[k].setRrRunningTotal(tprt + TP);
          double fprt = addlStats[k].getRrRunningTotal();
          addlStats[k].setRrRunningTotal(fprt + FP);
          double fnrt = addlStats[k].getRrRunningTotal();
          addlStats[k].setRrRunningTotal(fnrt + FN);
          
//          rrRunningTotal += RR;
//          apRunningTotal += AP;
//          microAverageF1RunningTotal += f1;
//          
//          tpRunningTotal += TP;
//          fpRunningTotal += FP;
//          fnRunningTotal += FN;
          
        
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

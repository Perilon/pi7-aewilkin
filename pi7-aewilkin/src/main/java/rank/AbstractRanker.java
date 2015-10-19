package rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import type.Measurement;
import type.Passage;
import type.Question;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import edu.cmu.meteor.scorer.MeteorConfiguration;
import edu.cmu.meteor.scorer.MeteorScorer;
import edu.cmu.meteor.util.Constants;


/**
 * This class provides a skeletal implementation of interface IRanker.
 */
public abstract class AbstractRanker implements IRanker {

  /**
   * Sorts the given list of passages associated with the given question, and returns a ranked list
   * of passages. A subclass needs to implement this method.
   * 
   * @param aJCas
   * @param question
   * @param passages
   */
  
  
  public List<Passage> rank(JCas aJCas, Question question, List<Passage> passages, MeteorScorer scorer) {
    // TODO Complete the implementation of this method.

    // Score all the given passages and sort them in List object 'rankedPassages' below.
    
    System.out.println("numPassages = " + Integer.toString(passages.size()));
    
    
    int numPassages = passages.size();
    
    List<Passage> rankedPassages = new ArrayList<Passage>();
    
    class Psg implements Comparable {
      
      private int begin;
      private int end;
      private String sentence;
      private String sentLem;
      private double score;
      private boolean label;
      private String sourceDocID;
      private String id;
     
      public Psg() {
      }
      
      public void setBegin(int bg) {
        this.begin = bg;
      }
      
      public void setEnd(int e) {
        this.end = e;
      }
      
      public void setSentence(String sent) {
        this.sentence = sent;
      }
      
      public void setSentLem(String sentLem) {
        this.sentLem = sentLem;
      }
      
      public void setScore(double sc) {
        this.score = sc;
      }
      
      public void setLabel(boolean lb) {
        this.label = lb;
      }
      
      public void setSourceDocID(String sdi) {
        this.sourceDocID = sdi;
      }
      
      public void setID(String id) {
        this.id = id;
      }
      
      public int getBegin() {
        return this.begin;
      }
      
      public int getEnd() {
        return this.end;
      }
      
      public String getSentence() {
        return this.sentence;
      }
      
      public String getSentLem() {
        return this.sentLem;
      }
      
      public double getScore() {
        return this.score;
      }
      
      public boolean getLabel() {
        return this.label;
      }
      
      public String getSourceDocID() {
        return this.sourceDocID;
      }
      
      public String getId() {
        return this.id;
      }

      public int compareTo(Object anotherPsg) {
        if (!(anotherPsg instanceof Psg)) {
          throw new ClassCastException("A Passage object is expected.");
        }
        double anotherPsgScore = ((Psg) anotherPsg).getScore();
        return Double.compare(anotherPsgScore, this.score);
      }
    }
    
    /*Put each member of passageFSArray into a regular array; then sort the array*/
    
    Psg[] psgArray = new Psg[numPassages];
    
    for (int i = 0; i < numPassages; i++) {
      Passage passage = (Passage) passages.get(i);
      
      Psg psg = new Psg();
      
      psg.setBegin(passage.getBegin());
      psg.setEnd(passage.getEnd());
            
      psg.setScore(score(aJCas, question, passage, scorer));
      
      psg.setSentence(passage.getSentence());
      psg.setLabel(passage.getLabel());
      psg.setSourceDocID(passage.getSourceDocId());
      
      psgArray[i] = psg;
    }
    
    Arrays.sort(psgArray);
    
    /*Then put the items in that array, which are now ordered by score, into a new FSArray.  That FSArray becomes the
    RankedPassageFSArray feature of the QASet*/
        
    for (int i = 0; i < numPassages; i++) {
      Passage newPass = new Passage(aJCas);
      
      newPass.setBegin(psgArray[i].getBegin());
      newPass.setEnd(psgArray[i].getEnd());
      newPass.setScore(psgArray[i].getScore());
      newPass.setSentence(psgArray[i].getSentence());
      newPass.setLabel(psgArray[i].getLabel());
      newPass.setSourceDocId(psgArray[i].getSourceDocID());
      
      /*We're still in the same question of the question iterator existing below the iterator of the QASet, so this should
      be the right one.*/
      
      newPass.setQuestion(question);
      
//      rankedPassages.set(i, newPass);
      
      rankedPassages.add(newPass);
      
    }
    return rankedPassages;

    
  }


  /**
   * Returns a score of the given passage associated with the given question. A subclass needs to
   * implement this method.
   * 
   * @param aJCas
   * @param question
   * @param passage
   * @return
   */
  public abstract Double score(JCas aJCas, Question question, Passage passage, MeteorScorer scorer);
  
  

}




package rank;

import java.util.ArrayList;
import java.util.List;

import type.Passage;
import type.Question;
import org.apache.uima.jcas.JCas;

import edu.cmu.meteor.scorer.MeteorConfiguration;
import edu.cmu.meteor.scorer.MeteorScorer;
import edu.cmu.meteor.util.Constants;


public class CompositeRanker extends AbstractRanker implements IAggregator {

  /** Individual rankers */
  private List<IRanker> rankers;

  public CompositeRanker() {
    rankers = new ArrayList<IRanker>();
  }

  public void addRanker(IRanker ranker) {
    rankers.add(ranker);
  }

  /**
   * Returns a score of the given passage associated with the given question.
   * 
   * @param question
   * @param passage
   * @return a score of the passage
   */
  
  
  @Override
  public Double score(JCas aJCas, Question question, Passage passage, MeteorScorer scorer) {
    List<Double> scores = new ArrayList<Double>();
    for (IRanker r : rankers) {
      scores.add(r.score(aJCas, question, passage, scorer));
    }
    return aggregateScores(scores);
  }

  @Override
  public Double aggregateScores(List<Double> scores) {
    // TODO Complete the implementation of this method.

    // In PI7, compute the aggregated score by taking a weighted average of scores. Note that you
    // can figure out which score comes from which ranker because the index of List object 'scores'
    // corresponds to the index of List object 'rankers'.
    
    double total = 0;
    double counter = 0;
    
    for (int i = 0; i < scores.size(); i++) {
      
      System.out.println("Score: " + Double.toString(scores.get(i)));
      
      if (i == 0) {
        total += (scores.get(i) * 0.80);
      } else if (i == 1) {
        total += (scores.get(i) * 0.20);
      }
      
      System.out.println("Total: " + Double.toString(scores.get(i)));
      
      counter++;
      
      System.out.println("Counter: " + Double.toString(counter));
      
    }
    
//    for (double score : scores) {
////      System.out.println("Score: " + Double.toString(score));
//      total += score;
////      System.out.println("Total: " + Double.toString(total));
//      counter++;
//      System.out.println("Counter: " + Double.toString(counter));
//    }
    
//    double weightedAvg = total / counter;
    
//    System.out.println("WeightedAvg: " + Double.toString(weightedAvg));
    System.out.println("\n");

//    return weightedAvg;
    
    return total;
    
  }

}

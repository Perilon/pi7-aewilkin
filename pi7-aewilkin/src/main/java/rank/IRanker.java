package rank;

import java.util.List;

import type.Passage;
import type.Question;
import org.apache.uima.jcas.JCas;

import edu.cmu.meteor.scorer.MeteorConfiguration;
import edu.cmu.meteor.scorer.MeteorScorer;
import edu.cmu.meteor.util.Constants;


public interface IRanker {

  /**
   * Sorts the given list of passages associated with the given question, and returns a ranked list
   * of passages.
   * 
   * @param question
   * @param passages
   */
  public List<Passage> rank(JCas aJCas, Question question, List<Passage> passages, MeteorScorer scorer);

  /**
   * Returns a score of the given passage associated with the given question.
   * 
   * @param question
   * @param passage
   * @return a score of the passage
   */
  public Double score(JCas aJCas, Question question, Passage passage, MeteorScorer scorer);

}

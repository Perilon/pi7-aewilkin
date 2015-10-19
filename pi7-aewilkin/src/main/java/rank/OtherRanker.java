package rank;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import type.InputDocument;
import type.Ngram;
import type.Passage;
import type.Question;
import type.Token;

import edu.cmu.meteor.scorer.MeteorConfiguration;
import edu.cmu.meteor.scorer.MeteorScorer;
import edu.cmu.meteor.util.Constants;


public class OtherRanker extends AbstractRanker {

  /**
   * Returns a score of the given passage associated with the given question.
   * 
   * @param aJCas
   * @param question
   * @param passage
   * @return a score of the passage
   */
  
    @Override
    public Double score(JCas aJCas, Question question, Passage passage, MeteorScorer scorer) {

    
    String q = question.getSentence();
    String p = passage.getSentence();

    
    double meteorScore = scorer.getMeteorStats(q, p).score;
      
    meteorScore = meteorScore * ((double) question.getLength() / (double) passage.getLength());
    
    return meteorScore;

  }
    

}



package rank;

import java.util.Arrays;
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


public class NgramRanker extends AbstractRanker {

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

    FSIndex inputDocumentIndex = aJCas.getAnnotationIndex(InputDocument.type);   
    FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);
    
    

    List<Token> tokenQuestionList = JCasUtil.selectCovered(aJCas, Token.class, question.getBegin() - 1, question.getEnd());
    
    int questionListLen = tokenQuestionList.size();
        
    String[] tokenQuestionStringArray = new String[questionListLen];

    for (int i = 0; i < questionListLen; i++) {
      tokenQuestionStringArray[i] = tokenQuestionList.get(i).getToStringValue();
    }
    
//    System.out.println("tokenQuestionStringArray: ");
//    System.out.println(Arrays.toString(tokenQuestionStringArray) + "\n");
    
    
    /*Do the same thing for the ngrams.*/
    
    FSIndex nGramIndexQuestion = aJCas.getAnnotationIndex(Ngram.type);
    List<Ngram> nGramQuestionList = JCasUtil.selectCovered(aJCas, Ngram.class, question.getBegin() - 1, question.getEnd());
    int questionNgramLen = nGramQuestionList.size();
    String[] nGramQuestionStringArray = new String[questionNgramLen];
    for (int i = 0; i < questionNgramLen; i++) {
      nGramQuestionStringArray[i] = nGramQuestionList.get(i).getToStringValue();
    }

    
//    Now for the passage, get the tokens, put their string versions in a string array, and calculate the score.
  
    
    List<Token> tokenPassageList = JCasUtil.selectCovered(aJCas, Token.class, passage.getBegin() - 1, passage.getEnd());
    
    int passageListLen = tokenPassageList.size();
    
    String[] tokenPassageStringArray = new String[passageListLen];
    
    for (int j = 0; j < passageListLen; j++) {
      tokenPassageStringArray[j] = tokenPassageList.get(j).getToStringValue();
    }
      
//    System.out.println("tokenPassageStringArray: ");
//    System.out.println(Arrays.toString(tokenPassageStringArray) + "\n");
      
    /*Get the passage ngrams too.*/
    
    FSIndex nGramIndexPassage = aJCas.getAnnotationIndex(Ngram.type);
    List<Ngram> nGramPassageList = JCasUtil.selectCovered(aJCas, Ngram.class, passage.getBegin() - 1, passage.getEnd());
    int passageNgramLen = nGramPassageList.size();
    String[] nGramPassageStringArray = new String[passageNgramLen];
    for (int h = 0; h < passageNgramLen; h++) {
      nGramPassageStringArray[h] = nGramPassageList.get(h).getToStringValue();
    }
    
    
    /*Calculate score for tokens.*/

    int matchesCounter = 0;
    int worthyMatches = 0;
    
    for (int k = 0; k < tokenQuestionStringArray.length; k++) {

      for (int L = 0; L < tokenPassageStringArray.length; L++) {
        
        /*Precision is calculated with the numerator being the number of matches (naturally) and the denominator
        being the number of words that represent "real" tokens in the passage; i.e., this disincludes HTML
        tags and the words they enclose.*/
        
        if (!tokenPassageStringArray[L].equals("") &&
                tokenPassageStringArray[L].charAt(0) != '<' && 
                tokenPassageStringArray[L].charAt(tokenPassageStringArray[L].length() - 1) != '>') {

          worthyMatches++;
          if (tokenQuestionStringArray[k].equals(tokenPassageStringArray[L])) {
            matchesCounter++;
          }
        }
      }
    }
      
  
    
    double precision = (double) matchesCounter / (double) worthyMatches;
    double recall = (double) matchesCounter / (double) tokenQuestionStringArray.length;
    double F1;
    
//    System.out.println("matchesCounter: " + Double.toString(matchesCounter));
//    System.out.println("worthyMatches: " + Double.toString(worthyMatches));
//    System.out.println("precision: " + Double.toString(precision));
//    System.out.println("recall: " + Double.toString(recall) + "\n");
    
    if ((precision + recall) != 0) {
      F1 = 2 * ((precision * recall) / (precision + recall));
    } else
      F1 = 0;
    
    
    
    /*Calculate score for ngrams.*/
    
    int matchesCounterNG = 0;
    int worthyMatchesNG = 0;
    
    for (int m = 0; m < nGramQuestionStringArray.length; m++) {

      for (int n = 0; n < nGramPassageStringArray.length; n++) {
        
        /*Precision is calculated with the numerator being the number of matches (naturally) and the denominator
        being the number of words that represent "real" tokens in the passage; i.e., this disincludes HTML
        tags and the words they enclose.*/
        
        if (!nGramPassageStringArray[n].equals("") &&
                nGramPassageStringArray[n].charAt(0) != '<' && 
                        nGramPassageStringArray[n].charAt(nGramPassageStringArray[n].length() - 1) != '>') {
          worthyMatchesNG++;
          if (nGramQuestionStringArray[m].equals(nGramPassageStringArray[n])) {
            matchesCounterNG++;
          }
        }
      }
    }
      
      
    double precisionNG = (double) matchesCounterNG / (double) worthyMatchesNG;
    double recallNG = (double) matchesCounterNG / (double) tokenQuestionStringArray.length;
    double F1NG;
    
    if ((precisionNG + recallNG) != 0) {
      F1NG = 2 * ((precisionNG * recallNG) / (precisionNG + recallNG));
    } else
      F1NG = 0;
    
    
//    passage.setScoreNgram(F1NG);
    
//    passage.addToIndexes();
    
    
    return F1NG;

  }
    

}



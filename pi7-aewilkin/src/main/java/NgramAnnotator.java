import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.fit.util.JCasUtil;

import type.Passage;
import type.Question;
import type.Token;
import type.Ngram;

public class NgramAnnotator extends JCasAnnotator_ImplBase {
   
  
  int n = 2;
  
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {

//    FSIndex answerIndex = aJCas.getAnnotationIndex(Passage.type);
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    FSIndex tokenIndex = aJCas.getAnnotationIndex(Token.type);
    
    
    
    
    Iterator qIter = questionIndex.iterator();
    while (qIter.hasNext()) {
      Question q = (Question) qIter.next();
            
      List<Passage> oinkapig = UimaUtils.convertFSListToList(q.getPassages(), Passage.class);
      
      for (Passage answer : oinkapig) {
    
    
    
//    Iterator answerIter = answerIndex.iterator();
//        
//    while (answerIter.hasNext()) {
//      
//      Passage answer = (Passage) answerIter.next();
      int beginAnswer = answer.getBegin();
      int endAnswer = answer.getEnd();
      
      List<Token> tokenAnswerList = JCasUtil.selectCovered(aJCas, Token.class, beginAnswer - 1, endAnswer);
      
      int listLen = tokenAnswerList.size();
            
      for (int i = 0; i < listLen - (n-1); i++) {
        
        FSArray tokensArray = new FSArray(aJCas, n);
        
        for (int N = 0; N < n; N++) {
          tokensArray.set(N, (Token) tokenAnswerList.get(i+N));
        }
        
        Ngram ngram = new Ngram(aJCas);
        ngram.setBegin(  tokenAnswerList.get(i).getBegin()  );
        ngram.setEnd(  tokenAnswerList.get(i+(n-1)).getEnd()  );
        
//        Here we set the ngram's string value.
        
        String tempNgramToString = "";
        
        for (int j = 0; j < tokensArray.size(); j++) {          
          tempNgramToString += (tokenAnswerList.get(j+i).getToStringValue() + " ");
        }
        
        String nGramToString = tempNgramToString.trim();  

        
        ngram.setToStringValue(nGramToString);
        
//        Here we set the value of n and also the tokens as an FSArray.
        
        ngram.setN(n);

        ngram.setTokens(tokensArray);

        ngram.addToIndexes();
        
      }
      }
    }
    
    Iterator questionIter = questionIndex.iterator();
    
    while (questionIter.hasNext()) {
      
      Question question = (Question) questionIter.next();
      int beginQuestion = question.getBegin();
      int endQuestion = question.getEnd();
      
      List<Token> tokenQuestionList = JCasUtil.selectCovered(aJCas, Token.class, beginQuestion - 1, endQuestion);
      
      int listLen = tokenQuestionList.size();
            
      for (int i = 0; i < listLen - (n-1); i++) {
        
        FSArray tokensArray = new FSArray(aJCas, n);
        
        for (int N = 0; N < n; N++) {
          tokensArray.set(N, tokenQuestionList.get(i+N));
        }
        
        Ngram ngram = new Ngram(aJCas);
        ngram.setBegin(  tokenQuestionList.get(i).getBegin()  );
        ngram.setEnd(  tokenQuestionList.get(i+(n-1)).getEnd()  );
        
        
//      Here we set the ngram's string value.
        
      String tempNgramToString = "";
      
      for (int j = 0; j < tokensArray.size(); j++) {
        tempNgramToString += (tokenQuestionList.get(j+i).getToStringValue() + " ");
      }
      
      String nGramToString = tempNgramToString.trim();       
      
      ngram.setToStringValue(nGramToString);
      
//      Here we set the value of n and also the tokens as an FSArray.
        
        
        ngram.setN(n);

        ngram.setTokens(tokensArray);

        ngram.addToIndexes();
        
      }
    }
    
  }
}


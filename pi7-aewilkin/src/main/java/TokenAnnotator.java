import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import type.Passage;
import type.Question;
import type.Token;


public class TokenAnnotator extends JCasAnnotator_ImplBase {
  
  private Pattern mTokenPattern = 
          Pattern.compile("([^\\s]+)");

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    
    StanfordLemmatizer lemmatizer = new StanfordLemmatizer();    

    FSIndex passageIndex = aJCas.getAnnotationIndex(Passage.type);
    FSIndex questionIndex = aJCas.getAnnotationIndex(Question.type);
    
    
    
    
    Iterator qIter = questionIndex.iterator();
    while (qIter.hasNext()) {
      Question q = (Question) qIter.next();
            
      List<Passage> oinkapig = UimaUtils.convertFSListToList(q.getPassages(), Passage.class);
      
      for (Passage passage : oinkapig) {
    
      int passageTokCounter = 0;
    
//    Iterator passageIter = passageIndex.iterator();
//    
//    while (passageIter.hasNext()) {
//      Passage passage = (Passage) passageIter.next();
      
//      System.out.println( "passage.getSentence() = " + passage.getSentence()); 
    
      
      Matcher matcher = mTokenPattern.matcher(passage.getSentence());
      int pos = 0;

      while (matcher.find(pos)) {
        passageTokCounter++;
        Token token = new Token(aJCas);
        
//        System.out.println("Passage begin is: " + Integer.toString(passage.getBegin()));
//        System.out.println("Passage end is: " + Integer.toString(passage.getEnd()));
        
        
        token.setBegin(passage.getBegin() + matcher.start(1));
        token.setEnd(passage.getBegin() + matcher.end(1));

        
        String ts = token.getCoveredText();
        
//        System.out.println( "ts = " + ts); 

        
        StringBuilder builder = new StringBuilder();
       
        for (char c : ts.toCharArray()) {
          if (Character.isLetterOrDigit(c)) {
            builder.append(Character.isLowerCase(c) ? c: Character.toLowerCase(c));
          } else if (c == '<' || c == '>') {
            builder.append(c);
          }
        }
        
        String finalToken = builder.toString();
        
        String finalTokenLemmatized = lemmatizer.lemmatize(finalToken);
        
        token.setToStringValue(finalTokenLemmatized);
        
        token.addToIndexes();
                       
        pos = matcher.end();
      }
      
      passage.setLength(passageTokCounter);
        
      }
    }
    
    Iterator questionIter = questionIndex.iterator();
    
    while (questionIter.hasNext()) {
      Question question = (Question) questionIter.next();
      
//      System.out.println( "question.getSentence() = " + question.getSentence()); 

      int questionTokCounter = 0;
      
      Matcher matcher = mTokenPattern.matcher(question.getSentence());
      int pos = 0;
      int index = 0;
      while (matcher.find(pos)) {
        
        questionTokCounter++;
        
        Token token = new Token(aJCas);
        token.setBegin(question.getBegin() + matcher.start(1));
        token.setEnd(question.getBegin() + matcher.end(1));
        
        String ts = token.getCoveredText();
        
      
        StringBuilder builder = new StringBuilder();
       
        for (char c : ts.toCharArray()) {
          
          /*Get rid of all punctuation here except the angle brackets that set off HTML tags; these, since they
          enclose words that we also want to not count as proper tokens, are left in here so that the whole
          tokens they enclose can be ignored later in InputDocumentAnnotator.*/
          
          if (Character.isLetterOrDigit(c)) {
            builder.append(Character.isLowerCase(c) ? c: Character.toLowerCase(c));
          } else if (c == '<' || c == '>') {
            builder.append(c);
          }
        }
        
        String finalToken = builder.toString();
        
        String finalTokenLemmatized = lemmatizer.lemmatize(finalToken);
        
        token.setToStringValue(finalTokenLemmatized);

        
        token.setToStringValue(finalToken);        
        
        token.addToIndexes();
        pos = matcher.end();
        
        index++;
      }
      question.setLength(questionTokCounter);
    }
  }
}


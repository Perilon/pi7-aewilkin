import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;


public class StanfordLemmatizer {
  
  private Pattern mTokenPattern = 
          Pattern.compile("([^\\s]+)");

  protected StanfordCoreNLP pipeline;

  public StanfordLemmatizer() {
      // Create StanfordCoreNLP object properties, with POS tagging
      // (required for lemmatization), and lemmatization
      Properties props;
      props = new Properties();
      props.put("annotators", "tokenize, ssplit, pos, lemma");

      
      RedwoodConfiguration.empty().apply();
      this.pipeline = new StanfordCoreNLP(props);
      // enable stderr again
      RedwoodConfiguration.current().clear().apply();

      }

  public String lemmatize(String documentText) {

    
    
      String[] docTextArray = documentText.split("([\\s]+)");
      
      
      StringBuilder sent = new StringBuilder();
      
      for (String word : docTextArray) {
        for (char c : word.toCharArray()) {
          if (Character.isLetterOrDigit(c)) {
            sent.append(Character.isLowerCase(c) ? c: Character.toLowerCase(c));

          }
        }
        sent.append(" ");
      }
      
    
      String sentString = sent.toString();
      sentString = sentString.trim();
      
   
      StringBuilder lemmas = new StringBuilder();

      // create an empty Annotation just with the given text
      Annotation sentAnno = new Annotation(sentString);

      // run all Annotators on this text
      this.pipeline.annotate(sentAnno);

      // Iterate over all of the sentences found
      List<CoreMap> sentences = sentAnno.get(SentencesAnnotation.class);
      for(CoreMap sentence: sentences) {
          // Iterate over all tokens in a sentence
          for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
            
//            
                  lemmas.append(token.getString(LemmaAnnotation.class));
                  lemmas.append(" ");
//                }
          }
      }
      

      String lemmasString = lemmas.toString();
      lemmasString = lemmasString.trim();
            
      return lemmasString;
  }
}


/* First created by JCasGen Sun Oct 04 16:19:56 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Stores the information of the passage.
 * Updated by JCasGen Mon Oct 19 17:55:16 EDT 2015
 * XML source: /home/perilon/git/pi7-aewilkin/pi7-aewilkin/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class Passage extends ComponentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Passage.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Passage() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Passage(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Passage(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Passage(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: sentence

  /** getter for sentence - gets The passage text extracted from the source document.
   * @generated
   * @return value of the feature 
   */
  public String getSentence() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "type.Passage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sentence);}
    
  /** setter for sentence - sets The passage text extracted from the source document. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentence(String v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "type.Passage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sentence, v);}    
   
    
  //*--------------*
  //* Feature: label

  /** getter for label - gets This stores a gold standard label of this answer.  'True' means that it answers the question; otherwise, it is does not.
   * @generated
   * @return value of the feature 
   */
  public boolean getLabel() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.Passage");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_label);}
    
  /** setter for label - sets This stores a gold standard label of this answer.  'True' means that it answers the question; otherwise, it is does not. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLabel(boolean v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_label == null)
      jcasType.jcas.throwFeatMissing("label", "type.Passage");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Passage_Type)jcasType).casFeatCode_label, v);}    
   
    
  //*--------------*
  //* Feature: sourceDocId

  /** getter for sourceDocId - gets This specifies the source document id to which the passage belongs to.
   * @generated
   * @return value of the feature 
   */
  public String getSourceDocId() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sourceDocId == null)
      jcasType.jcas.throwFeatMissing("sourceDocId", "type.Passage");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sourceDocId);}
    
  /** setter for sourceDocId - sets This specifies the source document id to which the passage belongs to. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSourceDocId(String v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_sourceDocId == null)
      jcasType.jcas.throwFeatMissing("sourceDocId", "type.Passage");
    jcasType.ll_cas.ll_setStringValue(addr, ((Passage_Type)jcasType).casFeatCode_sourceDocId, v);}    
   
    
  //*--------------*
  //* Feature: question

  /** getter for question - gets This denotes the corresponding question ID.
   * @generated
   * @return value of the feature 
   */
  public Question getQuestion() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "type.Passage");
    return (Question)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Passage_Type)jcasType).casFeatCode_question)));}
    
  /** setter for question - sets This denotes the corresponding question ID. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuestion(Question v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_question == null)
      jcasType.jcas.throwFeatMissing("question", "type.Passage");
    jcasType.ll_cas.ll_setRefValue(addr, ((Passage_Type)jcasType).casFeatCode_question, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: scoreNgram

  /** getter for scoreNgram - gets The score for a passage as determined by the ngram ranker
   * @generated
   * @return value of the feature 
   */
  public double getScoreNgram() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_scoreNgram == null)
      jcasType.jcas.throwFeatMissing("scoreNgram", "type.Passage");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Passage_Type)jcasType).casFeatCode_scoreNgram);}
    
  /** setter for scoreNgram - sets The score for a passage as determined by the ngram ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScoreNgram(double v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_scoreNgram == null)
      jcasType.jcas.throwFeatMissing("scoreNgram", "type.Passage");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Passage_Type)jcasType).casFeatCode_scoreNgram, v);}    
   
    
  //*--------------*
  //* Feature: scoreOther

  /** getter for scoreOther - gets The score for a passage as determined by the "Other" ranker.
   * @generated
   * @return value of the feature 
   */
  public double getScoreOther() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_scoreOther == null)
      jcasType.jcas.throwFeatMissing("scoreOther", "type.Passage");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Passage_Type)jcasType).casFeatCode_scoreOther);}
    
  /** setter for scoreOther - sets The score for a passage as determined by the "Other" ranker. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setScoreOther(double v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_scoreOther == null)
      jcasType.jcas.throwFeatMissing("scoreOther", "type.Passage");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Passage_Type)jcasType).casFeatCode_scoreOther, v);}    
   
    
  //*--------------*
  //* Feature: length

  /** getter for length - gets How many tokens are in the passage
   * @generated
   * @return value of the feature 
   */
  public int getLength() {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_length == null)
      jcasType.jcas.throwFeatMissing("length", "type.Passage");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Passage_Type)jcasType).casFeatCode_length);}
    
  /** setter for length - sets How many tokens are in the passage 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLength(int v) {
    if (Passage_Type.featOkTst && ((Passage_Type)jcasType).casFeat_length == null)
      jcasType.jcas.throwFeatMissing("length", "type.Passage");
    jcasType.ll_cas.ll_setIntValue(addr, ((Passage_Type)jcasType).casFeatCode_length, v);}    
  }

    
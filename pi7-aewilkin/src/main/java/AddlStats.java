

/* First created by JCasGen Mon Oct 19 01:50:52 EDT 2015 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** For macro and micro F1 and MAP and MRR
 * Updated by JCasGen Mon Oct 19 01:50:54 EDT 2015
 * XML source: /home/perilon/git/pi7-aewilkin/pi7-aewilkin/src/main/resources/descriptors/typeSystem.xml
 * @generated */
public class AddlStats extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AddlStats.class);
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
  protected AddlStats() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public AddlStats(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public AddlStats(JCas jcas) {
    super(jcas);
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
  //* Feature: macroF1

  /** getter for macroF1 - gets Macro F1 for one type of ranker
   * @generated
   * @return value of the feature 
   */
  public double getMacroF1() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_macroF1 == null)
      jcasType.jcas.throwFeatMissing("macroF1", "AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_macroF1);}
    
  /** setter for macroF1 - sets Macro F1 for one type of ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMacroF1(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_macroF1 == null)
      jcasType.jcas.throwFeatMissing("macroF1", "AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_macroF1, v);}    
   
    
  //*--------------*
  //* Feature: microF1

  /** getter for microF1 - gets Micro F1 for one type of ranker
   * @generated
   * @return value of the feature 
   */
  public double getMicroF1() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_microF1 == null)
      jcasType.jcas.throwFeatMissing("microF1", "AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_microF1);}
    
  /** setter for microF1 - sets Micro F1 for one type of ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMicroF1(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_microF1 == null)
      jcasType.jcas.throwFeatMissing("microF1", "AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_microF1, v);}    
   
    
  //*--------------*
  //* Feature: MAP

  /** getter for MAP - gets Mean average precision for one type of ranker
   * @generated
   * @return value of the feature 
   */
  public double getMAP() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_MAP == null)
      jcasType.jcas.throwFeatMissing("MAP", "AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_MAP);}
    
  /** setter for MAP - sets Mean average precision for one type of ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMAP(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_MAP == null)
      jcasType.jcas.throwFeatMissing("MAP", "AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_MAP, v);}    
   
    
  //*--------------*
  //* Feature: MRR

  /** getter for MRR - gets Mean reciprocal rank for one type of ranker
   * @generated
   * @return value of the feature 
   */
  public double getMRR() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_MRR == null)
      jcasType.jcas.throwFeatMissing("MRR", "AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_MRR);}
    
  /** setter for MRR - sets Mean reciprocal rank for one type of ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMRR(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_MRR == null)
      jcasType.jcas.throwFeatMissing("MRR", "AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_MRR, v);}    
  }

    
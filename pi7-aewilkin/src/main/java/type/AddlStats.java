

/* First created by JCasGen Mon Oct 19 01:51:18 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** For macro and micro F1 and MAP and MRR
 * Updated by JCasGen Mon Oct 19 17:55:16 EDT 2015
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
      jcasType.jcas.throwFeatMissing("macroF1", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_macroF1);}
    
  /** setter for macroF1 - sets Macro F1 for one type of ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMacroF1(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_macroF1 == null)
      jcasType.jcas.throwFeatMissing("macroF1", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_macroF1, v);}    
   
    
  //*--------------*
  //* Feature: microF1

  /** getter for microF1 - gets Micro F1 for one type of ranker
   * @generated
   * @return value of the feature 
   */
  public double getMicroF1() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_microF1 == null)
      jcasType.jcas.throwFeatMissing("microF1", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_microF1);}
    
  /** setter for microF1 - sets Micro F1 for one type of ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMicroF1(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_microF1 == null)
      jcasType.jcas.throwFeatMissing("microF1", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_microF1, v);}    
   
    
  //*--------------*
  //* Feature: MAP

  /** getter for MAP - gets Mean average precision for one type of ranker
   * @generated
   * @return value of the feature 
   */
  public double getMAP() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_MAP == null)
      jcasType.jcas.throwFeatMissing("MAP", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_MAP);}
    
  /** setter for MAP - sets Mean average precision for one type of ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMAP(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_MAP == null)
      jcasType.jcas.throwFeatMissing("MAP", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_MAP, v);}    
   
    
  //*--------------*
  //* Feature: MRR

  /** getter for MRR - gets Mean reciprocal rank for one type of ranker
   * @generated
   * @return value of the feature 
   */
  public double getMRR() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_MRR == null)
      jcasType.jcas.throwFeatMissing("MRR", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_MRR);}
    
  /** setter for MRR - sets Mean reciprocal rank for one type of ranker 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMRR(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_MRR == null)
      jcasType.jcas.throwFeatMissing("MRR", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_MRR, v);}    
   
    
  //*--------------*
  //* Feature: rrRunningTotal

  /** getter for rrRunningTotal - gets Reciprocal rank running  total
   * @generated
   * @return value of the feature 
   */
  public double getRrRunningTotal() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_rrRunningTotal == null)
      jcasType.jcas.throwFeatMissing("rrRunningTotal", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_rrRunningTotal);}
    
  /** setter for rrRunningTotal - sets Reciprocal rank running  total 
   * @generated
   * @param v value to set into the feature 
   */
  public void setRrRunningTotal(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_rrRunningTotal == null)
      jcasType.jcas.throwFeatMissing("rrRunningTotal", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_rrRunningTotal, v);}    
   
    
  //*--------------*
  //* Feature: apRunningTotal

  /** getter for apRunningTotal - gets Average precision running total
   * @generated
   * @return value of the feature 
   */
  public double getApRunningTotal() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_apRunningTotal == null)
      jcasType.jcas.throwFeatMissing("apRunningTotal", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_apRunningTotal);}
    
  /** setter for apRunningTotal - sets Average precision running total 
   * @generated
   * @param v value to set into the feature 
   */
  public void setApRunningTotal(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_apRunningTotal == null)
      jcasType.jcas.throwFeatMissing("apRunningTotal", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_apRunningTotal, v);}    
   
    
  //*--------------*
  //* Feature: tpRunningTotal

  /** getter for tpRunningTotal - gets True positives running total
   * @generated
   * @return value of the feature 
   */
  public double getTpRunningTotal() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_tpRunningTotal == null)
      jcasType.jcas.throwFeatMissing("tpRunningTotal", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_tpRunningTotal);}
    
  /** setter for tpRunningTotal - sets True positives running total 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTpRunningTotal(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_tpRunningTotal == null)
      jcasType.jcas.throwFeatMissing("tpRunningTotal", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_tpRunningTotal, v);}    
   
    
  //*--------------*
  //* Feature: fpRunningTotal

  /** getter for fpRunningTotal - gets False positives running total
   * @generated
   * @return value of the feature 
   */
  public double getFpRunningTotal() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_fpRunningTotal == null)
      jcasType.jcas.throwFeatMissing("fpRunningTotal", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_fpRunningTotal);}
    
  /** setter for fpRunningTotal - sets False positives running total 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFpRunningTotal(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_fpRunningTotal == null)
      jcasType.jcas.throwFeatMissing("fpRunningTotal", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_fpRunningTotal, v);}    
   
    
  //*--------------*
  //* Feature: fnRunningTotal

  /** getter for fnRunningTotal - gets False negatives running total
   * @generated
   * @return value of the feature 
   */
  public double getFnRunningTotal() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_fnRunningTotal == null)
      jcasType.jcas.throwFeatMissing("fnRunningTotal", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_fnRunningTotal);}
    
  /** setter for fnRunningTotal - sets False negatives running total 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFnRunningTotal(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_fnRunningTotal == null)
      jcasType.jcas.throwFeatMissing("fnRunningTotal", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_fnRunningTotal, v);}    
   
    
  //*--------------*
  //* Feature: microAverageF1RunningTotal

  /** getter for microAverageF1RunningTotal - gets The micro-average F1 running total
   * @generated
   * @return value of the feature 
   */
  public double getMicroAverageF1RunningTotal() {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_microAverageF1RunningTotal == null)
      jcasType.jcas.throwFeatMissing("microAverageF1RunningTotal", "type.AddlStats");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_microAverageF1RunningTotal);}
    
  /** setter for microAverageF1RunningTotal - sets The micro-average F1 running total 
   * @generated
   * @param v value to set into the feature 
   */
  public void setMicroAverageF1RunningTotal(double v) {
    if (AddlStats_Type.featOkTst && ((AddlStats_Type)jcasType).casFeat_microAverageF1RunningTotal == null)
      jcasType.jcas.throwFeatMissing("microAverageF1RunningTotal", "type.AddlStats");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((AddlStats_Type)jcasType).casFeatCode_microAverageF1RunningTotal, v);}    
  }

    
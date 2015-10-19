
/* First created by JCasGen Mon Oct 19 01:50:52 EDT 2015 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** For macro and micro F1 and MAP and MRR
 * Updated by JCasGen Mon Oct 19 01:50:54 EDT 2015
 * @generated */
public class AddlStats_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AddlStats_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AddlStats_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AddlStats(addr, AddlStats_Type.this);
  			   AddlStats_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AddlStats(addr, AddlStats_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AddlStats.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("AddlStats");
 
  /** @generated */
  final Feature casFeat_macroF1;
  /** @generated */
  final int     casFeatCode_macroF1;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getMacroF1(int addr) {
        if (featOkTst && casFeat_macroF1 == null)
      jcas.throwFeatMissing("macroF1", "AddlStats");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_macroF1);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMacroF1(int addr, double v) {
        if (featOkTst && casFeat_macroF1 == null)
      jcas.throwFeatMissing("macroF1", "AddlStats");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_macroF1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_microF1;
  /** @generated */
  final int     casFeatCode_microF1;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getMicroF1(int addr) {
        if (featOkTst && casFeat_microF1 == null)
      jcas.throwFeatMissing("microF1", "AddlStats");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_microF1);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMicroF1(int addr, double v) {
        if (featOkTst && casFeat_microF1 == null)
      jcas.throwFeatMissing("microF1", "AddlStats");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_microF1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_MAP;
  /** @generated */
  final int     casFeatCode_MAP;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getMAP(int addr) {
        if (featOkTst && casFeat_MAP == null)
      jcas.throwFeatMissing("MAP", "AddlStats");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_MAP);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMAP(int addr, double v) {
        if (featOkTst && casFeat_MAP == null)
      jcas.throwFeatMissing("MAP", "AddlStats");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_MAP, v);}
    
  
 
  /** @generated */
  final Feature casFeat_MRR;
  /** @generated */
  final int     casFeatCode_MRR;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getMRR(int addr) {
        if (featOkTst && casFeat_MRR == null)
      jcas.throwFeatMissing("MRR", "AddlStats");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_MRR);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMRR(int addr, double v) {
        if (featOkTst && casFeat_MRR == null)
      jcas.throwFeatMissing("MRR", "AddlStats");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_MRR, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public AddlStats_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_macroF1 = jcas.getRequiredFeatureDE(casType, "macroF1", "uima.cas.Double", featOkTst);
    casFeatCode_macroF1  = (null == casFeat_macroF1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_macroF1).getCode();

 
    casFeat_microF1 = jcas.getRequiredFeatureDE(casType, "microF1", "uima.cas.Double", featOkTst);
    casFeatCode_microF1  = (null == casFeat_microF1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_microF1).getCode();

 
    casFeat_MAP = jcas.getRequiredFeatureDE(casType, "MAP", "uima.cas.Double", featOkTst);
    casFeatCode_MAP  = (null == casFeat_MAP) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_MAP).getCode();

 
    casFeat_MRR = jcas.getRequiredFeatureDE(casType, "MRR", "uima.cas.Double", featOkTst);
    casFeatCode_MRR  = (null == casFeat_MRR) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_MRR).getCode();

  }
}



    
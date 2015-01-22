package boebot;
public class Botsingdetectie{
  private IrDetectie irL;
  private IrDetectie irR;
  private VsDetectie vsR;
  private VsDetectie vsL;
  private boolean IrDetectL;
  private boolean IrDetectR;
  private boolean VsDetectL;
  private boolean VsDetectR;

  public Botsingdetectie(){
      //irL = new IrDetectie(8, 9);
      //irR = new IrDetectie(2, 0);
      vsL = new VsDetectie(14);
      vsR = new VsDetectie(15);
  }

  public boolean detectEdge(){
   // IrDetectL = irL.detect();
   // IrDetectR = irR.detect();
  //  if(!IrDetectL || !IrDetectR){
  //      return true;
  //  }else{
      return false;
  }

  public int detectObject(){
    VsDetectL = vsL.detect();
    VsDetectR = vsR.detect();

    if(VsDetectL && VsDetectR)
    {
      return 3;
    }
    else if(VsDetectL)
    {
      return 1;
    }
    else if(VsDetectR)
    {
      return 2;
    }
    else {
      return 0;
    }
  }
}
public class MotorSeilfly extends Seilfly implements Motordrevet{
    int trekkraft;
    String motortype;

    MotorSeilfly(String id, int glidetall, int vingespenn, int trekkraft, String motortype){
        super(id, glidetall, vingespenn);
        this.trekkraft = trekkraft;
        this.motortype = motortype;
    }

    public int trekkraft(){
        return this.trekkraft;
    }

    public String motortype(){
        return this.motortype;
    }

}

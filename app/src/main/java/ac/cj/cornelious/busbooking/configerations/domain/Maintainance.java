package ac.siphiwo.mgijima.busbooking.config.domain;

import java.io.Serializable;

/**
 * Created by Cornelious on 4/17/2016.
 */
public class Maintainance implements Serializable {
    private Long maintainanceID;
    private String maintainanceCode;
    private    String description;
    private double  cost;

     private Maintainance(BuildMaintainance objBuilder)
    {
        this.maintainanceID=objBuilder.maintainanceID;
        this.description=objBuilder.description;
        this.cost=objBuilder.cost;
        this.maintainanceCode=objBuilder.maintainanceCode;
    }
    public Long getMaintainanceID(){
        return maintainanceID;
    }
    public String getMaintainanceCode() {
        return maintainanceCode;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }
    public static class BuildMaintainance
    {
        private Long maintainanceID;
        private String maintainanceCode;
        private    String description;
        private double  cost;
        public BuildMaintainance(){}

        public BuildMaintainance id(Long id)
        {
            this.maintainanceID=id;
            return this;
        }
        public BuildMaintainance code(String code)
        {
            maintainanceCode=code;
            return this;
        }

        public BuildMaintainance Description(String description)
        {
            this.description=description;
            return this;
        }
        public BuildMaintainance cost  (double cost)
        {
            this.cost=cost;
            return this;
        }

        public Maintainance build()
        {
            return new Maintainance( this);
        }
        public BuildMaintainance copy(Maintainance objMaintainance)
        {
            this.maintainanceID=objMaintainance.maintainanceID;
            this.maintainanceCode=objMaintainance.maintainanceCode;
            this.description=objMaintainance.description;
            this.cost=objMaintainance.cost;
            return this;

        }
    }
}

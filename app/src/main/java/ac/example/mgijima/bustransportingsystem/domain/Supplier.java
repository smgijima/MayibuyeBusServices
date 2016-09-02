package ac.example.mgijima.bustransportingsystem.domain;

import java.io.Serializable;

/**
 * Created by Cornelious on 5/12/2016.
 */
public class Supplier implements Serializable {
    private Long id;
    private String name;
    private String contactNumber;

    public Supplier(SupplierBuilder suplierBuilder){
        this.id=suplierBuilder.id;
        this.name=suplierBuilder.name;
        this.contactNumber=suplierBuilder.contactNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public static class SupplierBuilder{
        private  Long id;
        private String name;
        private String contactNumber;

        public SupplierBuilder id(Long id){
            this.id=id;
            return  this;
        }
        public SupplierBuilder name(String name){
            this.name=name;
            return  this;
        }
        public SupplierBuilder contactNumber(String contactNumber){
            this.contactNumber=contactNumber;
            return this;
        }
        public SupplierBuilder copy(Supplier supplier){
            this.id=supplier.id;
            this.name=supplier.name;
            this.contactNumber=supplier.contactNumber;
            return  this;
        }
        public Supplier build(){
            return new Supplier(this);
        }
    }
}

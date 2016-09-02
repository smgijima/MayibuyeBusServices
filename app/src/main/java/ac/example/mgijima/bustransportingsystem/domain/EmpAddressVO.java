package ac.example.mgijima.bustransportingsystem.domain;

import java.io.Serializable;

/**
 * Created by Cornelious on 4/13/2016.
 */
public class EmpAddressVO implements Serializable{
    private Long id;
    private String street;
    private String city;
    private String code;
    private EmpAddressVO()
    {}

    public EmpAddressVO(AddressBuilder objAdressBuilder)
    {
        this.id=objAdressBuilder.id;
        this.street=objAdressBuilder.street;
        this.city=objAdressBuilder.city;
        this.code=objAdressBuilder.code;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public static class AddressBuilder
    {
        private Long id;
        private String street;
        private String city;
        private String code;

        public AddressBuilder id(Long id){
            this.id=id;
            return this;
        }

        public AddressBuilder street(String street)
        {
            this.street=street;
            return this;
        }
        public AddressBuilder city(String city)
        {
            this.city=city;
            return  this;
        }
        public AddressBuilder code(String code)
        {
            this.code=code;
            return this;
        }
        public AddressBuilder copy(EmpAddressVO addressValues)
        {
            this.id=addressValues.id;
            this.street=addressValues.street;
            this.code=addressValues.code;
            this.city=addressValues.city;
            return this;
        }

        public EmpAddressVO build()
        {
            return new EmpAddressVO(this);
        }
    }
}

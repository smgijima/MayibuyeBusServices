package ac.siphiwo.mgijima.busbooking.config.domain;

import java.io.Serializable;

/**
 * Created by Cornelious on 5/12/2016.
 */
public class Account implements Serializable {
    private Long id;
    private String username;
    private String password;

public Account(AccountBuilder accountBuilder){
    this.id=accountBuilder.id;
    this.username=accountBuilder.username;
    this.password=accountBuilder.password;
}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class AccountBuilder{
        private  Long id;
        private String username;
        private String password;

        public AccountBuilder id(Long id){
            this.id=id;
            return  this;
        }
        public AccountBuilder username(String username){
            this.username= username;
            return this;
        }
        public AccountBuilder password(String password){
            this.password=password;
            return  this;
        }
        public AccountBuilder copy(Account account){
            this.id=account.id;
            this.username=account.username;
            this.password=account.password;
            return  this;
        }
        public Account build(){
            return  new Account(this);
        }
    }
}

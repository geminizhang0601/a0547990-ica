package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.javabean;

public class User {
    private String EditText_Account;
    private String EditText_Password;
    public User(){

    }
    public User (String account, String password){
        this.EditText_Account=account;
        this.EditText_Password=password;
    }
    public String getAccount(){
        return EditText_Account;
    }
    public void setAccount (String EditText_Account){
        this.EditText_Account=EditText_Account;
    }
    public String getPassword(){
        return EditText_Password;
    }
    public void setPassword (String EditText_Password){
        this.EditText_Password=EditText_Password;
    }
    public String toSpring(){
        return "User{account="+EditText_Account+",password= "+EditText_Password+"}";
    }
}

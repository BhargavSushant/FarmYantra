package com.droid.atl.farmyantra;

/**
 * Created by dev on 19-Mar-17.
 */

public class UserDetails {

    String userId,email,contact_person,contact,company;

    public UserDetails(){}

    public UserDetails(String userId, String email, String company, String contact_person, String contact) {
        this.email=email;
        this.contact_person=contact_person;
        this.contact=contact;
        this.company = company;
        this.userId = userId;
    }

    public String getEmail(){return email;}
    public String getContact_person (){return contact_person ;}
    public String getContact (){return contact;}
    public String getCompany (){return company;}
    public String getUserId (){return userId;}

    public void setEmail(String email){this.email = email;}
    public void setContact_person(String contact_person) {this.contact_person=contact_person;}
    public void setContact(String contact){this.contact=contact;}
    public void setCompany(String company){this.company=company;}
    public void setUserId(String userId){this.userId=userId;}
}

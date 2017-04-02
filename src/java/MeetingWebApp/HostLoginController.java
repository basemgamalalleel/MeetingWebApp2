/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeetingWebApp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author user
 */
@Named(value = "hostLoginController")
@SessionScoped
public class HostLoginController implements Serializable {
    
    String email;
    String response;
    
    // this is our class that uses Hibernare tp quert the host table
    HostLoginHelper helper;

    /**
     * Creates a new instance of HostLoginController
     */
    public HostLoginController() {
        
        helper = new HostLoginHelper();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponse() {
        
        if (email != null) {
            
            //calling our helper that inserts a row into the actor table
            if (helper.getHost(email) == 1 ){
                // insert was successful
                email = null;
                response = "Host Added.";
                return response;
            } else {
                // inser failed
                email = null;
                response = "Host Not Added.";
            }
        } else {
            // don't dis[lay a message when the user hasn't input 
            // a first and last name
            response = " ";
        }
        return response;
        
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    
}

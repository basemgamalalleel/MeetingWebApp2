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
@Named(value = "statusController")
@SessionScoped
public class StatusController implements Serializable {
    
    StatusHelper helper;
    Status status;
    String statusType;

    /**
     * Creates a new instance of StatusController
     */
    public StatusController() {
        helper = new StatusHelper();
    }

    public StatusHelper getHelper() {
        return helper;
    }

    public void setHelper(StatusHelper helper) {
        this.helper = helper;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
    
    public String statusType (int statusId){
        status = helper.getStatusType(statusId);
        statusType = status.getStatusType();
        return statusType;
    }
}

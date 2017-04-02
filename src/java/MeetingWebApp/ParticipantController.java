/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeetingWebApp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author mimi
 */
@Named(value = "participantController")
@SessionScoped
public class ParticipantController implements Serializable {

    // these fields map directly to components in the participant.xhtml
    int meetingId;
    String email;
    String response;
    
    DataModel participants;
    
    
    // this is our class that uses Hibernare to query the host table
    ParticipantHelper helper;
    
    // this is our Host POJO
    Participant participant;
    
    /**
     * Creates a new instance of ParticipantController
     */
    public ParticipantController() {
        helper = new ParticipantHelper();
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getResponse() {
            if (email != null) {
            
            
            // initializing an actor
            participant = new Participant(email);
            
            //calling our helper that inserts a row into the actor table
            if (helper.insertParticipant(participant) == 1 ){
                // insert was successful
                email = null;
                response = "Participant Added.";
                return response;
            } else {
                // inser failed
                email = null;
                response = "Participant Not Added.";
            }
        } else {
            // don't display a message when the user hasn't input 
            // a name and email
            response = " ";
        }
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ParticipantHelper getHelper() {
        return helper;
    }

    public void setHelper(ParticipantHelper helper) {
        this.helper = helper;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setparticipant(Participant participant) {
        if (participants == null){
            participants = new ListDataModel(helper.getParticipants());
        }
        this.participant = participant;
    }

    public DataModel getParticipants() {
        return participants;
    }

    public void setParticipants(DataModel participants) {
        this.participants = participants;
    }
    
   public String login(){

         if (email != null) {
            
            // initializing an actor
            participant = new Participant(email);
            
            //calling our helper that inserts a row into the actor table
            if (helper.searchParticipant(participant) == 1 ){
                // insert was successful
                email = null;
                return "participantMeeting";
                
            } else {
                // inser failed
                email = null;
                 return "participant_login";
            }
        } else {
            // don't dis[lay a message when the user hasn't input 
            // a first and last name
            return "participant_login";
        }
        
    }
   
   public String participantintuer() {
            if (email != null) {
            
            
            // initializing an actor
            participant = new Participant(email);
            
            //calling our helper that inserts a row into the actor table
            if (helper.searchParticipant(participant) == 1 ){
                if(helper.insertParticipant(participant) == 1){
                    email = null;
                    response = "Participant Added.";
                    return response;
                }else{
                    email = null;
                    response = "Participant Not Added.";
                }
                
            } else {
                // inser failed
                email = null;
                response = "Participant Already Exists.";
            }
        } else {
            // don't display a message when the user hasn't input 
            // a name and email
            response = " ";
        }
        return response;
    }
    
}
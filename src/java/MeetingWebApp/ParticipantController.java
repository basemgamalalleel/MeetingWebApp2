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
    Meeting selected;

    DataModel meetings;

    // this is our class that uses Hibernare to query the host table
    ParticipantHelper helper;

    // this is our Host POJO
    Participant participant;

    
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

            // initializing an Participant
            participant = new Participant(email);

            //calling our helper that inserts a row into the Participant table
            if (helper.searchParticipant(participant) == 1) {
                response = "Participant Already Exists.";
                return response;
            } else if (helper.insertParticipant(participant) == 1) {
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
            // an email
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
        this.participant = participant;
    }

    public DataModel getMeetings() {
        if (meetings == null) {
            email = participant.getParticipantEmail();
            meetings = new ListDataModel(helper.getMeetingParticipant(email));
        }
        return meetings;
    }

    public void setMeetings(DataModel meetings) {
        this.meetings = meetings;
    }

    public Meeting getSelected() {
        return selected;
    }

    public void setSelected(Meeting selected) {
        this.selected = selected;
    }
    
    

    public String prepareView(){
        // get all of the data associated with the selected meeting
        selected = (Meeting) getMeetings().getRowData();
        // return the name of the page that will load when the hyperlink
        // is selected
        return "viewMeetingParticipant";
    }

    public String login() {

        if (email != null) {

            // initializing an Participant
            participant = new Participant(email);

            //calling our helper that inserts a row into the Participant table
            if (helper.searchParticipant(participant) == 1) {
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
            // an email
            return " ";
        }

    }

    /*public String participantintuer() {
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
    }*/
}

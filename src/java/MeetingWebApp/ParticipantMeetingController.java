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
 * @author user
 */
@Named(value = "participantMeetingController")
@SessionScoped
public class ParticipantMeetingController implements Serializable {

    int startId;
    String response;
    
    
    DataModel meetingName;
    
    // helper
    ParticipantMeetingHelper helper;
    
    String email;
    // POJO
    ParticipantMeeting participantMeeting;
    Meeting meeting;
    Status status;
    Participant participant;
    
    private int recordCount;
    private int pageSize = 5;
    private Meeting selected;

    /**
     * Creates a new instance of ParticipantMeetingController
     */
    public ParticipantMeetingController() {
        meeting = new Meeting();
        status = new Status();
        participant = new Participant();
        helper = new ParticipantMeetingHelper();
        startId = 0;
        
    }

    public ParticipantMeeting getParticipantMeeting() {
        return participantMeeting;
    }

    public void setParticipantMeeting(ParticipantMeeting participantMeeting) {
        this.participantMeeting = participantMeeting;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String next(){
        startId = startId + (pageSize + 1);
        recreateModel();
        return "updateMeeting";
    }
    
    public String previous(){
        startId = startId - pageSize;
        recreateModel();
        return "updateMeeting";
    }
    
    private void recreateModel(){
        meetingName = null;
        recordCount = helper.getNumberMeeting();
    }
    
    public boolean isHasNextPage(){
        if (startId + pageSize < recordCount){
            return true;
        }
        return false;
    }
    
    public boolean isHasPreviousPage(){
        if (startId - pageSize > 0){
            return true;
        }
        return false;
    }
    
    public String getResponse() {
        if (meeting != null && participant != null && status != null) {
            
            // initializing an actor
            participantMeeting = new ParticipantMeeting(meeting, participant, status);
            
            //calling our helper that inserts a row into the actor table
            if (helper.inviteParticipant(participantMeeting) == 1 ){
                // insert was successful
                meeting = null;
                participant = null;
                status = null;
                response = "participant Added to meeting.";
                return response;
            } else {
                // inser failed
                meeting = null;
                participant = null;
                status = null;
                response = "Participant Not Added to Meeting.";
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

    public DataModel getMeetingName() {
        if (meetingName == null){
            meetingName = new ListDataModel(helper.getMeetingName(startId, email));
        }
        return meetingName;
    }

    public void setMeetingName(DataModel meetingName) {
        this.meetingName = meetingName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String prepareView(){
        // get all of the data associated with the selected movie
        selected = (Meeting) getMeetingName().getRowData();
        // return the name of the page that will load when the hyperlink
        // is selected
        return "browse";
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Meeting getSelected() {
        return selected;
    }

    public void setSelected(Meeting selected) {
        this.selected = selected;
    }
    
}

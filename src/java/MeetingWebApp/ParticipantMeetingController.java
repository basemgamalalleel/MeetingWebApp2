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

    String response;
    String participantEmail;
    Meeting meeting;
    int statusId;
    int meetingId;
    String hostEmail;
    Status status;

    DataModel participantsEmail;

    ParticipantHelper participantHelper;
    Participant participant;
    ParticipantMeetingHelper helper;
    ParticipantMeeting participantMeeting;
    MeetingController meetingController;
    MeetingHelper meetingHelper;

    public ParticipantMeetingController() {
        helper = new ParticipantMeetingHelper();
        participantHelper = new ParticipantHelper();
        participantMeeting = new ParticipantMeeting();

    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public ParticipantHelper getParticipantHelper() {
        return participantHelper;
    }

    public void setParticipantHelper(ParticipantHelper participantHelper) {
        this.participantHelper = participantHelper;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ParticipantMeeting getParticipantMeeting() {
        return participantMeeting;
    }

    public void setParticipantMeeting(ParticipantMeeting participantMeeting) {
        this.participantMeeting = participantMeeting;
    }

    public MeetingController getMeetingController() {
        return meetingController;
    }

    public void setMeetingController(MeetingController meetingController) {
        this.meetingController = meetingController;
    }

    public MeetingHelper getMeetingHelper() {
        return meetingHelper;
    }

    public void setMeetingHelper(MeetingHelper meetingHelper) {
        this.meetingHelper = meetingHelper;
    }

    public DataModel getParticipantsEmail() {

        meetingId = meeting.getMeetingId();
        participantsEmail = new ListDataModel(helper.getParticipant(meetingId));

        return participantsEmail;
    }

    public void setParticipantsEmail(DataModel participantsEmail) {
        this.participantsEmail = participantsEmail;
    }

    public String getResponse() {
        String states1;
        String states2;
        int statusId = 0;
        if (helper.getParticipantMeetingNum(meeting.getMeetingId()) < 5) {
            if (participantEmail != null) {

                participant = new Participant(participantEmail);
                Status status = new Status(statusId, null);
                participantMeeting = new ParticipantMeeting(meeting, participant, status);

                if (participantHelper.searchParticipant(participant) == 1) {
                    states1 = "participant already exists";
                    if (helper.insertParticipantMeeting(participantMeeting) == 1) {
                        states2 = "Participant Added to Meeting";
                        response = states1 + " and " + states2;
                        return response;
                    } else {
                        states2 = "Participant Not Added to Meeting";
                        response = states1 + " and " + states2;
                        return response;
                    }
                    //return response;
                } else if (participantHelper.insertParticipant(participant) == 1) {
                    states1 = "participant Added to participant table";
                    if (helper.insertParticipantMeeting(participantMeeting) == 1) {
                        states2 = "Participant Added to Meeting";
                        response = states1 + " and " + states2;
                        return response;
                    } else {
                        states2 = "Participant Not Added to Meeting";
                        response = states1 + " and " + states2;
                        return response;
                    }
                } else {
                    states1 = "participant Not Added to participant table";
                    states2 = "Participant Not Added to Meeting";
                    response = states1 + " and " + states2;
                    return response;
                }
            } else {
                // if nothing was input into the form
                //don't display a message 
                response = " ";
                return response;
            }
        } else {
            response = "This meeting is full it has five Participant.";
            return response;
        }
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String prepareAddParticipant(int meetingId) {
        // get all of the data associated with the selected meeting
        meeting = (Meeting) helper.getMeeting(meetingId);

        // is selected
        return "addparticipant";
    }

    public String prepareViewParticipant(int meetingId) {
        // get all of the data associated with the selected meeting

        meeting = (Meeting) helper.getMeeting(meetingId);
        //this.meetingId = meeting.getMeetingId();
        //status = (Status) helper.getStatus(meetingId);

        // is selected
        return "viewParticipant";
    }

    
}

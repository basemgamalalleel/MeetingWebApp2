package MeetingWebApp;
// Generated 12/03/2017 11:53:04 م by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Participant generated by hbm2java
 */
public class Participant  implements java.io.Serializable {


     private String participantEmail;
     private Set<ParticipantMeeting> participantMeetings = new HashSet<ParticipantMeeting>(0);

    public Participant() {
    }

	
    public Participant(String participantEmail) {
        this.participantEmail = participantEmail;
    }
    public Participant(String participantEmail, Set<ParticipantMeeting> participantMeetings) {
       this.participantEmail = participantEmail;
       this.participantMeetings = participantMeetings;
    }
   
    public String getParticipantEmail() {
        return this.participantEmail;
    }
    
    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
    }
    
    public Set<ParticipantMeeting> getParticipantMeetings() {
        return this.participantMeetings;
    }
    
    public void setParticipantMeetings(Set<ParticipantMeeting> participantMeetings) {
        this.participantMeetings = participantMeetings;
    }

}



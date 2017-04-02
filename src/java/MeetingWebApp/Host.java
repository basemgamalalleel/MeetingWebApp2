package MeetingWebApp;
// Generated 12/03/2017 11:53:04 م by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Host generated by hbm2java
 */
public class Host  implements java.io.Serializable {


     private String hostEmail;
     private String hostName;
     private Set<Meeting> meetings = new HashSet<Meeting>(0);

    public Host() {
    }

	
    public Host(String hostEmail, String hostName) {
        this.hostEmail = hostEmail;
        this.hostName = hostName;
    }
    public Host(String hostEmail, String hostName, Set<Meeting> meetings) {
       this.hostEmail = hostEmail;
       this.hostName = hostName;
       this.meetings = meetings;
    }
   
    public String getHostEmail() {
        return this.hostEmail;
    }
    
    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }
    public String getHostName() {
        return this.hostName;
    }
    
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    public Set<Meeting> getMeetings() {
        return this.meetings;
    }
    
    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }




}


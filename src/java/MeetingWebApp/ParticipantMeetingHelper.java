/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeetingWebApp;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author user
 */
public class ParticipantMeetingHelper {
    Session session = null;

    public ParticipantMeetingHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public int insertParticipantMeeting(ParticipantMeeting a) {
        int result = 0;
        String sql = "insert into participant_meeting(MEETING_ID, STATUS_ID, PARTICIPANT_EMAIL) "
                + "values (:meetingId, :statusId, :participantEmail)";
        try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            SQLQuery q = session.createSQLQuery(sql);
            // associate the Meeting POJO and table with the query 
            q.addEntity(ParticipantMeeting.class);
            // bind values to the query placeholders
            q.setParameter("meetingId", a.getMeeting().getMeetingId());
            q.setParameter("statusId", 3);
            q.setParameter("participantEmail", a.getParticipant().getParticipantEmail());
            
            // execute the query
            result = q.executeUpdate();
            // commit the changes to the database
            // this is what allows the changes to be
            // truely viewed in the database
            // but it also makes the transaction inactive
            // which means it will have to be started again
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public Meeting getMeeting (int meetingId) {
        
        Meeting meeting = null;
        
        // create the query, but as a String
        // :id is a placeholder for actual value
        // passed in as parameter
        String sql = "select * from meeting where MEETING_ID = :id";
        
        try {
            
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Film POJO and table with the query 
            q.addEntity(Meeting.class);
            
            // bind value to the query placeholder
            q.setParameter("id", meetingId);
            
            // execute the query and cast the return value
            // to a Film
            meeting = (Meeting) q.uniqueResult();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return meeting;
    }
    
    public int getParticipantMeetingNum(int meetingId){
        
        List<ParticipantMeeting> meetingList = null;
        
        // create the query, but as a String
        String sql = "select * from participant_meeting where MEETING_ID = :id";
        
        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Category POJO and table with the query 
            q.addEntity(ParticipantMeeting.class);
            q.setParameter("id", meetingId);
            // execute the query and cast the returned List
            // as a List of Films
            meetingList = (List<ParticipantMeeting>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return meetingList.size();
    }
    
    public List getParticipant(int meetingId) {
        
        List<ParticipantMeeting> participant = null;
        
        // create the query, but as a String
        // :id is a placeholder for actual value
        // passed in as parameter
        String sql = "select * from participant_meeting where MEETING_ID = :id";
        
        try {
            
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Film POJO and table with the query 
            q.addEntity(ParticipantMeeting.class);
            
            // bind value to the query placeholder
            q.setParameter("id", meetingId);
            
            // execute the query and cast the return value
            // to a Film
            participant = (List<ParticipantMeeting>) q.list();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return participant;
    }
    
    public Status getStatus (int meetingId) {
        
        Status status = null;
        
        // create the query, but as a String
        // :id is a placeholder for actual value
        // passed in as parameter
        String sql = "select * from status, participant_meeting "
                + "where status.STATUS_ID = participant_meeting.STATUS_ID "
                + "and participant_meeting.MEETING_ID = :id";
        
        try {
            
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Film POJO and table with the query 
            q.addEntity(Status.class);
            
            // bind value to the query placeholder
            q.setParameter("id", meetingId);
            
            // execute the query and cast the return value
            // to a Film
            status = (Status) q.uniqueResult();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
    
    public String getStatusType (int statusId) {
        
        String statusType = null;
        
        // create the query, but as a String
        // :id is a placeholder for actual value
        // passed in as parameter
        /*String sql = "select * from status, participant_meeting "
                + "where status.STATUS_ID = participant_meeting.STATUS_ID "
                + "and participant_meeting.MEETING_ID = :id";*/
        String sql = "select * from status "
                + "where status.STATUS_ID = :id";
        
        try {
            
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Film POJO and table with the query 
            q.addEntity(Meeting.class);
            
            // bind value to the query placeholder
            q.setParameter("id", statusId);
            
            // execute the query and cast the return value
            // to a Film
            statusType = (String) q.uniqueResult();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return statusType;
    }
    
    
    
    
}
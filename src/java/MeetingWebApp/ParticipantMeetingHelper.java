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
    
    public int inviteParticipant(ParticipantMeeting a){
                        int result = 0;
        
        String sql = "insert into participant(MEETING_ID, STATUS_ID, PARTICIPANT_EMAIL)" 
                + "values (:meeting, :status, :participant)";
        
        try {
            // starting a transaction if on wisn't active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            
            // creating an actul query that can be executed
            SQLQuery q = session.createSQLQuery(sql);
            //associating our Avtor POJO and table with the query 
            q.addEntity(Host.class);
            
            // binding values to the placeholders in the query
            q.setParameter("meeting", a.getMeeting());
            q.setParameter("participant", a.getParticipant());
            q.setParameter("status", a.getStatus());
            
            // executing the query 
            result = q.executeUpdate();
            
            // commiting the query to the database
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
       
    public List getMeetingName(int startID, String participantEmail){
        
        List<ParticipantMeeting> meetingList = null;
        
        // create the query, but as a String `meetingdb`.`participant_meeting`
        // :start and :end, are placeholders for actual values
        // passed in as parameters and hard-coded
        String sql = "select * from participant_meeting "
                + "where PARTICIPANT_EMAIL = :participantEmail";
        
        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Category POJO and table with the query 
            q.addEntity(ParticipantMeeting.class);
            // bind values to the query placeholders
            //q.setParameter("start", startID);
            //q.setParameter("end", 5);
            q.setParameter("participantEmail", participantEmail);
            
            // execute the query and cast the returned List
            // as a List of Films
            meetingList = (List<ParticipantMeeting>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
                
        return meetingList;
    }
    
    public int getNumberMeeting(){
        
        List<ParticipantMeeting> meetingList = null;
        
        // create the query, but as a String
        String sql = "select * from Participant_Meeting";
        
        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Category POJO and table with the query 
            q.addEntity(ParticipantMeeting.class);
            
            // execute the query and cast the returned List
            // as a List of Films
            meetingList = (List<ParticipantMeeting>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return meetingList.size();
    }
    
}

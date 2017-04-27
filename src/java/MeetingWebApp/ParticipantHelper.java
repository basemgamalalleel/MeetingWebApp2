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
 * @author mimi
 */
public class ParticipantHelper {

    Session session = null;

    public ParticipantHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insertParticipant(Participant a) {
        int result = 0;

        String sql = "insert into participant(PARTICIPANT_EMAIL) "
                + "values (:email)";

        try {
            // starting a transaction if on wisn't active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // creating an actul query that can be executed
            SQLQuery q = session.createSQLQuery(sql);
            //associating our Avtor POJO and table with the query 
            q.addEntity(Participant.class);

            // binding values to the placeholders in the query
            q.setParameter("email", a.getParticipantEmail());

            // executing the query 
            result = q.executeUpdate();

            // commiting the query to the database
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int searchParticipant(Participant a) {

        List<Participant> participant = null;

        // create the query, but as a String
        String sql = "select * from Participant where PARTICIPANT_EMAIL like :email";

        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);

            // associate the Category POJO and table with the query 
            q.addEntity(Participant.class);

            q.setParameter("email", a.getParticipantEmail());

            // execute the query and cast the returned List
            // as a List of Films
            participant = (List<Participant>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return participant.size();
    }
    
    public List getMeetingParticipant(String participantEmail){
        
        List<Participant> meetingList = null;
        
        // create the query, but as a String
        // :start and :end, are placeholders for actual values
        // passed in as parameters and hard-coded
        /*String sql = "select * from participant_meeting "
                + "where PARTICIPANT_EMAIL = :participantEmail "
                + "limit :start, :end";*/
        
        String sql = "select * from meeting, participant_meeting, participant "
                + "where meeting.MEETING_ID = participant_meeting.MEETING_ID "
                + "and participant_meeting.PARTICIPANT_EMAIL = participant.PARTICIPANT_EMAIL "
                + "and participant.PARTICIPANT_EMAIL = :participantEmail";
        
        
        
        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Category POJO and table with the query 
            q.addEntity(Meeting.class);
            // bind values to the query placeholders
            
            q.setParameter("participantEmail", participantEmail);
            
            // execute the query and cast the returned List
            // as a List of Films
            meetingList = (List<Participant>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
                
        return meetingList;
    }
    
    public int participantMeetingStatusUpdate(int statusId, int meetingId, String participantEmail) {
        int result = 0;
        String sql = "UPDATE participant_meeting "
                + "SET STATUS_ID = :statusId "
                + "WHERE PARTICIPANT_EMAIL = :participantEmail "
                + "and MEETING_ID = :meetingId";

        try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            SQLQuery q = session.createSQLQuery(sql);
            // associate the Meeting POJO and table with the query 
            q.addEntity(ParticipantMeeting.class);
            // bind values to the query placeholders
            q.setParameter("meetingId", meetingId);
            q.setParameter("statusId", statusId);
            q.setParameter("participantEmail", participantEmail);
            
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
}

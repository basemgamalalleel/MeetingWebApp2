
package MeetingWebApp;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author user
 */
public class MeetingHelper {
    
    Session session = null;
    
    public MeetingHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int insertMeeting(Meeting a) {
        int result = 0;
        String sql = "insert into meeting(MEETING_NAME, MEETING_DESCRIPTION, MEETING_DATE, MEETING_TIME, MEETING_LOCATION, HOST_EMAIL) "
                + "values (:name, :description, :date, :time, :locayion, :host)";
        try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            SQLQuery q = session.createSQLQuery(sql);
            // associate the Meeting POJO and table with the query 
            q.addEntity(Meeting.class);
            // bind values to the query placeholders
            q.setParameter("name", a.getMeetingName());
            q.setParameter("description", a.getMeetingDescription());
            q.setParameter("date", a.getMeetingDate());
            q.setParameter("time", a.getMeetingTime());
            q.setParameter("locayion", a.getMeetingLocation());
            q.setParameter("host", a.getHost().getHostEmail());
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
    
    public List getMeetingName(int startID, String hostEmail){
        
        List<Meeting> meetingList = null;
        
        // create the query, but as a String
        // :start and :end, are placeholders for actual values
        // passed in as parameters and hard-coded
        String sql = "select * from meeting "
                + "where HOST_EMAIL = :hostEmail "
                + "limit :start, :end";
        
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
            q.setParameter("start", startID);
            q.setParameter("end", 5);
            q.setParameter("hostEmail", hostEmail);
            
            // execute the query and cast the returned List
            // as a List of Films
            meetingList = (List<Meeting>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
                
        return meetingList;
    }
    
    public int getNumberMeeting(String hostEmail){
        
        List<Meeting> meetingList = null;
        
        // create the query, but as a String
        String sql = "select * from meeting where HOST_EMAIL = :hostEmail";
        
        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            // associate the Category POJO and table with the query 
            q.addEntity(Meeting.class);
            q.setParameter("hostEmail", hostEmail);
            // execute the query and cast the returned List
            // as a List of Films
            meetingList = (List<Meeting>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return meetingList.size();
    }
    
    public int getMeetingId(){
        
        List<Meeting> meetingList = null;
        
        // create the query, but as a String
        String sql = "select * from meeting order by MEETING_ID desc limit 1";
        
        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            // associate the Film POJO and table with the query
            q.addEntity(Meeting.class);
            // execute the query and cast the returned List
            // as a List of Films
            meetingList = (List<Meeting>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        // return just the id of the first Film in the List of Films
        return meetingList.get(0).getMeetingId();
    }
    
    
    
}

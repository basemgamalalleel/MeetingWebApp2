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
public class StatusHelper {
    Session session = null;

    public StatusHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public Status getStatusType (int statusId) {
        
        Status status = null;
        
        // create the query, but as a String
        // :id is a placeholder for actual value
        // passed in as parameter
        String sql = "select * from status where STATUS_ID = :id";
        
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
            q.setParameter("id", statusId);
            
            // execute the query and cast the return value
            // to a Film
            status = (Status) q.uniqueResult();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return status;
    }
}

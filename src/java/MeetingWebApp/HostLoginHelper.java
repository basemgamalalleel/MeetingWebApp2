/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeetingWebApp;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SQLQuery;

/**
 *
 * @author user
 */
public class HostLoginHelper {
    
    Session session = null;
    
    public HostLoginHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int searchHost(String a){
        int result = 0;
        
        String sql = "select * from Host where HOST_EMAIL like :email ";
        
        try {
            // starting a transaction if on wisn't active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            
            // creating an actul query that can be executed
            SQLQuery q = session.createSQLQuery(sql);
            //associating our Avtor POJO and table with the query 

            
            // binding values to the placeholders in the query
            q.setParameter("email", a);

            // executing the query 
            result = q.executeUpdate();
            
            // commiting the query to the database
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public int getHost(String a){
        
        List<Host> host = null;
        
        // create the query, but as a String
        String sql = "select HOST_EMAIL from host where HOST_EMAIL = :email ";
        
        try {
            // if the transaction isn't active, begin it
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            } 
            
            // create the actual query that will get executed
            SQLQuery q = session.createSQLQuery(sql);
            
            
            // associate the Category POJO and table with the query 
            q.addEntity(Host.class);
            
            q.setParameter("email", a);
            
            // execute the query and cast the returned List
            // as a List of Films
            host = (List<Host>) q.list();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return host.size();
    }
    
}

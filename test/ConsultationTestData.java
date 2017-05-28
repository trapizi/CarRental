package test;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Consultation;
import model.Member;
import util.InvalidInputException;



public class ConsultationTestData {
   
    public static void main(String[] args){
         
    
        Consultation consultation1 = new Consultation();
        try {
            consultation1.validateInput("2009-09-09", "1");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        Consultation consultation2 = new Consultation();
        try {
            consultation2.validateInput("2009-02-23", "2");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Consultation consultation3 = new Consultation();
        try {
            consultation3.validateInput("2014-09-23", "3");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation4 = new Consultation();
        try {
            consultation3.validateInput("2012-02-14", "4");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation5 = new Consultation();
        try {
            consultation3.validateInput("2016-02-23", "5");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation6 = new Consultation();
        try {
            consultation3.validateInput("2014-10-19", "6");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation7 = new Consultation();
        try {
            consultation3.validateInput("2013-11-03", "7");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation8 = new Consultation();
        try {
            consultation3.validateInput("2016-12-02", "8");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation9 = new Consultation();
        try {
            consultation3.validateInput("2017-10-24", "9");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation10 = new Consultation();
        try {
            consultation3.validateInput("2016-03-09", "10");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
            
        System.out.println(consultation1);
        //System.out.println(consultation2);
        //System.out.println(consultation3);
        //System.out.println(consultation4);
        //System.out.println(consultation5);
        //System.out.println(consultation6);
        //System.out.println(consultation7);
        //System.out.println(consultation8);
        //System.out.println(consultation9);
        //System.out.println(consultation10);
    }

    
}
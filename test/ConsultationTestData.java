package test;


import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Consultation;
import model.Member;
import util.InvalidInputException;



public class ConsultationTestData {
   
    public static void main(String[] args){
         
    
        Consultation consultation1 = new Consultation();
        consultation1.setConsultationNum(1);
        consultation1.setConsultationPrice(20);
        consultation1.setConsultationTime(new Time(12,12,12));
        consultation1.setConsultationDate("2009-12-12");
        consultation1.setCorporateID(1);
        try {
            consultation1.validateInput("2009-12-12", "1");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation2 = new Consultation();
        consultation2.setConsultationNum(2);
        consultation2.setConsultationPrice(30);
        consultation2.setConsultationTime(new Time(15,12,29));
        consultation2.setConsultationDate("2011-07-23");
        consultation2.setCorporateID(2);
        try {
            consultation2.validateInput("2011-07-23", "2");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Consultation consultation3 = new Consultation();
        consultation3.setConsultationNum(3);
        consultation3.setConsultationPrice(15);
        consultation3.setConsultationTime(new Time(18,02,59));
        consultation3.setConsultationDate("2015-11-03");
        consultation3.setCorporateID(3);
        try {
            consultation3.validateInput("2015-11-03", "3");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation4 = new Consultation();
        consultation4.setConsultationNum(4);
        consultation4.setConsultationPrice(30);
        consultation4.setConsultationTime(new Time(20,37,23));
        consultation4.setConsultationDate("2012-02-14");
        consultation4.setCorporateID(4);
        try {
            consultation4.validateInput("2012-02-14", "4");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation5 = new Consultation();
        consultation5.setConsultationNum(5);
        consultation5.setConsultationPrice(28);
        consultation5.setConsultationTime(new Time(15,12,48));
        consultation5.setConsultationDate("2016-02-23");
        consultation5.setCorporateID(5);
        try {
            consultation5.validateInput("2016-02-23", "5");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation6 = new Consultation();
        consultation6.setConsultationNum(6);
        consultation6.setConsultationPrice(13);
        consultation6.setConsultationTime(new Time(20,37,23));
        consultation6.setConsultationDate("2014-10-19");
        consultation6.setCorporateID(6);
        try {
            consultation6.validateInput("2014-10-19", "6");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation7 = new Consultation();
        consultation7.setConsultationNum(7);
        consultation7.setConsultationPrice(13);
        consultation7.setConsultationTime(new Time(11,57,59));
        consultation7.setConsultationDate("2013-11-03");
        consultation7.setCorporateID(7);
        try {
            consultation7.validateInput("2013-11-03", "7");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation8 = new Consultation();
        consultation8.setConsultationNum(8);
        consultation8.setConsultationPrice(29);
        consultation8.setConsultationTime(new Time(12,34,28));
        consultation8.setConsultationDate("2016-12-02");
        consultation8.setCorporateID(8);
        try {
            consultation8.validateInput("2016-12-02", "8");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation9 = new Consultation();
        consultation9.setConsultationNum(9);
        consultation9.setConsultationPrice(45);
        consultation9.setConsultationTime(new Time(24,89,12));
        consultation9.setConsultationDate("2017-10-24");
        consultation9.setCorporateID(9);
        try {
            consultation9.validateInput("2017-10-24", "9"); 
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Consultation consultation10 = new Consultation();
        consultation10.setConsultationNum(10);
        consultation10.setConsultationPrice(27);
        consultation10.setConsultationTime(new Time(20,37,23));
        consultation10.setConsultationDate("2016-03-09");
        consultation10.setCorporateID(10);
        try {
            consultation10.validateInput("2016-03-09", "10");
        } catch (Exception ex) {
            Logger.getLogger(ConsultationTestData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            /*
        System.out.println(consultation1.toString());
        System.out.println(consultation2);
        System.out.println(consultation3);
        System.out.println(consultation4);
        System.out.println(consultation5);
        System.out.println(consultation6);
        System.out.println(consultation7);
        System.out.println(consultation8);
        System.out.println(consultation9);
        System.out.println(consultation10);*/
    }

    
}
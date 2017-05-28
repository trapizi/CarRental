
package test;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CorporateMember;
import util.InvalidInputException;

public class CorporateMemberTestData {
    
    public static void main(String[] args){
    
    CorporateMember cm1 = new CorporateMember();
    try {
        cm1.validateCompanyName("Altium");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    CorporateMember cm2 = new CorporateMember();
    try {
        cm2.validateCompanyName("Freehand Group");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    CorporateMember cm3 = new CorporateMember();
    try {
        cm3.validateCompanyName("Nearmap");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    CorporateMember cm4 = new CorporateMember();
    try {
        cm4.validateCompanyName("Boxa");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    CorporateMember cm5 = new CorporateMember();
    try {
        cm5.validateCompanyName("Oneflare");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    CorporateMember cm6 = new CorporateMember();
    try {
        cm6.validateCompanyName("Babcock & Brown");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    CorporateMember cm7 = new CorporateMember();
    try {
        cm7.validateCompanyName("JKL");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    CorporateMember cm8 = new CorporateMember();
    try {
        cm8.validateCompanyName("Brambles Limited");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    CorporateMember cm9 = new CorporateMember();
    try { 
        cm9.validateCompanyName("Clayton Utz");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    CorporateMember cm10 = new CorporateMember();
    try {
        cm10.validateCompanyName("Nearmap");
    } catch (InvalidInputException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(CorporateMemberTestData.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    System.out.print(cm1);
    System.out.print(cm2);
    System.out.print(cm3);
    System.out.print(cm4);
    System.out.print(cm5);
    System.out.print(cm6);
    System.out.print(cm7);
    System.out.print(cm8);
    System.out.print(cm9);
    System.out.print(cm10);
        
    }
}

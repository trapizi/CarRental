package test;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Member;
import util.InvalidInputException;



public class MemberTestData {
   
    public static void main(String[] args) throws Exception {
         
    
        Member member1 = new Member();
            member1.validateInput("JoyceL", "password1", "Joyce", "Lu", "joyce_lu@gmail.com", "98003334", "2009-09-09", "3 Summer Street", "584647354567", 1);

              
        Member member2 = new Member();
            member2.validateInput("SamSmith", "password2", "Sam", "Smith", "sam.smith@gmail.com", "76545679", "2017-08-09", "4 Ray Road", "988753675465", 2);
        
        
        Member member3 = new Member();
            member3.validateInput("peter1997", "password3", "Peter", "Pan", "peterpan1997@gmail.com", "886657788", "2017-02-03", "7 Rawson Street", "997553786941", 3);
        
            
        Member member4 = new Member();
            member4.validateInput("CharlieChan", "password4", "Charlie", "Chan", "charliechan@hotmail.com", "56778776", "2017-02-23", "3 Wallow Street", "566764398897", 4);
        
            
        Member member5 = new Member();
            member5.validateInput("sarina_lee", "password5", "Sarina", "Lee", "sarina_lee@hotmail.com", "67678876", "2017-04-23", "32 Bathurst Road", "99635675679", 5);
        
            
        Member member6 = new Member();
            member6.validateInput("YenniT", "password6", "Yenni", "Tim", "yenni.tim@unsw.edu.au", "55665435", "2017-03-12", "23 Rockleigh Way", "345787649890", 6);
            
       
        Member member7 = new Member();
            member7.validateInput("benchoi123", "password7", "Ben", "Choi", "benchoi@gmail.com", "34456654", "2017-01-18", "10 Barrack Street", "974235664213", 7);
            
        
        Member member8 = new Member();
            member8.validateInput("johnsmith", "password8", "John", "Smith", "johnsmith123@hotmail.com", "23338899", "2017-04-13", "2 Wilsons Drive", "982357345412", 8);
            
        
        Member member9 = new Member();
            member9.validateInput("janey123", "password9", "Jane", "Yousef", "janey123@gmail.com", "67788776", "2017-05-03", "17 Willougby Crescent", "385740029384", 9);
            
            
        Member member10 = new Member();
            member10.validateInput("christinakim", "password10", "Christina", "Kim", "christina_kim@gmail.com", "57865433", "2017-04-18", "Unit2/28 Darling Street", "129474638292", 10);
      
            
        System.out.println(member1);
        System.out.println(member2);
        System.out.println(member3);
        System.out.println(member4);
        System.out.println(member5);
        System.out.println(member6);
        System.out.println(member7);
        System.out.println(member8);
        System.out.println(member9);
        System.out.println(member10);
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.Calendar;

/**
 *
 * @author elizabeth
 */
public class Consultation {
    private int consultationNum;
    private float consultationPrice;
    private Time consultationTime;
    private Calendar consultationDate;
    private String corporateID;

    /**
     * @return the consultationNum
     */
    public int getConsultationNum() {
        return consultationNum;
    }

    /**
     * @param consultationNum the consultationNum to set
     */
    public void setConsultationNum(int consultationNum) {
        this.consultationNum = consultationNum;
    }

    /**
     * @return the consultationPrice
     */
    public float getConsultationPrice() {
        return consultationPrice;
    }

    /**
     * @param consultationPrice the consultationPrice to set
     */
    public void setConsultationPrice(float consultationPrice) {
        this.consultationPrice = consultationPrice;
    }

    /**
     * @return the consultationTime
     */
    public Time getConsultationTime() {
        return consultationTime;
    }

    /**
     * @param consultationTime the consultationTime to set
     */
    public void setConsultationTime(Time consultationTime) {
        this.consultationTime = consultationTime;
    }

    /**
     * @return the consultationDate
     */
    public Calendar getConsultationDate() {
        return consultationDate;
    }

    /**
     * @param consultationDate the consultationDate to set
     */
    public void setConsultationDate(Calendar consultationDate) {
        this.consultationDate = consultationDate;
    }

    /**
     * @return the corporateID
     */
    public String getCorporateID() {
        return corporateID;
    }

    /**
     * @param corporateID the corporateID to set
     */
    public void setCorporateID(String corporateID) {
        this.corporateID = corporateID;
    }

    /**
     * @return the staff_ID
     */
    public String getStaff_ID() {
        return staff_ID;
    }

    /**
     * @param staff_ID the staff_ID to set
     */
    public void setStaff_ID(String staff_ID) {
        this.staff_ID = staff_ID;
    }
    
}

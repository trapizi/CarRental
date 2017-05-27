/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.Date;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.*;
/**
 *
 * @author elizabeth
 */
public class Consultation {
    private IntegerProperty consultationNum;
    private FloatProperty consultationPrice;
    private ObjectProperty consultationTime;
    private ObjectProperty consultationDate;
    private IntegerProperty corporateID;
    private Corporate corporate;

    public Consultation() {
        this.consultationNum = new SimpleIntegerProperty();
        this.consultationPrice = new SimpleFloatProperty();
        this.consultationTime = new SimpleObjectProperty<Time>();
        this.consultationDate = new SimpleObjectProperty<Date>();
        this.corporateID = new SimpleIntegerProperty();
        
   
    }

    @Override
    public String toString() {
        return "Consultation number: " + this.getConsultationNum() + "CorporateID: " + this.corporate.getCorporateID();
    }
    /**
     * @return the consultationNum
     */
    public int getConsultationNum() {
        return consultationNum.get();
    }

    /**
     * @param consultationNum the consultationNum to set
     */
    public void setConsultationNum(int consultationNum) {
        this.consultationNum.set(consultationNum);
    }
   
    public IntegerProperty consultationNumProperty() {
        return consultationNum;
    }
    /**
     * @return the consultationPrice
     */
    public float getConsultationPrice() {
        return consultationPrice.get();
    }

    /**
     * @param consultationPrice the consultationPrice to set
     */
    public void setConsultationPrice(float consultationPrice) {
        this.consultationPrice.set(consultationPrice);
    }
    
    public FloatProperty consultationPriceProperty() {
		return consultationPrice;
    } /**
     * @return the consultationTime
     */
    public Object getConsultationTime() {
        return consultationTime.get();
    }

    /**
     * @param consultationTime the consultationTime to set
     */
    public void setConsultationTime(Time consultationTime) {
        this.consultationTime.set(consultationTime);
    }

    public ObjectProperty consultationTimeProperty() {
        return consultationTime;
    }
    /**
     * @return the consultationDate
     */
    public Object getConsultationDate() {
        return consultationDate.get();
    }

    /**
     * @param consultationDate the consultationDate to set
     */
    public void setConsultationDate(Date consultationDate) {
        this.consultationDate.set(consultationDate);
    }
    
    public ObjectProperty consultationDateProperty() {
        return consultationDate;
    }
    /**
     * @return the corporateID
     */
    public int getCorporateID() {
        return corporateID.get();
    }

    /**
     * @param corporateID the corporateID to set
     */
    public void setCorporateID(int corporateID) {
        this.corporateID.set(corporateID);
    }
    
    public IntegerProperty corporateIDProperty() {
        return corporateID;
    }
}

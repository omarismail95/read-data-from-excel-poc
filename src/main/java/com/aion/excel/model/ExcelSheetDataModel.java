package com.aion.excel.model;

import java.util.Date;

/**
 * Excel Sheet Data Model Class.
 *
 * @author Omar Ismail
 * @project poc
 * @date 18/04/2023
 */
public class ExcelSheetDataModel {

    private String member;
    private long enquiryReference;
    private EnquiredProduct enquiredProduct;
    private EnquiryType enquiryType;
    private long enquiredId;

    private String enquiryDate;

    private String enquiryTime;

    private String userId;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public long getEnquiryReference() {
        return enquiryReference;
    }

    public void setEnquiryReference(long enquiryReference) {
        this.enquiryReference = enquiryReference;
    }

    public EnquiredProduct getEnquiredProduct() {
        return enquiredProduct;
    }

    public void setEnquiredProduct(EnquiredProduct enquiredProduct) {
        this.enquiredProduct = enquiredProduct;
    }

    public EnquiryType getEnquiryType() {
        return enquiryType;
    }

    public void setEnquiryType(EnquiryType enquiryType) {
        this.enquiryType = enquiryType;
    }

    public long getEnquiredId() {
        return enquiredId;
    }

    public void setEnquiredId(long enquiredId) {
        this.enquiredId = enquiredId;
    }

    public String getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(String enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public String getEnquiryTime() {
        return enquiryTime;
    }

    public void setEnquiryTime(String enquiryTime) {
        this.enquiryTime = enquiryTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

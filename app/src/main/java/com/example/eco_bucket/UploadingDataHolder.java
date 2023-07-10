package com.example.eco_bucket;

public class UploadingDataHolder {

    String Pname, Paddress, Ppincode, PphoneNumber, time,
            date, PaymentType, NumberOfItems, AmountPaid,
            Type, SubType,imageUrl,DiliveryStatus,username,phoneNo;




    UploadingDataHolder() {

    }



    public UploadingDataHolder(String pname, String paddress, String ppincode,
                               String pphoneNumber, String time, String date, String paymentType,
                               String numberOfItems, String amountPaid, String type,
                               String subType, String imageUrl, String diliveryStatus) {
        Pname = pname;
        Paddress = paddress;
        Ppincode = ppincode;
        PphoneNumber = pphoneNumber;
        this.time = time;
        this.date = date;
        PaymentType = paymentType;
        NumberOfItems = numberOfItems;
        AmountPaid = amountPaid;
        Type = type;
        SubType = subType;
        this.imageUrl =imageUrl;
        this.DiliveryStatus = diliveryStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNo() { return phoneNo; }

    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public UploadingDataHolder(String pname, String username, String phoneNo) {
        this.Pname = pname;
        this.username = username;
        this.phoneNo = phoneNo;
    }


    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPaddress() {
        return Paddress;
    }

    public void setPaddress(String paddress) {
        Paddress = paddress;
    }

    public String getPpincode() {
        return Ppincode;
    }

    public void setPpincode(String ppincode) {
        Ppincode = ppincode;
    }

    public String getPphoneNumber() {
        return PphoneNumber;
    }

    public void setPphoneNumber(String pphoneNumber) {
        PphoneNumber = pphoneNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getNumberOfItems() {
        return NumberOfItems;
    }

    public void setNumberOfItems(String numberOfItems) {
        NumberOfItems = numberOfItems;
    }

    public String getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        AmountPaid = amountPaid;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSubType() {
        return SubType;
    }

    public void setSubType(String subType) {
        SubType = subType;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDiliveryStatus() { return DiliveryStatus; }

    public void setDiliveryStatus(String diliveryStatus) { DiliveryStatus = diliveryStatus; }
}

package com.example.eco_bucket;

public class feedback_dataHolder {

    String Nname, feedback;

    feedback_dataHolder(){

    }

    public feedback_dataHolder(String nname, String feedback) {
        Nname = nname;
        this.feedback = feedback;
    }

    public String getNname() {
        return Nname;
    }

    public void setNname(String nname) { Nname = nname; }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


}

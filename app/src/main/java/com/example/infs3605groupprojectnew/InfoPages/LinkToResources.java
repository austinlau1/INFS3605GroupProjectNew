package com.example.infs3605groupprojectnew.InfoPages;

public class LinkToResources {
    private String Gujaga;
    private String IndigiGrow;
    private String LaPerouse;
    private String NuraGiliUnit;

    public LinkToResources (){
    }

    public LinkToResources (String Gujaga, String IndigiGrow, String LaPerouse, String NuraGiliUnit){
        this.Gujaga = Gujaga;
        this.IndigiGrow = IndigiGrow;
        this.LaPerouse = LaPerouse;
        this.NuraGiliUnit = NuraGiliUnit;
    }

    public String getGujaga() {
        return Gujaga;
    }

    public void setGujaga(String gujaga) {
        Gujaga = gujaga;
    }

    public String getIndigiGrow() {
        return IndigiGrow;
    }

    public void setIndigiGrow(String indigiGrow) {
        IndigiGrow = indigiGrow;
    }

    public String getLaPerouse() {
        return LaPerouse;
    }

    public void setLaPerouse(String laPerouse) {
        LaPerouse = laPerouse;
    }

    public String getNuraGiliUnit() {
        return NuraGiliUnit;
    }

    public void setNuraGiliUnit(String nuraGiliUnit) {
        NuraGiliUnit = nuraGiliUnit;
    }
}


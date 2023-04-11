package com.example.infs3605groupprojectnew.InfoPages;

public class Biodiversity {
    private String Desc;
    private String Values;
    private String Benefits;
    private String Importance;

    public Biodiversity (){
    }

    public Biodiversity (String Desc, String Values, String Benefits, String Importance){
        this.Desc = Desc;
        this.Values = Values;
        this.Benefits = Benefits;
        this.Importance = Importance;
    }


    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public String getValues() {
        return Values;
    }

    public void setValues(String values) {
        Values = values;
    }

    public String getBenefits() {
        return Benefits;
    }

    public void setBenefits(String benefits) {
        Benefits = benefits;
    }

    public String getImportance() {
        return Importance;
    }

    public void setImportance(String importance) {
        Importance = importance;
    }
}

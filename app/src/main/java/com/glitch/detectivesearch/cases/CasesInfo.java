package com.glitch.detectivesearch.cases;

public class CasesInfo{
    private String case_name;
    private int icon_case;
    private String enabled;
    private boolean isCase;

    public CasesInfo(String case_name,int icon_case,String enabled,boolean isCase){
        this.case_name=case_name;
        this.icon_case=icon_case;
        this.enabled=enabled;
        this.isCase =isCase;
    }

    public CasesInfo(){
        this.isCase = true;
        this.case_name="";
        this.icon_case=0;
        this.enabled="false";
    }

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }

    public int getIcon_case() {
        return icon_case;
    }

    public void setIcon_case(int icon_case) {
        this.icon_case = icon_case;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public boolean isCase() {
        return isCase;
    }

    public void setCase(boolean aCase) {
        isCase = aCase;
    }
}

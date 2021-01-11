package com.example.mini_projet_android;

public class Mission {
    private String id;
    private String userid;
    private String nomposte;
    private String datdeb;
    private String datefin;
    private String tjm;
    private String description;

    public Mission(String id, String userid, String nomposte, String datdeb, String datefin, String tjm, String description) {
        this.id = id;
        this.userid = userid;
        this.nomposte = nomposte;
        this.datdeb = datdeb;
        this.datefin = datefin;
        this.tjm = tjm;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", nomposte='" + nomposte + '\'' +
                ", datdeb='" + datdeb + '\'' +
                ", datefin='" + datefin + '\'' +
                ", tjm='" + tjm + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNomposte() {
        return nomposte;
    }

    public void setNomposte(String nomposte) {
        this.nomposte = nomposte;
    }

    public String getDatdeb() {
        return datdeb;
    }

    public void setDatdeb(String datdeb) {
        this.datdeb = datdeb;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getTjm() {
        return tjm;
    }

    public void setTjm(String tjm) {
        this.tjm = tjm;
    }
}

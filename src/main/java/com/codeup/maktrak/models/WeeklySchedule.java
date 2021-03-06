package com.codeup.maktrak.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WeeklySchedule {
    @Id @GeneratedValue
    private long id;

    @OneToOne
    private DailyMacro mondayMacro;

    @OneToOne
    private DailyMacro tuesdayMacro;

    @OneToOne
    private DailyMacro wednesdayMacro;

    @OneToOne
    private DailyMacro thursdayMacro;

    @OneToOne
    private DailyMacro fridayMacro;

    @OneToOne
    private DailyMacro saturdayMacro;

    @OneToOne
    private DailyMacro sundayMacro;

    @OneToOne
    private User owner;

    public WeeklySchedule(DailyMacro mondayMacro, DailyMacro tuesdayMacro, DailyMacro wednesdayMacro, DailyMacro thursdayMacro, DailyMacro fridayMacro, DailyMacro saturdayMacro, DailyMacro sundayMacro, User owner) {
        this.mondayMacro = mondayMacro;
        this.tuesdayMacro = tuesdayMacro;
        this.wednesdayMacro = wednesdayMacro;
        this.thursdayMacro = thursdayMacro;
        this.fridayMacro = fridayMacro;
        this.saturdayMacro = saturdayMacro;
        this.sundayMacro = sundayMacro;
        this.owner = owner;
    }

    public WeeklySchedule() {
    }

    public void removeSpecificRoutine(DailyMacro macro) {
        if(this.mondayMacro != null) {
            if(this.mondayMacro.getId() == macro.getId()) {
                this.mondayMacro = null;
            }
        }
        if(this.tuesdayMacro != null) {
            if(this.tuesdayMacro.getId() == macro.getId()) {
                this.tuesdayMacro = null;
            }
        }
        if(this.wednesdayMacro != null) {
            if(this.wednesdayMacro.getId() == macro.getId()) {
                this.wednesdayMacro = null;
            }
        }
        if(this.thursdayMacro != null) {
            if(this.thursdayMacro.getId() == macro.getId()) {
                this.thursdayMacro = null;
            }
        }
        if(this.fridayMacro != null) {
            if(this.fridayMacro.getId() == macro.getId()) {
                this.fridayMacro = null;
            }
        }
        if(this.saturdayMacro != null) {
            if(this.saturdayMacro.getId() == macro.getId()) {
                this.saturdayMacro = null;
            }
        }
        if(this.sundayMacro != null) {
            if(this.sundayMacro.getId() == macro.getId()) {
                this.sundayMacro = null;
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DailyMacro getMondayMacro() {
        return mondayMacro;
    }

    public void setMondayMacro(DailyMacro mondayMacro) {
        this.mondayMacro = mondayMacro;
    }

    public DailyMacro getTuesdayMacro() {
        return tuesdayMacro;
    }

    public void setTuesdayMacro(DailyMacro tuesdayMacro) {
        this.tuesdayMacro = tuesdayMacro;
    }

    public DailyMacro getWednesdayMacro() {
        return wednesdayMacro;
    }

    public void setWednesdayMacro(DailyMacro wednesdayMacro) {
        this.wednesdayMacro = wednesdayMacro;
    }

    public DailyMacro getThursdayMacro() {
        return thursdayMacro;
    }

    public void setThursdayMacro(DailyMacro thursdayMacro) {
        this.thursdayMacro = thursdayMacro;
    }

    public DailyMacro getFridayMacro() {
        return fridayMacro;
    }

    public void setFridayMacro(DailyMacro fridayMacro) {
        this.fridayMacro = fridayMacro;
    }

    public DailyMacro getSaturdayMacro() {
        return saturdayMacro;
    }

    public void setSaturdayMacro(DailyMacro saturdayMacro) {
        this.saturdayMacro = saturdayMacro;
    }

    public DailyMacro getSundayMacro() {
        return sundayMacro;
    }

    public void setSundayMacro(DailyMacro sundayMacro) {
        this.sundayMacro = sundayMacro;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

package com.nic.aishe.master.util;

import com.nic.aishe.master.entity.BroadCategory;
import com.nic.aishe.master.entity.BroadName;
import com.nic.aishe.master.entity.ProgramName;

import java.io.Serializable;

public class ProgrammeGroupCategoryId implements Serializable {
    private static final long serialVersionUID = 4199807624447134775L;

    private ProgramName programmeId;

    private BroadCategory broadDisciplineGroupCategoryId;

    private BroadName broadDisciplineGroupId;

    public ProgramName getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(ProgramName programmeId) {
        this.programmeId = programmeId;
    }

    public BroadCategory getBroadDisciplineGroupCategoryId() {
        return broadDisciplineGroupCategoryId;
    }

    public void setBroadDisciplineGroupCategoryId(BroadCategory broadDisciplineGroupCategoryId) {
        this.broadDisciplineGroupCategoryId = broadDisciplineGroupCategoryId;
    }

    public BroadName getBroadDisciplineGroupId() {
        return broadDisciplineGroupId;
    }

    public void setBroadDisciplineGroupId(BroadName broadDisciplineGroupId) {
        this.broadDisciplineGroupId = broadDisciplineGroupId;
    }
}

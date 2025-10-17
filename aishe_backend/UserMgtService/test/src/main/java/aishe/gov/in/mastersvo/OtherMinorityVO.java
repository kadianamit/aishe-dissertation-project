package aishe.gov.in.mastersvo;

import java.io.Serializable;

import aishe.gov.in.masterseo.RefCourseLevel;
import aishe.gov.in.masterseo.RefCourseMode;
import aishe.gov.in.masterseo.RefCourseType;


public class OtherMinorityVO implements Serializable{

	private static final long serialVersionUID = 4804942948270216297L;
	
	private Integer courseId;
	
	private RefCourseMode courseMode;

	private RefCourseLevel levelId;

	private RefProgramme programmeId;

	private String discipline;

	private RefCourseType typeId;

	private String year;

	private RefBroadDisciplineGroup broadGroupId;
		
	private OtherMinorityCategoryCountEO otherMinorityCategoryCount;

		
	
}
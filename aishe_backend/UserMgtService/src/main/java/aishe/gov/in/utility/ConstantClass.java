package aishe.gov.in.utility;

public enum ConstantClass {
	
	basic_detail("1.1.1"),        
	address("1.1.2"),
	econtact("1.1.3"),
	vernacular_name("1.1.4"),
	alternate_name("1.1.5"),
	regional_center("2.1.1"),	
	off_shore_center_college("11.1.1"),
	//off_shore_center_college("2.1.2"),
	
	list_of_faculty("3.1.1"),
	list_of_department("3.1.2"),
	regular_prog_through_faculty("3.2.1"),
	regular_prog_through_department("3.2.2"),
	regular_prog_other("3.2.3"),
	distance_prog_through_faculty("3.3.1"),
	distance_prog_through_department("3.3.2"),
	distance_prog_other	("3.3.3"),
	
	enroll_regular_prog_through_faculty("4.1.1"),
	enroll_regular_prog_through_department("4.1.2"),
	enroll_regular_prog_other("4.1.3"),
	
	
	enroll_distance_prog_through_faculty("4.2.1"),
	enroll_distance_prog_through_department("4.2.2"),
	enroll_distance_prog_other("4.2.3"),
	
	enroll_distance_prog_regional_center_through_faculty("4.3.1"),
	enroll_distance_prog_regional_center_through_department("4.3.2"),
	enroll_distance_prog_regional_center_through_other("4.3.3"),
	
	
	enroll_regular_foreign_student_through_faculty("5.1.1"),
	enroll_regular_foreign_student_through_department("5.1.2"),
	enroll_regular_foreign_student_through_other("5.1.3"),
	enroll_distance_foreign_student_through_faculty("5.2.1"),
	enroll_distance_foreign_student_through_department("5.2.2"),
	enroll_distance_foreign_student_through_other("5.2.3"),
	enroll_foreign_student_count("5.3.1"),

	exam_result_regular_through_faculty("6.1.1"),
	exam_result_regular_through_department("6.1.2"),
	exam_result_regular_through_other("6.1.3"),
	exam_result_distance_through_faculty("6.2.1"),
	exam_result_distance_through_department("6.2.2"),
	exam_result_distance_through_other("6.2.3"),
	exam_result_private_external("6.3.1"),
	exam_result_public_external("6.3.2"),
	//distance_exam_result_regional_centre("6.4.1"),
	distance_exam_result_regional_faculty("6.4.1"),
	distance_exam_result_regional_department("6.4.2"),
	distance_exam_result_regional_other("6.4.3"),
	//placement_details("14.1.1"),
	placement_details("6.4.1"),
	
	teaching_staff("7.1.1"),
	teaching_staff_econtact("7.1.2"),
	teaching_staff_vernacular_name("7.1.3"),
	non_teaching_staff_detail("7.2.1"),
	teaching_staff_sanctioned_strength("7.3.1"),
	
	financial_info_income("8.1.1"),
	financial_info_expenditure("8.1.2"),
	infra("9.1.1"),
	scholarship("10.1.1"),
	fellowship("10.1.2"),
	education_loan("10.1.3"),
	accreditation("10.1.4"),
	regulatory_info	("12.1.1"),
	final_lock("13.1.1");

   private String text;
    ConstantClass(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
    public boolean equalsName(String name) {
    	return text.equals(name);
    }

    public static ConstantClass fromString(String text) {
        for (ConstantClass b : ConstantClass.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
    public static ConstantClass fromStringName(String text) {
        for (ConstantClass b : ConstantClass.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
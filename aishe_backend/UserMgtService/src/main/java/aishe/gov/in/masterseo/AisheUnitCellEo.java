package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "public.aishe_unit_cell")
public class AisheUnitCellEo implements Serializable {
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 4844598342437081581L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "state_code")
    private RefState refState;
    
    @Column(name = "whether_state_having_aishe_unit_cell")
    private Boolean whetherStateHavingAisheUnitCell;
    
    @Column(name = "officer_name")
    private String officerName;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "current_salary")
    private Integer currentSalary;
    
    @Column(name = "mode_of_engagement")
    private String modeOfEngagement;
}
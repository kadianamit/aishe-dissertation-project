package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "ciso.system_details")
public class CisoSystemDetailsEO {
    @Id 
    @GenericGenerator(name="systemdetails" , strategy="increment")
	@GeneratedValue(generator="systemdetails")
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "designation")
    private String designation;
    @Column(name = "section")
    private String section;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "computer_model_number")
    private String computerModelNumber;
    @Column(name = "operating_system")
    private String operatingSystem;
    @Column(name = "configuration")
    private String configuration;
    @Column(name = "cpu_serial_number")
    private String cpuSerialNumber;
    @Column(name = "warranty_start_date")
    private String warrantyStartDate;
    @Column(name = "warranty_end_date")
    private String warrantyEndDate;
    @Column(name = "life_as_on_30_06_2022_in_years")
    private String lifeAsOn_30_06_2022_inYears;
}

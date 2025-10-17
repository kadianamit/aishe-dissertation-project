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
@Table(name = "ciso.server_details")
public class CisoServerDetailsEO {
    @Id 
    @GenericGenerator(name="serverdetails" , strategy="increment")
	@GeneratedValue(generator="serverdetails")
    @Column(name = "id")
    private Integer id;
    @Column(name = "application_name")
    private String applicationName;
    @Column(name = "server_type")
    private String serverType;
    @Column(name = "operating_system")
    private String operatingSystem;
    @Column(name = "no_of_cpu")
    private Integer noOfCpu;
    @Column(name = "ram_size_in_gb")
    private Integer ramSizeInGb;
    @Column(name = "public_ip")
    private String publicIp;
    @Column(name = "private_ip")
    private String privateIp;
    @Column(name = "server_location")
    private String serverLocation;
}

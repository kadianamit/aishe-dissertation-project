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
@Table(name = "ciso.switch_network_details")
public class CisoSwitchNetworkDetailsEO {
    @Id 
    @GenericGenerator(name="switchnetwork" , strategy="increment")
	@GeneratedValue(generator="switchnetwork")
    @Column(name = "id")
    private Integer id;
    @Column(name = "floor")
    private String floor;
    @Column(name = "room_number")
    private String roomNumber;
    @Column(name = "switch_host_name")
    private String switchHostName;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "model")
    private String model;
    @Column(name = "switch_part_number")
    private String switchPartNumber;
    @Column(name = "fast_ethernet")
    private String fastEthernet;
    @Column(name = "gigabit_port")
    private String gigabitPort;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "version")
    private String version;
    @Column(name = "backbone_location")
    private String backboneLocation;
    @Column(name = "port_number")
    private String portNumber;
    @Column(name = "utp_fiber")
    private String utpFiber;
}

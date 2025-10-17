package aishe.gov.in.masterseo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ciso.ciso_letter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CisoLetterEo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotBlank
    @Column(name = "agency_code")
    private String agencyCode;
    @NotNull
    @NotBlank
    private String post;
    @Column(name = "letter_title")
    private String letterTitle;
    @Column(name = "letter")
    private byte[] letter;
    @Column(name = "remarks")
    private String remarks;
    @JsonIgnore
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Transient
    private String issuedDated;
}

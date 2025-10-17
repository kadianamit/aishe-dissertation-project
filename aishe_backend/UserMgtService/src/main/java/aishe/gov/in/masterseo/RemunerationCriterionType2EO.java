package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Entity
@Table(name = "remuneration_criterion_type_2")
public class RemunerationCriterionType2EO {
    @Id
    @Column(name = "form_upload_id")
    private Integer formUploadId;

    @Column(name = "total_number_of_programmes")
    private Integer noOfProgramme;
}

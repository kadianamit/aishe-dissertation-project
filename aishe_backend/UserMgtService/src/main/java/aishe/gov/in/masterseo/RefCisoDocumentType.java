package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ciso.ref_document_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefCisoDocumentType implements Serializable {

	private static final long serialVersionUID = 4009016860898217740L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "type")
	private String type;
}
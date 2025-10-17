package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ciso.document")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CisoDocumentEo {
	@Id
    @GenericGenerator(name="documentciso" , strategy="increment")
	@GeneratedValue(generator="documentciso")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_file")
    private byte[] documentFile;

    @Column(name = "uploaded_by")
    private String uploadedBy;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

	@Column(name = "file_name")
	private String fileName;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "document_type_id")
	private RefCisoDocumentType refDocumentType;

}

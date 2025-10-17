package aishe.gov.in.mastersvo;

import java.util.Date;

public interface EntitySignature {

    Long getId();

    Long getVersion();

    boolean isDeleted();

    void setDeleted(boolean isDeleted);

    Date getDateCreated();

    Date getLastModified();

    Date getDateDeleted();
}

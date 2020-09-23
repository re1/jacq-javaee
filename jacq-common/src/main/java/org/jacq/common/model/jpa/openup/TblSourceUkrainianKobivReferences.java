package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_ukrainian_kobiv_references", schema = "openup")
public class TblSourceUkrainianKobivReferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Long id;
    @Basic
    @Size(max = 6)
    @Column(name = "short", length = 6)
    private String shortField;
    @Basic
    @Size(max = 338)
    @Column(name = "reference", length = 338)
    private String reference;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortField() {
        return shortField;
    }

    public void setShortField(String shortField) {
        this.shortField = shortField;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceUkrainianKobivReferences that = (TblSourceUkrainianKobivReferences) o;
        return Objects.equals(shortField, that.shortField) &&
                Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortField, reference);
    }
}

package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_ukrainian_kobiv_regions", schema = "openup")
public class TblSourceUkrainianKobivRegions {
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
    @Size(max = 36)
    @Column(name = "region", length = 36)
    private String region;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceUkrainianKobivRegions that = (TblSourceUkrainianKobivRegions) o;
        return Objects.equals(shortField, that.shortField) &&
                Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortField, region);
    }
}

package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_newZealand_landcare", schema = "openup")
public class TblSourceNewZealandLandcare implements Serializable {
    // using nameguid with VernacularGuid as id as their combination is unique and not null
    // TODO: check if adding an id column is favorable
    @Id
    @Basic
    @Size(max = 36)
    @Column(name = "nameguid")
    private String nameguid;
    @Basic
    @Size(max = 200)
    @Column(name = "NameFull")
    private String nameFull;
    @Id
    @Basic
    @Size(max = 36)
    @Column(name = "VernacularGuid")
    private String vernacularGuid;
    @Basic
    @Size(max = 200)
    @Column(name = "VernacularName")
    private String vernacularName;
    @Basic
    @Column(name = "ReferenceGenCitation", length = -1)
    private String referenceGenCitation;
    @Basic
    @Size(max = 100)
    @Column(name = "GeoRegionName")
    private String geoRegionName;
    @Basic
    @Size(max = 30)
    @Column(name = "LanguageEnglish")
    private String languageEnglish;
    @Basic
    @Size(max = 6)
    @Column(name = "LanguageISOCode")
    private String languageIsoCode;

    public String getNameguid() {
        return nameguid;
    }

    public void setNameguid(String nameguid) {
        this.nameguid = nameguid;
    }

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getVernacularGuid() {
        return vernacularGuid;
    }

    public void setVernacularGuid(String vernacularGuid) {
        this.vernacularGuid = vernacularGuid;
    }

    public String getVernacularName() {
        return vernacularName;
    }

    public void setVernacularName(String vernacularName) {
        this.vernacularName = vernacularName;
    }

    public String getReferenceGenCitation() {
        return referenceGenCitation;
    }

    public void setReferenceGenCitation(String referenceGenCitation) {
        this.referenceGenCitation = referenceGenCitation;
    }

    public String getGeoRegionName() {
        return geoRegionName;
    }

    public void setGeoRegionName(String geoRegionName) {
        this.geoRegionName = geoRegionName;
    }

    public String getLanguageEnglish() {
        return languageEnglish;
    }

    public void setLanguageEnglish(String languageEnglish) {
        this.languageEnglish = languageEnglish;
    }

    public String getLanguageIsoCode() {
        return languageIsoCode;
    }

    public void setLanguageIsoCode(String languageIsoCode) {
        this.languageIsoCode = languageIsoCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceNewZealandLandcare that = (TblSourceNewZealandLandcare) o;
        return Objects.equals(nameguid, that.nameguid) &&
                Objects.equals(nameFull, that.nameFull) &&
                Objects.equals(vernacularGuid, that.vernacularGuid) &&
                Objects.equals(vernacularName, that.vernacularName) &&
                Objects.equals(referenceGenCitation, that.referenceGenCitation) &&
                Objects.equals(geoRegionName, that.geoRegionName) &&
                Objects.equals(languageEnglish, that.languageEnglish) &&
                Objects.equals(languageIsoCode, that.languageIsoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameguid, nameFull, vernacularGuid, vernacularName, referenceGenCitation, geoRegionName, languageEnglish, languageIsoCode);
    }
}

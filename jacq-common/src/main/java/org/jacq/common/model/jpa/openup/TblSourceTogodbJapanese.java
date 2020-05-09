package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_togodb_japanese", schema = "openup")
public class TblSourceTogodbJapanese {
    @Basic
    @Size(max = 150)
    @Column(name = "scientific_name", nullable = false, length = 150)
    private String scientificName;
    @Basic
    @Size(max = 150)
    @Column(name = "japanese_name", nullable = false, length = 150)
    private String japaneseName;
    @Basic
    @Size(max = 255)
    @Column(name = "information_source_distributor")
    private String informationSourceDistributor;
    @Basic
    @Size(max = 255)
    @Column(name = "information_source_name")
    private String informationSourceName;
    @Basic
    @Size(max = 255)
    @Column(name = "information_source_edition")
    private String informationSourceEdition;
    @Id
    @Basic
    @Column(name = "ID")
    private int id;

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getInformationSourceDistributor() {
        return informationSourceDistributor;
    }

    public void setInformationSourceDistributor(String informationSourceDistributor) {
        this.informationSourceDistributor = informationSourceDistributor;
    }

    public String getInformationSourceName() {
        return informationSourceName;
    }

    public void setInformationSourceName(String informationSourceName) {
        this.informationSourceName = informationSourceName;
    }

    public String getInformationSourceEdition() {
        return informationSourceEdition;
    }

    public void setInformationSourceEdition(String informationSourceEdition) {
        this.informationSourceEdition = informationSourceEdition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceTogodbJapanese that = (TblSourceTogodbJapanese) o;
        return id == that.id &&
                Objects.equals(scientificName, that.scientificName) &&
                Objects.equals(japaneseName, that.japaneseName) &&
                Objects.equals(informationSourceDistributor, that.informationSourceDistributor) &&
                Objects.equals(informationSourceName, that.informationSourceName) &&
                Objects.equals(informationSourceEdition, that.informationSourceEdition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scientificName, japaneseName, informationSourceDistributor, informationSourceName, informationSourceEdition, id);
    }
}

package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_russian_plantarium", schema = "openup")
public class TblSourceRussianPlantarium {
    @Id
    @Basic
    @Column(name = "ID")
    private int id;
    @Basic
    @NotNull
    @Column(name = "scientific_name", nullable = false, length = 150)
    private String scientificName;
    @Basic
    @NotNull
    @Size(max = 100)
    @Column(name = "author", nullable = false, length = 100)
    private String author;
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "russian_name", nullable = false, length = 150)
    private String russianName;
    @Basic
    @NotNull
    @Column(name = "type", nullable = false)
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRussianName() {
        return russianName;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceRussianPlantarium that = (TblSourceRussianPlantarium) o;
        return id == that.id &&
                type == that.type &&
                Objects.equals(scientificName, that.scientificName) &&
                Objects.equals(author, that.author) &&
                Objects.equals(russianName, that.russianName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scientificName, author, russianName, type);
    }
}

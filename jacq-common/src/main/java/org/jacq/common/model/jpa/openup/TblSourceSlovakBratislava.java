package org.jacq.common.model.jpa.openup;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_slovak_bratislava", schema = "openup")
public class TblSourceSlovakBratislava {
    @Basic
    @NotNull
    @Size(max = 150)
    @Column(name = "fldName", nullable = false, length = 150)
    private String fldName;
    @Basic
    @NotNull
    @Size(max = 255)
    @Column(name = "fldNameSK_prefix", nullable = false)
    private String fldNameSkPrefix;
    @Basic
    @NotNull
    @Size(max = 255)
    @Column(name = "fldNameSK", nullable = false)
    private String fldNameSk;

    public String getFldName() {
        return fldName;
    }

    public void setFldName(String fldName) {
        this.fldName = fldName;
    }

    public String getFldNameSkPrefix() {
        return fldNameSkPrefix;
    }

    public void setFldNameSkPrefix(String fldNameSkPrefix) {
        this.fldNameSkPrefix = fldNameSkPrefix;
    }

    public String getFldNameSk() {
        return fldNameSk;
    }

    public void setFldNameSk(String fldNameSk) {
        this.fldNameSk = fldNameSk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceSlovakBratislava that = (TblSourceSlovakBratislava) o;
        return Objects.equals(fldName, that.fldName) &&
                Objects.equals(fldNameSkPrefix, that.fldNameSkPrefix) &&
                Objects.equals(fldNameSk, that.fldNameSk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fldName, fldNameSkPrefix, fldNameSk);
    }
}

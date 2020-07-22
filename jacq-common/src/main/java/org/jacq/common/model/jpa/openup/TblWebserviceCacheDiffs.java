package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author re1
 */
@Entity
@Table(name = "tbl_webservice_cache_diffs", schema = "openup")
public class TblWebserviceCacheDiffs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tbl_webservice_cache_id", nullable = false)
    private int tblWebserviceCacheId;
    @Basic
    @Lob
    @NotNull
    @Column(name = "diff", nullable = false, length = -1)
    private String diff;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTblWebserviceCacheId() {
        return tblWebserviceCacheId;
    }

    public void setTblWebserviceCacheId(int tblWebserviceCacheId) {
        this.tblWebserviceCacheId = tblWebserviceCacheId;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblWebserviceCacheDiffs that = (TblWebserviceCacheDiffs) o;
        return id == that.id &&
                tblWebserviceCacheId == that.tblWebserviceCacheId &&
                timestamp == that.timestamp &&
                Objects.equals(diff, that.diff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tblWebserviceCacheId, diff, timestamp);
    }
}

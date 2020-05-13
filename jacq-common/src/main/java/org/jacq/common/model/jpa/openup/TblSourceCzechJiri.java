package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * This interface is used to query all Czech Jiri related table classes
 * @see TblSourceCzechJiriBezo1
 * @see TblSourceCzechJiriRoztoci
 * @see TblSourceCzechJiriVacnatci
 */
public interface TblSourceCzechJiri {
    Long getId();

    void setId(Long id);

    String getLatinName();

    void setLatinName(String latinName);

    String getCzechName();

    void setCzechName(String czechName);

    String getFirstSynonym();

    void setFirstSynonym(String firstSynonym);

    String getSecondSynonym();

    void setSecondSynonym(String secondSynonym);
}

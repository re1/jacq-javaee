package org.jacq.common.model.names;

import java.util.List;

public interface NameResponse<T> {
    List<T> getResult();

    void setResult(List<T> result);
}

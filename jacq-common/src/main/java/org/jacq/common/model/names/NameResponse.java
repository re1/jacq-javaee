package org.jacq.common.model.names;

import java.util.List;

public interface NameResponse<T> {
    void setResult(List<T> result);
}

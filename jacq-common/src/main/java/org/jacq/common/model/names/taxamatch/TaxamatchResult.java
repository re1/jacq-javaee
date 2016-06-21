package org.jacq.common.model.names.taxamatch;

import java.util.ArrayList;
import java.util.List;

public class TaxamatchResult {

    private String error;
    private List<Result> result = new ArrayList<>();

    /**
     *
     * @return The error
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return The result
     */
    public List<Result> getResult() {
        return result;
    }

    /**
     *
     * @param result The result
     */
    public void setResult(List<Result> result) {
        this.result = result;
    }

}

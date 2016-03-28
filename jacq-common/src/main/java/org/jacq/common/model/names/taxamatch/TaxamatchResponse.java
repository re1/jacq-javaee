package org.jacq.common.model.names.taxamatch;

public class TaxamatchResponse {

    private Long id;
    private TaxamatchResult result;
    private Object error;

    /**
     *
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return The result
     */
    public TaxamatchResult getResult() {
        return result;
    }

    /**
     *
     * @param result The result
     */
    public void setResult(TaxamatchResult result) {
        this.result = result;
    }

    /**
     *
     * @return The error
     */
    public Object getError() {
        return error;
    }

    /**
     *
     * @param error The error
     */
    public void setError(Object error) {
        this.error = error;
    }

}

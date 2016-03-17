package org.jacq.common.model.names.taxamatch;

import org.jacq.common.model.names.taxamatch.Searchresult;
import java.util.ArrayList;
import java.util.List;

public class Result {

    private String searchtext;
    private String searchtextNearmatch;
    private Long rowsChecked;
    private String type;
    private String database;
    private Boolean includeCommonNames;
    private List<Searchresult> searchresult = new ArrayList<Searchresult>();

    /**
     *
     * @return The searchtext
     */
    public String getSearchtext() {
        return searchtext;
    }

    /**
     *
     * @param searchtext The searchtext
     */
    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext;
    }

    /**
     *
     * @return The searchtextNearmatch
     */
    public String getSearchtextNearmatch() {
        return searchtextNearmatch;
    }

    /**
     *
     * @param searchtextNearmatch The searchtextNearmatch
     */
    public void setSearchtextNearmatch(String searchtextNearmatch) {
        this.searchtextNearmatch = searchtextNearmatch;
    }

    /**
     *
     * @return The rowsChecked
     */
    public Long getRowsChecked() {
        return rowsChecked;
    }

    /**
     *
     * @param rowsChecked The rowsChecked
     */
    public void setRowsChecked(Long rowsChecked) {
        this.rowsChecked = rowsChecked;
    }

    /**
     *
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return The database
     */
    public String getDatabase() {
        return database;
    }

    /**
     *
     * @param database The database
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     *
     * @return The includeCommonNames
     */
    public Boolean getIncludeCommonNames() {
        return includeCommonNames;
    }

    /**
     *
     * @param includeCommonNames The includeCommonNames
     */
    public void setIncludeCommonNames(Boolean includeCommonNames) {
        this.includeCommonNames = includeCommonNames;
    }

    /**
     *
     * @return The searchresult
     */
    public List<Searchresult> getSearchresult() {
        return searchresult;
    }

    /**
     *
     * @param searchresult The searchresult
     */
    public void setSearchresult(List<Searchresult> searchresult) {
        this.searchresult = searchresult;
    }

}

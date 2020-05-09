package org.jacq.common.model.jpa.openup;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbl_source_hungarian_peregovits_literature", schema = "openup")
public class TblSourceHungarianPeregovitsLiterature {
    @Id
    @Column(name = "PUB_ID")
    private int pubId;
    @Basic
    @Column(name = "Authors")
    private String authors;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "Year")
    private Integer year;
    @Basic
    @Column(name = "Type", length = 10)
    private String type;
    @Basic
    @Column(name = "Series_journal_title")
    private String seriesJournalTitle;
    @Basic
    @Column(name = "volume_no", length = 10)
    private String volumeNo;
    @Basic
    @Column(name = "Publisher", length = 100)
    private String publisher;
    @Basic
    @Column(name = "Publisher_city", length = 100)
    private String publisherCity;
    @Basic
    @Column(name = "edition", length = 10)
    private String edition;
    @Basic
    @Column(name = "ISBN", length = 25)
    private String isbn;
    @Basic
    @Column(name = "pages", length = 10)
    private String pages;

    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeriesJournalTitle() {
        return seriesJournalTitle;
    }

    public void setSeriesJournalTitle(String seriesJournalTitle) {
        this.seriesJournalTitle = seriesJournalTitle;
    }

    public String getVolumeNo() {
        return volumeNo;
    }

    public void setVolumeNo(String volumeNo) {
        this.volumeNo = volumeNo;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherCity() {
        return publisherCity;
    }

    public void setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblSourceHungarianPeregovitsLiterature that = (TblSourceHungarianPeregovitsLiterature) o;
        return pubId == that.pubId &&
                Objects.equals(authors, that.authors) &&
                Objects.equals(title, that.title) &&
                Objects.equals(year, that.year) &&
                Objects.equals(type, that.type) &&
                Objects.equals(seriesJournalTitle, that.seriesJournalTitle) &&
                Objects.equals(volumeNo, that.volumeNo) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(publisherCity, that.publisherCity) &&
                Objects.equals(edition, that.edition) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(pages, that.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pubId, authors, title, year, type, seriesJournalTitle, volumeNo, publisher, publisherCity, edition, isbn, pages);
    }
}

/*
 * Copyright 2017 wkoller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jacq.output.model;

import java.util.ArrayList;
import java.util.List;
import org.jacq.common.model.jpa.RevClassification;
import org.jacq.common.model.jpa.TblGeoProvince;

/**
 *
 * @author wkoller
 */
public class RevClassificationView {

    private RevClassification revClassification;
    private List<TblGeoProvince> provinceList;

    public RevClassificationView(RevClassification revClassification) {
        this.revClassification = revClassification;

        this.provinceList = new ArrayList<>();

        if (this.getProvinceIds() != null && this.getProvinceCodes() != null) {
            String[] provinceIdArray = this.getProvinceIds().split(";");
            String[] provinceCodeArray = this.getProvinceCodes().split(";");

            for (int i = 0; i < provinceIdArray.length; i++) {
                TblGeoProvince geoProvince = new TblGeoProvince();
                geoProvince.setProvinceId(Integer.valueOf(provinceIdArray[i]));
                geoProvince.setProvinzCode(provinceCodeArray[i]);

                provinceList.add(geoProvince);
            }
        }
    }

    public Long getClassificationBrowserRevisionId() {
        return revClassification.getClassificationBrowserRevisionId();
    }

    public void setClassificationBrowserRevisionId(Long classificationBrowserRevisionId) {
        revClassification.setClassificationBrowserRevisionId(classificationBrowserRevisionId);
    }

    public long getClassificationId() {
        return revClassification.getClassificationId();
    }

    public void setClassificationId(long classificationId) {
        revClassification.setClassificationId(classificationId);
    }

    public long getScientificNameId() {
        return revClassification.getScientificNameId();
    }

    public void setScientificNameId(long scientificNameId) {
        revClassification.setScientificNameId(scientificNameId);
    }

    public Long getAccScientificNameId() {
        return revClassification.getAccScientificNameId();
    }

    public void setAccScientificNameId(Long accScientificNameId) {
        revClassification.setAccScientificNameId(accScientificNameId);
    }

    public short getPreferredTaxonomy() {
        return revClassification.getPreferredTaxonomy();
    }

    public void setPreferredTaxonomy(short preferredTaxonomy) {
        revClassification.setPreferredTaxonomy(preferredTaxonomy);
    }

    public String getAnnotations() {
        return revClassification.getAnnotations();
    }

    public void setAnnotations(String annotations) {
        revClassification.setAnnotations(annotations);
    }

    public short getLocked() {
        return revClassification.getLocked();
    }

    public void setLocked(short locked) {
        revClassification.setLocked(locked);
    }

    public String getSource() {
        return revClassification.getSource();
    }

    public void setSource(String source) {
        revClassification.setSource(source);
    }

    public Long getSourceId() {
        return revClassification.getSourceId();
    }

    public void setSourceId(Long sourceId) {
        revClassification.setSourceId(sourceId);
    }

    public long getUserId() {
        return revClassification.getUserId();
    }

    public void setUserId(long userId) {
        revClassification.setUserId(userId);
    }

    public Long getParentScientificNameId() {
        return revClassification.getParentScientificNameId();
    }

    public void setParentScientificNameId(Long parentScientificNameId) {
        revClassification.setParentScientificNameId(parentScientificNameId);
    }

    public String getNumber() {
        return revClassification.getNumber();
    }

    public void setNumber(String number) {
        revClassification.setNumber(number);
    }

    public Long getOrder() {
        return revClassification.getOrder();
    }

    public void setOrder(Long order) {
        revClassification.setOrder(order);
    }

    public String getScientificName() {
        return revClassification.getScientificName();
    }

    public void setScientificName(String scientificName) {
        revClassification.setScientificName(scientificName);
    }

    public String getScientificNameNoAuthor() {
        return revClassification.getScientificNameNoAuthor();
    }

    public void setScientificNameNoAuthor(String scientificNameNoAuthor) {
        revClassification.setScientificNameNoAuthor(scientificNameNoAuthor);
    }

    public String getProvinceIds() {
        return revClassification.getProvinceIds();
    }

    public void setProvinceIds(String provinceIds) {
        revClassification.setProvinceIds(provinceIds);
    }

    public String getProvinceCodes() {
        return revClassification.getProvinceCodes();
    }

    public void setProvinceCodes(String provinceCodes) {
        revClassification.setProvinceCodes(provinceCodes);
    }

    public Long getUuidMinterId() {
        return revClassification.getUuidMinterId();
    }

    public void setUuidMinterId(Long uuidMinterId) {
        revClassification.setUuidMinterId(uuidMinterId);
    }

    public List<TblGeoProvince> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<TblGeoProvince> provinceList) {
        this.provinceList = provinceList;
    }
}

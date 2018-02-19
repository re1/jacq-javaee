/*
 * Copyright 2018 wkoller.
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
package org.jacq.common.model.rest;

import java.util.ArrayList;
import java.util.List;
import org.jacq.common.model.jpa.TblCertificate;

/**
 *
 * @author wkoller
 */
public class CertificateResult {

    protected Long certificateId;
    protected String number;
    protected String annotation;
    protected CertificateTypeResult certificateType;

    public CertificateResult() {
        this.certificateType = new CertificateTypeResult();
    }

    public CertificateResult(TblCertificate tblCertificate) {
        if (tblCertificate != null) {
            this.certificateId = tblCertificate.getId();
            this.number = tblCertificate.getNumber();
            this.annotation = tblCertificate.getAnnotation();
            this.certificateType = new CertificateTypeResult(tblCertificate.getCertificateTypeId());
        }
    }

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public CertificateTypeResult getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateTypeResult certificateType) {
        this.certificateType = certificateType;
    }

    /**
     * Helper function for converting a list of TblCertificate entries to Certificate results
     *
     * @param tblCertificateList
     * @return
     */
    public static List<CertificateResult> fromList(List<TblCertificate> tblCertificateList) {
        List<CertificateResult> certificateList = new ArrayList<>();

        if (tblCertificateList != null) {
            for (TblCertificate tblCertificate : tblCertificateList) {
                certificateList.add(new CertificateResult(tblCertificate));
            }
        }

        return certificateList;
    }
}

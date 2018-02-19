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
import org.jacq.common.model.jpa.TblCertificateType;

/**
 *
 * @author wkoller
 */
public class CertificateTypeResult {

    protected Long certificateTypeId;
    protected String type;

    public CertificateTypeResult() {
    }

    public CertificateTypeResult(TblCertificateType tblCertificateType) {
        if (tblCertificateType != null) {
            this.certificateTypeId = tblCertificateType.getId();
            this.type = tblCertificateType.getType();
        }
    }

    public Long getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(Long certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Helper function for converting a list of TblCertificateType entries to CertificateType results
     *
     * @param tblCertificateTypeList
     * @return
     */
    public static List<CertificateTypeResult> fromList(List<TblCertificateType> tblCertificateTypeList) {
        List<CertificateTypeResult> certificateTypeList = new ArrayList<>();

        if (tblCertificateTypeList != null) {
            for (TblCertificateType tblCertificateType : tblCertificateTypeList) {
                certificateTypeList.add(new CertificateTypeResult(tblCertificateType));
            }
        }

        return certificateTypeList;
    }
}

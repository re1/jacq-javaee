/*
 * Copyright 2017 fhafner.
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
package org.jacq.input.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.jacq.common.model.rest.OrganisationResult;
import org.jacq.input.ApplicationManager;
import org.jacq.input.listener.OrganisationSelectListener;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author fhafner
 */
@ManagedBean
@ViewScoped
public class OrganisationHierarchicSelectController implements Serializable {

    @Inject
    protected SessionController sessionController;

    @Inject
    protected ApplicationManager applicationManager;

    protected TreeNode root;

    protected HashMap<Long, List<OrganisationResult>> organisationHierachy;

    protected TreeNode selectedNode;

    protected OrganisationSelectListener organisationSelectListener;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        organisationHierachy = applicationManager.getOrganisationHierarchicHasMap();
        createOrganisationHierarchicNode(root, organisationHierachy.get(0L));
    }

    public TreeNode getRoot() {
        return root;
    }

    public void createOrganisationHierarchicNode(TreeNode parentTreeNode, List<OrganisationResult> organisationResultList) {
        for (OrganisationResult organisationResult : organisationResultList) {
            TreeNode node = new DefaultTreeNode(organisationResult, parentTreeNode);
            if (organisationHierachy.get(organisationResult.getOrganisationId()) != null) {
                createOrganisationHierarchicNode(node, organisationHierachy.get(organisationResult.getOrganisationId()));
            }
        }
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
        if (this.organisationSelectListener != null && selectedNode != null) {
            this.organisationSelectListener.setSelectedOrganisation((OrganisationResult) selectedNode.getData());
        }
    }

    public void show(OrganisationResult organisationResult, OrganisationSelectListener organisationSelectListener) {
        this.organisationSelectListener = organisationSelectListener;
        setExpanded(organisationResult.getOrganisationId(), root.getChildren());
    }

    protected void setExpanded(Long organisationResultId, List<TreeNode> treeNodeList) {
        for (TreeNode node : treeNodeList) {
            OrganisationResult organisation = (OrganisationResult) node.getData();
            if (organisationResultId == organisation.getOrganisationId()) {
                node.setExpanded(true);
                setExpandedParent(organisation.getParentOrganisationId(), node.getParent());
            } else {
                setExpanded(organisationResultId, node.getChildren());
            }
        }
    }

    protected void setExpandedParent(Long organisationResultId, TreeNode treeNode) {
        OrganisationResult organisation = (OrganisationResult) treeNode.getData();
        if (organisationResultId == organisation.getOrganisationId()) {
            treeNode.setExpanded(true);
            if (organisation.getParentOrganisationId() != null && !treeNode.getParent().getData().equals("Root")) {
                setExpandedParent(organisation.getParentOrganisationId(), treeNode.getParent());
            }
        }
    }
}

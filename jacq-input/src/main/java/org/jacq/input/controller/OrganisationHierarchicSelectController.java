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
    protected ApplicationManager applicationManager;

    protected TreeNode root;

    protected HashMap<Long, List<OrganisationResult>> organisationHierachy;

    protected TreeNode selectedNode;

    protected OrganisationSelectListener organisationSelectListener;

    @PostConstruct
    public void init() {
        this.root = new DefaultTreeNode("Root", null);
        // gets chache of Organisation Hierarchy
        this.organisationHierachy = this.applicationManager.getOrganisationHierachy();
        // check if cache was empty because a new Organisation was created and the Cache has to be created again
        if (this.organisationHierachy == null || this.organisationHierachy.isEmpty()) {
            this.organisationHierachy = this.applicationManager.getOrganisationHierarchicHasMap();
        }
        // Creates the Tree View of the hierachic order of Organisations
        createOrganisationHierarchicNode(this.root, this.organisationHierachy.get(0L));
    }

    /**
     * TreeNode is the Shwon tree in the hierachic selection
     *
     * @return
     */
    public TreeNode getRoot() {
        return this.root;
    }

    /**
     * Iterator to create a tree of Nodes out of the Hierachic HasMap
     *
     * @param parentTreeNode
     * @param organisationResultList
     */
    public void createOrganisationHierarchicNode(TreeNode parentTreeNode, List<OrganisationResult> organisationResultList) {
        for (OrganisationResult organisationResult : organisationResultList) {
            TreeNode node = new DefaultTreeNode(organisationResult, parentTreeNode);
            if (this.organisationHierachy.get(organisationResult.getOrganisationId()) != null) {
                createOrganisationHierarchicNode(node, this.organisationHierachy.get(organisationResult.getOrganisationId()));
            }
        }
    }

    /**
     * Current selected Node
     *
     * @return
     */
    public TreeNode getSelectedNode() {
        return this.selectedNode;
    }

    /**
     * Sets the Selected node
     *
     * @param selectedNode
     */
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
        if (this.organisationSelectListener != null && selectedNode != null) {
            this.organisationSelectListener.setSelectedOrganisation((OrganisationResult) selectedNode.getData());
        }
    }

    /**
     * Listener to set the Organisation in the right Controller and to open the
     * tree when a Organisation is already selected
     *
     * @param organisationResult
     * @param organisationSelectListener
     */
    public void show(OrganisationResult organisationResult, OrganisationSelectListener organisationSelectListener) {
        this.organisationSelectListener = organisationSelectListener;
        if (organisationResult != null && organisationResult.getOrganisationId() != null) {
            setExpanded(organisationResult.getOrganisationId(), root.getChildren());
        }
    }

    /**
     * recursion to open the selected Node
     *
     * @param organisationResultId
     * @param treeNodeList
     */
    protected void setExpanded(Long organisationResultId, List<TreeNode> treeNodeList) {
        for (TreeNode node : treeNodeList) {
            OrganisationResult organisation = (OrganisationResult) node.getData();
            // Check if sleceted Organisation is reached
            if (organisationResultId == organisation.getOrganisationId()) {
                // Selected Organisation is reached expaned
                node.setExpanded(true);
                // Go up and expaned all parent items
                setExpandedParent(node.getParent());
            } else {
                // Selected Item not reached go deeper
                setExpanded(organisationResultId, node.getChildren());
            }
        }
    }

    /**
     * recursion to expaned all parents of selected node
     *
     * @param treeNode
     */
    protected void setExpandedParent(TreeNode treeNode) {
        treeNode.setExpanded(true);
        if (treeNode.getParent() != null) {
            setExpandedParent(treeNode.getParent());
        }
    }
}

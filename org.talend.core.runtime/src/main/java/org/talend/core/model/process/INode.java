// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.model.process;

import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.talend.core.model.components.IComponent;
import org.talend.core.model.components.IODataComponent;
import org.talend.core.model.metadata.IMetadataTable;

/**
 * DOC nrousseau class global comment. Detailled comment <br/>
 * 
 * $Id: INode.java 44861 2010-07-02 12:15:36Z gldu $
 */
public interface INode extends IElement {

    public static final String RELOAD_PARAMETER_METADATA_LIST = "param.metadataList"; //$NON-NLS-1$

    public static final String RELOAD_PARAMETER_ELEMENT_PARAMETERS = "param.elementParameters"; //$NON-NLS-1$

    public static final String RELOAD_PARAMETER_EXTERNAL_BYTES_DATA = "param.externalData"; //$NON-NLS-1$

    public static final String RELOAD_PARAMETER_CONNECTORS = "param.connectors"; //$NON-NLS-1$

    public static final String RELOAD_NEW = "param.new "; //$NON-NLS-1$

    /**
     * Returns the label of the node.
     * 
     * @return label
     */
    public String getLabel();

    /**
     * Gives the unique name of the node.
     * 
     * @return unique name
     */
    public String getUniqueName();

    public String getUniqueShortName();

    /**
     * Return the start status of this node.
     * 
     * @return
     */
    public boolean isStart();

    /**
     * Return the activate status of this node.
     * 
     * @return
     */
    public boolean isActivate();

    /**
     * Return true if the node is the start of a sub process.
     * 
     * @return
     */
    public boolean isSubProcessStart();

    /**
     * To call ONLY from a subprocessStart.
     * 
     * @return
     */
    public boolean isSubProcessContainTraceBreakpoint();

    /**
     * Gives all incoming connections (only).
     * 
     * @return List of Connection
     */
    public List<? extends IConnection> getIncomingConnections();

    /**
     * Gives all outgoing connections (only).
     * 
     * @return List of Connection
     */
    public List<? extends IConnection> getOutgoingConnections();

    public String getPluginFullName();

    public boolean hasConditionalOutputs();

    public List<BlockCode> getBlocksCodeToClose();

    public boolean isMultiplyingOutputs();

    /**
     * Set performance data on this node.
     * 
     * @param perfData Performance data (string to be parsed).
     */
    void setPerformanceData(String perfData);

    /**
     * Return list of Metadatas.
     * 
     * @return
     */
    public List<IMetadataTable> getMetadataList();

    List<? extends INodeReturn> getReturns();

    public IProcess getProcess();

    public void setProcess(IProcess process);

    public IComponent getComponent();

    public void setComponent(IComponent component);

    public IExternalNode getExternalNode();

    public void metadataInputChanged(IODataComponent dataComponent, String connectionToApply);

    public void metadataOutputChanged(IODataComponent dataComponent, String connectionToApply);

    /**
     * Will return the first item of the subprocess. If "withCondition" is true, if there is links from type RunIf /
     * RunAfter / RunBefore, it will return the first element found. If "withCondition" is false, it will return the
     * first element with no active link from type Main/Ref/Iterate.<br>
     * 
     * @param withCondition
     * @return Start Node found.
     */
    public INode getSubProcessStartNode(boolean withConditions);

    /**
     * Test if the component use one data (in the property or other), this data for example a name of a context /
     * component or other.
     * 
     * @param name
     * @return
     */
    public boolean useData(String name);

    /**
     * Used for example when a component is renamed, or when a context is renamed. This function should look into each
     * property of the component to modify the value.
     * 
     * @param oldName
     * @param newName
     */
    public void renameData(String oldName, String newName);

    /**
     * Gets current node's position.
     * 
     * @return
     */
    public Point getLocation();

    public boolean isThereLinkWithHash();

    public List<? extends IConnection> getOutgoingSortedConnections();

    public List<? extends IConnection> getMainOutgoingConnections();

    public List<? extends IConnection> getOutgoingConnections(EConnectionType connectionType);

    public List<? extends IConnection> getIncomingConnections(EConnectionType connectionType);

    public List<? extends IConnection> getOutgoingConnections(String connectorName);

    /**
     * the key is the Merge node, and value is inputId. if don't link with merge, it will return null.
     */
    public Map<INode, Integer> getLinkedMergeInfo();

    public boolean isThereLinkWithMerge();

    public IMetadataTable getMetadataFromConnector(String connector);

    /**
     * This function is used only for the designer. Not implemented yet for the components use.
     * 
     * @param connector name of the connector
     * @return INodeConnector
     */
    public INodeConnector getConnectorFromName(final String connector);

    public void reloadComponent(IComponent component, Map<String, Object> parameters);

    public INode getDesignSubjobStartNode();

    public boolean isDesignSubjobStartNode();

    // if the node is generated from the virtual component, there return true
    // This function is used

    /**
     * This function is used only in the generation part to know if the node is virtual or not.
     * 
     * @return
     */
    public boolean isVirtualGenerateNode();

    // if the node should be generated as a virtual component or not.
    // true if the template part of the component is used

    /**
     * This function is only used before the generation part, to know if the component can generate virtual or not.
     * 
     * @return
     */
    public boolean isGeneratedAsVirtualComponent();

    public boolean isELTComponent();

    public boolean isUseLoopOnConditionalOutput(String outputName);

}

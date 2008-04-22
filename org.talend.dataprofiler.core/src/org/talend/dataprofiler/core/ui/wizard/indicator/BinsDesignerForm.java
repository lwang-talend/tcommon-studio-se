// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.wizard.indicator;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm;
import org.talend.dq.analysis.parameters.IParameterConstant;


/**
 * DOC zqin  class global comment. Detailled comment
 */
public class BinsDesignerForm extends AbstractIndicatorForm {

    private final String formName = "Bins Designer";
    
    private Text minValue, maxValue, numbOfBins;
    /**
     * DOC zqin BinsDesignerForm constructor comment.
     * @param parent
     * @param style
     */
    public BinsDesignerForm(Composite parent, int style) {
        super(parent, style);
        setupForm();
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#adaptFormToReadOnly()
     */
    @Override
    protected void adaptFormToReadOnly() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#addFields()
     */
    @Override
    protected void addFields() {        
        this.setLayout(new GridLayout(2, false));
 
        GridData gdText = new GridData(GridData.FILL_HORIZONTAL);
        
        Label minValueText = new Label(this, SWT.NONE);
        minValueText.setText("minimal value");
        minValue = new Text(this, SWT.BORDER);
        minValue.setLayoutData(gdText);
        
        Label maxValueText = new Label(this, SWT.NONE);
        maxValueText.setText("maximal value");
        maxValue = new Text(this, SWT.BORDER);
        maxValue.setLayoutData(gdText);
        
        Label numOfBinsText = new Label(this, SWT.NONE);
        numOfBinsText.setText("number of bins");
        numbOfBins = new Text(this, SWT.BORDER);
    }


    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#addFieldsListeners()
     */
    @Override
    protected void addFieldsListeners() {
        
        minValue.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
               
                parameters.put(IParameterConstant.INDICATOR_MIN_VALUE, minValue.getText());
            }
            
        });
        
        maxValue.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
               
                parameters.put(IParameterConstant.INDICATOR_MAX_VALUE, maxValue.getText());
            }
            
        });
        
        numbOfBins.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
               
                parameters.put(IParameterConstant.INDICATOR_NUM_OF_BIN, numbOfBins.getText());
            }
            
        });
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#addUtilsButtonListeners()
     */
    @Override
    protected void addUtilsButtonListeners() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#checkFieldsValue()
     */
    @Override
    protected boolean checkFieldsValue() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractForm#initialize()
     */
    @Override
    protected void initialize() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.talend.dataprofiler.core.ui.utils.AbstractIndicatorForm#getFormName()
     */
    @Override
    public String getFormName() {
        
        return this.formName;
    }

}

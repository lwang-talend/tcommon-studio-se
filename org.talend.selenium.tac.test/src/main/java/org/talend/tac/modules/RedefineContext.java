/**
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements. See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership. The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.talend.tac.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.talend.tac.base.WebDriverBase;

/**
 * 
 */
public class RedefineContext extends WebDriverBase {
	   
       public RedefineContext(WebDriver driver) {
           super.setDriver(driver);
           this.driver = driver;
       }
                 
       public void selectDropDownList(String name, String id, String value) {
   		if(this.isElementPresent(By.xpath("//span[text()='Select" +
   		    " Feature from Talend repository']"), 2)) {
   			this.waitforElementDisplayed(By.xpath("//label[text()='"+name+"']//following-sibling::div//div[contains(@class,'x-form-trigger x-form-trigger-arrow')]"), 20);         
   	        getElementByXpath("//label[text()='"+name+"']//following-sibling::div//div[contains(@class,'x-form-trigger x-form-trigger-arrow')]").click();
   	        this.waitforElementDisplayed(By.xpath("//div[text()='"+value+"'and @role='listitem']"), 20);
   	        getElementByXpath("//div[text()='"+value+"'and @role='listitem']").click();	        
   		}
   		else {
   	  	    getElementByXpath("//label[text()='"+name+"']//following-sibling::div//input[@id='"+id+"']//following-sibling::div[contains(@class,'x-form-trigger x-form-trigger-arrow')]").click();
   	        getElementByXpath("//div[text()='"+value+"'and @role='listitem']").click();
   		}
   	}
      
       public void mouseDown(String xpathExpression) {
    	   Locatable hoverItem = (Locatable) driver.findElement(By.xpath(xpathExpression));
    	   Mouse mouse = ((HasInputDevices) driver).getMouse();
    	   mouse.mouseDown(hoverItem.getCoordinates());
    	   try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
       }
       
       public void intoESBConductorPage() {
           this.getElementById("!!!menu.esbconductor.element!!!").click();
           this.waitforTextDisplayed("ESB CONDUCTOR", WAIT_TIME_MAX);
       }
       
       public void addESBConductor(String label, String des, String repository,
                                   String group, String artifact, String version, String name, String type, 
                                   String context, String server) {
    	   this.getElementById("idESBConductorTaskGridAddButton").click();
           this.isElementPresent(By.xpath("//img[@class='gwt-Image" +
   				" x-component ']"), WAIT_TIME_MAX);
           this.typeTextById("idESBConductorTasklabelInput", label);
           this.typeTextById("idESBConductorTaskdesInput", des);
           this.getElementById("idESBConductorTaskSelectButton").click();
           this.isElementPresent(By.xpath("//span[text()='Select" +
   				" Feature from Talend repository']"), WAIT_TIME_MAX);
//           this.waitforElementDisplayed(By.xpath("//label[text()='Repository:']//following-sibling::div//div[contains(@class,'x-form-trigger x-form-trigger-arrow')]"), WAIT_TIME_MAX);         
//           getElementByXpath("//label[text()='Repository:']//following-sibling::div//div[contains(@class,'x-form-trigger x-form-trigger-arrow')]").click();
//           this.waitforElementDisplayed(By.xpath("//div[text()='"+repository+"'and @role='listitem']"), WAIT_TIME_MAX);
//           getElementByXpath("//div[text()='"+repository+"'and @role='listitem']").click();
//   		   this.getElementByXpath("//span[text()='" + group + "']").click();
//   		   this.getElementByXpath("//div[text()='" + artifact + "']")
//   			   .click();
//   		   this.getElementByXpath("//div[text()='" + version + "']").click();
           this.selectDropDownList("Repository:", "", repository);
           this.selectDropDownList("Group:", "", group);
           this.selectDropDownList("Artifact:", "", artifact);
           this.selectDropDownList("Version:", "", version);
   		   this.getElementByXpath("//button[text()='OK']").click();
           this.selectDropDownList("Name:","idTaskProjectListBox", name);
           this.selectDropDownList("Type:","idJobConductorExecutionServerListBox", type);
           this.selectDropDownList("Context:","idESBConductorTaskContextListBox", context);
           logger.info("select context");
           this.selectDropDownList("Server:","idJobConductorExecutionServerListBox", server);
           logger.info("select server");
           this.getElementById("idFormSaveButton").click();           
       }
       
       public void defineContext(String label,String variableName,String variableValue) {
    	   Robot bot;
		try {
		   bot = new Robot();
		   this.waitforElementDisplayed(By.xpath("//div[text()='" + label + "']"), WAIT_TIME_MIN);
		   this.mouseDown("//div[text()='" + label + "']");
		   this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorPropertyAddButton']"), WAIT_TIME_MIN);
		   this.getElementById("idESBConductorPropertyAddButton").click();
    	   getElementByXpath(other.getString("ESBConductor.ConfigProperties.Name")).click();
    	   this.typeString(By.xpath("//span[text()='Name']//ancestor::div[@class='x-grid3-header']//following-sibling::div//input"), variableName, WAIT_TIME_MIN);
    	   Thread.sleep(3000);
    	   getElementByXpath(other.getString("ESBConductor.ConfigProperties.Value")).click();
    	   this.typeString(By.xpath("//span[text()='Value']//ancestor::div[@class='x-grid3-header']//following-sibling::div//input"), variableValue, WAIT_TIME_MIN);
    	   bot.keyPress(KeyEvent.VK_ENTER);
    	   bot.keyRelease(KeyEvent.VK_ENTER);    
		} catch (AWTException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		   this.mouseDown(other.getString("ESBConductor.ConfigProperties.Value"));
		   this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorPropertySaveButton']"), WAIT_TIME_MIN);
		   this.getElementById("idESBConductorPropertySaveButton").click();
		   this.clickElementById("idESBConductorPropertySaveButton");
		   Assert.assertTrue(this.isElementPresent(By.xpath("//div[text()='"+label+"']"), WAIT_TIME_MIN));
    	   
       }
       
       public void deployEsbConductor(String label,String name) {
           String status = "Deployed and started";
           String promptInfo = "Feature '" + name + "' deployed.";
           this.waitforElementDisplayed(By.xpath("//div[text()='" + label + "']"), WAIT_TIME_MIN);
           this.mouseDown("//div[text()='" + label + "']");
           this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorTaskGridDeployButton']"), WAIT_TIME_MIN);
		   this.getElementById("idESBConductorTaskGridDeployButton").click();        
		   logger.info("--------PromptInfo:"+promptInfo);
	       this.waitforTextDisplayed(promptInfo, WAIT_TIME_MIN);	
		   this.clickElementById("idESBConductorTaskGridRefreshButton");
           Assert.assertTrue(this.isElementPresent(By.xpath("//div[text()='" + label + "']"
                   + "//ancestor::table[@class='x-grid3-row-table']//span[text()='" + status
                   + "']"), WAIT_TIME_MIN));
       }
       
       public void deleteContextPropertiesOk(String label) {
    	   this.waitforElementDisplayed(By.xpath("//div[text()='" + label + "']"), WAIT_TIME_MIN);
    	   this.mouseDown("//div[text()='" + label + "']");
    	   this.waitforElementDisplayed(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-property']"),WAIT_TIME_MIN);
    	   this.mouseDown("//div[@class='x-grid3-cell-inner x-grid3-col-property']");
    	   this.getElementByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-property']").click();
    	   this.getElementByXpath(other.getString("ESBConductor.ConfigProperties.Name")).click();
           this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorPropertyDeleteButton']"), WAIT_TIME_MIN);
    	   this.getElementById("idESBConductorPropertyDeleteButton").click();
           this.acceptAlert(); 
       }
       
       public void undeployEsbConductorOk(String label) {
    	   String status="Undeployed";
    	   this.mouseDown("//div[text()='" + label + "']");
    	   this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorTaskGridUndeployButton']"), WAIT_TIME_MIN);
    	   this.getElementById("idESBConductorTaskGridUndeployButton").click();    	   
    	   this.acceptAlert();
    	   this.clickElementById(other.getString("ESBConductor.RefreshButtonId"));
    	   Assert.assertTrue(this.isElementPresent(By.xpath("//div[text()='" + label + "']"
                   + "//ancestor::table[@class='x-grid3-row-table']//span[text()='" + status
                   + "']"), WAIT_TIME_MIN));
       }
       
       public void deleteUndeployedConductorOk(String label,String name) {
    	   this.mouseDown("//div[text()='" + label + "']");
    	   this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorTaskGridDeleteButton']"), WAIT_TIME_MIN);
    	   this.getElementById("idESBConductorTaskGridDeleteButton").click();        	   
    	   this.acceptAlert(); 	   
    	   logger.info("-------label:"+label);

       }
       
       public void deleteContextPropertyCancel(String label) {
    	   logger.info("^^^^^^^^^^^^^Label:"+label);
    	   this.waitforElementDisplayed(By.xpath("//div[text()='" + label + "']"), WAIT_TIME_MIN);
    	   this.mouseDown("//div[text()='" + label + "']");
    	   this.mouseDown(other.getString("ESBConductor.ConfigProperties.Value"));
    	   this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorPropertyDeleteButton']"), WAIT_TIME_MIN);
    	//   this.getElementById("idESBConductorPropertyDeleteButton").click();
    	   this.clickElementById("idESBConductorPropertyDeleteButton");
           this.dismissAlert(); 
       }
       
       public void resetContextPara(String label) {
    	   this.waitforElementDisplayed(By.xpath("//div[text()='" + label + "']"), WAIT_TIME_MIN);
    	   this.mouseDown("//div[text()='" + label + "']");
    	   this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorPropertyResetButton']"), WAIT_TIME_MIN);
    	   this.clickElementById("idESBConductorPropertyResetButton");
    	   this.acceptAlert();
    	   Assert.assertFalse(this.waitforElementDisplayed(By.xpath(other.getString("ESBConductor.ConfigProperties.Name")), 20));
       }
       
       public void controlDisplay(String label) {
//    	   this.waitforElementDisplayed(By.xpath("//div[text()='" + label + "']"), WAIT_TIME_MIN);
//    	   this.mouseDown("//div[text()='" + label + "']");
    	   this.focusElement();
    	   this.checkColumn("Name");
    	   this.checkColumnForValue("Value");
  //  	   Assert.assertFalse(this.isElementPresent(By.xpath("//div[@class='x-panel-body x-panel-body-noheader']//div[@class=' x-grid3-hd-inner x-grid3-hd-active x-component']//span[text()='Active']"), 20));
       }
       
       public void focusElement() {
    		
    		WebElement tag = driver.findElement(By.xpath("//div[@class=' x-grid3-hd-inner x-grid3-hd-property x-component']//ancestor::span[text()='Name']"));
    		this.moveToElement(tag);
    		WebElement jjj = driver.findElement(By.xpath("//div[contains(@class,' x-grid3-hd-inner x-grid3-hd-property x-component')]//a"));
    		jjj.click();
    		this.waitforElementDisplayed(By.xpath("//a[text()='Columns']"), WAIT_TIME_MIN);
    		WebElement columns = driver.findElement(By.xpath("//a[text()='Columns']"));
    		this.moveToElement(columns);
    	  }
       
       public void checkColumn(String columnName){
   		boolean present = this.isElementPresent(By.xpath("//div[@class=' x-grid3-hd-inner x-grid3-hd-property x-component']//ancestor::span[text()='Name']"),30);
   		if(!present){
   			logger.info("clumnName:"+columnName);
   			this.waitforElementDisplayed(By.xpath("//a[text()='"+columnName+"']"), 20);
   			this.getElementByXpath("//a[text()='"+columnName+"']").click();
   			this.waitforElementDisplayed(By.xpath("//div[@class=' x-grid3-hd-inner x-grid3-hd-property x-component']//ancestor::span[text()='Name']"), 20);
   			this.getElementByXpath("//a[text()='"+columnName+"']").click();
   		}
   		else{
   			this.waitforElementDisplayed(By.xpath("//a[contains(text(),'"+columnName+"')]"), 20);
   			this.getElementByXpath("//a[contains(text(),'"+columnName+"')]").click();
   			Assert.assertFalse(this.isElementPresent(By.xpath("//div[@class=' x-grid3-hd-inner x-grid3-hd-property x-component']//ancestor::span[text()='Name']"), 5));
   			this.getElementByXpath("//a[contains(text(),'"+columnName+"')]").click();
   		}   		
   	   }
       
       public void checkColumnForValue(String columnName){
      		boolean present = this.isElementPresent(By.xpath("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-esbvalue x-component')]//span[text()='Value']"),30);
      		if(!present){
      			logger.info("clumnName:"+columnName);
      			this.waitforElementDisplayed(By.xpath("//a[text()='"+columnName+"']"), 20);
      			this.getElementByXpath("//a[text()='"+columnName+"']").click();
      			this.waitforElementDisplayed(By.xpath("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-esbvalue x-component')]//span[text()='Value']"), 20);
      		}
      		else{
      			this.waitforElementDisplayed(By.xpath("//a[contains(text(),'"+columnName+"')]"), 20);
      			this.getElementByXpath("//a[contains(text(),'"+columnName+"')]").click();
      			Assert.assertFalse(this.isElementPresent(By.xpath("//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-esbvalue x-component')]//span[text()='Value']"), 5));
      			this.getElementByXpath("//a[contains(text(),'"+columnName+"')]").click();
           }   		
       }
       
   	public void SortAscendingSortDescendingOfContextPara(String value, String value1) {
		WebElement element = driver.findElement(By.xpath("//div[@class=' x-grid3-hd-inner x-grid3-hd-property x-component']//ancestor::span[text()='Name']"));
		this.moveToElement(element);
		WebElement drop = driver.findElement(By.xpath("//div[contains(@class,' x-grid3-hd-inner x-grid3-hd-property x-component')]//a"));
		drop.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.clickElementByXpath("//a[text()='Sort Descending']");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		logger.info("*********descend value:"+value);
		Assert.assertEquals(this.getElementByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-property']").getText(), value);       
       
		WebElement elementagain = driver.findElement(By.xpath("//div[contains(@class,' x-grid3-hd-inner x-grid3-hd-property x-component')]//ancestor::span[text()='Name']"));
		this.moveToElement(elementagain);
		WebElement dropagain = driver.findElement(By.xpath("//div[contains(@class,' x-grid3-hd-inner x-grid3-hd-property x-component')]//a"));
		dropagain.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        this.clickElementByXpath("//a[text()='Sort Ascending']");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(this.getElementByXpath("//div[@class='x-grid3-cell-inner x-grid3-col-property']").getText(), value1);
		
	}
   	
    public void defineContextForSort(String label,String variableName,String variableValue) {
 	   Robot bot;
		try {
		   bot = new Robot();
		   this.waitforElementDisplayed(By.xpath("//div[text()='" + label + "']"), WAIT_TIME_MIN);
		   this.mouseDown("//div[text()='" + label + "']");
		   this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorPropertyAddButton']"), WAIT_TIME_MIN);
		   this.getElementById("idESBConductorPropertyAddButton").click();
		   waitforElementDisplayed(By.xpath("//div[@class='x-grid3-check-col x-grid3-check-col x-grid3-cc-active']"+
 			   "//ancestor::td[@class='x-grid3-col x-grid3-cell x-grid3-td-active x-grid-cell-first x-grid3-check-col-td']//following-sibling::td[1]//img"), WAIT_TIME_MIN);
 	   getElementByXpath("//div[@class='x-grid3-check-col x-grid3-check-col x-grid3-cc-active']"+
 			   "//ancestor::td[@class='x-grid3-col x-grid3-cell x-grid3-td-active x-grid-cell-first x-grid3-check-col-td']//following-sibling::td[1]//img").click();
 	   this.typeString(By.xpath("//div[@class='x-grid3-check-col x-grid3-check-col x-grid3-cc-active']//ancestor::td[@class='x-grid3-col x-grid3-cell x-grid3-td-active x-grid-cell-first x-grid3-check-col-td']//following-sibling::td[2]//ancestor::div[@class='x-grid3-body']//following-sibling::div//input[contains(@class,'focus')]"), variableName, WAIT_TIME_MIN);
 	   getElementByXpath("//div[@class='x-grid3-check-col x-grid3-check-col x-grid3-cc-active']"+
 			   "//ancestor::td[@class='x-grid3-col x-grid3-cell x-grid3-td-active x-grid-cell-first x-grid3-check-col-td']//following-sibling::td[2]//img").click();
 	   this.typeString(By.xpath("//div[@class='x-grid3-check-col x-grid3-check-col x-grid3-cc-active']//ancestor::td[@class='x-grid3-col x-grid3-cell x-grid3-td-active x-grid-cell-first x-grid3-check-col-td']//following-sibling::td[2]//ancestor::div[@class='x-grid3-body']//following-sibling::div//input"), variableValue, WAIT_TIME_MIN);
 	   bot.keyPress(KeyEvent.VK_ENTER);
 	   bot.keyRelease(KeyEvent.VK_ENTER);    
		} catch (AWTException e) {			
			e.printStackTrace();	
		   this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorPropertySaveButton']"), WAIT_TIME_MIN);
		   this.getElementById("idESBConductorPropertySaveButton").click();
		   Assert.assertTrue(this.isElementPresent(By.xpath("//div[text()='"+label+"']"), WAIT_TIME_MIN));
 	   
    }
    }
    
    public void refreshConfig(String label) {
    	this.waitforElementDisplayed(By.xpath("//div[text()='" + label + "']"), WAIT_TIME_MIN);
    	this.mouseDown("//div[text()='" + label + "']");
    	this.waitforElementDisplayed(By.xpath("//button[@id='idESBConductorPropertyRefreshButton']"), WAIT_TIME_MIN);
    	this.clickElementById("idESBConductorPropertyRefreshButton");
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        Assert.assertTrue(this.isTextPresent("Refresh Done"));  
    }
}
   
package com.talend.tac.cases.user;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@Test(groups={"one"})
public class TestModifyLastAdministrationUserRole extends Login {
     
	@Test
	@Parameters({"userName","userName"})
	public void testModifyLastAdministrationUserRole(String userName,String userName1) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
//		selenium.click("idMenuUserElement");
//		
//		selenium.setSpeed(Base.MIN_SPEED);
		
		Assert.assertTrue(selenium.isTextPresent(userName));
        selenium.mouseDown("//div[text()='"+userName1+"']");
		selenium.click("idRoleButton");
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.roles.title")));
		selenium.setSpeed("3000");
		selenium.click("//div[text()='"+rb.getString("menu.role.designer")+"']");		
		selenium.click("idValidateButton");
		selenium.click("idFormSaveButton");
		selenium.setSpeed("3000");
		
		Assert.assertTrue(selenium.isTextPresent(rb.getString("user.error.roleModification.lastAdministrator")));
		
	    selenium.click("//button[text()='"+other.getString("modify.lastUserAdministrator.role.fail")+"']");
	}
}

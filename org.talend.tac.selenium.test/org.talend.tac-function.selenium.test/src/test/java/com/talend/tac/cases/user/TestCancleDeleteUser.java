package com.talend.tac.cases.user;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.talend.tac.cases.Login;
@Test(groups={"two"},dependsOnGroups={"one"},dependsOnMethods={"testAddNewUserRoleAdministrator"})
public class TestCancleDeleteUser extends Login {
    
	@Test
	@Parameters({"userName","DeleteUser"})
	public void testCancleDeleteUser(String userName,String DeleteUser) throws Exception {
		this.clickWaitForElementPresent("idMenuUserElement");
//		selenium.click("idMenuUserElement");
//		
//		selenium.setSpeed(Base.MIN_SPEED);
		
		Assert.assertTrue(selenium.isTextPresent(userName));
		selenium.mouseDown(DeleteUser);//Select an existing user
		selenium.chooseCancelOnNextConfirmation();
		selenium.click("idSubModuleDeleteButton");
		Assert.assertTrue(selenium.getConfirmation().matches("^"+other.getString("delete.User.confirmation")+" [\\s\\S]$"));
		selenium.setSpeed("3000");
	}
	
}

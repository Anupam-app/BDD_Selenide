package cucumber.steps;

import dataobjects.Role;
import dataobjects.RoleAction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.RolePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RolePageStepsDefinition {
	
    private final RolePage rolePage;
    private final Role role;

    public RolePageStepsDefinition(RolePage rolePage, Role role) {
        this.rolePage = rolePage;
        this.role = role;
    }
    
	@Given("I trigger Roles mode")
	public void iTriggerRolesMode() {
		rolePage.gotoRolesTab();
	}

	@When("I create random role")
	public void iCreateRandomRole() {
		this.role.setRoleName(RandomStringUtils.randomAlphabetic(10));
		this.role.setRoleAction(RoleAction.ADDED);
		rolePage.createNewrole(this.role.getRoleName());
	}

	@When("I assign permission {string}")
	public void iAssignPermission(String role) {
		this.role.getPermissions().add(role);
		rolePage.clickOnPermission(role);
	}

	@When("I click on save button")
	public void iSaveRole() {
    	rolePage.saveRole(this.role.getRoleAction());
	}

	@Then("I verify role details")
	public void iVerifyRoleDetails() {
		Assert.assertEquals(rolePage.getRoleNameFromTextbox(), role.getRoleName());
		List<String> permissions= rolePage.getPermissionList();
		Collections.sort(permissions);
		ArrayList<String> expectedPermissions=new ArrayList<>(role.getPermissions());
		Collections.sort(expectedPermissions);
		Assert.assertEquals(permissions,expectedPermissions);
	}

	@When("I search the role")
	public void iSearchTheRole() {
		rolePage.searchRole(this.role.getRoleName());
	}

	@When("I edit the role")
	public void iModifyTheRole() {
		iModifyRole(this.role.getRoleName());
	}

	@When("I edit role {string}")
	public void iModifyRole(String role) {
		this.role.setRoleAction(RoleAction.UPDATED);
    	this.role.setRoleName(role);
		rolePage.modifyRole(role);
		rolePage.getPermissionList().forEach(p->this.role.getPermissions().add(p));
	}

	@Then("I verify user details")
	public void iVerifyDetails() {
		Assert.assertEquals(rolePage.getRoleName(), role.getRoleName());
	}

	@When("I remove permission {string}")
	public void iRemovePermissions(String role) {
		this.role.getPermissions().remove(role);
    	rolePage.clickOnPermission(role);
	}

	@When("I assign {string} to user")
	public void iAssignToUser(String role) {
		rolePage.assignRole(role);
	}

	@Given("the role {string} exists")
	public void theRoleExists(String role) {
		rolePage.searchRole(role);
		rolePage.roleExists(role);
    }

	@Then("I do not see Roles mode")
	public void iDoNotSeeRolesMode() {
		rolePage.NoRolesTab();
	}
}

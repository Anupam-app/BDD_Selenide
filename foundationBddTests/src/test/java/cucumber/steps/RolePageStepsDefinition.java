package cucumber.steps;

import dataobjects.Login;
import dataobjects.Report;
import dataobjects.Role;
import dataobjects.RoleAction;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import pageobjects.pages.ReportsPage;
import pageobjects.pages.RolePage;

import static com.codeborne.selenide.Selenide.switchTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RolePageStepsDefinition {

	private final RolePage rolePage;
	private final Role role;
	private final Report report;
	private final ReportsPage reportPage;
	private final Login login;

	public RolePageStepsDefinition(RolePage rolePage, Role role, Report report,ReportsPage reportPage, Login login) {
		this.rolePage = rolePage;
		this.role = role;
		this.report = report;
		this.reportPage = reportPage;
		this.login = login;
		this.role.getPermissions().clear();
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

	@When("I select role sort by {string} in {string}")
	public void iSelectSortBy(String columnName, String sortMode) {
		rolePage.sortList(columnName,Boolean.parseBoolean(sortMode));
	}

	@Then("{string} from role should be displayed in sorted order {string}")
	public void roleDetailsShouldBeDisplayedInSortedOrder(String columnName,String sortMode) {
		rolePage.checkSortedElement(columnName,Boolean.parseBoolean(sortMode));
	}

	@Given("I search {string} role")
	public void iSearchRole(String role) {
		this.role.setRoleName(role);
		rolePage.searchRole(this.role.getRoleName());
	}

	@When("I assign permission {string}")
	public void iAssignPermission(String role) {
		this.role.getPermissions().add(role);
		rolePage.clickOnPermission(role);
	}

	@When("I see notification")
	public void iSeeNotification() {
		rolePage.notification(this.role.getRoleAction());
	}

	@Then("I verify role details")
	public void iVerifyRoleDetails() {
		Assert.assertEquals(rolePage.getRoleNameFromTextbox(), role.getRoleName());
		List<String> permissions= rolePage.getPermissionList();
		Collections.sort(permissions);
		ArrayList<String> expectedPermissions=new ArrayList<>(role.getPermissions());
		Collections.sort(expectedPermissions);
		Assert.assertEquals(expectedPermissions,permissions);
		rolePage.cancelButton();
	}
	
	@Then("I see the role added in report")
	public void iVerifyThatRoleIsAdded() throws Exception {
		this.report.checkAddedRole(reportPage.getPdfUrl(), this.role.getRoleName(), this.login.getLogin(), this.role.getPermissions());
		switchTo().parentFrame();
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
	@Then( "I see the error message of role {string}")
	public void iSeetheErrorMessage(String message) {
		rolePage.checkMessage(message);
	}
	@Then("I create role {string}")
	public void iCreateRole(String name) {
		this.role.setRoleName(name);
		this.role.setRoleAction(RoleAction.ERROR);
		rolePage.createNewrole(this.role.getRoleName());	

	}
	@When("I click on save button")
	public void iSaveRole() {
		rolePage.saveButton();
	}

}

package cucumber.steps;

import dataobjects.Login;
import dataobjects.Report;
import dataobjects.Role;
import dataobjects.RoleAction;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import pageobjects.pages.ReportsPage;
import pageobjects.pages.RolePage;

public class RolePageStepsDefinition {

    private final RolePage rolePage;
    private final Role role;
    private final Report report;
    private final ReportsPage reportPage;
    private final Login login;

    public RolePageStepsDefinition(RolePage rolePage, Role role, Report report, ReportsPage reportPage, Login login) {
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
        rolePage.sortList(columnName, Boolean.parseBoolean(sortMode));
    }

    @Then("{string} from role should be displayed in sorted order {string}")
    public void roleDetailsShouldBeDisplayedInSortedOrder(String columnName, String sortMode) {
        rolePage.checkSortedElement(columnName, Boolean.parseBoolean(sortMode));
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
        List<String> permissions = rolePage.getPermissionList();
        Collections.sort(permissions);
        ArrayList<String> expectedPermissions = new ArrayList<>(role.getPermissions());
        Collections.sort(expectedPermissions);
        Assert.assertEquals(expectedPermissions, permissions);
        rolePage.cancelButton();
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
        rolePage.getPermissionList().forEach(p -> this.role.getPermissions().add(p));
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

    @Given("tooltip shows the role name as {string}")
    public void tooltipShowsRoleName(String role) {
        rolePage.verifyRoleNameToolTip(role);
    }

    @Then("I do not see Roles mode")
    public void iDoNotSeeRolesMode() {
        rolePage.NoRolesTab();
    }

    @Then("I see the error message of role {string}")
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

    @And("^I verify default roles$")
    public void iNavigateroleIconVerifyDefaultRoles(DataTable datatable) {
        List<String> roles = datatable.asList();
        for (String names : roles) {
            rolePage.iVerifyDefaultRoles(names);
        }
    }


    @And("I verify {string} list of {string}")
    public void iVerifyPrivilegesListOfRoles(String userRole, String roles) {
        if (userRole.equalsIgnoreCase("privilege")) {
            rolePage.privilegesOfroles(roles);
        } else if (userRole.equalsIgnoreCase("proceessmanager")) {
            rolePage.processManager(roles);
        }
    }

    @And("^I should see view icon of perticular roles$")
    public void iSeeViewIconOfperticularRoles(DataTable datatable) {
        List<String> icon = datatable.asList();
        for (String name : icon) {
            rolePage.validateViewIcon(name);
        }
    }

    @And("I click on edit icon corresponding custom role")
    public void iClickOnEditIconCorrespondingCustomRole() {
        rolePage.adminIcon();
    }

    @And("^I unchecked role permissions$")
    public void iRemovePermissions(DataTable datatable) {
        List<String> uncheck = datatable.asList();
        for (String name : uncheck) {
            rolePage.unselectrolepremission(name);
        }
    }

    @And("I create a random rolename")
    public void iEnterNewCustomRoleName() {
        this.role.setRoleName(RandomStringUtils.randomAlphabetic(10));
        this.role.setOldRoleName(rolePage.getOldRoleName());
        rolePage.iCreateRondomName(this.role.getRoleName());
    }

    @And("I should see new custom role created")
    public void iShouldSeeNewCustomRole() {
        rolePage.isearchName(this.role.getRoleName());
        rolePage.verifyRoleName(this.role.getRoleName());
    }

    @And("I verify a new user {string} by selecting custom role")
    public void iCreatedNewUserRole(String rolename) {
        rolePage.iCreateNewUser(rolename);
    }

    @And("I should see role name {string} in role culumn")
    public void iSeeCustomRoleInRoleColumn(String role) {
        rolePage.rolename(role);
    }

}

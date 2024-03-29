package dataobjects;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {

    String roleName;
    String oldRoleName;
    Set<String> permissions = new HashSet<>();
    Set<String> oldPermissions = new HashSet<>();
    RoleAction roleAction;
    String updatedRoleName;
}

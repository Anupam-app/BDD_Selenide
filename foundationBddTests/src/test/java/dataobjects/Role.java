package dataobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class Role {

    @Setter
    @Getter
    String roleName;

    @Setter
    @Getter
    Set<String> permissions = new HashSet<>();

    @Setter
    @Getter
    RoleAction roleAction;
}

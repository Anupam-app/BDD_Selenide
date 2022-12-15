package dataobjects;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

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

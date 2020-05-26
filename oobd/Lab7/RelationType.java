import javax.management.relation.RoleInfo;
import javax.management.relation.RoleInfoNotFoundException;
import java.util.List;

public enum RelationType implements javax.management.relation.RelationType {
    OneToOne,
    ManyToOne,
    OneToMany,
    ManyToMany,
    ;

    @Override
    public String getRelationTypeName() {
        return null;
    }

    @Override
    public List<RoleInfo> getRoleInfos() {
        return null;
    }

    @Override
    public RoleInfo getRoleInfo(String roleInfoName) throws IllegalArgumentException, RoleInfoNotFoundException {
        return null;
    }
}
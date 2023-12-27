package ngo.drc.core.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@Getter
@Setter
@ToString(exclude = "roles")
@NoArgsConstructor
@AllArgsConstructor

public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false, updatable = false)
    private String name;

    private Boolean isDeleted;

    @ManyToMany(mappedBy = "permissions")
    @Setter(AccessLevel.PRIVATE)
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
        role.getPermissions().add(this);
    }

    public void addRoles(List<Role> roles) {
        for (Role role : roles) {
            addRole(role);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String getAuthority() {
        return name;
    }
}

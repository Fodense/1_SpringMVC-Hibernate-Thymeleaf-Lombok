package by.brel.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Override
    public String getAuthority() {
        return getRole();
    }

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }
}

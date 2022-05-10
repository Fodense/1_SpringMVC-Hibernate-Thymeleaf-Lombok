package by.brel.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @Column(name = "username")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private short enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "authorities",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

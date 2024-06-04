package pe.edu.upao.InversionesJI.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table (name= "Inmobiliaria")
public class Inmobiliaria implements UserDetails {

    @Id
    @GeneratedValue
    private Long idInmobiliaria;
    private String nombreInmobiliaria;
    private String direccion;
    private String username;
    private String password;
    private long ruc;
    private int telefonoContacto;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
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

    @OneToMany(mappedBy = "inmobiliaria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Articulo> articulos = new ArrayList<>();
}

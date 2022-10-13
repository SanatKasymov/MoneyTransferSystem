package com.example.moneytransfersystem.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@Entity
@Table(name = "cashboxes")
public class Cashbox implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CASHBOXES_SEQ")
    @SequenceGenerator(name = "CASHBOXES_SEQ", sequenceName = "CASHBOXES_SEQ", allocationSize = 1)
    Long id;

    @Column
    String name;

    @Column
    String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getUsername() {
        return name;
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

    public Cashbox(String name, String password) {
        this.name = name;
        this.password = password;
    }

}

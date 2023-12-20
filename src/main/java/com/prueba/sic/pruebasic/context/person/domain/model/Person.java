package com.prueba.sic.pruebasic.context.person.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person implements UserDetails {

    private Long identificationNumber;
    private String identificationType;
    private String name;
    private String lastname;
    private Long phone;
    private String address;
    private String email;
    private String password;
    private Timestamp creationDate;
    private Timestamp updateDate;

    public boolean isValid(Person person) {
        if(person.getIdentificationType() == null) return false;
        if(person.getName() == null) return false;
        if(person.getLastname() == null) return false;
        if(person.getPhone() == null) return false;
        if(person.getAddress() == null) return false;
        if(person.getEmail() == null) return false;
        return !person.getIdentificationType().isEmpty() &&
                !person.getName().isEmpty() &&
                !person.getLastname().isEmpty() &&
                !person.getPhone().toString().isEmpty() &&
                !person.getAddress().isEmpty() &&
                !person.getEmail().isEmpty();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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

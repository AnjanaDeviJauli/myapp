package org.anjanadevijaulikrishnamoorthy.myapp.security;

import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;

import org.anjanadevijaulikrishnamoorthy.myapp.models.AuthGroup;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserPrincipal implements UserDetails {
    private Teachers teachers;
    private List<AuthGroup> authGroup;

    public AppUserPrincipal( Teachers teachers,List<AuthGroup> authGroup) {

        this.teachers = teachers;
        this.authGroup=authGroup;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authGroup.stream().map(auth -> new SimpleGrantedAuthority(auth.getRole())).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return teachers.getPassword();
    }

    @Override
    public String getUsername() {
        return teachers.getEmail();
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

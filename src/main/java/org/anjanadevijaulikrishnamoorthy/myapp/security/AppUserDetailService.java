package org.anjanadevijaulikrishnamoorthy.myapp.security;

import org.anjanadevijaulikrishnamoorthy.myapp.dao.AuthGroupRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {
    TeacherRepoI teacherRepoI;
    AuthGroupRepoI authGroupRepoI;

    @Autowired
    public AppUserDetailService(TeacherRepoI teacherRepoI, AuthGroupRepoI authGroupRepoI) {
        this.teacherRepoI = teacherRepoI;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new AppUserPrincipal(
                teacherRepoI.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email Not Found"))
                , authGroupRepoI.findByEmail(username));
    }
}
package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.AuthGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class AuthGroupRepoITest {
   AuthGroupRepoI authGroupRepoI;

   @Autowired
    public AuthGroupRepoITest(AuthGroupRepoI authGroupRepoI) {
        this.authGroupRepoI = authGroupRepoI;
    }
    AuthGroup authGroup2 = new AuthGroup("ashley@gmail.com", "ROLE_USER");

    @Test
    void findByEmail() {
        assertThat(authGroupRepoI.findByEmail("ashley@gmail.com").get(0)).isEqualTo(authGroup2);
    }
}
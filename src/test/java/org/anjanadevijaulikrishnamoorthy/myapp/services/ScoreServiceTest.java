package org.anjanadevijaulikrishnamoorthy.myapp.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ScoreServiceTest {

    ScoreService scoreService;

    @Autowired
    public ScoreServiceTest(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @ParameterizedTest
    @ValueSource(doubles = {99,97,96,95})
    void findGrade(double number) {
        assertThat(scoreService.findGrade(number)).isEqualTo("A");
    }
}
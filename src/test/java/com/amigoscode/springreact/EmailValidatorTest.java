package com.amigoscode.springreact;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmailValidatorTest {

    private final EmailValidator underTest = new EmailValidator();

    @Test
    void itShouldValidateCorrectEmail() {
        assertThat(underTest.test("hello@gmail.com")).isTrue();
    }

    @Test
    void itShouldValidateAnIncorrectCorrectEmail() {
        assertThat(underTest.test("hellogmail.com")).isFalse();
    }

    @Test
    void itShouldValidateAnIncorrectCorrectEmailWithoutDotAtTheEnd() {
        assertThat(underTest.test("hello@gmail")).isFalse();
    }

}
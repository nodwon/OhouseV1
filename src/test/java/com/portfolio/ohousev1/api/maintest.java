package com.portfolio.ohousev1.api;


import com.portfolio.ohousev1.config.SecurityConfig;
import com.portfolio.ohousev1.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

@Import(SecurityConfig.class)
@WebMvcTest(HomeController.class)
public class maintest {

    private final MockMvc mvc;

    public maintest(MockMvc mvc) {
        this.mvc = mvc;
    }
    @Test
    public void passwordCompare() throws Exception {
        //given
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //when

        //then
    }
}

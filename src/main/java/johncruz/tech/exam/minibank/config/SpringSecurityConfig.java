package johncruz.tech.exam.minibank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/**").hasAnyAuthority("ADMIN","USER")
//                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/api/rest-call/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/").permitAll()
                .and().cors()
                .and().formLogin();

//        http.authorizeRequests()
//                .antMatchers("/**").hasAuthority("ADMIN")
//                .and().formLogin()
//                .and().logout()
//                    .logoutSuccessHandler(new LogoutSuccessHandler() {
//                        @Override
//                        public void onLogoutSuccess(HttpServletRequest httpServletRequest,
//                                                    HttpServletResponse httpServletResponse,
//                                                    Authentication authentication) throws IOException, ServletException {
//                            UrlPathHelper helper = new UrlPathHelper();
//                            System.out.println("LOGGING OUT -> " + authentication.getName());
//                            String contextPath = helper.getContextPath(httpServletRequest);
//                            httpServletResponse.sendRedirect(contextPath+"/login");
//
//                        }
//                    }).permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){return NoOpPasswordEncoder.getInstance(); }

}

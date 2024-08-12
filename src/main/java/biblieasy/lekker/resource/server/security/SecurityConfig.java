package biblieasy.lekker.resource.server.security;

import biblieasy.lekker.resource.server.entity.Role;
import biblieasy.lekker.resource.server.filters.CustomAuthenticationFilter;
import biblieasy.lekker.resource.server.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Order(1)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserAuthenticationSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.authorizeRequests();

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);


        //Allow user to use basic authentication.
        http.httpBasic();

        //User Restriction
        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
        //USER RESTRICTION
        //Add a new user
        http.authorizeRequests().antMatchers(POST, "/api/user/save").hasAnyAuthority("ADMIN" , "LIBRARIAN");
        //Search loan by userId and BookId
        http.authorizeRequests().antMatchers(GET, "/api/users").hasAnyAuthority("ADMIN" , "USER" , "LIBRARIAN");
        //update loan
        http.authorizeRequests().antMatchers(PUT, "/api/update/user/**").hasAnyAuthority("ADMIN" , "LIBRARIAN");
        //Delete loan
        http.authorizeRequests().antMatchers(DELETE, "/api/delete/user/**").hasAnyAuthority("ADMIN" , "LIBRARIAN");
        //Pagination
        http.authorizeRequests().antMatchers(GET, "/api/user/pageable").hasAnyAuthority("ADMIN" ,"LIBRARIAN");

        // ROLE RESTRICTION
        //Save new role
        http.authorizeRequests().antMatchers(POST, "/api/role/save").hasAuthority("ADMIN");
        //Add role to user
        http.authorizeRequests().antMatchers(POST, "/api/role/addtouser").hasAuthority("ADMIN");
        //Get roles
        http.authorizeRequests().antMatchers(GET, "/api/roles").hasAuthority("ADMIN");
        //Delete role
        http.authorizeRequests().antMatchers(DELETE, "/api/delete/role/**").hasAuthority("ADMIN");
        //Update role
        http.authorizeRequests().antMatchers(PUT, "/api/update/role/**").hasAuthority("ADMIN");

        // BOOK RESTRICTION
        //List all the books
        http.authorizeRequests().antMatchers(GET, "/api/books").hasAnyAuthority("ADMIN" , "USER" , "LIBRARIAN");
        //Search for a specific book using title and author
        http.authorizeRequests().antMatchers(GET, "/api/book").hasAnyAuthority("ADMIN" , "USER" , "LIBRARIAN");
        //Add a new book
        http.authorizeRequests().antMatchers(POST, "/api/book/save").hasAnyAuthority("ADMIN" , "LIBRARIAN");
        //Delete a book with specific id
        http.authorizeRequests().antMatchers(DELETE, "/api/delete/book/**").hasAnyAuthority("ADMIN" , "LIBRARIAN");
        //Update Book
        http.authorizeRequests().antMatchers(PUT, "api/update/book/**").hasAnyAuthority("ADMIN" ,"LIBRARIAN");
        //Pagination
        http.authorizeRequests().antMatchers(GET, "api/book/pageable").hasAnyAuthority("ADMIN" ,"LIBRARIAN");

        //LOAN RESTRICTION
        //loan
        http.authorizeRequests().antMatchers(POST, "/api/borrowBook").hasAnyAuthority("ADMIN", "LIBRARIAN");
        //Search loan by userId and BookId
        http.authorizeRequests().antMatchers(GET, "/api/loans").hasAnyAuthority("ADMIN" , "USER" , "LIBRARIAN");
        //update loan
        http.authorizeRequests().antMatchers(PUT, "/api/update/loan/**").hasAnyAuthority("ADMIN" , "LIBRARIAN");
        //Delete loan
        http.authorizeRequests().antMatchers(DELETE, "/api/delete/loan/**").hasAnyAuthority("ADMIN" , "LIBRARIAN");

        //RESERVE RESTRICTION
        //reservation
        http.authorizeRequests().antMatchers(POST, "/api/reserveBook").hasAnyAuthority("ADMIN", "LIBRARIAN");
        //Search reservation by userId and BookId
        http.authorizeRequests().antMatchers(GET, "/api/reservations").hasAnyAuthority("ADMIN" , "USER" , "LIBRARIAN");
        //update reservation
        http.authorizeRequests().antMatchers(PUT, "/api/update/reservation/**").hasAnyAuthority("ADMIN" , "LIBRARIAN");
        //Delete reservation
        http.authorizeRequests().antMatchers(DELETE, "/api/delete/reservation/**").hasAnyAuthority("ADMIN" , "LIBRARIAN");


//        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}

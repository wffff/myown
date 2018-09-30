package com.wanggoudan.www.security;

import com.wanggoudan.www.filter.qq.QQAuthenticationFilter;
import com.wanggoudan.www.filter.qq.QQAuthenticationManager;
import com.wanggoudan.www.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sang on 2017/1/10.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    UserDetailsService customUserService() {
//        return new CustomUserService();
//    }
    @Resource
    private UserService userService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
    public void configure(WebSecurity web) throws Exception {
        // 忽略URL
        web.ignoring().antMatchers("/static/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/uploadImg","/user/getInfo").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/main")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .permitAll();

        http.addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * 自定义 QQ登录 过滤器
     */
    private QQAuthenticationFilter qqAuthenticationFilter(){
        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/qq/return_url");
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setAlwaysUseDefaultTargetUrl(true);
        successHandler.setDefaultTargetUrl("/qq/user");
        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        return authenticationFilter;
    }

//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

//    public static void main(String[] args) throws ParseException {
//        System.out.println(new BCryptPasswordEncoder().encode("123"));
//        Date date = new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(date));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.parse(df.format(date)));
//    }

//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
}

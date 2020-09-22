package com.myeongmy.book.springboot.config.auth;

import com.myeongmy.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }

    /*
    @EnableWebSecurity : 스프링 시큐리티 관련 설정들을 활성화시켜준다.
    .csrf().disable().headers().frameOptions().disable()
    : h2 console 화면을 사용하기 위해 해당 옵션들을 disable 한다.

    authorizeRequests()
    :URL 별 권한 관리를 설정하는 옵션의 시작점이다. authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있다.

    antMatchers()
    : 권한 관리 대상을 지정하는 옵션. URL 별로 관리가 가능하다.
      permitAll() 옵션은 전체 열람 권한
      /api/v1/** 주소를 가진 api는 user 권한을 가진 사람만 열람 가능

    anyRequest()
    : 나머지 url에 대한 권한 정보 관리. 여기서는 authenticated를 추가하여 나머지 url들은 모두 로그인한 사용자들에게만 허용하게 한다.

    logout().logoutSuccessUrl("/")
    : 로그아웃 성공시 / 주소로 이동

    oauth2Login
    : OAuth2 로그인 기능에 대한 여러 설정의 진입점

    userInfoEndpoint().userService(클래스)
    : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다. 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시

     */
}

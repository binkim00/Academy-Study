package me.shinsunyoung.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.config.jwt.TokenProvider;
import me.shinsunyoung.springbootdeveloper.config.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import me.shinsunyoung.springbootdeveloper.config.oauth.OAuth2SuccessHandler;
import me.shinsunyoung.springbootdeveloper.config.oauth.OAuth2UserCustomService;
import me.shinsunyoung.springbootdeveloper.repository.RefreshTokenRepository;
import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {
    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure(){
        //web.ignoring() : 스프링 시큐리티를 적용하지 않을 주소들을 설정
        return (web) -> web.ignoring()
                // /h2-console의 접속에 스프링 시큐리티를 해제
//                .requestMatchers(toH2Console())
                // resources폴더의 static폴더 접속에 스프링 시큐리티를 해제
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                //.requestMatchers("/articles")
                ;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //csrf 설정 끄기
                .csrf(AbstractHttpConfigurer::disable)
                // 로그인시 id와pw를 base64로 인코딩하여 전달하는 설정 끄기
                .httpBasic(AbstractHttpConfigurer::disable)
                // 일반 로그인,로그아웃은 끄기 -> JWT를 이용한 로그인을 사용할것
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                // 세션을 사용하지 않도록 설정
                .sessionManagement(management->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // addFilterBefore(필터1, 필터2)
                // 필터2가 실행되기 전 필터1을 실행하도록 추가하는 메서드
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth->auth
                        // 아무런 권한이 없어도 실행 가능한 Mapping설정
                        .requestMatchers("/api/token").permitAll()
                        // 로그인을 해야만 실행 가능한 Mapping설정
                        .requestMatchers("/api/**").authenticated()
                        // 위의 두개를 제외한 모든 Mapping은 권한이 없어도 실행가능하도록 설정
                        .anyRequest().permitAll())
                // OAuth2 로그인 설정
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        // 로그인 처리를 실행할 서비스를 설정
                        .authorizationEndpoint(authorizationEndpoint ->
                                authorizationEndpoint.authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository()))
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(oAuth2UserCustomService))
                        // 로그인에 성공했을때 실행할 핸들러 설정
                        .successHandler(oAuth2SuccessHandler()))
                // 예외 처리 설정
                .exceptionHandling(exceptionHandling->exceptionHandling
                        .defaultAuthenticationEntryPointFor(
                                // /api/** 실행시 예외가 발생하면 UNAUTHORIZED(401)번 코드를 반환
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                                new AntPathRequestMatcher("/api/**")
//                                request -> request.getRequestURI().startsWith("/api/**")
                        ))
                .build();
    }
    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler(){
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository(),
                userService);
    }
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(){
        return new TokenAuthenticationFilter(tokenProvider);
    }
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository(){
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

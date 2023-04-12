package com.project.lumos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.lumos.jwt.TokenProvider;

@EnableWebSecurity
public class SecurityConfig {
	
	private final TokenProvider tokenProvider;
	
	@Autowired
	public SecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	/* 1. 암호화 처리를 위한 PasswordEncoder*/
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/* 2. 시큐리티 설정을 무시 할 정적 리소스 등록 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**",
				                                   "/lib/**", "/productimgs/**");
	}
	
	/* 3. HTTP요청에 대한 권한별 설정 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.exceptionHandling()
		    .and()
		    .authorizeRequests()
		    	.antMatchers("/").authenticated()
		    	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()	
		    	.antMatchers("/auth/**").permitAll()
		    	.antMatchers("/api/v1/products/**").permitAll() 
		    	.antMatchers("/api/v1/revies/**").permitAll()
		    	/* [구도연] */
		    	.antMatchers("/api/v1/company-management/**").permitAll()
		    	.antMatchers("/api/v1/shop-management/**").permitAll()
		    	.antMatchers("/api/v1/order/**").permitAll()
		    	.antMatchers("/api/v1/order-management/**").permitAll()
		    	.antMatchers("/api/v1/cart/**").permitAll()
		    	.antMatchers("/api/v1/profileUpdate/**").permitAll() 
//		    	.antMatchers("/api/v1/profileUpdate").hasAnyRole("USER") 
//		    	.antMatchers("/api/**").hasAnyRole("ADMIN")
		    	.antMatchers("/api/**").hasAnyRole("USER","ADMIN")
		    .and()
		    	.sessionManagement()
		    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
		    	.cors()
		    .and()
		    	/* jwt 토큰 방식을 쓰겠다는 설정 */
		    	.apply(new JwtSecurityConfig(tokenProvider));
		
		return http.build();
	}
	
	/* 4. CORS 설정용 Bean(허용 할 origin과 httpMethod 종류와 header 값) */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-type"
													, "Access-Control-Allow-Headers", "Authorization"
													, "X-Requested-With"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}

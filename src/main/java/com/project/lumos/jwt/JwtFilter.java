package com.project.lumos.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/* OncePerRequestFilter: 사용자의 요청에 한번만 동작하는 필터 */
public class JwtFilter extends OncePerRequestFilter{
	
	private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
	
	public static final String AUTHORIZATION_HEADER = "Authorization";	
	public static final String BEARER_PREFIX = "Bearer";
	
	private final TokenProvider tokenProvider;
	
	@Autowired
	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = resolveToken(request);	
		
		if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { 
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
	
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);	
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) { 
			return bearerToken.substring(7);						
		}
		return null;
	}

}


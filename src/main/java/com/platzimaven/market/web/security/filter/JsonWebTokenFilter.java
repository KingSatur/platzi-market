package com.platzimaven.market.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.platzimaven.market.domain.service.PlatziUserDetailService;
import com.platzimaven.market.web.security.JWTUtil;

//Es como un interceptor de angular
@Component
public class JsonWebTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private PlatziUserDetailService platziUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			String token = authorizationHeader.substring(7);
			String username = this.jwtUtil.extractUsernameFromToken(token);
			// Se usa para verificar que aun no exista ninguna autenticacion para este
			// usuario
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.platziUserDetailService.loadUserByUsername(username);
				if (this.jwtUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					// Con el fin de evaluar varios detalles del request
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// Se asgina la autenticacion para que la proxima vez no tenga que pasar por
					// toda la validacion de este filtro
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		
		filterChain.doFilter(request, response);

	}

}

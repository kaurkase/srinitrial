package kaur.srini.srinitrial.web.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import kaur.srini.srinitrial.web.authenticationhandlers.CustomAuthenticationFailureHandler;
import kaur.srini.srinitrial.web.authenticationhandlers.CustomAuthenticationSuccessHandler;

public class RestAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

	public RestAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher("/login", "POST"), authenticationManager);
		this.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
		this.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String username, password;

		try {
			Map<String, String> requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			username = requestMap.get("username");
			password = requestMap.get("password");
		} catch (IOException e) {
			throw new AuthenticationServiceException(e.getMessage(), e);
		}

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

}

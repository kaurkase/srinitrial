package kaur.srini.srinitrial.web.authenticationhandlers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private ObjectMapper mapper;
	public CustomAuthenticationSuccessHandler() {
		mapper = new ObjectMapper();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> responseMap = Map.of("result", "ok");
        response.getWriter().write(mapper.writeValueAsString(responseMap));
	}

}
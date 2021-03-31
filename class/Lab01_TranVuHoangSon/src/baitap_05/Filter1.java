package baitap_05;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class Filter1 implements Filter {
	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Pass Filter 1");
		
		arg2.doFilter(arg0, arg1);
		arg1.setContentType("text/html");
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}

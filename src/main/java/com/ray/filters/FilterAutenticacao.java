package com.ray.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.entities.User;
import com.ray.util.JPAUtil;

@WebFilter(urlPatterns = { "/pages/pessoas.xhtml", "/pages/user.xhtml", "/UserBean/*", "/pessoaBean/*", "/lancamentosBean/*", "/pages/lancamentos.xhtml" }) //
public class FilterAutenticacao implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	JPAUtil.getEntityManager();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();
	User user = (User) session.getAttribute("user");
	String url = req.getContextPath();
	if (user == null) {
	    System.out.println("true");
	    HttpServletResponse resp = (HttpServletResponse) response;
	    resp.sendRedirect(url + "/pages/login.xhtml");
	    System.out.println(url);
	    return;
	} else {
	    chain.doFilter(request, response);
	}
    }

}

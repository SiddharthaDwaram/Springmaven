package com.acme.s4ext.odata;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.olingo.odata2.api.exception.ODataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Servlet {@link Filter} to block access to secure entities via non secure
 * servlet (/espm.svc/)
 * <p>
 * Refer to the web.xml file on the declaration of the Filter.
 * 
 */
public class EntityServiceFactoryFilter implements Filter {

	/**
	 * {@link Logger} implementation for logging.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityServiceFactoryFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {

			if (request instanceof HttpServletRequest) {

				HttpServletRequest oCntxt = (HttpServletRequest) request;
				String url = oCntxt.getRequestURI().toString();
				if (url.contains("/employee/")) {
					if (isPathRestrictedEmp(oCntxt)) {
						HttpServletResponse httpResp = (HttpServletResponse) response;
						httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
								"Access denied to the secure entity, Employee");
					} else {
						chain.doFilter(request, response);
					}
				} 
				else if(url.contains("/hr/")){
					if (isPathRestrictedHr(oCntxt)) {
						HttpServletResponse httpResp = (HttpServletResponse) response;
						httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
								"Access denied to the secure entity, HR");
					} else {
						chain.doFilter(request, response);
					}
				}
				else if(url.contains("/manager/")){
					if (isPathRestrictedManager(oCntxt)) {
						HttpServletResponse httpResp = (HttpServletResponse) response;
						httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
								"Access denied to the secure entity, Manager");
					} else {
						chain.doFilter(request, response);
					}
				}
				else {
					chain.doFilter(request, response);
				}
			}

		} catch (IOException | ServletException | ODataException e) {
			LOGGER.error(e.getMessage());
		} 

	}

	@Override
	public void destroy() {

	}

	/**
	 * Checks if triggered path is restricted for anonymous users
	 * 
	 * @param oCntxt
	 *            OData Context
	 * @return true if path is restricted else false
	 * @throws ODataException
	 */
	private boolean isPathRestrictedEmp(HttpServletRequest oCntxt) throws ODataException {
		boolean status;
		String path = oCntxt.getRequestURI().toString();
		if (path.contains("/Emp2Mgrs")||path.contains("/Projects")||path.contains("/CreateProjects")||path.contains("/AllEmployees")||path.contains("/ProjectMasters")){
			status = true;
		}else{
			status = false;
		}
		
		return status;
	}
	private boolean isPathRestrictedHr(HttpServletRequest oCntxt) throws ODataException {

		boolean status;
		String path = oCntxt.getRequestURI().toString();
		if (path.contains("/Emp2Mgrs")||path.contains("/Projects")||path.contains("/CreateProjects")||path.contains("/EmpSkillRefs")||path.contains("/CreateProjects")||path.contains("/EmpPastExps")||path.contains("/EMPLOYEEs")||path.contains("/ProjectMasters")){
			status = true;
		}else{
			status = false;
		}
		
		return status;
	}
	private boolean isPathRestrictedManager(HttpServletRequest oCntxt) throws ODataException {

		boolean status;
		String path = oCntxt.getRequestURI().toString();
		if (path.contains("/AllEmployees")){
			status = true;
		}else{
			status = false;
		}
		
		return status;
	}
}

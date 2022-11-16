package com.ideas2it.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.model.TechStack;
import com.ideas2it.service.TechStackService;
import com.ideas2it.service.impl.TechStackServiceImpl;
import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.logger.EmployeeManagementLogger;

@WebServlet(urlPatterns = { "/insertTechStack", "/getTechStacks", "/getTechStackById", "/removeTechStackById",
		"/getTechStackForUpdate", "/updateTechStack", "/getExistingTechStacks" })
public class TechStackServlet extends HttpServlet {
	TechStackService techStackService = new TechStackServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/insertTechStack":
			createTechStack(request, response);
			break;
		case "/getTechStacks":
			getTechStacks(request, response);
			break;
		case "/getTechStackById":
			getTechStackById(request, response);
			break;
		case "/removTechStackById":
			removeTechStackById(request, response);
			break;
		case "/getExistingTechStacks":
			getTechStacks(request, response);
			break;
		}
	}

	private void createTechStack(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			TechStack techStack = techStackService.createTechStack(request.getParameter("name"),
					Float.parseFloat(request.getParameter("version")));
			HttpSession session = request.getSession();
			session.setAttribute("TechStack", techStack);
			response.sendRedirect("insertTechStack.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	private void getTechStacks(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<TechStack> techStacks = techStackService.getTechStacks();
			HttpSession session = request.getSession();
			if (!techStacks.isEmpty()) {
				session.setAttribute("TechStacks", techStacks);
				if (request.getServletPath().equals("/getExistingTechStacks")) {
					response.sendRedirect("assignTechStacks.jsp");
				} else {
					response.sendRedirect("displayTechStacks.jsp");
				}
			} else {
				session.setAttribute("error", "No Record Found");
                response.sendRedirect("assignTechStacks.jsp");
			}
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	private void getTechStackById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			TechStack techStack = techStackService.getTechStackById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("TechStack", techStack);
			response.sendRedirect("getTechStack.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

	private void removeTechStackById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			boolean isDeleted = techStackService.removeTechStackById(Integer.parseInt(request.getParameter("id")));
			HttpSession session = request.getSession();
			session.setAttribute("isDeleted", isDeleted);
			response.sendRedirect("removeTechStack.jsp");
		} catch (EmployeeManagementException employeeManagementException) {
			EmployeeManagementLogger.displayErrorLogs(employeeManagementException.getMessage());
		}
	}

}

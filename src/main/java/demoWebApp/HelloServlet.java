/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package demoWebApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns="/order")
public class HelloServlet extends HttpServlet {
	
	 @EJB MessageSender sender;
	 
	 @EJB MessageReceiverSync receiver;
	 
	 protected void sendRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        //response.setContentType("text/html;charset=UTF-8");
		 
	     String issueDate=request.getParameter("IssueDate");
	     String numOfOrder=request.getParameter("NumOfOrder");
	     String m="Total of"+ numOfOrder + "sent on"+ issueDate;
	     
	     //sender.sendMessage(m);
	    
	     
		 try (PrintWriter out = response.getWriter()) {
	            out.println("");
	            out.println("");
	            out.println("JMS2 Send Message");
	            out.println("");
	            out.println("");
	            out.println("JMS2 Send/Receive Message using JMS2 " + request.getContextPath() + "");
	           // String m = "Hello there";
	            sender.sendMessage(m);
	            out.format("Message sent: %1$s.", m);
	            out.println("Receiving message...");
	            String message = receiver.receiveMessage();
	            out.println("Message rx: " + message);
	            out.println("");
	            out.println("");
	        }
	    }
	 
	 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	 
        response.getWriter().print("Hello, World!");  
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	
    	
    	
    	
//    	response.setContentType("text/html;charset=UTF-8");
//    	response.getWriter().println("JMS2 Send/Receive Message using JMS2 " + request.getContextPath() + "");
    	
    	//String message=receiver.receiveMessage();
    	
//    	 String issueDate=request.getParameter("IssueDate");
//  	     String numOfOrder=request.getParameter("NumOfOrder");
//  	     String m="Total of"+ numOfOrder + "sent on"+ issueDate;
//  	     sender.sendMessage(m);
 // 	     String message = receiver.receiveMessage();      
    	sendRequest(request,response);
         //request.setAttribute("theOrderDetails", m);
        // request.setAttribute("theReceivedAcknowledge",message);
         //request.getRequestDispatcher("response.jsp").forward(request, response); 
    }
}


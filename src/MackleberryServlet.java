

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Gradebook;
import model.QS3;

/**
 * Servlet implementation class MackleberryServlet
 */
@WebServlet("/MackleberryServlet")
public class MackleberryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MackleberryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QS3 qs3=new QS3();
		long id = 0;

		String student_name="" ,student_grade="" ,assignment_name="" ,assignment_type="";
		String user_name=request.getParameter("firstname");
		long count =qs3.CheckUserExists(user_name);
		if(count>0)
		{
		    
		    System.out.println("here 1");
		
			List<Gradebook> list=qs3.getNameFromUser(user_name).getResultList();
			for(Gradebook gb:list)
			{
				id=gb.getStudentId();
				student_name=gb.getStudentName();
				assignment_name=gb.getAssignmentName();
				assignment_type = gb.getAssignmentType();
				student_grade=gb.getGrade();
				 System.out.println("here 2");
			}
			request.setAttribute("id", id);
			request.setAttribute("student_name", student_name);
			request.setAttribute("assignment_name", assignment_name);
			request.setAttribute("assignment_type", assignment_type);
			request.setAttribute("student_grade", student_grade);

			request.getRequestDispatcher("/StudentPage.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("message", "Enter a valid name");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}



package model;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import model.DBUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class QS3 {
	public TypedQuery<Gradebook> getNameFromUser(String name)
	{

		EntityManager em1=DBUtil.getEmFactory().createEntityManager();
		System.out.println("here 3");
		return em1.createQuery(
				"Select gb from Gradebook gb where gb.studentName like :stud_name",Gradebook.class)
				.setParameter("stud_name",name);


	}

	public long CheckUserExists(String name)
	{

		EntityManager em1=DBUtil.getEmFactory().createEntityManager();
		TypedQuery query =em1.createQuery(
				"Select count(gb) from Gradebook gb where gb.studentName like :stud_name",Gradebook.class)
				.setParameter("stud_name",name);
		long total =(long) query.getSingleResult();

		return total;


	}
	
	public int UpdateGradeBook(String name ,String assignment_name ,String assignment_type ,String grade)
	{
	int count=0;
	EntityManager em1=DBUtil.getEmFactory().createEntityManager();
	EntityTransaction trans = em1.getTransaction();
	TypedQuery query =em1.createQuery(
			"Update Gradebook gb set gb.assignmentName =:ass_name ,gb.assignmentType= :ass_type ,gb.grade= :ass_grade where gb.studentName = :stud_name",Gradebook.class)
			
			.setParameter("ass_name",assignment_name)
			.setParameter("ass_type",assignment_type)
			.setParameter("ass_grade",grade)
			.setParameter("stud_name",name);
	     try
	     {
	    	trans.begin();
	    	count=query.executeUpdate();
	    	trans.commit();
	    	
	     }
	     catch (Exception e)
	     {
	    	 trans.rollback();
	    	 
	     }
	     finally
	     {
	    	 em1.close();
	     }
	     return count;
	}
}



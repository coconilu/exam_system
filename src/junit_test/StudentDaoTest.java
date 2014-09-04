package junit_test;

import org.junit.Test;

import dao.StudentDao;
import domain.Student;
import exception.StudentNotExistException;

public class StudentDaoTest {

	@Test
	public void test_add() {
		StudentDao dao=new StudentDao();
		Student s=new Student();
		s.setExamid("123");
		s.setGrade(59);
		s.setIdcard("321");
		s.setLocation("地狱");
		s.setName("tom");
		dao.add(s);
	}
	
	/**
	 * 为了节省时间，这里使用debug的方法设置断点，在watch返回值，结果正常
	 * @throws StudentNotExistException 
	 */
	@Test
	public void test_find() throws StudentNotExistException {
		StudentDao dao=new StudentDao();
		dao.find("123");
	}

	@Test
	public void test_delete() throws StudentNotExistException {
		StudentDao dao=new StudentDao();
		//下面输入不存在的name，就会抓捕到StudentNotExistException异常
		//dao.delete("adc");
		dao.delete("tom");
	}
}

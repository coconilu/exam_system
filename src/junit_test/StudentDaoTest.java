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
		s.setLocation("����");
		s.setName("tom");
		dao.add(s);
	}
	
	/**
	 * Ϊ�˽�ʡʱ�䣬����ʹ��debug�ķ������öϵ㣬��watch����ֵ���������
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
		//�������벻���ڵ�name���ͻ�ץ����StudentNotExistException�쳣
		//dao.delete("adc");
		dao.delete("tom");
	}
}

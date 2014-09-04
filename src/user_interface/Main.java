package user_interface;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import dao.StudentDao;
import domain.Student;
import exception.StudentNotExistException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("���ѧ����a��  ɾ��ѧ����b��  ����ѧ����c�� ���ԣ�d��");
		System.out.print("������������ͣ�");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String type = br.readLine();
			
			if("a".equals(type)){
				
				System.out.print("������ѧ��������");
				String name = br.readLine();
				
				System.out.print("������ѧ��׼��֤�ţ�");
				String examid = br.readLine();
				
				System.out.print("������ѧ�����֤�ţ�");
				String idcard = br.readLine();
				
				System.out.print("������ѧ�����ڵأ�");
				String location = br.readLine();
				
				System.out.print("������ѧ���ɼ���");
				String grade = br.readLine();
				
				Student s = new Student();
				s.setExamid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);
				
				StudentDao dao = new StudentDao();
				dao.add(s);
				
				System.out.print("��ӳɹ�");
				
			}else if("b".equals(type)){
				
				System.out.print("������ѧ��������");
				String name = br.readLine();
				
				StudentDao dao = new StudentDao();
				try{
					dao.delete(name);
					System.out.println("ɾ���ɹ���");
				}catch(StudentNotExistException e){
					System.out.println("�����ڸ�ѧ����");
				}
				
			}else if("c".equals(type)){
				
				System.out.print("������ѧ��׼��֤�ţ�");
				String examid = br.readLine();
				
				StudentDao dao=new StudentDao();
				try{
				Student s=dao.find(examid);
				System.out.println(s.getName());
				System.out.println(s.getExamid());
				System.out.println(s.getIdcard());
				System.out.println(s.getLocation());
				System.out.println(s.getGrade());
				}catch(StudentNotExistException e){
					System.out.println("�����ڸ�ѧ����");
				}
			}else{
				System.out.println("��֧�ֵĲ�����");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�Բ��𣬳�����");
		}
		
	}

}

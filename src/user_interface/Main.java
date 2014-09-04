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

		System.out.println("添加学生（a）  删除学生（b）  查找学生（c） 测试（d）");
		System.out.print("请输入操作类型：");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String type = br.readLine();
			
			if("a".equals(type)){
				
				System.out.print("请输入学生姓名：");
				String name = br.readLine();
				
				System.out.print("请输入学生准考证号：");
				String examid = br.readLine();
				
				System.out.print("请输入学生身份证号：");
				String idcard = br.readLine();
				
				System.out.print("请输入学生所在地：");
				String location = br.readLine();
				
				System.out.print("请输入学生成绩：");
				String grade = br.readLine();
				
				Student s = new Student();
				s.setExamid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);
				
				StudentDao dao = new StudentDao();
				dao.add(s);
				
				System.out.print("添加成功");
				
			}else if("b".equals(type)){
				
				System.out.print("请输入学生姓名：");
				String name = br.readLine();
				
				StudentDao dao = new StudentDao();
				try{
					dao.delete(name);
					System.out.println("删除成功！");
				}catch(StudentNotExistException e){
					System.out.println("不存在该学生。");
				}
				
			}else if("c".equals(type)){
				
				System.out.print("请输入学生准考证号：");
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
					System.out.println("不存在该学生。");
				}
			}else{
				System.out.println("不支持的操作！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("对不起，出错了");
		}
		
	}

}

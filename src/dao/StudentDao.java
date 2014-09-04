package dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.XmlUtils;
import domain.Student;
import exception.StudentNotExistException;

public class StudentDao {

	/**
	 * @param s
	 * DAO添加学生信息
	 */
	public void add(Student s){
		try {
			//创建文档对象
			Document document = XmlUtils.getDocument();
			//创建封装学生信息的标签
			Element student_tag=document.createElement("student");
			
			//添加属性
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());
			
			//设置子标签的值
			Element name=document.createElement("name");
			Element location=document.createElement("location");
			Element grade=document.createElement("grade");
			name.setTextContent(s.getName());
			location.setTextContent(s.getLocation());
			grade.setTextContent(s.getGrade()+"");
			
			//添加子标签
			student_tag.appendChild(name);
			student_tag.appendChild(location);
			student_tag.appendChild(grade);
			
			//把该标签添加到父标签
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			//更新写回文档
			XmlUtils.write2Xml(document);
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * @param examid
	 * @return
	 * 查找学生信息并返回student对象
	 * @throws StudentNotExistException 
	 */
	public Student find(String examid) throws StudentNotExistException{
		try {
			Document document=XmlUtils.getDocument();
			NodeList list=document.getElementsByTagName("student");
			for(int i=0;i<list.getLength();i++){
				Element student_tag=(Element) list.item(i);
				if(student_tag.getAttribute("examid").equals(examid)){
					Student s=new Student();
					s.setIdcard(student_tag.getAttribute("idcard"));
					s.setExamid(student_tag.getAttribute("examid"));
					s.setName(student_tag.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(student_tag.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(student_tag.getElementsByTagName("grade").item(0).getTextContent()));
					return s;
				}
			}
			throw new StudentNotExistException(examid+"不存在！！");
		} catch (StudentNotExistException e) {
			throw e;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param name
	 * @throws StudentNotExistException
	 * 自己编写一个StudentNotExistException，并在程序中抓捕
	 */
	public void delete(String name) throws StudentNotExistException{
		try {
			Document document=XmlUtils.getDocument();
			NodeList list=document.getElementsByTagName("name");
			for(int i=0;i<list.getLength();i++){
				Node node=list.item(i);
				if(node.getTextContent().equals(name)){
					node.getParentNode().getParentNode().removeChild(node.getParentNode());
					
					//更新写回文档
					XmlUtils.write2Xml(document);
					
					return;
				}
			}
			throw new StudentNotExistException(name+"不存在！！");
		} catch (StudentNotExistException e) {
			throw e;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}

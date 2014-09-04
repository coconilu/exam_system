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
	 * DAO���ѧ����Ϣ
	 */
	public void add(Student s){
		try {
			//�����ĵ�����
			Document document = XmlUtils.getDocument();
			//������װѧ����Ϣ�ı�ǩ
			Element student_tag=document.createElement("student");
			
			//�������
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());
			
			//�����ӱ�ǩ��ֵ
			Element name=document.createElement("name");
			Element location=document.createElement("location");
			Element grade=document.createElement("grade");
			name.setTextContent(s.getName());
			location.setTextContent(s.getLocation());
			grade.setTextContent(s.getGrade()+"");
			
			//����ӱ�ǩ
			student_tag.appendChild(name);
			student_tag.appendChild(location);
			student_tag.appendChild(grade);
			
			//�Ѹñ�ǩ��ӵ�����ǩ
			document.getElementsByTagName("exam").item(0).appendChild(student_tag);
			
			//����д���ĵ�
			XmlUtils.write2Xml(document);
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * @param examid
	 * @return
	 * ����ѧ����Ϣ������student����
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
			throw new StudentNotExistException(examid+"�����ڣ���");
		} catch (StudentNotExistException e) {
			throw e;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param name
	 * @throws StudentNotExistException
	 * �Լ���дһ��StudentNotExistException�����ڳ�����ץ��
	 */
	public void delete(String name) throws StudentNotExistException{
		try {
			Document document=XmlUtils.getDocument();
			NodeList list=document.getElementsByTagName("name");
			for(int i=0;i<list.getLength();i++){
				Node node=list.item(i);
				if(node.getTextContent().equals(name)){
					node.getParentNode().getParentNode().removeChild(node.getParentNode());
					
					//����д���ĵ�
					XmlUtils.write2Xml(document);
					
					return;
				}
			}
			throw new StudentNotExistException(name+"�����ڣ���");
		} catch (StudentNotExistException e) {
			throw e;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}

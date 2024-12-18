package com.lms.service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dto.ClassroomDTO;
import com.lms.dto.StudentDTO;
import com.lms.dto.TeacherDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.ClassroomEntity;
import com.lms.entity.StudentEntity;
import com.lms.entity.TeacherEnrollmentEntity;
import com.lms.entity.TeacherEntity;
import com.lms.mapper.ClassroomMapper;
import com.lms.mapper.StudentMapper;
import com.lms.mapper.TeacherMapper;
import com.lms.repository.AdminRepository;
import com.lms.repository.ClassroomRepository;
import com.lms.repository.StudentRepository;
import com.lms.repository.TeacherEnrollmentRepository;
import com.lms.repository.TeacherRepository;

import etc.RandomCodeGenerator;
@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private final TeacherRepository teacherRepo=new TeacherRepository();
	
	@Autowired
	private final AdminRepository adminRepo=new AdminRepository();
	
	@Autowired
	private final StudentRepository studentRepo=new StudentRepository();
	
	@Autowired
	private final ClassroomRepository classRepo=new ClassroomRepository();
	
	@Autowired
	private final TeacherEnrollmentRepository trEnrollRepo=new TeacherEnrollmentRepository();
	
	@Autowired
    private CloudinaryService cloudinaryService;
	
	private final TeacherMapper teacherMapper=new TeacherMapper();
	private final StudentMapper studentMapper=new StudentMapper();
	private final ClassroomMapper classMapper=new ClassroomMapper();
	
	@Override
	public List<TeacherDTO> getAllTeachers(){
		List<TeacherEntity> teacherList=teacherRepo.getAllTeachers();
		List<TeacherDTO> teacherListDTO=new ArrayList<TeacherDTO>();
		for(TeacherEntity teacher:teacherList) {
			if(teacher.getStatus()==1) {
				TeacherDTO teacherDTO=teacherMapper.toDTO(teacher);
				teacherListDTO.add(teacherDTO);
			}
		}
		return teacherListDTO;
	}
	@Override
	public boolean createTeacher(TeacherEntity teacher,int adminId) {
		AdminEntity admin=adminRepo.retrieveAdminById(adminId);
		teacher.setGenerate_password(RandomCodeGenerator.generateCode());
		teacher.setAdmin(admin);
		boolean result=teacherRepo.insertTeacher(teacher);
		if(result) {
			return true;
		}
		return false;
	}
	@Override
	public TeacherDTO getTeacherById(int teacherId) {
		TeacherEntity teacherEntity=teacherRepo.retrieveTeacherById(teacherId);
		TeacherDTO teacherDTO=teacherMapper.toDTO(teacherEntity);
		return teacherDTO;
	}
	@Override
	public void editTeacher(TeacherDTO teacherDTO) {
		TeacherEntity teacherEntity=teacherRepo.retrieveTeacherById(teacherDTO.getId());
		teacherEntity.setName(teacherDTO.getName());
		teacherEntity.setEmail(teacherDTO.getEmail());
		teacherEntity.setAddress(teacherDTO.getAddress());
		teacherEntity.setQualification(teacherDTO.getQualification());
		teacherRepo.updateTeacher(teacherEntity);
	}
	@Override
	public void deleteTeacher(int teacherId) {
		teacherRepo.updateTeacherStatusTo0(teacherId);
	}
	@Override
	public List<TeacherDTO> getDeletedTeachers(){
		List<TeacherEntity> teacherList=teacherRepo.getAllTeachers();
		List<TeacherDTO> teacherListDTO=new ArrayList<TeacherDTO>();
		for(TeacherEntity teacher:teacherList) {
			if(teacher.getStatus()==0) {
				TeacherDTO teacherDTO=teacherMapper.toDTO(teacher);
				teacherListDTO.add(teacherDTO);
			}
		}
		return teacherListDTO;
	}
	@Override
	public void restoreTeacher(int teacherId) {
		teacherRepo.updateTeacherStatusTo1(teacherId);
	}
	@Override
	public List<StudentDTO> getAllStudents(){
		List<StudentEntity> studentListEntity=studentRepo.retrieveStudents();
		List<StudentDTO> studentListDTO=new ArrayList<StudentDTO>();
		for(StudentEntity studentEntity:studentListEntity) {
			if(studentEntity.getStatus()==1) {
				StudentDTO studentDTO=studentMapper.toDTO(studentEntity);
				studentListDTO.add(studentDTO);
			}
		}
		return studentListDTO;
	}
	@Override
	public void deleteStudent(int studentId) {
		studentRepo.updateStudentStatus0(studentId);
	}
	@Override
	public void createClassroom(ClassroomDTO classDTO) {
		try {
			String imageURL=cloudinaryService.uploadFile(classDTO.getImageFile());
			classDTO.setImageUrl(imageURL);
			classDTO.setPasscode(RandomCodeGenerator.generateCode());
			ClassroomEntity classEntity=classMapper.toEntity(classDTO);
			int classId=classRepo.insertClassroom(classEntity);
			List<Integer> teacherIds=classDTO.getTeachers();
			createTeacherEnrollment(classId, teacherIds);
		} catch (IOException e) {
			System.out.println("Create Classroom Cloud Image Error : "+e.getMessage());
		}
	}
	@Override
	public List<ClassroomDTO> getAllClasses(){
		List<ClassroomEntity> classListEntity=classRepo.retrieveClassrooms();
		List<ClassroomDTO> classListDTO=new ArrayList<ClassroomDTO>();
		for(ClassroomEntity classEntity:classListEntity) {
			if(classEntity.getStatus()==1) {
				ClassroomDTO classDTO=classMapper.toDTO(classEntity);
				classListDTO.add(classDTO);
			}
		}
		return classListDTO;
	}
	@Override
	public void deleteClassroom(int classId) {
		boolean isDelete=classRepo.updateClassStatus0(classId);
		if(!isDelete) {
			System.out.println("Delete Classroom Error");
		}
	}
	@Override
	public void createTeacherEnrollment(int classroomId,List<Integer> teacherIds) {
		ClassroomEntity classroom=classRepo.retrieveClassById(classroomId);
		for(Integer teacherId:teacherIds) {
			TeacherEntity teacher=teacherRepo.retrieveTeacherById(teacherId);
			trEnrollRepo.insertTeacherEnrollment(teacher, classroom);	
		}
	}
	@Override
	public List<TeacherDTO> getTeachersInClassroom(int classroomId){
		List<TeacherEnrollmentEntity> trEnrollListEntity=trEnrollRepo.retrieveTeacherEnrollmentInTheClassroom(classroomId);
		List<TeacherDTO> trListDTO=new ArrayList<TeacherDTO>();
		for(TeacherEnrollmentEntity teacherEnrollEntity:trEnrollListEntity) {
			TeacherEntity teacherEntity=teacherEnrollEntity.getTeacher();
			TeacherDTO trDTO=teacherMapper.toDTO(teacherEntity);
			LocalDateTime createdDate = teacherEnrollEntity.getEnrollmentDate();
			String formattedDate = createdDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			trDTO.setEnrollmentDate(formattedDate);
			trListDTO.add(trDTO);
		}
		return trListDTO;
	}
	@Override
	public ClassroomDTO getClassById(int classroomId) {
		ClassroomEntity classEntity=classRepo.retrieveClassById(classroomId);
		if(classEntity!=null) {
			ClassroomDTO classDTO=classMapper.toDTO(classEntity);
			return classDTO;
		}else {
			System.out.println("Get Class By Id Error");
			return null;
		}
	}
}

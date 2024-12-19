package com.lms.repository;

import org.springframework.stereotype.Repository;

import com.lms.dto.LoginDTO;
import com.lms.dto.UserDTO;
import com.lms.entity.AdminEntity;
import com.lms.entity.StudentEntity;
import com.lms.entity.TeacherEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class UserRepository {
	public String getTableNameByEmail(String email) {
	    String role = null;
	    EntityManager em = JPAUtil.getEniEntityManager();
	    try {
	        String query = "SELECT a FROM AdminEntity a WHERE a.email = :email";
	        TypedQuery<AdminEntity> queryAdmin = em.createQuery(query, AdminEntity.class);
	        queryAdmin.setParameter("email", email);
	        AdminEntity admin = queryAdmin.getSingleResult(); 
	        if (admin != null) {
	            role = "Admin";
	            return role; 
	        }
	    } catch (NoResultException e) {
	       
	    }

	    // Check Teacher role
	    if (role == null) {
	        try {
	            String query = "SELECT t FROM TeacherEntity t WHERE t.email = :email";
	            TypedQuery<TeacherEntity> queryTeacher = em.createQuery(query, TeacherEntity.class);
	            queryTeacher.setParameter("email", email);
	            TeacherEntity teacher = queryTeacher.getSingleResult(); 
	            if (teacher != null) {
	                role = "Teacher";
	                return role; // Exit early if role is found
	            }
	        } catch (NoResultException e) {
	        	
	        }
	    }

	    // Check Student role
	    if (role == null) {
	        try {
	            String query = "SELECT s FROM StudentEntity s WHERE s.email = :email";
	            TypedQuery<StudentEntity> queryStudent = em.createQuery(query, StudentEntity.class);
	            queryStudent.setParameter("email", email);
	            StudentEntity student = queryStudent.getSingleResult(); 
	            if (student != null) {
	                role = "Student";
	                return role; // Exit early if role is found
	            }
	        } catch (NoResultException e) {
	        	
	        }
	    }
	    return role; 
	}

	// Close EntityManager properly
	public UserDTO checkAdminPassword1(LoginDTO loginDTO) {
	    UserDTO userDTO = null;
	    try (EntityManager em = JPAUtil.getEniEntityManager()) { // Try-with-resources
	        String query = "SELECT a FROM AdminEntity a WHERE a.email=:email AND a.password=:password";
	        TypedQuery<AdminEntity> queryAdmin = em.createQuery(query, AdminEntity.class);
	        queryAdmin.setParameter("email", loginDTO.getEmail());
	        queryAdmin.setParameter("password", loginDTO.getPassword());
	        AdminEntity admin = queryAdmin.getSingleResult();
	        if (admin != null) {
	            userDTO = new UserDTO();
	            userDTO.setId(admin.getId());
	            userDTO.setName(admin.getName());
	            userDTO.setEmail(admin.getEmail());
	            userDTO.setPassword(admin.getPassword());
	            userDTO.setRole("Admin");
	        }
	    } catch (NoResultException e) {
	        // Just log it, don't persist anything.
	        //System.out.println("No Admin Found for email: " + loginDTO.getEmail());
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    return userDTO;
	}

	public UserDTO checkAdminPassword(LoginDTO loginDTO) {
	    UserDTO userDTO = null;
	    EntityManager em = JPAUtil.getEniEntityManager();
	    try {
	        String query = "SELECT a FROM AdminEntity a WHERE a.email=:email AND a.password=:password";
	        TypedQuery<AdminEntity> queryAdmin = em.createQuery(query, AdminEntity.class);
	        queryAdmin.setParameter("email", loginDTO.getEmail());
	        queryAdmin.setParameter("password", loginDTO.getPassword());
	        AdminEntity admin = queryAdmin.getSingleResult();
	        if (admin != null) {
	            userDTO = new UserDTO();
	            userDTO.setId(admin.getId());
	            userDTO.setName(admin.getName());
	            userDTO.setEmail(admin.getEmail());
	            userDTO.setPassword(admin.getPassword());
	            userDTO.setRole("Admin");
	        }
	    } catch (NoResultException e) {
	       
	    } catch (Exception e) {
	       
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return userDTO;
	}
	public UserDTO checkTeacherPassword(LoginDTO loginDTO) {
	    UserDTO userDTO = null;
	    EntityManager em = JPAUtil.getEniEntityManager();
	    try {
	        String query = "SELECT t FROM TeacherEntity t WHERE t.email=:email AND t.password=:password";
	        TypedQuery<TeacherEntity> queryTeacher = em.createQuery(query, TeacherEntity.class);
	        queryTeacher.setParameter("email", loginDTO.getEmail());
	        queryTeacher.setParameter("password", loginDTO.getPassword());
	        TeacherEntity teacher = queryTeacher.getSingleResult();
	        if (teacher != null) {
	            userDTO = new UserDTO();
	            userDTO.setId(teacher.getId());
	            userDTO.setName(teacher.getName());
	            userDTO.setEmail(teacher.getEmail());
	            userDTO.setPassword(teacher.getPassword());
	            userDTO.setRole("Teacher");
	        }
	    } catch (NoResultException e) {
	       
	    } catch (Exception e) {
	       
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return userDTO;
	}
	public UserDTO checkTeacherPasscode(LoginDTO loginDTO) {
	    UserDTO userDTO = null;
	    EntityManager em = JPAUtil.getEniEntityManager();
	    try {
	        String query = "SELECT t FROM TeacherEntity t WHERE t.email=:email AND t.generate_password=:passcode";
	        TypedQuery<TeacherEntity> queryTeacher = em.createQuery(query, TeacherEntity.class);
	        queryTeacher.setParameter("email", loginDTO.getEmail());
	        queryTeacher.setParameter("passcode", loginDTO.getPassword());
	        TeacherEntity teacher = queryTeacher.getSingleResult();
	        if (teacher != null) {
	            userDTO = new UserDTO();
	            userDTO.setId(teacher.getId());
	            userDTO.setName(teacher.getName());
	            userDTO.setEmail(teacher.getEmail());
	            userDTO.setPassword(teacher.getPassword());
	            userDTO.setRole("Teacher");
	        }
	    } catch (NoResultException e) {
	       
	    } catch (Exception e) {
	       
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return userDTO;
	}

	public UserDTO checkStudentPassword(LoginDTO loginDTO) {
	    UserDTO userDTO = null;
	    EntityManager em = JPAUtil.getEniEntityManager();
	    try {
	        String query = "SELECT s FROM StudentEntity s WHERE s.email=:email AND s.password=:password";
	        TypedQuery<StudentEntity> queryStudent = em.createQuery(query, StudentEntity.class);
	        queryStudent.setParameter("email", loginDTO.getEmail());
	        queryStudent.setParameter("password", loginDTO.getPassword());
	        StudentEntity student = queryStudent.getSingleResult();
	        if (student != null) {
	            userDTO = new UserDTO();
	            userDTO.setId(student.getId());
	            userDTO.setName(student.getName());
	            userDTO.setEmail(student.getEmail());
	            userDTO.setPassword(student.getPassword());
	            userDTO.setRole("Student");
	        }
	    } catch (NoResultException e) {
	        
	    } catch (Exception e) {
	      
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	    return userDTO;
	}

}

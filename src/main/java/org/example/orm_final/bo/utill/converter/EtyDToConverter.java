package org.example.orm_final.bo.utill.converter;

import org.example.orm_final.entity.Course;
import org.example.orm_final.entity.Instructor;
import org.example.orm_final.entity.Lesson;
import org.example.orm_final.entity.Student;
import org.example.orm_final.entity.user.User;
import org.example.orm_final.entity.user.UserType;
import org.example.orm_final.entity.user.DtoUser;
import org.example.orm_final.entity.user.DtoUserType;
import org.example.orm_final.model.DtoCourse;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.model.DtoLesson;
import org.example.orm_final.model.DtoStudent;

import java.util.ArrayList;
import java.util.List;

public class EtyDToConverter {


    public static DtoUser getUserDto(User ety){
        DtoUser dtoUser = new DtoUser();
        dtoUser.setId(ety.getId());
        dtoUser.setUserName(ety.getUserName());
        dtoUser.setPassWold(ety.getPassWold());
        dtoUser.setUserType(ety.getUserType().equals(UserType.Admin)
                ? DtoUserType.Admin
                : DtoUserType.Receptionist);
        return dtoUser;
    }

    public static User getUserEty(DtoUser dtoUser){
        User user = new User();
        user.setId(dtoUser.getId());
        user.setUserName(dtoUser.getUserName());
        user.setPassWold(dtoUser.getPassWold());
        user.setUserType(dtoUser.getUserType().equals(DtoUserType.Admin)
                ? UserType.Admin
                : UserType.Receptionist);
        return user;
    }
    public static DtoLesson getLessonDto(Lesson lesson){
        DtoLesson dtoLesson = new DtoLesson();
        
        dtoLesson.setLessonId(lesson.getLessonId());
        dtoLesson.setLessonName(lesson.getLessonName());
        dtoLesson.setTime(lesson.getTime());
        dtoLesson.setDate(lesson.getDate());
        if(lesson.getInstructor()!=null) {
            dtoLesson.setInstructor(getInstructorDto(lesson.getInstructor()));
        }
        if(lesson.getCourse()!=null) {
            dtoLesson.setCourse(getCourseDto(lesson.getCourse()));
        }
        if(lesson.getStudent()!=null) {
            dtoLesson.setStudent(getStudentDto(lesson.getStudent()));
        }
        return dtoLesson;
    }

    public static DtoStudent getStudentDto(Student student) {
        DtoStudent dtoStudent = new DtoStudent();
        dtoStudent.setId(student.getId());
        dtoStudent.setName(student.getName());
        dtoStudent.setEmail(student.getEmail());
        dtoStudent.setPhoneNumber(student.getPhoneNumber());
        List<String> lessons = new ArrayList<>();
        List<String> courses = new ArrayList<>();
        List<String> payments = new ArrayList<>();

        student.getLessons().forEach(lesson -> {
            lessons.add(lesson.getLessonId());
        });
        dtoStudent.setLessons(lessons);

        student.getPayments().forEach(payment -> {
            payments.add(payment.getPaymentId());
        });
        dtoStudent.setPayments(payments);

        student.getCourses().forEach(course -> {
            courses.add(course.getId());
        });
        dtoStudent.setCourses(courses);

        return dtoStudent;


    }

    public static DtoCourse getCourseDto(Course course) {
        DtoCourse dtoCourse = new DtoCourse();
        dtoCourse.setId(course.getId());
        dtoCourse.setFree(course.getFree());
        dtoCourse.setDuration(course.getDuration());
        dtoCourse.setDescription(course.getDescription());
        List<String> lessonID = new ArrayList<>();
        course.getLessons().forEach(dtoLesson -> {
            lessonID.add(dtoLesson.getLessonId());
        });
        dtoCourse.setLessons(lessonID);
        return dtoCourse;
    }

    public static DtoInstructor getInstructorDto(Instructor instructor) {
        DtoInstructor dtoInstructor = new DtoInstructor();
        dtoInstructor.setId(instructor.getId());
        dtoInstructor.setEmail(instructor.getEmail());
        dtoInstructor.setName(instructor.getName());
        List<String> ids=new ArrayList<>();
        instructor.getLessons().forEach(lesson->{
            ids.add(lesson.getLessonId());
        });
        dtoInstructor.setLessons(ids);
        return dtoInstructor;
    }

    public static DtoInstructor getInstuctorDTO(Instructor instructor) {
        DtoInstructor dtoInstructor = new DtoInstructor();

        dtoInstructor.setId(instructor.getId());
        dtoInstructor.setEmail(instructor.getEmail());
        dtoInstructor.setName(instructor.getName());
        List<String> ids=new ArrayList<>();
        if(!instructor.getLessons().isEmpty()){

        }
        instructor.getLessons().forEach(lesson->{
            ids.add(lesson.getLessonId());
        });
        dtoInstructor.setLessons(ids);
        return dtoInstructor;
    }


    public static Instructor getInstructorEty(DtoInstructor dto) {
        Instructor instructor = new Instructor();

        instructor.setId(dto.getId());
        instructor.setEmail(dto.getEmail());
        instructor.setName(dto.getName());
        List<Lesson> lessons =new ArrayList<>();
        if(dto.getLessons()!= null) {
            dto.getLessons().forEach(ids -> {
                Lesson lesson = new Lesson();
                lesson.setLessonId(ids);
                lessons.add(lesson);
            });
        }
        instructor.setLessons(lessons);
        return instructor;
    }

    public static DtoCourse getCourseDTO(Course course) {
        DtoCourse dtoCourse = new DtoCourse();
        dtoCourse.setId(course.getId());
        dtoCourse.setFree(course.getFree());
        dtoCourse.setDuration(course.getDuration());
        dtoCourse.setDescription(course.getDescription());
        if(course.getLessons()!=null) {
            List<String> lessonIDs = new ArrayList<>();
            course.getLessons().forEach(lesson->{
                lessonIDs.add(lesson.getLessonId());
            });
            dtoCourse.setLessons(lessonIDs);
        }
        return dtoCourse;
    }

    public static Course getCourseEty(DtoCourse dtoCourse) {
        Course course = new Course();
        course.setId(dtoCourse.getId());
        course.setFree(dtoCourse.getFree());
        course.setDuration(dtoCourse.getDuration());
        course.setDescription(dtoCourse.getDescription());
        List<Lesson> lessonID = new ArrayList<>();
        if(dtoCourse.getLessons()!=null) {
            dtoCourse.getLessons().forEach(ids -> {
                Lesson lesson = new Lesson();
                lesson.setLessonId(ids);
            });
            course.setLessons(lessonID);
        }


        return  course;
    }



    public static Lesson getLessonEty(DtoLesson dtoLesson) {
        Lesson lesson = new Lesson();

        lesson.setLessonId(dtoLesson.getLessonId());
        lesson.setLessonName(dtoLesson.getLessonName());
        lesson.setTime(dtoLesson.getTime());
        lesson.setDate(dtoLesson.getDate());
        if(dtoLesson.getInstructor()!=null) {
            lesson.setInstructor(getInstructorEty(dtoLesson.getInstructor()));
        }
        if(dtoLesson.getCourse()!=null) {
            lesson.setCourse(getCourseEty(dtoLesson.getCourse()));
        }
        if(dtoLesson.getStudent()!=null) {
            lesson.setStudent(getStudentETY(dtoLesson.getStudent()));
        }
        return lesson;
    }

    private static Student getStudentETY(DtoStudent dtoStudent) {
        Student student=new Student();
        student.setId(dtoStudent.getId());
        student.setName(dtoStudent.getName());
        student.setEmail(dtoStudent.getEmail());
        return student;
    }

    public static Student getStudentEty(DtoStudent dtoStudent) {
        Student student = new Student();
        student.setId(dtoStudent.getId());
        student.setName(dtoStudent.getName());
        student.setEmail(dtoStudent.getEmail());
        student.setPhoneNumber(dtoStudent.getPhoneNumber());

        if(dtoStudent.getCourses()!=null) {
            List<Course> courses = new ArrayList<>();
            dtoStudent.getCourses().forEach(id -> {
                Course course = new Course();
                course.setId(id);
            });
            student.setCourses(courses);
        }
        return student;
    }
}

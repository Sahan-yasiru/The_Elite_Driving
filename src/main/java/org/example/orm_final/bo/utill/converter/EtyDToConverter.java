package org.example.orm_final.bo.utill.converter;

import org.example.orm_final.entity.Course;
import org.example.orm_final.entity.Instructor;
import org.example.orm_final.entity.user.User;
import org.example.orm_final.entity.user.UserType;
import org.example.orm_final.model.DtoCourse;
import org.example.orm_final.model.DtoInstructor;
import org.example.orm_final.model.user.DtoUser;
import org.example.orm_final.model.user.DtoUserType;

import java.util.ArrayList;
import java.util.List;

public class EtyDToConverter {

    /* ================= USER ================= */

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

    /* ================= INSTRUCTOR ================= */

    public static Instructor getInstructorEty(DtoInstructor dtoInstructor){
        Instructor instructor = new Instructor();
        instructor.setId(dtoInstructor.getId());
        instructor.setName(dtoInstructor.getName());
        instructor.setNic(dtoInstructor.getNic());


        List<Course> courses = new ArrayList<>();
        if(dtoInstructor.getCourses()!=null) {
            dtoInstructor.getCourses().forEach(dtoCourse -> {
                courses.add(getCourseEty(dtoCourse));
            });
            instructor.setCourses(courses);
        }

        return instructor;
    }

    public static DtoInstructor getInstructorDto(Instructor instructor){
        DtoInstructor dtoInstructor = new DtoInstructor();
        dtoInstructor.setId(instructor.getId());
        dtoInstructor.setName(instructor.getName());
        dtoInstructor.setNic(instructor.getNic());

        List<DtoCourse> dtoCourses = new ArrayList<>();
        if(instructor.getCourses()!=null) {
            instructor.getCourses().forEach(course -> {
                dtoCourses.add(getCourseDTO(course)); // avoid loop
            });
        }
        dtoInstructor.setCourses(dtoCourses);

        return dtoInstructor;
    }

    /* ================= COURSE ================= */

    public static DtoCourse getCourseDTO(Course course){
        DtoCourse dtoCourse = new DtoCourse();

        dtoCourse.setId(course.getId());
        dtoCourse.setDescription(course.getDescription());
        dtoCourse.setDuration(course.getDuration());
        dtoCourse.setFree(course.getFree());

        return dtoCourse;
    }

    public static Course getCourseEty(DtoCourse dtoCourse){
        Course course = new Course();

        course.setId(dtoCourse.getId());
        course.setDescription(dtoCourse.getDescription());
        course.setDuration(dtoCourse.getDuration());
        course.setFree(dtoCourse.getFree());

        return course;
    }

    /* ================= NO-LOOP HELPERS ================= */

    // Instructor without courses
    private static DtoInstructor getInstructorDto_NoLoop(Instructor instructor){
        DtoInstructor dto = new DtoInstructor();
        dto.setId(instructor.getId());
        dto.setName(instructor.getName());
        dto.setNic(instructor.getNic());
        // stop: do not add courses again
        return dto;
    }

    private static Instructor getInstructorEty_NoLoop(DtoInstructor dtoInstructor){
        Instructor instructor = new Instructor();
        instructor.setId(dtoInstructor.getId());
        instructor.setName(dtoInstructor.getName());
        instructor.setNic(dtoInstructor.getNic());
        // stop: do not add courses again
        return instructor;
    }

    // Course without instructors
    private static DtoCourse getCourseDTO_NoLoop(Course course){
        DtoCourse dto = new DtoCourse();
        dto.setId(course.getId());
        dto.setDescription(course.getDescription());
        dto.setDuration(course.getDuration());
        dto.setFree(course.getFree());
        // stop: do not add instructors again
        return dto;
    }

    private static Course getCourseEty_NoLoop(DtoCourse dtoCourse){
        Course course = new Course();
        course.setId(dtoCourse.getId());
        course.setDescription(dtoCourse.getDescription());
        course.setDuration(dtoCourse.getDuration());
        course.setFree(dtoCourse.getFree());
        // stop: do not add instructors again
        return course;
    }
}

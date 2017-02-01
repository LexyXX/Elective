package model.instances;

/**
 * Created by lexy on 02.01.17.
 */
public class ArchiveDTO {
    private int id;
    private UserDTO student;
    private CourseDTO courseDTO;
    private int mark;

    public ArchiveDTO(int id, UserDTO student, CourseDTO courseDTO, int mark) {
        this.id = id;
        this.student = student;
        this.courseDTO = courseDTO;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public UserDTO getStudent() {
        return student;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public int getMark() {
        return mark;
    }
}

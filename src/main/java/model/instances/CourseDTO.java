package model.instances;

import java.sql.Date;

/**
 * Created by lexy on 02.01.17.
 */
public class CourseDTO {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private UserDTO teacher;

    public CourseDTO(int id, String name, Date startDate, Date endDate, UserDTO teacher) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public UserDTO getTeacher() {
        return teacher;
    }

}

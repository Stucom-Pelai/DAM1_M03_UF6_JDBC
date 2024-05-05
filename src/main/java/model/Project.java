
package model;

import java.time.LocalDate;

/**
 *
 * @author Maria del Mar
 */
public class Project {
    private int code;
    private String name;
    private LocalDate date;
    private int mark;

    public Project(int code, String name, LocalDate date, int mark) {
        this.code = code;
        this.name = name;
        this.date = date;
        this.mark = mark;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Project{" + "code=" + code + ", name=" + name + ", date=" + date + ", mark=" + mark + '}';
    }
    
    
}

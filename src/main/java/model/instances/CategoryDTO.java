package model.instances;

/**
 * Created by lexy on 25.01.17.
 */
public class CategoryDTO {
    private int id;
    private String name;
    private String descr;

    public CategoryDTO(int id, String name, String descr) {
        this.id = id;
        this.name = name;
        this.descr = descr;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescr() {
        return descr;
    }
}

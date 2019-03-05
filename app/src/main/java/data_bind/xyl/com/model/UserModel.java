package data_bind.xyl.com.model;

import org.litepal.crud.LitePalSupport;


public class UserModel extends LitePalSupport {
    //id自动增长 所有要给get set 方法
    private long id;
    private String name;
    private  int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

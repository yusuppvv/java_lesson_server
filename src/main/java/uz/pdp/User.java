package uz.pdp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private int id;
    private String name;
    private String surname;
    private String phone;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

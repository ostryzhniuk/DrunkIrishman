package andrii.dto;

import andrii.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

public class UserDTO {

    private Integer id;
    private String email;
    private String name;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String address;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User convertToEntity() {
        return new ModelMapper().map(this, User.class);
    }

    public static UserDTO convertToDTO(User user) {
       return new ModelMapper().map(user, UserDTO.class);
    }

}

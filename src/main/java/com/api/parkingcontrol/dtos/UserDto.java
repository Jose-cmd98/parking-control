package com.api.parkingcontrol.dtos;

import com.api.parkingcontrol.models.UserModel;
import lombok.Data;
import org.apache.catalina.User;

@Data
public class UserDto {

    private String nome;

    private String email;

    public UserModel convertToModel(UserModel userModel){
        userModel.setNome(this.nome);
        userModel.setEmail(this.email);
        return userModel;
    }

    public UserModel convertToModel(){
        return convertToModel(new UserModel());
    }
}

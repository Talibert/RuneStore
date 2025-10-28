package com.example.runestore.mappers;

import com.example.runestore.VOs.UserVO;
import com.example.runestore.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserVoMapper{

    /**
     * Metodo para converter a entidade no VO
     * @param user
     * @return
     */
    public UserVO getVO(User user){
        return new UserVO(
                user.getNickName(),
                user.getUserRole()
        );
    }

    /**
     * Metodo para converter uma lista de entidades em uma lista de VOs
     * @param userList
     * @return
     */
    public List<UserVO> getListVO(List<User> userList){
        return userList.stream().map(this::getVO).collect(Collectors.toList());
    }
}

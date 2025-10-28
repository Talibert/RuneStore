package com.example.runestore.services;

import com.example.runestore.VOs.UserVO;
import com.example.runestore.exceptions.notfound.UserNotFoundException;
import com.example.runestore.mappers.UserVoMapper;
import com.example.runestore.repositories.UserRepository;
import com.example.runestore.types.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVoMapper userVoMapper;

    public List<UserVO> findAllWithFilter(UserRole userRole){
        List<UserVO> response;

        if(userRole != null){
            response = userVoMapper.getListVO(userRepository.findByUserRole(userRole));
        } else {
            response = userVoMapper.getListVO(userRepository.findAll());
        }

        if(response.isEmpty())
            throw new UserNotFoundException();

        return response;
    }
}

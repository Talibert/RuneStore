package com.example.employeeperformance.services;

import com.example.employeeperformance.VOs.UserVO;
import com.example.employeeperformance.exceptions.notfound.UserNotFoundException;
import com.example.employeeperformance.mappers.UserVoMapper;
import com.example.employeeperformance.repositories.UserRepository;
import com.example.employeeperformance.types.UserRole;
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

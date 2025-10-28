package com.example.runestore.VOs;

import java.util.ArrayList;
import java.util.List;

/**
 * Esse VO é para encapsular uma resposta para o método de busca de lista de funcionários
 */
public class UserListResponseVO {

    private List<UserVO> userVOList = new ArrayList<>();

    private String errorMessage;

    public UserListResponseVO() {
    }

    public UserListResponseVO(List<UserVO> userVOList, String errorMessage) {
        this.userVOList = userVOList;
        this.errorMessage = errorMessage;
    }

    public List<UserVO> getUserVOList() {
        return userVOList;
    }

    public void setUserVOList(List<UserVO> userVOList) {
        this.userVOList = userVOList;
    }

    public void addEmployeeVOList(UserVO userVO){
        this.userVOList.add(userVO);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

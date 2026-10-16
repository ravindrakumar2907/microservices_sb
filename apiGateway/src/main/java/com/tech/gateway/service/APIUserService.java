package com.tech.gateway.service;


import com.tech.gateway.bean.APIResponse;
import com.tech.gateway.bean.APIUser;
import com.tech.gateway.bean.UserRequest;
import com.tech.gateway.exception.UserNotFoundException;

public interface APIUserService {

	APIResponse getUser(String userId) throws UserNotFoundException;

	APIResponse updateUser(String userId, UserRequest userRequest) throws UserNotFoundException;

	APIResponse deleteUser(String userId) throws UserNotFoundException;

	APIResponse createUser(APIUser userRequest);

	com.tech.gateway.bean.APIResponse getUsers(int start, int size);

}

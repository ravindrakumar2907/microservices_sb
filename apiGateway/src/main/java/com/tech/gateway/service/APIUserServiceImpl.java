package com.tech.gateway.service;

import com.tech.gateway.bean.APIResponse;
import com.tech.gateway.bean.APIUser;
import com.tech.gateway.bean.UserRequest;
import com.tech.gateway.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class APIUserServiceImpl implements APIUserService {

    @Override
    public APIResponse getUser(String userId) throws UserNotFoundException {
        APIResponse resp = new APIResponse();
        APIUser user = new APIUser();
        user.setFirstName(userId);
        user.setEmail(userId + "@example.com");
        resp.setList(Collections.singletonList(user));
        resp.setStatus("SUCCESS");
        return resp;
    }

    @Override
    public APIResponse updateUser(String userId, UserRequest userRequest) throws UserNotFoundException {
        APIResponse resp = new APIResponse();
        resp.setStatus("SUCCESS");
        return resp;
    }

    @Override
    public APIResponse deleteUser(String userId) throws UserNotFoundException {
        APIResponse resp = new APIResponse();
        resp.setStatus("SUCCESS");
        return resp;
    }

    @Override
    public APIResponse createUser(APIUser userRequest) {
        APIResponse resp = new APIResponse();
        resp.setStatus("SUCCESS");
        return resp;
    }

    @Override
    public com.tech.gateway.bean.APIResponse getUsers(int start, int size) {
        APIResponse resp = new APIResponse();
        resp.setStatus("SUCCESS");
        return resp;
    }
}

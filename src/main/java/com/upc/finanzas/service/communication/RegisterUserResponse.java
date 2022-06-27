package com.upc.finanzas.service.communication;


import com.upc.finanzas.resource.UserResource;
import com.upc.finanzas.shared.domain.service.communication.BaseResponse;

public class RegisterUserResponse extends BaseResponse<UserResource> {
    public RegisterUserResponse(String message) {
        super(message);
    }

    public RegisterUserResponse(UserResource resource) {
        super(resource);
    }
}

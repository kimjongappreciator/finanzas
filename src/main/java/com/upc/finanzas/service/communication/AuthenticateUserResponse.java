package com.upc.finanzas.service.communication;

import com.upc.finanzas.resource.AuthenticateUserResource;
import com.upc.finanzas.shared.domain.service.communication.BaseResponse;

public class AuthenticateUserResponse extends BaseResponse<AuthenticateUserResource> {
    public AuthenticateUserResponse(String message) {
        super(message);
    }

    public AuthenticateUserResponse(AuthenticateUserResource resource) {
        super(resource);
    }
}

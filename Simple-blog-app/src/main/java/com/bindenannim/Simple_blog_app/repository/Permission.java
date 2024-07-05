package com.bindenannim.Simple_blog_app.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_APPROVE("admin:approve post"),
    ADMIN_APPROVE_EMPLOYEE("admin: approve employee"),
    USER_READ("management:read"),
    USER_CREATE("management:create"),

    ;

    @Getter
    private final String permission;
}

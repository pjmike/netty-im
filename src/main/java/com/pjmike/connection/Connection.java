package com.pjmike.connection;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/07
 */
@Data
@NoArgsConstructor
public class Connection {
    private String userId;
    private String username;

    public Connection(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    public String toString() {
        return userId + " : " + username;
    }
}

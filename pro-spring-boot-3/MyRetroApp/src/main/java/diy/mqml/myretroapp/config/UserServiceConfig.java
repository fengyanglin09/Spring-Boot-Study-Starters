package diy.mqml.myretroapp.config;

import lombok.Data;

@Data
public class UserServiceConfig {
    String server;
    Integer port;
    String username;
    String password;
}

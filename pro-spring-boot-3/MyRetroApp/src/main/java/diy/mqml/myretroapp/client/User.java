package diy.mqml.myretroapp.client;

import java.util.List;

public record User(String email, String name, String password, String gravatarUrl, List<UserRole> userRole, boolean active) {}

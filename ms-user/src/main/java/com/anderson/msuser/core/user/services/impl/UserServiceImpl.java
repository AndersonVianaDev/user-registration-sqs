package com.anderson.msuser.core.user.services.impl;

import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.dtos.UserResponseDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.repository.UserRepository;
import com.anderson.msuser.core.user.services.EmailService;
import com.anderson.msuser.core.user.services.PasswordEncodeService;
import com.anderson.msuser.core.user.services.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    private final PasswordEncodeService passwordEncodeService;

    private EmailService emailService;

    public UserServiceImpl(UserRepository repository, PasswordEncodeService passwordEncodeService, EmailService emailService) {
        this.repository = repository;
        this.passwordEncodeService = passwordEncodeService;
        this.emailService = emailService;
    }

    @Override
    public UserResponseDTO insert(UserDTO dto) {
        User user = new User(dto);

        String password = passwordEncodeService.encode(dto.password());
        user.setPassword(password);

        user = repository.save(user);

        emailService.sendMessage(user);

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }
}

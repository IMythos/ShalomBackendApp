package com.shalom.shalom_backend_app.user.infraestructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shalom.shalom_backend_app.user.domain.model.User;
import com.shalom.shalom_backend_app.user.domain.ports.out.UserRepositoryPort;
import com.shalom.shalom_backend_app.user.infraestructure.mapper.UserMapper;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.entity.UserEntity;
import com.shalom.shalom_backend_app.user.infraestructure.persistence.repository.UserRepository;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort{

    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saved = userRepository.save(entity);

        return UserMapper.toDomain(saved);
    }
    
}

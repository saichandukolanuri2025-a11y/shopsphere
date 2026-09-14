package Shopsphere.service;

import Shopsphere.exception.UserNotFoundException;
import Shopsphere.exception.EmailAlreadyExistsException;
import Shopsphere.dto.UserRequestDTO;
import Shopsphere.dto.UserResponseDTO;
import Shopsphere.entity.User;
import Shopsphere.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));


        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());

        return dto;
    }

public UserResponseDTO createUser(UserRequestDTO request) {

    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
    throw new EmailAlreadyExistsException("Email already registered");
}

    User user = new User();

    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setPhone(request.getPhone());

    User savedUser = userRepository.save(user);

    UserResponseDTO response = new UserResponseDTO();

    response.setId(savedUser.getId());
    response.setName(savedUser.getName());
    response.setEmail(savedUser.getEmail());
    response.setPhone(savedUser.getPhone());

    return response;
}
}
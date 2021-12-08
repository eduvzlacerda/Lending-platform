package com.lendandborrow.service;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.Role;
import com.lendandborrow.model.User;
import com.lendandborrow.model.dto.UserDTO;
import com.lendandborrow.model.enums.EnumRole;
import com.lendandborrow.repositories.ArticleRepository;
import com.lendandborrow.repositories.RoleRepository;
import com.lendandborrow.repositories.UserRepository;
import com.lendandborrow.utils.converters.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    final RoleRepository roleRepository;

    final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    final ArticleRepository articleRepository;



    public void setUserRole(EnumRole enumRole, UUID userId) {

        Role role = roleRepository.findRoleByName(enumRole.toString());

        User user = userRepository.findById(userId).orElseThrow();

        user.getRoles().add(role);

        userRepository.save(user);

    }

    public void addArticle(Article article, UUID userId) {

        User user = userRepository.findById(userId).orElseThrow();

        article.setOwner(user);

        articleRepository.save(article);

    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean loginUser(String email, String password) {
        if(email != null && password != null) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserConverter::convertUserToUserDTO)
                .collect(Collectors.toList());
    }
}

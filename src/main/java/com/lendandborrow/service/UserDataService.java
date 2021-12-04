package com.lendandborrow.service;

import com.lendandborrow.model.Article;
import com.lendandborrow.model.Role;
import com.lendandborrow.model.User;
import com.lendandborrow.model.enums.EnumRole;
import com.lendandborrow.repositories.ArticleRepository;
import com.lendandborrow.repositories.RoleRepository;
import com.lendandborrow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDataService {

    final RoleRepository roleRepository;

    final UserRepository userRepository;

    final ArticleRepository articleRepository;

    public UserDataService(
            RoleRepository roleRepository,
            ArticleRepository articleRepository,
            UserRepository userRepository) {

        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;

    }

    public void setUserRole(EnumRole enumRole, Long userId) {

        Role role = roleRepository.findRoleByName(enumRole.toString());

        User user = userRepository.findById(userId).orElseThrow();

        user.getRoles().add(role);

        userRepository.save(user);

    }

    public void addArticle(Article article, Long userId) {

        User user = userRepository.findById(userId).orElseThrow();

        article.setOwner(user);

        articleRepository.save(article);

    }



}

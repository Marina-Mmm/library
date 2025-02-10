package ru.itgirls.library.service;


import ru.itgirls.library.model.UserOfLibrary;
import ru.itgirls.library.security.Role;

public interface UserOfLibraryService {

    UserOfLibrary createUserOfLibrary(String username, String password, Role userRole);

}

package Dress.app.Mappers;

import Dress.app.Models.User;
import Dress.app.Requests.CreateUserRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.UUID;

@Component
public class CreateUserReqToUser {
    public User Map(CreateUserRequest request) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(request.firstName);
        user.setLastName(request.lastName);
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(request.password);
        return user;
    }
}

package tr.com.readingisgood.apigateway.service;


import tr.com.readingisgood.apigateway.model.domain.User;

public interface UserService {
    User getUserByUserName(String userName);
}

package tr.com.readingisgood.orderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.readingisgood.orderservice.model.domain.User;
import tr.com.readingisgood.orderservice.repository.UserRepository;
import tr.com.readingisgood.orderservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}

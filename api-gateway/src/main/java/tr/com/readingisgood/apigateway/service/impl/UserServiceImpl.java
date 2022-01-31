package tr.com.readingisgood.apigateway.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.readingisgood.apigateway.model.domain.User;
import tr.com.readingisgood.apigateway.repository.UserRepository;
import tr.com.readingisgood.apigateway.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).stream().findFirst().orElse(null);
    }
}

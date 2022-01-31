package tr.com.readingisgood.userservice.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.readingisgood.userservice.exception.BaseException;
import tr.com.readingisgood.userservice.exception.UserAlreadyExistsException;
import tr.com.readingisgood.userservice.exception.UserNotExistException;
import tr.com.readingisgood.userservice.model.domain.User;
import tr.com.readingisgood.userservice.model.dto.userservice.UserDto;
import tr.com.readingisgood.userservice.repository.UserRepository;
import tr.com.readingisgood.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void createUser(User user) throws BaseException {
        if (user.getId() != null && userRepository.findById(user.getId()).isPresent()) {
            logger.debug("The user already exists! => username: " + user.getUserName());
            throw new UserAlreadyExistsException("The user already exists!");
        }
        userRepository.save(user);
        logger.info("The user was created! => username: " + user.getUserName());
    }

    @Override
    public void updateUser(User user) throws BaseException {
        if (userRepository.findById(user.getId()).isEmpty() || userRepository.findByUserName(user.getUserName()) == null) {
            logger.debug("The user not exist! => username: " + user.getUserName());
            throw new UserNotExistException("The user not exist!");
        }
        userRepository.save(user);
        logger.info("The user was updated! => username: " + user.getUserName());
    }

    @Override
    public void deleteUser(User user) throws BaseException {
        if (userRepository.findById(user.getId()).isEmpty() || userRepository.findByUserName(user.getUserName()) == null) {
            logger.debug("The user not exist! => username: " + user.getUserName());
            throw new UserNotExistException("The user not exist!");
        }
        userRepository.delete(user);
        logger.info("The user was deleted! => username: " + user.getUserName());
    }

    @Override
    public User getUserByUserName(String userName) throws BaseException {
        return userRepository.findByUserName(userName).stream().findFirst().orElse(null);
    }

    @Override
    public User transformUserFromDto(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return user;
    }
}

package com.roommate.basecontrol.controllers.restControllers;

import com.roommate.basecontrol.model.convertors.implementation.UserDTOtoUserEntity;
import com.roommate.basecontrol.model.convertors.implementation.UserEntityToUserDTO;
import com.roommate.basecontrol.model.dto.UserDTO;
import com.roommate.basecontrol.repository.entities.Group;
import com.roommate.basecontrol.repository.entities.User;
import com.roommate.basecontrol.service.api.GroupService;
import com.roommate.basecontrol.service.api.UserService;
import com.roommate.basecontrol.utils.exceptions.DAOException;
import com.roommate.basecontrol.utils.exceptions.GroupNotFoundException;
import com.roommate.basecontrol.utils.exceptions.UserNotFoundException;
import com.roommate.basecontrol.utils.security.PasswordChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Artem Karnov @date 07.04.17.
 *         artem.karnov@t-systems.com
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserEntityToUserDTO userToUserDTO;

    @Autowired
    private UserDTOtoUserEntity userDTOtoUser;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/getUserByCredentials", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserByCredentials(HttpServletRequest req,
                                                        @RequestParam(value = "email") String email,
                                                        @RequestParam(value = "password") String password) {
        try {
            User user = userService.getUserByEMAil(email);

            if (PasswordChecker.check(user.getPassword(), password)) {
                UserDTO result = userToUserDTO.convert(user);
                return new ResponseEntity(result, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (UserNotFoundException ex) {
            logger.warn("User " + email + " required by " + req.getHeader("service") + " wasn't found.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getUserList(HttpServletRequest req,
                                                     @RequestParam(value = "emails") List<String> emails) {
        // TODO: 08.04.17 to optimize with named query
        // TODO: 08.04.17 make feature that allow recognize not fully existing
        List<User> users = new ArrayList();
        for (String currentEmail : emails) {
            try {
                users.add(userService.getUserByEMAil(currentEmail));
            } catch (UserNotFoundException ex) {
                logger.warn("User " + currentEmail + " required by " + req.getHeader("service") + " wasn't found.");
            }
        }
        List<UserDTO> userDTOS = userToUserDTO.convertList(users);
        return new ResponseEntity<List<UserDTO>>(userDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserDTO> getUserByEmail(HttpServletRequest req,
                                                  @RequestParam(value = "email") String email) {
        try {
            User user = userService.getUserByEMAil(email);
            UserDTO result = userToUserDTO.convert(user);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            logger.warn("User " + email + " required by " + req.getHeader("service") + " wasn't found.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/persistUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity persisUser(HttpServletRequest req,
                                     @RequestParam(value = "user") UserDTO userDTO,
                                     @RequestParam(value = "password") String password) {
        // TODO: 08.04.17 make this shit secure and safety
        try {
            User user = userDTOtoUser.convert(userDTO);
            user.setPassword(PasswordChecker.cryptPassword(password));
            userService.createEntity(user);
        } catch (DAOException ex) {
            logger.warn("User " + userDTO.getEmail() + " required by " + req.getHeader("service") + " already exists.");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/persistUsers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity persistUsers(HttpServletRequest req,
                                       @RequestParam(value = "users") Map<UserDTO, String> usersMap) {
        // TODO: 08.04.17 make feature that allow recognize not fully persistence
        for (UserDTO currentUser : usersMap.keySet()) {
            try {
                User user = userDTOtoUser.convert(currentUser);
                user.setPassword(PasswordChecker.cryptPassword(usersMap.get(currentUser)));
                userService.createEntity(user);
            } catch (DAOException ex) {
                logger.warn("User " + currentUser.getEmail() + " required by " + req.getHeader("service") + " already exists.");

            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/addToGroup", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity userToGroup(HttpServletRequest req,
                                      @RequestParam(value = "email") String userEmail,
                                      @RequestParam(value = "groupTitle") String groupTitle) {
        Group group;
        User user;
        try {
            user = userService.getUserByEMAil(userEmail);
        } catch (UserNotFoundException ex) {
            logger.warn("User " + userEmail + " required by " + req.getHeader("service") + " wasn't found.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        try {
            group = groupService.getGroupByTitle(groupTitle);
        } catch (GroupNotFoundException ex) {
            logger.warn("Group " + groupTitle + " required by " + req.getHeader("service") + " wasn't found.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        user.addGroup(group);
        userService.updateEntity(user);
        return new ResponseEntity(HttpStatus.OK);
    }

}

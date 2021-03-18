package edu.miu.itravel.service;

import edu.miu.itravel.model.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    User createUser( User user );

    User updateUser( User user ) throws ResourceNotFoundException;

    User updatePatchUser( User user );

    List< User > getAllUsers( int page, int size);

    User getUserById( long userId ) throws ResourceNotFoundException;

    void deleteUserById( long id );


}

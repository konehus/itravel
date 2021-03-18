package edu.miu.itravel.service;

import edu.miu.itravel.model.User;
import edu.miu.itravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return userRepository.save( user );
    }


    @Override
    public User updateUser(User user) throws ResourceNotFoundException{

        Optional< User > userDb = this.userRepository.findById( user.getId() );

        if( userDb.isPresent() ){
            User userUpdate = userDb.get();
            userUpdate.setId( user.getId() );
            userUpdate.setFirstName(user.getFirstName());
            userUpdate.setLastName(user.getLastName());
            userUpdate.setGender(user.getGender());
            userUpdate.setAddress(user.getAddress() );
            userUpdate.setEmail(user.getEmail() );
            userUpdate.setPassword(user.getPassword());
            userUpdate.setPosts(user.getPosts());
            userRepository.save( userUpdate );
            return userUpdate;
        }else{
            throw new ResourceNotFoundException("User not found with id: " + user.getId() );
        }
    }


    @Override
    public User updatePatchUser(User user) {
        return null;
    }


    @Override
    public List<User> getAllUsers(int page, int size) {
        //Pageable sortedByPriceDesc = PageRequest.of(0, 3, Sort.by("price").descending());
        //Pageable sortedByPriceDescNameAsc = PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));

        Pageable pageWitSize = PageRequest.of(page, size);
        Page< User > userPage= this.userRepository.findAll( pageWitSize );
        return userPage.getContent();
    }


    @Override
    public User getUserById(long userId) {

        Optional < User > userDb = this.userRepository.findById( userId );

        if(userDb.isPresent() ){
            return userDb.get();
        }else {
            throw new ResourceNotFoundException( "User not found with id: " + userId );
        }
    }


    @Override
    public void deleteUserById(long userId) {
        User user = this.userRepository.findById( userId )
                .orElseThrow( () -> new ResourceNotFoundException( "User not found with id : " + userId ));
        userRepository.delete( user );
    }
}

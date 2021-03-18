package edu.miu.itravel.service;

import edu.miu.itravel.model.Post;
import edu.miu.itravel.model.User;
import edu.miu.itravel.repository.PostRepository;
import edu.miu.itravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService{

    PostRepository postRepository;
    UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save( post );
    }

    @Override
    public Post updatePost(Post post) {

        Optional< Post > postDb = this.postRepository.findById( post.getId() );

        if( postDb.isPresent() ){
            Post postUpdate = postDb.get();
            postUpdate.setId( post.getId() );
            postUpdate.setDetails( post.getDetails() );
            postUpdate.setLikes( post.getLikes() );
            postUpdate.setPostDateTime( post.getPostDateTime() );
            postUpdate.setComments( post.getComments() );
            postRepository.save( postUpdate );
            return postUpdate;
        }else{
            throw new ResourceNotFoundException("Resource not found with id: " + post.getId() );
        }
    }


    @Override
    public Post updatePatchPost(Post post) {
        return null;
    }


    @Override
    public List<Post> getAllPosts(int page, int size) {
        Pageable pageWitSize = PageRequest.of(page, size);
        Page<Post> postsPage = this.postRepository.findAll( pageWitSize );
        return postsPage.getContent();
    }


    @Override
    public Post getPostById(long postId) {
        Optional < Post > postDb = this.postRepository.findById( postId );

        if(postDb.isPresent() ){
            return postDb.get();
        }else {
            throw new ResourceNotFoundException( "Resource not found with id: " + postId );
        }
    }


    @Override
    public void deletePostById(long postId) {
        Optional < Post > postDb = this.postRepository.findById( postId );

        if( postDb.isPresent() ){
            this.postRepository.delete( postDb.get() );
        } else {
            throw new ResourceNotFoundException( "Resource not found with id : " + postId );
        }
    }


    @Override
    public Post createUserPost(Long userId, Post post) {
        User user = userService.getUserById(userId);
        post.setUser(user);
        post.setPostDateTime(LocalDateTime.now());
        return postRepository.save( post );
    }

}

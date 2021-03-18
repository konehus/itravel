package edu.miu.itravel.controller;

import edu.miu.itravel.controller.exhandlers.BindingErrorsException;
import edu.miu.itravel.model.Post;
import edu.miu.itravel.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api")
public class PostController {
    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts(
            @RequestParam(value = "page", defaultValue = "0") @Digits(integer = Integer.MAX_VALUE, fraction = 0) int page,
            @RequestParam(value = "size", defaultValue = "3") @Digits(integer = 50, fraction = 0) int size
    ) {
        if(this.postService.getAllPosts(page, size).isEmpty())
            throw new ResourceNotFoundException("Posts Data is Empty!");
        return ResponseEntity.status(HttpStatus.FOUND).body( this.postService.getAllPosts(page, size));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "id")  @Min(value = 1) Long postId ){
        return ResponseEntity.ok().body(this.postService.getPostById(postId));
    }

    @PostMapping("/posts")
    public ResponseEntity< Post > createPost(@RequestBody @Valid Post post,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new BindingErrorsException(bindingResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.createPost(post));
    }

    @PutMapping( "/posts/{id}")
    public ResponseEntity< Post > updatePost(@PathVariable(value = "id") @Min(value = 1) Long postId,
                                             @RequestBody Post post,
                                             BindingResult bindingResult ){
        if(bindingResult.hasErrors())
            throw new BindingErrorsException(bindingResult);
        post.setId( postId );
        return ResponseEntity.ok().body(this.postService.updatePost(post));
    }

    @DeleteMapping( "/posts/{id}")
    public ResponseEntity<?> deletePost( @PathVariable(value = "id") @Min(value = 1) Long postId){
        this.postService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }



    //////////////////////////////SUB_SOURCES///////////////////////////


    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<?> createUserPost(@RequestBody @Valid Post post,
                                            @PathVariable(value = "userId") @Min(value = 1) Long userId,
                                            BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new BindingErrorsException(bindingResult);

        return ResponseEntity.status(HttpStatus.CREATED).body("Post successfully added for user with id: " + userId);
    }


    @PutMapping( "/users/{userId}/posts/{postId}")
    public ResponseEntity< Post > updateUserPost(@PathVariable(value = "id") @Min(value = 1) Long postId,
                                             @RequestBody Post post,
                                             BindingResult bindingResult ){
        if(bindingResult.hasErrors())
            throw new BindingErrorsException(bindingResult);
        post.setId( postId );
        return ResponseEntity.ok().body(this.postService.updatePost(post));
    }
}

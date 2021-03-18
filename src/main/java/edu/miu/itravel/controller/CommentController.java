package edu.miu.itravel.controller;

import edu.miu.itravel.controller.exhandlers.BindingErrorsException;
import edu.miu.itravel.model.Comment;
import edu.miu.itravel.service.CommentService;
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
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("/comments")
    public ResponseEntity<?> getAllComments(
            @RequestParam(value = "page", defaultValue = "0") @Digits(integer = Integer.MAX_VALUE, fraction = 0) int page,
            @RequestParam(value = "size", defaultValue = "3") @Digits(integer = 50, fraction = 0) int size
    ) {
        if(this.commentService.getAllComments(page, size).isEmpty())
            throw new ResourceNotFoundException("Comments Data is Empty!");
        return ResponseEntity.status(HttpStatus.FOUND).body( this.commentService.getAllComments(page, size));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id")  @Min(value = 1) Long commentId ){
        return ResponseEntity.ok().body(this.commentService.getCommentById(commentId));
    }

    @PostMapping("/comments")
    public ResponseEntity< Comment > createComment(@RequestBody @Valid Comment comment,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new BindingErrorsException(bindingResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.createComment(comment));
    }

    @PutMapping( "/comments/{id}")
    public ResponseEntity< Comment > updateComment(@PathVariable(value = "id") @Min(value = 1) Long commentId,
                                             @RequestBody Comment comment,
                                             BindingResult bindingResult ){
        if(bindingResult.hasErrors())
            throw new BindingErrorsException(bindingResult);
        comment.setId( commentId );
        return ResponseEntity.ok().body(this.commentService.updateComment(comment));
    }

    @DeleteMapping( "/comments/{id}")
    public ResponseEntity<?> deleteComment( @PathVariable(value = "id") @Min(value = 1) Long commentId){
        this.commentService.deleteCommentById(commentId);
        return ResponseEntity.noContent().build();
    }
}

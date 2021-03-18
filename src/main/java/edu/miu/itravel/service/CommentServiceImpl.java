package edu.miu.itravel.service;


import edu.miu.itravel.model.Comment;
import edu.miu.itravel.repository.CommentRepository;
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
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save( comment );
    }

    @Override
    public Comment updateComment(Comment comment) {

        Optional< Comment > commentDb = this.commentRepository.findById( comment.getId() );

        if( commentDb.isPresent() ){
            Comment commentUpdate = commentDb.get();
            commentUpdate.setId( comment.getId() );
            commentUpdate.setComment(comment.getComment() );
            commentUpdate.setDateCreated( comment.getDateCreated() );
            commentRepository.save( commentUpdate );
            return commentUpdate;
        }else{
            throw new ResourceNotFoundException("Resource not found with id: " + comment.getId() );
        }
    }


    @Override
    public Comment updatePatchComment(Comment comment) {
        return null;
    }


    @Override
    public List<Comment> getAllComments(int page, int size) {
        Pageable pageWitSize = PageRequest.of( page, size);
        Page<Comment> commentsPage= this.commentRepository.findAll( pageWitSize );
        return commentsPage.getContent();
    }


    @Override
    public Comment getCommentById(long commentId) {
        Optional < Comment > commentDb = this.commentRepository.findById( commentId );

        if(commentDb.isPresent() ){
            return commentDb.get();
        }else {
            throw new ResourceNotFoundException( "Resource not found with id: " + commentId );
        }
    }


    @Override
    public void deleteCommentById(long commentId) {
        Optional < Comment > commentDb = this.commentRepository.findById( commentId );

        if( commentDb.isPresent() ){
            this.commentRepository.delete( commentDb.get() );
        } else {
            throw new ResourceNotFoundException( "Resource not found with id : " + commentId );
        }
    }
}

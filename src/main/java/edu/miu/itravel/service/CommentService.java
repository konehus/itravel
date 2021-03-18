package edu.miu.itravel.service;

import edu.miu.itravel.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment( Comment comment);

    Comment updateComment( Comment comment);

    Comment updatePatchComment( Comment comment );

    List< Comment > getAllComments(int page, int size);

    Comment getCommentById( long commentId );

    void deleteCommentById ( long commentId);

}

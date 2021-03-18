package edu.miu.itravel.service;

import edu.miu.itravel.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post );

    Post createUserPost(Long userId, Post post);

    Post updatePost(Post post );

    Post updatePatchPost( Post post );

    List< Post > getAllPosts(int page, int size);

    Post getPostById( long postId );

    void deletePostById( long postId );

}

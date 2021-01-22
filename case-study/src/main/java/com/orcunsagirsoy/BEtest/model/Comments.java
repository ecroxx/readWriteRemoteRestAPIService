package com.orcunsagirsoy.BEtest.model;

import java.io.Serializable;

public class Comments implements Serializable {
    

    private Long postId;
    private String body;
    private Long id;

    public Comments() {}

    public Comments(Long id, String body, Long postId) {
        super();
        this.id = id;
        this.body = body;
        this.postId = postId;
    }


    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}   

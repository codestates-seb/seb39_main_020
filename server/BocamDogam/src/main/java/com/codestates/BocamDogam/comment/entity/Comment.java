package com.codestates.BocamDogam.comment.entity;

import com.codestates.BocamDogam.audit.BaseTimeEntity;
import com.codestates.BocamDogam.member.entity.Member;
import com.codestates.BocamDogam.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column
    private int like_count = 0;

    @Column
    private boolean reported;

    @Column
    private boolean block;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}

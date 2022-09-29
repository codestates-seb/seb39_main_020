package com.codestates.BocamDogam.question.entity;

import com.codestates.BocamDogam.audit.BaseTimeEntity;
import com.codestates.BocamDogam.member.entity.Member;
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
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private int view = 0;

    @Column
    private int like_count = 0;

    @Column
    private boolean reported;

    @Column
    private boolean block;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

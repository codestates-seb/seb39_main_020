package com.codestates.BocamDogam.post.entity;

import com.codestates.BocamDogam.audit.BaseTimeEntity;
import com.codestates.BocamDogam.institute.entity.Institute;
import com.codestates.BocamDogam.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Board board;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private int view = 0;

    @Column(name = "like_count")
    private int likeCount = 0;

    @Column
    private boolean reported;

    @Column
    private boolean block;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public enum Board {
        ALL("전체"),
        FREE("자유"),
        WANNABE("지망생"),
        STUDENT("수강생"),
        GRADUATE("수료생");

        private String name;

        private Board(String name) {

            this.name = name;
        }

        public String getName() {

            return this.name;

        }
    }
}

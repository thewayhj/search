package com.api.search.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Description;

@Entity
@Getter
@Setter
@Description("메뉴")
@Table(name="KEYWORD_LIST", indexes = @Index(name="KEYWORD_INFO", columnList = "KEYWORD"))
public class SearchEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //@Description("SEQUENCE")
    @Column(name="SEQ", unique = true, nullable=false)
    private int menuId;

    //@Description("검색어")
    @Column(name="KEYWORD", nullable=false)
    private String keyword;

    //@Description("검색 횟수")
    @Column(name="TOTAL_COUNT", nullable=false)
    private int totalCount;
}
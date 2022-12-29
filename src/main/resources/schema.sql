--
-- KEYWORD_LIST
--
DROP TABLE IF EXISTS KEYWORD_LIST;

CREATE TABLE KEYWORD_LIST COMMENT '키워드 리스트' (
    SEQ             INT             NOT NULL    AUTO_INCREMENT  COMMENT 'SEQUENCE',
    KEYWORD         VARCHAR(30)     NOT NULL                    COMMENT '검색어',
    TOTAL_COUNT     INT             NOT NULL                    COMMENT '검색 횟수',
    PRIMARY KEY(SEQ)
);

--
-- KEYWORD_HISTORY
--
DROP TABLE IF EXISTS KEYWORD_HISTORY;

CREATE TABLE KEYWORD_HISTORY COMMENT '키워드 히스토리' (
    SEQ             INT                                     NOT NULL    AUTO_INCREMENT  COMMENT 'SEQUENCE',
    KEYWORD         VARCHAR(30)                             NOT NULL                    COMMENT '검색어',
    CREATED_AT      TIMESTAMP   DEFAULT CURRENT_TIMESTAMP   NOT NULL                    COMMENT '조회 날짜',
    PRIMARY KEY(SEQ)
);
import { Container, Typography } from "@mui/material";
import { Link as RouterLink, useOutletContext } from "react-router-dom";
import React from "react";
import { MainListBox } from "../../components/mainListComponent";
import { BoardTab } from "../../components/boardTabComponent";

/* 게시판 목록 페이지 */
const BoardMain = () => {

    return (
            <Container>
                <Typography variant="h5">커뮤니티</Typography>
                <BoardTab curr="ALL"/>
                
                <Typography variant="h6">자유게시판</Typography>
                <MainListBox typ="board" datas={[]}/>

                <Typography variant="h6">지망생</Typography>
                <MainListBox typ="board" datas={[]}/>

                <Typography variant="h6">수강생</Typography>
                <MainListBox typ="board" datas={[]}/>

                <Typography variant="h6">수료생</Typography>
                <MainListBox typ="board" datas={[]}/>
            </Container>
    );
}

export default React.memo(BoardMain);
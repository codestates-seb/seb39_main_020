import { Container, Typography } from "@mui/material";
import React, { useEffect } from "react";
import { MainListBox } from "../../components/mainListComponent";
import { BoardTab } from "../../components/boardTabComponent";
import { useDispatch, useSelector } from "react-redux";
import { fetchBoardMain } from "../../reducers/boardReducer";
import { MainTitleText } from "../../components/commonComponent";

/* 게시판 목록 페이지 */
const BoardMain = () => {

    const {boardMain} = useSelector((state) => state.board );
    
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(fetchBoardMain());
    },[dispatch])

    return (
            <Container>
                <MainTitleText>커뮤니티</MainTitleText>
                <BoardTab curr="ALL"/>
                
                <Typography variant="h6">자유게시판</Typography>
                <MainListBox typ="board" div="FREE" datas={boardMain.FREE}/>

                <Typography variant="h6">지망생</Typography>
                <MainListBox typ="board" div="WANNABE" datas={boardMain.WANNABE}/>

                <Typography variant="h6">수강생</Typography>
                <MainListBox typ="board" div="STUDENT" datas={boardMain.STUDENT}/>

                <Typography variant="h6">수료생</Typography>
                <MainListBox typ="board" div="GRADUATE" datas={boardMain.GRADUATE}/>
            </Container>
    );
}

export default React.memo(BoardMain);
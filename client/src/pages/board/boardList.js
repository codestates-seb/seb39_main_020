import { Button, Container, Grid, Link, Table, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import { Link as RouterLink, useOutletContext, useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchBoards, initList } from "../../reducers/boardReducer";
import PagingLayout from "../../components/pagingComponent";
import { BoardTab } from "../../components/boardTabComponent";
import { MainTitleText } from "../../components/commonComponent";

/* 게시판 목록 페이지 */
const Board = () => {

    const params = useParams();
    const {list : {data, pageInfo}, error, loading} = useSelector((state) => state.board);
    
    /* 공통 오류/로딩 처리 시작 */
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => (!!error) && snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    //페이징처리 시작
    //const [boardOffset, setBoardOffset] = useState(paging?.offset || 0);
    const [page, setPage] = useState(pageInfo?.page || 1);
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchBoards({board:params.board, page, size:10}))
        return () => {
            dispatch(initList());
        }
    },[dispatch, page, params.board]);
    //페이징처리 끝
 
    
    return (
            <Container>
                <MainTitleText>커뮤니티</MainTitleText>
                <BoardTab curr={params.board} setPage={setPage}/>

                <Grid container>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>제목</TableCell>
                                <TableCell>작성자</TableCell>
                                <TableCell>작성일</TableCell>
                                <TableCell>추천수</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {(!!data) ? (data).map((item,idx) => {
                            return <TableRow key={idx}>
                                <TableCell>
                                    <Link component={RouterLink} to={`/board/${params.board}/${item.post_id}`}>{item.title}</Link>
                                </TableCell>
                                <TableCell>{item.member_id}</TableCell>
                                <TableCell>{item.created_date}</TableCell>
                                <TableCell>{item.regdate}</TableCell>                                                                 
                            </TableRow>
                            }) : <TableRow><TableCell colSpan={4}>검색된 결과가 없습니다.</TableCell></TableRow>
                            }
                        </TableBody>
                                    
                    </Table>
                </Grid>
                <Grid container justifyContent="center" sx={{mt:2}}>
                    <PagingLayout paging={pageInfo} setter={setPage} />
                </Grid>
                <Grid container justifyContent="flex-end" sx={{mt:3}}>
                    <Button variant="outlined" component={RouterLink} to={`/boardForm/${params.board}`}>등록</Button>
                </Grid>
            </Container>
    );
}

export default React.memo(Board);
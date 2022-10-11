import { Button, Card, CardContent, Container, Grid, Link, Table, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import { Link as RouterLink, useOutletContext } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchQuestions } from "../../reducers/qnaReducer";
import PagingLayout from "../../components/pagingComponent";
import { MainTitleText } from "../../components/commonComponent";

const Qna = () => {

    const {list : {data, pageInfo}, error, loading} = useSelector((state) => state.qna);
    /* 공통 오류/로딩 처리 시작 */
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => (!!error) && snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    //페이징처리 시작
    const [page, setPage] = useState(pageInfo?.page || 1)

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchQuestions({page, size: 10}))
    },[dispatch, page]);
    //페이징처리 끝
    
    return (
            <Container>
                <MainTitleText>질문/답변</MainTitleText>
                <Card>
                    <CardContent>
                        <Grid container>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>제목</TableCell>
                                        <TableCell>작성일</TableCell>
                                        <TableCell>추천수</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {(!!data) ? (data).map((item,idx) => {
                                    return <TableRow key={idx}>
                                        <TableCell>
                                            <Link component={RouterLink} to={`/qna/${item.questionId}`}>{item.title}</Link>
                                        </TableCell>
                                        <TableCell>{item.createdDate}</TableCell>
                                        <TableCell>{item.likeCount}</TableCell>                                                                 
                                    </TableRow>
                                    }) : <TableRow><TableCell colSpan={4}>검색된 결과가 없습니다.</TableCell></TableRow>
                                    }
                                </TableBody>
                            </Table>
                        </Grid>
                    </CardContent>
                </Card>
                <Grid container justifyContent="center" sx={{mt:2}}>
                    <PagingLayout paging={pageInfo} setter={setPage} />
                </Grid>
                <Grid container justifyContent="flex-end" sx={{mt:3}}>
                    <Button variant="outlined" component={RouterLink} to="/qnaForm">질문하기</Button>
                </Grid>
            </Container>
    );
}

export default Qna;
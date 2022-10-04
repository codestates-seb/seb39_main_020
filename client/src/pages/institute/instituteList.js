import { Button, Container, Grid, Link, Table, TableBody, TableCell, TableFooter, TableHead, TableRow, Typography } from "@mui/material";
import { useLocation, Link as RouterLink, useOutletContext } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchInstitutes } from "../../reducers/instituteReducer";
import PagingLayout from "../../components/pagingComponent";
import { RatingStar } from "../../components/starComponent";
import { StarBorder } from '@mui/icons-material';

/* 게시판 목록 페이지 */
const Institute = () => {

    const {list : {items, paging}, error, loading} = useSelector((state) => state.institute);
    
    /* 공통 오류/로딩 처리 시작 */
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => (!!error) && snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    //페이징처리 시작
    const [boardOffset, setBoardOffset] = useState(paging?.offset || 0)

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchInstitutes({offset:boardOffset, limit:10}))
    },[dispatch, boardOffset]);
    //페이징처리 끝
    
    return (
            <Container>
                <Typography variant="h5">교육기관정보</Typography>

                <Grid container>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>이름</TableCell>
                                <TableCell>과정</TableCell>
                                <TableCell>기관 평점</TableCell>
                                <TableCell>접수마감일</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {(!!items) ? (items).map((item,idx) => {
                            return <TableRow key={idx}>
                                <TableCell>
                                    <Link component={RouterLink} to={`/board/${item.id}`}>{item.title}</Link>
                                </TableCell>
                                <TableCell>{item.writer}</TableCell>
                                <TableCell>
                                    <RatingStar rate={3}/>
                                </TableCell>
                                <TableCell>{item.regdate}</TableCell>                                                                 
                            </TableRow>
                            }) : <TableRow><TableCell colSpan={4}>검색된 결과가 없습니다.</TableCell></TableRow>
                            }
                        </TableBody>
                        <TableFooter>
                            <TableRow >
                                <TableCell colSpan={4} align="center">
                                    <PagingLayout paging={paging} setter={setBoardOffset} /> 
                                </TableCell>
                            </TableRow>
                        </TableFooter>
                    </Table>
                </Grid>
            </Container>
    );
}

export default React.memo(Institute);
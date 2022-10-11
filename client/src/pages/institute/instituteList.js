import { Container, Grid, Link, Table, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import { Link as RouterLink, useOutletContext } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchInstitutes } from "../../reducers/instituteReducer";
import PagingLayout from "../../components/pagingComponent";
import { RatingStar } from "../../components/starComponent";
import { MainTitleText } from "../../components/commonComponent";

/* 게시판 목록 페이지 */
const Institute = () => {

    const {list : {data, pageInfo}, error, loading} = useSelector((state) => state.institute);
    
    /* 공통 오류/로딩 처리 시작 */
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => (!!error) && snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    //페이징처리 시작
    const [page, setPage] = useState(pageInfo?.page || 1)

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchInstitutes({page, size:10}))
    },[dispatch, page]);
    //페이징처리 끝
    
    return (
            <Container>
                <MainTitleText>교육기관정보</MainTitleText>

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
                            {(!!data) ? (data).map((item,idx) => {
                            return <TableRow key={idx}>
                                <TableCell>
                                    <Link component={RouterLink} to={`/institute/INSTITUTE/${item.instituteId}`}>{item.name}</Link>
                                </TableCell>
                                <TableCell>{item.description}</TableCell>
                                <TableCell>
                                    <RatingStar rate={item.score}/>
                                </TableCell>
                                <TableCell>{item.createDate}</TableCell>                                                                 
                            </TableRow>
                            }) : <TableRow><TableCell colSpan={4}>검색된 결과가 없습니다.</TableCell></TableRow>
                            }
                        </TableBody>
                    </Table>
                </Grid>
                <Grid container justifyContent="center" sx={{mt:2}}>
                    <PagingLayout paging={pageInfo} setter={setPage} />
                </Grid>
            </Container>
    );
}

export default React.memo(Institute);
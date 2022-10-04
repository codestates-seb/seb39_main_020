import {
  Button,
  Card,
  CardContent,
  Container,
  Grid,
  Link,
  Table,
  TableBody,
  TableCell,
  TableFooter,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import { Link as RouterLink, useOutletContext } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchFaqs } from "../../reducers/faqReducer";
import PagingLayout from "../../components/pagingComponent";

const Faq = () => {
  const {
    list: { items, paging },
    error,
    loading,
  } = useSelector((state) => state.faq);
  /* 공통 오류/로딩 처리 시작 */
  const { snackbar, loader } = useOutletContext();
  useEffect(() => loader.setLoading(loading), [loader, loading]);
  useEffect(() => !!error && snackbar.open(error, "danger"), [error, snackbar]);
  /* 공통 오류/로딩 처리 끝 */

  //페이징처리 시작
  const [boardOffset, setBoardOffset] = useState(paging?.offset || 0);

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchFaqs({ offset: boardOffset, limit: 10 }));
  }, [dispatch, boardOffset]);
  //페이징처리 끝

  return (
    <Container>
      <Typography variant="h5">질문/답변</Typography>
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
                {!!items ? (
                  items.map((item, idx) => {
                    return (
                      <TableRow key={idx}>
                        <TableCell>
                          <Link component={RouterLink} to={`/qna/${item.id}`}>
                            {item.title}
                          </Link>
                        </TableCell>
                        <TableCell>{item.regdate}</TableCell>
                        <TableCell>{item.regdate}</TableCell>
                      </TableRow>
                    );
                  })
                ) : (
                  <TableRow>
                    <TableCell colSpan={4}>검색된 결과가 없습니다.</TableCell>
                  </TableRow>
                )}
              </TableBody>
              <TableFooter>
                <TableRow>
                  <TableCell colSpan={4} align="center">
                    <PagingLayout paging={paging} setter={setBoardOffset} />
                  </TableCell>
                </TableRow>
              </TableFooter>
            </Table>
          </Grid>
        </CardContent>
      </Card>
      <Grid container justifyContent="flex-end">
        <Button variant="outlined" component={RouterLink} to="/boardForm">
          질문하기
        </Button>
      </Grid>
    </Container>
  );
};

export default Faq;

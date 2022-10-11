import { Container, Card, CardContent, Table, TableBody, TableRow, TableCell, Link } from "@mui/material";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useOutletContext, useParams } from "react-router-dom";
import { MainTitleText } from "../../components/commonComponent";
import { InstituteTab } from "../../components/instituteTabComponent";
import { RatingStar } from "../../components/starComponent";
import { fetchInstitute, initView } from "../../reducers/instituteReducer";

export default function InstituteView() {
    
    const dispatch = useDispatch();
    const {view, loading, error} = useSelector((state) => state.institute);

    /* 공통 오류/로딩 처리 시작*/
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    //현재 게시판 타입 받아오기
    const { id } = useParams();

    useEffect(() => {
        dispatch(fetchInstitute({id}));
        return () => {dispatch(initView())}
    },[dispatch, id]);

    return (
        <Container>
            <MainTitleText>교육기관정보</MainTitleText>
            <InstituteTab curr="INSTITUTE" id={id}/>

            <Container>
                <Card>
                    <CardContent>
                        <Table>
                            <TableBody>
                                <TableRow>
                                    <TableCell>운영기업</TableCell>
                                    <TableCell>{view.name}</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>기업규모</TableCell>
                                    <TableCell>{view.employee}명</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>교육기관평점</TableCell>
                                    <TableCell><RatingStar rate={view.score}/></TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>수업방식</TableCell>
                                    <TableCell>{view.study_type}</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>홈페이지</TableCell>
                                    <TableCell>
                                        {view.homepage &&
                                        <Link target="_new" href={view.homepage}>홈페이지</Link>
                                        }
                                    </TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>교육장 위치</TableCell>
                                    <TableCell>{view.location}</TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>
                    </CardContent>
                </Card>
            </Container>
        </Container>
    )
}
import { Container, Card, CardContent, Table, TableBody, TableRow, TableCell } from "@mui/material";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useOutletContext, useParams } from "react-router-dom";
import { MainTitleText } from "../../components/commonComponent";
import { InstituteTab } from "../../components/instituteTabComponent";
import { fetchCourses, initCourses } from "../../reducers/instituteReducer";

export default function CourseView() {
    
    const dispatch = useDispatch();
    const {courses, loading, error} = useSelector((state) => state.institute);
    
    /* 공통 오류/로딩 처리 시작 */
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => (!!error) && snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    const { id } = useParams();

    useEffect(() => {
        dispatch(fetchCourses({id}));
        return () => {dispatch(initCourses())}
    },[dispatch, id]);

    return (
        <Container>
            <MainTitleText>수강과정정보</MainTitleText>
            <InstituteTab curr="COURSE" id={id}/>

            <Container>
                <Card>
                    <CardContent>
                        {(courses.map) && courses.map((course) => {
                        return <Table sx={{marginBottom:"25px"}}>
                            <TableBody>
                                <TableRow>
                                    <TableCell>코스안내</TableCell>
                                    <TableCell>{course.name}</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>커리큘럼</TableCell>
                                    <TableCell>{course.category}</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>모집인원</TableCell>
                                    <TableCell>{course.scale||0}명</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>모집기간</TableCell>
                                    <TableCell>{course.apply_start} ~ {course.apply_end}</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>수업기간</TableCell>
                                    <TableCell>{course.start_date} ~ {course.end_date}</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>교육기관</TableCell>
                                    <TableCell>{course.period||0}개월</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>교육비용</TableCell>
                                    <TableCell>{course.cost}원</TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell>국비지원여부</TableCell>
                                    <TableCell>{course.support === 'Y' ? "국비지원" : "미지원"}</TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>  
                        })}
                    </CardContent>
                </Card>
            </Container>
        </Container>
    )
}
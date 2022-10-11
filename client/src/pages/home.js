import { Container, Grid, Typography } from "@mui/material";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useOutletContext } from "react-router-dom";
import { MainListBox, MainSchedule } from "../components/mainListComponent";
import { fetchMainBoards } from "../reducers/boardReducer";
import { fetchMainCourses, fetchMainInstitutes } from "../reducers/instituteReducer";
import { fetchMainQuestions } from "../reducers/qnaReducer";

const Home = () => {
    
    const MainTitle = (props) => {
        return <Typography align="center" variant="h6" sx={{mt:5, mb: 3, fontWeight:"bold"}}>
            {props.children}
            </Typography>;
    }

    const {mainCourses, mainInstitutes, error, loading} = useSelector((state) => {
        return state.institute;
    });
    const {mainBoard} = useSelector((state) => state.board);
    const {mainQuestions} = useSelector((state) => state.qna);

    /* 공통 오류/로딩 처리 시작 */
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => (!!error) && snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchMainInstitutes());
        dispatch(fetchMainBoards());
        dispatch(fetchMainCourses());
        dispatch(fetchMainQuestions());
    },[dispatch]);

    useEffect(() => {

    });

    //페이징처리 끝

    return (
        <Container >
            <MainTitle>부트캠프 개강일정</MainTitle>
            <Grid container spacing={0}>
                <Grid item xs={6}>
                    <MainSchedule course={mainCourses&&mainCourses[0]}/>
                </Grid>
                <Grid item xs={6}>
                <MainSchedule course={mainCourses&&mainCourses[1]}/>
                </Grid>
            </Grid>

            <MainTitle>교육기관/부트캠프</MainTitle>
            <Grid container spacing={3}>
                <Grid item xs={4}>
                    <MainListBox typ="institute" datas={mainInstitutes&&mainInstitutes.slice?mainInstitutes.slice(0,4):[]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="institute" datas={mainInstitutes&&mainInstitutes.slice?mainInstitutes.slice(4,8):[]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="institute" datas={mainInstitutes&&mainInstitutes.slice?mainInstitutes.slice(8,12):[]}/> 
                </Grid>
            </Grid>

            <MainTitle>커뮤니티</MainTitle>
            <Grid container spacing={3}>
                <Grid item xs={4}>
                    <MainListBox typ="board" datas={mainBoard&&mainBoard.slice?mainBoard.slice(0,4):[]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="board" datas={mainBoard&&mainBoard.slice?mainBoard.slice(4,8):[]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="board" datas={mainBoard&&mainBoard.slice?mainBoard.slice(8,12):[]}/> 
                </Grid>
            </Grid>

            <MainTitle>질문/답변</MainTitle>
            <Grid container spacing={3}>
                <Grid item xs={4}>
                    <MainListBox typ="qna" datas={mainQuestions&&mainQuestions.slice?mainQuestions.slice(0,4):[]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="qna" datas={mainQuestions&&mainQuestions.slice?mainQuestions.slice(4,8):[]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="qna" datas={mainQuestions&&mainQuestions.slice?mainQuestions.slice(8,12):[]}/> 
                </Grid>
            </Grid>
        </Container>
    )
}

export default Home;
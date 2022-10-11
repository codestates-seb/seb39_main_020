import { Button, Container, Divider, Card, CardContent, List, ListItem, Grid } from "@mui/material";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { useOutletContext, useParams } from "react-router-dom";
import { MainTitleText } from "../../components/commonComponent";
import { FormHidden, FormSelect, FormTextArea } from "../../components/formComponents";
import { InstituteTab } from "../../components/instituteTabComponent";
import { RatingStar, StarRating } from "../../components/starComponent";
import { fetchCourses, fetchReviews, initCourses, initReviews, postReview } from "../../reducers/instituteReducer";

export default function ReviewView() {
    
    const params = useParams();
    const { handleSubmit, control, setValue } = useForm();
    const dispatch = useDispatch();
    const {reviews, courses, loading, error} = useSelector((state) => state.institute);
    const { id } = useParams();
    
    /* 공통 오류/로딩 처리 시작*/
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    const [rate,setRate] = useState(0);

    useEffect(() => {
        dispatch(fetchReviews({id}));
        dispatch(fetchCourses({id}));
        return () => {
            dispatch(initReviews());
            dispatch(initCourses());            
        }
    },[dispatch, params, id]);

    const getCourseOptions = courses.map((c) => {
        return {value : c.courseId, text: c.name}
    });

    const onSubmit= (data) => {
        dispatch(postReview({
            id,
            data,
            successFn:()=>{
                dispatch(fetchReviews({id}));
                snackbar.open("등록을 성공하였습니다.");
            }
        }));
    }

    useEffect(() => {
        setValue("rating", rate)
    },[setValue, rate]);

    return (
        <Container>
            <MainTitleText>수강후기</MainTitleText>
            <InstituteTab curr="REVIEW" id={id}/>

            <Container>
                <Card>
                    <CardContent>
                        <List>
                            {(reviews.map) && reviews.map((review)=>{
                            return <><ListItem key={review.reviewId}>
                                <Grid container>
                                    <Grid item xs={9}>{review.member} ({review.course})</Grid> 
                                    <Grid item xs={3}alignContent="right"><RatingStar rate={review.score}/></Grid>
                                    <Grid item xs={12} md={6} xl={6}>{review.summary}</Grid>
                                </Grid>
                            </ListItem>
                            <Divider/></>
                            })}
                        </List>
                    </CardContent>
                </Card>
                <Card>
                    <CardContent>
                    <form name="bform" onSubmit={handleSubmit(onSubmit)} noValidate>
                        <FormHidden control={control} name="memberId" defaultValue="test" />
                        <FormHidden control={control} name="rating" defaultValue={rate} value={rate}/>
                        <FormSelect control={control} label="과목" name="course" options={getCourseOptions}></FormSelect> 
                        <FormTextArea 
                            control={control} 
                            fullWidth 
                            name="content" 
                            placeholder="후기 작성 시 로그인 + 인증이 필요합니다."
                        />
                        <StarRating rate={rate} setRate={setRate}/>
                        <Button type="submit">등록</Button>
                    </form>
                    </CardContent>
                    
                </Card>
            </Container>
        </Container>
    )
}
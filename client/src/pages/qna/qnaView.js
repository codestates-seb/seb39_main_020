import { Button, Container, Divider, Card, Typography, CardContent, Grid } from "@mui/material";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { useOutletContext, useParams } from "react-router-dom";
import { MainTitleText } from "../../components/commonComponent";
import { FormHidden, FormTextArea } from "../../components/formComponents";
import { fetchAnswers, fetchQuestion, initView, postAnswer } from "../../reducers/qnaReducer";

export default function QnaView() {
    const { handleSubmit, control } = useForm();
    const params = useParams();

    const dispatch = useDispatch();
    const {view, answers, loading, error} = useSelector((state) => state.qna);

    /* 공통 오류/로딩 처리 시작*/
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    const onSubmit= (data) => {
        dispatch(postAnswer({
            id: params.id,
            data,
            successFn:()=>{
                dispatch(fetchAnswers({id:params.id}));
                snackbar.open("등록을 성공하였습니다.");
            }
        }));
    }

    useEffect(() => {
        dispatch(fetchQuestion({id:params.id}));
        return () => {dispatch(initView())}
    },[dispatch, params]);

    useEffect(() => {
        if(!!view) dispatch(fetchAnswers({id:params.id}));
    },[dispatch, view, params.id]);

    return (
        <Container>
            <MainTitleText>질문/답변</MainTitleText>
            
            <Container>
                <Card>
                    <CardContent>
                        <div style={{display:"flex", marginBottom:"10px"}}>
                            <Typography variant="h5" color="red" noWrap>
                                Q. 
                            </Typography>
                            <Typography variant="h5">{view.title}</Typography>
                        </div>
                        <Grid container sx={{display:"flex", mb:3, minHeight:"30px"}}>
                            <Typography variant="body1" component="pre" >{view.content}</Typography>
                        </Grid>
                        <Typography variant="subtitle1">{view.memberId}</Typography>
                        <Typography variant="subtitle2">{view.createdDate}</Typography> 
                        <Button variant="outlined" size="small">추천</Button>
                        <Button variant="outlined" size="small">신고</Button>
                        <Divider sx={{margin:"10px 0"}}/>
                        {answers.map((a)=>{
                        return <>
                            <div style={{display:"flex", marginBottom:"10px"}}>
                            <Typography variant="h5" color="red" noWrap>
                                A. 
                            </Typography>
                            <Typography variant="h5">{a.content}</Typography>
                            </div>
                            <Typography variant="subtitle2">{a.createdDate}</Typography> 
                            <Divider sx={{margin:"10px 0"}}/>
                        </>;
                        })}
                    </CardContent>
                </Card>

                <Card sx={{mt:3}}>
                    <CardContent>
                    <form name="bform" onSubmit={handleSubmit(onSubmit)} noValidate>
                        <FormHidden control={control} name="memberId" defaultValue="test" /> 
                        <FormTextArea 
                            control={control} 
                            fullWidth 
                            name="content" 
                            placeholder="후기 작성 시 로그인 + 인증이 필요합니다."
                            rules={{required:"내용을 입력하세요."}}
                        />
                        <Grid container justifyContent="flex-end" sx={{mt:2}}>
                            <Button variant="outlined" type="submit">등록</Button>
                        </Grid>

                    </form>
                    </CardContent>
                </Card>
            </Container>
        </Container>
    )
}
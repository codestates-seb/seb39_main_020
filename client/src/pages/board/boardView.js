import { Button, Container, Divider, Card, Typography, CardContent, Grid } from "@mui/material";
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { Link as RouterLink, useOutletContext, useParams } from "react-router-dom";
import { BoardTab } from "../../components/boardTabComponent";
import { MainTitleText } from "../../components/commonComponent";
import { FormHidden, FormTextArea } from "../../components/formComponents";
import { fetchBoard, fetchComments, initComments, initView, postBoard, postComments } from "../../reducers/boardReducer";

export default function BoardView() {
    
    const params = useParams();
    const { handleSubmit, control } = useForm();

    const dispatch = useDispatch();
    const {view, comments, loading, error} = useSelector((state) => state.board);

    /* 공통 오류/로딩 처리 시작*/
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    const onSubmit= (data) => {
        dispatch(postComments({
            board: params.board,
            id: params.id,
            data,
            successFn:()=>{
                dispatch(fetchComments({board:params.board, id:params.id}));
                snackbar.open("등록을 성공하였습니다.");
            }
        }));
    }

    useEffect(() => {
        dispatch(fetchBoard({board:params.board,id:params.id}));
        dispatch(fetchComments({board:params.board,id:params.id}));
        return () => {
            dispatch(initView());
            dispatch(initComments());
        }
    },[dispatch, params.board, params.id]);

    return (
        <Container>
            <MainTitleText>커뮤니티</MainTitleText>
            <BoardTab curr={params.board}/>
            
            <Container>
                <Card>
                    <CardContent>
                        <Typography variant="h5" sx={{mb:2}}>{view.title}</Typography>
                        <Grid container justifyContent="space-between">
                            <Grid>
                                <Typography variant="subtitle2">{view.memberId}</Typography>
                                <Typography variant="subtitle1">{view.createdDate}</Typography>
                            </Grid>
                            <Grid>
                            <Typography variant="h5"><ThumbUpIcon/> 0</Typography>
                            </Grid>
                        </Grid>
                        
                        <Divider sx={{margin:"5px 0"}}/>
                        <Typography variant="body1" component="pre" sx={{mb:3, minHeight:"50px"}}>{view.content}</Typography>
                        <Button variant="outlined" size="small" sx={{mr:1}}>도움됐어요</Button>
                        <Button variant="outlined" size="small">신고하기</Button>
                        <Divider sx={{margin:"10px 0"}}/>
                        {comments.map((comment) => { 
                            return <>
                                <Typography variant="body2" sx={{mb:0.5}}>{comment.memberId}</Typography>
                                <Typography variant="body1" sx={{mb:2, minHeight:"25px"}}>{comment.content}</Typography>
                                <Grid container>
                                    <Typography sx={{mr:3}} variant="subtitle2">{comment.createdDate}</Typography>
                                    <Typography sx={{mr:3}} variant="subtitle2">추천 {comment.likeCount||0}</Typography>
                                    <Typography variant="subtitle2">신고</Typography>
                                </Grid>
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
                            placeholder="댓글을 남겨보세요."
                        />
                        <Grid container justifyContent="flex-end" sx={{mt:2}}>
                            <Button variant="outlined" type="submit">등록</Button>
                        </Grid>

                    </form>
                    </CardContent>
                </Card>
                <Grid container justifyContent="flex-end" sx={{mt:3}} >
                    <Grid sx={{mr:2}}>
                        <Button variant="outlined" component={RouterLink} to={`/boardForm/${params.board}/${params.id}`}>수정</Button>
                    </Grid>
                    <Grid>
                        <Button variant="outlined" component={RouterLink} to={`/board/${params.board}`}>목록</Button>
                    </Grid>
                </Grid>
            </Container>
        </Container>
    )
}
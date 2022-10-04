import { Button, Container, Divider, Card, Typography, CardContent } from "@mui/material";
import QueryString from "qs";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import { BoardTab } from "../../components/boardTabComponent";
import { deleteBoard, fetchBoard, initView } from "../../reducers/boardReducer";

export default function FaqView() {
    
    const params = useParams();
    const navigate = useNavigate();

    const dispatch = useDispatch();
    const {view, loading} = useSelector((state) => state.board);

    useEffect(() => {
        dispatch(fetchBoard({id:params.id}));
        return () => {dispatch(initView())}
    },[dispatch, params]);

    return (
        <Container>
            <Typography variant="h5">질문/답변</Typography>
            
            <Container>
                <Card>
                    <CardContent>
                        <div style={{display:"flex", marginBottom:"10px"}}>
                        <Typography variant="h5" color="red" noWrap>
                            Q. 
                        </Typography>
                        <Typography variant="h5">{view.title}</Typography>
                        </div>
                        <Typography variant="body2">{view.content}</Typography>

                        <Typography variant="subtitle1">{view.writer}</Typography>
                        <Typography variant="subtitle1">{view.regdate}</Typography> 
                        <Button variant="outlined" size="small">추천</Button>
                        <Button variant="outlined" size="small">신고</Button>
                        <Divider sx={{margin:"10px 0"}}/>
                        <div style={{display:"flex", marginBottom:"10px"}}>
                        <Typography variant="h5" color="red" noWrap>
                            A. 
                        </Typography>
                        <Typography variant="h5">{view.title}</Typography>
                        </div>
                        
                        <Typography variant="subtitle2">{view.regdate}</Typography> 
                        
                    </CardContent>
                </Card>
            </Container>
            {/*<Button component={Link} to={`/boardForm/${params.id}`}>수정</Button>
            <Button onClick={() => {navigate("/board"); dispatch(deleteBoard({id:params.id}))}}>삭제</Button>
    <Button component={Link} to={`/board`}>리스트로</Button>*/}
        </Container>
    )
}
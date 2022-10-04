import { Button, Container, Divider, Card, Typography, CardContent } from "@mui/material";
import QueryString from "qs";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import { BoardTab } from "../../components/boardTabComponent";
import { deleteBoard, fetchBoard, initView } from "../../reducers/boardReducer";

export default function BoardView() {
    
    const params = useParams();
    const navigate = useNavigate();

    const dispatch = useDispatch();
    const {view, loading} = useSelector((state) => state.board);

    //현재 게시판 타입 받아오기
    const location = useLocation();
    const query = QueryString.parse(location.search, {
        ignoreQueryPrefix: true
    });

    useEffect(() => {
        dispatch(fetchBoard({id:params.id}));
        return () => {dispatch(initView())}
    },[dispatch, params]);

    return (
        <Container>
            <Typography variant="h5">커뮤니티</Typography>
            <BoardTab curr={query.type}/>
            
            <Container>
                <Card>
                    <CardContent>
                        <Typography variant="h5">{view.title}</Typography>
                        <Typography variant="subtitle2">{view.writer}</Typography>
                        <Typography variant="subtitle1">{view.regdate}</Typography>
                        <Divider sx={{margin:"5px 0"}}/>
                        <Typography variant="body2">{view.content}</Typography>
                        <Button variant="outlined" size="small">도움됐어요</Button>
                        <Button variant="outlined" size="small">신고하기</Button>
                        <Divider sx={{margin:"10px 0"}}/>
                        {/* 댓글 컴포넌트 작업예정 */}
                    </CardContent>
                </Card>
            </Container>
            {/*<Button component={Link} to={`/boardForm/${params.id}`}>수정</Button>
            <Button onClick={() => {navigate("/board"); dispatch(deleteBoard({id:params.id}))}}>삭제</Button>
    <Button component={Link} to={`/board`}>리스트로</Button>*/}
        </Container>
    )
}
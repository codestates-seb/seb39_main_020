import Home from "../pages/home";
import Layout from "../layout/layout";
import Institute from "../pages/institute/instituteList";
import InstituteView from "../pages/institute/instituteView";
import CourseView from "../pages/institute/courseView";
import ReviewView from "../pages/institute/reviewView";
import BoardMain from "../pages/board/boardMain";
import Board from "../pages/board/boardList";
import BoardView from "../pages/board/boardView";
import BoardForm from "../pages/board/boardForm";
import Qna from "../pages/qna/qna";
import QnaView from "../pages/qna/qnaView";
import QnaForm from "../pages/qna/qnaForm";

const routes = [{
    path: "/",
    element: <Layout />,
    children: [
    {
        index: true,
        element : <Home />
    },
    { 
        path: "home",
        name: "Home",
        icon: "accessibility",
        element : <Home />
    },
    { 
        path: "institute",
        name: "교육기관정보",
        icon: "accessibility",
        visible: true,
        element : <Institute />
    },
    {
        path: "institute/INSTITUTE/:id",
        name: "교육기관정보",
        element : <InstituteView />
    },
    {
        path: "institute/COURSE/:id",
        name: "수강과정정보",
        element : <CourseView />
    },
    {
        path: "institute/REVIEW/:id",
        name: "수강후기",
        element : <ReviewView />
    },
    {
        path: "boardForm/:board",
        name: "게시판등록",
        element : <BoardForm />
    },
    {
        path: "boardForm/:board/:id",
        name: "게시판수정",
        element : <BoardForm />
    },
    {
        path: "boardMain",
        name: "커뮤니티",
        icon: "notes",
        visible: true,
        element : <BoardMain />
    },
    {
        path: "board/:board/:id",
        name: "커뮤니티",
        visible: false,
        element : <BoardView />
    },
    {
        path: "board/:board",
        name: "게시판",
        visible: false,
        element : <Board/>
    },
    {
        path: "qna",
        name: "질문/답변",
        visible: true,
        icon: "question_answer",
        element : <Qna />
    },
    {
        path: "qna/:id",
        name: "질문/답변",
        element : <QnaView />
    },{
        path: "qnaForm",
        name: "질문/답변",
        element : <QnaForm />
    },]
}];

export default routes;
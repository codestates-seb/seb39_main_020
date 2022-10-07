import Home from "../pages/home";
import Institute from "../pages/institute/instituteList";
import Board from "../pages/board/boardList";
import Faq from "../pages/faq/faq";
import BoardView from "../pages/board/boardView";
import Layout from "../layout/layout";
import BoardForm from "../pages/board/boardForm";
import BoardMain from "../pages/board/boardMain";
import FaqView from "../pages/faq/faqView";
import Login from "../pages/User/login";
import Register from "../pages/User/register";

const routes = [
  {
    path: "/",
    element: <Layout />,
    children: [
      {
        index: true,
        element: <Home />,
      },
      {
        path: "/",
        name: "Home",
        icon: "accessibility",
        element: <Home />,
      },
      {
        path: "main/institutes",
        name: "교육기관정보",
        icon: "accessibility",
        visible: true,
        element: <Institute />,
      },
      {
        path: "boardForm/",
        name: "게시판등록",
        element: <BoardForm />,
      },
      {
        path: "boardForm/:id",
        name: "게시판수정",
        element: <BoardForm />,
      },
      {
        path: "board",
        name: "게시판",
        visible: false,
        element: <Board />,
      },
      {
        path: "community",
        name: "커뮤니티",
        icon: "notes",
        visible: true,
        element: <BoardMain />,
      },
      {
        path: "board/:id",
        name: "커뮤니티",
        element: <BoardView />,
      },
      {
        path: "main/qna",
        name: "질문/답변",
        visible: true,
        icon: "question_answer",
        element: <Faq />,
      },
      {
        path: "main/qna/:id",
        name: "질문/답변",
        element: <FaqView />,
      },
      {
        path: "login",
        name: "로그인",
        element: <Login />,
      },
      {
        path: "register",
        name: "회원가입",
        element: <Register />,
      },
    ],
  },

];

export default routes;

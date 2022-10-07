import {
  Box,
  Button,
  Card,
  Container,
  Divider,
  Grid,
  List,
  ListItem,
  ListItemText,
  Typography,
} from "@mui/material";
import { MainListBox, MainSchedule } from "../components/mainListComponent";

const MainCard = (props) => {
  return <Card sx={{ borderRadius: "0px" }}>{props.children}</Card>;
};

const Home = () => {
  return (
    <Container>
      <Typography align="center" variant="h6">
        부트캠프 개강일정
      </Typography>
      <Grid container spacing={0}>
        <Grid item xs={6}>
          <MainSchedule
            course={{
              category: "백엔드(웹)",
              title: "멀티잇(Multi_IT) 웹 서비스 백엔드 개발자 양성",
              description: `'웹 서비스 백엔드 양성'과정은 JAVA기반의 백엔드~
                    `,
            }}
          />
        </Grid>
        <Grid item xs={6}>
          <MainCard>2</MainCard>
        </Grid>
      </Grid>

      <Typography variant="h6">교육기관/부트캠프</Typography>
      <Grid container spacing={3}>
        <Grid item xs={4}>
          <MainListBox
            typ="institutes"
            datas={[
              { name: "코드스테이츠" },
              { name: "바닐라코딩" },
              { name: "패스트캠퍼스" },
              { name: "카카오클라우드스쿨" },
            ]}
          />
        </Grid>
        <Grid item xs={4}>
          <MainListBox
            typ="institutes"
            datas={[
              { name: "플레이데이터" },
              { name: "야곰아카데미" },
              { name: "메가스터디아카데미" },
              { name: "스파르타코딩클럽" },
            ]}
          />
        </Grid>
        <Grid item xs={4}>
          <MainListBox
            typ="institutes"
            datas={[
              { name: "우아한테크코스" },
              { name: "항해99" },
              { name: "영카데미" },
              { name: "구름" },
            ]}
          />
        </Grid>
      </Grid>

      <Typography variant="h6">커뮤니티</Typography>
      <Grid container spacing={3}>
        <Grid item xs={4}>
          <MainListBox
            typ="board"
            datas={[
              { title: "불안해할 시간에 더 깊이 몰입하여 공부하세요." },
              { title: "취업 분명히 하실 수 있습니다 화이팅!" },
              { title: "하나의 프로젝트를 잘 살명하는게 매력적인 답변" },
              { title: "다시 돌아간다면 조금 무리해서라도 제가 원" },
            ]}
          />
        </Grid>
        <Grid item xs={4}>
          <MainListBox
            typ="board"
            datas={[
              { title: "다른사람의 프로젝트를 클론 코딩 하는 것이 아니라" },
              { title: "기술 하나하나의 선택에 나만의 스토리를" },
              { title: "그 후부터는 서류는 합격했던 것 같아요." },
              {
                title: "처음에는 프로젝트 위주의 코딩을 하다가 기본기의 ",
              },
            ]}
          />
        </Grid>
        <Grid item xs={4}>
          <MainListBox
            typ="board"
            datas={[
              { title: "혹시 개발자를 간절하게 꿈꾸진 않지만, 취업의" },
              {
                title: "결국 부트캠프도 서칭 비용을 줄여줄 뿐 공부하는",
              },
              { title: "특히 창업을 준비하는 개발자 시라면..." },
              {
                title: "본인의 상황과 본인의 장단점에 가장 맞는 회사가",
              },
            ]}
          />
        </Grid>
      </Grid>

      <Typography variant="h6">질문/답변</Typography>
      <Grid container spacing={3}>
        <Grid item xs={4}>
          <MainListBox
            typ="qna"
            datas={[{ title: "test" }, { title: "test2" }]}
          />
        </Grid>
        <Grid item xs={4}>
          <MainListBox
            typ="qna"
            datas={[
              { title: "test" },
              { title: "test2" },
              { title: "test3" },
              { title: "test4" },
            ]}
          />
        </Grid>
        <Grid item xs={4}>
          <MainListBox typ="qna" datas={[]} />
        </Grid>
      </Grid>
    </Container>
  );
};

export default Home;
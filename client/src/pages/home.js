import { Box, Button, Card, Container, Divider, Grid, List, ListItem, ListItemText, Typography } from "@mui/material";
import { MainListBox, MainSchedule } from "../components/mainListComponent";

const MainCard = (props) => {
    return <Card sx={{borderRadius:"0px"}}>
        {props.children}
    </Card>;
}

const Home = () => {
    return (
        <Container >
            <Typography align="center" variant="h6" >부트캠프 개강일정</Typography>
            <Grid container spacing={0}>
                <Grid item xs={6}>
                    <MainSchedule course={{category:"풀스택(웹)", title:"LOL게임 빅데이터 활용 시각화 양성과정 3기", description:`blablabla
                        blablabla
                        blablabla
                        blablabla
                        blablabla
                    `}}/>
                </Grid>
                <Grid item xs={6}>
                    <MainCard>2</MainCard>
                </Grid>
            </Grid>

            <Typography variant="h6">교육기관/부트캠프</Typography>
            <Grid container spacing={3}>
                <Grid item xs={4}>
                    <MainListBox typ="institutes" datas={[{name:"test"},{name:"test2"}]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="institutes" datas={[{name:"test"},{name:"test2"}]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="institutes" datas={[]}/> 
                </Grid>
            </Grid>

            <Typography variant="h6">커뮤니티</Typography>
            <Grid container spacing={3}>
                <Grid item xs={4}>
                    <MainListBox typ="board" datas={[{title:"test"},{title:"test2"}]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="board" datas={[{title:"test"},{title:"test2"},{title:"test3"},{title:"test4"},{title:"test5"}]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="board" datas={[]}/> 
                </Grid>
            </Grid>

            <Typography variant="h6">질문/답변</Typography>
            <Grid container spacing={3}>
                <Grid item xs={4}>
                    <MainListBox typ="qna" datas={[{title:"test"},{title:"test2"}]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="qna" datas={[{title:"test"},{title:"test2"},{title:"test3"},{title:"test4"},{title:"test5"}]}/> 
                </Grid>
                <Grid item xs={4}>
                    <MainListBox typ="qna" datas={[]}/> 
                </Grid>
            </Grid>
        </Container>
    )
}

export default Home;
import { Card, CardContent, Chip, Divider, Grid, List, ListItem, ListItemText, Typography } from "@mui/material"

const mainScheduleSize = 2;
const mainListSize = 4;

const getBoardProps = (typ) => {
    //공통프로퍼티
    let idProp, titleProp, hasIcon, hasComments;
    //게시판용프로퍼티
    let boardProp;
    switch(typ) {
        case "institutes" :
            idProp="institute_id";
            titleProp="name";
            hasIcon=true;
            hasComments=false; 
            break;
        case "board" : 
            idProp="post_id";
            boardProp="board"
            titleProp="title";
            hasIcon=true;
            hasComments=false; 
            break;
        case "qna" :
            idProp="question_id";
            titleProp="title";
            hasIcon=true;
            hasComments=false; 
            break;
    }

    return {
        idProp,titleProp,hasIcon,hasComments,boardProp
    };
}

export const MainCard = (props) => {
    return <Card sx={{borderRadius:"0px"}}>
        {props.children}
    </Card>;
}

export const MainSchedule = ({course}) => {
    //0,1,2
    return <MainCard>
        <CardContent>
            <Chip variant="outlined" label={course?.category}></Chip>
            <Typography variant="h6" component="div">{course?.title}</Typography>
            <Typography sx={{ mb: 1.5 }} color="text.secondary">
            {course?.description}
            </Typography>
            <Grid>
                <Grid xs={3}>개강일</Grid>
                <Grid xs={9}>{course?.start_date}</Grid>
            </Grid>
            <Grid>
                <Grid xs={3}>위치</Grid>
                <Grid xs={9}>{course?.location}</Grid>
            </Grid>
            <Grid>
                <Grid xs={3}>교육기간</Grid>
                <Grid xs={9}>{course?.start_date} ~ {course?.end_date}</Grid>
            </Grid>
        </CardContent>
    </MainCard>
}

export const MainListBox = ({typ, datas}) => {
    datas = (!!datas.map)?datas:[];
    const config = getBoardProps(typ);
    const dataCnt = datas.length;

    let emptyArr = [];
    for(let i= dataCnt; i< mainListSize; i++) {
        emptyArr.push({});
    }
    return (<Card>
            <List component="nav" aria-label="mailbox folders">
            { 
            datas.map((data, idx) => {
                const item = (
                    (idx < mainListSize-1) ? (
                    <> 
                        <ListItem button>
                            <ListItemText primary={data[config.titleProp]} />
                        </ListItem>
                        <Divider light variant="middle"/>
                    </>) : (
                        <ListItem button>
                            <ListItemText primary={data[config.titleProp]} />
                        </ListItem>
                    )
                );
                return item;
            })
            }
            { 
            emptyArr.map((v, idx) => {
                const item = (
                    (dataCnt+idx < mainListSize-1) ? (
                    <>
                        <ListItem button>
                            <ListItemText sx={{minHeight:"24px"}} key={idx} primary=""/>
                        </ListItem>
                        <Divider light variant="middle"/>
                    </>) : (
                        <ListItem button>
                            <ListItemText sx={{minHeight:"24px"}} key={idx} primary=""/>
                        </ListItem>
                    )
                );
                return item;
            })  
            }
        </List>
    </Card>);
}

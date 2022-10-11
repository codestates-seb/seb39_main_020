import { Card, CardContent, Chip, Divider, Grid, List, ListItem, ListItemText, Typography } from "@mui/material"
import { Link as RouterLink } from 'react-router-dom'
const mainListSize = 4;

const getBoardProps = (typ) => {
    //공통프로퍼티
    let idProp, titleProp, hasIcon, hasComments;
    //게시판용프로퍼티
    let boardProp;
    switch(typ) {
        case "institute" :
            idProp="instituteId";
            titleProp="name";
            hasIcon=true;
            hasComments=false; 
            break;
        case "board" : 
            idProp="postId";
            boardProp="board"
            titleProp="title";
            hasIcon=false;
            hasComments=true; 
            break;
        case "qna" :
            idProp="questionId";
            titleProp="title";
            hasIcon=true;
            hasComments=false; 
            break;
        default : break;
    }

    return {
        idProp,titleProp,hasIcon,hasComments,boardProp
    };
}

export const MainCard = (props) => {
    return <Card sx={{borderRadius:"0px", minHeight:"200px"}}>
        {props.children}
    </Card>;
}

export const MainSchedule = ({course}) => {
    //0,1,2
    return (course) ? <MainCard>
        <CardContent>
            <Grid container justifyContent="flex-end">
                <Chip size="small" variant="outlined" label={course?.category}></Chip>
            </Grid>
            <Typography variant="h5" component="div" sc={{mb:1.4}}>{course?.name}</Typography>
            <Typography sx={{ mb: 2 }} color="text.secondary">
            {course?.description}
            </Typography>
            <Grid container spacing={0}>
                <Grid xs={3}>개강일</Grid>
                <Grid xs={9}>{course?.startDate}</Grid>
            </Grid>
            <Grid container spacing={0}>
                <Grid xs={3}>위치</Grid>
                <Grid xs={9}>{course?.location}</Grid>
            </Grid>
            <Grid container spacing={0}>
                <Grid xs={3}>교육기간</Grid>
                <Grid xs={9}>{course?.startDate} ~ {course?.endDate}</Grid>
            </Grid>
        </CardContent>
    </MainCard> : <MainCard></MainCard>
}

export const MainListBox = ({typ, datas}) => {
    datas = (!!datas?.map)?datas:[];
    const config = getBoardProps(typ);
    const dataCnt = datas.length;
    const getLink = (data) => {
        let link = '';
        if (typ === 'board') {
            link = `/board/${data[config.boardProp]}/${data[config.idProp]}`
        } else if(typ === 'institute') {
            link = `/${typ}/INSTITUTE/${data[config.idProp]}`
        } else if(typ === 'qna'){
            link = `/${typ}/${data[config.idProp]}`
        }
        return link;
    }

    let emptyArr = [];
    for(let i= dataCnt; i< mainListSize; i++) {
        emptyArr.push({});
    }

    return (
        <Card>
            <List component="nav" aria-label="mailbox folders">
            { 
            datas.map((data, idx) => {
                const prefix = (config.boardProp)?`[${data[config.boardProp]}]`:'';
                const title = data[config.titleProp];
                const suffix = (config.hasComments)?`[${data['commentCount']?data['commentCount']:0}]`:'';
                const item = (
                    (idx < mainListSize) && (
                        (idx < mainListSize-1) ? (
                        <> 
                            <ListItem button key={data[config.idProp]} component={RouterLink} to={getLink(data)} >
                                <ListItemText key={idx} primary={`${prefix} ${title} ${suffix}`}/> 
                            </ListItem>
                            <Divider light variant="middle"/>
                        </>) : (
                            <ListItem button key={data[config.idProp]} component={RouterLink} to={getLink(data)} >
                                 <ListItemText key={idx} primary={`${prefix} ${title} ${suffix}`}/> 
                            </ListItem>
                        )
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
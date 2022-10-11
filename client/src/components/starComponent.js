import { StarBorder } from '@mui/icons-material';
import StarIcon from '@mui/icons-material/Star';
import { Grid } from '@mui/material';

export const RatingStar = ({rate}) => {
    let starArr = [0,1,2,3,4];
    return (
        <Grid container>
        {
            starArr.map((v,i) => {
                return (v < rate) ?
                <StarIcon key ={i}/>
                :
                <StarBorder key = {i}/>
            })
        }
        </Grid>
    )
}

export const StarRating = ({rate,setRate}) => {
    const starArr = [0,1,2,3,4];
    return (
        <Grid container>
        {
            starArr.map((v,i) => {
                return (v < rate) ?
                <StarIcon key ={i} onClick={()=>{setRate(i+1)}}/>
                :
                <StarBorder key = {i} onClick={()=>{setRate(i+1)}}/>
            })
        }
        </Grid>
    )
}
import { StarBorder } from '@mui/icons-material';
import StarIcon from '@mui/icons-material/Star';

export const RatingStar = ({rate}) => {
    let starArr = [0,1,2,3,4];
    console.log(starArr)
    return (
        <div>
        {
            starArr.map((v,i) => {
                return (v < rate) ?
                <StarIcon key ={i}/>
                :
                <StarBorder key = {i}/>
            })
        }
        </div>
    )
}
import { Typography } from "@mui/material"


export const MainTitleText = (props) => {
    return <Typography variant="h5" sx={{fontWeight:"bold", mt:2, mb:3}}>{props?.children}</Typography>
}
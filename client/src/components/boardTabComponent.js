import { Tab, Tabs } from "@mui/material";
import { useNavigate } from "react-router-dom";
import React from "react";

export const BoardTab = ({curr, setPage}) => {
    console.log(curr, setPage)
    const [value, setValue] = React.useState(curr||"ALL");
    const navigate = useNavigate();
    const handleChange = (_, newValue) => {
        setValue(newValue);
        (!!setPage) && setPage(1);
        if(newValue === "ALL") {
            navigate("/boardMain");
        } else {
            navigate("/board/"+newValue);
        }
    };
    
    return <> 
        <Tabs align="center" value={value} onChange={handleChange} sx={{mb:2}}>
            <Tab value="ALL" label="전체보기"></Tab>
            <Tab value="FREE" label="자유게시판"></Tab>
            <Tab value="WANNABE" label="지망생"></Tab>
            <Tab value="STUDENT" label="수강생"></Tab>
            <Tab value="GRADUATE" label="수료생"></Tab>
        </Tabs>
    </>
}
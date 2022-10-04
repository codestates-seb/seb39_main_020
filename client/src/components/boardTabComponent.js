import { Tab, Tabs, Typography } from "@mui/material";
import { Link as RouterLink, useNavigate, useOutletContext } from "react-router-dom";
import React from "react";

export const BoardTab = ({curr}) => {
    const [value, setValue] = React.useState(curr||"ALL");
    const navigate = useNavigate();

    const handleChange = (event, newValue) => {
        setValue(newValue);
        if(newValue === "ALL") {
            navigate("/boardMain");
        } else {
            navigate("/board?type="+newValue);
        }
    };
    
    return <> 
        <Tabs align="center" value={value} onChange={handleChange}>
            <Tab value="ALL" label="전체보기"></Tab>
            <Tab value="FREE" label="자유게시판"></Tab>
            <Tab value="WANNABE" label="지망생"></Tab>
            <Tab value="STUDENT" label="수강생"></Tab>
            <Tab value="GRADUATE" label="수료생"></Tab>
        </Tabs>
    </>
}
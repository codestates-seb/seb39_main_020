import { Tab, Tabs } from "@mui/material";
import { useNavigate } from "react-router-dom";
import React from "react";

export const InstituteTab = ({curr,id}) => {
    const [value, setValue] = React.useState(curr||"INSTITUTE");
    const navigate = useNavigate();

    const handleChange = (event, newValue) => {
        setValue(newValue);
        navigate(`/institute/${newValue}/${id}`);
    };
    
    return <> 
        <Tabs align="center" value={value} onChange={handleChange}>
            <Tab value="INSTITUTE" label="교육기관정보"></Tab>
            <Tab value="COURSE" label="수강과정정보"></Tab>
            <Tab value="REVIEW" label="수강후기"></Tab>
        </Tabs>
    </>
}
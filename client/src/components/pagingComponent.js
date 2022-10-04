import { Button, ButtonGroup, Container, Pagination } from "@mui/material";
import React from "react";

const makePaging = (page={total:0, limit:10, offset:0}, pagePerPageSet = 10, setter) => {
    const {total, limit, offset} = page;
    const totalPage = Math.ceil(total/limit) || 1;
    const currentPage = Math.ceil(offset/limit)+1 || 1;
    const startPage = (Math.floor((currentPage-1) / pagePerPageSet) * pagePerPageSet + 1)|| 1;
    
    let endPage = Math.ceil(currentPage / pagePerPageSet) * pagePerPageSet;
    endPage = (endPage > totalPage) ? totalPage : endPage;
    const isStart = currentPage === 1;
    const isEnd = totalPage === currentPage;


    return {total, limit, offset, totalPage, currentPage, endPage}
}

const PagingLayout = (props) => {
    var pagingOpt = makePaging(props?.paging, props?.pagePerPageSet, props?.setter);
    
    const [page, setPage] = React.useState(1);
    const handleChange = (event, value) => {
      setPage(value);
      props?.setter((value-1)*pagingOpt.limit);
    };

    return (
        <Pagination align="center" count={pagingOpt?.totalPage} showFirstButton showLastButton onChange={handleChange}/>
        )
}
export default PagingLayout;
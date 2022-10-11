import { Pagination } from "@mui/material";
import React, { useEffect } from "react";

const makePaging = (pageInfo={totalElements:0, size:10, page:1}, pagePerPageSet = 10) => {
    const {totalElements, size, page} = pageInfo;
    const totalPage = Math.ceil(totalElements/size) || 1;
    //const currentPage = Math.ceil(offset/limit)+1 || 1;
    const offset = page-1*size;
    let endPage = Math.ceil(page / pagePerPageSet) * pagePerPageSet;
    endPage = (endPage > totalPage) ? totalPage : endPage;

    return {totalElements, limit: size, offset, totalPage, currentPage: page, endPage}
}

const PagingLayout = (props) => {
    var pagingOpt = makePaging(props?.paging, props?.pagePerPageSet, props?.setter);
    
    const [page, setPage] = React.useState(1);
    const handleChange = (_, value) => {
      //setPage(value);
      props?.setter(value);
    };
    useEffect(()=> {
        setPage(pagingOpt.currentPage)
    },[pagingOpt]);
    console.log(pagingOpt)
    return (
        <Pagination align="center" count={pagingOpt?.totalPage} showFirstButton showLastButton onChange={handleChange} page={page}/>
    )
}
export default PagingLayout;
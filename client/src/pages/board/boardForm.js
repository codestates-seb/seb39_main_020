import { Button, Container, Grid, Input, Table, TableBody, TableCell, TableRow } from "@mui/material";
import { queryAllByAltText } from "@testing-library/react";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams, useNavigate, useOutletContext } from "react-router-dom";
import { BoardTab } from "../../components/boardTabComponent";
import { MainTitleText } from "../../components/commonComponent";
import { FormHidden, FormText, FormTextArea } from "../../components/formComponents";
import { fetchBoard, initList, initView, postBoard, putBoard } from "../../reducers/boardReducer";

export default function BoardForm() {
    const params = useParams();
    const navigate = useNavigate();

    const { view, error, loading } = useSelector((state) => state.board);
        
    /* 공통 오류/로딩 처리 시작*/
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => {if(!!error) snackbar.open(error, 'error')}, [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    const mode = (params.id) ? 'UPDATE' : 'CREATE';
    const cancelUrl = (mode === 'UPDATE') ? `/board/${params.board}/${params.id}` : `/board/${params.board}`

    const dispatch = useDispatch();
    const { handleSubmit, control } = useForm();
    const onSubmit  = (data) => {
        if (mode === 'UPDATE') {
            dispatch(putBoard({
                board: params.board,
                id: params.id,
                data,
                successFn:()=>{
                    dispatch(initView());
                    navigate(cancelUrl,{replace:true});
                    snackbar.open("수정을 성공하였습니다.");
                }
            }));
        } else if(mode === 'CREATE') {
            dispatch(postBoard({
                board: params.board,
                data,
                successFn:()=>{
                    dispatch(initList());
                    navigate(cancelUrl,{replace:true});
                    snackbar.open("등록을 성공하였습니다.");
                }
            }));
        }
    }
    
    useEffect(() => {
        (mode === 'UPDATE') && dispatch(fetchBoard({id:params.id}))       
    },[dispatch, params, mode]);
    
    return (
        <Container>
            <MainTitleText>커뮤니티</MainTitleText>
            <BoardTab curr={params.board}/>

            <form name="bform" onSubmit={handleSubmit(onSubmit)} noValidate>
                <Table>
                    <TableBody>
                        <TableRow>                        
                            <TableCell>
                                <FormText 
                                    fullWidth
                                    name="title"
                                    control={control} 
                                    defaultValue={view.title} 
                                    rules={{required:"제목을 입력하세요"}} 
                                    label="제목"
                                />
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>
                                <FormTextArea
                                    fullWidth 
                                    name="content" 
                                    control={control} 
                                    defaultValue={view.content}
                                    rules={{required:"내용을 입력하세요"}}
                                    label="내용"
                                />
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
                <FormHidden control={control} name="memberId" defaultValue="test"/>
                <Button type="submit">{(mode==='CREATE')?'등록':'수정'}</Button>
                <Button component={Link} to={cancelUrl}>취소</Button>
            </form>
        </Container>
    )
}
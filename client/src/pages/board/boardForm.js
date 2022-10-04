import { Button, Input, Table, TableBody, TableCell, TableRow } from "@mui/material";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams, useNavigate, useOutletContext } from "react-router-dom";
import { FormText, FormTextArea } from "../../components/formComponents";
import { fetchBoard, initList, initView, postBoard, putBoard } from "../../reducers/boardReducer";

export default function BoardForm() {
    const params = useParams();
    const navigate = useNavigate();

    const { view, error, loading } = useSelector((state) => state.board);
        
    /* 공통 오류/로딩 처리 시작*/
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    const mode = (params.id) ? 'UPDATE' : 'CREATE';
    const cancelUrl = (mode === 'UPDATE') ? `/p/board/${params.id}` : `/p/board`

    const dispatch = useDispatch();
    const { handleSubmit, control, register, reset } = useForm();
    const onSubmit  = (data) => {
        if (mode === 'UPDATE') {
            dispatch(putBoard({
                data,
                successFn:()=>{
                    navigate(cancelUrl,{replace:true});
                    snackbar.open("수정을 성공하였습니다.");
                }
            }));
        } else if(mode === 'CREATE') {
            dispatch(initList());
            dispatch(postBoard({
                data,
                successFn:()=>{
                    navigate(cancelUrl,{replace:true});
                    snackbar.open("수정을 성공하였습니다.");
                }
            }));
        }
    }
    
    useEffect(() => {
        (mode === 'UPDATE') && dispatch(fetchBoard({id:params.id}))       
    },[dispatch, params, mode]);
    
    return (
        <div>
            <h3>게시물 {mode === 'UPDATE'?'수정':'등록'}</h3>
            <form name="bform" onSubmit={handleSubmit(onSubmit)} noValidate>
                <Table>
                    <TableBody>
                        {(mode === 'UPDATE') && (
                        <TableRow>
                            <TableCell>{view.id}<Input {...register("id")} type="hidden" name="id" value={view.id||0}/></TableCell>
                        </TableRow>
                        )}
                        <TableRow>                        
                            <TableCell>
                                <FormText 
                                    fullWidth
                                    name="title"
                                    control={control} 
                                    defaultValue={view.title} 
                                    rules={{required:"here1"}} 
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
                                    rules={{required:"here2"}}
                                    label="내용"
                                />
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>
                                <FormText 
                                    fullWidth
                                    name="writer" 
                                    control={control} 
                                    defaultValue={view.writer} 
                                    label="등록자"
                                />
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
                {mode === 'UPDATE' ?
                <Button type="submit">수정</Button>
                :
                <Button type="submit">등록</Button>
                }
                <Button onClick={()=>reset()}>리셋</Button>               
                <Button component={Link} to={cancelUrl}>취소</Button>
            </form>
        </div>
    )
}
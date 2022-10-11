import { Button, Table, TableBody, TableCell, TableRow } from "@mui/material";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate, useOutletContext } from "react-router-dom";
import { MainTitleText } from "../../components/commonComponent";
import { FormHidden, FormText, FormTextArea } from "../../components/formComponents";
import { postQuestion } from "../../reducers/qnaReducer";


export default function QnaForm() {
    const navigate = useNavigate();

    const { view, error, loading } = useSelector((state) => state.board);
        
    /* 공통 오류/로딩 처리 시작*/
    const { snackbar, loader } = useOutletContext();
    useEffect(() => loader.setLoading(loading), [loader, loading]);
    useEffect(() => snackbar.open(error, 'danger'), [error, snackbar]);
    /* 공통 오류/로딩 처리 끝 */

    const cancelUrl = `/qna`;

    const dispatch = useDispatch();
    const { handleSubmit, control } = useForm();
    const onSubmit  = (data) => {
        console.log(data)
        dispatch(postQuestion({
            data,
            successFn:()=>{
                navigate(cancelUrl,{replace:true});
                snackbar.open("등록을 성공하였습니다.");
            }
        }));
    }
    
    return (
        <div>
            <MainTitleText>질문/답변</MainTitleText>
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
                <Button type="submit">등록</Button>
                <Button component={Link} to={cancelUrl}>취소</Button>
            </form>
        </div>
    )
}
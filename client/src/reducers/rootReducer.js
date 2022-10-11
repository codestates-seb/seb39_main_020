import boardReducer from './boardReducer'
import qnaReducer from './qnaReducer';
import instituteReducer from './instituteReducer';

const reducer = {
    "board" : boardReducer,
    "qna" : qnaReducer,
    "institute" : instituteReducer,
}

export default reducer;
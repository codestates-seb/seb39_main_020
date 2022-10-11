import { aget, apost } from "../configs/axiosConfig";

const targetBE = process.env.REACT_APP_BACKEND_URI+'/main/qna'; 

const getMainQuestionsApi = () => { 
    return aget(`${targetBE}/main`);
};

const getQuestionsApi = (payload) => { 
    return aget(`${targetBE}/questions`,payload);
};

const getQuestionApi = (id) => {
    return aget(`${targetBE}/questions/${id}`);
}

const getAnswersApi = (id) => { 
    return aget(`${targetBE}/questions/${id}/answers`);
}

const postQuestionApi = (payload) => {
    return apost(`${targetBE}/questions`,payload)
}

const postAnswerApi = (id, payload) => {
    console.log(id, payload)
    return apost(`${targetBE}/questions/${id}/answers`,payload);
}

export {getQuestionsApi, getMainQuestionsApi, getQuestionApi,getAnswersApi, postQuestionApi, postAnswerApi};
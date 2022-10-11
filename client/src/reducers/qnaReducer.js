import { createAction, createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import { getAnswersApi,  getMainQuestionsApi,  getQuestionApi, getQuestionsApi, postAnswerApi, postQuestionApi } from '../apis/qnaApi'

// actions 
export const fetchMainQuestions = createAsyncThunk('QNA/MAIN/QUESTIONS', () => {
  return getMainQuestionsApi();
});


export const fetchQuestions = createAsyncThunk('QNA/QUESTIONS', (payload) => {
  return getQuestionsApi(payload);
});

export const fetchQuestion = createAsyncThunk('QNA/QUESTION', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return getQuestionApi(id);
});

// actions 
export const fetchAnswers = createAsyncThunk('QNA/ANSWERS', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return getAnswersApi(id);
});


export const postQuestion = createAsyncThunk('QNA/CREATE_QUESTION', (payload) => {
  return !!(payload.data) && postQuestionApi(payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const postAnswer = createAsyncThunk('QNA/CREATE_ANSWER', (payload) => {
  return !!(payload.data) && postAnswerApi(payload.id, payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
})

export const initList = createAction("INIT_LIST");
export const initView = createAction("INIT_VIEW");
export const initAnswers = createAction("INIT_ANSWER");
export const initMainQuestions = createAction("INIT_MAIN_QUESTIONS");
// init states
const initialState = {
  list: {
    data:[],
    pageInfo:{}
  },
  view: {
    id:"",
    title:"",
    content:"",
    writer:"",
    regdate:""
  },
  mainQuestions: [],
  answers: [],
  loading : false,
  error: ""
}

const errorState = (err) => {
  return {...initialState, error: err} 
};

// slice
export const qnaSlice = createSlice({
  name: 'qna',
  initialState,
  reducers: {

  },
  extraReducers: {
    [initList] : (state) => {
      state.list = [];
    },
    [initView] : (state) => {
      state.view = {};
    },
    [initAnswers] : (state) => {
      state.answers = [];
    },
    [initMainQuestions] : (state) => {
      state.mainQuestions = [];
    },
    [fetchMainQuestions.pending] : (state) => {
      state.mainQuestions= [];
      state.loading = true;
      state.error = "";
    },
    [fetchMainQuestions.fulfilled] : (state, action) => {
      state.mainQuestions= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchMainQuestions.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchQuestion.pending] : (state) => {
      state.view= [];
      state.loading = true;
      state.error = "";
    },
    [fetchQuestions.fulfilled] : (state, action) => {
      state.list= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchQuestions.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchQuestion.pending] : (state) => {
      state.view= [];
      state.loading = true;
      state.error = "";
    },
    [fetchQuestion.fulfilled] : (state, action) => {
      state.view= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchQuestion.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchAnswers.pending] : (state) => {
      state.answers= [];
      state.loading = true;
      state.error = "";
    },
    [fetchAnswers.fulfilled] : (state, action) => {
      state.answers= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchAnswers.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [postQuestion.rejected] : (_, action) => {
      errorState(action.payload)
    },
  }
})

export default qnaSlice.reducer
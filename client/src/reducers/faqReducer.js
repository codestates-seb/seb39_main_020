import { createAction, createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import { deleteBoardApi, getBoardApi, getBoardsApi, postBoardApi, putBoardApi } from '../apis/boardApi'

// actions 
export const fetchFaqs = createAsyncThunk('FAQ/FAQS', (payload) => {
  return getBoardsApi(payload);
});

export const fetchFaq = createAsyncThunk('FAQ/FAQ', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return getBoardApi(id);
});

export const postFaq = createAsyncThunk('FAQ/CREATE_FAQ', (payload) => {
  return !!(payload.data) && postBoardApi(payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const putFaq = createAsyncThunk('FAQ/UPDATE_FAQ', (payload) => {
  console.log(payload)
  const id = (!!payload?.data && typeof payload.data === 'object') ? payload?.data?.id : 0;
  return !!(payload) && putBoardApi(id, payload.data).then(() => {
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const deleteFaq = createAsyncThunk('FAQ/DELETE_FAQ', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return deleteBoardApi(id);
});

export const initList = createAction("INIT_LIST");
export const initView = createAction("INIT_VIEW");

// init states
const initialState = {
  list: {
    item:[],
    paging:{}
  },
  view: {
    id:"",
    title:"",
    content:"",
    writer:"",
    regdate:""
  },
  loading : false,
  error: ""
}

const errorState = (err) => {
  return {...initialState, error: err} 
};

// slice
export const faqSlice = createSlice({
  name: 'faq',
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
    [fetchFaqs.pending] : (state) => {
      state.list= [];
      state.loading = true;
      state.error = "";
    },
    [fetchFaqs.fulfilled] : (state, action) => {
      state.list= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchFaqs.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchFaq.pending] : (state) => {
      state.view= [];
      state.loading = true;
      state.error = "";
    },
    [fetchFaq.fulfilled] : (state, action) => {
      state.view= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchFaq.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [postFaq.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [putFaq.fulfilled] : (state) => {
      state.error = "";
    },
    [putFaq.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [deleteFaq.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [deleteFaq.fulfilled] : (state) => {
      state.view= {};
      state.loading = false;
      state.error = "";
    }
  }
})

export default faqSlice.reducer
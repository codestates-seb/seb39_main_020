import { createAction, createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import { deleteBoardApi, getBoardApi, getBoardsApi, postBoardApi, putBoardApi } from '../apis/boardApi'

// actions 
export const fetchInstitutes = createAsyncThunk('INSTITUTE/INSTITUTES', (payload) => {
  return getBoardsApi(payload);
});

export const fetchInstitute = createAsyncThunk('INSTITUTE/INSTITUTE', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return getBoardApi(id);
});

export const postInstitute = createAsyncThunk('INSTITUTE/CREATE_INSTITUTE', (payload) => {
  return !!(payload.data) && postBoardApi(payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const putInstitute = createAsyncThunk('INSTITUTE/UPDATE_INSTITUTE', (payload) => {
  const id = (!!payload?.data && typeof payload.data === 'object') ? payload?.data?.id : 0;
  return !!(payload) && putBoardApi(id, payload.data).then(() => {
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const deleteInstitute = createAsyncThunk('FAQ/DELETE_Institute', (payload) => {
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
export const instituteSlice = createSlice({
  name: 'institute',
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
    [fetchInstitutes.pending] : (state) => {
      state.list= [];
      state.loading = true;
      state.error = "";
    },
    [fetchInstitutes.fulfilled] : (state, action) => {
      state.list= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchInstitutes.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchInstitute.pending] : (state) => {
      state.view= [];
      state.loading = true;
      state.error = "";
    },
    [fetchInstitute.fulfilled] : (state, action) => {
      state.view= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchInstitute.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [postInstitute.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [putInstitute.fulfilled] : (state) => {
      state.error = "";
    },
    [putInstitute.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [deleteInstitute.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [deleteInstitute.fulfilled] : (state) => {
      state.view= {};
      state.loading = false;
      state.error = "";
    }
  }
})

export default instituteSlice.reducer
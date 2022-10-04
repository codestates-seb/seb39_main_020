import { createAction, createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import { deleteBoardApi, getBoardApi, getBoardsApi, postBoardApi, putBoardApi } from '../apis/boardApi'

// actions 
export const fetchBoards = createAsyncThunk('BOARD/BOARDS', (payload) => {
  return getBoardsApi(payload);
});

export const fetchBoard = createAsyncThunk('BOARD/BOARD', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  return getBoardApi(id);
});

export const postBoard = createAsyncThunk('BOARD/CREATE_BOARD', (payload) => {
  return !!(payload.data) && postBoardApi(payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const putBoard = createAsyncThunk('BOARD/UPDATE_BOARD', (payload) => {
  console.log(payload)
  const id = (!!payload?.data && typeof payload.data === 'object') ? payload?.data?.id : 0;
  return !!(payload) && putBoardApi(id, payload.data).then(() => {
    (!!payload.successFn) && payload.successFn();
    return payload;
  });
});

export const deleteBoard = createAsyncThunk('BOARD/DELETE_BOARD', (payload) => {
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
export const boardSlice = createSlice({
  name: 'board',
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
    [fetchBoards.pending] : (state) => {
      state.list= [];
      state.loading = true;
      state.error = "";
    },
    [fetchBoards.fulfilled] : (state, action) => {
      state.list= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchBoards.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [fetchBoard.pending] : (state) => {
      state.view= [];
      state.loading = true;
      state.error = "";
    },
    [fetchBoard.fulfilled] : (state, action) => {
      state.view= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchBoard.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [postBoard.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [putBoard.fulfilled] : (state) => {
      state.error = "";
    },
    [putBoard.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [deleteBoard.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [deleteBoard.fulfilled] : (state) => {
      state.view= {};
      state.loading = false;
      state.error = "";
    }
  }
})

export default boardSlice.reducer
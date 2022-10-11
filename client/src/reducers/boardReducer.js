import { createAction, createAsyncThunk, createSlice, isRejectedWithValue } from '@reduxjs/toolkit'
import { deleteBoardApi, getBoardApi, getBoardMainApi, getBoardsApi, getCommentsApi, getMainboardsApi, postBoardApi, postCommentsApi, putBoardApi } from '../apis/boardApi'

// actions 
export const fetchMainBoards = createAsyncThunk('BOARD/MAIN_BOARDS', () => {
  return getMainboardsApi();
});

export const fetchBoardMain = createAsyncThunk('BOARD/BOARD_MAIN', () => {
  return getBoardMainApi();
});

export const fetchBoards = createAsyncThunk('BOARD/BOARDS', (payload) => {
  return getBoardsApi(payload.board,payload);
});

export const fetchBoard = createAsyncThunk('BOARD/BOARD', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  const board = (!!payload && typeof payload === 'object') ? payload.board : payload;
  return getBoardApi(board,id);
});

export const postBoard = createAsyncThunk('BOARD/CREATE_BOARD', (payload,{rejectWithValue}) => {
  return !!(payload.data) && postBoardApi(payload.board, payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  }).catch((err)=>{
    (!!payload.failFn) && payload.failFn();
    return rejectWithValue(err);
  });
});

export const putBoard = createAsyncThunk('BOARD/UPDATE_BOARD', (payload) => {
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

export const fetchComments = createAsyncThunk('BOARD/COMMENTS', (payload) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  const board = (!!payload && typeof payload === 'object') ? payload.board : payload;
  return getCommentsApi(board,id);
});

export const postComments = createAsyncThunk('BOARD/CREATE_COMMENT', (payload, {rejectWithValue}) => {
  const id = (!!payload && typeof payload === 'object') ? payload.id : payload;
  const board = (!!payload && typeof payload === 'object') ? payload.board : payload;
  return !!(payload.data) && postCommentsApi(board,id,payload.data).then(() =>{
    (!!payload.successFn) && payload.successFn();
    return payload;
  }).catch((err)=>{
    return rejectWithValue(err);
  });
});

export const initList = createAction("INIT_LIST");
export const initView = createAction("INIT_VIEW");
export const initComments = createAction("INIT_COMMENTS");

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
  mainBoard: [],
  comments: [],
  boardMain: {FREE:[],WANNABE:[],STUDENT:[],GRADUATE:[]},
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
      state.list = initialState.list;
    },
    [initView] : (state) => {
      state.view = {};
    },
    [initComments]: (state) => {
      state.comments = [];
    },
    [fetchMainBoards.pending] : (state) => {
      state.mainBoard= [];
      state.loading = true;
      state.error = "";
    },
    [fetchMainBoards.fulfilled] : (state, action) => {
      state.mainBoard= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchMainBoards.rejected] : (state, action) => {
      state.error= action.payload
    },
    [fetchBoardMain.pending] : (state) => {
      state.boardMain= initialState.boardMain;
      state.loading = true;
      state.error = "";
    },
    [fetchBoardMain.fulfilled] : (state, action) => {
      state.boardMain= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchBoardMain.rejected] : (state, action) => {
      state.error= action.payload
    },
    [fetchBoards.pending] : (state) => {
      state.list= initialState.list;
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
      state.view= initialState.view;
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
    [postBoard.fulfilled] : (state) => {
      state.error = "";
    },
    [postBoard.rejected] : (state, action) => {
      console.log(action)
      state.error= action.payload;
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
    },
    [fetchComments.pending] : (state) => {
      state.comments= initialState.comments;
      state.loading = true;
      state.error = "";
    },
    [fetchComments.fulfilled] : (state, action) => {
      state.comments= action.payload;
      state.loading = false;
      state.error = "";
    },
    [fetchComments.rejected] : (_, action) => {
      errorState(action.payload)
    },
    [postComments.rejected] : (_, action) => {
      errorState(action.payload)
    },
  }
})

export default boardSlice.reducer